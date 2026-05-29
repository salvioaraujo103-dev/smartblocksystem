package com.mac3.smartblock.controllers;

import com.mac3.smartblock.dtos.ObraRecordDto;
import com.mac3.smartblock.models.EtapaModel;
import com.mac3.smartblock.models.ObraModel;
import com.mac3.smartblock.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/smartblock")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @PostMapping("/obras/")
    public ResponseEntity<Object> saveObra(@RequestBody ObraRecordDto obraRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(obraService.saveObra(obraRecordDto));
    }

    @GetMapping("/obras/")
    public ResponseEntity<List<ObraModel>> getAllObras() {
       return obraService.findAllObra();
    }

    @GetMapping("/obras/{id}")
    public ResponseEntity<Object> getObraById(@RequestBody @PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(obraService.findObraById(id));
    }

    @PutMapping("/obras/{id}")
    public ResponseEntity<Object> putObra(@RequestBody ObraRecordDto obraRecordDto, @PathVariable UUID id) {
        return obraService.putObra(id, obraRecordDto);
    }

    @DeleteMapping("/obras/{id}")
    public ResponseEntity<Object> deleteObraById(@RequestBody @PathVariable (value = "id") UUID id) {
        return obraService.deleteObra(id);
    }


}
