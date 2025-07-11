package com.futebolmeli.demomelifutebol.controller;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.service.ClubeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clube")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @GetMapping("/")
    public List<Clube> listarClubes() {
        return clubeService.listarClubes();
    }

    @GetMapping("/{id}")
    public List<Clube> buscarClubePorId(@PathVariable Long id) {
        return clubeService.buscarClubePorId(id);
        //return id ;
    }
    @PostMapping("/")
    public ResponseEntity<String> criarClubes(@RequestBody Clube clube) {
        String mensagem = clubeService.cadastrar(clube);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
}
