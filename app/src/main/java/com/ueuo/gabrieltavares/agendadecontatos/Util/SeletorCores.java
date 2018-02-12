package com.ueuo.gabrieltavares.agendadecontatos.Util;

import com.ueuo.gabrieltavares.agendadecontatos.R;


public class SeletorCores {

    public int setarCor(String letraInicial){

        int verde = R.drawable.forma_contato_verde;
        int azul = R.drawable.forma_contato_azul;
        int rosa = R.drawable.forma_contato_rosa;
        int laranja = R.drawable.forma_contato_laranja;
        int vermelho = R.drawable.forma_contato_vermelho;

        switch (letraInicial){
            case "A":
                return verde;
            case "F":
                return verde;
            case "K":
                return verde;
            case "P":
                return verde;
            case "U":
                return verde;
            case "Z":
                return azul;
            case "B":
                return azul;
            case "G":
                return azul;
            case "L":
                return azul;
            case "Q":
                return azul;
            case "V":
                return rosa;
            case "C":
                return rosa;
            case "H":
                return rosa;
            case "M":
                return rosa;
            case "R":
                return rosa;
            case "W":
                return vermelho;
            case "D":
                return vermelho;
            case "I":
                return vermelho;
            case "N":
                return vermelho;
            case "S":
                return vermelho;
            case "X":
                return laranja;
            case "E":
                return laranja;
            case "J":
                return laranja;
            case "O":
                return laranja;
            case "T":
                return laranja;
            case "Y":
                return laranja;
            default:
                return vermelho;
        }

    }


}
