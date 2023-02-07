package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseUtil saveCustomer(@ModelAttribute CustomerDTO dto) {
        service.saveCustomer(dto);
        return new ResponseUtil("OK", "Successfully Registered.!", null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO dto) {
        service.updateCustomer(dto);
        return new ResponseUtil("OK", "Successfully Updated. :" + dto.getId(), null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping
    public ResponseUtil deleteCustomer(@RequestBody CustomerDTO dto) {
        service.deleteCustomer(dto);
        return new ResponseUtil("OK", "Successfully Deleted. :" + dto.getId(), null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/searchCusId", params = {"id"})
    public CustomerDTO searchCusId(String id) {
        return service.searchCusId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/loadAllCustomer")
    public ResponseUtil loadAllCustomer() {
        return new ResponseUtil("OK", "Successfully Loaded. :", service.loadAllCustomer());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/CustomerIdGenerate")
    public @ResponseBody CustomDTO customerIdGenerate() {
        return service.customerIdGenerate();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/CustomerCount")
    public @ResponseBody CustomDTO getSumCustomer() {
        return service.getSumCustomer();
    }
}