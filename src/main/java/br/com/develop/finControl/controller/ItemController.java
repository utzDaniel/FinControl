package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.ItemAtualizarRequest;
import br.com.develop.finControl.request.ItemCadastrarRequest;
import br.com.develop.finControl.response.IItemResponse;
import br.com.develop.finControl.response.PaginacaoResponse;
import br.com.develop.finControl.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<PaginacaoResponse<IItemResponse>> listarItens(
            @RequestParam(defaultValue = "1", required = false)
            @Min(value = 1, message = "O campo 'paginaAtual' deve ser um número positivo.")
            Long paginaAtual,
            @RequestParam(defaultValue = "10", required = false)
            @Min(value = 1, message = "O campo 'itensPorPag' deve ser um número positivo.")
            Long itensPorPagina,
            @RequestParam(required = false)
            String valor) {
        return new ResponseEntity<>(this.itemService.listarItens(paginaAtual, itensPorPagina, valor), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IItemResponse> obterItem(@PathVariable Long id) {
        return this.itemService.obterItem(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IItemResponse> cadastrarItem(@RequestBody @Valid ItemCadastrarRequest request) {
        return new ResponseEntity<>(this.itemService.cadastrarItem(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IItemResponse> atualizarItem(@PathVariable Long id, @RequestBody @Valid ItemAtualizarRequest request) {
        return this.itemService.atualizarItem(id, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        return this.itemService.deletarItem(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
