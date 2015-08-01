/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.controller.CentroCustoController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class CentroCustoExcluir {
    
    public boolean excluir(CentroCustoClasse centro) {
        if (centro.getSaldo() > 0) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir um centro de estoque que possua saldo! Retire o saldo para continuar!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o centro de custo "+centro.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            CentroCustoController cont = new CentroCustoController();
            cont.removeCentroCusto(centro.getId());
            return true;
        }
        return false;
    }
    
}
