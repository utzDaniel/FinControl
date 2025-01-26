package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dominio_pagamento")
public class DominioPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;
}
