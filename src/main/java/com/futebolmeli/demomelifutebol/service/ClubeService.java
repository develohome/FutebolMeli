package com.futebolmeli.demomelifutebol.service;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Estados;
import com.futebolmeli.demomelifutebol.exception.ClubeException;
import com.futebolmeli.demomelifutebol.repository.ClubeRepository;
import com.futebolmeli.demomelifutebol.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    private Map<Long, Clube> clube = new HashMap<>();

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

            if(clubeRepository.existsByClube(clube.getClube())) {
                return "Clube ja existente";
            }

            clubeRepository.save(clube);
            return "Clube: " + clube.getClube() + ", cadastrado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String atualizar(Long id, Clube clube) {
        clube.setId(id);
        this.clube.put(id, clube);
        return "Atualizado com sucesso!";
    }

    public String deletarClub(Long id) {
        Clube clubeEncontrado = clubeRepository.findById(id).orElse(null);
        clubeEncontrado.setAtivo(false);
        clubeEncontrado = clubeRepository.save(clubeEncontrado);
        return clubeEncontrado.getClube() + ", deletado com sucesso!";
    }
}
