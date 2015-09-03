/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.janela;

import centro_estoque.classe.CentroEstoqueClasse;
import centro_estoque.controller.CentroEstoqueController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class CentroEstoqueExcluir {
    
    public boolean excluir(CentroEstoqueClasse classe) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o centro de estoque "+classe.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            CentroEstoqueController cont = new CentroEstoqueController();
            cont.removeCentroEstoque(classe.getId());
            return true;
        }
        return false;
    }
    
}
