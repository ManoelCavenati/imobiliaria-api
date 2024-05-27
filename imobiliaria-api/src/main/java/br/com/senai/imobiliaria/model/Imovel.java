package br.com.senai.imobiliaria.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "imoveis")
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {

    @Id
    private Integer codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(length = 200, nullable = false)
    private String descricao;

    @Column(length = 200, nullable = false)
    private String endereco;

    @Column(length = 40, nullable = false)
    private String bairro;

    @Column(length = 200, nullable = false)
    private String cidade;

    @Column(nullable = false)
    private Float valor;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    public Imovel(Integer codigo, Tipo tipo, String descricao, String endereco, String bairro, String cidade,
            Float valor) {
        this(codigo, tipo, descricao, endereco, bairro, cidade, valor, LocalDate.now());
    }

}
