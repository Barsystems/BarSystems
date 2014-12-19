/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barsystems;

import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class Class_Fechar_Sistema {
    
    /**
     * Este método finaliza o sistema
     */
    public void fecharSistema() {
        
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Atenção", JOptionPane.YES_NO_OPTION)==0) 
        {
            System.exit(0);
        }
        
    }
    
}
