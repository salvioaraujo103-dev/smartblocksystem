package com.mac3.smartblock.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_executor")
public class ExecutorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;



   @Column(nullable = false, unique = true)
    private String nome;

   

    @Column(nullable = false, unique = true)
    private String cpf;


   @Column(nullable = false)
    private String celular;

   @Column
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    private Set<ObraModel> obras = new HashSet<ObraModel>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ObraModel> getObras() {
        return obras;
    }

    public void setObras(Set<ObraModel> obras) {
        this.obras = obras;
    }
}
