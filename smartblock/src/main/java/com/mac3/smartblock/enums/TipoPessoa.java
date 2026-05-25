package com.mac3.smartblock.enums;

public enum TipoPessoa {
    CLIENTE("Cliente"),
    ENGENHEIRO("Engenheiro"),
    EXECUTOR("Executor");

    private final String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
