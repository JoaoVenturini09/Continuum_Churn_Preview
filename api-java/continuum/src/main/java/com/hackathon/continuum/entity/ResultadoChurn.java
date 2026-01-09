package com.hackathon.continuum.entity;

import java.time.LocalDateTime;

import com.hackathon.continuum.dto.RespostaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "resultado_churn")
public class ResultadoChurn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultado_churn_id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private AnalizeChurn analizeChurn;

    @Column(nullable = false)
    private Double probabilidadeChurn;

    @Column(nullable = false)
    private String risco;

    @Column(name = "primeiro_mais_relevante", length = 100)
	private String primeiroMaisRelevante;

	@Column(name = "segundo_mais_relevante", length = 100)
	private String segundoMaisRelevante;

	@Column(name = "terceiro_mais_relevante", length = 100)
	private String terceiroMaisRelevante;

    @Column(nullable = false, updatable = false)
    private LocalDateTime resultadoDataHora;

	// Construtor padrão
	public ResultadoChurn() {
	}

	// Construtor 
	public ResultadoChurn(RespostaDTO dto, AnalizeChurn analizeChurn) {
    this.analizeChurn = analizeChurn;
    this.probabilidadeChurn = dto.probabilidade_churn();
    this.risco = dto.risco();
    this.primeiroMaisRelevante = dto.primeiro_mais_relevante();
    this.segundoMaisRelevante = dto.segundo_mais_relevante();
    this.terceiroMaisRelevante = dto.terceiro_mais_relevante();
	}

	// Data e hora automáticas
	@PrePersist
	private void prePersist() {
    	this.resultadoDataHora = LocalDateTime.now();
	}

	// Getters
	public Long getId() {
        return resultado_churn_id;
    }

    public AnalizeChurn getAnalizeChurn() {
        return analizeChurn;
    }

    public Double getProbabilidadeChurn() {
        return probabilidadeChurn;
    }

    public String getRisco() {
        return risco;
    }

    public String getPrimeiroMaisRelevante() {
        return primeiroMaisRelevante;
    }

    public String getSegundoMaisRelevante() {
        return segundoMaisRelevante;
    }

    public String getTerceiroMaisRelevante() {
        return terceiroMaisRelevante;
    }

    public LocalDateTime getResultadoDataHora() {
        return resultadoDataHora;
    }

	// toString
	@Override
    public String toString() {
        return "ResultadoChurn{" +
                "id=" + resultado_churn_id +
                ", analizeChurnId=" + analizeChurn.getId() +
                ", probabilidadeChurn=" + probabilidadeChurn +
                ", risco='" + risco + '\'' +
                ", primeiroMaisRelevante='" + primeiroMaisRelevante + '\'' +
                ", segundoMaisRelevante='" + segundoMaisRelevante + '\'' +
                ", terceiroMaisRelevante='" + terceiroMaisRelevante + '\'' +
                ", resultadoDataHora=" + resultadoDataHora +
                '}';
    }

}
