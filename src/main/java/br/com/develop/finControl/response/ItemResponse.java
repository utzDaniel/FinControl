package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Item;
import lombok.Data;

@Data
public class ItemResponse implements IItemResponse {

    private Long id;
    private String nome;

    public ItemResponse(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
    }

}
