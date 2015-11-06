/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento.classe;

/**
 *
 * @author Marcos
 */
public class OrcamentoClasse {
    
    private Long id;
    private Long id_cliente;
    private String nome_cliente;
    private Long id_empresa;
    private String razao_social_empresa;
    private float valor_servico;
    private float valor_produto;
    private float desconto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getRazao_social_empresa() {
        return razao_social_empresa;
    }

    public void setRazao_social_empresa(String razao_social_empresa) {
        this.razao_social_empresa = razao_social_empresa;
    }

    public float getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(float valor_servico) {
        this.valor_servico = valor_servico;
    }

    public float getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(float valor_produto) {
        this.valor_produto = valor_produto;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

}
