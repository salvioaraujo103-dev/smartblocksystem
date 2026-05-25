package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.ClienteRecordDto;
import com.mac3.smartblock.models.ClienteModel;
import com.mac3.smartblock.models.ObraModel;
import com.mac3.smartblock.repositories.ClienteRepository;
import com.mac3.smartblock.repositories.ObraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ObraRepository obraRepository;

    public ClienteService(ClienteRepository clienteRepository, ObraRepository obraRepository) {
        this.clienteRepository = clienteRepository;
        this.obraRepository = obraRepository;
    }

    //cadasatro de cliente
    @Transactional
    public ClienteModel saveCliente(ClienteRecordDto clienteRecordDto) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome(clienteRecordDto.nome());
        clienteModel.setEmail(clienteRecordDto.email());
        clienteModel.setEndereco(clienteRecordDto.endereco());
        clienteModel.setCpf(clienteRecordDto.cpf());
        clienteModel.setCnpj(clienteRecordDto.cnpj());
        clienteModel.setCelular(clienteRecordDto.celular());

        return clienteRepository.save(clienteModel);
    }

    //consulta cliente id
    @Transactional
    public ResponseEntity<Object> getOneCliente(UUID id) {
    Optional<ClienteModel> clienteModel = clienteRepository.findById(id);

    if (clienteModel.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não  encontrado");
    }

        return ResponseEntity.status(HttpStatus.OK).body(clienteModel.get());

    }


    //busca todos clientes
    @Transactional
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    //atualza dados de cleinte
    @Transactional
    public ResponseEntity<Object> updateCliente(UUID id, ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> clienteModel = clienteRepository.findById(id);

        if (clienteModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        var cliente = clienteModel.get();
        BeanUtils.copyProperties(clienteRecordDto, cliente);
        return  ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));
    }

    @Transactional
    public ResponseEntity<Object> deleteCliente(UUID id) {
        Optional<ClienteModel> clienteModel = clienteRepository.findById(id);
        if (clienteModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado");
        }
     clienteRepository.delete(clienteModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
    }
}
