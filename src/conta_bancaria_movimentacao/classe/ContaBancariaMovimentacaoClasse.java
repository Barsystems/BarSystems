/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_bancaria_movimentacao.classe;

/**
 *
 * @author Marcos
 */
public class ContaBancariaMovimentacaoClasse {
    
    Long id;
    Long id_centro_custo;
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

    public Long getId_centro_custo() {
        return id_centro_custo;
    }

    public void setId_centro_custo(Long id_centro_custo) {
        this.id_centro_custo = id_centro_custo;
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
        return "ContaBancariaMovimentacaoClasse{" + "id=" + id + ", id_centro_custo=" + id_centro_custo + ", descricao=" + descricao + ", id_forma_pagamento=" + id_forma_pagamento + ", forma_pagamento=" + forma_pagamento + ", valor=" + valor + ", tipo=" + tipo + ", data=" + data + '}';
    }
    
}
