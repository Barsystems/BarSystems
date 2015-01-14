
package barsystems.Login;

import barsystems.Class_Fechar_Sistema;
import barsystems.Principal;
import barsystems.usuarios.Class_Usuarios;
import java.awt.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Painel_Login extends javax.swing.JPanel {

    protected JFrame Frm_Login;
    
    /**
     * Creates new form Login
     * @param Frm_Login
     */
    public Painel_Login(JFrame Frm_Login) {
        initComponents();
        
        this.Frm_Login = Frm_Login;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/cutlery6 (1).png"))); // NOI18N
        jLabel2.setText("BarSystem");
        add(jLabel2);
        jLabel2.setBounds(0, 30, 400, 29);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Usuário");
        add(jLabel3);
        jLabel3.setBounds(30, 110, 50, 17);

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUsuario.setToolTipText("Insira o nome de usuário");
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        add(txtUsuario);
        txtUsuario.setBounds(90, 100, 270, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Senha");
        add(jLabel4);
        jLabel4.setBounds(30, 160, 41, 17);

        txtSenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSenha.setToolTipText("Insira a senha");
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        add(txtSenha);
        txtSenha.setBounds(90, 150, 270, 30);

        btnEntrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/white65.png"))); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.setToolTipText("Entrar no sistema");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        add(btnEntrar);
        btnEntrar.setBounds(100, 230, 100, 30);

        btnSair.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setToolTipText("Sair do sistema");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        add(btnSair);
        btnSair.setBounds(220, 230, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        
        Class_Fechar_Sistema exit = new Class_Fechar_Sistema();
        exit.fecharSistema();
        
        if (txtUsuario.getText().length() == 0)
        {
            txtUsuario.grabFocus();
        }
        else
        {
            txtSenha.grabFocus();
        }
        
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed

        if (!txtUsuario.getText().isEmpty() || !txtSenha.getText().isEmpty())
        {
            Class_Login login = new Class_Login();
            if (login.verificaLoginSenha(txtUsuario.getText(), txtSenha.getText()) == true) 
            {
                Class_Usuarios usuarios = new Class_Usuarios();
                String id_usuario = usuarios.retornaIdUsuario(txtUsuario.getText());
                Principal principal = new Principal(id_usuario, txtUsuario.getText());
                principal.setVisible(true);
                
                Frm_Login.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos! Tente novamente!", "Atenção", JOptionPane.ERROR_MESSAGE);
                if (txtUsuario.getText().length() == 0)
                {
                    txtUsuario.selectAll();
                    txtUsuario.grabFocus();
                }
                else
                {
                    txtSenha.selectAll();
                    txtSenha.grabFocus();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Insira o usuário e a senha!", "Atenção", JOptionPane.WARNING_MESSAGE);
            if (txtUsuario.getText().length() == 0)
            {
                txtUsuario.selectAll();
                txtUsuario.grabFocus();
            }
            else
            {
                txtSenha.selectAll();
                txtSenha.grabFocus();
            }
        }
        
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        
        if (evt.getKeyChar() == Event.ENTER) 
        {
            btnEntrarActionPerformed(null);
        }
        
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        
        if (evt.getKeyChar() == Event.ENTER) 
        {
            btnEntrarActionPerformed(null);
        }
        
    }//GEN-LAST:event_txtSenhaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
