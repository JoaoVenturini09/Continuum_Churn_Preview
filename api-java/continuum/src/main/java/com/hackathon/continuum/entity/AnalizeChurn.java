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
    @Column(nullable = false)
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
    private String churn;
    @Column(nullable = false, updatable = false)
    private LocalDateTime criacao_data_hora; // Data/hora de criação

    // Construtor padrão (OBRIGATÓRIO para JPA)
    protected AnalizeChurn() {
    }

    // Data e hora automática e nome não pode ser null
    @PrePersist
    private void prePersist() {
        this.criacao_data_hora = LocalDateTime.now();

        if (this.nome == null || this.nome.isBlank()) {
        this.nome = "SEM_NOME";
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNps_score() {
        return nps_score;
    }

    public void setNps_score(Integer nps_score) {
        this.nps_score = nps_score;
    }

    public Double getTempo_contrato_meses() {
        return tempo_contrato_meses;
    }

    public void setTempo_contrato_meses(Double tempo_contrato_meses) {
        this.tempo_contrato_meses = tempo_contrato_meses;
    }

    public String getTentou_cancelar_antes() {
        return tentou_cancelar_antes;
    }

    public void setTentou_cancelar_antes(String tentou_cancelar_antes) {
        this.tentou_cancelar_antes = tentou_cancelar_antes;
    }

    public Double getValor_mensal() {
        return valor_mensal;
    }

    public void setValor_mensal(Double valor_mensal) {
        this.valor_mensal = valor_mensal;
    }

    public Integer getAtrasos_pagamento_12m() {
        return atrasos_pagamento_12m;
    }

    public void setAtrasos_pagamento_12m(Integer atrasos_pagamento_12m) {
        this.atrasos_pagamento_12m = atrasos_pagamento_12m;
    }

    public Integer getDuracao_media_treino_min() {
        return duracao_media_treino_min;
    }

    public void setDuracao_media_treino_min(Integer duracao_media_treino_min) {
        this.duracao_media_treino_min = duracao_media_treino_min;
    }

    public Double getEngajamento_por_custo() {
        return engajamento_por_custo;
    }

    public void setEngajamento_por_custo(Double engajamento_por_custo) {
        this.engajamento_por_custo = engajamento_por_custo;
    }

    public String getReducao_frequencia_3m() {
        return reducao_frequencia_3m;
    }

    public void setReducao_frequencia_3m(String reducao_frequencia_3m) {
        this.reducao_frequencia_3m = reducao_frequencia_3m;
    }

    public Integer getFrequencia_mensal() {
        return frequencia_mensal;
    }

    public void setFrequencia_mensal(Integer frequencia_mensal) {
        this.frequencia_mensal = frequencia_mensal;
    }

    public String getTem_personal_trainer() {
        return tem_personal_trainer;
    }

    public void setTem_personal_trainer(String tem_personal_trainer) {
        this.tem_personal_trainer = tem_personal_trainer;
    }

    public Integer getNumero_reclamacoes() {
        return numero_reclamacoes;
    }

    public void setNumero_reclamacoes(Integer numero_reclamacoes) {
        this.numero_reclamacoes = numero_reclamacoes;
    }

    public String getParticipa_aulas_coletivas() {
        return participa_aulas_coletivas;
    }

    public void setParticipa_aulas_coletivas(String participa_aulas_coletivas) {
        this.participa_aulas_coletivas = participa_aulas_coletivas;
    }

    public String getParticipou_eventos() {
        return participou_eventos;
    }

    public void setParticipou_eventos(String participou_eventos) {
        this.participou_eventos = participou_eventos;
    }

    public String getUso_app_academia() {
        return uso_app_academia;
    }

    public void setUso_app_academia(String uso_app_academia) {
        this.uso_app_academia = uso_app_academia;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getTeve_desconto_promocao() {
        return teve_desconto_promocao;
    }

    public void setTeve_desconto_promocao(String teve_desconto_promocao) {
        this.teve_desconto_promocao = teve_desconto_promocao;
    }

    public String getTipo_plano() {
        return tipo_plano;
    }

    public void setTipo_plano(String tipo_plano) {
        this.tipo_plano = tipo_plano;
    }

    public LocalDate getData_inicio_contrato() {
        return data_inicio_contrato;
    }

    public void setData_inicio_contrato(LocalDate data_inicio_contrato) {
        this.data_inicio_contrato = data_inicio_contrato;
    }

    public Integer getDias_desde_ultimo_acesso() {
        return dias_desde_ultimo_acesso;
    }

    public void setDias_desde_ultimo_acesso(Integer dias_desde_ultimo_acesso) {
        this.dias_desde_ultimo_acesso = dias_desde_ultimo_acesso;
    }

    public String getChurn() {
        return churn;
    }

    public void setChurn(String churn) {
        this.churn = churn;
    }

    public LocalDateTime getCriacao_data_hora() {
        return criacao_data_hora;
    }
}
