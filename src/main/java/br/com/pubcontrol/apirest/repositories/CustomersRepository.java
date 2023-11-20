package br.com.pubcontrol.apirest.repositories;

import org.springframework.stereotype.Repository;

import br.com.pubcontrol.apirest.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, String>{
    
}
