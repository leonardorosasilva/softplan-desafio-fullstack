package br.com.softplandesafiofullstack.repositories.user;

import br.com.softplandesafiofullstack.models.user.UserEntity;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(@Email String email);
}