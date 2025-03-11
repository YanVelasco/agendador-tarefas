package com.yanvelasco.agendadortarefas.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanvelasco.agendadortarefas.domain.enums.StatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTO {

    private String id;

    private String nomeDaTarefa;

    private String descricao;

    private LocalDateTime dataCriacao;

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataEvento;

    private String emailDoUsuario;

    private LocalDateTime dataUltimaAtualizacao;

    private StatusEnum status;

}