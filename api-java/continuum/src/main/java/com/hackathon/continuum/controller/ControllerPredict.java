package com.hackathon.continuum.controller;

import com.hackathon.continuum.dto.EntradaDTO;
import com.hackathon.continuum.dto.EntradaModeloDTO;
import com.hackathon.continuum.dto.RespostaDTO;
import com.hackathon.continuum.infra.filtros.LogTrace;
import com.hackathon.continuum.service.PredictService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/predict")
public class ControllerPredict {

    private final PredictService predictService;

    public ControllerPredict(PredictService predictService) {
        this.predictService = predictService;
    }

    @PostMapping
    public ResponseEntity<RespostaDTO> predict(@Valid @RequestBody  EntradaDTO entradaDTO){
        String traceId = LogTrace.getTraceId();
        StopWatch watch = new StopWatch();
        watch.start();

        //TODO: cliente_id deve ser obtido no banco de dados.
        EntradaModeloDTO entradaModeloDTO = new EntradaModeloDTO(entradaDTO, traceId, "1");

        RespostaDTO respostaDTO = predictService.predict(entradaModeloDTO);
        watch.stop();

        log.info("Predição finalizada com sucesso. Tempo gasto: {}ms | Resultado: {}",
                watch.getTotalTimeMillis(),
                respostaDTO);

        return ResponseEntity.ok(respostaDTO);

    }
}
