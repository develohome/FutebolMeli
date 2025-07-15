package com.futebolmeli.demomelifutebol.controller;

import com.futebolmeli.demomelifutebol.entity.Clube;
import com.futebolmeli.demomelifutebol.repository.ClubeRepository;
import com.futebolmeli.demomelifutebol.service.ClubeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clube")
@CrossOrigin(origins = "*")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @Autowired
    ClubeRepository clubeRepository;


    @GetMapping("/")
    public List<Clube> listarClubes() {
        return clubeService.listarClubes();
    }

    @GetMapping("")
    public Page<Clube> listarClubesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "clube") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clubeRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public List<Clube> buscarClubePorId(@PathVariable Long id) {
        return clubeService.buscarClubePorId(id);
        //return id ;
    }
    @PostMapping("/")
    public ResponseEntity<String> criarClubes(@RequestBody Clube clube) {
        String mensagem = clubeService.cadastrar(clube);
        if(mensagem.contains("sucesso")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarClub(@PathVariable Long id, @RequestBody Clube clube) {
        String mensagem = clubeService.atualizar(id, clube);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarClub(@PathVariable Long id) {
        String mensagem =  clubeService.deletarClub(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensagem) ;
    }
}
