package com.uniba.corpusmanager.service.impl;

import com.uniba.corpusmanager.domain.Language;
import com.uniba.corpusmanager.repository.LanguageRepository;
import com.uniba.corpusmanager.repository.search.LanguageSearchRepository;
import com.uniba.corpusmanager.service.LanguageService;
import com.uniba.corpusmanager.service.dto.LanguageDTO;
import com.uniba.corpusmanager.service.mapper.LanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link Language}.
 */
@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final Logger log = LoggerFactory.getLogger(LanguageServiceImpl.class);

    private final LanguageRepository languageRepository;

    private final LanguageMapper languageMapper;

    private final LanguageSearchRepository languageSearchRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository, LanguageMapper languageMapper, LanguageSearchRepository languageSearchRepository) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
        this.languageSearchRepository = languageSearchRepository;
    }

    /**
     * Save a language.
     *
     * @param languageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LanguageDTO save(LanguageDTO languageDTO) {
        log.debug("Request to save Language : {}", languageDTO);
        Language language = languageMapper.toEntity(languageDTO);
        language = languageRepository.save(language);
        LanguageDTO result = languageMapper.toDto(language);
        languageSearchRepository.save(language);
        return result;
    }

    /**
     * Get all the languages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LanguageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Languages");
        return languageRepository.findAll(pageable)
            .map(languageMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LanguageDTO> findAll() {
        log.debug("Request to get all Languages");
        return languageRepository.findAll()
            .stream()
            .map(languageMapper::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Get one language by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LanguageDTO> findOne(Long id) {
        log.debug("Request to get Language : {}", id);
        return languageRepository.findById(id)
            .map(languageMapper::toDto);
    }

    /**
     * Delete the language by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Language : {}", id);
        languageRepository.deleteById(id);
        languageSearchRepository.deleteById(id);
    }

    /**
     * Search for the language corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LanguageDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Languages for query {}", query);
        return languageSearchRepository.search(queryStringQuery(query), pageable)
            .map(languageMapper::toDto);
    }
}
