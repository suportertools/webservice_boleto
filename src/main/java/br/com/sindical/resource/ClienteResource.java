package br.com.sindical.resource;

import br.com.sindical.controller.ClienteController;
import br.com.sindical.model.Boleto;
import br.com.sindical.model.Cliente;
import br.com.sindical.model.Contribuinte;
import br.com.sindical.utilitarios.RetornoJson;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Claudemir Rtools
 */
@Path("/cliente")
public class ClienteResource implements Serializable {

    private final ClienteController clienteController = new ClienteController();

    @GET
    @Path("listar_todos")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public List<Cliente> listar() {
        return clienteController.listar();
    }

    @POST
    @Path("salvar")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public RetornoJson salvar(@FormParam("documento") String documento, @FormParam("nome") String nome, @Context HttpServletRequest req) {
        req.getContentType();
        String retorno = clienteController.salvar(documento, nome);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("alterar")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8"})
    public RetornoJson alterar(@FormParam("id") Integer id, @FormParam("name") String nome) {
        String retorno = clienteController.alterar(id, nome);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("excluir")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8"})
    public RetornoJson excluir(@FormParam("id") Integer id) {
        String retorno = clienteController.delete(id);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @GET
    @Path("/{chave}/listar_contribuinte")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public List<Contribuinte> listar_contribuinte(@PathParam("chave") String chave) {
        return clienteController.listar_contribuinte(chave);
    }

    @POST
    @Path("{chave}/salvar_contribuinte")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=ISO-8859-1"})
    public RetornoJson salvar_contribuintes(@PathParam("chave") String chave, @FormParam("codigo") Integer codigo, @FormParam("documento") String documento, @FormParam("nome") String nome, @FormParam("endereco") String endereco, @FormParam("bairro") String bairro, @FormParam("cidade") String cidade, @FormParam("uf") String uf, @FormParam("cep") String cep) {
        String retorno = clienteController.salvar_contribuinte(chave, codigo, documento, nome, endereco, bairro, cidade, uf, cep);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("{chave}/alterar_contribuinte")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=ISO-8859-1"})
    public RetornoJson alterar_contribuinte(@PathParam("chave") String chave, @FormParam("codigo") Integer codigo, @FormParam("documento") String documento, @FormParam("nome") String nome, @FormParam("endereco") String endereco, @FormParam("bairro") String bairro, @FormParam("cidade") String cidade, @FormParam("uf") String uf, @FormParam("cep") String cep) {
        String retorno = clienteController.alterar_contribuinte(chave, codigo, documento, nome, endereco, bairro, cidade, uf, cep);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("{chave}/excluir_contribuinte")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=ISO-8859-1"})
    public RetornoJson excluir_contribuinte(@PathParam("chave") String chave, @FormParam("codigo") Integer codigo) {
        String retorno = clienteController.excluir_contribuinte(chave, codigo);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("{chave}/pesquisar_contribuinte")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=ISO-8859-1"})
    public Contribuinte pesquisar_contribuinte(@PathParam("chave") String chave, @FormParam("codigo") Integer codigo) {
        return clienteController.pesquisar_contribuinte(chave, codigo);
    }

    @POST
    @Path("/{chave}/criar_boleto")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=ISO-8859-1"})
    public RetornoJson criar_boleto(@PathParam("chave") String chave_cliente, @FormParam("codigo_contribuinte") Integer codigo, @FormParam("numero_banco") String numero_banco, @FormParam("layout") Integer id_layout, @FormParam("data_vencimento") String data_vencimento, @FormParam("valor") String valor, @FormParam("referencia") String referencia, @FormParam("conta") String conta, @FormParam("agencia") String agencia, @FormParam("codigo_cedente") String codigo_cedente, @FormParam("local_pagamento") String local_pagamento, @FormParam("moeda") String moeda, @FormParam("especie_moeda") String especie_moeda, @FormParam("especie_documento") String especie_documento, @FormParam("carteira") String carteira, @FormParam("mensagem_via_contribuinte") String mensagem_via_contribuinte, @FormParam("mensagem_via_banco") String mensagem_via_banco, @FormParam("nosso_numero") String nosso_numero, @FormParam("id_boleto") Integer id_boleto) {
        String retorno = clienteController.criar_boleto_contribuinte(chave_cliente, codigo, numero_banco, id_layout, data_vencimento, valor, referencia, conta, agencia, codigo_cedente, local_pagamento, moeda, especie_moeda, especie_documento, carteira, mensagem_via_contribuinte, mensagem_via_banco, nosso_numero, id_boleto);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("/{chave}/alterar_boleto")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=ISO-8859-1"})
    public RetornoJson alterar_boleto(@PathParam("chave") String chave_cliente, @FormParam("codigo_contribuinte") Integer codigo, @FormParam("numero_banco") String numero_banco, @FormParam("layout") Integer id_layout, @FormParam("data_vencimento") String data_vencimento, @FormParam("valor") String valor, @FormParam("referencia") String referencia, @FormParam("conta") String conta, @FormParam("agencia") String agencia, @FormParam("codigo_cedente") String codigo_cedente, @FormParam("local_pagamento") String local_pagamento, @FormParam("moeda") String moeda, @FormParam("especie_moeda") String especie_moeda, @FormParam("especie_documento") String especie_documento, @FormParam("carteira") String carteira, @FormParam("mensagem_via_contribuinte") String mensagem_via_contribuinte, @FormParam("mensagem_via_banco") String mensagem_via_banco, @FormParam("nosso_numero") String nosso_numero) {
        String retorno = clienteController.alterar_boleto_contribuinte(chave_cliente, codigo, numero_banco, id_layout, data_vencimento, valor, referencia, conta, agencia, codigo_cedente, local_pagamento, moeda, especie_moeda, especie_documento, carteira, mensagem_via_contribuinte, mensagem_via_banco, nosso_numero);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("/{chave}/excluir_boleto")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public RetornoJson excluir_boleto(@PathParam("chave") String chave_cliente, @FormParam("nosso_numero") String nosso_numero) {
        String retorno = clienteController.excluir_boleto_contribuinte(chave_cliente, nosso_numero);
        if (retorno.isEmpty()) {
            return new RetornoJson(true, "");
        } else {
            return new RetornoJson(false, retorno);
        }
    }

    @POST
    @Path("/{chave}/imprimir_boleto")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8"})
    public RetornoJson imprimir_boleto(@PathParam("chave") String chave_cliente, @FormParam("nosso_numero") String nosso_numero) {
        HashMap retorno = clienteController.imprimir_boleto_contribuinte(chave_cliente, nosso_numero);
        return new RetornoJson((Boolean) retorno.get("status"), retorno.get("mensagem").toString());
    }

    @POST
    @Path("/{chave}/imprimir_boleto_test")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8"})
    public RetornoJson imprimir_boleto_test(@PathParam("chave") String chave_cliente, @FormParam("nosso_numero") String nosso_numero) {
        HashMap retorno = clienteController.imprimir_boleto_contribuinte(chave_cliente, nosso_numero, true);
        return new RetornoJson((Boolean) retorno.get("status"), retorno.get("mensagem").toString());
    }

    @POST
    @Path("/{chave}/pesquisar_boleto")
    @Produces({MediaType.APPLICATION_JSON + ";charset=ISO-8859-1"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8"})
    public Boleto pesquisar_boleto(@PathParam("chave") String chave_cliente, @FormParam("nosso_numero") String nosso_numero) {
        return clienteController.pesquisar_boleto_contribuinte(chave_cliente, nosso_numero);
    }

}
