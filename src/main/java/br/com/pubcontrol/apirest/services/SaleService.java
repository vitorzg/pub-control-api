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

    public void create(Sales sales) {
        if (sales.getSalesProducts() != null && !sales.getSalesProducts().isEmpty()) {
            for (SalesProducts salesProduct : sales.getSalesProducts()) {
                salesProduct.setSales(sales);
            }
        }
        salesRepository.save(sales);
    }

    public Sales findById(String string) {
        Optional<Sales> sale = this.salesRepository.findById(string);
        return sale.orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    public List<Sales> findAll() {
        return this.salesRepository.findAll();
    }

    public Sales update(Sales sale) {
        Sales existingSale = findById(sale.getId());
        existingSale.setSeller(sale.getSeller());
        existingSale.setCustomer(sale.getCustomer());
        existingSale.setSalesProducts(sale.getSalesProducts());
        existingSale.setSaleDate(sale.getSaleDate());

        return this.salesRepository.save(existingSale);
    }

    public void delete(String id) {
        Sales existingSale = findById(id);
        try {
            this.salesRepository.delete(existingSale);
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
