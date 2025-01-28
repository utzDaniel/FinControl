package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Pagamento;
import br.com.develop.finControl.util.Constante;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagamentoResponse implements IPagamentoResponse {

    private Long id;
    private Long tipoPagamento;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long tipoBeneficio;
    @JsonFormat(pattern = Constante.FORMATO_DATA_HORA, timezone = "UTC")
    private LocalDateTime data;

    public PagamentoResponse(int id, LocalDateTime data, Long tipoPagamento, Long tipoBeneficio) {
        this.id = (long) id;
        this.tipoPagamento = tipoPagamento;
        this.tipoBeneficio = tipoBeneficio;
        this.data = data;
    }

    public PagamentoResponse(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.data = pagamento.getData();
        this.tipoPagamento = pagamento.getIdDominioPagamento();
        this.tipoBeneficio = pagamento.getIdDominioBeneficio();
    }
}
