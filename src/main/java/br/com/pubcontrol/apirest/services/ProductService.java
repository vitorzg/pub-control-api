package br.com.pubcontrol.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pubcontrol.apirest.models.Product;
import br.com.pubcontrol.apirest.repositories.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        product.setId(null);
        product.setStatus("A");
        product = this.productRepository.save(product);
        return product;
    }

    public Product findById(String id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> findAll() {
        List<Product> product = this.productRepository.findAll();
        return product;
    }

    @Transactional
    public Product update(Product product) {
        Product newObj = findById(product.getId());
        newObj.setName(product.getName());
        newObj.setQuantity(product.getQuantity());
        newObj.setValue(product.getValue());
        newObj.setStatus(product.getStatus());
        return this.productRepository.save(newObj);
    }

    @Transactional
    public void delete(String id) {
        Product newObj = findById(id);
        try {
            newObj.setStatus("I");
            this.productRepository.save(newObj);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete, check your database rules.");
        }
    }

    @Transactional
    public Product incrementQuantity(String productId, int quantityToAdd) {
        Product newObj = findById(productId);
        int currentQuantity = newObj.getQuantity();
        newObj.setQuantity(currentQuantity + quantityToAdd);
        return this.productRepository.save(newObj);
    }
}
