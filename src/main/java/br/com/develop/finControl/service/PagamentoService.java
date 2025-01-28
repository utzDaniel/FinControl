package br.com.develop.finControl.service;

import br.com.develop.finControl.enums.TipoPagamento;
import br.com.develop.finControl.exception.RegraExeception;
import br.com.develop.finControl.repository.IDespesaRepository;
import br.com.develop.finControl.repository.IPagamentoRepository;
import br.com.develop.finControl.request.PagamentoAtualizarRequest;
import br.com.develop.finControl.request.PagamentoCadastrarRequest;
import br.com.develop.finControl.response.IPagamentoResponse;
import br.com.develop.finControl.response.PagamentoResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository pagamentoRepository;
    @Autowired
    private IDespesaRepository despesaRepository;

    public List<IPagamentoResponse> listarPagamentosUsuario(String cpf) {
        return this.pagamentoRepository.listarPagamentosUsuario(cpf);
    }

    public IPagamentoResponse cadastrarPagamento(Long idDespesa, String cpf, PagamentoCadastrarRequest request) {
        validaTipo(request.getTipoPagamento(), request.getTipoBeneficio());
        this.despesaRepository.existeDespesa(idDespesa)
                .orElseThrow(() -> new EntityNotFoundException("Despesa não encontrado com esse id na base de dados."));
        this.pagamentoRepository.existePagamentoDespesa(idDespesa)
                .orElseThrow(() -> new RegraExeception("Já existe um pagamento associada nessa despesa."));
        var data = Objects.isNull(request.getData()) ? LocalDateTime.now(ZoneOffset.UTC) : request.getData();
        var id = this.pagamentoRepository.cadastrarPagamento(idDespesa, cpf, data, request.getTipoPagamento(), request.getTipoBeneficio());
        if (id == 0) throw new EntityNotFoundException("Usuario não encontrado com esse cpf na base de dados.");
        return new PagamentoResponse(id, data, request.getTipoPagamento(), request.getTipoBeneficio());
    }

    public Optional<IPagamentoResponse> atualizarPagamento(Long id, PagamentoAtualizarRequest request) {
        validaTipo(request.getTipoPagamento(), request.getTipoBeneficio());
        return Optional.ofNullable(this.pagamentoRepository.findById(id)
                .map(v -> {
                    v.setIdDominioPagamento(request.getTipoPagamento());
                    v.setIdDominioBeneficio(request.getTipoBeneficio());
                    v.setData(Objects.isNull(request.getData()) ? LocalDateTime.now(ZoneOffset.UTC) : request.getData());
                    this.pagamentoRepository.save(v);
                    return new PagamentoResponse(v);
                }).orElse(null));
    }

    public boolean deletarPagamento(Long id) {
        return this.pagamentoRepository.deletarPorId(id) > 0;
    }

    private static void validaTipo(Long tipoPagamento, Long tipoBeneficio) {
        if (Objects.equals(TipoPagamento.BENEFICIO.getId(), tipoPagamento)) {
            if (Objects.isNull(tipoBeneficio))
                throw new RegraExeception("O campo 'tipoBeneficio' deve preenchido quando o campo 'tipoPagamento' for igual a 1.");
        } else {
            if (Objects.nonNull(tipoBeneficio))
                throw new RegraExeception("O campo 'tipoBeneficio' não deve preenchido quando o campo 'tipoPagamento' for diferente de 1.");
        }
    }

}
