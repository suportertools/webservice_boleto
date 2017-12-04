/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Claudemir Rtools
 */
@Entity
@Table(name = "pes_pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ds_documento", length = 20)
    private String documento;
    @Column(name = "ds_nome", length = 200)
    private String nome;
    @Column(name = "ds_endereco", length = 1000)
    private String endereco;
    @Column(name = "ds_bairro", length = 1000)
    private String bairro;
    @Column(name = "ds_cidade")
    private String cidade;
    @Column(name = "ds_uf")
    private String uf;
    @Column(name = "ds_cep")
    private String cep;

    public Pessoa() {
        this.id = -1;
        this.documento = "";
        this.nome = "";
        this.endereco = "";
        this.bairro = "";
        this.cidade = "";
        this.uf = "";
        this.cep = "";
    }

    public Pessoa(int id, String documento, String nome, String endereco, String bairro, String cidade, String uf, String cep) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
