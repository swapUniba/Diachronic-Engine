package com.uniba.corpusmanager.service;

import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.uniba.corpusmanager.domain.ActionLog}.
 */
public interface ActionLogService {

    /**
     * Save a actionLog.
     *
     * @param actionLogDTO the entity to save.
     * @return the persisted entity.
     */
    ActionLogDTO save(ActionLogDTO actionLogDTO);

    /**
     * Get all the actionLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActionLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" actionLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActionLogDTO> findOne(Long id);

    /**
     * Delete the "id" actionLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the actionLog corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActionLogDTO> search(String query, Pageable pageable);
}
