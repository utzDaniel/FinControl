package br.com.develop.finControl.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dominio_beneficio")
public class DominioBeneficio {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;

}

