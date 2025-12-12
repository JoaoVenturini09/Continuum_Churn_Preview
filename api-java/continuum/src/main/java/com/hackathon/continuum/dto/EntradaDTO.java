package com.hackathon.continuum.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EntradaDTO(
    @JsonAlias ("account_Charges_Monthly") double valor_mensal_conta,
    @JsonAlias ("internet_InternetService_Fiber optic") String tipo_servico_internet,
    @JsonAlias ("account_PaperlessBilling") String tipo_faturamento,
    @JsonAlias ("account_PaymentMethod_Electronic check") String metodo_pagamento,
    @JsonAlias ("internet_StreamingMovies_1") String uso_streaming) {

}
