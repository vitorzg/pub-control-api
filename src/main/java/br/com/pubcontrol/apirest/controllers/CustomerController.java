package br.com.pubcontrol.apirest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pubcontrol.apirest.models.Customers;
import br.com.pubcontrol.apirest.services.CustomersService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {
    
    @Autowired
    private CustomersService customersService;

    @GetMapping("/details/{id}")
    public ResponseEntity<Customers> findOneCustomer(@PathVariable @Valid String id) {
        Customers obj = this.customersService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping(value="/new")
    public ResponseEntity<Void> createNewCustomer(@RequestBody @Valid Customers obj) {
        this.customersService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    
    @GetMapping(value="/all")
    public ResponseEntity<List<Customers>> findAllCustomers() {
        List<Customers> obj = this.customersService.findAll();
        return ResponseEntity.ok(obj); 
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> updateCustomer(@Validated @PathVariable String id, @RequestBody Customers customer) {
        customer.setId(id);
        this.customersService.update(customer);
        return ResponseEntity.accepted().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable @Validated String id){
        this.customersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
