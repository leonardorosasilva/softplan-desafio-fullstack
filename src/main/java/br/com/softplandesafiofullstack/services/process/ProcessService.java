package br.com.softplandesafiofullstack.services.process;

import br.com.softplandesafiofullstack.models.process.ProcessEntity;
import br.com.softplandesafiofullstack.repositories.process.ProcessRepositories;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProcessService {

    @Autowired
    ProcessRepositories processRepositories;

    public ResponseEntity<Object> createProcess(ProcessEntity process) {

        try{
            if(processRepositories.findByTitle(process.getTitle()).isPresent()){
                System.out.println("Conflito de titulos");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            System.out.println("Processo " + process.getTitle());
            this.processRepositories.save(process);
            return ResponseEntity.status(HttpStatus.CREATED).body(process);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
