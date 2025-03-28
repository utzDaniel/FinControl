package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Beneficio;
import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BeneficioResponse implements IBeneficioResponse {

    private Long id;
    private Long tipoBeneficio;
    private BigDecimal valor;
    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    private LocalDateTime data;

    public BeneficioResponse(int id, BigDecimal valor, LocalDateTime data, Long tipoBeneficio) {
        this.id = (long) id;
        this.tipoBeneficio = tipoBeneficio;
        this.valor = valor;
        this.data = data;
    }

    public BeneficioResponse(Beneficio beneficio) {
        this.id = beneficio.getId();
        this.valor = beneficio.getValor();
        this.data = beneficio.getData();
        this.tipoBeneficio = beneficio.getIdDominioBeneficio();
    }
}
