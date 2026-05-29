package com.mac3.smartblock.services;

import com.mac3.smartblock.dtos.GastoRecordDto;
import com.mac3.smartblock.models.GastoModel;
import com.mac3.smartblock.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GastoService {

    private final GastoRepository gastoRepository;
    private final EtapaRepository etapaRepository;
    private final ObraRepository obraRepository;
    private final ExecutorRepository executorRepository;
    private final ItemRepository itemRepository;

    public GastoService(GastoRepository gastoRepository, EtapaRepository etapaRepository, ObraRepository obraRepository, ExecutorRepository executorRepository, ItemRepository itemRepository) {
        this.gastoRepository = gastoRepository;
        this.etapaRepository = etapaRepository;
        this.obraRepository = obraRepository;
        this.executorRepository = executorRepository;
        this.itemRepository = itemRepository;
    }


    public GastoModel saveGasto(GastoRecordDto gastoRecordDto) {
        GastoModel gastoModel = new GastoModel();
        gastoModel.setDescricao(gastoRecordDto.descricao());
        gastoModel.setData(gastoRecordDto.data());
        gastoModel.setValor(gastoRecordDto.valor());
        gastoModel.setEtapa(etapaRepository.findById(gastoRecordDto.etapaId()).get());
        gastoModel.setExecutor(executorRepository.findById(gastoRecordDto.executorId()).get());
        gastoModel.setItens(itemRepository.findAllById(gastoRecordDto.itensIds()).stream().collect(Collectors.toSet()));

        return gas
    }
}
