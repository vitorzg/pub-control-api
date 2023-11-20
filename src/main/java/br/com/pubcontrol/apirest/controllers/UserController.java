package br.com.pubcontrol.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pubcontrol.apirest.models.user.User;
import br.com.pubcontrol.apirest.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/details/{id}")
    public ResponseEntity<UserDetails> findOneUsers(@Valid @PathVariable String id){
        UserDetails user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }
    
    // @PutMapping("/{id}")
    // public ResponseEntity<Void> userUpdate(@Valid @PathVariable String id, @RequestBody User user){
    //     user.setId(id);
    //     this.userService.update(user);
    //     return ResponseEntity.ok().build();
    // } 

    //  @DeleteMapping("/{id}")
    // public ResponseEntity<Void> delete(@PathVariable String id){
    //     this.userService.delete(id);
    //     return ResponseEntity.noContent().build();
    // }
}
