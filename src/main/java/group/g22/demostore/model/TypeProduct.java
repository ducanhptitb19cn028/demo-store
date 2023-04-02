package group.g22.demostore.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_product")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_product_id")
    private Long typeProductId;

    @Column(name = "type_product_code")
    private String typeProductCode;

    @Column(name = "type_product_name")
    private String typeProductName;

    @Column(name = "type_product_status")
    private Integer typeProductStatus;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "stop_date")
    private LocalDateTime stopDate;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name= "sell_number")
    private Integer sellNumber = 0;

    @Column(name= "total_revenue")
    private Double totalRevenue = 0.0;
}
