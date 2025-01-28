package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "salario")
public class Salario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_usr")
    private Long idUsuario;

    @Column(name = "vlr_liq")
    private BigDecimal valorLiquido;

    @Column(name = "dat")
    private LocalDateTime data;

}
