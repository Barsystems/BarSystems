/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.janela;

import javax.swing.JOptionPane;
import produto_setor.classe.ProdutoSetorClasse;
import produto_setor.controller.ProdutoSetorController;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorExcluir {
    
    public boolean excluir(ProdutoSetorClasse setor) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o setor "+setor.getSetor()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            ProdutoSetorController cont = new ProdutoSetorController();
            cont.removeSetor(setor.getId());
            return true;
        }
        return false;
    }
    
}
