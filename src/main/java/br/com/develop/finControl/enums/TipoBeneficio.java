package br.com.develop.finControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoBeneficio {

    VA(1L),
    VR(2L),
    FLASH(3L);

    private final Long id;

}
