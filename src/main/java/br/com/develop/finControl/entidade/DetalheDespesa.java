package br.com.develop.finControl.entidade;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@IdClass(ChaveComposta.class)
@Table(name = "detalhe_despesa")
public class DetalheDespesa {

    @Id
    @Column(name = "id_dps")
    private Long id1;

    @Id
    @Column(name = "id_itm")
    private Long id2;

    @Column(name = "id_dom_dps")
    private Long idDominioDespesa;

    @Column(name = "id_usr")
    private Long idUsuario;

    @Column(name = "id_fml")
    private Long idFamilia;

    @Column(name = "qtd")
    private Long quantidade;

    @Column(name = "dsc")
    private String descricao;

    @Column(name = "prc")
    private BigDecimal preco;
}
