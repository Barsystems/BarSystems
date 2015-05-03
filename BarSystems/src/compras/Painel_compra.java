
package compras;

import centros_custo.Class_Caixa;
import centros_custo.Class_Centros_Custo;
import renderers.Class_Cell_Editor;
import estoque.Class_estoque;
import financeiro.Class_Setores_Financeiros;
import formas_pagamento.Class_Formas_Pagto;
import fornecedores.Class_Fornecedores;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import principal.Class_Consumir_Letras;
import principal.Class_Troca_Virgula_Por_Ponto;
import principal.Class_Verifica_Menu_Aberto;
import produtos.Class_produtos;
import renderers.Class_Renderer_Centralizar_Texto;
import usuarios.Class_Usuarios;

public class Painel_compra extends javax.swing.JPanel {
    
    protected JTabbedPane painel_principal;
    protected int id_usuario;
    protected String nome_usuario;

    public Painel_compra(JTabbedPane painel_principal, int id_usuario, String nome_usuario) {
        initComponents();
        
        this.painel_principal = painel_principal;
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        
        tabelaParcelas.setDefaultRenderer(Object.class, new Class_Renderer_Centralizar_Texto());
        TableColumn colData = tabelaParcelas.getColumnModel().getColumn(3);  
        colData.setCellEditor(new Class_Cell_Editor());
    }
    
    public void refreshTable(){
        Class_compra compra = new Class_compra();
        compra.carregaTabela((DefaultTableModel) tabela_compra.getModel());
    }
    
    public void refreshTotalCompra(DefaultTableModel modelo) {
        float total = 0;
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        for (int i = 0; i < tabelaProdutosCompradosNovaCompra.getRowCount(); i++) {
            total = troca.trocaVirgulaPorPonto(modelo.getValueAt(i,3).toString().replace("R$ ", "")) + total;      
        }
        NumberFormat n = NumberFormat.getCurrencyInstance();
        lbl_total_compra.setText(n.format(total)); 
    }
    
    public void refreshCampos(int linha) {
        
        DefaultTableModel modelo = (DefaultTableModel) tabela_compra.getModel();
        int codigo = Integer.valueOf(modelo.getValueAt(linha, 0).toString());
        Class_compra compra = new Class_compra();
        
        //aqui carregamos os dados da compra
        compra.carregaDados(codigo);
        descricao_compra.setText(compra.getDescricao());
        fornecedor_compra.setText(compra.getFornecedor());
        txtDataCompra.setText(compra.getData());
        numero_nota_compra.setText(compra.getNumero_nota());
        valor_total_compra.setText(compra.getValor());
        responsavel_compra.setText(compra.getResponsavel());
        
        //aqui carregamos os produtos da compra
        compra.carregaProdutos_Compra(codigo, (DefaultTableModel) tabela_produtos_comprado.getModel());
    }
    
    public void refreshProdutos() {
        Class_produtos produtos = new Class_produtos();
        produtos.carregaProdutosComboBox(comboProdutoNovaCompra);
    }
    
    public void refreshFornecedor() {
        Class_Fornecedores fornecedores = new Class_Fornecedores();
        fornecedores.carregaFornecedorComboBox(comboFornecedorNovaCompra);
    }
    
    public void refreshFormasPagamento() {
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        formas.carregaFormasPagamento(comboFormaPagamentoNovaCompra);
    }
    
    public void refreshCentrosEstoque() {
         Class_estoque estoque = new Class_estoque();
         estoque.carregaCentrosEstoqueComboBox(comboCentroEstoqueNovaCompra);
    }
    
    public void pesquisaProduto(int id_produto, String descricao) {
        Class_produtos produtos = new Class_produtos();
        String nome = produtos.retornaProdutoPesquisa(id_produto, descricao);
        if (nome.equals("")) {
            comboProdutoNovaCompra.setSelectedIndex(0);
        } else {
            for (int i = 0; i < comboProdutoNovaCompra.getItemCount(); i++) {
                if (comboProdutoNovaCompra.getItemAt(i).toString().contains(nome)) {
                    comboProdutoNovaCompra.setSelectedItem(nome);
                    i = comboProdutoNovaCompra.getItemCount();
                } else {
                    comboProdutoNovaCompra.setSelectedIndex(0);
                }
            }
        }
    }
    
    public void adicionaProdutoTabela(DefaultTableModel modelo, String produto, int quantidade, float valor, String centro) {
        boolean flag = false;
        String produtoAdd, centroAdd;
        int quantidadeAdd, quantidadeTotal;
        float valorAdd, valorTotal;
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        NumberFormat n = NumberFormat.getCurrencyInstance();
        //se o produto que vou lançar já existe na tabela, eu só preciso atualizar a quantidade
        for (int i = 0; i < tabelaProdutosCompradosNovaCompra.getRowCount(); i++) {
            produtoAdd = modelo.getValueAt(i, 0).toString();
            valorAdd = troca.trocaVirgulaPorPonto(modelo.getValueAt(i, 2).toString().replace("R$ ", ""));
            centroAdd = modelo.getValueAt(i, 4).toString();
            if (produtoAdd.equals(produto) && valorAdd == valor && centroAdd.equals(centro)) {
                quantidadeAdd = Integer.valueOf(modelo.getValueAt(i, 1).toString().replace("R$ ", ""));
                quantidadeTotal = quantidade + quantidadeAdd;
                valorTotal = valor * (quantidadeAdd + quantidade);
                modelo.setValueAt(quantidadeTotal, i, 1);
                modelo.setValueAt(n.format(valorTotal), i, 3);
                i = tabelaProdutosCompradosNovaCompra.getRowCount();
                flag = true;
            }
        }
        //se não, lanço o produto na tabela
        if (flag == false) {
            float total = quantidade * valor;
            modelo.addRow(new Object[] {
                produto, 
                quantidade, 
                n.format(valor), 
                n.format(total), 
                centro
            });
        }
        
        //por fim, recarrego o valor total da compra
        refreshTotalCompra(modelo);
    }
    
    public void limpaCamposNovaCompra() {
        //limpa os campos da compra
        txtDescricaoNovaCompra.setText("");
        txtNumeroNotaNovaCompra.setText("");
        dataNovaCompra.setDate(new Date());
        txtResponsavelNovaCompra.setText("");
        txtIdProdutoPesquisar.setText("");
        txtProdutoPesquisar.setText("");
        txtQuantidadeNovaCompra.setText("");
        txtValorUnitarioNovaCompra.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCompradosNovaCompra.getModel();
        modelo.setRowCount(0);        
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
        txtDescricaoNovaCompra = new javax.swing.JTextField();
        comboFornecedorNovaCompra = new javax.swing.JComboBox();
        txtNumeroNotaNovaCompra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnAddProdutosCompra = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutosCompradosNovaCompra = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbl_total_compra = new javax.swing.JLabel();
        btnAvancarNovaCompra = new javax.swing.JButton();
        btnCancelarNovaCompra = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtQuantidadeNovaCompra = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtValorUnitarioNovaCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboCentroEstoqueNovaCompra = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dataNovaCompra = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtIdProdutoPesquisar = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtProdutoPesquisar = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        comboProdutoNovaCompra = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        txtResponsavelNovaCompra = new javax.swing.JTextField();
        formas_pagamento = new javax.swing.JDialog();
        lbl_total_parcelas = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboFormaPagamentoNovaCompra = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaParcelas = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPagoPrimeiraParcela = new javax.swing.JTextField();
        txtVoltarNovaCompra = new javax.swing.JButton();
        txtSalvarNovaCompra = new javax.swing.JButton();
        btnGerarParcelaNovaCompra = new javax.swing.JButton();
        comboNumeroParcelasNovaCompra = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dataPagamentoPrimeiraParcela = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        comboCentrosCustoNovaCompra = new javax.swing.JComboBox();
        lblProdutosCadastrados = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        descricao_compra = new javax.swing.JTextField();
        fornecedor_compra = new javax.swing.JTextField();
        numero_nota_compra = new javax.swing.JTextField();
        txtDataCompra = new javax.swing.JTextField();
        responsavel_compra = new javax.swing.JTextField();
        valor_total_compra = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela_compra = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_produtos_comprado = new javax.swing.JTable();

        nova_compra.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        nova_compra.setTitle("Nova compra");
        nova_compra.setBounds(new java.awt.Rectangle(0, 0, 600, 600));
        nova_compra.setMinimumSize(new java.awt.Dimension(600, 600));
        nova_compra.setModal(true);
        nova_compra.setResizable(false);
        nova_compra.getContentPane().setLayout(null);

        txtDescricaoNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(txtDescricaoNovaCompra);
        txtDescricaoNovaCompra.setBounds(190, 90, 670, 30);

        comboFornecedorNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(comboFornecedorNovaCompra);
        comboFornecedorNovaCompra.setBounds(190, 170, 670, 30);

        txtNumeroNotaNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(txtNumeroNotaNovaCompra);
        txtNumeroNotaNovaCompra.setBounds(190, 130, 350, 30);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Produto");
        nova_compra.getContentPane().add(jLabel11);
        jLabel11.setBounds(290, 360, 50, 17);

        btnAddProdutosCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnAddProdutosCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProdutosCompraActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(btnAddProdutosCompra);
        btnAddProdutosCompra.setBounds(730, 380, 60, 30);

        tabelaProdutosCompradosNovaCompra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaProdutosCompradosNovaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quant", "Valor unit.", "Valor total", "Centro de Estoque"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutosCompradosNovaCompra.setRowHeight(25);
        tabelaProdutosCompradosNovaCompra.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabelaProdutosCompradosNovaCompra.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelaProdutosCompradosNovaCompra.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaProdutosCompradosNovaCompra);
        if (tabelaProdutosCompradosNovaCompra.getColumnModel().getColumnCount() > 0) {
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(0).setResizable(false);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(0).setPreferredWidth(400);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(1).setResizable(false);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(2).setResizable(false);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(3).setResizable(false);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(4).setResizable(false);
            tabelaProdutosCompradosNovaCompra.getColumnModel().getColumn(4).setPreferredWidth(250);
        }

        nova_compra.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 430, 810, 120);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(jButton6);
        jButton6.setBounds(800, 380, 60, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Valor total da compra");
        nova_compra.getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 560, 140, 17);

        lbl_total_compra.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl_total_compra.setForeground(new java.awt.Color(255, 0, 0));
        lbl_total_compra.setText("R$ 0,00");
        nova_compra.getContentPane().add(lbl_total_compra);
        lbl_total_compra.setBounds(200, 560, 110, 17);

        btnAvancarNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAvancarNovaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Avançar 16px.png"))); // NOI18N
        btnAvancarNovaCompra.setText("Avançar");
        btnAvancarNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvancarNovaCompraActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(btnAvancarNovaCompra);
        btnAvancarNovaCompra.setBounds(360, 600, 110, 30);

        btnCancelarNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarNovaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnCancelarNovaCompra.setText("Sair");
        btnCancelarNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarNovaCompraActionPerformed(evt);
            }
        });
        nova_compra.getContentPane().add(btnCancelarNovaCompra);
        btnCancelarNovaCompra.setBounds(480, 600, 110, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Quant");
        nova_compra.getContentPane().add(jLabel16);
        jLabel16.setBounds(550, 360, 50, 17);

        txtQuantidadeNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtQuantidadeNovaCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeNovaCompraKeyReleased(evt);
            }
        });
        nova_compra.getContentPane().add(txtQuantidadeNovaCompra);
        txtQuantidadeNovaCompra.setBounds(550, 380, 70, 30);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Valor unit.");
        nova_compra.getContentPane().add(jLabel17);
        jLabel17.setBounds(640, 360, 70, 17);

        txtValorUnitarioNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtValorUnitarioNovaCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorUnitarioNovaCompraKeyReleased(evt);
            }
        });
        nova_compra.getContentPane().add(txtValorUnitarioNovaCompra);
        txtValorUnitarioNovaCompra.setBounds(640, 380, 70, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Centro de estoque");
        nova_compra.getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 360, 130, 20);

        comboCentroEstoqueNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(comboCentroEstoqueNovaCompra);
        comboCentroEstoqueNovaCompra.setBounds(50, 380, 220, 30);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Comprar 24px.png"))); // NOI18N
        jLabel12.setText("Nova compra");
        nova_compra.getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 30, 940, 30);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Descrição");
        nova_compra.getContentPane().add(jLabel18);
        jLabel18.setBounds(100, 100, 70, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Fornecedor");
        nova_compra.getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 180, 80, 17);

        dataNovaCompra.setDate(new Date());
        dataNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(dataNovaCompra);
        dataNovaCompra.setBounds(690, 130, 170, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Data da compra");
        nova_compra.getContentPane().add(jLabel4);
        jLabel4.setBounds(570, 140, 110, 17);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText(" Número da nota");
        nova_compra.getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 140, 110, 17);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("---------- Adicione produtos à compra ----------");
        nova_compra.getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 260, 900, 17);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("ID produto");
        nova_compra.getContentPane().add(jLabel19);
        jLabel19.setBounds(220, 310, 80, 17);

        txtIdProdutoPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIdProdutoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdProdutoPesquisarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdProdutoPesquisarKeyTyped(evt);
            }
        });
        nova_compra.getContentPane().add(txtIdProdutoPesquisar);
        txtIdProdutoPesquisar.setBounds(300, 300, 59, 30);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Produto");
        nova_compra.getContentPane().add(jLabel21);
        jLabel21.setBounds(400, 310, 60, 17);

        txtProdutoPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtProdutoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdutoPesquisarKeyReleased(evt);
            }
        });
        nova_compra.getContentPane().add(txtProdutoPesquisar);
        txtProdutoPesquisar.setBounds(470, 300, 390, 30);

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText(" Pesquise produtos");
        nova_compra.getContentPane().add(jLabel27);
        jLabel27.setBounds(50, 310, 130, 17);

        comboProdutoNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(comboProdutoNovaCompra);
        comboProdutoNovaCompra.setBounds(290, 380, 240, 30);

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Responsável");
        nova_compra.getContentPane().add(jLabel29);
        jLabel29.setBounds(80, 220, 90, 17);

        txtResponsavelNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nova_compra.getContentPane().add(txtResponsavelNovaCompra);
        txtResponsavelNovaCompra.setBounds(190, 210, 670, 30);

        formas_pagamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        formas_pagamento.setTitle("Defina a forma de pagamento");
        formas_pagamento.setModal(true);
        formas_pagamento.setResizable(false);
        formas_pagamento.getContentPane().setLayout(null);

        lbl_total_parcelas.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl_total_parcelas.setText("R$ 0,00");
        formas_pagamento.getContentPane().add(lbl_total_parcelas);
        lbl_total_parcelas.setBounds(180, 80, 130, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Valor da compra");
        formas_pagamento.getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 80, 140, 30);

        comboFormaPagamentoNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboFormaPagamentoNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormaPagamentoNovaCompraActionPerformed(evt);
            }
        });
        formas_pagamento.getContentPane().add(comboFormaPagamentoNovaCompra);
        comboFormaPagamentoNovaCompra.setBounds(190, 140, 360, 30);

        tabelaParcelas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaParcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Parcela", "Valor", "Liquidado", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaParcelas.setRowHeight(25);
        tabelaParcelas.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabelaParcelas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelaParcelas.getTableHeader().setReorderingAllowed(false);
        tabelaParcelas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabelaParcelasPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(tabelaParcelas);
        if (tabelaParcelas.getColumnModel().getColumnCount() > 0) {
            tabelaParcelas.getColumnModel().getColumn(0).setResizable(false);
            tabelaParcelas.getColumnModel().getColumn(1).setResizable(false);
            tabelaParcelas.getColumnModel().getColumn(2).setResizable(false);
            tabelaParcelas.getColumnModel().getColumn(3).setResizable(false);
        }

        formas_pagamento.getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(30, 270, 520, 220);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Parcelas");
        formas_pagamento.getContentPane().add(jLabel14);
        jLabel14.setBounds(30, 190, 70, 17);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Pago na primeira parcela");
        formas_pagamento.getContentPane().add(jLabel15);
        jLabel15.setBounds(190, 190, 159, 17);

        txtPagoPrimeiraParcela.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        formas_pagamento.getContentPane().add(txtPagoPrimeiraParcela);
        txtPagoPrimeiraParcela.setBounds(370, 180, 180, 30);

        txtVoltarNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtVoltarNovaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Voltar 16px.png"))); // NOI18N
        txtVoltarNovaCompra.setText("Voltar");
        txtVoltarNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoltarNovaCompraActionPerformed(evt);
            }
        });
        formas_pagamento.getContentPane().add(txtVoltarNovaCompra);
        txtVoltarNovaCompra.setBounds(300, 580, 100, 30);

        txtSalvarNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSalvarNovaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        txtSalvarNovaCompra.setText("Salvar");
        txtSalvarNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalvarNovaCompraActionPerformed(evt);
            }
        });
        formas_pagamento.getContentPane().add(txtSalvarNovaCompra);
        txtSalvarNovaCompra.setBounds(190, 580, 100, 30);

        btnGerarParcelaNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGerarParcelaNovaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Notas 16px.png"))); // NOI18N
        btnGerarParcelaNovaCompra.setText("Gerar");
        btnGerarParcelaNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarParcelaNovaCompraActionPerformed(evt);
            }
        });
        formas_pagamento.getContentPane().add(btnGerarParcelaNovaCompra);
        btnGerarParcelaNovaCompra.setBounds(460, 220, 90, 30);

        comboNumeroParcelasNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboNumeroParcelasNovaCompra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        comboNumeroParcelasNovaCompra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboNumeroParcelasNovaCompraItemStateChanged(evt);
            }
        });
        comboNumeroParcelasNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNumeroParcelasNovaCompraActionPerformed(evt);
            }
        });
        formas_pagamento.getContentPane().add(comboNumeroParcelasNovaCompra);
        comboNumeroParcelasNovaCompra.setBounds(100, 180, 60, 30);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Comprar 24px.png"))); // NOI18N
        jLabel10.setText("Forma de pagamento e parcelas");
        formas_pagamento.getContentPane().add(jLabel10);
        jLabel10.setBounds(0, 30, 580, 30);

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText("Forma de pagamento");
        formas_pagamento.getContentPane().add(jLabel28);
        jLabel28.setBounds(30, 150, 150, 17);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Data de pagamento da primeira parcela");
        formas_pagamento.getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 230, 270, 17);

        dataPagamentoPrimeiraParcela.setDate(new Date());
        dataPagamentoPrimeiraParcela.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        formas_pagamento.getContentPane().add(dataPagamentoPrimeiraParcela);
        dataPagamentoPrimeiraParcela.setBounds(300, 220, 150, 30);

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("As parcelas liquidadas irão entrar no (a)");
        formas_pagamento.getContentPane().add(jLabel30);
        jLabel30.setBounds(30, 520, 260, 17);

        comboCentrosCustoNovaCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        formas_pagamento.getContentPane().add(comboCentrosCustoNovaCompra);
        comboCentrosCustoNovaCompra.setBounds(300, 510, 250, 30);

        setLayout(null);

        lblProdutosCadastrados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblProdutosCadastrados.setText("Compras cadastradas");
        add(lblProdutosCadastrados);
        lblProdutosCadastrados.setBounds(20, 100, 160, 17);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Comprar 24px.png"))); // NOI18N
        jLabel1.setText("Manutenção do cadastro de compras");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 1020, 29);

        descricao_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        descricao_compra.setEnabled(false);
        add(descricao_compra);
        descricao_compra.setBounds(470, 120, 260, 30);

        fornecedor_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fornecedor_compra.setEnabled(false);
        add(fornecedor_compra);
        fornecedor_compra.setBounds(470, 160, 260, 30);

        numero_nota_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        numero_nota_compra.setEnabled(false);
        add(numero_nota_compra);
        numero_nota_compra.setBounds(470, 200, 260, 30);

        txtDataCompra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDataCompra.setEnabled(false);
        add(txtDataCompra);
        txtDataCompra.setBounds(470, 320, 260, 30);

        responsavel_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        responsavel_compra.setEnabled(false);
        add(responsavel_compra);
        responsavel_compra.setBounds(470, 280, 260, 30);

        valor_total_compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        valor_total_compra.setEnabled(false);
        add(valor_total_compra);
        valor_total_compra.setBounds(470, 240, 260, 30);

        btnNovo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setToolTipText("Cadastrar um novo produto");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        add(btnNovo);
        btnNovo.setBounds(290, 440, 100, 30);

        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        jButton3.setText("Editar");
        jButton3.setToolTipText("Alterar as informações de um produto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(400, 440, 100, 30);

        jButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        jButton4.setText("Excluir");
        jButton4.setToolTipText("Excluir um produto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(510, 440, 100, 30);

        btnSair.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setToolTipText("Fechar a tela de cadastro de produtos");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        add(btnSair);
        btnSair.setBounds(620, 440, 100, 30);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("Data");
        add(jLabel24);
        jLabel24.setBounds(420, 330, 50, 17);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("Nota");
        add(jLabel23);
        jLabel23.setBounds(420, 210, 40, 17);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Fornecedor");
        add(jLabel22);
        jLabel22.setBounds(380, 170, 80, 17);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Descrição");
        add(jLabel20);
        jLabel20.setBounds(390, 130, 80, 17);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Responsável");
        add(jLabel25);
        jLabel25.setBounds(370, 290, 90, 17);

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText(" Valor Total");
        add(jLabel26);
        jLabel26.setBounds(380, 250, 80, 17);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Produtos comprados");
        add(jLabel2);
        jLabel2.setBounds(760, 100, 180, 14);

        tabela_compra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabela_compra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Descrição", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_compra.setColumnSelectionAllowed(true);
        tabela_compra.setRowHeight(25);
        tabela_compra.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabela_compra.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabela_compra.getTableHeader().setReorderingAllowed(false);
        tabela_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabela_compraMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabela_compra);
        tabela_compra.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabela_compra.getColumnModel().getColumnCount() > 0) {
            tabela_compra.getColumnModel().getColumn(0).setResizable(false);
            tabela_compra.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabela_compra.getColumnModel().getColumn(1).setResizable(false);
            tabela_compra.getColumnModel().getColumn(1).setPreferredWidth(160);
            tabela_compra.getColumnModel().getColumn(2).setResizable(false);
            tabela_compra.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        add(jScrollPane3);
        jScrollPane3.setBounds(20, 120, 330, 240);

        tabela_produtos_comprado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabela_produtos_comprado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quant"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_produtos_comprado.setRowHeight(25);
        tabela_produtos_comprado.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabela_produtos_comprado.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabela_produtos_comprado.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabela_produtos_comprado);
        if (tabela_produtos_comprado.getColumnModel().getColumnCount() > 0) {
            tabela_produtos_comprado.getColumnModel().getColumn(0).setResizable(false);
            tabela_produtos_comprado.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabela_produtos_comprado.getColumnModel().getColumn(1).setResizable(false);
            tabela_produtos_comprado.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        add(jScrollPane2);
        jScrollPane2.setBounds(760, 120, 240, 230);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        limpaCamposNovaCompra();
        refreshFornecedor();
        refreshCentrosEstoque();
        refreshProdutos();
        nova_compra.setBounds(0, 0, 930, 670);
        nova_compra.setLocationRelativeTo(null);
        nova_compra.setVisible(true);
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed

        Class_Verifica_Menu_Aberto verifica = new Class_Verifica_Menu_Aberto();
        int index = verifica.verificaMenuAberto(painel_principal, "Compras   ");
        painel_principal.remove(index);

    }//GEN-LAST:event_btnSairActionPerformed

    private void btnAddProdutosCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProdutosCompraActionPerformed
       
        if (comboProdutoNovaCompra.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar à compra!", "Atenção", JOptionPane.WARNING_MESSAGE);
            comboProdutoNovaCompra.grabFocus();
        } else if (txtQuantidadeNovaCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Defina uma quantidade ao produto comprado!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtQuantidadeNovaCompra.grabFocus();
        } else if (txtValorUnitarioNovaCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Defina um valor ao produto comprado!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtValorUnitarioNovaCompra.grabFocus();
        } else {
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            adicionaProdutoTabela((DefaultTableModel) tabelaProdutosCompradosNovaCompra.getModel(), 
                    comboProdutoNovaCompra.getSelectedItem().toString(), 
                    Integer.valueOf(txtQuantidadeNovaCompra.getText()), troca.trocaVirgulaPorPonto(txtValorUnitarioNovaCompra.getText()), 
                    comboCentroEstoqueNovaCompra.getSelectedItem().toString());
            
            //em seguida vamos limpar os campos dos produtos
            comboCentroEstoqueNovaCompra.setSelectedIndex(0);
            comboProdutoNovaCompra.setSelectedIndex(0);
            txtQuantidadeNovaCompra.setText("");
            txtValorUnitarioNovaCompra.setText("");
            txtQuantidadeNovaCompra.grabFocus();
        }
       
    }//GEN-LAST:event_btnAddProdutosCompraActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        int linha = tabelaProdutosCompradosNovaCompra.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para retirar da compra!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCompradosNovaCompra.getModel();
            int quantidade = Integer.valueOf(modelo.getValueAt(linha, 1).toString());
            if (quantidade > 1) {
                //se a quantidade for maior que um, então devo perguntar quantos o usuário quer retirar
                String quantRetirar = null;
                quantRetirar = JOptionPane.showInputDialog(null, "Digite a quantidade que deseja retirar", "Atenção", JOptionPane.PLAIN_MESSAGE);
                if (quantRetirar == null || quantRetirar.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida!", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    Class_Consumir_Letras cons = new Class_Consumir_Letras();
                    int quantRetirarInt = Integer.valueOf(cons.retiraLetrasEPontos(quantRetirar));
                    if (quantRetirarInt > quantidade) {
                        JOptionPane.showMessageDialog(null, "Não é possível retirar uma quantidade maior que a existente!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    } else if (quantRetirarInt == quantidade) {  
                        modelo.removeRow(linha);
                    } else {
                        int quantFinal = quantidade - quantRetirarInt;
                        modelo.setValueAt(quantFinal, linha, 1);
                    }
                }
            } else {
                //se não, basta retirar todo o produto
                modelo.removeRow(linha);
            }
            // por fim, basta recarregar o total da compra
            refreshTotalCompra(modelo);
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnAvancarNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvancarNovaCompraActionPerformed
        
        if (txtDescricaoNovaCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a descrição da compra antes de avançar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtDescricaoNovaCompra.grabFocus();
        } else if (txtNumeroNotaNovaCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o número da nota da compra antes de avançar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNumeroNotaNovaCompra.grabFocus();
        } else if (txtResponsavelNovaCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Defina quem é o responsável pela compra!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtResponsavelNovaCompra.grabFocus();
        } else if (tabelaProdutosCompradosNovaCompra.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Insira um produto na compra antes de avançar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            lbl_total_parcelas.setText(lbl_total_compra.getText());
            txtPagoPrimeiraParcela.setText(lbl_total_compra.getText().replace("R$ ", ""));
            comboNumeroParcelasNovaCompra.setSelectedIndex(0);
            refreshFormasPagamento();
            DefaultTableModel modelo = (DefaultTableModel) tabelaParcelas.getModel();
            modelo.setRowCount(0);
            formas_pagamento.setBounds(0, 0, 590, 660);
            formas_pagamento.setLocationRelativeTo(null);
            formas_pagamento.setVisible(true);
        }
        
    }//GEN-LAST:event_btnAvancarNovaCompraActionPerformed

    private void btnGerarParcelaNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarParcelaNovaCompraActionPerformed
        
        int parcelas = Integer.valueOf(comboNumeroParcelasNovaCompra.getSelectedItem().toString());
        if (txtPagoPrimeiraParcela.getText().isEmpty()) {
            txtPagoPrimeiraParcela.setText("0,00");
        }
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float pago = troca.trocaVirgulaPorPonto(txtPagoPrimeiraParcela.getText());
        float total = troca.trocaVirgulaPorPonto(lbl_total_parcelas.getText().replace("R$ ", ""));
        
        float valor_parcelas = (total - pago) / (parcelas - 1); 
        
        NumberFormat n = NumberFormat.getCurrencyInstance();
        
        DefaultTableModel modelo = (DefaultTableModel) tabelaParcelas.getModel();
        modelo.setRowCount(0);
        Object linha[] = new Object[4];
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPagamentoPrimeiraParcela.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(calendar.getTime());
        
        boolean liquidado = true;
        for (int i = 1; i <= parcelas; i++) {
            linha[0] = i;
            linha[1] = n.format(pago);
            linha[2] = liquidado;
            linha[3] = data;
            modelo.addRow(linha);
            
            pago = valor_parcelas;
            liquidado = false;
            calendar.add(Calendar.MONTH, 1);
            data = sdf.format(calendar.getTime());
        }
        
    }//GEN-LAST:event_btnGerarParcelaNovaCompraActionPerformed

    private void txtSalvarNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalvarNovaCompraActionPerformed

        if (tabelaParcelas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Defina as parcelas para o pagamento!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            //CLASSES NECESSARIAS
            Class_Caixa caixa = new Class_Caixa();
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            Class_Centros_Custo centros = new Class_Centros_Custo();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            
            //VARIAVEIS NECESSARIAS
            int flag;
            int id_caixa = 0;
            String centro = comboCentrosCustoNovaCompra.getSelectedItem().toString();
            String tipo_centro_custo = centros.retornaTipoCentroCusto(centro);
            int id_centro_custo = centros.retornaIdCentroCusto(centro);
            int id_forma_pagamento = formas.retornaIdFormaPagamento(comboFormaPagamentoNovaCompra.getSelectedItem().toString());
            
            //PRIMEIRO VERIFICO QUAL O TIPO DO CENTRO DE CUSTO
            
            //SE O TIPO FOR CAIXA, E NÃO ESTIVER ABERTO, PERGUNTO SE O USUARIO DESEJA ABRIR
            if (tipo_centro_custo.equals("Caixa")) {
                if (caixa.verificaCaixaAberto(id_caixa) == false) {
                    if (JOptionPane.showConfirmDialog(null, "O caixa que você está tentando realizar o lançamento não se encontra aberto no momento. Deseja abrir este caixa agora?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                        String valor_abertura = null;
                        valor_abertura = JOptionPane.showInputDialog(null, "Digite o valor da abertura", "Atenção", JOptionPane.PLAIN_MESSAGE);
                        if (valor_abertura == null || valor_abertura.equals("")) {
                            flag = 0;
                        } else {
                            //SE TUDO CORRER BEM, ABRO O CAIXA
                            Class_Consumir_Letras cons = new Class_Consumir_Letras();
                            valor_abertura = cons.retiraLetras(valor_abertura);
                            caixa.abrirCaixa(id_centro_custo);
                            id_caixa = caixa.getIdCaixa(id_centro_custo);
                            caixa.registraMovimentacaoCaixa(id_caixa, "Abertura do caixa", id_forma_pagamento, 1, 
                                    troca.trocaVirgulaPorPonto(valor_abertura), "Abertura", id_usuario, sdf.format(new Date()));
                            flag = 1;
                        }
                        
                    } else {
                        flag = 0;
                    }
                } else {
                    id_caixa = caixa.getIdCaixa(id_centro_custo);
                    flag = 1;
                }
            } else {
                flag = 1;
            }
            
            //SE TUDO CORRER BEM E ESTIVER OK COM OS CENTROS DE CUSTO, ENTAO PROSSEGUIMOS
            if (flag == 1) {
                //INSTANCIO AS CLASSES
                Class_Fornecedores forn = new Class_Fornecedores();
                Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
                
                //COLETO OS DADOS PARA REALIZAR A COMPRA
                String descricao = txtDescricaoNovaCompra.getText();
                int id_fornecedor = forn.retornaIdFornecedor(comboFornecedorNovaCompra.getSelectedItem().toString());
                String numero_nota = txtNumeroNotaNovaCompra.getText();
                String data_compra = sdf.format(dataNovaCompra.getDate());
                float valor_compra = troca.trocaVirgulaPorPonto(lbl_total_compra.getText().replace("R$ ", ""));
                int id_setor = setores.retornaIdSetorFinanceiro("Compras");
                String responsavel = txtResponsavelNovaCompra.getText();
                int numero_parcelas = Integer.valueOf(comboNumeroParcelasNovaCompra.getSelectedItem().toString());

                //CADASTRO A COMPRA
                Class_compra compra = new Class_compra();
                compra.cadastraCompra(descricao, id_fornecedor, numero_nota, data_compra, responsavel, valor_compra, 
                        numero_parcelas, id_forma_pagamento);

                //PEGO O ID DESTA COMPRA
                int id_compra = compra.getIdUltimaCompra();

                //CADASTRO AS PARCELAS DA COMPRA
                compra.cadastraPagamentosCompra(tabelaParcelas, id_compra, descricao, responsavel, id_fornecedor, data_compra,
                        numero_parcelas, id_forma_pagamento, id_setor, id_usuario, caixa, id_caixa, tipo_centro_custo, id_centro_custo);

                //CADASTRO OS PRODUTOS DA COMPRA
                compra.cadastraProdutosCompra(tabelaProdutosCompradosNovaCompra, id_compra);

                //POR FIM CADASTRO OS PRODUTOS NO CENTRO DE ESTOQUE
                Class_estoque estoque = new Class_estoque();
                estoque.cadastraProdutosCentroEstoque(tabelaProdutosCompradosNovaCompra);

                //CARREGAMOS AS COMPRAS NOVAMENTE
                JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                formas_pagamento.dispose();
                nova_compra.dispose();
                compra.carregaTabela((DefaultTableModel) tabela_compra.getModel());
            }
        }
                
    }//GEN-LAST:event_txtSalvarNovaCompraActionPerformed

    private void comboNumeroParcelasNovaCompraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboNumeroParcelasNovaCompraItemStateChanged
        try{
        int val = (int) comboNumeroParcelasNovaCompra.getSelectedItem();
        if(val == 1) {
            txtPagoPrimeiraParcela.setText(lbl_total_parcelas.getText());    
            txtPagoPrimeiraParcela.setEditable(false);
        }
        else{
            txtPagoPrimeiraParcela.setEditable(true);
        }
        }catch(Exception ex){
            
        }
        
    }//GEN-LAST:event_comboNumeroParcelasNovaCompraItemStateChanged

    private void tabela_compraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_compraMousePressed
        
        int linha = tabela_compra.getSelectedRow();
        if (linha == -1) {
            descricao_compra.setText("");
            fornecedor_compra.setText("");
            txtDataCompra.setText("");
            numero_nota_compra.setText("");
            valor_total_compra.setText("");
            responsavel_compra.setText("");
        } else {
            refreshCampos(linha);
        }
        
    }//GEN-LAST:event_tabela_compraMousePressed

    private void txtIdProdutoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProdutoPesquisarKeyReleased
        
        txtProdutoPesquisar.setText("");
        if (!txtIdProdutoPesquisar.getText().isEmpty()) {
            pesquisaProduto(Integer.valueOf(txtIdProdutoPesquisar.getText()), "");
        }
        
    }//GEN-LAST:event_txtIdProdutoPesquisarKeyReleased

    private void txtProdutoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdutoPesquisarKeyReleased
        
        txtIdProdutoPesquisar.setText("");
        if (!txtProdutoPesquisar.getText().isEmpty()) {
            pesquisaProduto(0, txtProdutoPesquisar.getText());
        }
        
    }//GEN-LAST:event_txtProdutoPesquisarKeyReleased

    private void txtIdProdutoPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProdutoPesquisarKeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890", evt);
        
    }//GEN-LAST:event_txtIdProdutoPesquisarKeyTyped

    private void txtQuantidadeNovaCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeNovaCompraKeyReleased
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890", evt);
        
    }//GEN-LAST:event_txtQuantidadeNovaCompraKeyReleased

    private void txtValorUnitarioNovaCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioNovaCompraKeyReleased
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890,.", evt);
        
    }//GEN-LAST:event_txtValorUnitarioNovaCompraKeyReleased

    private void comboFormaPagamentoNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormaPagamentoNovaCompraActionPerformed
        
        Object forma = comboFormaPagamentoNovaCompra.getSelectedItem();
        if (forma == null) {
        } else {
            if (forma.toString().equals("Cartão de débito")) {
                comboNumeroParcelasNovaCompra.setSelectedIndex(0);
                comboNumeroParcelasNovaCompra.setEnabled(false);
            } else {
                comboNumeroParcelasNovaCompra.setEnabled(true);
            }
            
            Class_Centros_Custo centros = new Class_Centros_Custo();
            Class_Usuarios usuarios = new Class_Usuarios();
            String tipo_usuario = usuarios.getTipoUsuario(nome_usuario);
            if (forma.toString().equals("Dinheiro") || forma.toString().equals("Cheque")) {
                centros.carregaCentrosCustoComboBox(comboCentrosCustoNovaCompra, "Todos", id_usuario, tipo_usuario);
            } else {
                centros.carregaCentrosCustoComboBox(comboCentrosCustoNovaCompra, "Conta bancária", id_usuario, tipo_usuario);
            }
        }
        
    }//GEN-LAST:event_comboFormaPagamentoNovaCompraActionPerformed

    private void comboNumeroParcelasNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNumeroParcelasNovaCompraActionPerformed
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float total = troca.trocaVirgulaPorPonto(lbl_total_parcelas.getText().replace("R$ ", ""));
        int parcelas = Integer.valueOf(comboNumeroParcelasNovaCompra.getSelectedItem().toString());
        float valor_parcelado = total / parcelas;
        
        NumberFormat n = NumberFormat.getCurrencyInstance();
        txtPagoPrimeiraParcela.setText(n.format(valor_parcelado).replace("R$ ", ""));
        
        if (comboNumeroParcelasNovaCompra.getSelectedIndex() == 0) {
            txtPagoPrimeiraParcela.setEditable(false);
        } else {
            txtPagoPrimeiraParcela.setEditable(true);
        }
        
    }//GEN-LAST:event_comboNumeroParcelasNovaCompraActionPerformed

    private void txtVoltarNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVoltarNovaCompraActionPerformed
        
        formas_pagamento.dispose();
        
    }//GEN-LAST:event_txtVoltarNovaCompraActionPerformed

    private void btnCancelarNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarNovaCompraActionPerformed
        
        nova_compra.dispose();
        
    }//GEN-LAST:event_btnCancelarNovaCompraActionPerformed

    private void tabelaParcelasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabelaParcelasPropertyChange
        
        int linha = tabelaParcelas.getSelectedRow();
        int column = tabelaParcelas.getSelectedColumn();
        if (linha >=0 && column >=0) {
            if (column == 2) {
                DefaultTableModel modelo = (DefaultTableModel) tabelaParcelas.getModel();
                boolean liq = (boolean) modelo.getValueAt(linha, 2);
                if (liq == true) {
                    for (int i = 0; i <= linha; i++) {
                        modelo.setValueAt(liq, i, 2);
                    }
                } else {
                    for (int i = 0; i < tabelaParcelas.getRowCount(); i++) {
                        modelo.setValueAt(liq, i, 2);
                    }
                }
            }
        }
        
    }//GEN-LAST:event_tabelaParcelasPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProdutosCompra;
    private javax.swing.JButton btnAvancarNovaCompra;
    private javax.swing.JButton btnCancelarNovaCompra;
    private javax.swing.JButton btnGerarParcelaNovaCompra;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox comboCentroEstoqueNovaCompra;
    private javax.swing.JComboBox comboCentrosCustoNovaCompra;
    private javax.swing.JComboBox comboFormaPagamentoNovaCompra;
    private javax.swing.JComboBox comboFornecedorNovaCompra;
    private javax.swing.JComboBox comboNumeroParcelasNovaCompra;
    private javax.swing.JComboBox comboProdutoNovaCompra;
    private com.toedter.calendar.JDateChooser dataNovaCompra;
    private com.toedter.calendar.JDateChooser dataPagamentoPrimeiraParcela;
    private javax.swing.JTextField descricao_compra;
    private javax.swing.JDialog formas_pagamento;
    private javax.swing.JTextField fornecedor_compra;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblProdutosCadastrados;
    private javax.swing.JLabel lbl_total_compra;
    private javax.swing.JLabel lbl_total_parcelas;
    private javax.swing.JDialog nova_compra;
    private javax.swing.JTextField numero_nota_compra;
    private javax.swing.JTextField responsavel_compra;
    private javax.swing.JTable tabelaParcelas;
    private javax.swing.JTable tabelaProdutosCompradosNovaCompra;
    private javax.swing.JTable tabela_compra;
    private javax.swing.JTable tabela_produtos_comprado;
    private javax.swing.JTextField txtDataCompra;
    private javax.swing.JTextField txtDescricaoNovaCompra;
    private javax.swing.JTextField txtIdProdutoPesquisar;
    private javax.swing.JTextField txtNumeroNotaNovaCompra;
    private javax.swing.JTextField txtPagoPrimeiraParcela;
    private javax.swing.JTextField txtProdutoPesquisar;
    private javax.swing.JTextField txtQuantidadeNovaCompra;
    private javax.swing.JTextField txtResponsavelNovaCompra;
    private javax.swing.JButton txtSalvarNovaCompra;
    private javax.swing.JTextField txtValorUnitarioNovaCompra;
    private javax.swing.JButton txtVoltarNovaCompra;
    private javax.swing.JTextField valor_total_compra;
    // End of variables declaration//GEN-END:variables
}
