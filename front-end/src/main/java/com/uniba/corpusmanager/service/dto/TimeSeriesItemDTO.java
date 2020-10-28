package com.uniba.corpusmanager.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class TimeSeriesItemDTO implements Serializable {

    String word;

    List<Double> values;
}
