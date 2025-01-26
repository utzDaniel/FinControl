package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Salario;
import br.com.develop.finControl.response.ISalarioResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ISalarioRepository extends JpaRepository<Salario, Long> {

    @Query(value = "select salario.id as id, salario.vlr_liq as valorLiquido, salario.dat as data " +
            "from salario inner join usuario on salario.id_usr = usuario.id " +
            "where usuario.cpf = :cpf " +
            "order by salario.dat desc", nativeQuery = true)
    List<ISalarioResponse> listarSalariosUsuario(String cpf);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO salario " +
            "(id_usr, vlr_liq, dat) " +
            "SELECT usuario.id, :valorLiquido, :data " +
            "FROM usuario " +
            "WHERE usuario.cpf = :cpf; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int cadastrarSalario(String cpf, BigDecimal valorLiquido, LocalDateTime data);

    @Modifying
    @Transactional
    @Query(value = "delete salario where id = :id; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long id);

}