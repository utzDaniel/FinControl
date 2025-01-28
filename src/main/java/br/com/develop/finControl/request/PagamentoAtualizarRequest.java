package br.com.develop.finControl.request;

import br.com.develop.finControl.enums.TipoBeneficio;
import br.com.develop.finControl.enums.TipoPagamento;
import br.com.develop.finControl.util.ValidEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagamentoAtualizarRequest {

    @ValidEnum(enumClass = TipoBeneficio.class, message = "O campo 'tipoBeneficio' é inválido.")
    private Long tipoBeneficio;

    @NotNull(message = "O campo 'tipoPagamento' não pode ser nulo")
    @ValidEnum(enumClass = TipoPagamento.class, message = "O campo 'tipoPagamento' é inválido.")
    private Long tipoPagamento;

    private LocalDateTime data;

}
