package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.Familia;
import lombok.Data;

@Data
public class FamiliaResponse implements IFamiliaResponse {

    private Long id;
    private String nome;

    public FamiliaResponse(Familia familia) {
        this.id = familia.getId();
        this.nome = familia.getNome();
    }

}
