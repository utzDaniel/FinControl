package br.com.develop.finControl.service;

import br.com.develop.finControl.repository.IDominioBeneficioRepository;
import br.com.develop.finControl.repository.IDominioDespesaRepository;
import br.com.develop.finControl.repository.IDominioPagamentoRepository;
import br.com.develop.finControl.response.IDominioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DominioService {

    @Autowired
    private IDominioPagamentoRepository dominioPagamentoRepository;
    @Autowired
    private IDominioBeneficioRepository dominioBeneficioRepository;
    @Autowired
    private IDominioDespesaRepository dominioDespesaRepository;

    public List<IDominioResponse> listarDominioPagamentos() {
        return this.dominioPagamentoRepository.listarDominioPagamentos();
    }

    public List<IDominioResponse> listarDominioBeneficios() {
        return this.dominioBeneficioRepository.listarDominioBeneficios();
    }

    public List<IDominioResponse> listarDominioDespesas() {
        return this.dominioDespesaRepository.listarDominioDespesas();
    }


}
