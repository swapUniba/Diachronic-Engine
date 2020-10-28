package com.uniba.corpusmanager.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.uniba.corpusmanager.domain.Language} entity.
 */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class LanguageDTO extends AbstractAuditingDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 2, max = 2)
    private String code;
}
