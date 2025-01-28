package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.BeneficioAtualizarRequest;
import br.com.develop.finControl.request.BeneficioCadastrarRequest;
import br.com.develop.finControl.response.IBeneficioResponse;
import br.com.develop.finControl.service.BeneficioService;
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
@RequestMapping("/beneficios")
public class BeneficioController {

    @Autowired
    private BeneficioService beneficioService;

    @GetMapping("/{cpf}")
    public ResponseEntity<List<IBeneficioResponse>> listarBeneficios(@PathVariable String cpf) {
        return new ResponseEntity<>(this.beneficioService.listarBeneficiosUsuario(cpf), HttpStatus.OK);
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<IBeneficioResponse> cadastrarBeneficio(@PathVariable
                                                                 @NotNull(message = "O campo 'cpf' não pode ser nulo")
                                                                 @Pattern(regexp = Constante.REGEX_CPF, message = "O campo 'cpf' deve ter 11 caracteres numéricos")
                                                                 String cpf,
                                                                 @RequestBody @Valid BeneficioCadastrarRequest request) {
        return new ResponseEntity<>(this.beneficioService.cadastrarBeneficio(cpf, request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IBeneficioResponse> atualizarBeneficio(@PathVariable Long id, @RequestBody @Valid BeneficioAtualizarRequest request) {
        return this.beneficioService.atualizarBeneficio(id, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBeneficio(@PathVariable Long id) {
        return this.beneficioService.deletarBeneficio(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
