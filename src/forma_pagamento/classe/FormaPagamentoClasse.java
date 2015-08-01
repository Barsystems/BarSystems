/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.classe;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoClasse {
    
    private Long id;
    private String nome;
    private Long id_tipo;
    private String tipo;
    private int dias_recebimento;
    private boolean padrao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Long id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDias_recebimento() {
        return dias_recebimento;
    }

    public void setDias_recebimento(int dias_recebimento) {
        this.dias_recebimento = dias_recebimento;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    @Override
    public String toString() {
        return "FormaPagamentoClasse{" + "id=" + id + ", nome=" + nome + ", id_tipo_fk=" + id_tipo + ", tipo=" + tipo + ", dias_recebimento=" + dias_recebimento + ", padrao=" + padrao + '}';
    }
    
}
