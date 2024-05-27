package br.com.senai.imobiliaria.dto;

import br.com.senai.imobiliaria.model.Tipo;

import lombok.Data;

@Data
public class ImovelResponse {

    private Integer codigo;

    private Tipo tipo;

    private String descricao;

    private String endereco;

    private String bairro;

    private String cidade;

    private Float valor;

}