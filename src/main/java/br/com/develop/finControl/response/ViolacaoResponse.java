package br.com.develop.finControl.response;

import jakarta.validation.ConstraintViolation;
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

    public ViolacaoResponse(ConstraintViolation error) {
        this.campo = getCampo(error.getPropertyPath().toString());
        this.motivo = error.getMessage();
        this.valor = error.getInvalidValue();
    }

    private String getCampo(String path) {
        int index = path.indexOf('.');
        return path.indexOf('.') != -1 ? path.substring(index + 1) : path;
    }

}
