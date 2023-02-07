package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;
    private double salary;
}
