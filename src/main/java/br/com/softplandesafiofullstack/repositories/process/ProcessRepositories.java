package br.com.softplandesafiofullstack.repositories.process;

import br.com.softplandesafiofullstack.models.process.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcessRepositories extends JpaRepository<ProcessEntity, UUID> {
    Optional<ProcessEntity> findByTitle(String title);
}

