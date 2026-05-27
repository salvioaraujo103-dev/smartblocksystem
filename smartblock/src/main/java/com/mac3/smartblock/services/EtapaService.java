package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.EtapaRecordDto;
import com.mac3.smartblock.models.EtapaModel;
import com.mac3.smartblock.repositories.EtapaRepository;
import com.mac3.smartblock.repositories.ObraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EtapaService {

    private final EtapaRepository etapaRepository;
    private final ObraRepository obraRepository;


    public EtapaService(EtapaRepository etapaRepository, ObraRepository obraRepository) {
        this.etapaRepository = etapaRepository;
        this.obraRepository = obraRepository;
    }

    //salva etapa
    @Transactional
    public EtapaModel saveEtapa(EtapaRecordDto etapaRecordDto) {
        EtapaModel etapaModel = new EtapaModel();
        etapaModel.setNome(etapaRecordDto.nome());
        etapaModel.setOrdemExecucao(etapaRecordDto.ordemExec());
        etapaModel.setObraModel(obraRepository.findById(etapaRecordDto.ObraId()).get());
        return etapaRepository.save(etapaModel);
    }

    @Transactional
    public ResponseEntity<Object>  putEtapa(EtapaRecordDto etapaRecordDto, UUID id) {
        Optional<EtapaModel> etapaModel0 = etapaRepository.findById(id);
        if (etapaModel0.isPresent()) {
            EtapaModel etapaModel = etapaModel0.get();
            BeanUtils.copyProperties(etapaRecordDto,etapaModel);
            return ResponseEntity.status(HttpStatus.OK).body(etapaRepository.save(etapaModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etapa nao encontrada");
    }


    @Transactional
    public ResponseEntity<List<EtapaModel>> getAllEtapas() {
        return ResponseEntity.status(HttpStatus.OK).body(etapaRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Object> getEtapaById(UUID id) {

        Optional<EtapaModel> etapaModel = etapaRepository.findById(id);

        if(etapaModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(etapaModel);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etapa nao encontrada");
    }

    @Transactional
    public ResponseEntity<Object> deleteEtapa(UUID id) {
        Optional<EtapaModel> etapaModel = etapaRepository.findById(id);
        if(etapaModel.isPresent()) {
            etapaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Etapa removido");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etapa nao encontrada");
    }
}
