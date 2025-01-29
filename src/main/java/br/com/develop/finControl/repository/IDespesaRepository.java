package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Despesa;
import br.com.develop.finControl.response.IDespesaResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDespesaRepository extends JpaRepository<Despesa, Long> {

    @Query(value = "select 1 from despesa where id = :id", nativeQuery = true)
    Optional<Integer> existeDespesa(Long id);

    @Query(value = "select id as id, nom as nome, dat_ref as dataReferencia, dat_vct as dataVencimento " +
            "from despesa where id = :id", nativeQuery = true)
    Optional<IDespesaResponse> obterDespesaPorId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete despesa where id = :id; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long id);

}