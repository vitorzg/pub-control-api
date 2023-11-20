package br.com.pubcontrol.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pubcontrol.apirest.models.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
    
}
