package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.service.ActionLogService;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import com.uniba.corpusmanager.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.uniba.corpusmanager.domain.ActionLog}.
 */
@RestController
@RequestMapping("/api")
public class ActionLogResource {

    private static final String ENTITY_NAME = "actionLog";
    private final Logger log = LoggerFactory.getLogger(ActionLogResource.class);
    private final ActionLogService actionLogService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public ActionLogResource(ActionLogService actionLogService) {
        this.actionLogService = actionLogService;
    }

    /**
     * {@code POST  /action-logs} : Create a new actionLog.
     *
     * @param actionLogDTO the actionLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new actionLogDTO, or with status {@code 400 (Bad Request)} if the actionLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/action-logs")
    public ResponseEntity<ActionLogDTO> createActionLog(@Valid @RequestBody ActionLogDTO actionLogDTO) throws URISyntaxException {
        log.debug("REST request to save ActionLog : {}", actionLogDTO);
        if (actionLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new actionLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActionLogDTO result = actionLogService.save(actionLogDTO);
        return ResponseEntity.created(new URI("/api/action-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /action-logs} : Updates an existing actionLog.
     *
     * @param actionLogDTO the actionLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actionLogDTO,
     * or with status {@code 400 (Bad Request)} if the actionLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the actionLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/action-logs")
    public ResponseEntity<ActionLogDTO> updateActionLog(@Valid @RequestBody ActionLogDTO actionLogDTO) throws URISyntaxException {
        log.debug("REST request to update ActionLog : {}", actionLogDTO);
        if (actionLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActionLogDTO result = actionLogService.save(actionLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, actionLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /action-logs} : get all the actionLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of actionLogs in body.
     */
    @GetMapping("/action-logs")
    public ResponseEntity<List<ActionLogDTO>> getAllActionLogs(Pageable pageable) {
        log.debug("REST request to get a page of ActionLogs");
        Page<ActionLogDTO> page = actionLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /action-logs/:id} : get the "id" actionLog.
     *
     * @param id the id of the actionLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actionLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/action-logs/{id}")
    public ResponseEntity<ActionLogDTO> getActionLog(@PathVariable Long id) {
        log.debug("REST request to get ActionLog : {}", id);
        Optional<ActionLogDTO> actionLogDTO = actionLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actionLogDTO);
    }

    /**
     * {@code DELETE  /action-logs/:id} : delete the "id" actionLog.
     *
     * @param id the id of the actionLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/action-logs/{id}")
    public ResponseEntity<Void> deleteActionLog(@PathVariable Long id) {
        log.debug("REST request to delete ActionLog : {}", id);
        actionLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/action-logs?query=:query} : search for the actionLog corresponding
     * to the query.
     *
     * @param query    the query of the actionLog search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/action-logs")
    public ResponseEntity<List<ActionLogDTO>> searchActionLogs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ActionLogs for query {}", query);
        Page<ActionLogDTO> page = actionLogService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
