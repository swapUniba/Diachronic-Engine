package com.uniba.corpusmanager.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link LanguageSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class LanguageSearchRepositoryMockConfiguration {

    @MockBean
    private LanguageSearchRepository mockLanguageSearchRepository;

}
