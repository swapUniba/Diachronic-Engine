package com.uniba.corpusmanager.domain;

import com.uniba.corpusmanager.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CorpusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Corpus.class);
        Corpus corpus1 = new Corpus();
        corpus1.setId(1L);
        Corpus corpus2 = new Corpus();
        corpus2.setId(corpus1.getId());
        assertThat(corpus1).isEqualTo(corpus2);
        corpus2.setId(2L);
        assertThat(corpus1).isNotEqualTo(corpus2);
        corpus1.setId(null);
        assertThat(corpus1).isNotEqualTo(corpus2);
    }
}
