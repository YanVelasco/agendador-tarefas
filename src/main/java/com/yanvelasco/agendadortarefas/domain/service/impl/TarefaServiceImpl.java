package com.yanvelasco.agendadortarefas.domain.service.impl;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import com.yanvelasco.agendadortarefas.domain.entity.TarefaEntity;
import com.yanvelasco.agendadortarefas.domain.enums.StatusEnum;
import com.yanvelasco.agendadortarefas.domain.mapper.TarefasMapper;
import com.yanvelasco.agendadortarefas.domain.repository.TarefaRepository;
import com.yanvelasco.agendadortarefas.domain.service.TarefaService;
import com.yanvelasco.agendadortarefas.infra.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefasMapper tarefaMapper;
    private final JwtUtil jwtUtil;

    public TarefaServiceImpl(TarefaRepository tarefaRepository, TarefasMapper tarefaMapper, JwtUtil jwtUtil) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
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
}