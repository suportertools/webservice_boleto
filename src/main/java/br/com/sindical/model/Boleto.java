/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.model;

import br.com.sindical.utilitarios.Datas;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Claudemir Rtools
 */
@Entity
@Table(name = "fin_boleto")
@XmlRootElement
public class Boleto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "id_contribuinte", referencedColumnName = "id")
    @ManyToOne
    private Contribuinte contribuinte;
    @JoinColumn(name = "id_banco", referencedColumnName = "id")
    @ManyToOne
    private Banco banco;
    @JoinColumn(name = "id_layout", referencedColumnName = "id")
    @ManyToOne
    private Layout layout;
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_vencimento")
    private Date dataVencimento;
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_lancamento")
    private Date dataLancamento;
    @Column(name = "nr_valor")
    private Float valor;
    @Column(name = "ds_referencia", length = 10)
    private String referencia;
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_baixa")
    private Date dataBaixa;
    @Column(name = "nr_valor_baixa")
    private Float valorBaixa;
    @Column(name = "ds_conta", length = 50)
    private String conta;
    @Column(name = "ds_agencia", length = 50)
    private String agencia;
    @Column(name = "ds_codigo_cedente", length = 100)
    private String codigoCedente;
    @Column(name = "ds_local_pagamento")
    private String localPagamento;
    @Column(name = "ds_moeda")
    private String moeda;
    @Column(name = "ds_especie_moeda")
    private String especieMoeda;
    @Column(name = "ds_especie_documento")
    private String especieDocumento;
    @Column(name = "ds_carteira")
    private String carteira;
    @Column(name = "ds_mensagem_via_contribuinte")
    private String mensagemViaContribuinte;
    @Column(name = "ds_mensagem_via_banco")
    private String mensagemViaBanco;
    @Column(name = "ds_nosso_numero")
    private String nossoNumero;
    @Column(name = "ds_nosso_numero_banco")
    private String nossoNumeroBanco;
    @Column(name = "id_boleto")
    private Integer id_boleto;
    @Column(name = "pe_juros_mensal")
    private Double jurosMensal;
    @Column(name = "pe_multa")
    private Double multa;

    public Boleto() {
        this.id = -1;
        this.cliente = new Cliente();
        this.contribuinte = new Contribuinte();
        this.banco = new Banco();
        this.layout = new Layout();
        this.dataVencimento = null;
        this.dataLancamento = null;
        this.valor = new Float(0);
        this.referencia = "";
        this.dataBaixa = null;
        this.valorBaixa = new Float(0);
        this.conta = "";
        this.agencia = "";
        this.codigoCedente = "";
        this.localPagamento = "";
        this.moeda = "";
        this.especieMoeda = "";
        this.especieDocumento = "";
        this.carteira = "";
        this.mensagemViaContribuinte = "";
        this.mensagemViaBanco = "";
        this.nossoNumero = "";
        this.nossoNumeroBanco = "";
        this.id_boleto = null;
        this.jurosMensal = new Double(0);
        this.multa = new Double(0);
    }

    public Boleto(int id, Cliente cliente, Contribuinte contribuinte, Banco banco, Layout layout, Date dataVencimento, Date dataLancamento, Float valor, String referencia, Date dataBaixa, Float valorBaixa, String conta, String agencia, String codigoCedente, String localPagamento, String moeda, String especieMoeda, String especieDocumento, String carteira, String mensagemViaContribuinte, String mensagemViaBanco, String nossoNumero, String nossoNumeroBanco, Integer id_boleto, Double jurosMensal, Double multa) {
        this.id = id;
        this.cliente = cliente;
        this.contribuinte = contribuinte;
        this.banco = banco;
        this.layout = layout;
        this.dataVencimento = dataVencimento;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
        this.referencia = referencia;
        this.dataBaixa = dataBaixa;
        this.valorBaixa = valorBaixa;
        this.conta = conta;
        this.agencia = agencia;
        this.codigoCedente = codigoCedente;
        this.localPagamento = localPagamento;
        this.moeda = moeda;
        this.especieMoeda = especieMoeda;
        this.especieDocumento = especieDocumento;
        this.carteira = carteira;
        this.mensagemViaContribuinte = mensagemViaContribuinte;
        this.mensagemViaBanco = mensagemViaBanco;
        this.nossoNumero = nossoNumero;
        this.nossoNumeroBanco = nossoNumeroBanco;
        this.id_boleto = id_boleto;
        this.jurosMensal = jurosMensal;
        this.multa = multa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contribuinte getContribuinte() {
        return contribuinte;
    }

    public void setContribuinte(Contribuinte contribuinte) {
        this.contribuinte = contribuinte;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataVencimentoString() {
        return Datas.converteData(dataVencimento);
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDataLancamentoString() {
        return Datas.converteData(dataLancamento);
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Float getValorBaixa() {
        return valorBaixa;
    }

    public void setValorBaixa(Float valorBaixa) {
        this.valorBaixa = valorBaixa;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCodigoCedente() {
        return codigoCedente;
    }

    public void setCodigoCedente(String codigoCedente) {
        this.codigoCedente = codigoCedente;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getEspecieMoeda() {
        return especieMoeda;
    }

    public void setEspecieMoeda(String especieMoeda) {
        this.especieMoeda = especieMoeda;
    }

    public String getEspecieDocumento() {
        return especieDocumento;
    }

    public void setEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public String getMensagemViaContribuinte() {
        return mensagemViaContribuinte;
    }

    public void setMensagemViaContribuinte(String mensagemViaContribuinte) {
        this.mensagemViaContribuinte = mensagemViaContribuinte;
    }

    public String getMensagemViaBanco() {
        return mensagemViaBanco;
    }

    public void setMensagemViaBanco(String mensagemViaBanco) {
        this.mensagemViaBanco = mensagemViaBanco;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getNossoNumeroBanco() {
        return nossoNumeroBanco;
    }

    public void setNossoNumeroBanco(String nossoNumeroBanco) {
        this.nossoNumeroBanco = nossoNumeroBanco;
    }

    public Integer getId_boleto() {
        return id_boleto;
    }

    public void setId_boleto(Integer id_boleto) {
        this.id_boleto = id_boleto;
    }

    public Double getJurosMensal() {
        return jurosMensal;
    }

    public void setJurosMensal(Double jurosMensal) {
        this.jurosMensal = jurosMensal;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

}
