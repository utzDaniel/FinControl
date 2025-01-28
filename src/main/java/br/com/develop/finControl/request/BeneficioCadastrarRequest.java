package br.com.develop.finControl.request;

import br.com.develop.finControl.enuns.TipoBeneficio;
import br.com.develop.finControl.enuns.ValidEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BeneficioCadastrarRequest {

    @NotNull(message = "O campo 'valor' não pode ser nulo")
    @Digits(integer = 8, fraction = 2, message = "O campo 'valor' deve ter até 8 dígitos inteiros e 2 casas decimais.")
    @DecimalMin(value = "0.01", message = "O campo 'valor' não pode ser menor que 0.01")
    private BigDecimal valor;

    @NotNull(message = "O campo 'tipo' não pode ser nulo")
    @ValidEnum(enumClass = TipoBeneficio.class, message = "O campo 'tipo' é inválido.")
    private Long tipo;

}