package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Despesa;
import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DespesaResponse implements IDespesaResponse {

    private Long id;
    private String nome;
    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    private LocalDateTime dataReferencia;
    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    private LocalDateTime dataVencimento;

    public DespesaResponse(Despesa despesa) {
        this.id = despesa.getId();
        this.nome = despesa.getNome();
        this.dataReferencia = despesa.getDataReferencia();
        this.dataVencimento = despesa.getDataVencimento();
    }

}
