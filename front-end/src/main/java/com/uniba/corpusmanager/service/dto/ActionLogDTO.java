package com.uniba.corpusmanager.service.dto;

import com.uniba.corpusmanager.domain.enumeration.Action;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.uniba.corpusmanager.domain.ActionLog} entity.
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLogDTO extends AbstractAuditingDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private Long corpusId;

    @NotNull
    @Size(min = 1, max = 255)
    private String corpusName;

    @NotNull
    private Action action;

    @NotNull
    @Size(min = 1, max = 255)
    private String request;

}
