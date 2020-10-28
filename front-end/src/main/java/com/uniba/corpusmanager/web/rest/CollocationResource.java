package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.service.ActionLogService;
import com.uniba.corpusmanager.service.CorpusService;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import com.uniba.corpusmanager.service.dto.CollocationResultDTO;
import com.uniba.corpusmanager.domain.enumeration.CollocationType;
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
import java.util.List;

import static com.uniba.corpusmanager.domain.enumeration.Action.COLLOCATION;

/**
 * REST controller for managing {@link com.uniba.corpusmanager.domain.Corpus}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class CollocationResource {

    private final ActionLogService actionLogService;
    private final CorpusService corpusService;

    public CollocationResource(ActionLogService actionLogService, CorpusService corpusService) {
        this.actionLogService = actionLogService;
        this.corpusService = corpusService;
    }

    @GetMapping("/collocation/result")
    public ResponseEntity<List<CollocationResultDTO>> getCollocationResult(
        @RequestParam Long corpusId,
        @RequestParam String search,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date startDate,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @Nullable Date endDate
    ) {
        log.debug("REST request to get collocation result for corpus {}, search {}, start date {}, end date {}",
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
UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/collocation/result")
        .queryParam("corpusId", corpusId)
        .queryParam("search", search)
        .queryParam("startDate", startDate)
        .queryParam("endDate", endDate);
        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET,requestEntity, String.class);
        String body = responseEntity.getBody();
        Map<String, Object> mapbody = new BasicJsonParser().parseMap(body);
        List<String> values = (List<String>) mapbody.get("values");
        CollocationResultDTO results = CollocationResultDTO.builder()
                       .corpusId(corpusId)
                       .search(search)
                       .startDate(LocalDate.of(2000, 1, 1))
                       .endDate(LocalDate.of(2009, 12, 31))
                       .type(CollocationType.VERB)
                       .values(values)
                       .build();
        List<CollocationResultDTO> body_formatted = new ArrayList<CollocationResultDTO>();
        body_formatted.add(results);
        String requestPath = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        actionLogService.save(
            ActionLogDTO.builder()
                .corpusId(corpusId)
                .corpusName(corpusService.findOne(corpusId).get().getName())
                .action(COLLOCATION)
                .request(requestPath.substring(requestPath.indexOf("/api") + 4))
                .build()
        );
        return ResponseEntity.ok().body(body_formatted);
    }
}
