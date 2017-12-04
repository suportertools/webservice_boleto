/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Datas {

    public static Date dataHoje() {
        Date dateTime = new Date();
        return dateTime;
    }

    public static String data() {
        Date dateTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String a = dateFormat.format(dateTime);
        return a;
    }

    public static String converteData(Date data) {
        if (data != null) {
            String a = data.toString();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String b = dateFormat.format(data);
            return b;
        } else {
            return "";
        }
    }

    public static String converteData(String data) {
        if (!data.isEmpty()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(data);
        } else {
            return "";
        }
    }

    public static Date converte(String data) {
        if (data != null) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.parse(data);
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static int qtdeDiasDoMes(int mes, int ano) {
        int dias = 0;
        if ((mes == 1)
                || (mes == 3)
                || (mes == 5)
                || (mes == 7)
                || (mes == 8)
                || (mes == 10)
                || (mes == 12)) {
            return 31;
        } else if ((mes == 4)
                || (mes == 6)
                || (mes == 9)
                || (mes == 11)) {
            return 30;
        } else if (mes == 2) {
            if (isBisexto(ano)) {
                return 29;
            } else {
                return 28;
            }
        }

        return dias;
    }

    public static boolean isBisexto(int ano) {
        if ((ano % 4) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String hora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        return sdf.format(gc.getTime());
    }

    public static String horaMinuto() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        return sdf.format(gc.getTime());
    }

    public static String dataExtenso(String data) {
        return dataExtenso(data, 0);
    }

    /**
     * Tipo
     *
     * @param data
     * @param tipo 0 = dia extenso/mes extenso/ano extenso/ ; 1 = mes/ano ; 2 -
     * ano; 3 - dia/mes extenso/ano extenso/
     * @return
     */
    public static String dataExtenso(String data, int tipo) {
        String extenso;
        try {
            String dia = data.substring(0, 2);
            String mes = data.substring(3, 5);
            String ano = data.substring(6, 10);
            if (tipo != 3) {
                switch (Integer.parseInt(dia)) {
                    case 1:
                        dia = "Primeiro";
                        break;
                    case 2:
                        dia = "Dois";
                        break;
                    case 3:
                        dia = "Três";
                        break;
                    case 4:
                        dia = "Quatro";
                        break;
                    case 5:
                        dia = "Cinco";
                        break;
                    case 6:
                        dia = "Seis";
                        break;
                    case 7:
                        dia = "Sete";
                        break;
                    case 8:
                        dia = "Oito";
                        break;
                    case 9:
                        dia = "Nove";
                        break;
                    case 10:
                        dia = "Dez";
                        break;
                    case 11:
                        dia = "Onze";
                        break;
                    case 12:
                        dia = "Doze";
                        break;
                    case 13:
                        dia = "Treze";
                        break;
                    case 14:
                        dia = "Quatorze";
                        break;
                    case 15:
                        dia = "Quinze";
                        break;
                    case 16:
                        dia = "Dezesseis";
                        break;
                    case 17:
                        dia = "Dezessete";
                        break;
                    case 18:
                        dia = "Dezoito";
                        break;
                    case 19:
                        dia = "Dezenove";
                        break;
                    case 20:
                        dia = "Vinte";
                        break;
                    case 21:
                        dia = "Vinte e Um";
                        break;
                    case 22:
                        dia = "Vinte e Dois";
                        break;
                    case 23:
                        dia = "Vinte e Três";
                        break;
                    case 24:
                        dia = "Vinte e Quatro";
                        break;
                    case 25:
                        dia = "Vinte e Cinco";
                        break;
                    case 26:
                        dia = "Vinte e Seis";
                        break;
                    case 27:
                        dia = "Vinte e Sete";
                        break;
                    case 28:
                        dia = "Vinte e Oito";
                        break;
                    case 29:
                        dia = "Vinte e Nove";
                        break;
                    case 30:
                        dia = "Trinta";
                        break;
                    case 31:
                        dia = "Trinta e Um";
                        break;
                }
            }
            switch (Integer.parseInt(mes)) {
                case 1:
                    mes = "Janeiro";
                    break;
                case 2:
                    mes = "Fevereiro";
                    break;
                case 3:
                    mes = "Março";
                    break;
                case 4:
                    mes = "Abril";
                    break;
                case 5:
                    mes = "Maio";
                    break;
                case 6:
                    mes = "Junho";
                    break;
                case 7:
                    mes = "Julho";
                    break;
                case 8:
                    mes = "Agosto";
                    break;
                case 9:
                    mes = "Setembro";
                    break;
                case 10:
                    mes = "Outubro";
                    break;
                case 11:
                    mes = "Novembro";
                    break;
                case 12:
                    mes = "Dezembro";
                    break;
            }
            if (tipo == 2) {
                extenso = ano;
            } else if (tipo == 1) {
                extenso = mes + " de " + ano;
            } else {
                extenso = dia + " de " + mes + " de " + ano;
            }
        } catch (NumberFormatException e) {
            extenso = data;

        }
        return extenso;
    }

    public static int calcularIdade(String dataNascimento) {
        int idade = 0;
        if (isDataValida(dataNascimento)) {
            int dN = converteDataParaInteger(dataNascimento);
            int dH = converteDataParaInteger(data());
            if (dN >= dH) {
                return 0;
            }
            String dataHoje = Datas.data();
            int[] dataH = Datas.DataToArrayInt(dataHoje);
            int[] dataN = Datas.DataToArrayInt(dataNascimento);
            idade = dataH[2] - dataN[2];
            int[] novaData = Datas.DataToArrayInt(incrementarAnos(idade, dataNascimento));
            if (dataH[1] < novaData[1]) {
                idade--;
            } else if (dataH[1] == novaData[1]) {
                if (dataH[0] < dataH[0]) {
                    idade--;
                }
            }
        }
        return idade;
    }

    public int calcularIdade(Date data) {
        if (data == null) {
            return 0;
        }
        return calcularIdade(Datas.converteData(data));
    }

    public static boolean isDataValida(String input) {
        return dataValidaConverte(input) != null;
    }

    public static Date dataValidaConverte(String input) {
        List<SimpleDateFormat> dateFormats = new ArrayList();
        dateFormats.add(new SimpleDateFormat("M/dd/yyyy"));
        dateFormats.add(new SimpleDateFormat("dd.M.yyyy"));
        dateFormats.add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
        dateFormats.add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
        dateFormats.add(new SimpleDateFormat("dd.MMM.yyyy"));
        dateFormats.add(new SimpleDateFormat("dd-MMM-yyyy"));
        dateFormats.add(new SimpleDateFormat("dd/MM/yyyy"));
        Date date = null;
        if (null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                //Shhh.. try other formats
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }

    public static int converteDataParaInteger(String data) {
        if (isDataValida(data)) {
            return Integer.parseInt(Datas.DataToArrayString(data)[2] + Datas.DataToArrayString(data)[1] + Datas.DataToArrayString(data)[0]);
        }
        return 0;
    }

    public static String[] DataToArrayString(String data) {
        String[] result = new String[3];
        result[0] = data.substring(0, 2);
        result[1] = data.substring(3, 5);
        result[2] = data.substring(6, 10);
        return result;
    }

    public static int[] DataToArrayInt(String data) {
        int[] result = new int[3];
        result[0] = Integer.parseInt(data.substring(0, 2));
        result[1] = Integer.parseInt(data.substring(3, 5));
        result[2] = Integer.parseInt(data.substring(6, 10));
        return result;
    }

    public static int[] DataToArrayInt(Date data) {
        int[] result = new int[3];
        result[0] = Integer.parseInt(converteData(data).substring(0, 2));
        result[1] = Integer.parseInt(converteData(data).substring(3, 5));
        result[2] = Integer.parseInt(converteData(data).substring(6, 10));
        return result;
    }
    

    public static String incrementarAnos(int qtd, String data) {
        if (isDataValida(data)) {
            try {
                if (qtd <= 0) {
                    qtd = 1;
                }
                int[] d = Datas.DataToArrayInt(data);
                d[2] = d[2] + qtd;
                if ((d[1] == 2) && ((d[0] == 28) || (d[0] == 29))) {
                    if (isBisexto(d[2])) {
                        d[0] = 29;
                    } else {
                        d[0] = 28;
                    }
                }
                return mascararData(d[0] + "/" + d[1] + "/" + d[2]);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }   
    
    public static String mascararData(String data) {
        return Datas.converteData(Datas.converte(data));
    }    
    
    public static String diaSemanaString(String data) {
        if (data.isEmpty()) {
            return "";
        }
        try {
            Date d = Datas.converte(data);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(d);
            int DAY_OF_WEEK = calendar.get(Calendar.DAY_OF_WEEK);
            switch (DAY_OF_WEEK) {
                case 1:
                    return "Domingo";
                case 2:
                    return "Segunda-Feira";
                case 3:
                    return "Terça-Feira";
                case 4:
                    return "Quarta-Feira";
                case 5:
                    return "Quinta-Feira";
                case 6:
                    return "Sexta-Feira";
                case 7:
                    return "Sábado";
            }
        } catch (Exception e) {
            return "";
        }
        return "";

    }
}
