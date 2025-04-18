package br.com.fiap.coinmapping.domain.reserva;

import java.time.LocalDate;

public class Reserva {
  private Long id;
  private String nome;
  private Double valor;
  private String descricao;
  private LocalDate dataInicio;
  private LocalDate daraPrazo;

  public Reserva(Long id, String nome, Double valor, String descricao, LocalDate dataInicio, LocalDate daraPrazo) {
    this.id = id;
    this.nome = nome;
    this.valor = valor;
    this.descricao = descricao;
    this.dataInicio = dataInicio;
    this.daraPrazo = daraPrazo;
  }

  public Reserva(String nome, Double valor, String descricao, LocalDate dataInicio, LocalDate daraPrazo) {
    this.nome = nome;
    this.valor = valor;
    this.descricao = descricao;
    this.dataInicio = dataInicio;
    this.daraPrazo = daraPrazo;
  }

  public Reserva(){}

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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDate getDaraPrazo() {
    return daraPrazo;
  }

  public void setDaraPrazo(LocalDate daraPrazo) {
    this.daraPrazo = daraPrazo;
  }
}
