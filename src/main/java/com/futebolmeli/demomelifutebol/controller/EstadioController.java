package com.futebolmeli.demomelifutebol.controller;

import com.futebolmeli.demomelifutebol.entity.Estadio;
import com.futebolmeli.demomelifutebol.service.EstadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadio")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;

    @GetMapping("/")
    public List<Estadio> listarEstadios() {
        return estadioService.listarEstadios();
    }

    @GetMapping("/{id}")
    public Estadio buscarEstadioPorId(@PathVariable Long id) {
        return estadioService.buscarEstadioPorId(id);
    }
    @PostMapping
    public ResponseEntity<String> listarEstadios(@RequestBody Estadio estadio) {
        String mensagem = estadioService.cadastrarEstadio(estadio);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
}
