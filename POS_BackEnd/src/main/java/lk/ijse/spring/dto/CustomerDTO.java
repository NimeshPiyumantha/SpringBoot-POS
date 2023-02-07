package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private double salary;
}
