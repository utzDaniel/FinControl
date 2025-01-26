package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Usuario;
import lombok.Data;

@Data
public class UsuarioResponse implements IUsuarioResponse {

    private String nome;
    private String cpf;
    private String email;

    public UsuarioResponse(Usuario usuario) {
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
    }

}
