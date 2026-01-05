package com.hackathon.continuum.infra.excecoes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class TratadorDeExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){

        var erros = ex.getFieldErrors();

        log.warn("Erro de validação [{}]: {} campos inválidos", getTraceId(), erros.size());

        return ResponseEntity.badRequest().body(new ErroPadraoDTO(
                "Erro de validação nos campos informados.",
                getTraceId(),
                erros.stream().map(DadosErroValidacao::new).toList()
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {

        log.warn("Falha na leitura do JSON [{}]: {}", getTraceId(), ex.getMostSpecificCause().getMessage());

        return ResponseEntity.badRequest().body(new ErroPadraoDTO(
                "Erro na sintaxe do JSON ou tipo de dado inválido. Verifique se campos numéricos receberam texto.",
                getTraceId(),
                null
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {

        log.error("Erro crítico [{}]: {}", getTraceId(), ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroPadraoDTO(
                "Ocorreu um erro interno. Informe o código de rastreio ao suporte.",
                getTraceId(),
                null
        ));
    }

    private String getTraceId() {
        return org.slf4j.MDC.get("traceId");
    }

    private record DadosErroValidacao(
            String campo,
            String mensagem) {

        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }

    }

    private record ErroPadraoDTO(
            String mensagem,
            String traceId,
            Object detalhes
    ) {}
}
