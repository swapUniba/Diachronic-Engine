package com.uniba.corpusmanager.service.mapper;


import com.uniba.corpusmanager.domain.Language;
import com.uniba.corpusmanager.service.dto.LanguageDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Language} and its DTO {@link LanguageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LanguageMapper extends EntityMapper<LanguageDTO, Language> {


    default Language fromId(Long id) {
        if (id == null) {
            return null;
        }
        Language language = new Language();
        language.setId(id);
        return language;
    }
}
