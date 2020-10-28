package com.uniba.corpusmanager.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionLogMapperTest {

    private ActionLogMapper actionLogMapper;

    @BeforeEach
    public void setUp() {
        actionLogMapper = new ActionLogMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(actionLogMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(actionLogMapper.fromId(null)).isNull();
    }
}
