package br.com.develop.finControl.service;

import br.com.develop.finControl.entidade.Item;
import br.com.develop.finControl.repository.IItemRepository;
import br.com.develop.finControl.request.ItemAtualizarRequest;
import br.com.develop.finControl.request.ItemCadastrarRequest;
import br.com.develop.finControl.response.IItemResponse;
import br.com.develop.finControl.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    public List<IItemResponse> listarItens() {
        return this.itemRepository.listarItens();
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
