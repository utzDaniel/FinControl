package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Familia;
import br.com.develop.finControl.response.IFamiliaResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IFamiliaRepository extends JpaRepository<Familia, Long> {

    @Query(value = "select id as id, nom as nome " +
            "from familia", nativeQuery = true)
    List<IFamiliaResponse> listarFamilias();

    @Query(value = "select id as id, nom as nome " +
            "from familia where id = :id", nativeQuery = true)
    Optional<IFamiliaResponse> obterFamiliaPorId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete familia where id = :id; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long id);

    @Query(value = "select 1 from familia where id = :id", nativeQuery = true)
    Optional<Integer> existeFamilia(Long id);

}