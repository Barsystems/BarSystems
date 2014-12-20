/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.produtos;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Lucas
 */
public class Painel_Produtos extends javax.swing.JPanel {

    /**
     * Creates new form CadastroProduto
     */
    public Painel_Produtos() {
        initComponents();
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
        qtd_caixa_produto1.setText(seleciona.getQuantidade());
        tipo_produto1.setText(seleciona.getTipo());
        valor_compra_produto1.setText(seleciona.getValor_Compra());
        valor_venda_produto1.setText(seleciona.getValor_Venda());
    }
    
    public void carregaEditar(){
        Class_produtos seleciona = new Class_produtos();
        seleciona.carregaProduto((String) lista_produtos.getSelectedValue());
        codigo_produto2.setText(seleciona.getCodigo());
        nome_produto2.setText(seleciona.getDescricao());
        qtd_caixa_produto2.setText(seleciona.getQuantidade());
        tipo_produto2.setText(seleciona.getTipo());
        valor_compra_produto2.setText(seleciona.getValor_Compra());
        valor_venda_produto2.setText(seleciona.getValor_Venda());
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
        qtd_caixa_produto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
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
        qtd_caixa_produto2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        codigo_produto1 = new javax.swing.JTextField();
        nome_produto1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        qtd_caixa_produto1 = new javax.swing.JTextField();
        tipo_produto1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        valor_compra_produto1 = new javax.swing.JTextField();
        valor_venda_produto1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        cadastro_produtos.setModal(true);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Codigo: ");

        codigo_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_produtoActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Nome: ");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Quantidade por Caixa:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Tipo: ");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Valor de compra: ");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Valor de venda: ");

        jButton8.setText("Salvar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Limpar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Cancelar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cadastrar novo produto");

        javax.swing.GroupLayout cadastro_produtosLayout = new javax.swing.GroupLayout(cadastro_produtos.getContentPane());
        cadastro_produtos.getContentPane().setLayout(cadastro_produtosLayout);
        cadastro_produtosLayout.setHorizontalGroup(
            cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastro_produtosLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(4, 4, 4)
                        .addComponent(codigo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(4, 4, 4)
                        .addComponent(nome_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(4, 4, 4)
                        .addComponent(qtd_caixa_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(4, 4, 4)
                        .addComponent(tipo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(4, 4, 4)
                        .addComponent(valor_compra_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(4, 4, 4)
                        .addComponent(valor_venda_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton8)
                        .addGap(6, 6, 6)
                        .addComponent(jButton9)
                        .addGap(6, 6, 6)
                        .addComponent(jButton10)))
                .addGap(51, 51, 51))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cadastro_produtosLayout.setVerticalGroup(
            cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastro_produtosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel15))
                    .addComponent(codigo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel17))
                    .addComponent(nome_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel14))
                    .addComponent(qtd_caixa_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel18))
                    .addComponent(tipo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13))
                    .addComponent(valor_compra_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastro_produtosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel16))
                    .addComponent(valor_venda_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(cadastro_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(24, 24, 24))
        );

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Codigo: ");

        codigo_produto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_produto2ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("Nome: ");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText("Quantidade por Caixa:");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText("Tipo: ");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Valor de compra: ");

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Valor de venda: ");

        jButton11.setText("Atualizar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Editar produto");

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editar_produtosLayout = new javax.swing.GroupLayout(editar_produtos.getContentPane());
        editar_produtos.getContentPane().setLayout(editar_produtosLayout);
        editar_produtosLayout.setHorizontalGroup(
            editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(editar_produtosLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(editar_produtosLayout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addGap(4, 4, 4)
                            .addComponent(codigo_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(4, 4, 4)
                            .addComponent(nome_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(4, 4, 4)
                            .addComponent(qtd_caixa_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addGap(4, 4, 4)
                            .addComponent(tipo_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addGap(4, 4, 4)
                            .addComponent(valor_compra_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(4, 4, 4)
                            .addComponent(valor_venda_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(65, Short.MAX_VALUE)))
        );
        editar_produtosLayout.setVerticalGroup(
            editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editar_produtosLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton5))
                .addGap(41, 41, 41))
            .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(editar_produtosLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel25))
                        .addComponent(codigo_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel26))
                        .addComponent(nome_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel27))
                        .addComponent(qtd_caixa_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel28))
                        .addComponent(tipo_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(17, 17, 17)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel29))
                        .addComponent(valor_compra_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(editar_produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(editar_produtosLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel30))
                        .addComponent(valor_venda_produto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        setLayout(null);

        lista_produtos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
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
        jScrollPane1.setBounds(20, 120, 295, 280);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Produtos");
        add(jLabel1);
        jLabel1.setBounds(0, 10, 640, 50);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Produtos cadastrados");
        add(jLabel2);
        jLabel2.setBounds(20, 60, 160, 17);
        add(jTextField1);
        jTextField1.setBounds(20, 80, 160, 30);

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(190, 80, 130, 30);

        jButton2.setText("Novo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(320, 370, 80, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Codigo: ");

        codigo_produto1.setEnabled(false);
        codigo_produto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_produto1ActionPerformed(evt);
            }
        });

        nome_produto1.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Nome: ");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Quantidade por Caixa:");

        qtd_caixa_produto1.setEnabled(false);

        tipo_produto1.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Tipo: ");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("Valor de compra: ");

        valor_compra_produto1.setEnabled(false);

        valor_venda_produto1.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("Valor de venda: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigo_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nome_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(8, 8, 8)
                        .addComponent(qtd_caixa_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipo_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valor_compra_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valor_venda_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(codigo_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(nome_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(qtd_caixa_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(valor_compra_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(valor_venda_produto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(320, 120, 306, 247);

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(400, 370, 80, 30);

        jButton4.setText("Excluir");
        add(jButton4);
        jButton4.setBounds(480, 370, 80, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void codigo_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_produtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_produtoActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(codigo_produto.getText().equals("") ||
            nome_produto.getText().equals("") ||
            qtd_caixa_produto.getText().equals("") ||
            tipo_produto.getText().equals("") ||
            valor_compra_produto.getText().equals("") ||
            valor_venda_produto.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Nao pode haver campos vazios");
        }
        else{
            Class_produtos enviar = new Class_produtos(
                codigo_produto.getText(),
                nome_produto.getText(),
                qtd_caixa_produto.getText(),
                tipo_produto.getText(),
                valor_compra_produto.getText(),
                valor_venda_produto.getText()); //Instancia classe de cadastrar produtos
            boolean result = enviar.Cadastra();
            if(!result){

                JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                codigo_produto.setText("");
                nome_produto.setText("");
                qtd_caixa_produto.setText("");
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
        cadastro_produtos.setBounds(0, 0, 400, 400);
        cadastro_produtos.setVisible(true);
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
            qtd_caixa_produto2.getText().equals("") ||
            tipo_produto2.getText().equals("") ||
            valor_compra_produto2.getText().equals("") ||
            valor_venda_produto2.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Nao pode haver campos vazios");
        }
        else{
        Class_produtos editar = new Class_produtos();
        boolean result = editar.edita(codigo_produto2.getText(),
                     nome_produto2.getText(),
                     qtd_caixa_produto2.getText(),
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
       editar_produtos.setBounds(0, 0, 400, 400);
       editar_produtos.setVisible(true);
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
                qtd_caixa_produto.setText("");
                tipo_produto.setText("");
                valor_compra_produto.setText("");
                valor_venda_produto.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed


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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList lista_produtos;
    private javax.swing.JTextField nome_produto;
    private javax.swing.JTextField nome_produto1;
    private javax.swing.JTextField nome_produto2;
    private javax.swing.JTextField qtd_caixa_produto;
    private javax.swing.JTextField qtd_caixa_produto1;
    private javax.swing.JTextField qtd_caixa_produto2;
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
