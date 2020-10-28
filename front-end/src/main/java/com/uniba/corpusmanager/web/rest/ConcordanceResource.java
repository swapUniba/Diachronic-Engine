package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.service.ActionLogService;
import com.uniba.corpusmanager.service.CorpusService;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import com.uniba.corpusmanager.service.dto.ConcordanceResultDTO;
import com.uniba.corpusmanager.service.dto.ConcordanceContextDTO;
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

import static com.uniba.corpusmanager.domain.enumeration.Action.CONCORDANCE;

/**
 * REST controller for managing {@link com.uniba.corpusmanager.domain.Corpus}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ConcordanceResource {

    private final ActionLogService actionLogService;
    private final CorpusService corpusService;

    public ConcordanceResource(ActionLogService actionLogService, CorpusService corpusService) {
        this.actionLogService = actionLogService;
        this.corpusService = corpusService;
    }

    @GetMapping("/concordance/result")
    public ResponseEntity<ConcordanceResultDTO> getConcordanceResult(
        @RequestParam @Nullable Long page,
        @RequestParam Long corpusId,
        @RequestParam String search,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date startDate,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date endDate
    ) {
        log.debug("REST request to get concordance result for corpus {}, search {}, start date {}, end date {}",
            corpusId, search, startDate, endDate
        );
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            throw new InvalidInputDateException();
        }
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/concordance/result")
        .queryParam("page", page)
        .queryParam("corpusId", corpusId)
        .queryParam("search", search)
        .queryParam("startDate", startDate)
        .queryParam("endDate", endDate);
        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET,requestEntity, String.class);
        String body = responseEntity.getBody();
        Map<String, Object> mapbody = new BasicJsonParser().parseMap(body);
        List<Object> resultlist = (List<Object>) mapbody.get("values");
        Long n_items = (Long) mapbody.get("n_items");
        List<ConcordanceContextDTO> values = new ArrayList<ConcordanceContextDTO>();
        for(Object entry : resultlist){
           Map<String,Object> map = null;
           if(entry.getClass().equals(String.class)){
              map = new BasicJsonParser().parseMap((String)entry);
           }else{
              map = (Map<String, Object>) entry;
           }
           String source = (String) map.get("source");
           String[] date = ((String) map.get("date")).split("-");
           LocalDate ldate = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
           String leftContext = (String) map.get("leftContext");
           String kwic = (String) map.get("kwic");
           String rightContext = (String) map.get("rightContext");
           values.add(ConcordanceContextDTO.builder()
            .source(source)
            .date(ldate)
            .leftContext(leftContext)
            .kwic(kwic)
            .rightContext(rightContext)
            .build()
            );
        }
        ConcordanceResultDTO body_formatted = ConcordanceResultDTO.builder()
                       .corpusId(corpusId)
                       .n_items(n_items)
                       .search(search)
                       .startDate(LocalDate.of(2000, 1, 1))
                       .endDate(LocalDate.of(2009, 12, 31))
                       .values(values)
                       .build();
        String requestPath = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        actionLogService.save(
            ActionLogDTO.builder()
                .corpusId(corpusId)
                .corpusName(corpusService.findOne(corpusId).get().getName())
                .action(CONCORDANCE)
                .request(requestPath.substring(requestPath.indexOf("/api") + 4))
                .build()
        );
        return ResponseEntity.ok().body(body_formatted);
    }
}
