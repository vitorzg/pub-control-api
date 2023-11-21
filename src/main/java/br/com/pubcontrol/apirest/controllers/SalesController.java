package br.com.pubcontrol.apirest.controllers;

import br.com.pubcontrol.apirest.models.Sales;
import br.com.pubcontrol.apirest.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sales")
@Validated
public class SalesController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/details/{id}")
    public ResponseEntity<Sales> findOneSale(@PathVariable String id) {
        Sales sale = this.saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createNewSale(@RequestBody Sales sale) {
        this.saleService.create(sale);
        URI uri = URI.create("/sales/details/" + sale.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sales>> findAllSales() {
        List<Sales> sales = this.saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSale(@Validated @PathVariable String id, @RequestBody Sales sale) {
        sale.setId(id);
        this.saleService.update(sale);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable String id) {
        this.saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-customer/{id}")
    public ResponseEntity<List<Sales>> findByCustomer(@PathVariable String id) {
        List<Sales> sales = this.saleService.findByCustomer(id);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/by-seller/{id}")
    public ResponseEntity<List<Sales>> findBySeller(@PathVariable String id) {
        List<Sales> sales = this.saleService.findBySeller(id);
        return ResponseEntity.ok(sales);
    }
}
