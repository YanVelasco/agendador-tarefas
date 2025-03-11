package com.yanvelasco.agendadortarefas.domain.service;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import com.yanvelasco.agendadortarefas.domain.enums.StatusEnum;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TarefaService {
    ResponseEntity<TarefaDTO> save(TarefaDTO tarefaDTOm, String token);
    ResponseEntity<List<TarefaDTO>> findByDataEventoBetween(LocalDateTime dataEventoInicial,
                                                            LocalDateTime dataEventoFinal);
    ResponseEntity<List<TarefaDTO>> findByEmailDoUsuario(String emailDoUsuario);
    ResponseEntity<Void> deletaTarefaPorId(String id);
    ResponseEntity<TarefaDTO> alterarStatus(StatusEnum status, String id);
    ResponseEntity<TarefaDTO> updateDeTarefa(TarefaDTO tarefaDTO, String id);
}
