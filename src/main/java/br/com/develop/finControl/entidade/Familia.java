package br.com.develop.finControl.entidade;

import br.com.develop.finControl.request.FamiliaCadastrarRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "familia")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;

    public Familia(FamiliaCadastrarRequest request) {
        this.nome = request.getNome();
    }

}