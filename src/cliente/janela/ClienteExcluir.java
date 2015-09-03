/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.janela;

import cliente.classe.ClienteClasse;
import cliente.controller.ClienteController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class ClienteExcluir {
    
    public boolean excluir(ClienteClasse classe) {
        boolean result = false;
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cliente "+classe.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            ClienteController cont = new ClienteController();
            cont.removeCliente(classe.getId());
            result = true;
        }
        return result;
    }
    
}
