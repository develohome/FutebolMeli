package com.futebolmeli.demomelifutebol.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tb_estadios")
public class Estadio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String estado;
    private LocalDate fundacao;
    private LocalTime hora;

    public Estadio() {
    }

    public Estadio(Long id, String nome, String estado, LocalDate fundacao, LocalTime hora) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.fundacao = fundacao;
        this.hora = hora;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFuncacao(LocalDate funcacao) {
        this.fundacao = funcacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
