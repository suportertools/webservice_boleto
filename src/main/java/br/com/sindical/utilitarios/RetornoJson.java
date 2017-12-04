/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.utilitarios;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Claudemir Rtools
 */
@XmlRootElement
public class RetornoJson {
    private Boolean status;
    private String mensagem;

    public RetornoJson() {
        this.status = true;
        this.mensagem = "";
    }
    
    public RetornoJson(Boolean status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
