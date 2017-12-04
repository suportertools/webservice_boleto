/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Claudemir Rtools
 */
@Entity
@Table(name = "pes_cliente")
@XmlRootElement
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;
    @Column(name = "ds_chave_cliente", length = 500)
    private String chaveCliente;
    @Column(name = "ds_chave_acesso_banco", length = 500)
    private String chaveAcessoBanco;
    @Column(name = "ds_codigo_municipio", length = 20)
    private String codigoMunicipio;
    @Column(name = "ds_inicio_representacao", length = 20)
    private String inicioRepresentacao;

    public Cliente() {
        this.id = -1;
        this.pessoa = new Pessoa();
        this.chaveCliente = "";
        this.chaveAcessoBanco = "";
        this.codigoMunicipio = "";
        this.inicioRepresentacao = "";
    }

    public Cliente(int id, Pessoa pessoa, String chaveCliente, String chaveAcessoBanco, String codigoMunicipio, String inicioRepresentacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.chaveCliente = chaveCliente;
        this.chaveAcessoBanco = chaveAcessoBanco;
        this.codigoMunicipio = codigoMunicipio;
        this.inicioRepresentacao = inicioRepresentacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getChaveCliente() {
        return chaveCliente;
    }

    public void setChaveCliente(String chaveCliente) {
        this.chaveCliente = chaveCliente;
    }

    public String getChaveAcessoBanco() {
        return chaveAcessoBanco;
    }

    public void setChaveAcessoBanco(String chaveAcessoBanco) {
        this.chaveAcessoBanco = chaveAcessoBanco;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getInicioRepresentacao() {
        return inicioRepresentacao;
    }

    public void setInicioRepresentacao(String inicioRepresentacao) {
        this.inicioRepresentacao = inicioRepresentacao;
    }

}
