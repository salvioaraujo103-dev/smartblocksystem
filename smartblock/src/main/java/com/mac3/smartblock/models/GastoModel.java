package com.mac3.smartblock.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_gasto")
public class GastoModel implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private Date data;

    @Column
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etapa_id")
    private EtapaModel etapa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private ExecutorModel executor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_gasto_item",
            joinColumns = @JoinColumn(name = "gasto_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<ItemModel> itens = new HashSet<>();


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EtapaModel getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaModel etapa) {
        this.etapa = etapa;
    }

    public ExecutorModel getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorModel executor) {
        this.executor = executor;
    }

    public Set<ItemModel> getItens() {
        return itens;
    }

    public void setItens(Set<ItemModel> items) {
        this.itens = items;
    }
}
