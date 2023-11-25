package br.com.pubcontrol.apirest.services;

import br.com.pubcontrol.apirest.models.Sales;
import br.com.pubcontrol.apirest.models.SalesProducts;
import br.com.pubcontrol.apirest.repositories.SalesRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SalesRepository salesRepository;

    public void create(Sales sale) {
        if (sale.getSalesProducts() != null && !sale.getSalesProducts().isEmpty()) {
            for (SalesProducts salesProduct : sale.getSalesProducts()) {
                salesProduct.setSales(sale);
            }
        }
        sale.setStatus("P");
        salesRepository.save(sale);
    }

    public Sales findById(String string) {
        Optional<Sales> sale = this.salesRepository.findById(string);
        return sale.orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    public List<Sales> findAll() {
        return this.salesRepository.findAll();
    }

    public void delete(String id) {
        Sales existingSale = findById(id);
        try {
            existingSale.setStatus("D");
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete, check your database rules.");
        }
    }

    public List<Sales> findByCustomer(String customerId) {
        return this.salesRepository.findByCustomer(customerId);
    }

    public List<Sales> findBySeller(String sellerId) {
        return this.salesRepository.findBySeller(sellerId);
    }
}
