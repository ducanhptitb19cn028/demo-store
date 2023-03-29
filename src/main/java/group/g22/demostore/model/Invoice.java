package group.g22.demostore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long id;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "invoice_products", joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "type_product_id"))
    private List<TypeProduct> products;

    @ElementCollection
    @CollectionTable(name = "invoice_product_quantities", joinColumns = @JoinColumn(name = "invoice_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<TypeProduct, Integer> productQuantities;

    @Column(name = "total_Amount")
    private double totalAmount;
}
