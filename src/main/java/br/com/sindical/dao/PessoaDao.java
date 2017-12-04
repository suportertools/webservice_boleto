/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.dao;

import br.com.sindical.factory.ConnectionDB;
import br.com.sindical.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Claudemir Rtools
 */
public class PessoaDao extends ConnectionDB {

    public List<Cliente> listaCliente() {
        Query qry = getEntityManager().createNativeQuery("SELECT p.* FROM pes_cliente c", Cliente.class);
        try {
            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
}
