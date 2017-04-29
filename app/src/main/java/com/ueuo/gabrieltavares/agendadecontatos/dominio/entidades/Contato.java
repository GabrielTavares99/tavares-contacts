package com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gabri on 21/04/2017.
 */
//implementei serializable pra poder transmitir esse objeto como parametro pra outra activity
public class Contato implements Serializable{

    public static String ID = "_id";
    public static String NOME = "nome";
    public static String TELEFONE = "telefone";
    public static String TIPO_TELEFONE = "tipoTelefone";
    public static String EMAIL = "email";
    public static String TIPO_EMAIL = "tipoEmail";
    public static String ENDERECO = "endereco";
    public static String TIPO_ENDERECO = "tipoEndereco";
    public static String DATA_ESPECIAL = "dataEspecial";
    public static String TIPO_DATA_ESPECIAL = "tipoDataEspecial";
    public static String GRUPO = "grupo";


    private long id;
    private String nome;
    private String telefone;
    private String tipoTelefone;
    private String email;
    private String tipoEmail;
    private String endereco;
    private String tipoEndereco;
    private Date dataEspecial;
    private String tipoDataEspecial;
    private String grupo;

    public Contato (){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Date getDataEspecial() {
        return dataEspecial;
    }

    public void setDataEspecial(Date dataEspecial) {
        this.dataEspecial = dataEspecial;
    }

    public String getTipoDataEspecial() {
        return tipoDataEspecial;
    }

    public void setTipoDataEspecial(String tipoDataEspecial) {
        this.tipoDataEspecial = tipoDataEspecial;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }


    @Override
    public String toString() {
        return this.nome+"\t - "+this.grupo+" \n "+ this.telefone;
    }
}
