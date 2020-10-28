package com.uniba.corpusmanager.repository.search;

import com.uniba.corpusmanager.domain.Corpus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Corpus} entity.
 */
public interface CorpusSearchRepository extends ElasticsearchRepository<Corpus, Long> {
}
