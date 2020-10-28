package com.uniba.corpusmanager.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CorpusMapperTest {

    private CorpusMapper corpusMapper;

    @BeforeEach
    public void setUp() {
        corpusMapper = new CorpusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(corpusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(corpusMapper.fromId(null)).isNull();
    }
}
