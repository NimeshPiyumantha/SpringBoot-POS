package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomDTO;
import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
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
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseUtil saveItem(@ModelAttribute ItemDTO dto) {
        service.saveItem(dto);
        return new ResponseUtil("OK", "Successfully Registered.!", null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO dto) {
        service.updateItem(dto);
        return new ResponseUtil("OK", "Successfully Updated. :" + dto.getCode(), null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping
    public ResponseUtil deleteItem(@RequestBody ItemDTO dto) {
        service.deleteItem(dto);
        return new ResponseUtil("OK", "Successfully Deleted. :" + dto.getCode(), null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/searchItemCode", params = {"code"})
    public ItemDTO searchItemCode(String code) {
        return service.searchItemCode(code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/loadAllItem")
    public ResponseUtil loadAllItem() {
        return new ResponseUtil("OK", "Successfully Loaded. :", service.loadAllItem());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/ItemIdGenerate")
    public @ResponseBody CustomDTO ItemIdGenerate() {
        return service.itemIdGenerate();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/itemCount")
    public @ResponseBody CustomDTO getSumItem() {
        return service.getSumItem();
    }
}