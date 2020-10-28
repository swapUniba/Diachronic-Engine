package com.uniba.corpusmanager.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FrequencyItemDTO implements Serializable {

    String word;

    Long frequency;
}
