
package barsystems.Centros_Custo;

import barsystems.Principal;
import javax.swing.JTabbedPane;

public class Painel_Centros_Custo extends javax.swing.JPanel {

    int id_usuario;
    String nome_usuario;
     
    public Painel_Centros_Custo(Principal principal, JTabbedPane painel_principal, int id_usuario, String usuario) {
        initComponents();
        
        this.id_usuario = id_usuario;
        this.nome_usuario = usuario;
    }
    
    public void adicionaCentrosCusto() {
        painel_centros_custo.setBounds(0, 0, 2000, 2000);
        Class_Centros_Custo centros_custo = new Class_Centros_Custo();
        centros_custo.getCentrosCusto(painel_centros_custo, id_usuario, nome_usuario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel_centros_custo = new javax.swing.JTabbedPane();

        setLayout(null);

        painel_centros_custo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        add(painel_centros_custo);
        painel_centros_custo.setBounds(0, 0, 540, 360);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane painel_centros_custo;
    // End of variables declaration//GEN-END:variables
}
