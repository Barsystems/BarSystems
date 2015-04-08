
package centros_custo;

import principal.Class_Consumir_Letras;
import principal.Class_Troca_Virgula_Por_Ponto;
import financeiro.Class_Despesas;
import financeiro.Class_Receitas;
import usuarios.Class_Usuarios;
import usuarios.Painel_Senha_Administrador;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Painel_Caixa extends javax.swing.JPanel {
    
    protected String nome_usuario, nome_caixa, valor_lancamento;
    protected int id_centro_custo, id_usuario;
    
    public Painel_Caixa() {
        
    }

    public Painel_Caixa(int id_centro_custo, String nome_caixa, int id_usuario, String nome_usuario) {
        initComponents();
        
        this.nome_caixa = nome_caixa;
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_centro_custo = id_centro_custo;
        
        lblTituloCaixa.setText("Gerenciamento do "+this.nome_caixa+": Usuário "+this.nome_usuario);
        
        refreshMovimentacaoCaixa();
    }
    
    public void refreshMovimentacaoCaixa() {
        
        Class_Caixa caixa = new Class_Caixa();
        int id_caixa = caixa.getIdCaixa(id_centro_custo);
        if (caixa.verificaCaixaAberto(id_caixa) == true) 
        {
            caixa.carregaMovimentacoesCaixa((DefaultTableModel) tabelaMovimentacoesCaixa.getModel(), id_caixa);
            btnAbrirFecharCaixa.setText("Fechar caixa");
        } 
        else 
        {
            btnAbrirFecharCaixa.setText("Abrir caixa");
        }
        
    }
    
    public void limpaCamposNovoLancamento() {
        comboTipoNovoLancamento.setSelectedIndex(0);
        txtDescricaoNovoLancamento.setText("");
        comboFormaNovoLancamento.setSelectedIndex(0);
        txtValorNovoLancamento.setText("");
        txtDescricaoNovoLancamento.grabFocus();
    }
    
    public void carregaCamposAlterarLancamento(int linha) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesCaixa.getModel();
        lblTipoAlterarLancamento.setText(modelo.getValueAt(linha, 3).toString());
        txtDescricaoAlterarLancamento.setText(modelo.getValueAt(linha, 0).toString());
        comboFormaAlterarLancamento.setSelectedItem(modelo.getValueAt(linha, 1).toString());
        txtValorAlterarLancamento.setText(modelo.getValueAt(linha, 2).toString().replace("R$ ", ""));
        valor_lancamento = txtValorAlterarLancamento.getText();
        
        //Aqui precisamos verificar se a movimentação pertence a uma abertura de caixa.
        //Se sim, não podemos deixar o usuário modificar a descrição e a forma de pagamento.
        if (lblTipoAlterarLancamento.getText().equals("Abertura")) {
            txtDescricaoAlterarLancamento.setEnabled(false);
            comboFormaAlterarLancamento.setEnabled(false);
        } else {
            txtDescricaoAlterarLancamento.setEnabled(true);
            comboFormaAlterarLancamento.setEnabled(true);
        }

        Alterar_Lancamento.setBounds(0, 0, 690, 410);
        Alterar_Lancamento.setLocationRelativeTo(null);
        Alterar_Lancamento.setVisible(true);
    }
    
    public void excluiMovimentacao(int linha) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION)==0) {
            DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesCaixa.getModel();
            String valor = modelo.getValueAt(linha, 2).toString().replace("R$ ", "");
            String tipo = modelo.getValueAt(linha, 3).toString();
            int id_movimentacao = Integer.valueOf(modelo.getValueAt(linha, 6).toString());
            Class_Caixa caixa = new Class_Caixa();
            caixa.excluiMovimentacaoCaixa(id_movimentacao);

            if (tipo.equals("Receita")) {                
                Class_Receitas receitas = new Class_Receitas();
                receitas.excluiReceitaPelaMovimentacaoCaixa(id_movimentacao);
            } else if (tipo.equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.excluiDespesaPelaMovimentacaoCaixa(id_movimentacao);
            }
            
            Class_Centros_Custo centros = new Class_Centros_Custo();
            centros.alteraSaldoCentroCusto(tipo, valor, "0,00", id_centro_custo);

            int id_caixa = caixa.getIdCaixa(id_centro_custo);
            caixa.carregaMovimentacoesCaixa((DefaultTableModel) tabelaMovimentacoesCaixa.getModel(), id_caixa);
            JOptionPane.showMessageDialog(null, "Lançamento excluído com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void desliquidarMovimentacao(int linha) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesCaixa.getModel();
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente desliquidar este lançamento?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
            String tipo = modelo.getValueAt(linha, 3).toString();
            int id = Integer.valueOf(modelo.getValueAt(linha, 6).toString());

            Class_Caixa caixa = new Class_Caixa();
            caixa.excluiMovimentacaoCaixa(id);

            if (tipo.equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                receitas.desliquidarReceitaPelaMovimentacaoCaixa(id);
            } else {
                Class_Despesas despesas = new Class_Despesas();
                despesas.desliquidarDespesaPelaMovimentacaoCaixa(id);
            }
            
            int id_caixa = caixa.getIdCaixa(id_centro_custo);
            caixa.carregaMovimentacoesCaixa((DefaultTableModel) tabelaMovimentacoesCaixa.getModel(), id_caixa);
            
            JOptionPane.showMessageDialog(null, "Lançamento desliquidado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Novo_Lancamento = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricaoNovoLancamento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboTipoNovoLancamento = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtValorNovoLancamento = new javax.swing.JTextField();
        btnSalvarNovoLancamento = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSairNovoLancamento = new javax.swing.JButton();
        comboFormaNovoLancamento = new javax.swing.JComboBox();
        Alterar_Lancamento = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDescricaoAlterarLancamento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtValorAlterarLancamento = new javax.swing.JTextField();
        btnSalvarAlterarLancamento = new javax.swing.JButton();
        btnSairAlterarLancamento = new javax.swing.JButton();
        lblTipoAlterarLancamento = new javax.swing.JLabel();
        comboFormaAlterarLancamento = new javax.swing.JComboBox();
        lblTituloCaixa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMovimentacoesCaixa = new javax.swing.JTable();
        btnAbrirFecharCaixa = new javax.swing.JButton();
        btnNovoLancamento = new javax.swing.JButton();
        btnAlterarLancamento = new javax.swing.JButton();
        btnExcluirLancamento = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnDesliquidarLancamentos = new javax.swing.JButton();

        Novo_Lancamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Novo_Lancamento.setTitle("Lançamento avulso no caixa");
        Novo_Lancamento.setModal(true);
        Novo_Lancamento.setResizable(false);
        Novo_Lancamento.getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Dinheiro 16px.png"))); // NOI18N
        jLabel1.setText("Lançamento avulso no caixa");
        Novo_Lancamento.getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 30, 671, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Descrição do lançamento");
        Novo_Lancamento.getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 150, 170, 17);

        txtDescricaoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Novo_Lancamento.getContentPane().add(txtDescricaoNovoLancamento);
        txtDescricaoNovoLancamento.setBounds(220, 140, 410, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText(" Forma de pagamento");
        Novo_Lancamento.getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 190, 150, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText(" Tipo de lançamento");
        Novo_Lancamento.getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 110, 140, 17);

        comboTipoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboTipoNovoLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Receita", "Despesa" }));
        Novo_Lancamento.getContentPane().add(comboTipoNovoLancamento);
        comboTipoNovoLancamento.setBounds(220, 100, 410, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Valor do lançamento");
        Novo_Lancamento.getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 230, 140, 17);

        txtValorNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtValorNovoLancamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorNovoLancamentoKeyTyped(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(txtValorNovoLancamento);
        txtValorNovoLancamento.setBounds(220, 220, 130, 30);

        btnSalvarNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarNovoLancamento.setText("Salvar");
        btnSalvarNovoLancamento.setToolTipText("Cadastra o lançamento no caixa");
        btnSalvarNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarNovoLancamentoActionPerformed(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(btnSalvarNovoLancamento);
        btnSalvarNovoLancamento.setBounds(180, 310, 100, 30);

        btnLimpar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar 16px.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.setToolTipText("Limpa os campos para um novo lançamento");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(btnLimpar);
        btnLimpar.setBounds(290, 310, 100, 30);

        btnSairNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairNovoLancamento.setText("Sair");
        btnSairNovoLancamento.setToolTipText("Cancela o lançamento");
        btnSairNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairNovoLancamentoActionPerformed(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(btnSairNovoLancamento);
        btnSairNovoLancamento.setBounds(400, 310, 100, 30);

        comboFormaNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboFormaNovoLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque" }));
        Novo_Lancamento.getContentPane().add(comboFormaNovoLancamento);
        comboFormaNovoLancamento.setBounds(220, 180, 410, 30);

        Alterar_Lancamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Alterar_Lancamento.setTitle("Alterar lançamento");
        Alterar_Lancamento.setModal(true);
        Alterar_Lancamento.setResizable(false);
        Alterar_Lancamento.getContentPane().setLayout(null);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Dinheiro 16px.png"))); // NOI18N
        jLabel7.setText("Alterar lançamento no caixa");
        Alterar_Lancamento.getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 30, 690, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Descrição do lançamento");
        Alterar_Lancamento.getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 150, 170, 17);

        txtDescricaoAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Alterar_Lancamento.getContentPane().add(txtDescricaoAlterarLancamento);
        txtDescricaoAlterarLancamento.setBounds(220, 140, 410, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText(" Forma de pagamento");
        Alterar_Lancamento.getContentPane().add(jLabel9);
        jLabel9.setBounds(60, 190, 150, 17);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText(" Tipo de lançamento");
        Alterar_Lancamento.getContentPane().add(jLabel10);
        jLabel10.setBounds(70, 110, 140, 17);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Valor do lançamento");
        Alterar_Lancamento.getContentPane().add(jLabel12);
        jLabel12.setBounds(70, 230, 140, 17);

        txtValorAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtValorAlterarLancamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorAlterarLancamentoKeyTyped(evt);
            }
        });
        Alterar_Lancamento.getContentPane().add(txtValorAlterarLancamento);
        txtValorAlterarLancamento.setBounds(220, 220, 150, 30);

        btnSalvarAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarAlterarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarAlterarLancamento.setText("Salvar");
        btnSalvarAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlterarLancamentoActionPerformed(evt);
            }
        });
        Alterar_Lancamento.getContentPane().add(btnSalvarAlterarLancamento);
        btnSalvarAlterarLancamento.setBounds(240, 310, 100, 30);

        btnSairAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairAlterarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairAlterarLancamento.setText("Sair");
        btnSairAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairAlterarLancamentoActionPerformed(evt);
            }
        });
        Alterar_Lancamento.getContentPane().add(btnSairAlterarLancamento);
        btnSairAlterarLancamento.setBounds(350, 310, 100, 30);

        lblTipoAlterarLancamento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTipoAlterarLancamento.setForeground(new java.awt.Color(255, 0, 0));
        lblTipoAlterarLancamento.setText("lblTipoLancamento");
        Alterar_Lancamento.getContentPane().add(lblTipoAlterarLancamento);
        lblTipoAlterarLancamento.setBounds(220, 110, 410, 17);

        comboFormaAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboFormaAlterarLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque" }));
        Alterar_Lancamento.getContentPane().add(comboFormaAlterarLancamento);
        comboFormaAlterarLancamento.setBounds(220, 180, 410, 30);

        setLayout(null);

        lblTituloCaixa.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblTituloCaixa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Dinheiro 24px.png"))); // NOI18N
        lblTituloCaixa.setText("Gerenciamento de caixa por usuário");
        add(lblTituloCaixa);
        lblTituloCaixa.setBounds(0, 30, 1020, 29);

        tabelaMovimentacoesCaixa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaMovimentacoesCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Forma Pagto", "Valor", "Tipo", "Data", "Responsável", "Cod"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaMovimentacoesCaixa.setToolTipText("Movimentações do caixa");
        tabelaMovimentacoesCaixa.setRowHeight(25);
        tabelaMovimentacoesCaixa.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabelaMovimentacoesCaixa.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelaMovimentacoesCaixa.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaMovimentacoesCaixa);
        if (tabelaMovimentacoesCaixa.getColumnModel().getColumnCount() > 0) {
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(4).setPreferredWidth(120);
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(5).setPreferredWidth(90);
            tabelaMovimentacoesCaixa.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 980, 300);

        btnAbrirFecharCaixa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAbrirFecharCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Dinheiro 16px.png"))); // NOI18N
        btnAbrirFecharCaixa.setText("Abrir caixa");
        btnAbrirFecharCaixa.setToolTipText("Abra e feche o caixa clicando neste botão");
        btnAbrirFecharCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirFecharCaixaActionPerformed(evt);
            }
        });
        add(btnAbrirFecharCaixa);
        btnAbrirFecharCaixa.setBounds(20, 80, 170, 30);

        btnNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovoLancamento.setText("Novo lançamento");
        btnNovoLancamento.setToolTipText("Faça lançamentos de receita e despesa no caixa");
        btnNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoLancamentoActionPerformed(evt);
            }
        });
        add(btnNovoLancamento);
        btnNovoLancamento.setBounds(330, 460, 180, 30);

        btnAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAlterarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        btnAlterarLancamento.setText("Editar lançamento");
        btnAlterarLancamento.setToolTipText("Altere as informações de algum lançamento");
        btnAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarLancamentoActionPerformed(evt);
            }
        });
        add(btnAlterarLancamento);
        btnAlterarLancamento.setBounds(520, 460, 180, 30);

        btnExcluirLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        btnExcluirLancamento.setText("Excluir lançamento");
        btnExcluirLancamento.setToolTipText("Exclua lançamentos indesejados");
        btnExcluirLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirLancamentoActionPerformed(evt);
            }
        });
        add(btnExcluirLancamento);
        btnExcluirLancamento.setBounds(710, 460, 180, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Impressora 16px.png"))); // NOI18N
        jButton1.setToolTipText("Imprima a movimentação deste caixa");
        add(jButton1);
        jButton1.setBounds(190, 80, 50, 30);

        btnDesliquidarLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDesliquidarLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Voltar 16px.png"))); // NOI18N
        btnDesliquidarLancamentos.setText("Desliquidar");
        btnDesliquidarLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesliquidarLancamentosActionPerformed(evt);
            }
        });
        add(btnDesliquidarLancamentos);
        btnDesliquidarLancamentos.setBounds(140, 460, 180, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarNovoLancamentoActionPerformed
        
        if (txtDescricaoNovoLancamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a descrição do lançamento!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtDescricaoNovoLancamento.grabFocus();
        } else if (txtValorNovoLancamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o valor do lançamento!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtValorNovoLancamento.grabFocus();
        } else {
            String forma = comboFormaNovoLancamento.getSelectedItem().toString();
            //Adicionando a movimentação
            Class_Caixa caixa = new Class_Caixa();
            int id_caixa = caixa.getIdCaixa(id_centro_custo);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String data = sdf.format(new Date());
            
            caixa.registraMovimentacaoCaixa(id_caixa, txtDescricaoNovoLancamento.getText(), forma, 1, 
                    txtValorNovoLancamento.getText(), comboTipoNovoLancamento.getSelectedItem().toString(), id_usuario, 
                    data);
            caixa.carregaMovimentacoesCaixa((DefaultTableModel) tabelaMovimentacoesCaixa.getModel(), id_caixa);
            
            int id_movimentacao_caixa = caixa.getIdUltimaMovimentacaoCaixa();
            if (comboTipoNovoLancamento.getSelectedItem().toString().equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                receitas.cadastraReceita(txtDescricaoNovoLancamento.getText(), 0, "", "Entrada no caixa", forma, 
                        txtValorNovoLancamento.getText(), "0", "0", 1, id_movimentacao_caixa, 0, data, data, 1, 0);
            } else if (comboTipoNovoLancamento.getSelectedItem().equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.cadastraDespesa(0, txtDescricaoNovoLancamento.getText(), nome_usuario, "", 1, data, data, forma, 
                        txtValorNovoLancamento.getText(), "0", "0", 0, 1, "Saída no caixa", id_usuario, 
                        id_movimentacao_caixa, 0, 1, 0);
            }
            
            Class_Centros_Custo centros = new Class_Centros_Custo();
            centros.alteraSaldoCentroCusto(comboTipoNovoLancamento.getSelectedItem().toString(), "0,00",
                    txtValorNovoLancamento.getText(), id_centro_custo);
            
            JOptionPane.showMessageDialog(null, "Lançamento realizado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            Novo_Lancamento.dispose();
        }
        
    }//GEN-LAST:event_btnSalvarNovoLancamentoActionPerformed

    private void btnAbrirFecharCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirFecharCaixaActionPerformed
        
        if (btnAbrirFecharCaixa.getText().equals("Abrir caixa"))
        {
            if (JOptionPane.showConfirmDialog(null, "Deseja realmente abrir o caixa?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                String valor = JOptionPane.showInputDialog(null, "Informe o valor de abertura", "Atenção", JOptionPane.PLAIN_MESSAGE);
                if (valor.isEmpty() || valor == null) {
                    valor = "0,00";
                }
                
                Class_Consumir_Letras cons = new Class_Consumir_Letras();
                valor = cons.retiraLetras(valor);
                
                Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
                float Valor = troca.trocaVirgulaPorPonto(valor);
                valor = String.valueOf(Valor);

                Class_Caixa caixa = new Class_Caixa();
                caixa.abrirCaixa(id_centro_custo);
                int id_caixa = caixa.getIdCaixa(id_centro_custo);
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String data_pagamento = sdf.format(new Date());
                
                caixa.registraMovimentacaoCaixa(id_caixa, "Abertura do caixa", "Dinheiro", 1, valor, "Abertura", id_usuario, 
                        data_pagamento);
                caixa.carregaMovimentacoesCaixa((DefaultTableModel) tabelaMovimentacoesCaixa.getModel(), id_caixa);
                
                Class_Centros_Custo centros = new Class_Centros_Custo();
                centros.alteraSaldoCentroCusto("Receita", "0,00", valor, id_centro_custo);
                
                btnAbrirFecharCaixa.setText("Fechar caixa");
            }
        }
        else
        {
            if (JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o caixa? Não será possível abrí-lo novamente!", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                Class_Caixa caixa = new Class_Caixa();
                int id_caixa = caixa.getIdCaixa(id_centro_custo);
                caixa.fecharCaixa(id_caixa);
                DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesCaixa.getModel();
                modelo.setRowCount(0);
                btnAbrirFecharCaixa.setText("Abrir caixa");
            }
        }

    }//GEN-LAST:event_btnAbrirFecharCaixaActionPerformed

    private void btnNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoLancamentoActionPerformed

        if (btnAbrirFecharCaixa.getText().equals("Abrir caixa")) {
            JOptionPane.showMessageDialog(null, "Abra uma caixa para fazer um lançamento!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {                        
            Novo_Lancamento.setBounds(0, 0, 690, 410);
            Novo_Lancamento.setLocationRelativeTo(null);
            Novo_Lancamento.setVisible(true);
            
            limpaCamposNovoLancamento();
        }

    }//GEN-LAST:event_btnNovoLancamentoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        
        limpaCamposNovoLancamento();
        
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairNovoLancamentoActionPerformed
        
        Novo_Lancamento.dispose();
        
    }//GEN-LAST:event_btnSairNovoLancamentoActionPerformed

    private void txtValorNovoLancamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorNovoLancamentoKeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890,.", evt);
        
    }//GEN-LAST:event_txtValorNovoLancamentoKeyTyped

    private void btnAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarLancamentoActionPerformed
        
        int linha = tabelaMovimentacoesCaixa.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento para alterar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            Class_Usuarios usuarios = new Class_Usuarios();
            if (!usuarios.getTipoUsuario(nome_usuario).equals("Administrador")) {
                Painel_Senha_Administrador painel_senha = new Painel_Senha_Administrador();
                painel_senha.txtSenha.grabFocus();
                JOptionPane.showMessageDialog(null, new Object[]{painel_senha.lblSenha, painel_senha.txtSenha}, "Atenção", JOptionPane.PLAIN_MESSAGE);
                String senha = painel_senha.txtSenha.getText();
                if (usuarios.verificaSenhaAdministrador(senha) == true) {
                    carregaCamposAlterarLancamento(linha);
                } else {
                    JOptionPane.showMessageDialog(null, "Senha inválida!", "Atenção", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                carregaCamposAlterarLancamento(linha);
            }
        }
        
    }//GEN-LAST:event_btnAlterarLancamentoActionPerformed

    private void txtValorAlterarLancamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAlterarLancamentoKeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890,.", evt);
        
    }//GEN-LAST:event_txtValorAlterarLancamentoKeyTyped

    private void btnSalvarAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlterarLancamentoActionPerformed
        
        if (txtDescricaoAlterarLancamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a descrição do lançamento!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtDescricaoAlterarLancamento.grabFocus();
        } else if (txtValorAlterarLancamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o valor do lançamento!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtValorAlterarLancamento.grabFocus();
        } else {
            DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesCaixa.getModel();
            int id_movimentacao = Integer.valueOf(modelo.getValueAt(tabelaMovimentacoesCaixa.getSelectedRow(), 6).toString());
            
            Class_Caixa caixa = new Class_Caixa();
            caixa.alteraMovimentacaoCaixa(id_movimentacao, txtDescricaoAlterarLancamento.getText(), 
                    comboFormaAlterarLancamento.getSelectedItem().toString(), txtValorAlterarLancamento.getText());
            
            int id_caixa = caixa.getIdCaixa(id_centro_custo);
            caixa.carregaMovimentacoesCaixa((DefaultTableModel) tabelaMovimentacoesCaixa.getModel(), id_caixa);
            
            if (lblTipoAlterarLancamento.getText().equals("Receita")) {                
                Class_Receitas receitas = new Class_Receitas();
                receitas.alteraReceitaPelaMovimentacaoCaixa(id_movimentacao, txtDescricaoAlterarLancamento.getText(),
                        comboFormaAlterarLancamento.getSelectedItem().toString(), txtValorAlterarLancamento.getText());
            } else if (lblTipoAlterarLancamento.getText().equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.alteraDespesaPelaMovimentacaoCaixa(id_movimentacao, txtDescricaoAlterarLancamento.getText(),
                        comboFormaAlterarLancamento.getSelectedItem().toString(), txtValorAlterarLancamento.getText());                
            }
            
            Class_Centros_Custo centros = new Class_Centros_Custo();
            centros.alteraSaldoCentroCusto(lblTipoAlterarLancamento.getText(), valor_lancamento,
                    txtValorAlterarLancamento.getText(), id_centro_custo);
            
            JOptionPane.showMessageDialog(null, "Lançamento alterado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            Alterar_Lancamento.dispose();
        }
        
    }//GEN-LAST:event_btnSalvarAlterarLancamentoActionPerformed

    private void btnSairAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairAlterarLancamentoActionPerformed
        
        Alterar_Lancamento.dispose();
        
    }//GEN-LAST:event_btnSairAlterarLancamentoActionPerformed

    private void btnExcluirLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirLancamentoActionPerformed
        
        int linha = tabelaMovimentacoesCaixa.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (tabelaMovimentacoesCaixa.getModel().getValueAt(linha, 3).toString().equals("Abertura")) {
                JOptionPane.showMessageDialog(null, "A abertura do caixa não pode ser excluída!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                Class_Usuarios usuarios = new Class_Usuarios();
                if (!usuarios.getTipoUsuario(nome_usuario).equals("Administrador")) {
                    Painel_Senha_Administrador painel_senha = new Painel_Senha_Administrador();
                    painel_senha.txtSenha.grabFocus();
                    JOptionPane.showMessageDialog(null, new Object[]{painel_senha.lblSenha, painel_senha.txtSenha}, "Atenção", JOptionPane.PLAIN_MESSAGE);
                    String senha = painel_senha.txtSenha.getText();
                    if (usuarios.verificaSenhaAdministrador(senha) == true) {
                        excluiMovimentacao(linha);
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha inválida!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    excluiMovimentacao(linha);
                }
            }
        }
        
    }//GEN-LAST:event_btnExcluirLancamentoActionPerformed

    private void btnDesliquidarLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesliquidarLancamentosActionPerformed

        int linha = tabelaMovimentacoesCaixa.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento na tabela para desliquidar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            Class_Usuarios usuarios = new Class_Usuarios();
            if (!usuarios.getTipoUsuario(nome_usuario).equals("Administrador")) {
                Painel_Senha_Administrador painel_senha = new Painel_Senha_Administrador();
                painel_senha.txtSenha.grabFocus();
                JOptionPane.showMessageDialog(null, new Object[]{painel_senha.lblSenha, painel_senha.txtSenha}, "Atenção", JOptionPane.PLAIN_MESSAGE);
                String senha = painel_senha.txtSenha.getText();
                if (usuarios.verificaSenhaAdministrador(senha) == true) {
                    desliquidarMovimentacao(linha);
                } else {
                    JOptionPane.showMessageDialog(null, "Senha inválida!", "Atenção", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                desliquidarMovimentacao(linha);
            }
        }

    }//GEN-LAST:event_btnDesliquidarLancamentosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Alterar_Lancamento;
    private javax.swing.JDialog Novo_Lancamento;
    private javax.swing.JButton btnAbrirFecharCaixa;
    private javax.swing.JButton btnAlterarLancamento;
    private javax.swing.JButton btnDesliquidarLancamentos;
    private javax.swing.JButton btnExcluirLancamento;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovoLancamento;
    private javax.swing.JButton btnSairAlterarLancamento;
    private javax.swing.JButton btnSairNovoLancamento;
    private javax.swing.JButton btnSalvarAlterarLancamento;
    private javax.swing.JButton btnSalvarNovoLancamento;
    private javax.swing.JComboBox comboFormaAlterarLancamento;
    private javax.swing.JComboBox comboFormaNovoLancamento;
    private javax.swing.JComboBox comboTipoNovoLancamento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTipoAlterarLancamento;
    private javax.swing.JLabel lblTituloCaixa;
    private javax.swing.JTable tabelaMovimentacoesCaixa;
    private javax.swing.JTextField txtDescricaoAlterarLancamento;
    private javax.swing.JTextField txtDescricaoNovoLancamento;
    private javax.swing.JTextField txtValorAlterarLancamento;
    private javax.swing.JTextField txtValorNovoLancamento;
    // End of variables declaration//GEN-END:variables
}