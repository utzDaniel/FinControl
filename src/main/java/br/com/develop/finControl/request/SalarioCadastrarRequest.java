package br.com.develop.finControl.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalarioCadastrarRequest {

    @NotNull(message = "O campo 'valorLiquido' não pode ser nulo")
    @Digits(integer = 8, fraction = 2, message = "O campo 'valorLiquido' deve ter até 8 dígitos inteiros e 2 casas decimais.")
    @DecimalMin(value = "0.01", message = "O campo 'valorLiquido' não pode ser menor que 0.01")
    private BigDecimal valorLiquido;

}