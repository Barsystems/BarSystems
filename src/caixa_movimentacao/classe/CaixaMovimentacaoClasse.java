/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa_movimentacao.classe;

/**
 *
 * @author Marcos
 */
public class CaixaMovimentacaoClasse {
    
    Long id;
    Long id_caixa;
    String descricao;
    Long id_forma_pagamento;
    String forma_pagamento;
    float valor;
    String tipo;
    String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(Long id_caixa) {
        this.id_caixa = id_caixa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId_forma_pagamento() {
        return id_forma_pagamento;
    }

    public void setId_forma_pagamento(Long id_forma_pagamento) {
        this.id_forma_pagamento = id_forma_pagamento;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CaixaMovimentacaoClasse{" + "id=" + id + ", id_caixa=" + id_caixa + ", descricao=" + descricao + ", id_forma_pagamento=" + id_forma_pagamento + ", forma_pagamento=" + forma_pagamento + ", valor=" + valor + ", tipo=" + tipo + ", data=" + data + '}';
    }
    
}
