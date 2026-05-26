package com.mac3.smartblock.controllers;

import com.mac3.smartblock.dtos.EngenheiroRecordDto;
import com.mac3.smartblock.repositories.ClienteRepository;
import com.mac3.smartblock.repositories.EngenheiroRepository;
import com.mac3.smartblock.services.EngenheiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class EngenheiroController {

    @Autowired
    private EngenheiroService engenheiroService;

    @PostMapping("/smartblock/engenheiros/")
    public ResponseEntity<Object> saveEng(@RequestBody EngenheiroRecordDto engenheiroRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(engenheiroService.saveEng(engenheiroRecordDto));
    }


    @GetMapping("/smartblock/engenheiros/{id}")
    public ResponseEntity<Object> getEngById(@RequestBody  @PathVariable(value = "id") UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(engenheiroService.getEngById(id));
    }



    @GetMapping("/smartblock/engenheiros/")
    public ResponseEntity<Object> getAllEng() {
        return ResponseEntity.status(HttpStatus.OK).body(engenheiroService.getAllEng());
    }

    @PutMapping("/smartblock/engenheiros/{id}")
    public ResponseEntity<Object> updateEng(@RequestBody @PathVariable(value = "id")UUID id, EngenheiroRecordDto engenheiroRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(engenheiroService.updateEng(id,engenheiroRecordDto));
    }

    @DeleteMapping("/smartblock/engenheiros/{id}")
    public ResponseEntity<Object> deleteEng(@RequestBody @PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(engenheiroService.deleteEng(id));
    }

}
