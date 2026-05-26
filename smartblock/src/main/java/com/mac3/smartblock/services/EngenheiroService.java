package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.EngenheiroRecordDto;
import com.mac3.smartblock.models.ClienteModel;
import com.mac3.smartblock.models.EngenheiroModel;
import com.mac3.smartblock.repositories.EngenheiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EngenheiroService {

    @Autowired
    private EngenheiroRepository engenheiroRepository;


    //cadastrar engenheiro
    @Transactional
    public EngenheiroModel saveEng(EngenheiroRecordDto engenheiroRecordDto) {
        EngenheiroModel engenheiroModel = new EngenheiroModel();
        engenheiroModel.setNome(engenheiroRecordDto.nome());
        engenheiroModel.setEmail(engenheiroRecordDto.email());
        engenheiroModel.setCpf(engenheiroRecordDto.cpf());
        engenheiroModel.setCelular(engenheiroRecordDto.celular());
        engenheiroModel.setCrea(engenheiroRecordDto.crea());

        return engenheiroRepository.save(engenheiroModel);

    }

    //busca engenheiros
    @Transactional
    public ResponseEntity<Object> getEngById(UUID id) {
        Optional<EngenheiroModel> engenheiroModel = engenheiroRepository.findById(id);

        if (engenheiroModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Engenheiro não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(engenheiroModel.get());
    }


    @Transactional
    public ResponseEntity<List<EngenheiroModel>> getAllEng() {
        return ResponseEntity.status(HttpStatus.OK).body(engenheiroRepository.findAll());

    }

    //update
    @Transactional
    public ResponseEntity<Object> updateEng(UUID id , EngenheiroRecordDto engenheiroRecordDto) {
        Optional<EngenheiroModel> engenheiroModel = engenheiroRepository.findById(id);

        if (engenheiroModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Engenehiro nao encontrado");
        }

        var eng = engenheiroModel.get();
        BeanUtils.copyProperties(engenheiroRecordDto, eng);
        return ResponseEntity.status(HttpStatus.OK).body(engenheiroRepository.save(eng));

    }

    @Transactional
    public ResponseEntity<Object> deleteEng(UUID id) {
        Optional<EngenheiroModel> engenheiroModel = engenheiroRepository.findById(id);

        if (engenheiroModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Engenehiro nao encontrado");
        }

        engenheiroRepository.delete(engenheiroModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Engenheiro removido com sucesso");

    }

}
