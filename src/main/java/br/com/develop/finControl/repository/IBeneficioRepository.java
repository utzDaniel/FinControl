package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Beneficio;
import br.com.develop.finControl.response.IBeneficioResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IBeneficioRepository extends JpaRepository<Beneficio, Long> {

    @Query(value = "select beneficio.id as id, beneficio.vlr as valor, beneficio.dat as data, beneficio.id_dmn_bnf as tipo " +
            "from beneficio inner join usuario on beneficio.id_usr = usuario.id " +
            "where usuario.cpf = :cpf " +
            "order by beneficio.dat desc", nativeQuery = true)
    List<IBeneficioResponse> listarBeneficiosUsuario(String cpf);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO beneficio " +
            "(id_usr, vlr, dat, id_dmn_bnf) " +
            "SELECT usuario.id, :valor, :data, :tipo " +
            "FROM usuario " +
            "WHERE usuario.cpf = :cpf; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int cadastrarBeneficio(String cpf, BigDecimal valor, LocalDateTime data, Long tipo);

    @Modifying
    @Transactional
    @Query(value = "delete beneficio where id = :id; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long id);

}