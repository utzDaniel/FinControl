package br.com.develop.finControl.service;

import br.com.develop.finControl.repository.ISalarioRepository;
import br.com.develop.finControl.request.SalarioAtualizarRequest;
import br.com.develop.finControl.request.SalarioCadastrarRequest;
import br.com.develop.finControl.response.ISalarioResponse;
import br.com.develop.finControl.response.SalarioResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class SalarioService {

    @Autowired
    private ISalarioRepository salarioRepository;

    public List<ISalarioResponse> listarSalariosUsuario(String cpf) {
        return this.salarioRepository.listarSalariosUsuario(cpf);
    }

    public ISalarioResponse cadastrarSalario(String cpf, SalarioCadastrarRequest request) {
        var data = LocalDateTime.now(ZoneOffset.UTC);
        var id = this.salarioRepository.cadastrarSalario(cpf, request.getValorLiquido(), data);
        if (id == 0) throw new EntityNotFoundException("Usuario não encontrado com esse cpf na base de dados.");
        return new SalarioResponse(id, request.getValorLiquido(), data);
    }

    public Optional<ISalarioResponse> atualizarSalario(Long id, SalarioAtualizarRequest request) {
        return Optional.ofNullable(this.salarioRepository.findById(id)
                .map(v -> {
                    v.setValorLiquido(request.getValorLiquido());
                    this.salarioRepository.save(v);
                    return new SalarioResponse(v);
                }).orElse(null));
    }

    public boolean deletarSalario(Long id) {
        return this.salarioRepository.deletarPorId(id) > 0;
    }

}
