package br.com.develop.finControl.entidade;

import br.com.develop.finControl.request.SalarioCadastrarRequest;
import jakarta.persistence.*;
import lombok.Data;

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
    private Double valorLiquido;

    @Column(name = "dat")
    private LocalDateTime data;

}
