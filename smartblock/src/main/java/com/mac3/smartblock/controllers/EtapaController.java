package com.mac3.smartblock.controllers;

import com.mac3.smartblock.dtos.EtapaRecordDto;
import com.mac3.smartblock.models.EtapaModel;
import com.mac3.smartblock.services.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/smartblock")
public class EtapaController {

    @Autowired
    private EtapaService etapaService;

    @PostMapping("/etapas/")
    public ResponseEntity<EtapaModel> saveEtapa(@RequestBody EtapaRecordDto etapaRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(etapaService.saveEtapa(etapaRecordDto));
    }

    @GetMapping("/etapas/")
    public ResponseEntity<List<EtapaModel>> findAllEtapas() {
        return etapaService.getAllEtapas();
    }




    @GetMapping("/etapas/{id}")
    public ResponseEntity<Object> findEtapaById(@RequestBody @PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(etapaService.getEtapaById(id));
    }

    @PutMapping("/etapas/{id}")
    public ResponseEntity<Object> putEtapa(@RequestBody EtapaRecordDto etapaRecordDto, @PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(etapaService.putEtapa(etapaRecordDto, id));
    }

}
