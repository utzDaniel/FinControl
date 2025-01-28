package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
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
