package br.com.pubcontrol.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pubcontrol.apirest.models.Customers;
import br.com.pubcontrol.apirest.repositories.CustomersRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomersService {
    
    @Autowired
    private CustomersRepository customersRepository;

    public Customers create(Customers customer){
        customer.setId(null);
        customer.setStatus("A");
        customer = this.customersRepository.save(customer);
        return customer;
    }

    public Customers findById(String id) {
        Optional<Customers> customer = this.customersRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("customer Not Found"));
    }

    public List<Customers> findAll() {
        List<Customers> Users = this.customersRepository.findAll();
        return Users;
    }

    @Transactional
    public Customers update(Customers customer){
        Customers newObj = findById(customer.getId());
        newObj.setName(customer.getName());
        newObj.setCpf(customer.getCpf());
        newObj.setTel(customer.getTel());
        newObj.setStatus(customer.getStatus());
        return this.customersRepository.save(newObj);
    }

    @Transactional
    public void delete(String id){
        Customers newObj = findById(id);
        try {
            newObj.setStatus("I");
            this.customersRepository.save(newObj);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete, check your database rules.");
        }
    }
}
