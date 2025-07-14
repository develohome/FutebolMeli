package com.futebolmeli.demomelifutebol.exception;

import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

public class PartidaException {

    public PartidaException() {}

    private PartidaService partidaService;

    public String validarDataAnterior(LocalDate data) {
        if(data.isBefore(LocalDate.now())){
            return "Data invalido, data anterior a de hoje";
        }
        return  null;
    }

    public String validarDataPArtida(LocalDate partida, List<Partida> partidaList) {

        for(Partida p : partidaList){
            long dias = ChronoUnit.DAYS.between(p.getData(), partida);
            if(dias <= 2){
                return "Data deve ser maior que 2 dias";
            }
        }
        return  null;
    }

}
