package com.futebolmeli.demomelifutebol.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tb_partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time1;
    private String time2;
    private Long idTime1;
    private Long idTime2;
    private int resultado1;
    private int resultado2;
    private String estadio;
    private LocalDate data;
    private LocalTime hora;
    private Long idEstadio;


    public Partida() {
    }

    public Partida(Long id, String time1, String time2, Long idTime1, Long idTime2, int resultado1, int resultado2, String estadio, LocalDate data, LocalTime hora,  Long idEstadio) {
        this.id = id;
        this.time1 = time1;
        this.time2 = time2;
        this.idTime1 = idTime1;
        this.idTime2 = idTime2;
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
        this.estadio = estadio;
        this.data = data;
        this.hora = hora;
        this.idEstadio = idEstadio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public int getResultado1() {
        return resultado1;
    }

    public void setResultado1(int resultado1) {
        this.resultado1 = resultado1;
    }

    public int getResultado2() {
        return resultado2;
    }

    public void setResultado2(int resultado2) {
        this.resultado2 = resultado2;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getIdTime1() {
        return idTime1;
    }

    public void setIdTime1(Long idTime1) {
        this.idTime1 = idTime1;
    }

    public Long getIdTime2() {
        return idTime2;
    }

    public void setIdTime2(Long idTime2) {
        this.idTime2 = idTime2;
    }

    public Long getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(Long idEstadio) {
        this.idEstadio = idEstadio;
    }
}
