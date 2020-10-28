package com.uniba.corpusmanager.web.rest;

import com.uniba.corpusmanager.domain.enumeration.CollocationType;
import com.uniba.corpusmanager.domain.enumeration.FrequencyListSearchFilter;
import com.uniba.corpusmanager.domain.enumeration.FrequencyListSearchType;
import com.uniba.corpusmanager.service.dto.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FakeData {

    private static final Long CORPUS_ID = 1L;

    public static FrequencyListResultDTO fakeFrequencyList = FrequencyListResultDTO.builder()
        .corpusId(CORPUS_ID)
        .searchType(FrequencyListSearchType.WORDS.name())
        .searchFilter(FrequencyListSearchFilter.ALL.name())
        .startDate(LocalDate.of(2000, 1, 1))
        .endDate(LocalDate.of(2009, 12, 31))
        .values(Arrays.asList(
            getFrequencyItem(",", 294967368L),
            getFrequencyItem("di", 204007900L),
            getFrequencyItem(".", 188985776L),
            getFrequencyItem("e", 147122730L),
            getFrequencyItem("il", 97774427L),
            getFrequencyItem("la", 97000247L),
            getFrequencyItem("che", 87633205L),
            getFrequencyItem("in", 77664783L),
            getFrequencyItem("a", 72005360L),
            getFrequencyItem("per", 68783056L),
            getFrequencyItem("\"", 59164776L),
            getFrequencyItem("un", 56853428L),
            getFrequencyItem("del", 54791491L),
            getFrequencyItem("è", 54391477L),
            getFrequencyItem("l'", 44488897L),
            getFrequencyItem("con", 42598994L),
            getFrequencyItem("i", 42543638L),
            getFrequencyItem("della", 42091947L),
            getFrequencyItem("non", 41203560L),
            getFrequencyItem("le", 40440747L),
            getFrequencyItem("una", 39279978L),
            getFrequencyItem("si", 36797547L),
            getFrequencyItem(":", 34597310L),
            getFrequencyItem("da", 34218462L),
            getFrequencyItem(")", 33000620L),
            getFrequencyItem("(", 30985862L),
            getFrequencyItem("al", 27556851L),
            getFrequencyItem("dei", 23679145L),
            getFrequencyItem("sono", 21763748L),
            getFrequencyItem("nel", 20660910L),
            getFrequencyItem("più", 19308414L),
            getFrequencyItem("come", 19155252L),
            getFrequencyItem("ha", 18993572L),
            getFrequencyItem("delle", 18852148L),
            getFrequencyItem("alla", 18845974L),
            getFrequencyItem("dell'", 18752267L),
            getFrequencyItem("ma", 18104913L),
            getFrequencyItem("anche", 17830513L),
            getFrequencyItem("-", 16291930L),
            getFrequencyItem("o", 15763313L),
            getFrequencyItem("gli", 15078762L),
            getFrequencyItem("...", 13595168L),
            getFrequencyItem("se", 12768671L),
            getFrequencyItem("ed", 12581933L),
            getFrequencyItem(";", 12299237L),
            getFrequencyItem("ad", 12215119L),
            getFrequencyItem("dal", 12159484L),
            getFrequencyItem("lo", 11967648L),
            getFrequencyItem("questo", 11932841L),
            getFrequencyItem("nella", 11447627L),
            getFrequencyItem("all'", 10994736L),
            getFrequencyItem("?", 10003583L),
            getFrequencyItem("/", 9342969L),
            getFrequencyItem("essere", 9322629L),
            getFrequencyItem("su", 9011627L),
            getFrequencyItem("cui", 8876337L),
            getFrequencyItem("ci", 8739893L),
            getFrequencyItem("alle", 8629349L),
            getFrequencyItem("tra", 8624832L),
            getFrequencyItem("ai", 8320758L)
        ))
        .build();

    public static List<CollocationResultDTO> fakeCollocation() {
        return Arrays.asList(
            CollocationResultDTO.builder()
                .corpusId(CORPUS_ID)
                .search("trovato")
                .startDate(LocalDate.of(2000, 1, 1))
                .endDate(LocalDate.of(2009, 12, 31))
                .type(CollocationType.VERB)
                .values(Arrays.asList(
                    "trovare",
                    "acquistare",
                    "scaricare",
                    "sequestrare",
                    "ritrovare",
                    "provare",
                    "essere",
                    "avere"
                ))
                .build(),
            CollocationResultDTO.builder()
                .corpusId(CORPUS_ID)
                .search("trovato")
                .startDate(LocalDate.of(2000, 1, 1))
                .endDate(LocalDate.of(2009, 12, 31))
                .type(CollocationType.NOUN)
                .values(Arrays.asList(
                    "soluzione",
                    "modo",
                    "spazio",
                    "informazione",
                    "risposta",
                    "lavoro",
                    "posto",
                    "applicazione",
                    "accordo"
                ))
                .build(),
            CollocationResultDTO.builder()
                .corpusId(CORPUS_ID)
                .search("trovato")
                .startDate(LocalDate.of(2000, 1, 1))
                .endDate(LocalDate.of(2009, 12, 31))
                .type(CollocationType.ADJECTIVE)
                .values(Arrays.asList(
                    "vicino",
                    "interessante",
                    "pronto",
                    "impreparato",
                    "utile",
                    "giusto"
                ))
                .build(),
            CollocationResultDTO.builder()
                .corpusId(CORPUS_ID)
                .search("trovato")
                .startDate(LocalDate.of(2000, 1, 1))
                .endDate(LocalDate.of(2009, 12, 31))
                .type(CollocationType.PRONOUN)
                .values(Arrays.asList(
                    "io",
                    "essi",
                    "esso",
                    "egli",
                    "essa",
                    "noi",
                    "esse",
                    "tu"
                ))
                .build()
        );
    }

    public static ConcordanceResultDTO fakeConcordance() {
        return ConcordanceResultDTO.builder()
            .corpusId(CORPUS_ID)
            .search("con")
            .startDate(LocalDate.of(2000, 1, 1))
            .endDate(LocalDate.of(2009, 12, 31))
            .values(Arrays.asList(
                getConcordanceContext("hotelsancarloallago.it", LocalDate.of(1998, 1, 1), "L'Hotel San Carlo, situato in riva al lago di Endine, dispone di camere", "con", "servizi privati, telefono e televisione.</s><s>Il ristorante con cucina"),
                getConcordanceContext("hotelsancarloallago.it", LocalDate.of(1998, 1, 1), "di camere con servizi privati, telefono e televisione.</s><s>Il ristorante", "con", "cucina internazionale e sempre naturale, offre un servizio accurato e una"),
                getConcordanceContext("hotelsancarloallago.it", LocalDate.of(1998, 1, 1), "offre un servizio accurato e una vasta gamma di specialità.</s><s>Parco", "con", "giardino e piscina esterna aperta da Giugno a meta' Settembre, terrazza "),
                getConcordanceContext("hotelsancarloallago.it", LocalDate.of(1998, 1, 1), " attività sportive ed escursionistiche.</s><s>SERVIZI Grande parco esterno", "con", "piscina e parcheggio interno.</s><s>Terrazze soleggiate, taverna,"),
                getConcordanceContext("toscana-mare.it ", LocalDate.of(1998, 2, 13), "italiana a statuto ordinario di 3 745 983 abitanti, situata nell'Italia centrale,", "con", "capoluogo Firenze.</s><s>Confina a nord-ovest con la Liguria"),
                getConcordanceContext("toscana-mare.it ", LocalDate.of(1998, 2, 13), "situata nell'Italia centrale, con capoluogo Firenze.</s><s>Confina a nord-ovest", "con", "la Liguria, a nord con l'Emilia-Romagna, a est con le Marche"),
                getConcordanceContext("vacanzelaminiera.it ", LocalDate.of(1998, 2, 15), "Deve il suo nome alla vecchia miniera di rame più importante d'Europa, ora visitabile", "con", "percorsi guidati.</s><s>È un luogo unico nel suo genere per chi ama gli spazi")
            ))
            .build();
    }

    private static FrequencyItemDTO getFrequencyItem(String word, Long frequency) {
        return FrequencyItemDTO.builder()
            .word(word)
            .frequency(frequency)
            .build();
    }

    private static ConcordanceContextDTO getConcordanceContext(String source, LocalDate date, String leftContext, String kwic, String rightContext) {
        return ConcordanceContextDTO.builder()
            .source(source)
            .date(date)
            .leftContext(leftContext)
            .kwic(kwic)
            .rightContext(rightContext)
            .build();
    }
}
