package com.yanvelasco.agendadortarefas.domain.service.impl;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import com.yanvelasco.agendadortarefas.domain.entity.TarefaEntity;
import com.yanvelasco.agendadortarefas.domain.enums.StatusEnum;
import com.yanvelasco.agendadortarefas.domain.mapper.TarefaUpdateMapper;
import com.yanvelasco.agendadortarefas.domain.mapper.TarefasMapper;
import com.yanvelasco.agendadortarefas.domain.repository.TarefaRepository;
import com.yanvelasco.agendadortarefas.domain.service.TarefaService;
import com.yanvelasco.agendadortarefas.exceptions.ResourceNotFoundException;
import com.yanvelasco.agendadortarefas.infra.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefasMapper tarefaMapper;
    private final TarefaUpdateMapper tarefaUpdateMapper;
    private final JwtUtil jwtUtil;

    public TarefaServiceImpl(TarefaRepository tarefaRepository, TarefasMapper tarefaMapper,
                             TarefaUpdateMapper tarefaUpdateMapper, JwtUtil jwtUtil) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
        this.tarefaUpdateMapper = tarefaUpdateMapper;
        this.jwtUtil = jwtUtil;
    }


    public ResponseEntity<TarefaDTO> save(TarefaDTO tarefaDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatus(StatusEnum.PERNDENTE);
        tarefaDTO.setEmailDoUsuario(email);

        TarefaEntity tarefaEntity = tarefaMapper.toEntity(tarefaDTO);
        TarefaEntity savedEntity = tarefaRepository.save(tarefaEntity);
        TarefaDTO savedDTO = tarefaMapper.toDTO(savedEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }


    public ResponseEntity<List<TarefaDTO>> findByDataEventoBetween(LocalDateTime dataEventoInicial,
                                                                   LocalDateTime dataEventoFinal) {
        List<TarefaEntity> tarefaEntities = tarefaRepository.findByDataEventoBetween(dataEventoInicial,
                dataEventoFinal);
        List<TarefaDTO> tarefaDTOs = tarefaMapper.toListDTO(tarefaEntities);
        return ResponseEntity.ok(tarefaDTOs);
    }

    public ResponseEntity<List<TarefaDTO>> findByEmailDoUsuario(String emailDoUsuario) {
        String email = jwtUtil.extractUsername(emailDoUsuario.substring(7));
        List<TarefaEntity> tarefaEntities = tarefaRepository.findByEmailDoUsuario(email);
        List<TarefaDTO> tarefaDTOs = tarefaMapper.toListDTO(tarefaEntities);
        return ResponseEntity.ok(tarefaDTOs);
    }

    public ResponseEntity<Void> deletaTarefaPorId(String id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Tarefa", id, "id");
        }
    }

    public ResponseEntity<TarefaDTO> alterarStatus(StatusEnum status, String id) {
        try {
            TarefaEntity tarefa = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa", "id", id)
            );
            tarefa.setStatus(status);
            tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaMapper.toDTO(tarefa));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<TarefaDTO> updateDeTarefa(TarefaDTO tarefaDTO, String id) {
        TarefaEntity tarefa = tarefaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tarefa", "id", id)
        );
        tarefaUpdateMapper.upadateDeTarefas(tarefaDTO, tarefa);
        tarefaRepository.save(tarefa);
        return ResponseEntity.ok(tarefaMapper.toDTO(tarefa));
    }

}