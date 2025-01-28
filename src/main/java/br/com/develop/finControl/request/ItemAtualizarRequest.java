package br.com.develop.finControl.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemAtualizarRequest {

    @NotNull(message = "O campo 'nome' não pode ser nulo")
    @Size(min = 3, max = 100, message = "O campo 'nome' deve ter entre 3 e 100 caracteres")
    private String nome;

}
