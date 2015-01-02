

package barsystems.usuarios;

import barsystems.Principal;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class Painel_Usuarios extends javax.swing.JPanel {
    
    protected JTabbedPane painelPrincipal;
    protected Principal principal;
    
    public Painel_Usuarios(Principal principal, JTabbedPane painelPrincipal) {
        initComponents();
        this.principal = principal;
        this.painelPrincipal = painelPrincipal;
    }
    
    public void refreshList() {
        
        Class_Usuarios usuarios = new Class_Usuarios();
        DefaultListModel lista = usuarios.carregaUsuarios();
        listUsuarios.setModel(lista);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listUsuarios = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/business60 (1).png"))); // NOI18N
        jLabel1.setText("Manutenção do cadastro de usuários");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 550, 29);

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        listUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listUsuarios);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 210, 260);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Usuários cadastrados");
        add(jLabel2);
        jLabel2.setBounds(20, 100, 160, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Usuário");
        add(jLabel4);
        jLabel4.setBounds(260, 130, 60, 17);

        jTextField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1.setEnabled(false);
        add(jTextField1);
        jTextField1.setBounds(320, 120, 200, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Senha");
        add(jLabel5);
        jLabel5.setBounds(270, 170, 50, 17);

        jPasswordField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPasswordField1.setEnabled(false);
        add(jPasswordField1);
        jPasswordField1.setBounds(320, 160, 200, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Tipo");
        add(jLabel6);
        jLabel6.setBounds(280, 210, 40, 17);

        jTextField2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2.setEnabled(false);
        add(jTextField2);
        jTextField2.setBounds(320, 200, 200, 30);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/prohibition9.png"))); // NOI18N
        jButton3.setText("Excluir");
        add(jButton3);
        jButton3.setBounds(280, 430, 100, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/plus24.png"))); // NOI18N
        jButton1.setText("Novo");
        add(jButton1);
        jButton1.setBounds(60, 430, 100, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/drawing4.png"))); // NOI18N
        jButton2.setText("Alterar");
        add(jButton2);
        jButton2.setBounds(170, 430, 100, 30);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        jButton4.setText("Sair");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(390, 430, 100, 30);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/magnifier12.png"))); // NOI18N
        add(jButton6);
        jButton6.setBounds(180, 90, 49, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        int index = principal.verificaMenuAberto("Usuários");
        painelPrincipal.remove(index);
        
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JList listUsuarios;
    // End of variables declaration//GEN-END:variables
}
