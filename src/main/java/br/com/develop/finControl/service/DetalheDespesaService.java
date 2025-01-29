package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.ChaveComposta;
import br.com.develop.finControl.repository.IDespesaRepository;
import br.com.develop.finControl.repository.IDetalheDespesaRepository;
import br.com.develop.finControl.repository.IItemRepository;
import br.com.develop.finControl.repository.IUsuarioRepository;
import br.com.develop.finControl.request.DetalheDespesaAtualizarRequest;
import br.com.develop.finControl.request.DetalheDespesaCadastrarRequest;
import br.com.develop.finControl.response.DetalheDespesaResponse;
import br.com.develop.finControl.response.IDetalheDespesaResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalheDespesaService {

    @Autowired
    private IDetalheDespesaRepository detalheDespesaRepository;
    @Autowired
    private IDespesaRepository despesaRepository;
    @Autowired
    private IItemRepository itemRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public IDetalheDespesaResponse cadastrarDetalheDespesa(String cpf, DetalheDespesaCadastrarRequest request) {
        this.despesaRepository.existeDespesa(request.getIdDespesa())
                .orElseThrow(() -> new EntityNotFoundException("Despesa não encontrado com esse idDespesa na base de dados."));
        this.itemRepository.existeItem(request.getIdItem())
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com esse idItem na base de dados."));
        var idFamilia = buscaIdFamilia(cpf);
        var id = this.detalheDespesaRepository.cadastrarDetalheDespesa(cpf, request.getIdDespesa(), request.getIdItem(),
                request.getTipoDespesa(), request.getQuantidade(), request.getPreco(), request.getDescricao(),
                request.getDespesaFamiliar() ? 1 : 0);
        if (id == 0) throw new EntityNotFoundException("Usuario não encontrado com esse cpf na base de dados.");
        return new DetalheDespesaResponse(idFamilia, request);
    }

    public Optional<IDetalheDespesaResponse> atualizarDetalheDespesa(Long idDespesa, Long idItem, DetalheDespesaAtualizarRequest request) {
        return Optional.ofNullable(this.detalheDespesaRepository.findById(new ChaveComposta(idDespesa, idItem))
                .map(v -> {
                    v.setPreco(request.getPreco());
                    v.setIdDominioDespesa(request.getTipoDespesa());
                    v.setDescricao(request.getDescricao());
                    v.setQuantidade(request.getQuantidade());
                    this.detalheDespesaRepository.save(v);
                    return new DetalheDespesaResponse(v);
                }).orElse(null));
    }

    public boolean deletarDetalheDespesa(Long idDespesa, Long idItem) {
        return this.detalheDespesaRepository.deletarPorId(idDespesa, idItem) > 0;
    }

    private Integer buscaIdFamilia(String cpf) {
        return this.usuarioRepository.existeFamilia(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Familia não encontrada com esse cpf na base de dados."));
    }

}
