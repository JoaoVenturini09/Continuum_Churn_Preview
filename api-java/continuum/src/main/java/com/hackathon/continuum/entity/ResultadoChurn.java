package com.hackathon.continuum.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ResultadoChurn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(nullable = false)
    private LocalDateTime resultadoDataHora;

}
