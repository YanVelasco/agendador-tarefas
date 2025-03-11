package com.yanvelasco.agendadortarefas.domain.repository;

import com.yanvelasco.agendadortarefas.domain.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
    List<TarefaEntity> findByDataEventoBetween(LocalDateTime dataEventoInicial, LocalDateTime dataEventoFinal);
    List<TarefaEntity> findByEmailDoUsuario(String emailDoUsuario);
}