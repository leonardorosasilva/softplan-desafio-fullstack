package br.com.softplandesafiofullstack.models.process;

import br.com.softplandesafiofullstack.models.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity(name = "process")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class ProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
