package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomDTO;
import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveItem(ItemDTO dto) {
        if (repo.existsById(dto.getCode())) {
            throw new RuntimeException("Item Already Exist. Please enter another id..!");
        }
        repo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void updateItem(ItemDTO dto) {
        if (!repo.existsById(dto.getCode())) {
            throw new RuntimeException("Item Not Exist. Please enter Valid id..!");
        }
        repo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void deleteItem(ItemDTO dto) {
        if (!repo.existsById(dto.getCode())) {
            throw new RuntimeException("Wrong ID..Please enter valid id..!");
        }
        repo.delete(mapper.map(dto, Item.class));
    }

    @Override
    public ItemDTO searchItemCode(String code) {
        if (!repo.existsById(code)) {
            throw new RuntimeException("Wrong ID. Please enter Valid id..!");
        }
        Item item = repo.findById(code).get();
        return mapper.map(item, ItemDTO.class);
    }

    @Override
    public ArrayList<ItemDTO> loadAllItem() {
        return mapper.map(repo.findAll(), new TypeToken<ArrayList<Item>>() {
        }.getType());
    }

    @Override
    public CustomDTO itemIdGenerate() {
        return new CustomDTO(repo.getLastIndex());
    }

    @Override
    public CustomDTO getSumItem() {
        return new CustomDTO(repo.getSumItem());
    }
}
