package com.yanvelasco.agendadortarefas.domain.controller;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import com.yanvelasco.agendadortarefas.domain.enums.StatusEnum;
import com.yanvelasco.agendadortarefas.domain.service.TarefaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> save(
            @RequestBody TarefaDTO tarefaDTO,
            @RequestHeader("Authorization") String token
    ) {
        return tarefaService.save(tarefaDTO, token);
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> findByDataEventoBetween(
            @RequestParam("dataEventoInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoInicial,
            @RequestParam("dataEventoFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoFinal
    ) {
        return tarefaService.findByDataEventoBetween(dataEventoInicial, dataEventoFinal);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> findByEmailDoUsuario(@RequestHeader("Authorization") String token){
        return tarefaService.findByEmailDoUsuario(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaTarefaPorId(@PathVariable String id){
        return tarefaService.deletaTarefaPorId(id);
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatus(@RequestParam("status") String status, @RequestParam("id") String id) {
        return tarefaService.alterarStatus(StatusEnum.valueOf(status), id);
    }

    @PutMapping()
    public ResponseEntity<TarefaDTO> updateDeTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestParam("id") String id) {
        return tarefaService.updateDeTarefa(tarefaDTO, id);
    }
}