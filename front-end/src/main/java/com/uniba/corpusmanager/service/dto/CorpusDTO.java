package com.uniba.corpusmanager.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.uniba.corpusmanager.domain.Corpus} entity.
 */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CorpusDTO extends AbstractAuditingDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Min(value = 0)
    @Max(value = 9999)
    private Integer periodStart;

    @Min(value = 0)
    @Max(value = 9999)
    private Integer periodEnd;

    @NotNull
    private Boolean isPublic;

    private Long languageId;

    private String languageName;
}
