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
       return this.estadioRepository.findAll();
    }
    public Estadio buscarEstadioPorId(Long id) {
        return this.estadioRepository.findById(id).orElse(null);
    }
    public String cadastrarEstadio(Estadio estadio) {
        this.validarEstadio(estadio.getNome());
        this.estadioRepository.save(estadio);
        return "Estadio cadastrado com sucesso!";
    }

    public String atualizarEstadio(Long id, Estadio estadio) {
        try{
            Estadio estadioParaAtualizar = this.estadioRepository.findById(id).orElse(null);
            this.validarEstadio(estadio.getNome());

            estadioParaAtualizar.setNome(estadio.getNome());
            estadioParaAtualizar.setId(estadio.getId());
            estadioParaAtualizar.setEstado(estadio.getEstado());
            estadioParaAtualizar.setFuncacao(estadio.getFundacao());
            //this.estadioRepository.save(estadioParaAtualizar);
            return "Estadio atualizado com sucesso!";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    /*
     * ################# Validacoes ##################
     * */

    public String validarEstadio(Estadio estadio) {
        if(this.nomeDoEstadio(estadio.getNome())){
            return "Nome invalido";
        }
        if(this.validarEstadio(estadio.getNome())){
            return "Ja existe um estadio com esse nome";
        }
        return null;
    }

    public Boolean nomeDoEstadio(String nome){
        return nome.length() < 3?true:false;
    }
    public Boolean validarEstadio(String nome){
        return this.estadioRepository.existsByNome(nome);
    }
}



