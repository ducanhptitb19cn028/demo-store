package group.g22.demostore.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @ElementCollection
    @CollectionTable(name = "invoice_product_quantities", joinColumns = @JoinColumn(name = "invoice_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productQuantities;

    @Column(name = "total_Amount")
    private double totalAmount;
}
