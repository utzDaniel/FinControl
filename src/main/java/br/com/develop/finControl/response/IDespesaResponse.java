package br.com.develop.finControl.response;

import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface IDespesaResponse {

    Long getId();

    String getNome();

    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    LocalDateTime getDataReferencia();

    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    LocalDateTime getDataVencimento();

}
