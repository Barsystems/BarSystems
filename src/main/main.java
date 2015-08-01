/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import login.janela.LoginJanela;

/**
 *
 * @author Marcos
 */
public class main {
    
    public static void main(String args[]) {
        
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        //LOOK AND FEEL DISPONÍVEIS
        
        //javax.swing.plaf.nimbus.NimbusLookAndFeel  
        //com.sun.java.swing.plaf.motif.MotifLookAndFeel  
        //com.sun.java.swing.plaf.windows.WindowsLookAndFeel  
        //com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel 
        
        new LoginJanela().setVisible(true);
    }
    
}
