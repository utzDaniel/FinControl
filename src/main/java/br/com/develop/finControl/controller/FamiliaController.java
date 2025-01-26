package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.FamiliaAtualizarRequest;
import br.com.develop.finControl.request.FamiliaCadastrarRequest;
import br.com.develop.finControl.response.IFamiliaResponse;
import br.com.develop.finControl.service.FamiliaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/familias")
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    @GetMapping
    public ResponseEntity<List<IFamiliaResponse>> listarFamilias() {
        return new ResponseEntity<>(this.familiaService.listarFamilias(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IFamiliaResponse> obterFamilia(@PathVariable Long id) {
        return this.familiaService.obterFamilia(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IFamiliaResponse> cadastrarFamilia(@RequestBody @Valid FamiliaCadastrarRequest request) {
        return new ResponseEntity<>(this.familiaService.cadastrarFamilia(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IFamiliaResponse> atualizarFamilia(@PathVariable Long id, @RequestBody @Valid FamiliaAtualizarRequest request) {
        return this.familiaService.atualizarFamilia(id, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFamilia(@PathVariable Long id) {
        return this.familiaService.deletarFamilia(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
