/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.janela;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import funcionario_funcao.controller.FuncionarioFuncaoController;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoExcluir extends JDialog {
    
    public boolean excluir(FuncionarioFuncaoClasse func) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a função "+func.getFuncao()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            FuncionarioFuncaoController cont = new FuncionarioFuncaoController();
            cont.removeFuncao(func.getId());
            return true;
        }
        return false;
    }
    
}
