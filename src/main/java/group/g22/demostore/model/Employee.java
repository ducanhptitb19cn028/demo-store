package group.g22.demostore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")

    private Date dob;
    private String address;
    private String genderOption;
    private String peopleOption;
    @Column(name = "identity_no")
    private String identityno;
    private String images;
    public Employee() {

    }
}