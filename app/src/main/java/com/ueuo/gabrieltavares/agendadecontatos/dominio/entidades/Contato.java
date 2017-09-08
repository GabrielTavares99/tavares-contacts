package com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gabri on 21/04/2017.
 */
//implementei serializable pra poder transmitir esse objeto como parametro pra outra activity
public class Contato implements Serializable{

    private long id;
    private String nome;
    private String telefone;
    private Integer tipoTelefone;
    private String email;
    private Integer tipoEmail;
    private String endereco;
    private Integer tipoEndereco;
    private Date dataEspecial;
    private Integer tipoDataEspecial;
    private String grupo;
    private String caminhoFoto;

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

    public Integer getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(Integer tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(Integer tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(Integer tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Date getDataEspecial() {
        return dataEspecial;
    }

    public void setDataEspecial(Date dataEspecial) {
        this.dataEspecial = dataEspecial;
    }

    public Integer getTipoDataEspecial() {
        return tipoDataEspecial;
    }

    public void setTipoDataEspecial(Integer tipoDataEspecial) {
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
        return this.nome + " " + this.getTelefone();
    }


    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
