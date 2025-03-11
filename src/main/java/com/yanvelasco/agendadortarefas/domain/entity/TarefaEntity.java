package com.yanvelasco.agendadortarefas.domain.entity;

import com.yanvelasco.agendadortarefas.domain.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("tarefas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaEntity {

    @Id
    private String id;

    private String nomeDaTarefa;

    private String descricao;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataEvento;

    private String emailDoUsuario;

    private LocalDateTime dataUltimaAtualizacao;

    private StatusEnum status;

}