package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.exception.PartidaException;
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
        try{
            PartidaException partidaException = new PartidaException();
            List<Partida> partidaList = partidaRepository.findAll();

            if(partidaException.validarDataAnterior(partida.getData()) != null){
                return partidaException.validarDataAnterior(partida.getData());
            }

            if(partidaException.validarDataPArtida(partida.getData(), partidaList) != null){
                return partidaException.validarDataPArtida(partida.getData(), partidaList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        partidaRepository.save(partida);
        return partida.getTime1() + " X " + partida.getTime2() + " cadastrado com sucesso!";
    }

    public String deletarPartida(Long id) {
        partidaRepository.deleteById(id);
        return "Partida deletada com sucesso!";
    }

}
