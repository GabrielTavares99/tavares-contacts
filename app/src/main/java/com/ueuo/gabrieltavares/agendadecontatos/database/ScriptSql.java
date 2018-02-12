package com.ueuo.gabrieltavares.agendadecontatos.database;

public class ScriptSql {

    public static String getCreateContato() {

        //Arruma a junçaõ das nossas string sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(
                "CREATE TABLE IF NOT EXISTS tb_contato(\n" +
                        "\t_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT\n" +
                        "\t,nome VARCHAR(200)\n" +
                        "\t,telefone VARCHAR(14)\n" +
                        "\t,tipoTelefone INTEGER(2)\n" +
                        "\t,email VARCHAR(255)\n" +
                        "\t,tipoEmail INTEGER(2)\n" +
                        "\t,endereco VARCHAR(255)\n" +
                        "\t,tipoEndereco INTEGER(2)\n" +
                        "\t,dataEspecial DATE\n" +
                        "\t,tipoDataEspecial INTEGER(2)\n" +
                        "\t,grupo VARCHAR(255)\n" +
                        "\t,caminhoFoto TEXT" +
                        ");"
        );

        return sqlBuilder.toString();
    }

    public static String gerarRegistros(){
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("INSERT INTO tb_contato (nome, telefone, )");
        return stringBuilder.toString();
    }

}
