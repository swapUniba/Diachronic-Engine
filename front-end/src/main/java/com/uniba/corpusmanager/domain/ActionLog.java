package com.uniba.corpusmanager.domain;

import com.uniba.corpusmanager.domain.enumeration.Action;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A ActionLog.
 */
@Entity
@Table(name = "action_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "actionlog")
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ActionLog extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(name = "corpus_id", nullable = false)
    private Long corpusId;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "corpus_name", nullable = false)
    private String corpusName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "request", nullable = false)
    private String request;
}
