package br.com.pubcontrol.apirest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pubcontrol.apirest.models.Product;
import br.com.pubcontrol.apirest.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> findOneProduct(@PathVariable @Valid String id) {
        Product obj = this.productService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createNewProduct(@RequestBody @Valid Product obj) {
        this.productService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> obj = this.productService.findAll();
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@Validated @PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        this.productService.update(product);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Validated String id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/increment")
    public ResponseEntity<Product> incrementProductQuantity(@PathVariable @Validated String id, @RequestParam int quantityToAdd) {
        Product product = productService.incrementQuantity(id, quantityToAdd);
        return ResponseEntity.ok(product);
    }
}
