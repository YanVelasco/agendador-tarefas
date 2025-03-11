package com.yanvelasco.agendadortarefas.domain.service;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TarefaService {
    ResponseEntity<TarefaDTO> save(TarefaDTO tarefaDTOm, String token);
    ResponseEntity<List<TarefaDTO>> findByDataEventoBetween(LocalDateTime dataEventoInicial,
                                                            LocalDateTime dataEventoFinal);
    ResponseEntity<List<TarefaDTO>> findByEmailDoUsuario(String emailDoUsuario);
}
