package br.com.softplandesafiofullstack.repositories.user;

import br.com.softplandesafiofullstack.models.user.UserEntity;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositories extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(@Email String email);
}