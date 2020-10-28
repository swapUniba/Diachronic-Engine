package com.uniba.corpusmanager.service.impl;

import com.uniba.corpusmanager.domain.ActionLog;
import com.uniba.corpusmanager.repository.ActionLogRepository;
import com.uniba.corpusmanager.repository.search.ActionLogSearchRepository;
import com.uniba.corpusmanager.security.SecurityUtils;
import com.uniba.corpusmanager.service.ActionLogService;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import com.uniba.corpusmanager.service.mapper.ActionLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link ActionLog}.
 */
@Service
@Transactional
public class ActionLogServiceImpl implements ActionLogService {

    private final Logger log = LoggerFactory.getLogger(ActionLogServiceImpl.class);

    private final ActionLogRepository actionLogRepository;

    private final ActionLogMapper actionLogMapper;

    private final ActionLogSearchRepository actionLogSearchRepository;

    public ActionLogServiceImpl(ActionLogRepository actionLogRepository, ActionLogMapper actionLogMapper, ActionLogSearchRepository actionLogSearchRepository) {
        this.actionLogRepository = actionLogRepository;
        this.actionLogMapper = actionLogMapper;
        this.actionLogSearchRepository = actionLogSearchRepository;
    }

    /**
     * Save a actionLog.
     *
     * @param actionLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ActionLogDTO save(ActionLogDTO actionLogDTO) {
        log.debug("Request to save ActionLog : {}", actionLogDTO);
        ActionLog actionLog = actionLogMapper.toEntity(actionLogDTO);
        actionLog = actionLogRepository.save(actionLog);
        ActionLogDTO result = actionLogMapper.toDto(actionLog);
        actionLogSearchRepository.save(actionLog);
        return result;
    }

    /**
     * Get all the actionLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActionLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActionLogs");
        return actionLogRepository.findAllByCreatedByOrderByCreatedDateDesc(pageable, SecurityUtils.getLoggedUserLogin())
            .map(actionLogMapper::toDto);
    }

    /**
     * Get one actionLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActionLogDTO> findOne(Long id) {
        log.debug("Request to get ActionLog : {}", id);
        return actionLogRepository.findById(id)
            .map(actionLogMapper::toDto);
    }

    /**
     * Delete the actionLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActionLog : {}", id);
        actionLogRepository.deleteById(id);
        actionLogSearchRepository.deleteById(id);
    }

    /**
     * Search for the actionLog corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActionLogDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ActionLogs for query {}", query);
        return actionLogSearchRepository.search(queryStringQuery(query), pageable)
            .map(actionLogMapper::toDto);
    }
}
