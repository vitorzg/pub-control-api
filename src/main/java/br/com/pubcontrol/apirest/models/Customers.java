package br.com.pubcontrol.apirest.models;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "customers")
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Customers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_customer")
    private String id;

    @Column(name = "name_custumer")
    @NotBlank(message = "name field cannot be blank")
    @Size(min = 3)
    private String name;

    @Column(name = "cpf_custumer")
    @NotBlank(message = "cpf field cannot be blank")
    private String cpf;

    @Column(name = "tel_customer")
    @NotBlank(message = "tel field cannot be blank")
    private String tel;

    @Column(name = "sts_customer")
    @Size(max = 1)
    private String status;
}
