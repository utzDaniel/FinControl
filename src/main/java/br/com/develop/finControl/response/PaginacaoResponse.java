package br.com.develop.finControl.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginacaoResponse<T> {

    private long paginaAtual;
    private long itensPorPagina;
    private long quantidadeDePaginas;
    private long quantidadeTotalDeItens;
    private List<T> itens;
}
