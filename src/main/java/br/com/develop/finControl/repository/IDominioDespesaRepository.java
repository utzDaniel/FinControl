package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.DominioDespesa;
import br.com.develop.finControl.response.IDominioResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDominioDespesaRepository extends JpaRepository<DominioDespesa, Long> {

    @Query(value = "select id as id, nom as nome " +
            "from dominio_despesa order by nom asc", nativeQuery = true)
    List<IDominioResponse> listarDominioDespesas();

}