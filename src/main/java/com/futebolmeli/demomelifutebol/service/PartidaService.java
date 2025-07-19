package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.exception.PartidaException;
import com.futebolmeli.demomelifutebol.repository.ClubeRepository;
import com.futebolmeli.demomelifutebol.repository.EstadioRepository;
import com.futebolmeli.demomelifutebol.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private EstadioRepository estadioRepository;

    private boolean primeiroClubeEncontrado = false;
    private boolean segundoClubeUmEncontrado = false;
    private boolean estadioEncontrado = false;

    public PartidaService() {
    }

    public List<Partida> buscarPartidas() {
        return this.partidaRepository.findAll();
    }

    public Partida buscarPartidasPorId(Long id) {
        return this.partidaRepository.findById(id).orElse(null);
    }

    public String criarPartida(Partida partida) {
        try{
            PartidaException partidaException = new PartidaException();


            if(!validarMesmoClubeNaPartida(partida)){
                return "Nao e possivel cadastrar o mesmo clube partida";
            }

            if(!this.validarClubExistente(partida.getTime1(),  partida.getTime2())){
                return "Um dos times nao esta cadastrado";
            }

            if(!this.validarNomeEstadio(partida.getEstadio())){
                return "Estadio nao esta cadastrado";
            }

            if(partida.getResultado1() < 0 || partida.getResultado2() < 0){
                return "O resultadoo nao pode ser negativo";
            }

            //Corrigir
            //if(this.validarDataAnterior(partida.getData(), partida.getTime1(), partida.getTime2())){
                //return "Data invalida";
            //}

            //if(!this.validarDataPosterior(partida)){
                //return "Nao e possivel cadastrar nessa data";
            //}

            if(this.ValidarClubeInativo(partida)){
                return "Um dos times esta inativo";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        partidaRepository.save(partida);
        return partida.getTime1() + " X " + partida.getTime2() + " cadastrado com sucesso!";
    }

    public String atualizarPartida(Long id, Partida partida) {
       Partida atualizarPartida = this.partidaRepository.findById(id).orElse(null);
       List<Clube> clubeList = this.clubeRepository.findAll();
       List<Partida> partidaList = this.partidaRepository.findAll();

       try{
           for (Partida partidas : partidaList) {

           }

           if(partida.getTime1().equals(partida.getTime2())){
               return "Não é possivel cadastrar o mesmo clube 2 vezes";
           }else{
               for(Clube clube : clubeList){
                   if(partida.getTime1().toUpperCase().equals(clube.getClube().toUpperCase())){
                       this.primeiroClubeEncontrado =  true;
                   }
                   if(partida.getTime2().toUpperCase().equals(clube.getClube().toUpperCase())){
                       this.segundoClubeUmEncontrado =  true;
                   }
                   if(partida.getEstadio().toUpperCase().equals(clube.getEstado().toUpperCase())){
                       this.estadioEncontrado =  true;
                   }
               }
           }

        if (!this.primeiroClubeEncontrado || !this.segundoClubeUmEncontrado){
            return "Não encontrado!";
        }
//        if(!this.estadioEncontrado){
//            return "Estádio não encontrado!";
//        }
       }catch (Exception e){

       }

       atualizarPartida.setData(partida.getData());
       atualizarPartida.setTime1(partida.getTime1());
       atualizarPartida.setTime2(partida.getTime2());
       atualizarPartida.setEstadio(partida.getEstadio());
       atualizarPartida.setResultado1(partida.getResultado1());
       atualizarPartida.setResultado2(partida.getResultado2());
       this.partidaRepository.save(atualizarPartida);
       return  "Atualizado com sucesso!";
    }

    /*
     * Validacoes ------------------------------------------------------------------------
     * */

    public Boolean validarMesmoClubeNaPartida(Partida partida) {
        return partida.getTime1().equals(partida.getTime2())?false:true;
    }

    public Boolean validarClubExistente(String time1, String time2) {
        if(clubeRepository.existsByClube(time1.toUpperCase()) && clubeRepository.existsByClube(time2.toUpperCase())){
            return true;
        }
        return false;
    }

    public Boolean validarDataPosterior(Partida partida) {
        List<Partida> partidaList = this.partidaRepository.findAll();
        for(Partida p : partidaList){
            long dias = ChronoUnit.DAYS.between((Temporal) p.getData(), (Temporal) partida.getData());
            if(dias < 2 && partida.getEstadio().equals(p.getEstadio())){
                return true;
            }
        }
        return  false;
    }

    public Boolean validarDataAnterior(LocalDate data, String time1, String time2) {
        List<Clube> clubeList = this.clubeRepository.findAll();
        for(Clube clube : clubeList){
            if(clube.getClube().toUpperCase().equals(time1.toUpperCase()) || clube.getClube().toUpperCase().equals(time2.toUpperCase())){
                if(clube.getDatacriacao().isBefore(data)){
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean ValidarClubeInativo(Partida partida) {
        List<Clube> clubeList = this.clubeRepository.findAll();
        for(Clube clube : clubeList){
            if(clube.getClube().equals(partida.getTime1()) && clube.getAtivo() == false || clube.getClube().equals(partida.getTime2()) && clube.getAtivo() == false ){
                return true;
            }
        }
        return  false;
    }



    public Boolean validarNomeEstadio(String nome) {
        return this.estadioRepository.existsByNome(nome);
    }
}




