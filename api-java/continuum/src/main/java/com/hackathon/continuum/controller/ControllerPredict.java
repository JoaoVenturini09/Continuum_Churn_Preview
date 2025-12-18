package com.hackathon.continuum.controller;

import com.hackathon.continuum.dto.EntradaDTO;
import com.hackathon.continuum.dto.RespostaDTO;
import com.hackathon.continuum.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
public class ControllerPredict {

    @Autowired
    PredictService predict;

    @PostMapping
    public ResponseEntity<RespostaDTO> predict(@RequestBody  EntradaDTO entradaDTO){
            RespostaDTO respostaDTO = predict.predict(entradaDTO);

            return ResponseEntity.ok(respostaDTO);

    }
}
