package com.futebolmeli.demomelifutebol.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_estados")
public class Estados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;

    private String sigla;

    public Estados() {
    }

    public Estados(Long id, String estado, String sigla) {
        this.id = id;
        this.estado = estado;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
