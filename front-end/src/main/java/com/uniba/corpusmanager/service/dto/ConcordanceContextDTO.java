package com.uniba.corpusmanager.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class ConcordanceContextDTO implements Serializable {

    String source;

    LocalDate date;

    String leftContext;

    String kwic;

    String rightContext;
}
