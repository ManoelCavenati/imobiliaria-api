package br.com.senai.imobiliaria.dto;

import br.com.senai.imobiliaria.model.Tipo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ImovelAtualizacaoRequest {

    @NotNull(message = "Campo obrigatório")
    private Tipo tipo;

    @NotEmpty(message = "Campo obrigatório")
    private String descricao;

    @NotEmpty(message = "Campo obrigatório")
    private String endereco;

    @Size(min = 1, max = 40)
    private String bairro;

    @NotEmpty(message = "Campo obrigatório")
    @Size(min = 3, max = 50, message = "Valor inválido")
    private String cidade;

    @NotNull(message = "Campo obrigatório")
    @Positive(message = "Valor inválido")
    private Float valor;

}
