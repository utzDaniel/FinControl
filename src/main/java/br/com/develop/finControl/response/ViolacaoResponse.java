package br.com.develop.finControl.response;

import lombok.Data;
import org.springframework.validation.FieldError;

@Data
public final class ViolacaoResponse {

    private String campo;
    private String motivo;
    private Object valor;

    public ViolacaoResponse(FieldError error) {
        this.campo = error.getField();
        this.motivo = error.getDefaultMessage();
        this.valor = error.getRejectedValue();
    }

}
