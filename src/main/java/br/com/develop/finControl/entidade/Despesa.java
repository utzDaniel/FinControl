package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;

    @Column(name = "dat_ref")
    private LocalDate dataReferencia;

    @Column(name = "dat_vct")
    private LocalDate dataVencimento;

}
