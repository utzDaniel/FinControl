package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.ChaveComposta;
import br.com.develop.finControl.entidade.DetalheDespesa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface IDetalheDespesaRepository extends JpaRepository<DetalheDespesa, ChaveComposta> {


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO detalhe_despesa " +
            "(id_dps, id_itm, id_dom_dps, qtd, prc, dsc, id_usr, id_fml) " +
            "SELECT :idDespesa, :idItem, :tipoDespesa, :quantidade, :preco, :descricao, " +
            "CASE WHEN 1 = :idCase THEN null ELSE id END AS id_usr, " +
            "CASE WHEN 1 = :idCase THEN id_fml ELSE null END AS id_fml " +
            "FROM usuario " +
            "WHERE usuario.cpf = :cpf; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int cadastrarDetalheDespesa(String cpf, Long idDespesa, Long idItem, Long tipoDespesa, Long quantidade,
                                BigDecimal preco, String descricao, int idCase);

    @Modifying
    @Transactional
    @Query(value = "delete detalhe_despesa where id_dps = :idDespesa and id_itm = :idItem; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long idDespesa, Long idItem);

}