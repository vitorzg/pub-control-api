package br.com.pubcontrol.apirest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pubcontrol.apirest.models.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, String>{
    
    @Query(value = "SELECT * FROM sales WHERE customer_id = :customerId", nativeQuery = true)
    List<Sales> findByCustomer(@Param("customerId") String customerId);

    @Query(value = "SELECT * FROM sales WHERE seller_id = :sellerId", nativeQuery = true)
    List<Sales> findBySeller(@Param("sellerId") String sellerId);
    
}
