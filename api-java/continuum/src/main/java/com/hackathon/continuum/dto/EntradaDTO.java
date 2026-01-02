package com.hackathon.continuum.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EntradaDTO(

    // Score NPS: obrigatório e normalmente varia de 0 a 10
    @NotNull(message = "O NPS Score é obrigatório")
    @Min(value = 0, message = "O NPS Score não pode ser negativo")
    @Max(value = 10, message = "O NPS Score não pode ser maior que 10")
    @JsonAlias("num__nps_score")
    Integer nps_score,

    // Tempo de contrato em meses: obrigatório e não pode ser negativo
    @NotNull(message = "O tempo de contrato é obrigatório")
    @PositiveOrZero(message = "O tempo de contrato não pode ser negativo")
    @JsonAlias("num__tempo_contrato_meses")
    Double tempo_contrato_meses,

    // Indica se o cliente já tentou cancelar antes (ex: "Sim"/"Não")
    @NotBlank(message = "A informação sobre tentativa de cancelamento é obrigatória")
    @JsonAlias("num__tentou_cancelar_antes")
    String tentou_cancelar_antes,

    // Valor mensal pago pelo cliente: obrigatório e deve ser maior que zero
    @NotNull(message = "O valor mensal é obrigatório")
    @Positive(message = "O valor mensal deve ser maior que zero")
    @JsonAlias("num__valor_mensal")
    Double valor_mensal,

    // Quantidade de atrasos de pagamento nos últimos 12 meses
    @NotNull(message = "A quantidade de atrasos de pagamento é obrigatória")
    @PositiveOrZero(message = "A quantidade de atrasos não pode ser negativa")
    @JsonAlias("num__atrasos_pagamento_12m")
    Integer atrasos_pagamento_12m,

    // Duração média dos treinos em minutos
    @NotNull(message = "A duração média do treino é obrigatória")
    @Positive(message = "A duração média do treino deve ser maior que zero")
    @JsonAlias("num__duracao_media_treino_min")
    Integer duracao_media_treino_min,

    // Engajamento do cliente em relação ao custo (ex: índice numérico)
    @NotNull(message = "O engajamento por custo é obrigatório")
    @PositiveOrZero(message = "O engajamento por custo não pode ser negativo")
    @JsonAlias("num__engajamento_por_custo")
    Double engajamento_por_custo,

    // Indica se houve redução de frequência nos últimos 3 meses
    @NotBlank(message = "A informação sobre redução de frequência é obrigatória")
    @JsonAlias("num__reducao_frequencia_3m")
    String reducao_frequencia_3m,

    // Frequência mensal de treinos
    @NotNull(message = "A frequência mensal é obrigatória")
    @PositiveOrZero(message = "A frequência mensal não pode ser negativa")
    @JsonAlias("num__frequencia_mensal")
    Integer frequencia_mensal,

    // Indica se o cliente possui personal trainer
    @NotBlank(message = "A informação sobre personal trainer é obrigatória")
    @JsonAlias("num__tem_personal_trainer")
    String tem_personal_trainer

) {
}
