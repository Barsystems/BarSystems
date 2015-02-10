/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.compras;

import barsystems.estoque.Class_estoque;
import java.awt.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Lucas
 */
public class Painel_compra extends javax.swing.JPanel {

    /**
     * Creates new form Painel_compra
     */
    public Painel_compra() {
        initComponents();
    }
    
    public void refreshTable(){
        Class_compra resolve = new Class_compra();
        resolve.carregaTabela((DefaultTableModel) tabela_compra.getModel());
    }
    
    public void refreshCampos(String codigo){
        Class_compra carrega = new Class_compra();
        carrega.carregaDados(codigo);
        codigo_compra.setText(carrega.getId_compra());
        descricao_compra.setText(carrega.getDescricao());
        fornecedor_compra.setText(carrega.getFornecedor());
        data_compra.setText(carrega.getData());
        numero_nota_compra.setText(carrega.getNumero_nota());
        valor_total_compra.setText(carrega.getValor());
        responsavel_compra.setText(carrega.getResponsavel());
    }
    
    public void refreshListTipo(){
        Class_compra carrega = new Class_compra();
        lista_tipo.setModel(carrega.carregaTipo());
    }
    
    public void refreshListProdutos(String tipo){
        Class_compra carrega = new Class_compra();
        lista_produto.setModel(carrega.carregaListaProduto(tipo));
    }
    
    public void refreshCombo_Fornecedor(){
        combo_fornecedor.removeAllItems();
        List lista;
         Class_compra resolve = new Class_compra();
        lista = resolve.carrega_Fornecedor();
        
        for(int i=0;i<lista.getItemCount();i++){
            combo_fornecedor.addItem(lista.getItem(i));
        }
    }
    
    public void refresh_subValor(){
        Class_compra resolve = new Class_compra(); 
        String valor;
        valor = resolve.carrega_valor((String) lista_produto.getSelectedValue());
        int  b, c;
        float a,resultado;
        a = Float.parseFloat(valor);
        b = Integer.parseInt( quantidade_por_caixa.getText() );
        c = Integer.parseInt( quantidade_de_caixas.getText() );
        resultado = a * b * c;

        sub_valor.setText(Float.toString(resultado));
    }
    
    public void refreshFormaPagamento(){
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("A vista");
        listModel.addElement("Cartao");
        listModel.addElement("Parcelado");
        listModel.addElement("Boleto");
        List_forma_pagamento.setModel(listModel);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nova_compra = new javax.swing.JDialog();
        descricao_nova = new javax.swing.JTextField();
        combo_fornecedor = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        data_comprado = new javax.swing.JTextField();
        n_nota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        List_forma_pagamento = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lista_tipo = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        lista_produto = new javax.swing.JList();
        pesquisa_produto = new javax.swing.JTextField();
        quantidade_de_caixas = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        sub_valor = new javax.swing.JTextField();
        pesquisa_tipo = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabela_adcionados = new javax.swing.JTable();
        quantidade_por_caixa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        lblProdutosCadastrados = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        codigo_compra = new javax.swing.JTextField();
        descricao_compra = new javax.swing.JTextField();
        fornecedor_compra = new javax.swing.JTextField();
        numero_nota_compra = new javax.swing.JTextField();
        data_compra = new javax.swing.JTextField();
        responsavel_compra = new javax.swing.JTextField();
        valor_total_compra = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela_compra = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_produtos_comprado = new javax.swing.JTable();

        nova_compra.setBounds(new java.awt.Rectangle(0, 0, 600, 600));
        nova_compra.setMinimumSize(new java.awt.Dimension(600, 600));
        nova_compra.setModal(true);
        nova_compra.getContentPane().setLayout(null);

        descricao_nova.setText("Descricacao da compra");
        descricao_nova.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descricao_novaFocusGained(evt);
            }
        });
        descricao_nova.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descricao_novaMouseClicked(evt);
            }
        });
        descricao_nova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricao_novaActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(descricao_nova);
        descricao_nova.setBounds(20, 10, 280, 30);

        combo_fornecedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_fornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_fornecedorItemStateChanged(evt);
            }
        });
        combo_fornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_fornecedorMouseClicked(evt);
            }
        });
        nova_compra.getContentPane().add(combo_fornecedor);
        combo_fornecedor.setBounds(20, 60, 270, 20);

        jLabel4.setText("Fornecedor");
        nova_compra.getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 40, 70, 14);

        data_comprado.setText("Data");
        data_comprado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                data_compradoMouseClicked(evt);
            }
        });
        data_comprado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data_compradoActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(data_comprado);
        data_comprado.setBounds(20, 90, 120, 30);

        n_nota.setText("Nº da nota");
        n_nota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n_notaMouseClicked(evt);
            }
        });
        n_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_notaActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(n_nota);
        n_nota.setBounds(160, 90, 140, 30);

        List_forma_pagamento.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(List_forma_pagamento);

        nova_compra.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(350, 310, 530, 90);

        jLabel7.setText("Forma de pagamento");
        nova_compra.getContentPane().add(jLabel7);
        jLabel7.setBounds(350, 290, 110, 14);

        jLabel8.setText("Parcelas");
        nova_compra.getContentPane().add(jLabel8);
        jLabel8.setBounds(350, 410, 50, 14);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList2);

        nova_compra.getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(350, 430, 530, 90);

        jButton5.setText("Adcionar parcela");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(jButton5);
        jButton5.setBounds(680, 530, 200, 23);

        jButton6.setText("Remover parcela");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(jButton6);
        jButton6.setBounds(350, 530, 210, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        nova_compra.getContentPane().add(jPanel1);
        jPanel1.setBounds(330, 10, 10, 530);

        jLabel9.setText("Adcionar produtos à compra");
        nova_compra.getContentPane().add(jLabel9);
        jLabel9.setBounds(100, 130, 150, 14);

        lista_tipo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista_tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_tipoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(lista_tipo);

        nova_compra.getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(10, 190, 310, 70);

        lista_produto.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_produtoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lista_produto);

        nova_compra.getContentPane().add(jScrollPane6);
        jScrollPane6.setBounds(10, 310, 310, 90);

        pesquisa_produto.setText("Pesquisar produto");
        pesquisa_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pesquisa_produtoMouseClicked(evt);
            }
        });
        nova_compra.getContentPane().add(pesquisa_produto);
        pesquisa_produto.setBounds(10, 270, 160, 30);

        quantidade_de_caixas.setText("1");
        quantidade_de_caixas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantidade_de_caixasMouseClicked(evt);
            }
        });
        quantidade_de_caixas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantidade_de_caixasKeyReleased(evt);
            }
        });
        nova_compra.getContentPane().add(quantidade_de_caixas);
        quantidade_de_caixas.setBounds(10, 430, 140, 30);

        jButton7.setText("Adicionar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(jButton7);
        jButton7.setBounds(10, 520, 310, 30);

        sub_valor.setEditable(false);
        sub_valor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sub_valor.setText("0.00");
        nova_compra.getContentPane().add(sub_valor);
        sub_valor.setBounds(180, 410, 140, 100);

        pesquisa_tipo.setText("Pesquisar tipo do produto");
        pesquisa_tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pesquisa_tipoMouseClicked(evt);
            }
        });
        pesquisa_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_tipoActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(pesquisa_tipo);
        pesquisa_tipo.setBounds(10, 150, 160, 30);

        tabela_adcionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "QtD caixa", "qtd p/caixa", "descricao", "fornecedor", "v unitario", "v caixa", "total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tabela_adcionados);
        if (tabela_adcionados.getColumnModel().getColumnCount() > 0) {
            tabela_adcionados.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabela_adcionados.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabela_adcionados.getColumnModel().getColumn(2).setPreferredWidth(240);
            tabela_adcionados.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabela_adcionados.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabela_adcionados.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabela_adcionados.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        nova_compra.getContentPane().add(jScrollPane7);
        jScrollPane7.setBounds(350, 20, 770, 230);

        quantidade_por_caixa.setText("Quantidade por caixa");
        quantidade_por_caixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantidade_por_caixaMouseClicked(evt);
            }
        });
        quantidade_por_caixa.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                quantidade_por_caixaInputMethodTextChanged(evt);
            }
        });
        quantidade_por_caixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantidade_por_caixaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantidade_por_caixaKeyTyped(evt);
            }
        });
        nova_compra.getContentPane().add(quantidade_por_caixa);
        quantidade_por_caixa.setBounds(10, 480, 140, 30);

        jLabel3.setText("Quantidade do produto por caixa");
        nova_compra.getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 460, 170, 14);

        jLabel5.setText("Quantidade de caixas compradas");
        nova_compra.getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 410, 170, 14);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Total: ");
        nova_compra.getContentPane().add(jLabel6);
        jLabel6.setBounds(890, 260, 70, 30);

        lbl_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total.setText("0,0");
        nova_compra.getContentPane().add(lbl_total);
        lbl_total.setBounds(1000, 260, 120, 30);

        jButton8.setText("Finalizar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(jButton8);
        jButton8.setBounds(890, 320, 160, 60);

        setLayout(null);

        lblProdutosCadastrados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblProdutosCadastrados.setText("Compras cadastradas");
        add(lblProdutosCadastrados);
        lblProdutosCadastrados.setBounds(20, 100, 160, 17);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/snacks.png"))); // NOI18N
        jLabel1.setText("Manutenção do cadastro de compras");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 600, 29);

        codigo_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        codigo_compra.setEnabled(false);
        codigo_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_compraActionPerformed(evt);
            }
        });
        add(codigo_compra);
        codigo_compra.setBounds(430, 130, 160, 30);

        descricao_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        descricao_compra.setEnabled(false);
        add(descricao_compra);
        descricao_compra.setBounds(430, 170, 160, 30);

        fornecedor_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fornecedor_compra.setEnabled(false);
        add(fornecedor_compra);
        fornecedor_compra.setBounds(430, 210, 160, 30);

        numero_nota_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        numero_nota_compra.setEnabled(false);
        add(numero_nota_compra);
        numero_nota_compra.setBounds(430, 250, 160, 30);

        data_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        data_compra.setEnabled(false);
        add(data_compra);
        data_compra.setBounds(430, 370, 160, 30);

        responsavel_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        responsavel_compra.setEnabled(false);
        add(responsavel_compra);
        responsavel_compra.setBounds(430, 330, 160, 30);

        valor_total_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        valor_total_compra.setEnabled(false);
        add(valor_total_compra);
        valor_total_compra.setBounds(430, 290, 160, 30);

        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/plus24.png"))); // NOI18N
        jButton2.setText("Novo");
        jButton2.setToolTipText("Cadastrar um novo produto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(240, 440, 100, 30);

        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/drawing4.png"))); // NOI18N
        jButton3.setText("Alterar");
        jButton3.setToolTipText("Alterar as informações de um produto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(350, 440, 100, 30);

        jButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/prohibition9.png"))); // NOI18N
        jButton4.setText("Excluir");
        jButton4.setToolTipText("Excluir um produto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(460, 440, 100, 30);

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barsystems/imagens/man349.png"))); // NOI18N
        jButton1.setText("Sair");
        jButton1.setToolTipText("Fechar a tela de cadastro de produtos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(570, 440, 100, 30);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("Data:");
        add(jLabel24);
        jLabel24.setBounds(370, 380, 50, 17);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("Numero da nota");
        add(jLabel23);
        jLabel23.setBounds(310, 260, 110, 17);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Fornecedor");
        add(jLabel22);
        jLabel22.setBounds(340, 220, 80, 17);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Descricacao");
        add(jLabel20);
        jLabel20.setBounds(340, 180, 78, 17);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Codigo ");
        add(jLabel19);
        jLabel19.setBounds(370, 140, 50, 17);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Responsavel");
        add(jLabel25);
        jLabel25.setBounds(330, 340, 90, 17);

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("Valor Total");
        add(jLabel26);
        jLabel26.setBounds(350, 300, 80, 17);

        jLabel2.setText("Produtos comprados nessa compra");
        add(jLabel2);
        jLabel2.setBounds(620, 110, 180, 14);

        tabela_compra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descricao", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_compraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabela_compra);
        if (tabela_compra.getColumnModel().getColumnCount() > 0) {
            tabela_compra.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabela_compra.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela_compra.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        add(jScrollPane3);
        jScrollPane3.setBounds(20, 130, 280, 270);

        tabela_produtos_comprado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabela_produtos_comprado);
        if (tabela_produtos_comprado.getColumnModel().getColumnCount() > 0) {
            tabela_produtos_comprado.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela_produtos_comprado.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        add(jScrollPane2);
        jScrollPane2.setBounds(620, 130, 320, 270);
    }// </editor-fold>//GEN-END:initComponents

    private void codigo_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_compraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_compraActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        refreshListTipo();
        refreshCombo_Fornecedor();
        refreshListProdutos("Cerveja");
        nova_compra.setBounds(0, 0, 1180, 600);
        nova_compra.setLocationRelativeTo(null);
        nova_compra.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabela_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_compraMouseClicked
         int linha;
        linha = tabela_compra.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tabela_compra.getModel();
        refreshCampos((String) modelo.getValueAt(linha,0));
        Class_compra amostra = new Class_compra();
        amostra.carregaProdutos_Compra((String) modelo.getValueAt(linha,0), (DefaultTableModel) tabela_produtos_comprado.getModel());
    }//GEN-LAST:event_tabela_compraMouseClicked

    private void descricao_novaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricao_novaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricao_novaActionPerformed

    private void n_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_n_notaActionPerformed

    private void data_compradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_compradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_data_compradoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void pesquisa_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_tipoActionPerformed

    private void descricao_novaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descricao_novaFocusGained
        
    }//GEN-LAST:event_descricao_novaFocusGained

    private void descricao_novaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descricao_novaMouseClicked
        if(descricao_nova.getText().equals("Descricacao da compra")){
            descricao_nova.setText("");
        }
    }//GEN-LAST:event_descricao_novaMouseClicked

    private void lista_tipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_tipoMouseClicked
        refreshListProdutos((String) lista_tipo.getSelectedValue());
    }//GEN-LAST:event_lista_tipoMouseClicked

    private void pesquisa_tipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisa_tipoMouseClicked
        if(pesquisa_tipo.getText().equals("Pesquisar tipo do produto")){
            pesquisa_tipo.setText("");
        }
    }//GEN-LAST:event_pesquisa_tipoMouseClicked

    private void pesquisa_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisa_produtoMouseClicked
        if(pesquisa_produto.getText().equals("Pesquisar produto")){
            pesquisa_produto.setText("");
        }
    }//GEN-LAST:event_pesquisa_produtoMouseClicked

    private void quantidade_por_caixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantidade_por_caixaMouseClicked
        if(quantidade_por_caixa.getText().equals("Quantidade por caixa")){
            quantidade_por_caixa.setText("");
        }
    }//GEN-LAST:event_quantidade_por_caixaMouseClicked

    private void quantidade_de_caixasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantidade_de_caixasMouseClicked
         if(quantidade_de_caixas.getText().equals("Quantidade em caixa")){
            quantidade_de_caixas.setText("");
        }
    }//GEN-LAST:event_quantidade_de_caixasMouseClicked

    private void data_compradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_data_compradoMouseClicked
         if(data_comprado.getText().equals("Data")){
            data_comprado.setText("");
        }
    }//GEN-LAST:event_data_compradoMouseClicked

    private void n_notaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n_notaMouseClicked
        if(n_nota.getText().equals("Nº da nota")){
                   n_nota.setText("");
        }
    }//GEN-LAST:event_n_notaMouseClicked

    private void quantidade_por_caixaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_quantidade_por_caixaInputMethodTextChanged
       
    }//GEN-LAST:event_quantidade_por_caixaInputMethodTextChanged

    private void quantidade_por_caixaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidade_por_caixaKeyTyped

    }//GEN-LAST:event_quantidade_por_caixaKeyTyped

    private void quantidade_por_caixaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidade_por_caixaKeyReleased
        refresh_subValor();
    }//GEN-LAST:event_quantidade_por_caixaKeyReleased

    private void quantidade_de_caixasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidade_de_caixasKeyReleased
        refresh_subValor();
    }//GEN-LAST:event_quantidade_de_caixasKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        DefaultTableModel modelo;
        Class_compra resolve = new Class_compra(); 
        String valor_unitario = resolve.carrega_valor((String) lista_produto.getSelectedValue());
        Float valor_caixa = Float.parseFloat(valor_unitario) * Integer.parseInt(quantidade_por_caixa.getText());
        modelo = (DefaultTableModel) tabela_adcionados.getModel();
        modelo.addRow(new Object[] {quantidade_de_caixas.getText(), 
                                    quantidade_por_caixa.getText(),
                                    lista_produto.getSelectedValue(),
                                    combo_fornecedor.getSelectedItem(),
                                    valor_unitario,
                                    valor_caixa,
                                    sub_valor.getText()});
        float total =0;
        String aa;
        float bb;
        for(int i =0; i<tabela_adcionados.getRowCount();i++){
            total = ( Integer.parseInt((String)tabela_adcionados.getValueAt(i, 0)) *  
                    (float) tabela_adcionados.getValueAt(i,5) ) + total;
            
            
            //total = (Float.parseFloat(aa) * Float.parseFloat(bb)) + total;  
        }
        
        lbl_total.setText(Float.toString(total));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void lista_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_produtoMouseClicked
        quantidade_de_caixas.setText("1");
        quantidade_por_caixa.setText("1");
    }//GEN-LAST:event_lista_produtoMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Class_compra realiza = new Class_compra();
        String codigo_fornecedor = realiza.retorna_cod_fornecedor((String)combo_fornecedor.getSelectedItem());
        realiza.realiza_Compra(descricao_nova.getText(), 
                codigo_fornecedor, 
                n_nota.getText(), 
                data_comprado.getText(),
                lbl_total.getText(),
                "1", 
                tabela_adcionados.getModel(),
                "3"); // 3 pq estou mandndo tudo pro porao
    }//GEN-LAST:event_jButton8ActionPerformed

    private void combo_fornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_fornecedorItemStateChanged

        
    }//GEN-LAST:event_combo_fornecedorItemStateChanged

    private void combo_fornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_fornecedorMouseClicked
       
    }//GEN-LAST:event_combo_fornecedorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList List_forma_pagamento;
    private javax.swing.JTextField codigo_compra;
    private javax.swing.JComboBox combo_fornecedor;
    private javax.swing.JTextField data_compra;
    private javax.swing.JTextField data_comprado;
    private javax.swing.JTextField descricao_compra;
    private javax.swing.JTextField descricao_nova;
    private javax.swing.JTextField fornecedor_compra;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblProdutosCadastrados;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JList lista_produto;
    private javax.swing.JList lista_tipo;
    private javax.swing.JTextField n_nota;
    private javax.swing.JDialog nova_compra;
    private javax.swing.JTextField numero_nota_compra;
    private javax.swing.JTextField pesquisa_produto;
    private javax.swing.JTextField pesquisa_tipo;
    private javax.swing.JTextField quantidade_de_caixas;
    private javax.swing.JTextField quantidade_por_caixa;
    private javax.swing.JTextField responsavel_compra;
    private javax.swing.JTextField sub_valor;
    private javax.swing.JTable tabela_adcionados;
    private javax.swing.JTable tabela_compra;
    private javax.swing.JTable tabela_produtos_comprado;
    private javax.swing.JTextField valor_total_compra;
    // End of variables declaration//GEN-END:variables
}
