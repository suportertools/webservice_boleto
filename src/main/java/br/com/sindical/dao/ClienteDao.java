/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.dao;

import br.com.sindical.factory.ConnectionDB;
import br.com.sindical.model.Cliente;
import br.com.sindical.model.Contribuinte;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Claudemir Rtools
 */
public class ClienteDao extends ConnectionDB {

    public List<Cliente> listaCliente() {
        Query qry = getEntityManager().createNativeQuery("SELECT c.* FROM pes_cliente c", Cliente.class);
        try {
            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public Cliente pesquisaClienteDocumento(String documento) {
        Query qry = getEntityManager().createNativeQuery(
                "  SELECT c.* "
                + "  FROM pes_cliente c "
                + " INNER JOIN pes_pessoa p ON p.id = c.id_pessoa "
                + " WHERE p.ds_documento = '" + documento + "'", Cliente.class);
        try {
            return (Cliente) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<Contribuinte> listaContribuinte(String chave_cliente) {
        Query qry = getEntityManager().createNativeQuery(
                "  SELECT c.* \n "
                + "  FROM pes_contribuinte c \n"
                + " INNER JOIN pes_cliente cli ON cli.id = c.id_cliente \n"
                + " WHERE cli.ds_chave_cliente = '" + chave_cliente + "'", Contribuinte.class);
        try {
            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public Contribuinte pesquisaContribuinte(String chave_cliente, Integer nr_codigo) {
        Query qry = getEntityManager().createNativeQuery(
                "  SELECT c.* \n "
                + "  FROM pes_contribuinte c \n"
                + " INNER JOIN pes_cliente cli ON cli.id = c.id_cliente \n"
                + " WHERE cli.ds_chave_cliente = '" + chave_cliente + "'"
                + "   AND c.nr_codigo = " + nr_codigo, Contribuinte.class);
        try {
            return (Contribuinte) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Cliente pesquisaClienteChave(String chave_cliente) {
        Query qry = getEntityManager().createNativeQuery(
                "  SELECT c.* \n "
                + "  FROM pes_cliente c \n"
                + " WHERE c.ds_chave_cliente = '" + chave_cliente + "'", Cliente.class);
        try {
            return (Cliente) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
