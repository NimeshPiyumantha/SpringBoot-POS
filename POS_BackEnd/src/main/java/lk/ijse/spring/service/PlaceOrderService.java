package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomDTO;
import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public interface PlaceOrderService {
    void placeOrder(@RequestBody OrdersDTO dto);

    ArrayList<OrdersDTO> LoadOrders();

    ArrayList<OrderDetailsDTO> LoadOrderDetails();

    @ResponseBody
    CustomDTO OrderIdGenerate();

    @ResponseBody
    CustomDTO getSumOrders();
}
