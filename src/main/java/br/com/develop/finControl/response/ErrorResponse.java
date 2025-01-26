package br.com.develop.finControl.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public final class ErrorResponse {

    private String path;
    private int status;
    private String titulo;
    private String detalhe;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ViolacaoResponse> violacoes;
}