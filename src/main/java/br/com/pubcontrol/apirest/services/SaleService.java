package br.com.pubcontrol.apirest.services;

import br.com.pubcontrol.apirest.models.Sales;
import br.com.pubcontrol.apirest.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SalesRepository salesRepository;

    public Sales create(Sales sale) {
        sale = this.salesRepository.save(sale);
        return sale;
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
        existingSale.setProducts(sale.getProducts());
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
