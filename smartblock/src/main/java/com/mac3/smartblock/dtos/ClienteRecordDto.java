package com.mac3.smartblock.dtos;

import java.util.Set;
import java.util.UUID;

public record ClienteRecordDto(String nome, String email,  String endereco, String cpf, String cnpj, String celular, Set<UUID> obrasIds) {
}
