package com.uniba.corpusmanager.service;

import com.uniba.corpusmanager.service.dto.CorpusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.uniba.corpusmanager.domain.Corpus}.
 */
public interface CorpusService {

    /**
     * Save a corpus.
     *
     * @param corpusDTO the entity to save.
     * @return the persisted entity.
     */
    CorpusDTO save(CorpusDTO corpusDTO);

    /**
     * Get all the corpora.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CorpusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" corpus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CorpusDTO> findOne(Long id);

    /**
     * Delete the "id" corpus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the corpus corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CorpusDTO> search(String query, Pageable pageable);
}
