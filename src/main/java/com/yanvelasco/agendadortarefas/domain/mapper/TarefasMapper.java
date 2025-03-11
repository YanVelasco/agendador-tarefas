package com.yanvelasco.agendadortarefas.domain.mapper;

import com.yanvelasco.agendadortarefas.domain.dto.TarefaDTO;
import com.yanvelasco.agendadortarefas.domain.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TarefasMapper {

    TarefaEntity toEntity(TarefaDTO tarefaDTO);

    TarefaDTO toDTO(TarefaEntity tarefaEntity);

    List<TarefaDTO> toListDTO(List<TarefaEntity> tarefaEntities);
}