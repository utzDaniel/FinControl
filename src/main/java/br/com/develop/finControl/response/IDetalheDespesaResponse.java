package br.com.develop.finControl.response;

import java.math.BigDecimal;

public interface IDetalheDespesaResponse {

    Long getIdDespesa();

    Long getIdItem();

    Long getIdFamilia();

    Long getTipoDespesa();

    BigDecimal getPreco();

    Long getQuantidade();

    String getDescricao();

}
