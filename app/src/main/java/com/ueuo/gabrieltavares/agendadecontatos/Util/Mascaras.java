package com.ueuo.gabrieltavares.agendadecontatos.Util;

import android.text.BoringLayout;

/**
 * Created by gabri on 04/05/2017.
 */

public class Mascaras {


    public String mascaraTelefone(String telefone){

        char vetor_novo_telefone[] = new char[16];
        vetor_novo_telefone[0]= '(';
        vetor_novo_telefone[3]=')';
        vetor_novo_telefone[4]=' ';
        vetor_novo_telefone[6]='.';
        vetor_novo_telefone[10]='-';



       // if (telefone.length() <= 11){
       //     char vetor_telefone[] = telefone.toCharArray();

       //     int cont = 0;

       //     for (int i =0;i<vetor_novo_telefone.length;i++){
       //         if (Character.isDefined(vetor_novo_telefone[i]) ){
          //          try {
        //                vetor_novo_telefone[i] = vetor_telefone[cont];
            //            cont++;
              //      }catch (Exception e){
                //
                  //  }

                //}

          //}
        //}

        //String novo_telefone = String.copyValueOf(vetor_novo_telefone);

        return telefone;
    }

}
