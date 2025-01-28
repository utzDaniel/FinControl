package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_usr")
    private Long idUsuario;

    @Column(name = "id_dmn_bnf")
    private Long idDominioBeneficio;

    @Column(name = "vlr")
    private BigDecimal valor;

    @Column(name = "dat")
    private LocalDateTime data;

}

