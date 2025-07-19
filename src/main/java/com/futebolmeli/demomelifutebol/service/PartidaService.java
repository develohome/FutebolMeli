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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        try {
            //PartidaException partidaException = new PartidaException();

            if (!validarMesmoClubeNaPartida(partida)) {
                return "Nao e possivel cadastrar o mesmo clube partida";
            }
            if (!this.validarClubExistente(partida.getTime1(), partida.getTime2())) {
                return "Um dos times nao esta cadastrado";
            }
            if (!this.validarNomeEstadio(partida.getEstadio())) {
                return "Estadio nao esta cadastrado";
            }
            if (partida.getResultado1() < 0 || partida.getResultado2() < 0) {
                return "O resultadoo nao pode ser negativo";
            }
            if (this.validarDataAnteriorCriacaoClube(partida.getData(), partida.getIdTime1(), partida.getIdTime2())) {
                return "Data invalida";
            }
            if (this.ValidarClubeInativo(partida)) {
                return "Um dos times esta inativo";
            }
            if (this.validarHorarioDoJogo(partida) != null) {
                return this.validarHorarioDoJogo(partida);
            }
            if (this.validardiaDojogo(partida) != null) {
                return this.validardiaDojogo(partida);
            }

            /*if(!this.validarDataPosterior(partida)){
                //return "Nao e possivel cadastrar nessa data";
            //}

            */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        partidaRepository.save(partida);
        return partida.getTime1() + " X " + partida.getTime2() + " cadastrado com sucesso!";
    }

    public String atualizarPartida(Long id, Partida partida) {
        try {
            Partida atualizarPartida = this.partidaRepository.findById(id).orElse(null);
            List<Clube> clubeList = this.clubeRepository.findAll();
            List<Partida> partidaList = this.partidaRepository.findAll();

            if (!validarMesmoClubeNaPartida(partida)) {
                return "Nao e possivel cadastrar o mesmo clube partida";
            }

            if (!this.validarClubExistente(partida.getTime1(), partida.getTime2())) {
                return "Um dos times nao esta cadastrado";
            }

            if (!this.validarNomeEstadio(partida.getEstadio())) {
                return "Estadio nao esta cadastrado";
            }

            if (partida.getResultado1() < 0 || partida.getResultado2() < 0) {
                return "O resultadoo nao pode ser negativo";
            }

            if (this.validarDataAnteriorCriacaoClube(partida.getData(), partida.getIdTime1(), partida.getIdTime2())) {
                return "Data invalida";
            }

            if (this.ValidarClubeInativo(partida)) {
                return "Um dos times esta inativo";
            }

            if (this.validarHorarioDoJogo(partida) != null) {
                return this.validarHorarioDoJogo(partida);
            }

            if (this.validardiaDojogo(partida) != null) {
                return this.validardiaDojogo(partida);
            }

            if(!this.validarPartidaExistente(id)){
                return "Nao existe partida cadastrada";
            }

            atualizarPartida.setData(partida.getData());
            atualizarPartida.setTime1(partida.getTime1());
            atualizarPartida.setTime2(partida.getTime2());
            atualizarPartida.setEstadio(partida.getEstadio());
            atualizarPartida.setIdEstadio(partida.getIdEstadio());
            atualizarPartida.setResultado1(partida.getResultado1());
            atualizarPartida.setResultado2(partida.getResultado2());

            this.partidaRepository.save(atualizarPartida);
            return "Atualizado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deletarPartida(Long id) {
        if(!this.validarPartidaExistente(id)){
            return "Nao existe partida cadastrada";
        }
        this.partidaRepository.deleteById(id);
        return "Partida deletada com sucesso!";
    }
    /*
     * ################# Validacoes #################
     * */


    public Boolean validarDataAnteriorCriacaoClube(LocalDate data, Long idTime1, Long idTime2) {
        List<Clube> clubeList = this.clubeRepository.findAll();
        for (Clube clube : clubeList) {
            if ((clube.getId().equals(idTime1) && data.isBefore(clube.getDatacriacao())) || (clube.getId().equals(idTime2) && data.isBefore(clube.getDatacriacao()))) {
                return true;
            }
        }
        return false;
    }

    public String validarCadastroNoPassado(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            return "Nao e possivel cadastrar o passado";
        }
        return null;
    }

    public Boolean validarMesmoClubeNaPartida(Partida partida) {
        return partida.getTime1().equals(partida.getTime2()) ? false : true;
    }

    public Boolean validarClubExistente(String time1, String time2) {
        if (clubeRepository.existsByClube(time1.toUpperCase()) && clubeRepository.existsByClube(time2.toUpperCase())) {
            return true;
        }
        return false;
    }

    public String validarHorarioDoJogo(Partida partida) {
        List<Partida> partidaList = this.partidaRepository.findAll();

        List<Partida> partidaEncontrada = partidaList.stream()
                .filter(partidaDoDia -> (partidaDoDia.getHora().equals(partida.getHora()))).toList();

        if (!partidaEncontrada.isEmpty()) {
            return "Ja existe uma partida para esse horario, proxima data disonivel em 48h";

        }
        return null;
    }

    public String validardiaDojogo(Partida partida) {
        List<Partida> partidaList = this.partidaRepository.findAll();

        List<Partida> partidaEncontrada = partidaList.stream()
                .filter(partidaDoDia -> (partidaDoDia.getData().equals(partida.getData()))).toList();

        if (validarCadastroNoPassado(partida.getData()) != null) {
            return "Nao e possivel cadastrar no passado";
        }
        if (!partidaEncontrada.isEmpty()) {
            return "Ja existe uma partida para esse dia";

        }
        return null;
    }

    public Boolean ValidarClubeInativo(Partida partida) {
        List<Clube> clubeList = this.clubeRepository.findAll();
        for (Clube clube : clubeList) {
            if (clube.getClube().equals(partida.getTime1()) && clube.getAtivo() == false || clube.getClube().equals(partida.getTime2()) && clube.getAtivo() == false) {
                return true;
            }
        }
        return false;
    }

    public Boolean validarNomeEstadio(String nome) {
        return this.estadioRepository.existsByNome(nome);
    }

    public Boolean validarPartidaExistente(Long idPartida) {
        return this.partidaRepository.existsById(idPartida);
    }

    /*public Boolean validarDataPosterior(Partida partida) {
        List<Partida> partidaList = this.partidaRepository.findAll();
        for(Partida p : partidaList){
            long dias = ChronoUnit.DAYS.between((Temporal) p.getData(), (Temporal) partida.getData());
            if(dias < 2 && partida.getEstadio().equals(p.getEstadio())){
                return true;
            }
        }
        return  false;
    }*/



}




