package br.com.softplandesafiofullstack.dtos.user;

import br.com.softplandesafiofullstack.models.user.UserRoles;

public record RegisterDTO (String email , String password, UserRoles role) {
}
