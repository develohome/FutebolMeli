package com.futebolmeli.demomelifutebol.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_estados")
public class Estados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;

    private String silga;

    public Estados() {
    }

    public Estados(Long id, String estado, String silga) {
        this.id = id;
        this.estado = estado;
        this.silga = silga;
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

    public String getSilga() {
        return silga;
    }

    public void setSilga(String silga) {
        this.silga = silga;
    }
}
