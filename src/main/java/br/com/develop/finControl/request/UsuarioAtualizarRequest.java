package br.com.develop.finControl.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioAtualizarRequest {

    @NotNull(message = "O campo 'nome' não pode ser nulo")
    @Size(min = 3, max = 100, message = "O campo 'nome' deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "O campo 'email' não pode ser nulo")
    @NotEmpty(message = "O campo 'email' não pode ser vazio")
    @Email(message = "O campo 'email' deve ter um formato válido")
    private String email;

    private Long idFamilia;

}
