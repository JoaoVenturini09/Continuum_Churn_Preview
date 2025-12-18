package com.hackathon.continuum.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EntradaDTO(

    @NotNull(message = "O valor mensal da conta é obrigatório")
    @Positive(message = "O valor mensal da conta deve ser maior que zero")
    @JsonAlias("account_Charges_Monthly")
    Double valor_mensal_conta,

    @NotBlank(message = "O tipo de serviço de internet é obrigatório")
    @JsonAlias("internet_InternetService_Fiber_optic")
    String tipo_servico_internet,

    @NotBlank(message = "O tipo de faturamento é obrigatório")
    @JsonAlias("account_PaperlessBilling")
    String tipo_faturamento,

    @NotBlank(message = "O método de pagamento é obrigatório")
    @JsonAlias("account_PaymentMethod_Electronic_check")
    String metodo_pagamento,

    @NotBlank(message = "O uso de streaming é obrigatório")
    @JsonAlias("internet_StreamingMovies_1")
    String uso_streaming

) {
}
