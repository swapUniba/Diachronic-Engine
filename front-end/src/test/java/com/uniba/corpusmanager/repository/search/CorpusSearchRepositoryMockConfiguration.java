package com.uniba.corpusmanager.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link CorpusSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CorpusSearchRepositoryMockConfiguration {

    @MockBean
    private CorpusSearchRepository mockCorpusSearchRepository;

}
