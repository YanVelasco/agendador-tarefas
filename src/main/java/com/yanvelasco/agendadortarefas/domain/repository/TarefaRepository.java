package com.yanvelasco.agendadortarefas.domain.repository;

import com.yanvelasco.agendadortarefas.domain.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
}