package br.com.develop.finControl.response;

import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public interface IPagamentoResponse {

    Long getId();

    Long getTipoPagamento();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long getTipoBeneficio();

    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    LocalDateTime getData();

}
