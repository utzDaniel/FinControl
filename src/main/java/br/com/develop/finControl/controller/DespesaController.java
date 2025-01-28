package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.DespesaAtualizarRequest;
import br.com.develop.finControl.request.DespesaCadastrarRequest;
import br.com.develop.finControl.response.IDespesaResponse;
import br.com.develop.finControl.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping("/{id}")
    public ResponseEntity<IDespesaResponse> obterDespesa(@PathVariable Long id) {
        return this.despesaService.obterDespesa(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IDespesaResponse> cadastrarDespesa(@RequestBody @Valid DespesaCadastrarRequest request) {
        return new ResponseEntity<>(this.despesaService.cadastrarDespesa(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IDespesaResponse> atualizarDespesa(@PathVariable Long id, @RequestBody @Valid DespesaAtualizarRequest request) {
        return this.despesaService.atualizarDespesa(id, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespesa(@PathVariable Long id) {
        return this.despesaService.deletarDespesa(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
