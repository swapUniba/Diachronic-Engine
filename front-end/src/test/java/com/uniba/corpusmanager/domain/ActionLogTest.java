package com.uniba.corpusmanager.domain;

import com.uniba.corpusmanager.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActionLog.class);
        ActionLog actionLog1 = new ActionLog();
        actionLog1.setId(1L);
        ActionLog actionLog2 = new ActionLog();
        actionLog2.setId(actionLog1.getId());
        assertThat(actionLog1).isEqualTo(actionLog2);
        actionLog2.setId(2L);
        assertThat(actionLog1).isNotEqualTo(actionLog2);
        actionLog1.setId(null);
        assertThat(actionLog1).isNotEqualTo(actionLog2);
    }
}
