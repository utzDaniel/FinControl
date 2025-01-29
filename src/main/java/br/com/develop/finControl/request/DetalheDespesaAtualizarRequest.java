package br.com.develop.finControl.request;

import br.com.develop.finControl.enums.TipoDespesa;
import br.com.develop.finControl.util.ValidEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalheDespesaAtualizarRequest {

    @NotNull(message = "O campo 'tipoDespesa' não pode ser nulo")
    @ValidEnum(enumClass = TipoDespesa.class, message = "O campo 'tipoDespesa' é inválido.")
    private Long tipoDespesa;

    @NotNull(message = "O campo 'preco' não pode ser nulo")
    @Digits(integer = 8, fraction = 2, message = "O campo 'preco' deve ter até 8 dígitos inteiros e 2 casas decimais.")
    @DecimalMin(value = "0.01", message = "O campo 'preco' não pode ser menor que 0.01")
    private BigDecimal preco;

    @NotNull(message = "O campo 'quantidade' não pode ser nulo")
    @Min(value = 1, message = "O campo 'quantidade' deve ser um número positivo.")
    private Long quantidade;

    @Size(min = 3, max = 255, message = "O campo 'descricao' deve ter entre 3 e 255 caracteres")
    private String descricao;

}