package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Salario;
import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalarioResponse implements ISalarioResponse {

    private Long id;
    private BigDecimal valorLiquido;
    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    private LocalDateTime data;

    public SalarioResponse(int id, BigDecimal valorLiquido, LocalDateTime data) {
        this.id = (long) id;
        this.valorLiquido = valorLiquido;
        this.data = data;
    }

    public SalarioResponse(Salario salario) {
        this.id = salario.getId();
        this.valorLiquido = salario.getValorLiquido();
        this.data = salario.getData();
    }

}
