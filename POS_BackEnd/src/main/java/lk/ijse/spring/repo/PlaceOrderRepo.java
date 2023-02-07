package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface PlaceOrderRepo extends JpaRepository<Orders, String> {
    @Query(value = "SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1", nativeQuery = true)
    String getLastIndex();

    @Query(value = "SELECT COUNT(oid) FROM Orders", nativeQuery = true)
    int getSumOrders();
}
