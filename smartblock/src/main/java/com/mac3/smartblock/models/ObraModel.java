package com.mac3.smartblock.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_obra")
public class ObraModel implements Serializable {
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private Date dataInicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engenheiro_id")
    private EngenheiroModel engenheiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private ExecutorModel executor;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "obraEtapa", fetch = FetchType.LAZY)
    private Set<EtapaModel> etapas = new HashSet<>();

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public EngenheiroModel getEngenheiro() {
        return engenheiro;
    }

    public void setEngenheiro(EngenheiroModel engenheiroModel) {
        this.engenheiro = engenheiroModel;
    }

    public ExecutorModel getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorModel executorModel) {
        this.executor = executorModel;
    }

    public Set<EtapaModel> getEtapas() {
        return etapas;
    }

    public void setEtapas(Set<EtapaModel> etapas) {
        this.etapas = etapas;
    }
}
