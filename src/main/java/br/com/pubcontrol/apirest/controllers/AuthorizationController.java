package br.com.pubcontrol.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pubcontrol.apirest.infra.security.TokenService;
import br.com.pubcontrol.apirest.models.user.AuthenticationDTO;
import br.com.pubcontrol.apirest.models.user.LoginResponseDTO;
import br.com.pubcontrol.apirest.models.user.RegisterDTO;
import br.com.pubcontrol.apirest.models.user.User;
import br.com.pubcontrol.apirest.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generatedToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO data){
        if (repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String ePassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), ePassword, data.name(), data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
    
}