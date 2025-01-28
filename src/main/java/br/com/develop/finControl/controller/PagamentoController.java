package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.PagamentoAtualizarRequest;
import br.com.develop.finControl.request.PagamentoCadastrarRequest;
import br.com.develop.finControl.response.IPagamentoResponse;
import br.com.develop.finControl.service.PagamentoService;
import br.com.develop.finControl.util.Constante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/{cpf}")
    public ResponseEntity<List<IPagamentoResponse>> listarPagamentos(@PathVariable String cpf) {
        return new ResponseEntity<>(this.pagamentoService.listarPagamentosUsuario(cpf), HttpStatus.OK);
    }

    @PostMapping("/{idDespesa}/{cpf}")
    public ResponseEntity<IPagamentoResponse> cadastrarPagamento(@PathVariable Long idDespesa,
                                                                 @PathVariable
                                                                 @NotNull(message = "O campo 'cpf' não pode ser nulo")
                                                                 @Pattern(regexp = Constante.REGEX_CPF, message = "O campo 'cpf' deve ter 11 caracteres numéricos")
                                                                 String cpf,
                                                                 @RequestBody @Valid PagamentoCadastrarRequest request) {
        return new ResponseEntity<>(this.pagamentoService.cadastrarPagamento(idDespesa, cpf, request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IPagamentoResponse> atualizarPagamento(@PathVariable Long id, @RequestBody @Valid PagamentoAtualizarRequest request) {
        return this.pagamentoService.atualizarPagamento(id, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
        return this.pagamentoService.deletarPagamento(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
