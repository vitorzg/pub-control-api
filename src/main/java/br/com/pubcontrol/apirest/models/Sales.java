package br.com.pubcontrol.apirest.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.com.pubcontrol.apirest.models.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "sales")
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Sales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_sale")
    private String id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL)
    private List<SalesProducts> salesProducts = new ArrayList<>();

    @Column(name = "amount_sale")
    private double amount;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "status_sale")
    @Size(max = 1)
    private String status = "P";
}
