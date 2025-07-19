package com.futebolmeli.demomelifutebol.controller;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.entity.Estadio;
import com.futebolmeli.demomelifutebol.repository.EstadioRepository;
import com.futebolmeli.demomelifutebol.service.EstadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadio")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;

    @Autowired
    private EstadioRepository estadioRepository;

    @GetMapping("")
    public Page<Estadio> listarClubesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "nome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.estadioRepository.findAll(pageable);
    }
    @GetMapping("/")
    public List<Estadio> listarEstadios() {
        return estadioService.listarEstadios();
    }

    @GetMapping("/{id}")
    public Estadio buscarEstadioPorId(@PathVariable Long id) {
        return estadioService.buscarEstadioPorId(id);
    }

    @PostMapping("/")
    public ResponseEntity<String> criarEstadios(@RequestBody Estadio estadio) {
        String mensagem = estadioService.cadastrarEstadio(estadio);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarEstadio(@PathVariable Long id,@RequestBody Estadio estadio) {
        String mensagem = estadioService.atualizarEstadio(id, estadio);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
}
