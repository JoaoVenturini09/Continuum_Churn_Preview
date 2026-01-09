package com.hackathon.continuum.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaDTO(
    @JsonAlias("probabilidade") Double probabilidade_churn,
    @JsonAlias("risco") String risco,
    @JsonAlias("1_mais_relevante") String primeiro_mais_relevante,
	@JsonAlias("2_mais_relevante") String segundo_mais_relevante,
	@JsonAlias("3_mais_relevante") String terceiro_mais_relevante) {

}
