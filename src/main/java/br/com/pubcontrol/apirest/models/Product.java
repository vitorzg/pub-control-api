package br.com.pubcontrol.apirest.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "product")
    private Set<SalesProducts> salesProducts = new HashSet<>();

}
