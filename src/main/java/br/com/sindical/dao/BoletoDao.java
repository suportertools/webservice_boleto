/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.dao;

import br.com.sindical.factory.ConnectionDB;
import br.com.sindical.model.Banco;
import br.com.sindical.model.Boleto;
import br.com.sindical.model.Layout;
import javax.persistence.Query;

/**
 *
 * @author Claudemir Rtools
 */
public class BoletoDao extends ConnectionDB {

    public Banco pesquisaBancoNumero(String numero) {
        Query qry = getEntityManager().createNativeQuery(
                "  SELECT b.* \n "
                + "  FROM fin_banco b \n"
                + " WHERE b.ds_numero = '" + numero + "'", Banco.class);
        try {
            return (Banco) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Layout pesquisaLayoutID(Integer id) {
        Query qry = getEntityManager().createNativeQuery(
                "  SELECT l.* \n "
                + "  FROM fin_layout l \n"
                + " WHERE l.id = " + id, Layout.class);
        try {
            return (Layout) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Boleto pesquisaBoletoNossoNumero(String chave, String nosso_numero) {
        Query qry = getEntityManager().createNativeQuery(
                " SELECT b.* \n"
                + " FROM fin_boleto b \n"
                + "INNER JOIN pes_cliente c ON c.id = b.id_cliente \n"
                + "WHERE b.ds_nosso_numero = '" + nosso_numero + "' \n"
                + "  AND c.ds_chave_cliente = '" + chave + "'", Boleto.class);
        try {
            return (Boleto) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
