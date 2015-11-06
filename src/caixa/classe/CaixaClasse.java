/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa.classe;

/**
 *
 * @author Marcos
 */
public class CaixaClasse {
    
    private Long id;
    private Long id_centro_custo;
    private String nome_centro_custo;
    private String data_abertura;
    private String data_fechamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_centro_custo() {
        return id_centro_custo;
    }

    public void setId_centro_custo(Long id_centro_custo) {
        this.id_centro_custo = id_centro_custo;
    }

    public String getNome_centro_custo() {
        return nome_centro_custo;
    }

    public void setNome_centro_custo(String nome_centro_custo) {
        this.nome_centro_custo = nome_centro_custo;
    }

    public String getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(String data_abertura) {
        this.data_abertura = data_abertura;
    }

    public String getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(String data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    @Override
    public String toString() {
        return "CaixaClasse{" + "id=" + id + ", id_centro_custo=" + id_centro_custo + ", nome_centro_custo=" + nome_centro_custo + ", data_abertura=" + data_abertura + ", data_fechamento=" + data_fechamento + '}';
    }
    
}
