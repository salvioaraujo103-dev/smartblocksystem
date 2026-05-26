package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.ExecutorRecordDto;
import com.mac3.smartblock.models.ExecutorModel;
import com.mac3.smartblock.repositories.ExecutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExecutorService {

    @Autowired
    private ExecutorRepository executorRepository;

    //cadastra executor
    @Transactional
    public ResponseEntity<ExecutorModel> saveExecutor(ExecutorRecordDto executorRecordDto) {
        ExecutorModel executorModel = new ExecutorModel();
        executorModel.setNome(executorRecordDto.nome());
        executorModel.setEmail(executorRecordDto.email());
        executorModel.setCpf(executorRecordDto.cpf());
        executorModel.setCelular(executorRecordDto.celular());

        return ResponseEntity.status(HttpStatus.CREATED).body(executorRepository.save(executorModel));
    }


    //atualiza dados de executor
    @Transactional
    public ResponseEntity<Object> updateExecutor(UUID id, ExecutorRecordDto executorRecordDto) {
        Optional<ExecutorModel> executorModel = executorRepository.findById(id);
        if (executorModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Executor não encontrado");
        }
        ExecutorModel executor = executorModel.get();
        BeanUtils.copyProperties(executorRecordDto, executor);

        return ResponseEntity.status(HttpStatus.OK).body(executorRepository.save(executor));
    }


    @Transactional
    public ResponseEntity<Object> deleteExecutor(UUID id) {
        Optional<ExecutorModel> executorModel = executorRepository.findById(id);
        if (executorModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Executor não encontrado");
        }
        executorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Executor excluido com sucesso");
    }

    //busca todos executor
    @Transactional
    public ResponseEntity<List<ExecutorModel>> findAllExecutors() {
        List<ExecutorModel> executors = executorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(executors);
    }

    @Transactional
    public ResponseEntity<Object> getExecById(UUID id ) {
        Optional<ExecutorModel> executorModel = executorRepository.findById(id);
        if (executorModel.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Executor nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(executorModel);
    }

}
