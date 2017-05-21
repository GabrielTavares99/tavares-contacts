package com.ueuo.gabrieltavares.agendadecontatos.Util;

import com.ueuo.gabrieltavares.agendadecontatos.R;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

/**
 * Created by gabri on 04/05/2017.
 */

public class SeletorCores {

    public SeletorCores(){

    }

    public int setarCor(String letraInicial){

        switch (letraInicial){
            case "A":
                return R.color.verde;
            case "F":
                return R.color.verde;
            case "K":
                return R.color.verde;
            case "P":
                return R.color.verde;
            case "U":
                return R.color.verde;
            case "Z":
                return R.color.verde;
            case "B":
                return R.color.azul;
            case "G":
                return R.color.azul;
            case "L":
                return R.color.azul;
            case "Q":
                return R.color.azul;
            case "V":
                return R.color.azul;
            case "C":
                return R.color.rosa;
            case "H":
                return R.color.rosa;
            case "M":
                return R.color.rosa;
            case "R":
                return R.color.rosa;
            case "W":
                return R.color.rosa;
            case "D":
                return R.color.vermelho;
            case "I":
                return R.color.vermelho;
            case "N":
                return R.color.vermelho;
            case "S":
                return R.color.vermelho;
            case "X":
                return R.color.vermelho;
            case "E":
                return R.color.laranja;
            case "J":
                return R.color.laranja;
            case "O":
                return R.color.laranja;
            case "T":
                return R.color.laranja;
            case "Y":
                return R.color.laranja;
            default:
                return R.color.colorPrimary;
        }

    }


}
