package com.mac3.smartblock.controllers;

import com.mac3.smartblock.dtos.ClienteRecordDto;
import com.mac3.smartblock.models.ClienteModel;
import com.mac3.smartblock.repositories.ClienteRepository;
import com.mac3.smartblock.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    private ClienteRepository clienteRepository;



    @PostMapping("/smartblock/clientes/")
  public ResponseEntity<ClienteModel> saveCliente(@RequestBody ClienteRecordDto clienteRecordDto) {

      return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(clienteRecordDto));
  }

  @PutMapping("/smartblock/clientes/{id}")
  public ResponseEntity<Object> updateCliente(@PathVariable(value = "id")UUID id, @RequestBody ClienteRecordDto clienteRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(id,clienteRecordDto));
  }


  @GetMapping("/smartblock/clientes/{id}")
  public ResponseEntity<Object> getClienteById(@PathVariable(value = "id")UUID id) {
       return ResponseEntity.status(HttpStatus.OK).body(clienteService.getOneCliente(id));
     }


  @GetMapping("/smartblock/clientes/")
  public ResponseEntity<List<ClienteModel>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAllClientes().getBody());
  }




}
