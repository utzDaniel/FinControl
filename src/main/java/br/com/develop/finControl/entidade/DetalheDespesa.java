package br.com.develop.finControl.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "detalhe_despesa")
public class DetalheDespesa {

    @Id
    @Column(name = "id_dps")
    private Long idDespensa;

    @Id
    @Column(name = "id_itm")
    private Long idItem;

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
    private Double preco;
}
