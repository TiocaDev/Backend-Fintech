package br.com.fiap.model;

import java.time.LocalDate;

public class User {

  private Long id;
  private String nome;
  private String email;
  private LocalDate dataCriacao;

  public User(Long id, String nome, String email, LocalDate dataCriacao) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.dataCriacao = dataCriacao;
  }

  public User(String nome, String email, LocalDate dataCriacao) {
    this.nome = nome;
    this.email = email;
    this.dataCriacao = dataCriacao;
  }

  public User() {
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }
}
