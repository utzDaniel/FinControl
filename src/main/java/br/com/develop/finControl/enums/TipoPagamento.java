package br.com.develop.finControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPagamento {

    BENEFICIO(1L),
    PIX(2L),
    CARTAO(3L),
    BOLETO(4L),
    DINHEIRO(5L);

    private final Long id;
}
