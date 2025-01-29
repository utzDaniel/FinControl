package br.com.develop.finControl.entidade;

import br.com.develop.finControl.request.UsuarioCadastrarRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "eml")
    private String email;

    @Column(name = "id_fml")
    private Long idFamilia;

    public Usuario(UsuarioCadastrarRequest request) {
        this.nome = request.getNome();
        this.cpf = request.getCpf();
        this.email = request.getEmail();
        this.idFamilia = request.getIdFamilia();
    }
}