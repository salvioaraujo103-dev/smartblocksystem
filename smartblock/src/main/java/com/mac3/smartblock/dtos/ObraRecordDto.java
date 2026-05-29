package com.mac3.smartblock.dtos;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record ObraRecordDto(Date dataInicio, String endereco, String nome, UUID clienteId, UUID engenheiroId, UUID executorId, Set<UUID> etapaIds ) {
}
