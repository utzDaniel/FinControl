package br.com.develop.finControl.repository;

import br.com.develop.finControl.entidade.Item;
import br.com.develop.finControl.response.IItemResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT id as id, nom as nome " +
            "FROM item  " +
            "WHERE (:valor IS NULL OR CONCAT(id, ' ', nom) LIKE '%' + :valor + '%') " +
            "ORDER BY nom asc " +
            "OFFSET (:pagAtual - 1) * :itensPorPag ROWS " +
            "FETCH NEXT :itensPorPag ROWS ONLY ", nativeQuery = true)
    List<IItemResponse> listarItens(Long pagAtual, Long itensPorPag, String valor);

    @Query(value = "SELECT count(*) FROM item " +
                   "WHERE (:valor IS NULL OR CONCAT(id, ' ', nom) LIKE '%' + :valor + '%') ", nativeQuery = true)
    long quantidadeItens(String valor);


    @Query(value = "select id as id, nom as nome " +
            "from item where id = :id", nativeQuery = true)
    Optional<IItemResponse> obterItemPorId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete item where id = :id; " +
            "SELECT SCOPE_IDENTITY()", nativeQuery = true)
    int deletarPorId(Long id);

    @Query(value = "select 1 from item where id = :id", nativeQuery = true)
    Optional<Integer> existeItem(Long id);
}