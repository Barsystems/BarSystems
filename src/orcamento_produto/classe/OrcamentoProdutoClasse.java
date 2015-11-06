/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento_produto.classe;

/**
 *
 * @author Marcos
 */
public class OrcamentoProdutoClasse {
    
    private Long id_orcamento;
    private Long id_produto;
    private String nome_produto;
    private float quantidade;
    private float valor_cobrado;
    private String tipo_medida;

    public Long getId_orcamento() {
        return id_orcamento;
    }

    public void setId_orcamento(Long id_orcamento) {
        this.id_orcamento = id_orcamento;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor_cobrado() {
        return valor_cobrado;
    }

    public void setValor_cobrado(float valor_cobrado) {
        this.valor_cobrado = valor_cobrado;
    }

    public String getTipo_medida() {
        return tipo_medida;
    }

    public void setTipo_medida(String tipo_medida) {
        this.tipo_medida = tipo_medida;
    }
    
}
