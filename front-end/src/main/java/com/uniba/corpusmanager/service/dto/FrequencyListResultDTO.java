package com.uniba.corpusmanager.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FrequencyListResultDTO implements Serializable {

    Long corpusId;

    String searchType;

    String searchFilter;

    String searchFilterText;

    LocalDate startDate;

    LocalDate endDate;

    List<FrequencyItemDTO> values;
}
