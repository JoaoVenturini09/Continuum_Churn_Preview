package com.hackathon.continuum.service;

import com.hackathon.continuum.dto.EntradaDTO;
import com.hackathon.continuum.dto.RespostaDTO;
import org.springframework.stereotype.Service;

@Service
public class PredictService {

    //TODO: implementar serviço de comunicação para API de dados
    //Observação: Atualmente é um mock.
    public RespostaDTO predict(EntradaDTO entradaDTO){
        return mockPredict();
    }


    //TODO: remover após conclusão do serviço.
    public RespostaDTO mockPredict(){
        return new RespostaDTO("Vai cancelar", 0.76);
    }

}
