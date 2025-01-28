package br.com.develop.finControl.entidade;

import br.com.develop.finControl.request.ItemCadastrarRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;

    public Item(ItemCadastrarRequest request) {
        this.nome = request.getNome();
    }
}
