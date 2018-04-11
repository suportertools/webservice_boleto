/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.controller;

import br.com.sindical.dao.BoletoDao;
import br.com.sindical.dao.ClienteDao;
import br.com.sindical.factory.Dao;
import br.com.sindical.model.Banco;
import br.com.sindical.model.Boleto;
import br.com.sindical.model.Cliente;
import br.com.sindical.model.Contribuinte;
import br.com.sindical.model.Layout;
import br.com.sindical.model.Pessoa;
import br.com.sindical.utilitarios.Datas;
import br.com.sindical.utilitarios.Moeda;
import br.com.sindical.utilitarios.MoedaDouble;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Claudemir Rtools
 */
public class ClienteController_backuo {

    public List<Cliente> listar() {
        return new ClienteDao().listaCliente();
    }

    public String salvar(String documento, String nome) {
        if (documento == null || documento.isEmpty() || documento.equals("null")) {
            return "Cliente não pode ser salvo sem documento";
        }

        if (nome == null || nome.isEmpty() || nome.equals("null")) {
            return "Cliente não pode ser salvo sem nome";
        }

        ClienteDao cdao = new ClienteDao();
        Cliente c = cdao.pesquisaClienteDocumento(documento);

        if (c != null) {
            return "Cliente já cadastrado";
        }

        Dao dao = new Dao();

        dao.begin();
        c = new Cliente();
        c.getPessoa().setDocumento(documento);
        c.getPessoa().setNome(nome);
        c.setChaveCliente("123hg2132sdfg3716dwsdjkas");

        if (!dao.save(c.getPessoa())) {
            dao.rollback();
            return "Erro ao Salvar Pessoa Cliente";
        }

        if (!dao.save(c)) {
            dao.rollback();
            return "Erro ao Salvar Cliente";
        }

        dao.commit();
        return "";
    }

    public String alterar(Integer id, String nome) {
        Dao dao = new Dao();
        Cliente c = (Cliente) dao.find(new Cliente(), id);
        if (c == null) {
            return "Cliente não encontrado";
        }

        dao.begin();

        c.getPessoa().setNome(nome);
        if (!dao.update(c)) {
            dao.rollback();
            return "Erro ao alterar Cliente";
        }

        dao.commit();
        return "";
    }

    public String delete(Integer id) {
        Dao dao = new Dao();
        Cliente c = (Cliente) dao.find(new Cliente(), id);
        if (c == null) {
            return "Cliente não encontrado";
        }

        dao.begin();
        if (!dao.remove(dao.find(new Cliente(), id))) {
            dao.rollback();
            return "Erro ao excluir Cliente";
        }

        dao.commit();
        return "";
    }

    public List<Contribuinte> listar_contribuinte(String chave_cliente) {
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return new ArrayList();
        }

        List<Contribuinte> lista = new ClienteDao().listaContribuinte(chave_cliente);
        return lista;
    }

    public String salvar_contribuinte(String chave_cliente, Integer codigo, String cnpj, String nome, String endereco, String bairro, String cidade, String uf, String cep) {
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return "Cliente não encontrado";
        }

        Contribuinte con = new ClienteDao().pesquisaContribuinte(chave_cliente, codigo);
        // CÓDIGO JÁ EXISTE
        if (con != null) {
            return "Este código já esta cadastrado para outro Contribuinte";
        }

        Dao dao = new Dao();

        dao.begin();
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setDocumento(cnpj.replace(".", "").replace("/", "").replace("-", ""));
        p.setEndereco(endereco);
        p.setBairro(bairro.isEmpty() ? "N/D" : bairro);
        p.setCidade(cidade);
        p.setUf(uf);
        p.setCep(cep);

        if (!dao.save(p)) {
            dao.rollback();
            return "Erro ao salvar Pessoa Contribuinte";
        }

        Contribuinte c = new Contribuinte();
        c.setPessoa(p);
        c.setCodigo(codigo);
        c.setCliente(cliente);

        if (!dao.save(c)) {
            dao.rollback();
            return "Erro ao salvar Cliente Contribuinte";
        }

        dao.commit();
        return "";
    }

    public String alterar_contribuinte(String chave_cliente, Integer codigo, String cnpj, String nome, String endereco, String bairro, String cidade, String uf, String cep) {
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return "Cliente não encontrado";
        }

        Contribuinte con = new ClienteDao().pesquisaContribuinte(chave_cliente, codigo);
        // SE CÓDIGO NÃO EXISTE
        if (con == null) {
            return "Código de Contribuinte não existe";
        }

        // PESQUISAR SE O CNPJ ALTERADO JÁ ESTA CADASTRADO
        if (1 == 2) {
            return "Este CNPJ já esta cadastrado";
        }

        Dao dao = new Dao();

        dao.begin();

        con.getPessoa().setNome(nome);
        con.getPessoa().setDocumento(cnpj.replace(".", "").replace("/", "").replace("-", ""));
        con.getPessoa().setEndereco(endereco);
        con.getPessoa().setBairro(bairro.isEmpty() ? "N/D" : bairro);
        con.getPessoa().setCidade(cidade);
        con.getPessoa().setUf(uf);
        con.getPessoa().setCep(cep);

        if (!dao.update(con.getPessoa())) {
            dao.rollback();
            return "Erro ao atualizar Pessoa Contribuinte";
        }

        if (!dao.update(con)) {
            dao.rollback();
            return "Erro ao atualizar Contribuinte";
        }

        dao.commit();
        return "";
    }

    public String excluir_contribuinte(String chave_cliente, Integer codigo) {
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return "Cliente não encontrado";
        }

        Contribuinte con = new ClienteDao().pesquisaContribuinte(chave_cliente, codigo);
        // SE CÓDIGO NÃO EXISTE
        if (con == null) {
            return "Código de Contribuinte não existe";
        }

        Dao dao = new Dao();

        dao.begin();
        if (!dao.remove(dao.find(con))) {
            dao.rollback();
            return "Erro ao excluir Contribuinte";
        }

        Pessoa pes = con.getPessoa();

        if (!dao.remove(dao.find(pes))) {
            dao.rollback();
            return "Erro ao excluir Pessoa Contribuinte";
        }

        dao.commit();

        return "";
    }

    public Contribuinte pesquisar_contribuinte(String chave_cliente, Integer codigo) {
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return null;
        }

        Contribuinte con = new ClienteDao().pesquisaContribuinte(chave_cliente, codigo);
        // SE CÓDIGO NÃO EXISTE
        if (con == null) {
            return null;
        }

        return con;
    }

    public String criar_boleto_contribuinte(String chave_cliente, Integer codigo, String numero_banco, Integer id_layout, String data_vencimento, String valor, String referencia, String conta, String agencia, String codigo_cedente, String local_pagamento, String moeda, String especie_moeda, String especie_documento, String carteira, String mensagem_via_contribuinte, String mensagem_via_banco, String nosso_numero, Integer id_boleto, String jurosMensal, String multa) {
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return "Cliente não encontrado";
        }

        Contribuinte con = new ClienteDao().pesquisaContribuinte(chave_cliente, codigo);
        // SE CÓDIGO NÃO EXISTE
        if (con == null) {
            return "Código de Contribuinte não existe";
        }

        Dao dao = new Dao();

        BoletoDao bdao = new BoletoDao();

        if (bdao.pesquisaBoletoNossoNumero(chave_cliente, nosso_numero) != null) {
            return "Boleto já cadastrado com este número";
        }

        Banco ban = bdao.pesquisaBancoNumero(numero_banco);
        if (ban == null) {
            return "Número do Banco " + numero_banco + " não existe";
        }

        Layout layout = bdao.pesquisaLayoutID(id_layout); // padrão 1
        if (layout == null) {
            return "Layout não informado, padrão é 1";
        }

        if (data_vencimento == null || data_vencimento.isEmpty() || data_vencimento.length() != 8) {
            return "Data de Vencimento inválida";
        }

        if (referencia == null || referencia.isEmpty() || referencia.length() != 6) {
            return "Referência inválida";
        }

        Boleto bol = new Boleto(
                -1,
                cliente,
                con,
                ban,
                layout,
                Datas.converte(data_vencimento.substring(0, 2) + "/" + data_vencimento.substring(2, 4) + "/" + data_vencimento.substring(4, 8)),
                Datas.dataHoje(),
                Moeda.converteUS$(valor),
                referencia.substring(0, 2) + "/" + referencia.substring(2, 6),
                null,
                new Float(0),
                conta.replace(".", "").replace("/", "").replace("-", ""),
                agencia.replace(".", "").replace("/", "").replace("-", ""),
                codigo_cedente.replace(".", "").replace("/", "").replace("-", ""),
                local_pagamento,
                moeda,
                especie_moeda,
                especie_documento,
                carteira,
                mensagem_via_contribuinte,
                mensagem_via_banco,
                nosso_numero,
                "",
                id_boleto,
                MoedaDouble.converteUS$(jurosMensal),
                MoedaDouble.converteUS$(multa)
        );

        dao.begin();

        if (!dao.save(bol)) {
            dao.rollback();
            return "Erro ao Criar Boleto, tente novamente";
        }

        dao.commit();
        return "";
    }

    public String alterar_boleto_contribuinte(String chave_cliente, Integer codigo, String numero_banco, Integer id_layout, String data_vencimento, String valor, String referencia, String conta, String agencia, String codigo_cedente, String local_pagamento, String moeda, String especie_moeda, String especie_documento, String carteira, String mensagem_via_contribuinte, String mensagem_via_banco, String nosso_numero) {
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return "Cliente não encontrado";
        }

        Dao dao = new Dao();

        BoletoDao bdao = new BoletoDao();
        Boleto boleto = bdao.pesquisaBoletoNossoNumero(chave_cliente, nosso_numero);

        if (boleto == null) {
            return "Boleto não encontrado";
        }

        Banco ban = bdao.pesquisaBancoNumero(numero_banco);
        Layout layout = bdao.pesquisaLayoutID(id_layout); // padrão 1

        boleto.setBanco(ban);
        boleto.setLayout(layout);
        boleto.setDataVencimento(Datas.converte(data_vencimento.substring(0, 2) + "/" + data_vencimento.substring(2, 4) + "/" + data_vencimento.substring(4, 8)));
        boleto.setValor(Moeda.converteUS$(valor));
        boleto.setReferencia(referencia.substring(0, 2) + "/" + referencia.substring(2, 6));
        boleto.setConta(conta.replace(".", "").replace("/", "").replace("-", ""));
        boleto.setAgencia(agencia.replace(".", "").replace("/", "").replace("-", ""));
        boleto.setCodigoCedente(codigo_cedente.replace(".", "").replace("/", "").replace("-", ""));
        boleto.setLocalPagamento(local_pagamento);
        boleto.setMoeda(moeda);
        boleto.setEspecieMoeda(especie_moeda);
        boleto.setCarteira(carteira);
        boleto.setMensagemViaContribuinte(mensagem_via_contribuinte);
        boleto.setMensagemViaBanco(mensagem_via_banco);

        dao.begin();

        if (!dao.update(boleto)) {
            dao.rollback();
            return "Erro ao Criar Boleto, tente novamente";
        }

        dao.commit();
        return "";
    }

    public String excluir_boleto_contribuinte(String chave_cliente, String nosso_numero) {
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return "Cliente não encontrado";
        }

        Dao dao = new Dao();

        BoletoDao bdao = new BoletoDao();

        Boleto bol = bdao.pesquisaBoletoNossoNumero(chave_cliente, nosso_numero);
        if (bol == null) {
            return "Boleto não encontrado";
        }

        dao.begin();

        if (!dao.remove(dao.find(bol))) {
            dao.rollback();
            return "Erro ao excluir Boleto";
        }

        dao.commit();
        return "";
    }

    public HashMap imprimir_boleto_contribuinte(String chave_cliente, String nosso_numero) {
        return imprimir_boleto_contribuinte(chave_cliente, nosso_numero, false);
    }
    
    public byte[] Hash(String dados)  {
        MessageDigest md;
        byte[] decoded = new byte[0];
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(dados.getBytes("ISO8859-1"));
            
            decoded = Base64.encodeBase64(hash);
            //decoded = Base64.decodeBase64(hash);
            System.out.println(Arrays.toString(decoded));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.getMessage();
        }
        return decoded;
    }

    public HashMap imprimir_boleto_contribuinte(String chave_cliente, String nosso_numero, Boolean test) {
        HashMap retorno = new LinkedHashMap();
        retorno.put("status", false);
        retorno.put("mensagem", "");
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        try {
            Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
            if (cliente == null) {
                // CLIENTE NÃO ENCONTRADO
                retorno.put("status", false);
                retorno.put("mensagem", "Cliente não encontrado");
                return retorno;
            }

            BoletoDao bdao = new BoletoDao();

            Boleto bol = bdao.pesquisaBoletoNossoNumero(chave_cliente, nosso_numero);
            if (bol == null) {
                retorno.put("status", false);
                retorno.put("mensagem", "Boleto não encontrado");
                return retorno;
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
            // COMENTADA PARA TESTE
            HttpPost httppost;
            if (test) {
                // httppost = new HttpPost("https://geraboleto.sicoobnet.com.br/geradorBoleto/Gerarerrr.do");
            } else {
                
            }

            if (cliente.getInicioRepresentacao().equals("75691.44111")) {
                httppost = new HttpPost("https://geraboleto.sicoobnet.com.br/geradorBoleto/GerarBoleto.do");
            } else {
                byte[] x = Hash("0200400000000000000000000510201600000000015005000123456789124");
                String zz = x.toString();
                
                //httppost = new HttpPost("https://des.barramento.caixa.gov.br/sibar/ConsultaCobrancaBancaria/Boleto?wsdl");             
            }

            if (1 == 1){
                retorno.put("status", false);
                retorno.put("mensagem", "Teste não completado!");
                return retorno;
            }
            
            List<NameValuePair> params = new ArrayList(2);

            params.add(new BasicNameValuePair("coopCartao", bol.getAgencia())); // Cooperativa do Cliente coopCartao N X 4 - Cooperativas do sistema SICOOB 3260
            params.add(new BasicNameValuePair("numCliente", bol.getCodigoCedente())); // Número do Cliente na Coop. numCliente N X 10 - Clientes cadastrados na cooperativa *** 70000 ou 68659 *** condominios rp
            params.add(new BasicNameValuePair("dataEmissao", bol.getDataLancamentoString().substring(6, 10) + bol.getDataLancamentoString().substring(3, 5) + bol.getDataLancamentoString().substring(0, 2))); // Data da Emissão do Boleto dataEmissao N X 8 aaaammdd Informação do Cliente 20080521
            params.add(new BasicNameValuePair("codTipoVencimento", "1")); // Código do Tipo de Vencimento codTipoVencimento N X 1 - Legenda no Item 7.1 1 - // 1 - NORMAL, 2 - A VISTA, 3 - CONTRA APRENSENTAÇÃO 
            params.add(new BasicNameValuePair("dataVencimentoTit", bol.getDataVencimentoString().substring(6, 10) + bol.getDataVencimentoString().substring(3, 5) + bol.getDataVencimentoString().substring(0, 2))); // Data de Vencimento do Título dataVencimentoTit N X 8 aaaammdd Informação do Cliente 20080621
            params.add(new BasicNameValuePair("valorTitulo", Moeda.converteR$Float(bol.getValor()).replace(".", "").replace(",", "."))); // Valor do Título valorTitulo N X 9 - Informação do Cliente 325.63
            params.add(new BasicNameValuePair("numContaCorrente", bol.getConta())); // Número da Conta Corrente numContaCorrente N X 10 - C/C do Cliente na Cooperativa 700003029
            params.add(new BasicNameValuePair("codEspDocumento", bol.getEspecieDocumento())); // Espécie Documento codEspDocumento A X 3 - Legenda no Item 7.2 DM
            params.add(new BasicNameValuePair("nomeSacado", bol.getContribuinte().getPessoa().getNome())); // Nome do Sacado nomeSacado A X 50 - Informação do Cliente Diego Neri
            params.add(new BasicNameValuePair("cpfCGC", bol.getContribuinte().getPessoa().getDocumento())); // CPF/CNPJ do Sacado cpfCGC A X 14 - Informação do Cliente 11111111111
            params.add(new BasicNameValuePair("endereco", bol.getContribuinte().getPessoa().getEndereco())); // Endereço do Sacado endereco A X 40 - Informação do Cliente Rua 15 de maio
            params.add(new BasicNameValuePair("bairro", bol.getContribuinte().getPessoa().getBairro())); // Bairro do Sacado bairro A X 15 - Informação do Cliente Ponta Verde
            params.add(new BasicNameValuePair("cidade", bol.getContribuinte().getPessoa().getCidade())); // Cidade do Sacado cidade A X 15 - Informação do Cliente Brasília
            params.add(new BasicNameValuePair("cep", bol.getContribuinte().getPessoa().getCep())); // CEP do Sacado cep A X 8 - Informação do Cliente 58108130
            params.add(new BasicNameValuePair("uf", bol.getContribuinte().getPessoa().getUf())); // UF do Sacado uf A X 2 - Informação do Cliente DF
            params.add(new BasicNameValuePair("codMunicipio", cliente.getCodigoMunicipio())); // Código do Município do Sacado codMunicipio N X - Informação do Cliente 1009
            //params.add(new BasicNameValuePair("codMunicipio", "29751")); // Código do Município do Sacado codMunicipio N X - Informação do Cliente 1009
            params.add(new BasicNameValuePair("chaveAcessoWeb", cliente.getChaveAcessoBanco())); // Chave de Acesso chaveAcessoWeb A X 36 - Informação gerada pela Cooperativa DFFF3ADD-7880-4A28-8413-91EDD1DBE2E1
            //params.add(new BasicNameValuePair("chaveAcessoWeb", "B049844D-C11D-4F5E-9D2F-87E0596304E6")); // Chave de Acesso chaveAcessoWeb A X 36 - Informação gerada pela Cooperativa DFFF3ADD-7880-4A28-8413-91EDD1DBE2E1

            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF8"));

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                //System.out.println(result);
                //if (result.contains("75691.44111")) {
                if (result.contains(cliente.getInicioRepresentacao())) {
                    retorno.put("status", true);
                    retorno.put("mensagem", result);
                    String nb = result;

                    Integer index = 0;
                    int pos1 = nb.indexOf(cliente.getInicioRepresentacao(), index);
                    nb = nb.substring(pos1, pos1 + 58);
                    nb = nb.replace(" ", "").replace(".", "");
                    nb = nb.subSequence(21, 28).toString();

                    bol.setNossoNumeroBanco(nb);

                    Dao dao = new Dao();
                    dao.begin();
                    dao.update(bol);
                    dao.commit();
                } else {
                    retorno.put("status", false);
                    String noHTMLString = result.replaceAll("\\<.*?\\>", "");
                    noHTMLString = noHTMLString.replaceAll("\\/\\*([\\S\\s]+?)\\*\\/", "");
                    noHTMLString = noHTMLString.replaceAll("(?s)/\\*.*?\\*/", "");
                    noHTMLString = noHTMLString.replaceAll("<.*?>", "");
                    noHTMLString = noHTMLString.replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "");
                    noHTMLString = noHTMLString.replaceAll("\\\\r", "");
                    noHTMLString = noHTMLString.replaceAll("\\\\n", "");
                    noHTMLString = noHTMLString.replaceAll("\\\\t", "");
                    String[] tokens = noHTMLString.split(" ");
                    noHTMLString = "";
                    for (String token : tokens) {
                        if (!token.trim().isEmpty()) {
                            if (!token.trim().equals("Erro")) {
                                if (!token.trim().contains("Entre em contato com o administrador do sistema.") && !token.trim().contains("Ocorreu um erro no sistema!")) {
                                    noHTMLString += token.trim() + " ";
                                }
                            }
                        }
                    }
                    noHTMLString = noHTMLString.replace("Entre em contato com o administrador do sistema.", "");
                    noHTMLString = noHTMLString.replace("Ocorreu um erro no sistema!", "");
                    noHTMLString = noHTMLString.trim();
                    if (result.contains("Ocorreu um erro no sistema")) {
                        if (result.contains("O campo [CPF/CNPJ] deve ter um valor válido")) {
                            retorno.put("mensagem", "Digite um CPF/CNPJ válido");
                        } else if (result.contains("O campo [Data de Vencimento] deve ter um valor válido e maior ou igual a [Data de Emissão]")) {
                            retorno.put("mensagem", "Data de Vencimento deve ser maior que ou igual a Data de Hoje");
                        } else if (result.contains("O campo [CEP] deve ter um valor válido")) {
                            retorno.put("mensagem", "Digite um CEP");
                        } else {
                            retorno.put("mensagem", noHTMLString);
                        }
                    } else if (noHTMLString.contains("404")) {
                        retorno.put("mensagem", noHTMLString);
                    } else {
                        retorno.put("mensagem", "Ocorreu um erro no sistema");
                    }
                }
                return retorno;
            }
        } catch (IOException | UnsupportedOperationException e) {
            System.out.println(e);

            retorno.put("status", false);
            retorno.put("mensagem", e.getMessage());
            return retorno;
        }
        return retorno;
    }

    public String imprimir_boleto_contribuinte_padrao_nao_util(String chave_cliente, String nosso_numero) {
        try {
            Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
            if (cliente == null) {
                // CLIENTE NÃO ENCONTRADO
                return "Cliente não encontrado";
            }

            BoletoDao bdao = new BoletoDao();

            Boleto bol = bdao.pesquisaBoletoNossoNumero(chave_cliente, nosso_numero);
            if (bol == null) {
                return "Boleto não encontrado";
            }

            String url = "https://geraboleto.sicoobnet.com.br/geradorBoleto/GerarBoleto.do";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            String urlParameters
                    = "coopCartao=" + bol.getAgencia() // Cooperativa do Cliente coopCartao N X 4 - Cooperativas do sistema SICOOB 3260
                    + "&numCliente=" + bol.getCodigoCedente() // Número do Cliente na Coop. numCliente N X 10 - Clientes cadastrados na cooperativa *** 70000 ou 68659 *** condominios rp
                    + "&dataEmissao=" + bol.getDataLancamentoString().substring(6, 10) + bol.getDataLancamentoString().substring(3, 5) + bol.getDataLancamentoString().substring(0, 2) // Data da Emissão do Boleto dataEmissao N X 8 aaaammdd Informação do Cliente 20080521
                    + "&codTipoVencimento=1" // Código do Tipo de Vencimento codTipoVencimento N X 1 - Legenda no Item 7.1 1 - // 1 - NORMAL, 2 - A VISTA, 3 - CONTRA APRENSENTAÇÃO 
                    + "&dataVencimentoTit=" + bol.getDataVencimentoString().substring(6, 10) + bol.getDataVencimentoString().substring(3, 5) + bol.getDataVencimentoString().substring(0, 2) // Data de Vencimento do Título dataVencimentoTit N X 8 aaaammdd Informação do Cliente 20080621
                    + "&valorTitulo=" + Moeda.converteR$Float(bol.getValor()).replace(".", "").replace(",", ".") // Valor do Título valorTitulo N X 9 - Informação do Cliente 325.63
                    + "&numContaCorrente=" + bol.getConta() // Número da Conta Corrente numContaCorrente N X 10 - C/C do Cliente na Cooperativa 700003029
                    + "&codEspDocumento=" + bol.getEspecieDocumento() // Espécie Documento codEspDocumento A X 3 - Legenda no Item 7.2 DM
                    + "&nomeSacado=" + bol.getContribuinte().getPessoa().getNome() // Nome do Sacado nomeSacado A X 50 - Informação do Cliente Diego Neri
                    + "&cpfCGC=" + bol.getContribuinte().getPessoa().getDocumento() // CPF/CNPJ do Sacado cpfCGC A X 14 - Informação do Cliente 11111111111
                    + "&endereco=" + bol.getContribuinte().getPessoa().getEndereco() // Endereço do Sacado endereco A X 40 - Informação do Cliente Rua 15 de maio
                    + "&bairro=" + bol.getContribuinte().getPessoa().getBairro() // Bairro do Sacado bairro A X 15 - Informação do Cliente Ponta Verde
                    + "&cidade=" + bol.getContribuinte().getPessoa().getCidade() // Cidade do Sacado cidade A X 15 - Informação do Cliente Brasília
                    + "&cep=" + bol.getContribuinte().getPessoa().getCep() // CEP do Sacado cep A X 8 - Informação do Cliente 58108130
                    + "&uf=" + bol.getContribuinte().getPessoa().getUf() // UF do Sacado uf A X 2 - Informação do Cliente DF
                    + "&codMunicipio=29751" // Código do Município do Sacado codMunicipio N X - Informação do Cliente 1009
                    + "&chaveAcessoWeb=B049844D-C11D-4F5E-9D2F-87E0596304E6"; // Chave de Acesso chaveAcessoWeb A X 36 - Informação gerada pela Cooperativa DFFF3ADD-7880-4A28-8413-91EDD1DBE2E1

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("\nPost parameters : " + urlParameters);
            System.out.println("\nResponse Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }

        return "";
    }

    public Boleto pesquisar_boleto_contribuinte(String chave_cliente, String nosso_numero) {
        chave_cliente = "dsf46sdf4da98dgf4ae98gf4afea";
        Cliente cliente = new ClienteDao().pesquisaClienteChave(chave_cliente);
        if (cliente == null) {
            // CLIENTE NÃO ENCONTRADO
            return null;
        }

        BoletoDao bdao = new BoletoDao();
        return bdao.pesquisaBoletoNossoNumero(chave_cliente, nosso_numero);
    }
}
