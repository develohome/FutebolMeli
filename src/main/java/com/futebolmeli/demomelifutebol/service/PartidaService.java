package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    public List<Partida> buscarPartidas() {
        return partidaRepository.findAll();
    }

    public Partida buscarPartidasPorId(Long id) {
        return partidaRepository.findById(id).orElse(null);
    }

    public String criarPartida(Partida partida) {
        partidaRepository.save(partida);
        return partida.getTime1() + " X " + partida.getTime2() + " cadastrado com sucesso!";
    }
}
