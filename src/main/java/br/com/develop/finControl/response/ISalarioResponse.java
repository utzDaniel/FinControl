package br.com.develop.finControl.response;

import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ISalarioResponse {

    Long getId();

    BigDecimal getValorLiquido();

    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    LocalDateTime getData();

}
