package com.mac3.smartblock.dtos;


import com.mac3.smartblock.models.ItemModel;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record GastoRecordDto(Double valor, Date data, String descricao, UUID etapaId, UUID executorId, Set<UUID> itensIds ) {
}
