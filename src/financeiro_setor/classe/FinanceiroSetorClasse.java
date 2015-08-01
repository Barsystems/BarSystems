/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.classe;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorClasse {
    
    private Long id;
    private String nome;
    private String tipo; //despesa ou receita
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    @Override
    public String toString() {
        return "FinanceiroSetorClasse{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", padrao=" + padrao + '}';
    }
    
}
