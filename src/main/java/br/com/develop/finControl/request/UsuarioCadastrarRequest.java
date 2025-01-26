package br.com.develop.finControl.request;

import br.com.develop.finControl.util.Constante;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioCadastrarRequest {

    @NotNull(message = "O campo 'nome' não pode ser nulo")
    @Size(min = 3, max = 100, message = "O campo 'nome' deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "O campo 'cpf' não pode ser nulo")
    @Pattern(regexp = Constante.REGEX_CPF, message = "O campo 'cpf' deve ter 11 caracteres numéricos")
    private String cpf;

    @NotNull(message = "O campo 'email' não pode ser nulo")
    @NotEmpty(message = "O campo 'email' não pode ser vazio")
    @Email(message = "O campo 'email' deve ter um formato válido")
    private String email;

}
