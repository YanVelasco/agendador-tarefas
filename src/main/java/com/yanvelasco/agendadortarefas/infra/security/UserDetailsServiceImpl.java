package com.yanvelasco.agendadortarefas.infra.security;

import com.yanvelasco.agendadortarefas.domain.dto.UsuarioDTO;
import com.yanvelasco.agendadortarefas.infra.client.UsuarioClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class UserDetailsServiceImpl {

    private final UsuarioClient usuarioClient;

    public UserDetailsServiceImpl(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public UserDetails carregaDadosDeUsuario(String email, String token) {
        ResponseEntity<UsuarioDTO> responseEntity = usuarioClient.buscaUsuarioPorEmail(token, email);
        UsuarioDTO usuarioDTO = responseEntity.getBody();
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}