package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.SalarioAtualizarRequest;
import br.com.develop.finControl.request.SalarioCadastrarRequest;
import br.com.develop.finControl.response.ISalarioResponse;
import br.com.develop.finControl.service.SalarioService;
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
@RequestMapping("/salarios")
public class SalarioController {

    @Autowired
    private SalarioService salarioService;

    @GetMapping("/{cpf}")
    public ResponseEntity<List<ISalarioResponse>> listarSalarios(@PathVariable String cpf) {
        return new ResponseEntity<>(this.salarioService.listarSalariosUsuario(cpf), HttpStatus.OK);
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<ISalarioResponse> cadastrarSalario(@PathVariable
                                                             @NotNull(message = "O campo 'cpf' não pode ser nulo")
                                                             @Pattern(regexp = Constante.REGEX_CPF, message = "O campo 'cpf' deve ter 11 caracteres numéricos")
                                                             String cpf,
                                                             @RequestBody @Valid SalarioCadastrarRequest request) {
        return new ResponseEntity<>(this.salarioService.cadastrarSalario(cpf, request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ISalarioResponse> atualizarSalario(@PathVariable Long id, @RequestBody @Valid SalarioAtualizarRequest request) {
        return this.salarioService.atualizarSalario(id, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSalario(@PathVariable Long id) {
        return this.salarioService.deletarSalario(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
