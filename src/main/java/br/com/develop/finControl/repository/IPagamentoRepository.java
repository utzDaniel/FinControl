package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Pagamento;
import br.com.develop.finControl.response.IPagamentoResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query(value = "select pagamento.id_dps from pagamento " +
            "inner join despesa on pagamento.id_dps = despesa.id " +
            "where despesa.id = :id", nativeQuery = true)
    Optional<Integer> existePagamentoDespesa(Long id);

    @Query(value = "select pagamento.id as id, pagamento.id_dom_pgt as tipoPagamento, " +
            "pagamento.id_dmn_bnf as tipoBeneficio, pagamento.dat as data " +
            "from pagamento inner join usuario on pagamento.id_usr = usuario.id " +
            "where usuario.cpf = :cpf " +
            "order by pagamento.dat desc", nativeQuery = true)
    List<IPagamentoResponse> listarPagamentosUsuario(String cpf);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pagamento " +
            "(id_dps, id_usr, dat, id_dom_pgt, id_dmn_bnf) " +
            "SELECT :idDespesa, usuario.id, :data, :tipoPagamento, :tipoBeneficio " +
            "FROM usuario " +
            "WHERE usuario.cpf = :cpf; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int cadastrarPagamento(Long idDespesa, String cpf, LocalDateTime data, Long tipoPagamento, Long tipoBeneficio);

    @Modifying
    @Transactional
    @Query(value = "delete pagamento where id = :id; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long id);

}