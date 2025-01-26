package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_dps")
    private Long idDespesa;

    @Column(name = "id_usr")
    private Long idUsuario;

    @Column(name = "dat")
    private LocalDateTime data;

    @Column(name = "id_dom_pgt")
    private Long idDominioPagamento;

    @Column(name = "id_dmn_bnf")
    private Long idDominioBeneficio;

}
