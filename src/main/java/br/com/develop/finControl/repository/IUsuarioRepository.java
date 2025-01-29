package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Usuario;
import br.com.develop.finControl.response.IUsuarioResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query(value = "select nom as nome, cpf as cpf, eml as email, id_fml as idFamilia " +
            "from usuario", nativeQuery = true)
    List<IUsuarioResponse> listarUsuarios();

    @Query(value = "select nom as nome, cpf as cpf, eml as email, id_fml as idFamilia " +
            "from usuario where cpf = :cpf", nativeQuery = true)
    Optional<IUsuarioResponse> obterUsuarioPorCpf(String cpf);

    @Modifying
    @Transactional
    @Query(value = "delete usuario where cpf = :cpf; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorCpf(String cpf);

    Optional<Usuario> findByCpf(String cpf);

    @Query(value = "select familia.id from usuario " +
            "inner join familia on usuario.id_fml = familia.id " +
            "where cpf = :cpf", nativeQuery = true)
    Optional<Integer> existeFamilia(String cpf);
}