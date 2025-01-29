package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.DetalheDespesaAtualizarRequest;
import br.com.develop.finControl.request.DetalheDespesaCadastrarRequest;
import br.com.develop.finControl.response.IDetalheDespesaResponse;
import br.com.develop.finControl.service.DetalheDespesaService;
import br.com.develop.finControl.util.Constante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/detalhe-despesa")
public class DetalheDespesaController {

    @Autowired
    private DetalheDespesaService detalheDespesaService;

    @PostMapping("/{cpf}")
    public ResponseEntity<IDetalheDespesaResponse> cadastrarDetalheDespesa(@PathVariable
                                                                           @NotNull(message = "O campo 'cpf' não pode ser nulo")
                                                                           @Pattern(regexp = Constante.REGEX_CPF, message = "O campo 'cpf' deve ter 11 caracteres numéricos")
                                                                           String cpf,
                                                                           @RequestBody @Valid DetalheDespesaCadastrarRequest request) {
        return new ResponseEntity<>(this.detalheDespesaService.cadastrarDetalheDespesa(cpf, request), HttpStatus.CREATED);
    }

    @PutMapping("/{idDespesa}/{idItem}")
    public ResponseEntity<IDetalheDespesaResponse> atualizarDetalheDespesa(@PathVariable Long idDespesa,
                                                                           @PathVariable Long idItem,
                                                                           @RequestBody @Valid DetalheDespesaAtualizarRequest request) {
        return this.detalheDespesaService.atualizarDetalheDespesa(idDespesa, idItem, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idDespesa}/{idItem}")
    public ResponseEntity<Void> deletarDetalheDespesa(@PathVariable Long idDespesa, @PathVariable Long idItem) {
        return this.detalheDespesaService.deletarDetalheDespesa(idDespesa, idItem) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
