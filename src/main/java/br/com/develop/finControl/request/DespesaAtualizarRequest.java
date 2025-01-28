package br.com.develop.finControl.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DespesaAtualizarRequest {

    @NotNull(message = "O campo 'nome' não pode ser nulo")
    @Size(min = 3, max = 100, message = "O campo 'nome' deve ter entre 3 e 100 caracteres")
    private String nome;

    private LocalDateTime dataReferencia;

    private LocalDateTime dataVencimento;

}
