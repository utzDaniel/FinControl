package br.com.develop.finControl.entidade;

import br.com.develop.finControl.request.DespesaCadastrarRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;

    @Column(name = "dat_ref")
    private LocalDateTime dataReferencia;

    @Column(name = "dat_vct")
    private LocalDateTime dataVencimento;

    public Despesa(DespesaCadastrarRequest request) {
        this.nome = request.getNome();
        this.dataReferencia = Objects.isNull(request.getDataReferencia()) ? LocalDateTime.now(ZoneOffset.UTC) : request.getDataReferencia();
        this.dataVencimento = request.getDataVencimento();
    }
}
