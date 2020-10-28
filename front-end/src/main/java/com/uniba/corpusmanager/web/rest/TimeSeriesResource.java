package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.service.ActionLogService;
import com.uniba.corpusmanager.service.CorpusService;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import com.uniba.corpusmanager.service.dto.TimeSeriesResultDTO;
import com.uniba.corpusmanager.service.dto.TimeSeriesItemDTO;
import com.uniba.corpusmanager.web.rest.errors.InvalidInputDateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nullable;
import java.util.Date;
import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.uniba.corpusmanager.domain.enumeration.Action.TIME_SERIES;

/**
 * REST controller for managing {@link com.uniba.corpusmanager.domain.Corpus}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class TimeSeriesResource {

    private final ActionLogService actionLogService;
    private final CorpusService corpusService;

    public TimeSeriesResource(ActionLogService actionLogService, CorpusService corpusService) {
        this.actionLogService = actionLogService;
        this.corpusService = corpusService;
    }

    @GetMapping("/time-series/result")
    public ResponseEntity<TimeSeriesResultDTO> getCollocationResult(
        @RequestParam Long corpusId,
        @RequestParam @Nullable List<String> searchTerms,
        @RequestParam @Nullable List<String> firstTerms,
        @RequestParam @Nullable List<String> secondTerms,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date startDate,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date endDate
    ) {
        log.debug("REST request to get time series for corpus {}, search terms {}, first terms {}, second terms {}, start date {}, end date {}",
            corpusId, searchTerms, firstTerms, secondTerms, startDate, endDate
        );
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            throw new InvalidInputDateException();
        }
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/time-series/result")
        .queryParam("corpusId", corpusId)
        .queryParam("searchTerms", searchTerms)
        .queryParam("firstTerms", firstTerms)
        .queryParam("secondTerms", secondTerms)
        .queryParam("startDate", startDate)
        .queryParam("endDate", endDate);
        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET,requestEntity, String.class);
        String body = responseEntity.getBody();
        Map<String, Object> mapbody = new BasicJsonParser().parseMap(body);
        Map<String,Object> map = (Map<String,Object>) mapbody.get("values");

        List<TimeSeriesItemDTO> values = new ArrayList<TimeSeriesItemDTO>();
        List<TimeSeriesItemDTO> changepoints = new ArrayList<TimeSeriesItemDTO>();


        List<String> words = (ArrayList<String>) map.get("words");
        List<String> xlabels = (ArrayList<String>) map.get("xlabels");
        Map<String,Object> items = (Map<String,Object>) map.get("items");
        Map<String,Object> cps = (Map<String,Object>) map.get("cps");

        for (Map.Entry<String, Object> entry : items.entrySet()) {
            String k = entry.getKey();
            ArrayList<Double> val = (ArrayList<Double>) entry.getValue();
            values.add(TimeSeriesItemDTO.builder().
                       word(k).
                       values(val).
                       build()
                      );
        }
        for (Map.Entry<String, Object> entry : cps.entrySet()) {
            String k = entry.getKey();
            ArrayList<String> val_string = (ArrayList<String>) entry.getValue();
            ArrayList<Double> val = new ArrayList<Double>();
            for(Object str: val_string){
               try{
                   val.add(Double.parseDouble(str.toString()));
               }catch(NumberFormatException e){
                   val.add(null);
               }
            }
            changepoints.add(TimeSeriesItemDTO.builder().
                       word(k).
                       values(val).
                       build()
                      );
        }
        TimeSeriesResultDTO body_formatted = TimeSeriesResultDTO.builder().
                       corpusId(corpusId).
                       searchTerms(searchTerms).
                       startDate(LocalDate.of(2000, 1, 1)).
                       endDate(LocalDate.of(2009, 12, 31)).
                       words(words).
                       xlabels(xlabels).
                       values(values).
                       changepoints(changepoints).
                       build();

        String requestPath = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        actionLogService.save(
            ActionLogDTO.builder()
                .corpusId(corpusId)
                .corpusName(corpusService.findOne(corpusId).get().getName())
                .action(TIME_SERIES)
                .request(requestPath.substring(requestPath.indexOf("/api") + 4))
                .build()
        );
        return ResponseEntity.ok().body(body_formatted);
    }
}
