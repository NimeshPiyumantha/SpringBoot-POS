package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface ItemRepo extends JpaRepository<Item, String> {
    @Query(value = "SELECT code FROM Item ORDER BY code DESC LIMIT 1", nativeQuery = true)
    String getLastIndex();

    @Query(value = "SELECT COUNT(code) FROM Item", nativeQuery = true)
    int getSumItem();

}
