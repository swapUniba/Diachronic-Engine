package com.uniba.corpusmanager.service.dto;

import com.uniba.corpusmanager.domain.enumeration.CollocationType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CollocationResultDTO implements Serializable {

    Long corpusId;

    String search;

    LocalDate startDate;

    LocalDate endDate;

    CollocationType type;

    List<String> values;
}
