package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Estados;
import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.exception.ClubeException;
import com.futebolmeli.demomelifutebol.repository.ClubeRepository;
import com.futebolmeli.demomelifutebol.repository.EstadosRepository;
import com.futebolmeli.demomelifutebol.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Autowired
    private PartidaRepository partidaRepository;

    public List<Clube> listarClubes() {
        return clubeRepository.findAll();
    }

    public List<Clube> buscarClubePorId(Long id) {
        if(!clubeRepository.existsById(id)){
            return null; // verificar
        }
        Optional<Clube> clube = clubeRepository.findById(id);
        return Collections.singletonList(clube.orElse(null));
    }

    public String cadastrar(Clube clube) {
        try {
            if(clube.getClube().length() < 2){
                return "Clube : " + clube.getClube() + " invalido!";
            }
            if(!this.validarEstado(clube)){
                return "Estado invalido";
            }
            if(this.validarDataDeCriacao(clube)){
                return "Data invalido";
            }
            if(this.validarClube(clube)){
                return "Clube ja existente";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        clubeRepository.save(clube);
        return clube.getClube();
    }

    public String atualizar(Long id, Clube clube) {
        try {

            if(!clubeRepository.existsById(id)){
                return "NOT FOUND";
            }
            if(clube.getClube().length() < 2){
                return "Clube : " + clube.getClube() + " invalido!";
            }

            if(!this.validarEstado(clube)){
                return "Estado invalido";
            }
            if(this.validarDataDeCriacao(clube)){
                return "Data invalido";
            }
            if (this.validarClube(clube)) {
                return "Clube ja existente";
            }


            //if(tratamentoErro.validarDataDeCricaoPartida(clube, partidaRepository.findAll()) != null) {
                //return tratamentoErro.validarDataDeCricaoPartida(clube, partidaRepository.findAll());
            //}

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clube.setId(id);
        //this.clube.put(id, clube);
        return clube.getClube() + ", atualizado com sucesso!" + id.toString();
    }

    public String deletarClub(Long id){
        if(!clubeRepository.existsById(id)){
            return "NOT FOUND";
        }
        Clube clubeEncontrado = clubeRepository.findById(id).orElse(null);
        clubeEncontrado.setAtivo(false);
        clubeEncontrado = clubeRepository.save(clubeEncontrado);
        return clubeEncontrado.getClube() + ", deletado com sucesso!";
    }


/*
* Validacoes
* */


    public Boolean validarEstado(Clube clube){
        List<Estados> estados =estadosRepository.findAll();
        for(Estados estado : estados){
            if(estado.getSigla().toUpperCase().equals(clube.getEstado().toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public Boolean validarDataDeCriacao(Clube clube) {
        return clube.getDatacriacao().isAfter(LocalDate.now())?true:false;
    }
    public Boolean validarClube(Clube clube) {
        List<Clube> clubeEncontrado = clubeRepository.findAll();
        for (Clube club : clubeEncontrado) {
            if (club.getClube().toUpperCase().equals(clube.getClube().toUpperCase()) && club.getEstado().toUpperCase().equals(clube.getEstado().toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public String validarDataDeCricaoPartida(Clube clube) {
        List<Partida> partidas = partidaRepository.findAll();
        List<Partida> partidaEncontradas = partidas.stream()
                .filter(el -> el.getTime1().equals(clube.getClube()) || el.getTime2().equals(clube.getClube())
                        && clube.getDatacriacao().isAfter(el.getData()))
                .toList();

        if(!partidaEncontradas.isEmpty()){
            return "Data encontrada";
        }
//        for (Partida partida : partidas) {
//            if(partida.getTime1().equals(clube.getClube()) || partida.getTime2().equals(clube.getClube())){
//                if(clube.getDatacriacao().isAfter(partida.getData())){
//                    return "" +  clube.getDatacriacao() + "----" + partida.getData();
//                }
//
//            }
//        }
        return null;
    }

}
