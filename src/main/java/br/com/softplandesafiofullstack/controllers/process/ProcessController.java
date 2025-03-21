package br.com.softplandesafiofullstack.controllers.process;


import br.com.softplandesafiofullstack.models.process.ProcessEntity;
import br.com.softplandesafiofullstack.repositories.process.ProcessRepositories;
import br.com.softplandesafiofullstack.repositories.user.UserRepositories;
import br.com.softplandesafiofullstack.services.process.ProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ProcessEntity processEntity) {
        ResponseEntity<Object> response = this.processService.createProcess(processEntity);
        return response;
    }
}

