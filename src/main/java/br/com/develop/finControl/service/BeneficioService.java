package br.com.develop.finControl.service;

import br.com.develop.finControl.repository.IBeneficioRepository;
import br.com.develop.finControl.request.BeneficioAtualizarRequest;
import br.com.develop.finControl.request.BeneficioCadastrarRequest;
import br.com.develop.finControl.response.BeneficioResponse;
import br.com.develop.finControl.response.IBeneficioResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficioService {

    @Autowired
    private IBeneficioRepository beneficioRepository;

    public List<IBeneficioResponse> listarBeneficiosUsuario(String cpf) {
        return this.beneficioRepository.listarBeneficiosUsuario(cpf);
    }

    public IBeneficioResponse cadastrarBeneficio(String cpf, BeneficioCadastrarRequest request) {
        var data = LocalDateTime.now(ZoneOffset.UTC);
        var id = this.beneficioRepository.cadastrarBeneficio(cpf, request.getValor(), data, request.getTipo());
        if (id == 0) throw new EntityNotFoundException("Usuario não encontrado com esse cpf na base de dados.");
        return new BeneficioResponse(id, request.getValor(), data, request.getTipo());
    }

    public Optional<IBeneficioResponse> atualizarBeneficio(Long id, BeneficioAtualizarRequest request) {
        return Optional.ofNullable(this.beneficioRepository.findById(id)
                .map(v -> {
                    v.setValor(request.getValor());
                    v.setIdDominioBeneficio(request.getTipo());
                    this.beneficioRepository.save(v);
                    return new BeneficioResponse(v);
                }).orElse(null));
    }

    public boolean deletarBeneficio(Long id) {
        return this.beneficioRepository.deletarPorId(id) > 0;
    }

}
