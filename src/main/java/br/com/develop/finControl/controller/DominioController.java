package br.com.develop.finControl.controller;

import br.com.develop.finControl.response.IDominioResponse;
import br.com.develop.finControl.service.DominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dominio")
public class DominioController {

    @Autowired
    private DominioService dominioService;

    @GetMapping("/pagamentos")
    public ResponseEntity<List<IDominioResponse>> listarDominioPagamentos() {
        return new ResponseEntity<>(this.dominioService.listarDominioPagamentos(), HttpStatus.OK);
    }

    @GetMapping("/beneficios")
    public ResponseEntity<List<IDominioResponse>> listarDominioBeneficios() {
        return new ResponseEntity<>(this.dominioService.listarDominioBeneficios(), HttpStatus.OK);
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<IDominioResponse>> listarDominioDespesas() {
        return new ResponseEntity<>(this.dominioService.listarDominioDespesas(), HttpStatus.OK);
    }

}
