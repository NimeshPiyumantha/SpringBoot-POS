package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    private String oid;
    //Out=verse
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customerID", referencedColumnName = "id", nullable = false)
    private Customer cusID;
    private String date;

    //Inverse
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
