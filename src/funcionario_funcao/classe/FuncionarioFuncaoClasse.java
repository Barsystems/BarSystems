/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.classe;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoClasse {
    
    private Long id;
    private String funcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "FuncionarioFuncaoClasse{" + "id=" + id + ", funcao=" + funcao + '}';
    }
    
}
