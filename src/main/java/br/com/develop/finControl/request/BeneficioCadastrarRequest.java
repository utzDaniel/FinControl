package br.com.develop.finControl.request;

import br.com.develop.finControl.enums.TipoBeneficio;
import br.com.develop.finControl.util.ValidEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BeneficioCadastrarRequest {

    @NotNull(message = "O campo 'valor' não pode ser nulo")
    @Digits(integer = 8, fraction = 2, message = "O campo 'valor' deve ter até 8 dígitos inteiros e 2 casas decimais.")
    @DecimalMin(value = "0.01", message = "O campo 'valor' não pode ser menor que 0.01")
    private BigDecimal valor;

    @NotNull(message = "O campo 'tipoBeneficio' não pode ser nulo")
    @ValidEnum(enumClass = TipoBeneficio.class, message = "O campo 'tipoBeneficio' é inválido.")
    private Long tipoBeneficio;

    private LocalDateTime data;

}