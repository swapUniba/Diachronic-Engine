package com.uniba.corpusmanager.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ConcordanceResultDTO implements Serializable {

    Long corpusId;
 
    Long n_items;

    String search;

    LocalDate startDate;

    LocalDate endDate;

    List<ConcordanceContextDTO> values;
}
