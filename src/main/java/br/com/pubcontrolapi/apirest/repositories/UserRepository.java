package br.com.pubcontrolapi.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.pubcontrolapi.apirest.models.user.User;


public interface UserRepository extends JpaRepository<User, String> {
    
    UserDetails findByLogin(String login);
}
