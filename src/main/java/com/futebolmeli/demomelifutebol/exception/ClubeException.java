package com.futebolmeli.demomelifutebol.exception;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Estados;
import com.futebolmeli.demomelifutebol.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ClubeException {

    private static final Logger logger = Logger.getLogger(ClubeException.class.getName());

    private Clube clube;

    public ClubeException(Clube clube) {
        this.clube = clube;
    }

    public String validarNomeDoClube(){
        if(clube.getClube().length() < 2){
            return "Nome '"+ clube.getClube() + "' esta invalido";

        }
        return null;
    }

    public String validarDataDeCriacao(){
        if(clube.getDatacriacao().after(new Date())){
            return "Data de criacao invalido";
        }
        return null;
    }

    public Boolean validarEstado(List<Estados> estados){
        for(Estados estado : estados){
            if(estado.getSigla().equals(clube.getEstado().toUpperCase())){
                return true;
            }
        }
        return false;
    }

}
