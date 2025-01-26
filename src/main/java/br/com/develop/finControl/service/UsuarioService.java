package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.Usuario;
import br.com.develop.finControl.repository.IUsuarioRepository;
import br.com.develop.finControl.request.UsuarioAtualizarRequest;
import br.com.develop.finControl.request.UsuarioCadastrarRequest;
import br.com.develop.finControl.response.IUsuarioResponse;
import br.com.develop.finControl.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<IUsuarioResponse> listarUsuarios() {
        return this.usuarioRepository.listarUsuarios();
    }

    public Optional<IUsuarioResponse> obterUsuario(String cpf) {
        return this.usuarioRepository.obterUsuarioPorCpf(cpf);
    }

    public IUsuarioResponse cadastrarUsuario(UsuarioCadastrarRequest request) {
        var usuario = new Usuario(request);
        this.usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    public Optional<IUsuarioResponse> atualizarUsuario(String cpf, UsuarioAtualizarRequest request) {
        return Optional.ofNullable(this.usuarioRepository.findByCpf(cpf)
                .map(v -> {
                    v.setNome(request.getNome());
                    v.setEmail(request.getEmail());
                    this.usuarioRepository.save(v);
                    return new UsuarioResponse(v);
                }).orElse(null));
    }

    public boolean deletarUsuario(String cpf) {
        return this.usuarioRepository.deletarPorCpf(cpf) > 0;
    }

}
