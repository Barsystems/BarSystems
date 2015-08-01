/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.classe;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTaxaCartaoClasse {
    
    private Long id_forma_pagamento;
    private Long id_maquina_cartao;
    private float taxa;

    public Long getId_forma_pagamento() {
        return id_forma_pagamento;
    }

    public void setId_forma_pagamento(Long id_forma_pagamento) {
        this.id_forma_pagamento = id_forma_pagamento;
    }

    public Long getId_maquina_cartao() {
        return id_maquina_cartao;
    }

    public void setId_maquina_cartao(Long id_maquina_cartao) {
        this.id_maquina_cartao = id_maquina_cartao;
    }

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    @Override
    public String toString() {
        return "FormaPagamentoTaxaCartaoClasse{" + "id_forma_pagamento=" + id_forma_pagamento + ", id_maquina_cartao=" + id_maquina_cartao + ", taxa=" + taxa + '}';
    }
    
}
