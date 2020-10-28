package com.uniba.corpusmanager.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TimeSeriesResultDTO implements Serializable {

    Long corpusId;

    List<String> searchTerms;

    LocalDate startDate;

    LocalDate endDate;

    List<String> words;

    List<String> xlabels;

    List<TimeSeriesItemDTO> values;

    List<TimeSeriesItemDTO> changepoints;
}
