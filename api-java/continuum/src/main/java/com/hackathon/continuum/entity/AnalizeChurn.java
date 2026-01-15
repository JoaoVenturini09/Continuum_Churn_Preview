package com.hackathon.continuum.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "analize_churn")
public class AnalizeChurn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;

    @Column
    private String nome;

    @Column
    private Integer nps_score;
    @Column
    private Double tempo_contrato_meses;
    @Column
    private String tentou_cancelar_antes;
    @Column
    private Double valor_mensal;
    @Column
    private Integer atrasos_pagamento_12m;
    @Column
    private Integer duracao_media_treino_min;
    @Column
    private Double engajamento_por_custo;
    @Column
    private String reducao_frequencia_3m;
    @Column
    private Integer frequencia_mensal;
    @Column
    private String tem_personal_trainer;
    @Column
    private Integer numero_reclamacoes;
    @Column
    private String participa_aulas_coletivas;
    @Column
    private String participou_eventos;
    @Column
    private String uso_app_academia;
    @Column
    private String forma_pagamento;
    @Column
    private String teve_desconto_promocao;
    @Column
    private String tipo_plano;
    @Column
    private LocalDate data_inicio_contrato;
    @Column
    private Integer dias_desde_ultimo_acesso;
    @Column
    private Double churn;

    @Column
    private LocalDateTime criacao_data_hora; // Data/hora de criação

}
