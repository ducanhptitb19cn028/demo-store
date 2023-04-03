package group.g22.demostore.model;

//import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String ProductName;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name= "sell_number")
    private Integer sellNumber = 0;

    @Column(name= "total_revenue")
    private Double totalRevenue = 0.0;
}
