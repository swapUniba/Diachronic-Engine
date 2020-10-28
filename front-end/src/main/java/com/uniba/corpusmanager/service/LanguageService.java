package com.uniba.corpusmanager.service;

import com.uniba.corpusmanager.service.dto.LanguageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.uniba.corpusmanager.domain.Language}.
 */
public interface LanguageService {

    /**
     * Save a language.
     *
     * @param languageDTO the entity to save.
     * @return the persisted entity.
     */
    LanguageDTO save(LanguageDTO languageDTO);

    /**
     * Get all the languages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LanguageDTO> findAll(Pageable pageable);

    List<LanguageDTO> findAll();

    /**
     * Get the "id" language.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LanguageDTO> findOne(Long id);

    /**
     * Delete the "id" language.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the language corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LanguageDTO> search(String query, Pageable pageable);
}
