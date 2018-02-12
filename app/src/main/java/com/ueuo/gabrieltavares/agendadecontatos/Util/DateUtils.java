package com.ueuo.gabrieltavares.agendadecontatos.Util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    String dataFormatada;

    public Date data(int year,int month, int dayOfMonth){

        //Classe calendário
        Calendar calendar = Calendar.getInstance();

        //Seto as variaveis do ano, mes, dia
        calendar.set(year,month,dayOfMonth);

        //Objeto do tipo Date
        Date date = calendar.getTime();

        //Classe de formatacao da minha data
        //Medium 12 de outubro de 2019
        //Shor 1/87/7
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

        return date;
    }

    public String dataToString(int year,int month, int dayOfMonth){

        //Classe calendário
        Calendar calendar = Calendar.getInstance();

        //Seto as variaveis do ano, mes, dia
        calendar.set(year,month,dayOfMonth);

        //Objeto do tipo Date
        Date date = calendar.getTime();

        //Classe de formatacao da minha data
        //Medium 12 de outubro de 2019
        //Shor 1/87/7
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

        //Formato a data
       dataFormatada = dateFormat.format(date);

        return dataFormatada;
    }

    public String dataToString(Date date){

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

        //Formato a data
        dataFormatada = dateFormat.format(date);

        return dataFormatada;
    }

}
