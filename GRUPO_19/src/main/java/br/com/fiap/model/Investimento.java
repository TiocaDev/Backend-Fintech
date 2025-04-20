package br.com.fiap.model;

import java.time.LocalDate;

public class Investimento {
  private Long id;
  private String nome;
  private Double valor;
  private LocalDate dataInclusao;
  private String nomeInsituicao;

  public Investimento(Long id, String nome, Double valor, LocalDate dataInclusao, String nomeInsituicao) {
    this.id = id;
    this.nome = nome;
    this.valor = valor;
    this.dataInclusao = dataInclusao;
    this.nomeInsituicao = nomeInsituicao;
  }

  public Investimento(String nome, Double valor, LocalDate dataInclusao, String nomeInsituicao) {
    this.nome = nome;
    this.valor = valor;
    this.dataInclusao = dataInclusao;
    this.nomeInsituicao = nomeInsituicao;
  }

  public Investimento(){}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public LocalDate getDataInclusao() {
    return dataInclusao;
  }

  public void setDataInclusao(LocalDate dataInclusao) {
    this.dataInclusao = dataInclusao;
  }

  public String getNomeInsituicao() {
    return nomeInsituicao;
  }

  public void setNomeInsituicao(String nomeInsituicao) {
    this.nomeInsituicao = nomeInsituicao;
  }
}
