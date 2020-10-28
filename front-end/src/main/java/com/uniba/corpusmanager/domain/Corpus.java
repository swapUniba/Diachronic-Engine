package com.uniba.corpusmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Corpus.
 */
@Entity
@Table(name = "corpus")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "corpus")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Corpus extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 0)
    @Max(value = 9999)
    @Column(name = "period_start")
    private Integer periodStart;

    @Min(value = 0)
    @Max(value = 9999)
    @Column(name = "period_end")
    private Integer periodEnd;

    @NotNull
    @Column(name = "is_public")
    private Boolean isPublic;

    @ManyToOne
    @JsonIgnoreProperties("corpora")
    private Language language;
}
