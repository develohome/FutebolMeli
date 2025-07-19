package com.futebolmeli.demomelifutebol.controller;

import com.futebolmeli.demomelifutebol.entity.Estadio;
import com.futebolmeli.demomelifutebol.entity.Partida;
import com.futebolmeli.demomelifutebol.repository.PartidaRepository;
import com.futebolmeli.demomelifutebol.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private PartidaRepository partidaRepository;

    @GetMapping("")
    public Page<Partida> listarClubesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "estadio") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.partidaRepository.findAll(pageable);
    }
    @GetMapping("/")
    public List<Partida> buscarPartidas() {
        return partidaService.buscarPartidas();
    }

    @GetMapping("/{id}")
    public List<Partida> buscarPartidasPorId(@PathVariable Long id) {
        return Collections.singletonList(partidaService.buscarPartidasPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> criarPartida(@RequestBody Partida partida) {
        String mensagem = partidaService.criarPartida(partida);
        return  ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPartida(@PathVariable Long id, @RequestBody Partida partida) {
        String mensagem = partidaService.atualizarPartida(id, partida);
        //var codStatus = HttpStatus.CREATED;
        return  ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPartida(@PathVariable Long id) {
        String mensagem = partidaService.deletarPartida(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensagem);
    }
}
