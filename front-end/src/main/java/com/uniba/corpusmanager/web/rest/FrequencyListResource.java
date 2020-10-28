package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.service.ActionLogService;
import com.uniba.corpusmanager.service.CorpusService;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import com.uniba.corpusmanager.domain.enumeration.FrequencyListSearchFilter;
import com.uniba.corpusmanager.domain.enumeration.FrequencyListSearchType;
import com.uniba.corpusmanager.service.dto.FrequencyItemDTO;
import com.uniba.corpusmanager.service.dto.FrequencyListResultDTO;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.boot.json.BasicJsonParser;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.Date;

import static com.uniba.corpusmanager.domain.enumeration.Action.FREQUENCY_LIST;

/**
 * REST controller for managing {@link com.uniba.corpusmanager.domain.Corpus}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class FrequencyListResource {

    private final ActionLogService actionLogService;
    private final CorpusService corpusService;

    public FrequencyListResource(ActionLogService actionLogService, CorpusService corpusService) {
        this.actionLogService = actionLogService;
        this.corpusService = corpusService;
    }

    @GetMapping("/frequency-list/result")
    public ResponseEntity<FrequencyListResultDTO> getCollocationResult(
        @RequestParam Long corpusId,
        @RequestParam String searchType,
        @RequestParam String searchFilter,
        @RequestParam @Nullable String searchFilterText,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date startDate,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date endDate
    ) {
        log.debug("REST request to get frequency list result for corpus {}, search type {}, search filter {}, search filter text {}, start date {}, end date {}",
            corpusId, searchType, searchFilter, searchFilterText, startDate, endDate
        );
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            throw new InvalidInputDateException();
        }
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/frequency-list/result")
        .queryParam("corpusId", corpusId)
        .queryParam("searchType", searchType)
        .queryParam("searchFilter", searchFilter)
        .queryParam("searchFilterText", searchFilterText)
        .queryParam("startDate", startDate)
        .queryParam("endDate", endDate);
        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET,requestEntity, String.class);
        String body = responseEntity.getBody();
        Map<String, Object> mapbody = new BasicJsonParser().parseMap(body);
        Map<String,Object> map = (Map<String,Object>) mapbody.get("values");
        List<FrequencyItemDTO> values = new ArrayList<FrequencyItemDTO>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue().toString();
            values.add(FrequencyItemDTO.builder().
                       word(k).
                       frequency(Long.parseLong(v)).
                       build()
            );
        }
        FrequencyListResultDTO body_formatted = FrequencyListResultDTO.builder().
                       corpusId(corpusId).
                       searchType(searchType).
                       searchFilter(searchFilter).
                       startDate(LocalDate.of(2000, 1, 1)).
                       endDate(LocalDate.of(2009, 12, 31)).
                       values(values).
                       build();
        String requestPath = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        actionLogService.save(
            ActionLogDTO.builder()
                .corpusId(corpusId)
                .corpusName(corpusService.findOne(corpusId).get().getName())
                .action(FREQUENCY_LIST)
                .request(requestPath.substring(requestPath.indexOf("/api") + 4))
                .build()
        );
        return ResponseEntity.ok().body(body_formatted);
    }
}
