package br.com.softplandesafiofullstack.controllers.user;

import br.com.softplandesafiofullstack.dtos.user.AuthenticatorDTO;
import br.com.softplandesafiofullstack.dtos.user.LoginResponseDTO;
import br.com.softplandesafiofullstack.dtos.user.RegisterDTO;
import br.com.softplandesafiofullstack.models.user.UserEntity;
import br.com.softplandesafiofullstack.repositories.user.UserRepositories;
import br.com.softplandesafiofullstack.services.user.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("login/")
    public ResponseEntity<Object> login(@RequestBody AuthenticatorDTO authenticatorDTO) {
        UserEntity userEntity = this.userRepositories.findByEmail(authenticatorDTO.email()).orElseThrow();
        if(!passwordEncoder.matches(authenticatorDTO.password(), userEntity.getPassword())) {
            return ResponseEntity.status(401).build();
        } else {
            var userNamePassword = new UsernamePasswordAuthenticationToken(authenticatorDTO.email(), authenticatorDTO.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            var token = this.tokenService.generateToken(userEntity);
            return ResponseEntity.ok(new LoginResponseDTO(token, userEntity.getUsername()));
        }
    }


    @PostMapping("register/")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.userRepositories.findByEmail(registerDTO.email()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            String encodedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
            UserEntity userEntity = new UserEntity(registerDTO.email(), encodedPassword, registerDTO.role());

            this.userRepositories.save(userEntity);
            return ResponseEntity.status(201).build();
        }
    }
}