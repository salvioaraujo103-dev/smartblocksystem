package com.mac3.smartblock.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_etapa")
public class EtapaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String ordemExecucao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id")
    private ObraModel obraEtapa;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "etapa", fetch = FetchType.LAZY)
    private Set<GastoModel> gastos = new HashSet<>();




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrdemExecucao() {
        return ordemExecucao;
    }

    public void setOrdemExecucao(String ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
    }

    public ObraModel getObraEtapa() {
        return obraEtapa;
    }

    public void setObraEtapa(ObraModel obra) {
        this.obraEtapa = obra;
    }

    public Set<GastoModel> getGastos() {
        return gastos;
    }

    public void setGastos(Set<GastoModel> gastos) {
        this.gastos = gastos;
    }
}
