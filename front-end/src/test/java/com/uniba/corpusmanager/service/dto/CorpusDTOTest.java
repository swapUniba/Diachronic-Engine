package com.uniba.corpusmanager.service.dto;

import com.uniba.corpusmanager.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CorpusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CorpusDTO.class);
        CorpusDTO corpusDTO1 = new CorpusDTO();
        corpusDTO1.setId(1L);
        CorpusDTO corpusDTO2 = new CorpusDTO();
        assertThat(corpusDTO1).isNotEqualTo(corpusDTO2);
        corpusDTO2.setId(corpusDTO1.getId());
        assertThat(corpusDTO1).isEqualTo(corpusDTO2);
        corpusDTO2.setId(2L);
        assertThat(corpusDTO1).isNotEqualTo(corpusDTO2);
        corpusDTO1.setId(null);
        assertThat(corpusDTO1).isNotEqualTo(corpusDTO2);
    }
}
