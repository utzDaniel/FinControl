package br.com.develop.finControl.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDespesa {

    ESSENCIAL(1L),
    NECESSIDADE(2L),
    OUTROS(3L);

    private final Long id;

}
