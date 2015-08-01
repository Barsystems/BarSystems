/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.classe;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoClasse {
    
    private Long id;
    private String nome;

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

    @Override
    public String toString() {
        return "FormaPagamentoMaquinaCartaoClasse{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
