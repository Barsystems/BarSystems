/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems;

import barsystems.Login.Frm_Login;
import barsystems.Venda.Painel_Venda;
import barsystems.compras.Painel_compra;
import barsystems.estoque.Class_estoque;
import barsystems.estoque.Painel_estoque;
import barsystems.fornecedores.Painel_Fornecedores;
import barsystems.produtos.Painel_Produtos;
import barsystems.usuarios.Painel_Usuarios;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class Principal extends javax.swing.JFrame {

    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }
    
    public void gerarTamanhoTela() {
        
        
        
    }
    
    /**
     * Este método vai verificar se um determinado menu já está aberto, para não o abrirmos novamente
     * @param Aba Nome da aba queserá verificada se já está aberta
     * @return Se retornar >= 0 quer dizer que o menu já foi aberto e o valor de retorno é o index no painel
     */
    public int verificaMenuAberto(String Aba) {
        
        String nomeAba;
        int flag = -1;
        for (int i = 0; i < painel_principal.getTabCount(); i++)
        {
            nomeAba = painel_principal.getTitleAt(i);
            if (nomeAba.equals(Aba))
            {
                flag = i;
                i = painel_principal.getTabCount();
            }
        }
        
        return flag;
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuCadastros = new javax.swing.JMenu();
        menuItemCadastroUsuarios = new javax.swing.JMenuItem();
        menuItemCadastroProdutos = new javax.swing.JMenuItem();
        menuItemCadastroFornecedores = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuSistema = new javax.swing.JMenu();
        menuItemTrocarUsuario = new javax.swing.JMenuItem();
        menuItemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("BarSystem - Sistema gerenciador de bares e restaurantes - Versão 0.1.1");
        setExtendedState(MAXIMIZED_BOTH);
        setName("Principal"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        painel_principal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenuBar1.setToolTipText("");

        jMenu1.setText("Configurações");
        jMenu1.setToolTipText("");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/three115.png"))); // NOI18N
        jMenuItem1.setText("Configurações do sistema");
        jMenuItem1.setToolTipText("Alterar as configurações do sistema");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        menuCadastros.setText("Cadastros");
        menuCadastros.setToolTipText("");
        menuCadastros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuCadastros.setName("menu_Produto"); // NOI18N

        menuItemCadastroUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuItemCadastroUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/business60.png"))); // NOI18N
        menuItemCadastroUsuarios.setText("Usuários");
        menuItemCadastroUsuarios.setToolTipText("Cadastrar usuários");
        menuItemCadastroUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroUsuariosActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastroUsuarios);

        menuItemCadastroProdutos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuItemCadastroProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/hot51.png"))); // NOI18N
        menuItemCadastroProdutos.setText("Produtos");
        menuItemCadastroProdutos.setToolTipText("Cadastrar produtos");
        menuItemCadastroProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroProdutosActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastroProdutos);

        menuItemCadastroFornecedores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuItemCadastroFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/grocery10.png"))); // NOI18N
        menuItemCadastroFornecedores.setText("Fornecedores");
        menuItemCadastroFornecedores.setToolTipText("Cadastrar fornecedores");
        menuItemCadastroFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroFornecedoresActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastroFornecedores);

        jMenuBar1.add(menuCadastros);

        jMenu3.setText("Estoque");
        jMenu3.setToolTipText("");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/boxes2.png"))); // NOI18N
        jMenuItem2.setText("Centros de estoque");
        jMenuItem2.setToolTipText("Cadastrar e controlar centros de estoque");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/add17.png"))); // NOI18N
        jMenuItem4.setText("Compras");
        jMenuItem4.setToolTipText("Realizar uma compra");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Bar");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenuItem8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/covered16.png"))); // NOI18N
        jMenuItem8.setText("Venda");
        jMenuItem8.setToolTipText("Realizar uma venda");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Relatórios");
        jMenu6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu6);

        menuSistema.setText("Sistema");
        menuSistema.setToolTipText("");
        menuSistema.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        menuItemTrocarUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuItemTrocarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/group12.png"))); // NOI18N
        menuItemTrocarUsuario.setText("Trocar de usuário");
        menuItemTrocarUsuario.setToolTipText("Abre a tela de login para escolher um usuário");
        menuItemTrocarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTrocarUsuarioActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemTrocarUsuario);

        menuItemSair.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        menuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        menuItemSair.setText("Sair");
        menuItemSair.setToolTipText("Sair do sistema");
        menuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSairActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemSair);

        jMenuBar1.add(menuSistema);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel_principal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemCadastroProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroProdutosActionPerformed
        
        int index = verificaMenuAberto("Produtos");
        if (index >= 0)
        {
            painel_principal.setSelectedIndex(index);
        }
        else
        {
            Painel_Produtos cadastraProdutos = new Painel_Produtos(this, painel_principal);
            painel_principal.add("Produtos", cadastraProdutos);
            cadastraProdutos.setBounds(0, 0, 500, 500);
            cadastraProdutos.setVisible(true);
            painel_principal.setSelectedIndex(painel_principal.getTabCount()-1);
            cadastraProdutos.refreshList();
        }
        
    }//GEN-LAST:event_menuItemCadastroProdutosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        Class_Fechar_Sistema exit = new Class_Fechar_Sistema();
        exit.fecharSistema();
        
    }//GEN-LAST:event_formWindowClosing

    private void menuItemCadastroFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroFornecedoresActionPerformed
        
        int index = verificaMenuAberto("Fornecedores");
        if (index >= 0)
        {
            painel_principal.setSelectedIndex(index);
        }
        else
        {
            Painel_Fornecedores cadastraFornecedores = new Painel_Fornecedores(this, painel_principal);
            painel_principal.add("Fornecedores", cadastraFornecedores);
            cadastraFornecedores.setBounds(0, 0, 500, 500);
            cadastraFornecedores.setVisible(true);
            // select the last tab
            painel_principal.setSelectedIndex(painel_principal.getTabCount()-1);
            cadastraFornecedores.refreshList();
        }
        
    }//GEN-LAST:event_menuItemCadastroFornecedoresActionPerformed

    private void menuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSairActionPerformed
        
        Class_Fechar_Sistema exit = new Class_Fechar_Sistema();
        exit.fecharSistema();
        
    }//GEN-LAST:event_menuItemSairActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        
        Painel_estoque estoque = new Painel_estoque();
        painel_principal.add(estoque);
        estoque.setBounds(0, 0, 500, 500);
        estoque.setVisible(true);
        estoque.refreshList();
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuItemTrocarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTrocarUsuarioActionPerformed
        
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente trocar de usuário?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) 
        {
            Frm_Login login = new Frm_Login();
            login.setVisible(true);

            this.dispose();
        }
        
    }//GEN-LAST:event_menuItemTrocarUsuarioActionPerformed

    private void menuItemCadastroUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroUsuariosActionPerformed
        
        int index = verificaMenuAberto("Usuários");
        if (index >= 0)
        {
            painel_principal.setSelectedIndex(index);
        }
        else
        {
            Painel_Usuarios usuarios = new Painel_Usuarios(this, painel_principal);
            painel_principal.add("Usuários", usuarios);
            usuarios.setBounds(0, 0, 500, 500);
            usuarios.setVisible(true);
            painel_principal.setSelectedIndex(painel_principal.getTabCount()-1);
            usuarios.refreshList();
        }
        
    }//GEN-LAST:event_menuItemCadastroUsuariosActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        
        int index = verificaMenuAberto("Vendas");
        if (index >= 0)
        {
            painel_principal.setSelectedIndex(index);
        }
        else
        {
            Painel_Venda venda = new Painel_Venda(this, painel_principal);
            painel_principal.add("Vendas", venda);
            venda.setBounds(0, 0, 1000, 700);
            venda.setVisible(true);
            painel_principal.setSelectedIndex(painel_principal.getTabCount()-1);
        }
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Painel_compra compra = new Painel_compra();
        painel_principal.add(compra);
        compra.setBounds(0, 0, 500, 500);
        compra.setVisible(true);
        compra.refreshTable();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenuItem menuItemCadastroFornecedores;
    private javax.swing.JMenuItem menuItemCadastroProdutos;
    private javax.swing.JMenuItem menuItemCadastroUsuarios;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemTrocarUsuario;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JTabbedPane painel_principal;
    // End of variables declaration//GEN-END:variables
}
