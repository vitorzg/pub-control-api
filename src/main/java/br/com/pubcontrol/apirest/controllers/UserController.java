package br.com.pubcontrol.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> userUpdate(@Valid @PathVariable String id, @RequestBody User user){
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.ok().build();
    } 

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
