package com.hackathon.continuum.infra.excecoes;

import com.hackathon.continuum.infra.filtros.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TratadorDeExcecoes {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){

        var erros = ex.getFieldErrors();
        int numeroDeErros = erros.size();
        String traceId = LogTrace.getTraceId();

        var msgCampos = erros.size() > 1 ? String.format("Os seguintes %d campos não passaram na validação: ", numeroDeErros) : "O seguinte campo não passou na validação: ";

        var camposInvalidos = erros.stream().map(DadosErroValidacao::new).toList();

        log.warn("Erro de validação - TraceID: [{}] - {} {}",
                traceId,
                msgCampos,
                camposInvalidos);

        return ResponseEntity.badRequest().body(new ErroPadraoDTO(
                "Erro de validação nos campos informados.",
                traceId,
                camposInvalidos
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
        String traceId = LogTrace.getTraceId();
        log.warn("Falha na leitura do JSON - TraceId: [{}] - {}", traceId, ex.getMostSpecificCause().getMessage());

        return ResponseEntity.badRequest().body(new ErroPadraoDTO(
                "Erro na sintaxe do JSON ou tipo de dado inválido. Verifique se campos numéricos receberam texto.",
                traceId,
                null
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        String traceId = LogTrace.getTraceId();
        log.error("Erro crítico - TraceId: [{}] - {}", traceId, ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroPadraoDTO(
                "Ocorreu um erro interno. Informe o código de rastreio ao suporte.",
                traceId,
                null
        ));
    }

    private record DadosErroValidacao(
            String campo,
            String mensagem) {

        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }

        @Override
        public String toString() {
            return String.format("Campo: %s, Mensagem: %s",campo(), mensagem());
        }

    }

    private record ErroPadraoDTO(
            String mensagem,
            String traceId,
            Object detalhes
    ) {}
}
