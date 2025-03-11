package com.yanvelasco.agendadortarefas.domain.service;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import org.springframework.http.ResponseEntity;

public interface TarefaService {
    ResponseEntity<TarefaDTO> save(TarefaDTO tarefaDTOm, String token);
}
