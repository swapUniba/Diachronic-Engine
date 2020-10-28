package com.uniba.corpusmanager.service.impl;

import com.uniba.corpusmanager.domain.Corpus;
import com.uniba.corpusmanager.repository.CorpusRepository;
import com.uniba.corpusmanager.repository.search.CorpusSearchRepository;
import com.uniba.corpusmanager.security.SecurityUtils;
import com.uniba.corpusmanager.service.CorpusService;
import com.uniba.corpusmanager.service.dto.CorpusDTO;
import com.uniba.corpusmanager.service.mapper.CorpusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Corpus}.
 */
@Service
@Transactional
public class CorpusServiceImpl implements CorpusService {

    private final Logger log = LoggerFactory.getLogger(CorpusServiceImpl.class);

    private final CorpusRepository corpusRepository;

    private final CorpusMapper corpusMapper;

    private final CorpusSearchRepository corpusSearchRepository;

    public CorpusServiceImpl(CorpusRepository corpusRepository, CorpusMapper corpusMapper, CorpusSearchRepository corpusSearchRepository) {
        this.corpusRepository = corpusRepository;
        this.corpusMapper = corpusMapper;
        this.corpusSearchRepository = corpusSearchRepository;
    }

    /**
     * Save a corpus.
     *
     * @param corpusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CorpusDTO save(CorpusDTO corpusDTO) {
        log.debug("Request to save Corpus : {}", corpusDTO);
        Corpus corpus = corpusMapper.toEntity(corpusDTO);
        corpus = corpusRepository.save(corpus);
        CorpusDTO result = corpusMapper.toDto(corpus);
        corpusSearchRepository.save(corpus);
        return result;
    }

    /**
     * Get all the corpora.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CorpusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all corpora");
        return corpusRepository.findAllByIsPublicIsTrueOrCreatedByEquals(pageable, SecurityUtils.getLoggedUserLogin())
            .map(corpusMapper::toDto);
    }

    /**
     * Get one corpus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CorpusDTO> findOne(Long id) {
        log.debug("Request to get Corpus : {}", id);
        return corpusRepository.findById(id)
            .map(corpusMapper::toDto);
    }

    /**
     * Delete the corpus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Corpus : {}", id);
        corpusRepository.deleteById(id);
        corpusSearchRepository.deleteById(id);
    }

    /**
     * Search for the corpus corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CorpusDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of corpora for query {}", query);
        return corpusRepository.search(pageable, query, SecurityUtils.getLoggedUserLogin())
            .map(corpusMapper::toDto);
    }
}
