package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Estadio;
import com.futebolmeli.demomelifutebol.repository.EstadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    public List<Estadio> listarEstadios() {
       return estadioRepository.findAll();
    }
    public Estadio buscarEstadioPorId(Long id) {
        return estadioRepository.findById(id).orElse(null);
    }
    public String cadastrarEstadio(Estadio estadio) {
        estadioRepository.save(estadio);
        return "Estadio cadastrado com sucesso!";
    }
}
