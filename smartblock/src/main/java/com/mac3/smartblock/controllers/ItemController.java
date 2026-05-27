package com.mac3.smartblock.controllers;

import com.mac3.smartblock.dtos.ItemRecordDto;
import com.mac3.smartblock.models.ItemModel;
import com.mac3.smartblock.repositories.ItemRepository;
import com.mac3.smartblock.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/smartblock")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/itens/")
    public ResponseEntity<ItemModel> saveItem( @RequestBody ItemRecordDto itemRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(itemRecordDto));
    }

    @GetMapping("/itens/")
    public ResponseEntity<List<ItemModel>> getAllItems() {
        return itemService.getAllItem();
    }

    @GetMapping("/itens/{id}")
    public ResponseEntity<Object> getItemById(@RequestBody @PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemById(id));
    }

    @DeleteMapping("/itens/{id}")
    public ResponseEntity<Object> deleteItemById(@RequestBody @PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.deleteItem(id));
    }

    @PutMapping("/itens/{id}")
    public ResponseEntity<Object> putItem(@RequestBody ItemRecordDto itemRecordDto, @PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.putItem(itemRecordDto, id));
    }

}
