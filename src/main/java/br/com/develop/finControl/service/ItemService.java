package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.Item;
import br.com.develop.finControl.repository.IItemRepository;
import br.com.develop.finControl.request.ItemAtualizarRequest;
import br.com.develop.finControl.request.ItemCadastrarRequest;
import br.com.develop.finControl.response.IItemResponse;
import br.com.develop.finControl.response.ItemResponse;
import br.com.develop.finControl.response.PaginacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    public PaginacaoResponse<IItemResponse> listarItens(Long pagAtual, Long itensPorPag, String valor) {
        var qtdTotalItens = this.itemRepository.quantidadeItens(valor);
        var itens = this.itemRepository.listarItens(pagAtual, itensPorPag, valor);
        var qtdPag = (long) Math.ceil((double) this.itemRepository.count() / itensPorPag);
        return new PaginacaoResponse<>(pagAtual, itensPorPag, qtdPag, qtdTotalItens, itens);
    }

    public Optional<IItemResponse> obterItem(Long id) {
        return this.itemRepository.obterItemPorId(id);
    }

    public IItemResponse cadastrarItem(ItemCadastrarRequest request) {
        var item = new Item(request);
        this.itemRepository.save(item);
        return new ItemResponse(item);
    }

    public Optional<IItemResponse> atualizarItem(Long id, ItemAtualizarRequest request) {
        return Optional.ofNullable(this.itemRepository.findById(id)
                .map(v -> {
                    v.setNome(request.getNome());
                    this.itemRepository.save(v);
                    return new ItemResponse(v);
                }).orElse(null));
    }

    public boolean deletarItem(Long id) {
        return this.itemRepository.deletarPorId(id) > 0;
    }

}
