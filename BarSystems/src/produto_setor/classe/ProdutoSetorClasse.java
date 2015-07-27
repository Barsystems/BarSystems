/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.classe;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorClasse {
    
    private Long id;
    private String setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "ProdutoSetor{" + "id=" + id + ", setor=" + setor + '}';
    }
    
}
