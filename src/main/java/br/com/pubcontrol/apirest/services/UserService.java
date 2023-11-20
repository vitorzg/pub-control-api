package br.com.pubcontrol.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pubcontrol.apirest.models.user.User;
import br.com.pubcontrol.apirest.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User findById(String id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public List<User> findAll() {
        List<User> Users = this.userRepository.findAll();
        return Users;
    }

    @Transactional
    public User update(User obj){
        User newUser = findById(obj.getId());
        newUser.setLogin(obj.getLogin());
        newUser.setName(obj.getName());
        newUser.setPassword(obj.getPassword());
        newUser.setRole(obj.getRole());
        return this.userRepository.save(newUser);
    }

    public void delete(String id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete, check your database rules.");
        }
    }
}
