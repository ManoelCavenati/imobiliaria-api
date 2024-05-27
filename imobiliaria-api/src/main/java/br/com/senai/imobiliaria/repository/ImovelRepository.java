package br.com.senai.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.imobiliaria.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

}