/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.classe;

/**
 *
 * @author Marcos
 */
public class ProdutoClasse {
    
    private Long id;
    private String nome;
    private float valor_compra;
    private float valor_venda;
    private float valor_comissao;
    private Long id_setor;
    private String setor;
    private String tipo_medida;

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

    public float getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(float valor_compra) {
        this.valor_compra = valor_compra;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }
    
    public float getValor_comissao() {
        return valor_comissao;
    }
    
    public void setValor_comissao(float valor_comissao) {
        this.valor_comissao = valor_comissao;
    }

    public Long getId_setor() {
        return id_setor;
    }

    public void setId_setor(Long id_setor) {
        this.id_setor = id_setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getTipo_medida() {
        return tipo_medida;
    }

    public void setTipo_medida(String tipo_medida) {
        this.tipo_medida = tipo_medida;
    }    

    @Override
    public String toString() {
        return "ProdutoClasse{" + "id=" + id + ", nome=" + nome + ", valor_compra=" + valor_compra + ", valor_venda=" + valor_venda + ", valor_comissao=" + valor_comissao + ", id_setor=" + id_setor + ", setor=" + setor + ", tipo_medida=" + tipo_medida + '}';
    }
    
}
