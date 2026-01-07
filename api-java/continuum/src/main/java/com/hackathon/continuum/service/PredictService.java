package com.hackathon.continuum.service;

import com.hackathon.continuum.dto.EntradaDTO;
import com.hackathon.continuum.dto.RespostaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PredictService {

    @Value("${URI_API_CONTINUUM_CHURN_PYTHON}")
    private String uri;

    private final RestClient restClient = RestClient.create();

    public RespostaDTO predict(EntradaDTO entradaDTO){

        return restClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(entradaDTO)
                .retrieve()
                .body(RespostaDTO.class);
    }
}
