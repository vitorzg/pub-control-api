package br.com.pubcontrol.apirest.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "products")
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_product")
    private String id;

    @Column(name = "name_product")
    @NotBlank(message = "name field cannot be blank")
    @Size(min = 3)
    private String name;

    @Column(name = "quantity_product")
    @Positive(message = "quantity must be positive")
    private int quantity;

    @Column(name = "value_unit_product")
    @Positive(message = "value must be positive")
    private double value;

    @Column(name = "sts_product")
    @Size(max = 1)
    private String status;

    @ManyToMany
    @JoinTable(
        name = "sales_products",
        joinColumns = @JoinColumn(name = "products_id"),
        inverseJoinColumns = @JoinColumn(name = "sales_id"))
    private List<Sales> sales;

}
