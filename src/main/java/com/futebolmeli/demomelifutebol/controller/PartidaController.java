package com.futebolmeli.demomelifutebol.controller;

import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @GetMapping("/")
    public List<Partida> buscarPartidas() {
        return partidaService.buscarPartidas();
    }

    @GetMapping("/{id}")
    public List<Partida> buscarPartidasPorId(@PathVariable Long id) {
        return Collections.singletonList(partidaService.buscarPartidasPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> buscarPartida(@RequestBody Partida partida) {
        String mensagem = partidaService.criarPartida(partida);
        return  ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

}
