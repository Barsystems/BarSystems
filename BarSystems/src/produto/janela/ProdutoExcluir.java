/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.janela;

import javax.swing.JOptionPane;
import produto.classe.ProdutoClasse;
import produto.controller.ProdutoController;

/**
 *
 * @author Marcos
 */
public class ProdutoExcluir {
    
    public boolean excluir(ProdutoClasse prod) {
        boolean result = false;
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o produto "+prod.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            ProdutoController cont = new ProdutoController();
            cont.removeProduto(prod.getId());
            result = true;
        }
        return result;
    }
    
}
