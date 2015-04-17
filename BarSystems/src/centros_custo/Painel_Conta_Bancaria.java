
package centros_custo;

import principal.Class_Consumir_Letras;
import financeiro.Class_Despesas;
import financeiro.Class_Receitas;
import formas_pagamento.Class_Formas_Pagto;
import usuarios.Class_Usuarios;
import usuarios.Painel_Senha_Administrador;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Painel_Conta_Bancaria extends javax.swing.JPanel {

    protected String nome_usuario, nome_conta, valor_lancamento;
    protected int id_centro_custo, id_usuario;
    
    public Painel_Conta_Bancaria(int id_centro_custo, String nome_conta, int id_usuario, String nome_usuario) {
        initComponents();
        
        this.nome_conta = nome_conta;
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_centro_custo = id_centro_custo;
        
        lblTituloCaixa.setText("Gerenciamento da conta bancária "+this.nome_conta+": Usuário "+this.nome_usuario);
        
        refreshMovimentacoes();
    }
    
    public void refreshMovimentacoes() {
        Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
        conta.carregaMovimentacoesContaBancaria((DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel(), 
                    id_centro_custo, dataPesquisa1.getDate(), dataPesquisa2.getDate());
    }
    
    public void limpaCamposNovoLancamento() {
        comboTipoNovoLancamento.setSelectedIndex(0);
        txtDescricaoNovoLancamento.setText("");
        txtValorNovoLancamento.setText("");
        txtDescricaoNovoLancamento.grabFocus();
    }
    
    public void carregaCamposAlterarLancamento(int linha) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel();
        lblTipoAlterarLancamento.setText(modelo.getValueAt(linha, 3).toString());
        txtDescricaoAlterarLancamento.setText(modelo.getValueAt(linha, 0).toString());
        if (lblTipoAlterarLancamento.getText().equals("Receita")) {
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            formas.carregaFormasPagamento(comboFormaAlterarLancamento);
            comboFormaAlterarLancamento.setEnabled(true);
        } else {
            comboFormaAlterarLancamento.removeAllItems();
            comboFormaAlterarLancamento.addItem("Dinheiro");
            comboFormaAlterarLancamento.setEnabled(false);
        }
        comboFormaAlterarLancamento.setSelectedItem(modelo.getValueAt(linha, 1).toString());
        txtValorAlterarLancamento.setText(modelo.getValueAt(linha, 2).toString().replace("R$ ", ""));
        valor_lancamento = txtValorAlterarLancamento.getText();
        
        Alterar_Lancamento.setBounds(0, 0, 690, 410);
        Alterar_Lancamento.setLocationRelativeTo(null);
        Alterar_Lancamento.setVisible(true);
    }
    
    public void excluiMovimentacao(int linha) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION)==0) {
            DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel();
            String valor = modelo.getValueAt(linha, 2).toString().replace("R$ ", "");
            String tipo = modelo.getValueAt(linha, 3).toString();
            int id_movimentacao = Integer.valueOf(modelo.getValueAt(linha, 6).toString());
            Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
            conta.excluiMovimentacaoContaBancaria(id_movimentacao);

            if (tipo.equals("Receita")) {                
                Class_Receitas receitas = new Class_Receitas();
                receitas.excluiReceitaPelaMovimentacaoContaBancaria(id_movimentacao);
            } else if (tipo.equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.excluiDespesaPelaMovimentacaoContaBancaria(id_movimentacao);
            }
            
            Class_Centros_Custo centros = new Class_Centros_Custo();
            centros.alteraSaldoCentroCusto(tipo, valor, "0,00", id_centro_custo);

            conta.carregaMovimentacoesContaBancaria((DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel(), 
                    id_centro_custo, dataPesquisa1.getDate(), dataPesquisa2.getDate());
            JOptionPane.showMessageDialog(null, "Lançamento excluído com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void desliquidarMovimentacao(int linha) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel();
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente desliquidar este lançamento?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
            int flag = 0;
            String tipo = modelo.getValueAt(linha, 3).toString();
            int id = Integer.valueOf(modelo.getValueAt(linha, 6).toString());
            
            if (tipo.equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                if (receitas.verificaReceitaAgendadaPelaMovimentacaoContaBancaria(id) == false) {
                    JOptionPane.showMessageDialog(null, "Esta receita não pode ser desliquidada, pois ela não foi agendada!"
                            + "\nNeste caso, você vai precisar excluí-la clicando no botão \"Excluir\"!", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    receitas.desliquidarReceitaPelaMovimentacaoContaBancaria(id);
                    flag = 1;
                }
            } else {
                Class_Despesas despesas = new Class_Despesas();
                despesas.desliquidarDespesaPelaMovimentacaoContaBancaria(id);
            }
            
            if (flag == 1) {
                Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
                conta.excluiMovimentacaoContaBancaria(id);

                conta.carregaMovimentacoesContaBancaria((DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel(), 
                        id_centro_custo, dataPesquisa1.getDate(), dataPesquisa2.getDate());

                JOptionPane.showMessageDialog(null, "Lançamento desliquidado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescricaoNovoLancamento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        tabelaMovimentacoesContaBancaria = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnNovoLancamento = new javax.swing.JButton();
        btnAlterarLancamento = new javax.swing.JButton();
        btnExcluirLancamento = new javax.swing.JButton();
        dataPesquisa1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        dataPesquisa2 = new com.toedter.calendar.JDateChooser();
        btnPesquisa = new javax.swing.JButton();
        btnDesliquidarLancamentos = new javax.swing.JButton();

        Novo_Lancamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Novo_Lancamento.setTitle("Lançamento avulso na conta bancária");
        Novo_Lancamento.setModal(true);
        Novo_Lancamento.setResizable(false);
        Novo_Lancamento.getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Símbolo de dinheiro 24px.png"))); // NOI18N
        jLabel2.setText("Lançamento avulso na conta bancária");
        Novo_Lancamento.getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 30, 671, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Descrição do lançamento");
        Novo_Lancamento.getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 150, 170, 17);

        txtDescricaoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Novo_Lancamento.getContentPane().add(txtDescricaoNovoLancamento);
        txtDescricaoNovoLancamento.setBounds(220, 140, 410, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText(" Forma de pagamento");
        Novo_Lancamento.getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 190, 150, 17);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText(" Tipo de lançamento");
        Novo_Lancamento.getContentPane().add(jLabel5);
        jLabel5.setBounds(70, 110, 140, 17);

        comboTipoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboTipoNovoLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Receita", "Despesa" }));
        comboTipoNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoNovoLancamentoActionPerformed(evt);
            }
        });
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
        btnSalvarNovoLancamento.setToolTipText("Cadastra a receita na conta bancária");
        btnSalvarNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarNovoLancamentoActionPerformed(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(btnSalvarNovoLancamento);
        btnSalvarNovoLancamento.setBounds(190, 310, 100, 30);

        btnLimpar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar 16px.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.setToolTipText("Limpa os campos para recomeçar o lançamento");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(btnLimpar);
        btnLimpar.setBounds(300, 310, 100, 30);

        btnSairNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairNovoLancamento.setText("Sair");
        btnSairNovoLancamento.setToolTipText("Cancelar lançamento na conta bancária");
        btnSairNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairNovoLancamentoActionPerformed(evt);
            }
        });
        Novo_Lancamento.getContentPane().add(btnSairNovoLancamento);
        btnSairNovoLancamento.setBounds(410, 310, 100, 30);

        comboFormaNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Novo_Lancamento.getContentPane().add(comboFormaNovoLancamento);
        comboFormaNovoLancamento.setBounds(220, 180, 410, 30);

        Alterar_Lancamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Alterar_Lancamento.setTitle("Alterar lançamento");
        Alterar_Lancamento.setModal(true);
        Alterar_Lancamento.setResizable(false);
        Alterar_Lancamento.getContentPane().setLayout(null);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Símbolo de dinheiro 24px.png"))); // NOI18N
        jLabel7.setText("Alterar lançamento na conta bancária");
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
        btnSalvarAlterarLancamento.setToolTipText("Salva as alterações realizadas");
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
        btnSairAlterarLancamento.setToolTipText("Cancela alterações");
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
        Alterar_Lancamento.getContentPane().add(comboFormaAlterarLancamento);
        comboFormaAlterarLancamento.setBounds(220, 180, 410, 30);

        setLayout(null);

        lblTituloCaixa.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblTituloCaixa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Símbolo de dinheiro 24px.png"))); // NOI18N
        lblTituloCaixa.setText("Gerenciamento da conta bancária");
        add(lblTituloCaixa);
        lblTituloCaixa.setBounds(0, 30, 1020, 29);

        tabelaMovimentacoesContaBancaria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaMovimentacoesContaBancaria.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaMovimentacoesContaBancaria.setToolTipText("Movimentações da conta bancária");
        tabelaMovimentacoesContaBancaria.setRowHeight(25);
        tabelaMovimentacoesContaBancaria.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabelaMovimentacoesContaBancaria.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelaMovimentacoesContaBancaria.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaMovimentacoesContaBancaria);
        if (tabelaMovimentacoesContaBancaria.getColumnModel().getColumnCount() > 0) {
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(0).setPreferredWidth(280);
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(1).setPreferredWidth(180);
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(5).setPreferredWidth(90);
            tabelaMovimentacoesContaBancaria.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 980, 300);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Impressora 16px.png"))); // NOI18N
        jButton1.setToolTipText("Imprima a movimentação desta conta bancária");
        add(jButton1);
        jButton1.setBounds(420, 80, 50, 30);

        btnNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovoLancamento.setText("Novo lançamento");
        btnNovoLancamento.setToolTipText("Faça lançamentos de receita e despesa na conta bancária");
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

        dataPesquisa1.setDate(new Date());
        dataPesquisa1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataPesquisa1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataPesquisa1PropertyChange(evt);
            }
        });
        add(dataPesquisa1);
        dataPesquisa1.setBounds(20, 80, 140, 30);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Até");
        add(jLabel1);
        jLabel1.setBounds(180, 90, 21, 17);

        dataPesquisa2.setDate(new Date());
        dataPesquisa2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataPesquisa2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataPesquisa2PropertyChange(evt);
            }
        });
        add(dataPesquisa2);
        dataPesquisa2.setBounds(220, 80, 140, 30);

        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        btnPesquisa.setToolTipText("Clique para pesquisar as movimentações no período escolhido");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });
        add(btnPesquisa);
        btnPesquisa.setBounds(370, 80, 50, 30);

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

    private void btnNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoLancamentoActionPerformed
        
        limpaCamposNovoLancamento();
        Novo_Lancamento.setBounds(0, 0, 690, 410);
        Novo_Lancamento.setLocationRelativeTo(null);
        Novo_Lancamento.setVisible(true);
        
    }//GEN-LAST:event_btnNovoLancamentoActionPerformed

    private void btnAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarLancamentoActionPerformed

        int linha = tabelaMovimentacoesContaBancaria.getSelectedRow();
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

    private void btnExcluirLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirLancamentoActionPerformed

        int linha = tabelaMovimentacoesContaBancaria.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
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

    }//GEN-LAST:event_btnExcluirLancamentoActionPerformed

    private void txtValorNovoLancamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorNovoLancamentoKeyTyped

        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890,.", evt);

    }//GEN-LAST:event_txtValorNovoLancamentoKeyTyped

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String data = sdf.format(new Date());

            Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
            conta.registraMovimentacaoContaBancaria(id_centro_custo, txtDescricaoNovoLancamento.getText(), forma, 1,
                txtValorNovoLancamento.getText(), comboTipoNovoLancamento.getSelectedItem().toString(), id_usuario,
                data);
            conta.carregaMovimentacoesContaBancaria((DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel(), 
                    id_centro_custo, dataPesquisa1.getDate(), dataPesquisa2.getDate());

            int id_movimentacao_conta_bancaria = conta.getIdUltimaMovimentacaoContaBancaria();
            if (comboTipoNovoLancamento.getSelectedItem().toString().equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                receitas.cadastraReceita(txtDescricaoNovoLancamento.getText(), 0, "", "Entrada na conta bancária", forma,
                    txtValorNovoLancamento.getText(), "0", "0", 1, 0, id_movimentacao_conta_bancaria, data, data, 1, 0);
            } else if (comboTipoNovoLancamento.getSelectedItem().equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.cadastraDespesa(0, txtDescricaoNovoLancamento.getText(), nome_usuario, "", 1, data, data, forma,
                    txtValorNovoLancamento.getText(), "0", "0", 0, 1, "Saída na conta bancária", id_usuario, 0,
                    id_movimentacao_conta_bancaria, 1, 0);
            }

            Class_Centros_Custo centros = new Class_Centros_Custo();
            centros.alteraSaldoCentroCusto(comboTipoNovoLancamento.getSelectedItem().toString(), "0,00",
                txtValorNovoLancamento.getText(), id_centro_custo);

            JOptionPane.showMessageDialog(null, "Lançamento realizado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            Novo_Lancamento.dispose();
        }

    }//GEN-LAST:event_btnSalvarNovoLancamentoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        
        limpaCamposNovoLancamento();

    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairNovoLancamentoActionPerformed

        Novo_Lancamento.dispose();

    }//GEN-LAST:event_btnSairNovoLancamentoActionPerformed

    private void comboTipoNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoNovoLancamentoActionPerformed
        
        if (comboTipoNovoLancamento.getSelectedItem().toString().equals("Receita")) {
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            formas.carregaFormasPagamento(comboFormaNovoLancamento);
            comboFormaNovoLancamento.setEnabled(true);
        } else {
            comboFormaNovoLancamento.removeAllItems();
            comboFormaNovoLancamento.addItem("Dinheiro");
            comboFormaNovoLancamento.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboTipoNovoLancamentoActionPerformed

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
            DefaultTableModel modelo = (DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel();
            int id_movimentacao = Integer.valueOf(modelo.getValueAt(tabelaMovimentacoesContaBancaria.getSelectedRow(), 6).toString());
            
            Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
            conta.alteraMovimentacaoContaBancaria(id_movimentacao, txtDescricaoAlterarLancamento.getText(),
                comboFormaAlterarLancamento.getSelectedItem().toString(), txtValorAlterarLancamento.getText());
            
            conta.carregaMovimentacoesContaBancaria((DefaultTableModel) tabelaMovimentacoesContaBancaria.getModel(), 
                    id_centro_custo, dataPesquisa1.getDate(), dataPesquisa2.getDate());

            if (lblTipoAlterarLancamento.getText().equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                receitas.alteraReceitaPelaMovimentacaoContaBancaria(id_movimentacao, txtDescricaoAlterarLancamento.getText(),
                    comboFormaAlterarLancamento.getSelectedItem().toString(), txtValorAlterarLancamento.getText());
            } else if (lblTipoAlterarLancamento.getText().equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.alteraDespesaPelaMovimentacaoContaBancaria(id_movimentacao, txtDescricaoAlterarLancamento.getText(),
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

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        
        refreshMovimentacoes();
        
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnDesliquidarLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesliquidarLancamentosActionPerformed

        int linha = tabelaMovimentacoesContaBancaria.getSelectedRow();
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

    private void dataPesquisa1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dataPesquisa1PropertyChange
        
        if (dataPesquisa2.getDate().before(dataPesquisa1.getDate())) {
            dataPesquisa2.setDate(dataPesquisa1.getDate());
        }
        
    }//GEN-LAST:event_dataPesquisa1PropertyChange

    private void dataPesquisa2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dataPesquisa2PropertyChange
        
        if (dataPesquisa2.getDate().before(dataPesquisa1.getDate())) {
            dataPesquisa2.setDate(dataPesquisa1.getDate());
        }
        
    }//GEN-LAST:event_dataPesquisa2PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Alterar_Lancamento;
    private javax.swing.JDialog Novo_Lancamento;
    private javax.swing.JButton btnAlterarLancamento;
    private javax.swing.JButton btnDesliquidarLancamentos;
    private javax.swing.JButton btnExcluirLancamento;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovoLancamento;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSairAlterarLancamento;
    private javax.swing.JButton btnSairNovoLancamento;
    private javax.swing.JButton btnSalvarAlterarLancamento;
    private javax.swing.JButton btnSalvarNovoLancamento;
    private javax.swing.JComboBox comboFormaAlterarLancamento;
    private javax.swing.JComboBox comboFormaNovoLancamento;
    private javax.swing.JComboBox comboTipoNovoLancamento;
    private com.toedter.calendar.JDateChooser dataPesquisa1;
    private com.toedter.calendar.JDateChooser dataPesquisa2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTipoAlterarLancamento;
    private javax.swing.JLabel lblTituloCaixa;
    private javax.swing.JTable tabelaMovimentacoesContaBancaria;
    private javax.swing.JTextField txtDescricaoAlterarLancamento;
    private javax.swing.JTextField txtDescricaoNovoLancamento;
    private javax.swing.JTextField txtValorAlterarLancamento;
    private javax.swing.JTextField txtValorNovoLancamento;
    // End of variables declaration//GEN-END:variables
}
