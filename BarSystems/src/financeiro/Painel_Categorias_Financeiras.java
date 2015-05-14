
package financeiro;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import principal.Class_Verifica_Menu_Aberto;

public class Painel_Categorias_Financeiras extends javax.swing.JPanel {
    
    protected JTabbedPane painel_principal;
    protected int id_usuario;
    protected String nome_usuario;

    public Painel_Categorias_Financeiras(JTabbedPane painel_principal, int id_usuario, String nome_usuario) {
        initComponents();
        
        this.painel_principal = painel_principal;
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
    }
    
    public void refreshList() {
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        listSetores.setModel(setores.refreshList());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Novo_Setor = new javax.swing.JDialog();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtNomeNovoSetorFinanceiro = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        comboTipoNovoSetorFinanceiro = new javax.swing.JComboBox();
        btnSalvarNovoSetorFinanceiro = new javax.swing.JButton();
        btnSairNovoSetorFinanceiro = new javax.swing.JButton();
        Alterar_Setor = new javax.swing.JDialog();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtNomeAlterarSetorFinanceiro = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        comboTipoAlterarSetorFinanceiro = new javax.swing.JComboBox();
        btnSalvarAlterarSetorFinanceiro = new javax.swing.JButton();
        btnSairAlterarSetorFinanceiro = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listSetores = new javax.swing.JList();
        jLabel41 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnNovoSetor = new javax.swing.JButton();
        btnEditarSetor = new javax.swing.JButton();
        btnExcuirSetor = new javax.swing.JButton();
        btnSairSetor = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtNomeSetor = new javax.swing.JTextField();
        txtTipoSetor = new javax.swing.JTextField();

        Novo_Setor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Novo_Setor.setTitle("Novo setor");
        Novo_Setor.setModal(true);
        Novo_Setor.setResizable(false);
        Novo_Setor.getContentPane().setLayout(null);

        jLabel47.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Categoria financeira 24px.png"))); // NOI18N
        jLabel47.setText("Novo setor financeiro");
        Novo_Setor.getContentPane().add(jLabel47);
        jLabel47.setBounds(0, 30, 390, 30);

        jLabel48.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel48.setText("Descrição");
        Novo_Setor.getContentPane().add(jLabel48);
        jLabel48.setBounds(40, 90, 70, 17);

        txtNomeNovoSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Novo_Setor.getContentPane().add(txtNomeNovoSetorFinanceiro);
        txtNomeNovoSetorFinanceiro.setBounds(130, 80, 220, 30);

        jLabel49.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel49.setText(" Tipo");
        Novo_Setor.getContentPane().add(jLabel49);
        jLabel49.setBounds(70, 130, 50, 17);

        comboTipoNovoSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboTipoNovoSetorFinanceiro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Receita", "Despesa" }));
        comboTipoNovoSetorFinanceiro.setToolTipText("Escolha se este setor será de receita ou despesa");
        Novo_Setor.getContentPane().add(comboTipoNovoSetorFinanceiro);
        comboTipoNovoSetorFinanceiro.setBounds(130, 120, 220, 30);

        btnSalvarNovoSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarNovoSetorFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarNovoSetorFinanceiro.setText("Salvar");
        btnSalvarNovoSetorFinanceiro.setToolTipText("Cadastra um novo setor financeiro");
        btnSalvarNovoSetorFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarNovoSetorFinanceiroActionPerformed(evt);
            }
        });
        Novo_Setor.getContentPane().add(btnSalvarNovoSetorFinanceiro);
        btnSalvarNovoSetorFinanceiro.setBounds(90, 200, 100, 30);

        btnSairNovoSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairNovoSetorFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairNovoSetorFinanceiro.setText("Sair");
        btnSairNovoSetorFinanceiro.setToolTipText("Cancela o cadastro do setor");
        btnSairNovoSetorFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairNovoSetorFinanceiroActionPerformed(evt);
            }
        });
        Novo_Setor.getContentPane().add(btnSairNovoSetorFinanceiro);
        btnSairNovoSetorFinanceiro.setBounds(200, 200, 100, 30);

        Alterar_Setor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Alterar_Setor.setTitle("Alterar setor");
        Alterar_Setor.setModal(true);
        Alterar_Setor.setResizable(false);
        Alterar_Setor.getContentPane().setLayout(null);

        jLabel50.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Categoria financeira 24px.png"))); // NOI18N
        jLabel50.setText("Alterar setor financeiro");
        Alterar_Setor.getContentPane().add(jLabel50);
        jLabel50.setBounds(0, 30, 390, 30);

        jLabel51.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel51.setText("Descrição");
        Alterar_Setor.getContentPane().add(jLabel51);
        jLabel51.setBounds(40, 90, 70, 17);

        txtNomeAlterarSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Alterar_Setor.getContentPane().add(txtNomeAlterarSetorFinanceiro);
        txtNomeAlterarSetorFinanceiro.setBounds(130, 80, 220, 30);

        jLabel52.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel52.setText(" Tipo");
        Alterar_Setor.getContentPane().add(jLabel52);
        jLabel52.setBounds(70, 130, 50, 17);

        comboTipoAlterarSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboTipoAlterarSetorFinanceiro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Receita", "Despesa" }));
        comboTipoAlterarSetorFinanceiro.setToolTipText("Escolha se este setor será de receita ou despesa");
        Alterar_Setor.getContentPane().add(comboTipoAlterarSetorFinanceiro);
        comboTipoAlterarSetorFinanceiro.setBounds(130, 120, 220, 30);

        btnSalvarAlterarSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarAlterarSetorFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarAlterarSetorFinanceiro.setText("Salvar");
        btnSalvarAlterarSetorFinanceiro.setToolTipText("Confirma as alterações no setor financeiro");
        btnSalvarAlterarSetorFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlterarSetorFinanceiroActionPerformed(evt);
            }
        });
        Alterar_Setor.getContentPane().add(btnSalvarAlterarSetorFinanceiro);
        btnSalvarAlterarSetorFinanceiro.setBounds(90, 200, 100, 30);

        btnSairAlterarSetorFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairAlterarSetorFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairAlterarSetorFinanceiro.setText("Sair");
        btnSairAlterarSetorFinanceiro.setToolTipText("Cancela as alterações do setor");
        btnSairAlterarSetorFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairAlterarSetorFinanceiroActionPerformed(evt);
            }
        });
        Alterar_Setor.getContentPane().add(btnSairAlterarSetorFinanceiro);
        btnSairAlterarSetorFinanceiro.setBounds(200, 200, 100, 30);

        setLayout(null);

        jLabel40.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Categoria financeira 24px.png"))); // NOI18N
        jLabel40.setText("Manutenção do cadastro de setores financeiros");
        add(jLabel40);
        jLabel40.setBounds(0, 30, 670, 30);

        listSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        listSetores.setSelectionBackground(new java.awt.Color(204, 255, 255));
        listSetores.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listSetores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSetoresValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(listSetores);

        add(jScrollPane5);
        jScrollPane5.setBounds(20, 120, 250, 240);

        jLabel41.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel41.setText("Setores cadastrados");
        add(jLabel41);
        jLabel41.setBounds(20, 100, 160, 17);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        add(jButton3);
        jButton3.setBounds(230, 90, 40, 30);

        btnNovoSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovoSetor.setText("Novo");
        btnNovoSetor.setToolTipText("Cadastra um novo setor financeiro");
        btnNovoSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoSetorActionPerformed(evt);
            }
        });
        add(btnNovoSetor);
        btnNovoSetor.setBounds(130, 410, 100, 30);

        btnEditarSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        btnEditarSetor.setText("Editar");
        btnEditarSetor.setToolTipText("Edita o cadastro de um setor existente");
        btnEditarSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarSetorActionPerformed(evt);
            }
        });
        add(btnEditarSetor);
        btnEditarSetor.setBounds(240, 410, 100, 30);

        btnExcuirSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcuirSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        btnExcuirSetor.setText("Excluir");
        btnExcuirSetor.setToolTipText("Exclui um setor financeiro");
        btnExcuirSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcuirSetorActionPerformed(evt);
            }
        });
        add(btnExcuirSetor);
        btnExcuirSetor.setBounds(350, 410, 100, 30);

        btnSairSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairSetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairSetor.setText("Sair");
        btnSairSetor.setToolTipText("Sai da tela de manutenção de setores financeiros");
        btnSairSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairSetorActionPerformed(evt);
            }
        });
        add(btnSairSetor);
        btnSairSetor.setBounds(460, 410, 100, 30);

        jLabel42.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel42.setText("Nome");
        add(jLabel42);
        jLabel42.setBounds(330, 130, 50, 17);

        jLabel43.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel43.setText("  Tipo");
        add(jLabel43);
        jLabel43.setBounds(330, 170, 40, 17);

        txtNomeSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNomeSetor.setEnabled(false);
        add(txtNomeSetor);
        txtNomeSetor.setBounds(400, 120, 250, 30);

        txtTipoSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTipoSetor.setEnabled(false);
        add(txtTipoSetor);
        txtTipoSetor.setBounds(400, 160, 250, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void listSetoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSetoresValueChanged

        Object descricao = listSetores.getSelectedValue();
        if (descricao != null) {
            Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
            txtNomeSetor.setText(descricao.toString());
            txtTipoSetor.setText(setores.retornaTipoSetor(descricao.toString()));
        } else {
            txtNomeSetor.setText("");
            txtTipoSetor.setText("");
        }

    }//GEN-LAST:event_listSetoresValueChanged

    private void btnNovoSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoSetorActionPerformed

        txtNomeNovoSetorFinanceiro.setText("");
        comboTipoNovoSetorFinanceiro.setSelectedIndex(0);
        Novo_Setor.setBounds(0, 0, 390, 300);
        Novo_Setor.setLocationRelativeTo(null);
        Novo_Setor.setVisible(true);
        txtNomeNovoSetorFinanceiro.grabFocus();

    }//GEN-LAST:event_btnNovoSetorActionPerformed

    private void btnEditarSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarSetorActionPerformed

        Object descricao = listSetores.getSelectedValue();
        if (descricao == null) {
            JOptionPane.showMessageDialog(null, "Selecione um setor para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
            txtNomeAlterarSetorFinanceiro.setText(descricao.toString());
            comboTipoAlterarSetorFinanceiro.setSelectedItem(setores.retornaTipoSetor(descricao.toString()));

            Alterar_Setor.setBounds(0, 0, 390, 300);
            Alterar_Setor.setLocationRelativeTo(null);
            Alterar_Setor.setVisible(true);
            txtNomeAlterarSetorFinanceiro.grabFocus();
        }

    }//GEN-LAST:event_btnEditarSetorActionPerformed

    private void btnExcuirSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcuirSetorActionPerformed

        Object descricao = listSetores.getSelectedValue();
        if (descricao == null) {
            JOptionPane.showMessageDialog(null, "Selecione um setor para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
            if (setores.retornaPadrao(descricao.toString()) == true) {
                JOptionPane.showMessageDialog(null, "Este é um setor financeiro padrão e não poderá ser excluído!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                    setores.excluiSetorFinanceiro(descricao.toString());
                    listSetores.setModel(setores.refreshList());
                }
            }
        }

    }//GEN-LAST:event_btnExcuirSetorActionPerformed

    private void btnSairSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairSetorActionPerformed

        Class_Verifica_Menu_Aberto verifica = new Class_Verifica_Menu_Aberto();
        int index = verifica.verificaMenuAberto(painel_principal, "Categorias financeiras   ");
        painel_principal.remove(index);

    }//GEN-LAST:event_btnSairSetorActionPerformed

    private void btnSalvarNovoSetorFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarNovoSetorFinanceiroActionPerformed

        if (txtNomeNovoSetorFinanceiro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a descrição do setor!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeNovoSetorFinanceiro.grabFocus();
        } else if (txtNomeNovoSetorFinanceiro.getText().length() > 45) {
            JOptionPane.showMessageDialog(null, "O campo descrição do setor ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeNovoSetorFinanceiro.grabFocus();
        } else {
            Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
            if (setores.verificaSetorRepetido(txtNomeNovoSetorFinanceiro.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Este setor financeiro já existe!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                setores.cadastraSetorFinanceiro(txtNomeNovoSetorFinanceiro.getText(), comboTipoNovoSetorFinanceiro.getSelectedItem().toString());
                listSetores.setModel(setores.refreshList());
                Novo_Setor.dispose();
            }
        }

    }//GEN-LAST:event_btnSalvarNovoSetorFinanceiroActionPerformed

    private void btnSairNovoSetorFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairNovoSetorFinanceiroActionPerformed

        Novo_Setor.dispose();

    }//GEN-LAST:event_btnSairNovoSetorFinanceiroActionPerformed

    private void btnSalvarAlterarSetorFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlterarSetorFinanceiroActionPerformed

        if (txtNomeAlterarSetorFinanceiro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a descrição do setor!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeAlterarSetorFinanceiro.grabFocus();
        } else if (txtNomeAlterarSetorFinanceiro.getText().length() > 45) {
            JOptionPane.showMessageDialog(null, "O campo descrição do setor ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeAlterarSetorFinanceiro.grabFocus();
        } else {
            Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
            if (setores.verificaSetorRepetido(txtNomeAlterarSetorFinanceiro.getText()) == true && !txtNomeAlterarSetorFinanceiro.getText().equals(listSetores.getSelectedValue().toString())) {
                JOptionPane.showMessageDialog(null, "Este setor financeiro já existe!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                int id_setor = setores.retornaIdSetorFinanceiro(listSetores.getSelectedValue().toString());
                setores.alteraSetorFinanceiro(txtNomeAlterarSetorFinanceiro.getText(), comboTipoAlterarSetorFinanceiro.getSelectedItem().toString(), id_setor);
                listSetores.setModel(setores.refreshList());
                Alterar_Setor.dispose();
            }
        }

    }//GEN-LAST:event_btnSalvarAlterarSetorFinanceiroActionPerformed

    private void btnSairAlterarSetorFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairAlterarSetorFinanceiroActionPerformed

        Alterar_Setor.dispose();

    }//GEN-LAST:event_btnSairAlterarSetorFinanceiroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Alterar_Setor;
    private javax.swing.JDialog Novo_Setor;
    private javax.swing.JButton btnEditarSetor;
    private javax.swing.JButton btnExcuirSetor;
    private javax.swing.JButton btnNovoSetor;
    private javax.swing.JButton btnSairAlterarSetorFinanceiro;
    private javax.swing.JButton btnSairNovoSetorFinanceiro;
    private javax.swing.JButton btnSairSetor;
    private javax.swing.JButton btnSalvarAlterarSetorFinanceiro;
    private javax.swing.JButton btnSalvarNovoSetorFinanceiro;
    private javax.swing.JComboBox comboTipoAlterarSetorFinanceiro;
    private javax.swing.JComboBox comboTipoNovoSetorFinanceiro;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList listSetores;
    private javax.swing.JTextField txtNomeAlterarSetorFinanceiro;
    private javax.swing.JTextField txtNomeNovoSetorFinanceiro;
    private javax.swing.JTextField txtNomeSetor;
    private javax.swing.JTextField txtTipoSetor;
    // End of variables declaration//GEN-END:variables
}