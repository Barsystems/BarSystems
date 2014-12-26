/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.produtos;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Lucas
 */
public class Painel_Produtos extends javax.swing.JPanel {

    protected JTabbedPane tabela;
    protected int IndexTabela = 0;
    
    public Painel_Produtos() {
        initComponents();
        
    }
    
    public void getIndexTabela(JTabbedPane tabela, int IndexTabela) {
        this.tabela = tabela;
        this.IndexTabela = IndexTabela;
    }
    
    public void refreshList(){
        Class_produtos carrega = new Class_produtos();
        DefaultListModel lista = carrega.carregaLista();
        lista_produtos.setModel(lista);
    }
    
    public void refreshCampos(){
        Class_produtos seleciona = new Class_produtos();
        seleciona.carregaProduto((String) lista_produtos.getSelectedValue());
        codigo_produto1.setText(seleciona.getCodigo());
        nome_produto1.setText(seleciona.getDescricao());
        tipo_produto1.setText(seleciona.getTipo());
        valor_compra_produto1.setText(seleciona.getValor_Compra());
        valor_venda_produto1.setText(seleciona.getValor_Venda());
    }
    
    public void carregaEditar(){
        Class_produtos seleciona = new Class_produtos();
        seleciona.carregaProduto((String) lista_produtos.getSelectedValue());
        codigo_produto2.setText(seleciona.getCodigo());
        nome_produto2.setText(seleciona.getDescricao());
        tipo_produto2.setText(seleciona.getTipo());
        valor_compra_produto2.setText(seleciona.getValor_Compra());
        valor_venda_produto2.setText(seleciona.getValor_Venda());
    }
    
    public void refreshPesquisa(String nome){
        Class_produtos carrega = new Class_produtos();
        DefaultListModel lista = carrega.pesquisa(nome);
        lista_produtos.setModel(lista);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cadastro_produtos = new javax.swing.JDialog();
        jLabel15 = new javax.swing.JLabel();
        codigo_produto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        nome_produto = new javax.swing.JTextField();
        tipo_produto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        valor_compra_produto = new javax.swing.JTextField();
        valor_venda_produto = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        editar_produtos = new javax.swing.JDialog();
        jLabel25 = new javax.swing.JLabel();
        codigo_produto2 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        nome_produto2 = new javax.swing.JTextField();
        tipo_produto2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        valor_compra_produto2 = new javax.swing.JTextField();
        valor_venda_produto2 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_produtos = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        codigo_produto1 = new javax.swing.JTextField();
        nome_produto1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tipo_produto1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        valor_compra_produto1 = new javax.swing.JTextField();
        valor_venda_produto1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        cadastro_produtos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cadastro_produtos.setTitle("Novo cadastro");
        cadastro_produtos.setModal(true);
        cadastro_produtos.setPreferredSize(new java.awt.Dimension(400, 400));
        cadastro_produtos.getContentPane().setLayout(null);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Codigo");
        cadastro_produtos.getContentPane().add(jLabel15);
        jLabel15.setBounds(120, 80, 45, 17);

        codigo_produto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        codigo_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_produtoActionPerformed(evt);
            }
        });
        cadastro_produtos.getContentPane().add(codigo_produto);
        codigo_produto.setBounds(180, 70, 160, 30);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Nome");
        cadastro_produtos.getContentPane().add(jLabel17);
        jLabel17.setBounds(130, 120, 44, 17);

        nome_produto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastro_produtos.getContentPane().add(nome_produto);
        nome_produto.setBounds(180, 110, 160, 30);

        tipo_produto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastro_produtos.getContentPane().add(tipo_produto);
        tipo_produto.setBounds(180, 150, 160, 30);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText(" Tipo ");
        cadastro_produtos.getContentPane().add(jLabel18);
        jLabel18.setBounds(130, 160, 36, 17);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Valor de compra");
        cadastro_produtos.getContentPane().add(jLabel13);
        jLabel13.setBounds(60, 200, 112, 17);

        valor_compra_produto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastro_produtos.getContentPane().add(valor_compra_produto);
        valor_compra_produto.setBounds(180, 190, 160, 30);

        valor_venda_produto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastro_produtos.getContentPane().add(valor_venda_produto);
        valor_venda_produto.setBounds(180, 230, 160, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Valor de venda");
        cadastro_produtos.getContentPane().add(jLabel16);
        jLabel16.setBounds(70, 240, 104, 17);

        jButton8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/white65.png"))); // NOI18N
        jButton8.setText("Salvar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        cadastro_produtos.getContentPane().add(jButton8);
        jButton8.setBounds(40, 340, 100, 30);

        jButton9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/drawing4.png"))); // NOI18N
        jButton9.setText("Limpar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        cadastro_produtos.getContentPane().add(jButton9);
        jButton9.setBounds(150, 340, 100, 30);

        jButton10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        jButton10.setText("Sair");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        cadastro_produtos.getContentPane().add(jButton10);
        jButton10.setBounds(260, 340, 100, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/snacks.png"))); // NOI18N
        jLabel3.setText("Cadastrar novo produto");
        cadastro_produtos.getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 25, 397, 29);

        editar_produtos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        editar_produtos.setTitle("Alteração de dados");
        editar_produtos.setModal(true);
        editar_produtos.setPreferredSize(new java.awt.Dimension(400, 400));
        editar_produtos.getContentPane().setLayout(null);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Codigo");
        editar_produtos.getContentPane().add(jLabel25);
        jLabel25.setBounds(120, 80, 53, 17);

        codigo_produto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        codigo_produto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_produto2ActionPerformed(evt);
            }
        });
        editar_produtos.getContentPane().add(codigo_produto2);
        codigo_produto2.setBounds(180, 70, 160, 30);

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("Nome ");
        editar_produtos.getContentPane().add(jLabel26);
        jLabel26.setBounds(130, 120, 40, 17);

        nome_produto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        editar_produtos.getContentPane().add(nome_produto2);
        nome_produto2.setBounds(180, 110, 160, 30);

        tipo_produto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        editar_produtos.getContentPane().add(tipo_produto2);
        tipo_produto2.setBounds(180, 150, 160, 30);

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText(" Tipo ");
        editar_produtos.getContentPane().add(jLabel28);
        jLabel28.setBounds(130, 160, 36, 17);

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Valor de compra");
        editar_produtos.getContentPane().add(jLabel29);
        jLabel29.setBounds(60, 200, 112, 17);

        valor_compra_produto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        editar_produtos.getContentPane().add(valor_compra_produto2);
        valor_compra_produto2.setBounds(180, 190, 160, 30);

        valor_venda_produto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        editar_produtos.getContentPane().add(valor_venda_produto2);
        valor_venda_produto2.setBounds(180, 230, 160, 30);

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Valor de venda");
        editar_produtos.getContentPane().add(jLabel30);
        jLabel30.setBounds(70, 240, 104, 17);

        jButton11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/white65.png"))); // NOI18N
        jButton11.setText("Salvar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        editar_produtos.getContentPane().add(jButton11);
        jButton11.setBounds(100, 340, 100, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/snacks.png"))); // NOI18N
        jLabel4.setText("Editar produto");
        editar_produtos.getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 20, 400, 29);

        jButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        jButton5.setText("Sair");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        editar_produtos.getContentPane().add(jButton5);
        jButton5.setBounds(210, 340, 100, 30);

        setLayout(null);

        lista_produtos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lista_produtos.setSelectionBackground(new java.awt.Color(204, 255, 255));
        lista_produtos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        lista_produtos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_produtosMouseClicked(evt);
            }
        });
        lista_produtos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_produtosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista_produtos);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 230, 230);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/snacks.png"))); // NOI18N
        jLabel1.setText("Manutenção do cadastro de produtos");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 600, 29);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Produtos cadastrados");
        add(jLabel2);
        jLabel2.setBounds(20, 100, 160, 17);

        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/plus24.png"))); // NOI18N
        jButton2.setText("Novo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(80, 390, 100, 30);

        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/drawing4.png"))); // NOI18N
        jButton3.setText("Alterar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(190, 390, 100, 30);

        jButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/prohibition9.png"))); // NOI18N
        jButton4.setText("Excluir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(300, 390, 100, 30);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Codigo ");
        add(jLabel19);
        jLabel19.setBounds(350, 130, 50, 17);

        codigo_produto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        codigo_produto1.setEnabled(false);
        codigo_produto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_produto1ActionPerformed(evt);
            }
        });
        add(codigo_produto1);
        codigo_produto1.setBounds(410, 120, 160, 30);

        nome_produto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nome_produto1.setEnabled(false);
        add(nome_produto1);
        nome_produto1.setBounds(410, 160, 160, 30);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Nome");
        add(jLabel20);
        jLabel20.setBounds(360, 170, 36, 17);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("  Tipo ");
        add(jLabel22);
        jLabel22.setBounds(360, 210, 40, 17);

        tipo_produto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tipo_produto1.setEnabled(false);
        add(tipo_produto1);
        tipo_produto1.setBounds(410, 200, 160, 30);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("Valor de compra");
        add(jLabel23);
        jLabel23.setBounds(290, 250, 110, 17);

        valor_compra_produto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        valor_compra_produto1.setEnabled(false);
        add(valor_compra_produto1);
        valor_compra_produto1.setBounds(410, 240, 160, 30);

        valor_venda_produto1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        valor_venda_produto1.setEnabled(false);
        add(valor_venda_produto1);
        valor_venda_produto1.setBounds(410, 280, 160, 30);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("Valor de venda");
        add(jLabel24);
        jLabel24.setBounds(300, 290, 104, 17);

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(410, 390, 100, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void codigo_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_produtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_produtoActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(codigo_produto.getText().equals("") ||
            nome_produto.getText().equals("") ||
            tipo_produto.getText().equals("") ||
            valor_compra_produto.getText().equals("") ||
            valor_venda_produto.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Nao pode haver campos vazios");
        }
        else{
            Class_produtos enviar = new Class_produtos(
                codigo_produto.getText(),
                nome_produto.getText(),
                tipo_produto.getText(),
                valor_compra_produto.getText(),
                valor_venda_produto.getText()); //Instancia classe de cadastrar produtos
            boolean result = enviar.Cadastra();
            if(!result){

                JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                codigo_produto.setText("");
                nome_produto.setText("");
                tipo_produto.setText("");
                valor_compra_produto.setText("");
                valor_venda_produto.setText("");
                refreshList();
                cadastro_produtos.setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto");
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cadastro_produtos.setBounds(0, 0, 410, 430);
        cadastro_produtos.setLocationRelativeTo(null);  
        cadastro_produtos.setVisible(true);
        codigo_produto.grabFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void codigo_produto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_produto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_produto1ActionPerformed

    private void lista_produtosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_produtosMouseClicked
        refreshCampos();
    }//GEN-LAST:event_lista_produtosMouseClicked

    private void lista_produtosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_produtosValueChanged
        refreshCampos();
    }//GEN-LAST:event_lista_produtosValueChanged

    private void codigo_produto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_produto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_produto2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(codigo_produto2.getText().equals("") ||
            nome_produto2.getText().equals("") ||
            tipo_produto2.getText().equals("") ||
            valor_compra_produto2.getText().equals("") ||
            valor_venda_produto2.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Nao pode haver campos vazios");
        }
        else{
        Class_produtos editar = new Class_produtos();
        boolean result = editar.edita(codigo_produto2.getText(),
                     nome_produto2.getText(),
                     tipo_produto2.getText(),
                     valor_compra_produto2.getText(),
                     valor_venda_produto2.getText());
        if(result){
            JOptionPane.showMessageDialog(null,"Produto editado com sucesso!");
            refreshList();
            editar_produtos.setVisible(false);
        }
        else{
                JOptionPane.showMessageDialog(null, "Falha ao editar produto");
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       carregaEditar();
       editar_produtos.setBounds(0, 0, 410, 430);
       editar_produtos.setLocationRelativeTo(null);
       editar_produtos.setVisible(true);
       codigo_produto2.grabFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        editar_produtos.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        cadastro_produtos.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        codigo_produto.setText("");
                nome_produto.setText("");
                tipo_produto.setText("");
                valor_compra_produto.setText("");
                valor_venda_produto.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(!codigo_produto1.getText().equals("")){
            if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o produto "+nome_produto1.getText()+"?", "Atenção", JOptionPane.YES_NO_OPTION)==0){
                Class_produtos exclui = new Class_produtos();
                exclui.exclui(codigo_produto1.getText());
                refreshList();
                }
            }
        else
                    JOptionPane.showMessageDialog(null, "Selecione um produto!");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        tabela.remove(IndexTabela);
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog cadastro_produtos;
    private javax.swing.JTextField codigo_produto;
    private javax.swing.JTextField codigo_produto1;
    private javax.swing.JTextField codigo_produto2;
    private javax.swing.JDialog editar_produtos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lista_produtos;
    private javax.swing.JTextField nome_produto;
    private javax.swing.JTextField nome_produto1;
    private javax.swing.JTextField nome_produto2;
    private javax.swing.JTextField tipo_produto;
    private javax.swing.JTextField tipo_produto1;
    private javax.swing.JTextField tipo_produto2;
    private javax.swing.JTextField valor_compra_produto;
    private javax.swing.JTextField valor_compra_produto1;
    private javax.swing.JTextField valor_compra_produto2;
    private javax.swing.JTextField valor_venda_produto;
    private javax.swing.JTextField valor_venda_produto1;
    private javax.swing.JTextField valor_venda_produto2;
    // End of variables declaration//GEN-END:variables
}
