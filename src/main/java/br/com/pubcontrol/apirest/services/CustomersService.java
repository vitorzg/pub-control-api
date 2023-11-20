package br.com.pubcontrol.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pubcontrol.apirest.models.Customers;
import br.com.pubcontrol.apirest.repositories.CustomersRepository;

@Service
public class CustomersService {
    
    @Autowired
    private CustomersRepository customersRepository;

    public Customers findById(String id) {
        Optional<Customers> customer = this.customersRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("customer Not Found"));
    }

    public List<Customers> findAll() {
        List<Customers> Users = this.customersRepository.findAll();
        return Users;
    }
}
