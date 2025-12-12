package com.hackathon.continuum.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaDTO(
    String previsao,
    double probabilidade) {

}
