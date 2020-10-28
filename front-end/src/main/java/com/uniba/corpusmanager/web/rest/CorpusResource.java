package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.security.SecurityUtils;
import com.uniba.corpusmanager.service.CorpusService;
import com.uniba.corpusmanager.service.dto.CorpusDTO;
import com.uniba.corpusmanager.web.rest.errors.BadRequestAlertException;
import com.uniba.corpusmanager.web.rest.errors.InvalidInputDateException;
import com.uniba.corpusmanager.web.rest.errors.UnauthorizedException;
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

import static com.uniba.corpusmanager.security.AuthoritiesConstants.ADMIN;

/**
 * REST controller for managing {@link com.uniba.corpusmanager.domain.Corpus}.
 */
@RestController
@RequestMapping("/api")
public class CorpusResource {

    private static final String ENTITY_NAME = "corpus";
    private final Logger log = LoggerFactory.getLogger(CorpusResource.class);
    private final CorpusService corpusService;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public CorpusResource(CorpusService corpusService) {
        this.corpusService = corpusService;
    }

    /**
     * {@code POST  /corpora} : Create a new corpus.
     *
     * @param corpusDTO the corpusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new corpusDTO, or with status {@code 400 (Bad Request)} if the corpus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/corpora")
    public ResponseEntity<CorpusDTO> createCorpus(@Valid @RequestBody CorpusDTO corpusDTO) throws URISyntaxException {
        log.debug("REST request to save Corpus : {}", corpusDTO);
        if (corpusDTO.getId() != null) {
            throw new BadRequestAlertException("A new corpus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (corpusDTO.getPeriodStart() != null && corpusDTO.getPeriodEnd() != null && corpusDTO.getPeriodStart() > corpusDTO.getPeriodEnd()) {
            throw new InvalidInputDateException();
        }
        CorpusDTO result = corpusService.save(corpusDTO);
        return ResponseEntity.created(new URI("/api/corpora/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /corpora} : Updates an existing corpus.
     *
     * @param corpusDTO the corpusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated corpusDTO,
     * or with status {@code 400 (Bad Request)} if the corpusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the corpusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/corpora")
    public ResponseEntity<CorpusDTO> updateCorpus(@Valid @RequestBody CorpusDTO corpusDTO) throws URISyntaxException {
        log.debug("REST request to update Corpus : {}", corpusDTO);
        if (corpusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        corpusService.findOne(corpusDTO.getId()).ifPresent(this::throwExceptionIfNotEnabled);
        CorpusDTO result = corpusService.save(corpusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, corpusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /corpora} : get all the corpora.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of corpora in body.
     */
    @GetMapping("/corpora")
    public ResponseEntity<List<CorpusDTO>> getAllCorpora(Pageable pageable) {
        log.debug("REST request to get a page of corpora");
        Page<CorpusDTO> page = corpusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /corpora/:id} : get the "id" corpus.
     *
     * @param id the id of the corpusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the corpusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/corpora/{id}")
    public ResponseEntity<CorpusDTO> getCorpus(@PathVariable Long id) {
        log.debug("REST request to get Corpus : {}", id);
        Optional<CorpusDTO> corpusDTO = corpusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(corpusDTO);
    }

    /**
     * {@code DELETE  /corpora/:id} : delete the "id" corpus.
     *
     * @param id the id of the corpusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/corpora/{id}")
    public ResponseEntity<Void> deleteCorpus(@PathVariable Long id) {
        log.debug("REST request to delete Corpus : {}", id);
        corpusService.findOne(id).ifPresent(this::throwExceptionIfNotEnabled);
        corpusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/corpora?query=:query} : search for the corpus corresponding
     * to the query.
     *
     * @param query    the query of the corpus search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/corpora")
    public ResponseEntity<List<CorpusDTO>> searchCorpora(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of corpora for query {}", query);
        Page<CorpusDTO> page = corpusService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    private void throwExceptionIfNotEnabled(CorpusDTO dto) {
        if (!dto.getCreatedBy().equals(SecurityUtils.getLoggedUserLogin()) && !(SecurityUtils.isCurrentUserInRole(ADMIN) && dto.getIsPublic())) {
            throw new UnauthorizedException();
        }
    }

}
