package com.yanvelasco.agendadortarefas.domain.controller;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import com.yanvelasco.agendadortarefas.domain.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ){
        return tarefaService.save(tarefaDTO, token);
    }

}