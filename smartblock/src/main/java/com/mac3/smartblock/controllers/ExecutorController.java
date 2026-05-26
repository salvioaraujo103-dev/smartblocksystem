package com.mac3.smartblock.controllers;

import com.mac3.smartblock.dtos.ExecutorRecordDto;
import com.mac3.smartblock.models.ExecutorModel;
import com.mac3.smartblock.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ExecutorController {

    @Autowired
    private ExecutorService executorService;

    @PostMapping("/smartblock/executores/")
    public ResponseEntity<Object> saveExecutor(@RequestBody ExecutorRecordDto executorRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(executorService.saveExecutor(executorRecordDto));
    }

    @GetMapping("/smartblock/executores/")
    public ResponseEntity<List<ExecutorModel>> getAllExecutors() {
        return executorService.findAllExecutors();
    }

    @GetMapping("/smartblock/executores/{id}")
    public ResponseEntity<Object> getExecutorById(@RequestBody @PathVariable(value = "id") UUID id) {
        return executorService.getExecById(id);


    }

    @PutMapping("/smartblock/executores/{id}")
    public ResponseEntity<Object> putExecutor(@RequestBody @PathVariable(value = "id")UUID id, ExecutorRecordDto executorRecordDto) {
        return executorService.updateExecutor(id, executorRecordDto);
    }

    @DeleteMapping("/smartblock/executores/")
    public ResponseEntity<Object> deleteExecutorById(@RequestBody @PathVariable(value = "id") UUID id) {
        return executorService.deleteExecutor(id);
    }
}
