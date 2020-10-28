package com.uniba.corpusmanager.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ActionLogSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ActionLogSearchRepositoryMockConfiguration {

    @MockBean
    private ActionLogSearchRepository mockActionLogSearchRepository;

}
