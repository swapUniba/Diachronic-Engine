package com.uniba.corpusmanager.repository.search;

import com.uniba.corpusmanager.domain.ActionLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ActionLog} entity.
 */
public interface ActionLogSearchRepository extends ElasticsearchRepository<ActionLog, Long> {
}
