package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.Usuario;
import br.com.develop.finControl.repository.IFamiliaRepository;
import br.com.develop.finControl.repository.IUsuarioRepository;
import br.com.develop.finControl.request.UsuarioAtualizarRequest;
import br.com.develop.finControl.request.UsuarioCadastrarRequest;
import br.com.develop.finControl.response.IUsuarioResponse;
import br.com.develop.finControl.response.UsuarioResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IFamiliaRepository familiaRepository;

    public List<IUsuarioResponse> listarUsuarios() {
        return this.usuarioRepository.listarUsuarios();
    }

    public Optional<IUsuarioResponse> obterUsuario(String cpf) {
        return this.usuarioRepository.obterUsuarioPorCpf(cpf);
    }

    public IUsuarioResponse cadastrarUsuario(UsuarioCadastrarRequest request) {
        validaIdFamilia(request.getIdFamilia());
        var usuario = new Usuario(request);
        this.usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    public Optional<IUsuarioResponse> atualizarUsuario(String cpf, UsuarioAtualizarRequest request) {
        validaIdFamilia(request.getIdFamilia());
        return Optional.ofNullable(this.usuarioRepository.findByCpf(cpf)
                .map(v -> {
                    v.setNome(request.getNome());
                    v.setEmail(request.getEmail());
                    v.setIdFamilia(request.getIdFamilia());
                    this.usuarioRepository.save(v);
                    return new UsuarioResponse(v);
                }).orElse(null));
    }

    public boolean deletarUsuario(String cpf) {
        return this.usuarioRepository.deletarPorCpf(cpf) > 0;
    }

    private void validaIdFamilia(Long request) {
        if (Objects.nonNull(request)) {
            this.familiaRepository.existeFamilia(request)
                    .orElseThrow(() -> new EntityNotFoundException("Familia não encontrado com esse idFamilia na base de dados."));
        }
    }

}
