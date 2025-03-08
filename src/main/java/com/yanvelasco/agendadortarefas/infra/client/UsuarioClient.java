package com.yanvelasco.agendadortarefas.infra.client;

import com.yanvelasco.agendadortarefas.domain.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario-service", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(
            @RequestHeader("Authorization") String token,
            @RequestParam("email") String email);

}
