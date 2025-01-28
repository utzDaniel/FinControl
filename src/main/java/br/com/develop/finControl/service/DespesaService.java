package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.Despesa;
import br.com.develop.finControl.repository.IDespesaRepository;
import br.com.develop.finControl.request.DespesaAtualizarRequest;
import br.com.develop.finControl.request.DespesaCadastrarRequest;
import br.com.develop.finControl.response.DespesaResponse;
import br.com.develop.finControl.response.IDespesaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private IDespesaRepository despesaRepository;

    public Optional<IDespesaResponse> obterDespesa(Long id) {
        return this.despesaRepository.obterDespesaPorId(id);
    }

    public IDespesaResponse cadastrarDespesa(DespesaCadastrarRequest request) {
        var despesa = new Despesa(request);
        this.despesaRepository.save(despesa);
        return new DespesaResponse(despesa);
    }

    public Optional<IDespesaResponse> atualizarDespesa(Long id, DespesaAtualizarRequest request) {
        return Optional.ofNullable(this.despesaRepository.findById(id)
                .map(v -> {
                    v.setNome(request.getNome());
                    v.setDataReferencia(Objects.isNull(request.getDataReferencia()) ? LocalDateTime.now(ZoneOffset.UTC) : request.getDataReferencia());
                    v.setDataVencimento(request.getDataVencimento());
                    this.despesaRepository.save(v);
                    return new DespesaResponse(v);
                }).orElse(null));
    }

    public boolean deletarDespesa(Long id) {
        return this.despesaRepository.deletarPorId(id) > 0;
    }

}
