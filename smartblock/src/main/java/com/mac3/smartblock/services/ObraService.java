package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.EtapaRecordDto;
import com.mac3.smartblock.dtos.ObraRecordDto;
import com.mac3.smartblock.models.ObraModel;
import com.mac3.smartblock.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ObraService {


     private final EtapaRepository etapaRepository;
     private final ClienteRepository clienteRepository;
     private final EngenheiroRepository engenheiroRepository;
     private final ExecutorRepository executorRepository;
    private final ObraRepository obraRepository;

    public ObraService(EtapaRepository etapaRepository, ClienteRepository clienteRepository, EngenheiroRepository engenheiroRepository, ExecutorRepository executorRepository, ObraRepository obraRepository) {
        this.etapaRepository = etapaRepository;

        this.clienteRepository = clienteRepository;
        this.engenheiroRepository = engenheiroRepository;
        this.executorRepository = executorRepository;
        this.obraRepository = obraRepository;
    }


    //cadastra obra
    @Transactional
    public ObraModel saveObra(ObraRecordDto obraRecordDto) {
        ObraModel obraModel = new ObraModel();
        obraModel.setDataInicio(obraRecordDto.dataInicio());
        obraModel.setEndereco(obraRecordDto.endereco());
        obraModel.setNome(obraRecordDto.nome());
        obraModel.setCliente(clienteRepository.findById(obraRecordDto.clienteId()).get());
        obraModel.setEngenheiro(engenheiroRepository.findById(obraRecordDto.engenheiroId()).get());
        obraModel.setExecutor(executorRepository.findById(obraRecordDto.executorId()).get());
        obraModel.setEtapas(  etapaRepository.findAllById(obraRecordDto.etapaIds()).stream().collect(Collectors.toSet()) );

        return obraRepository.save(obraModel);
    }

    //busca uma obras
    @Transactional
    public ResponseEntity<Object> findObraById(UUID id) {
        Optional<ObraModel> obraModel = obraRepository.findById(id);
        if (obraModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(obraModel.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada");
    }

    //busca todas obras
    @Transactional
    public ResponseEntity<List<ObraModel>> findAllObra() {

        return ResponseEntity.status(HttpStatus.OK).body(obraRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Object> deleteObra(UUID id) {
        Optional<ObraModel> obraModel = obraRepository.findById(id);
        if (obraModel.isPresent()) {
            obraRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Obra removida com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OBra not found");
    }

    @Transactional
    public ResponseEntity<Object> putObra(UUID id, ObraRecordDto obraRecordDto) {
        Optional<ObraModel> obraModel = obraRepository.findById(id);
        if (obraModel.isPresent()) {
            var obraModel0 = obraModel.get();
            BeanUtils.copyProperties(obraRecordDto, obraModel0);
            return ResponseEntity.status(HttpStatus.OK).body(obraRepository.save(obraModel0));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra not found");
    }



}
