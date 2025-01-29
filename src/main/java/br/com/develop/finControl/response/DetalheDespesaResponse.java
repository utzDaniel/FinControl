package br.com.develop.finControl.response;

import br.com.develop.finControl.entidade.DetalheDespesa;
import br.com.develop.finControl.request.DetalheDespesaCadastrarRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class DetalheDespesaResponse implements IDetalheDespesaResponse {

    private Long idDespesa;
    private Long idItem;
    private Long idFamilia;
    private Long tipoDespesa;
    private BigDecimal preco;
    private Long quantidade;
    private String descricao;

    public DetalheDespesaResponse(Integer idFamilia, DetalheDespesaCadastrarRequest request) {
        this.idDespesa = request.getIdDespesa();
        this.idItem = request.getIdItem();
        this.idFamilia = Objects.isNull(idFamilia) ? null : (long) idFamilia;
        this.tipoDespesa = request.getTipoDespesa();
        this.preco = request.getPreco();
        this.quantidade = request.getQuantidade();
        this.descricao = request.getDescricao();
    }

    public DetalheDespesaResponse(DetalheDespesa detalheDespesa) {
        this.idDespesa = detalheDespesa.getId1();
        this.idItem = detalheDespesa.getId2();
        this.idFamilia = detalheDespesa.getIdFamilia();
        this.tipoDespesa = detalheDespesa.getIdDominioDespesa();
        this.preco = detalheDespesa.getPreco();
        this.quantidade = detalheDespesa.getQuantidade();
        this.descricao = detalheDespesa.getDescricao();
    }

}
