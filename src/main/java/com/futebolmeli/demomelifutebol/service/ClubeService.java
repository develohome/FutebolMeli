package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Estados;
import com.futebolmeli.demomelifutebol.exception.ClubeException;
import com.futebolmeli.demomelifutebol.repository.ClubeRepository;
import com.futebolmeli.demomelifutebol.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private EstadosRepository estadosRepository;


    public List<Clube> listarClubes() {
        return clubeRepository.findAll();
    }

    public List<Clube> buscarClubePorId(Long id) {
        Optional<Clube> clube = clubeRepository.findById(id);
        return Collections.singletonList(clube.orElse(null));
    }

    public String cadastrar(Clube clube) {

        try {
            ClubeException tratamentoErro = new ClubeException(clube);

            if (tratamentoErro.validarNomeDoClube() != null) {
                return tratamentoErro.validarNomeDoClube();
            }

            if (tratamentoErro.validarDataDeCriacao() != null) {
                return tratamentoErro.validarDataDeCriacao();
            }

            if (!tratamentoErro.validarEstado(estadosRepository.findAll())) {
                return "Estado invalido";
            }

            clubeRepository.save(clube);
            return "Clube: " + clube.getClube() + ", cadastrado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
