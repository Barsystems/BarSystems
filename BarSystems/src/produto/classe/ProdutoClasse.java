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
    private Long id_setor;
    private String setor;

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

    @Override
    public String toString() {
        return "ProdutoClasse{" + "id=" + id + ", nome=" + nome + ", valor_compra=" + valor_compra + ", valor_venda=" + valor_venda + ", id_setor=" + id_setor + ", setor=" + setor + '}';
    }
    
}
