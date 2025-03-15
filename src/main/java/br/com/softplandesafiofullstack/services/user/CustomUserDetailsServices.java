package br.com.softplandesafiofullstack.services.user;

import br.com.softplandesafiofullstack.models.user.UserEntity;
import br.com.softplandesafiofullstack.repositories.user.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepositories UserRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.UserRepositories.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(userEntity.getLogin(), userEntity.getPassword(), userEntity.getAuthorities());
    }
}