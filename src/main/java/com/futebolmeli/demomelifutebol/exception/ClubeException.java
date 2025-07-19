package com.futebolmeli.demomelifutebol.exception;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Estados;
import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.repository.ClubeRepository;
import com.futebolmeli.demomelifutebol.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ClubeException {

    private ClubeRepository clubeRepository ;
    private Clube clube;

    public ClubeException(Clube clube) {
        this.clube = clube;
    }


    public String postValidarDataDeCriacao(){
        if(clube.getDatacriacao().isAfter(LocalDate.now())){
            return "Data de criacao invalido";
        }
        return null;
    }



    public String validarDataDeCricaoPartida(Clube clube, List<Partida> partidas) {

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
