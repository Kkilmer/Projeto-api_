package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
