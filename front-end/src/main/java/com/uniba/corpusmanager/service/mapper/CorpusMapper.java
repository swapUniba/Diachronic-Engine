package com.uniba.corpusmanager.service.mapper;


import com.uniba.corpusmanager.domain.Corpus;
import com.uniba.corpusmanager.service.dto.CorpusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Corpus} and its DTO {@link CorpusDTO}.
 */
@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface CorpusMapper extends EntityMapper<CorpusDTO, Corpus> {

    @Mapping(source = "language.id", target = "languageId")
    @Mapping(source = "language.name", target = "languageName")
    CorpusDTO toDto(Corpus corpus);

    @Mapping(source = "languageId", target = "language")
    Corpus toEntity(CorpusDTO corpusDTO);

    default Corpus fromId(Long id) {
        if (id == null) {
            return null;
        }
        Corpus corpus = new Corpus();
        corpus.setId(id);
        return corpus;
    }
}
