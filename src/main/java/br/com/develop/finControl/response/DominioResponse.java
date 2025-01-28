package br.com.develop.finControl.response;

import lombok.Data;

@Data
public class DominioResponse implements IDominioResponse {

    private Long id;
    private String nome;

}
