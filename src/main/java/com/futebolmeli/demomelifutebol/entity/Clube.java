package com.futebolmeli.demomelifutebol.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "tb_clubes")
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(unique = true, nullable = false)
    private String clube;

    private String estado;
    private LocalDate datacriacao;
    private Boolean ativo  = true;

    public Clube() {
    }

    public Clube(Long id, String clube, String estado, LocalDate datacriacao, Boolean ativo) {
        this.id = id;
        this.clube = clube;
        this.estado = estado;
        this.datacriacao = datacriacao;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(LocalDate datacriacao) {
        this.datacriacao = datacriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
