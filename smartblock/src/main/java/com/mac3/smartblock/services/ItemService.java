package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.ItemRecordDto;
import com.mac3.smartblock.models.ItemModel;
import com.mac3.smartblock.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private  ItemRepository itemRepository;

    //cadastra item
    @Transactional
    public ItemModel saveItem(ItemRecordDto itemRecordDto) {
        ItemModel itemModel = new ItemModel();
        itemModel.setNome_item(itemRecordDto.nomeItem());
        itemModel.setDescricao(itemRecordDto.descricao());
        itemModel.setUnidadeMedida(itemRecordDto.unidadeMedida());

        return itemRepository.save(itemModel);
    }

    @Transactional
    public ResponseEntity<Object> getItemById(UUID id){
        Optional<ItemModel> itemModel = itemRepository.findById(id);
        if(itemModel.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(itemModel.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }

    @Transactional
    public ResponseEntity<List<ItemModel>> getAllItem(){
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Object> deleteItem(UUID id){
        Optional<ItemModel> itemModel = itemRepository.findById(id);
        if(itemModel.isPresent()){
            itemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Item deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }

    @Transactional
    public ResponseEntity<Object> putItem(ItemRecordDto itemRecordDto, UUID id) {
        Optional<ItemModel> itemModel = itemRepository.findById(id);
        if(itemModel.isPresent()){
            ItemModel itemModel1 = itemModel.get();
            BeanUtils.copyProperties(itemRecordDto, itemModel1);
            return ResponseEntity.status(HttpStatus.OK).body(itemRepository.save(itemModel1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }
}
