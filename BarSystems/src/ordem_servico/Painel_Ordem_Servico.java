
package ordem_servico;

import principal.Principal;
import javax.swing.JTabbedPane;

public class Painel_Ordem_Servico extends javax.swing.JPanel {

    protected Principal principal;
    protected JTabbedPane painelPrincipal;
    
    public Painel_Ordem_Servico(Principal principal, JTabbedPane painelPrincipal) {
        initComponents();
        
        this.principal = principal;
        this.painelPrincipal = painelPrincipal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel_principal = new javax.swing.JTabbedPane();

        setLayout(null);
        add(painel_principal);
        painel_principal.setBounds(0, 0, 490, 310);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane painel_principal;
    // End of variables declaration//GEN-END:variables
}
