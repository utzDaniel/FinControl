package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.DominioBeneficio;
import br.com.develop.finControl.response.IDominioResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDominioBeneficioRepository extends JpaRepository<DominioBeneficio, Long> {

    @Query(value = "select id as id, nom as nome " +
            "from dominio_beneficio order by nom asc", nativeQuery = true)
    List<IDominioResponse> listarDominioBeneficios();

}