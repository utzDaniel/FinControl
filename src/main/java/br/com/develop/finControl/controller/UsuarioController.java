package br.com.develop.finControl.controller;

import br.com.develop.finControl.request.UsuarioAtualizarRequest;
import br.com.develop.finControl.request.UsuarioCadastrarRequest;
import br.com.develop.finControl.response.IUsuarioResponse;
import br.com.develop.finControl.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<IUsuarioResponse>> listarUsuarios() {
        return new ResponseEntity<>(this.usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<IUsuarioResponse> obterUsuario(@PathVariable String cpf) {
        return this.usuarioService.obterUsuario(cpf)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IUsuarioResponse> cadastrarUsuario(@RequestBody @Valid UsuarioCadastrarRequest request) {
        return new ResponseEntity<>(this.usuarioService.cadastrarUsuario(request), HttpStatus.CREATED);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<IUsuarioResponse> atualizarUsuario(@PathVariable String cpf, @RequestBody @Valid UsuarioAtualizarRequest request) {
        return this.usuarioService.atualizarUsuario(cpf, request)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String cpf) {
        return this.usuarioService.deletarUsuario(cpf) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}