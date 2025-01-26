package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.Familia;
import br.com.develop.finControl.repository.IFamiliaRepository;
import br.com.develop.finControl.request.FamiliaAtualizarRequest;
import br.com.develop.finControl.request.FamiliaCadastrarRequest;
import br.com.develop.finControl.response.IFamiliaResponse;
import br.com.develop.finControl.response.FamiliaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliaService {

    @Autowired
    private IFamiliaRepository familiaRepository;

    public List<IFamiliaResponse> listarFamilias() {
        return this.familiaRepository.listarFamilias();
    }

    public Optional<IFamiliaResponse> obterFamilia(Long id) {
        return this.familiaRepository.obterFamiliaPorId(id);
    }

    public IFamiliaResponse cadastrarFamilia(FamiliaCadastrarRequest request) {
        var familia = new Familia(request);
        this.familiaRepository.save(familia);
        return new FamiliaResponse(familia);
    }

    public Optional<IFamiliaResponse> atualizarFamilia(Long id, FamiliaAtualizarRequest request) {
        return Optional.ofNullable(this.familiaRepository.findById(id)
                .map(v -> {
                    v.setNome(request.getNome());
                    this.familiaRepository.save(v);
                    return new FamiliaResponse(v);
                }).orElse(null));
    }

    public boolean deletarFamilia(Long id) {
        return this.familiaRepository.deletarPorId(id) > 0;
    }

}
