
package centros_custo;

import financeiro.Class_Despesas;
import financeiro.Class_Receitas;
import financeiro.Class_Setores_Financeiros;
import renderers.Class_Renderer_Receitas_Despesas;
import formas_pagamento.Class_Formas_Pagto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import principal.Class_Troca_Virgula_Por_Ponto;
import usuarios.Class_Usuarios;

public class Painel_Agendamentos extends javax.swing.JPanel {
    
    protected int id_usuario;
    protected String nome_usuario;

    public Painel_Agendamentos(int id_usuario, String nome_usuario) {
        initComponents();
        
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        
        tabelaAgendamentos.setDefaultRenderer(Object.class, new Class_Renderer_Receitas_Despesas());
        
        refreshLancamentos();
    }
    
    public void refreshLancamentos() {
        
        int flagReceita = 0, flagDespesa = 0;
        String data_de, data_ate;
        
        //PRIMEIRO VERIFICO O QUE DEVO PESQUISAR (TODOS, RECEITA OU DESPESA).
        String pesquisa = comboPesquisaLancamentos.getSelectedItem().toString();
        if (pesquisa.equals("Todos")) {
            flagReceita = 1;
            flagDespesa = 1;
        } else if (pesquisa.equals("Receita")) {
            flagReceita = 1;
        } else {
            flagDespesa = 1;
        }
        
        //REALIZO A FORMATAÇÃO DAS DATAS PARA A PESQUISA.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        data_de = sdf.format(dataPesquisa1Lancamentos.getDate())+" 00:00:00";
        data_ate = sdf.format(dataPesquisa2Lancamentos.getDate())+" 23:59:59";
        
        //AGORA FAÇO A PESQUISA.
        DefaultTableModel modelo = (DefaultTableModel) tabelaAgendamentos.getModel();
        modelo.setRowCount(0);
        if (flagReceita == 1) {
            modelo.addRow(new Object[] {"--- RECEITAS ---", "", "", "", "", "", ""});
            Class_Receitas receitas = new Class_Receitas();
            receitas.carregaReceitas("Não liquidado", data_de, data_ate, txtDescricaoPesquisar.getText(), 
                    comboCategoriaPesquisar.getSelectedItem().toString(), modelo);
        }
        if (flagDespesa == 1) {
            modelo.addRow(new Object[] {"--- DESPESAS ---", "", "", "", "", "", ""});
            Class_Despesas despesas = new Class_Despesas();
            despesas.carregaDespesas("Não liquidado", data_de, data_ate, txtDescricaoPesquisar.getText(), 
                    comboCategoriaPesquisar.getSelectedItem().toString(), modelo);
        }
    }
    
    public void carregaSetores(String tipo) {
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        setores.carregaSetoresFinanceirosComboBox(comboCategoriaPesquisar, tipo, "Todos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        liquidar_lancamentos = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        comboFormaPagto = new javax.swing.JComboBox();
        lblValor = new javax.swing.JLabel();
        lblQntParcelas = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblParcelaNumero = new javax.swing.JLabel();
        lblDataVencimento = new javax.swing.JLabel();
        comboCentroCusto = new javax.swing.JComboBox();
        btnLiquidarDefinitivo = new javax.swing.JButton();
        btnSairLiquidar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnLiquidarLancamentos = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaAgendamentos = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        comboPesquisaLancamentos = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        dataPesquisa1Lancamentos = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        dataPesquisa2Lancamentos = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        btnPesquisarLancamento = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtDescricaoPesquisar = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        comboCategoriaPesquisar = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();

        liquidar_lancamentos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        liquidar_lancamentos.setTitle("Liquidar lançamento");
        liquidar_lancamentos.setModal(true);
        liquidar_lancamentos.setResizable(false);
        liquidar_lancamentos.getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Agendamentos 24px.png"))); // NOI18N
        jLabel1.setText("Liquidar lançamento");
        liquidar_lancamentos.getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 30, 650, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Descrição");
        liquidar_lancamentos.getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 120, 63, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Forma de pagamento");
        liquidar_lancamentos.getContentPane().add(jLabel3);
        jLabel3.setBounds(70, 160, 136, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Valor");
        liquidar_lancamentos.getContentPane().add(jLabel4);
        jLabel4.setBounds(170, 200, 33, 17);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Parcelas");
        liquidar_lancamentos.getContentPane().add(jLabel6);
        jLabel6.setBounds(150, 240, 55, 17);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Data de vencimento");
        liquidar_lancamentos.getContentPane().add(jLabel7);
        jLabel7.setBounds(80, 280, 126, 17);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText(" Tipo");
        liquidar_lancamentos.getContentPane().add(jLabel8);
        jLabel8.setBounds(170, 90, 40, 17);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Escolha em qual centro de custo vai entrar o lançamento");
        liquidar_lancamentos.getContentPane().add(jLabel9);
        jLabel9.setBounds(70, 330, 500, 17);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Centro de custo");
        liquidar_lancamentos.getContentPane().add(jLabel10);
        jLabel10.setBounds(110, 370, 101, 17);

        lblTipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 0, 0));
        lblTipo.setText("lblTipo");
        liquidar_lancamentos.getContentPane().add(lblTipo);
        lblTipo.setBounds(240, 90, 330, 17);

        lblDescricao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDescricao.setText("lblDescricao");
        liquidar_lancamentos.getContentPane().add(lblDescricao);
        lblDescricao.setBounds(240, 120, 330, 17);

        comboFormaPagto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboFormaPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormaPagtoActionPerformed(evt);
            }
        });
        liquidar_lancamentos.getContentPane().add(comboFormaPagto);
        comboFormaPagto.setBounds(240, 150, 330, 30);

        lblValor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblValor.setText("lblValor");
        liquidar_lancamentos.getContentPane().add(lblValor);
        lblValor.setBounds(240, 200, 330, 17);

        lblQntParcelas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblQntParcelas.setForeground(new java.awt.Color(255, 0, 0));
        lblQntParcelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQntParcelas.setText("lblQntParcelas");
        liquidar_lancamentos.getContentPane().add(lblQntParcelas);
        lblQntParcelas.setBounds(230, 240, 100, 17);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Parcela número");
        liquidar_lancamentos.getContentPane().add(jLabel17);
        jLabel17.setBounds(340, 240, 100, 17);

        lblParcelaNumero.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblParcelaNumero.setForeground(new java.awt.Color(255, 0, 0));
        lblParcelaNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblParcelaNumero.setText("lblParcelaNumero");
        liquidar_lancamentos.getContentPane().add(lblParcelaNumero);
        lblParcelaNumero.setBounds(450, 240, 120, 17);

        lblDataVencimento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDataVencimento.setText("lblDataVencimento");
        liquidar_lancamentos.getContentPane().add(lblDataVencimento);
        lblDataVencimento.setBounds(240, 280, 330, 17);

        comboCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        liquidar_lancamentos.getContentPane().add(comboCentroCusto);
        comboCentroCusto.setBounds(240, 360, 330, 30);

        btnLiquidarDefinitivo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLiquidarDefinitivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnLiquidarDefinitivo.setText("Liquidar");
        btnLiquidarDefinitivo.setToolTipText("Liquida o lançamento e o lança no centro de custo selecionado");
        btnLiquidarDefinitivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiquidarDefinitivoActionPerformed(evt);
            }
        });
        liquidar_lancamentos.getContentPane().add(btnLiquidarDefinitivo);
        btnLiquidarDefinitivo.setBounds(210, 440, 110, 30);

        btnSairLiquidar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairLiquidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairLiquidar.setText("Sair");
        btnSairLiquidar.setToolTipText("Cancela liquidação do lançamento");
        btnSairLiquidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairLiquidarActionPerformed(evt);
            }
        });
        liquidar_lancamentos.getContentPane().add(btnSairLiquidar);
        btnSairLiquidar.setBounds(330, 440, 110, 30);

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Agendamentos 24px.png"))); // NOI18N
        jLabel5.setText("Lançamentos agendados");
        add(jLabel5);
        jLabel5.setBounds(0, 30, 1020, 30);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Estão listados abaixo os lançamentos de receita e despesa agendados pelo administrador (inclui parcelas de compras e vendas)");
        add(jLabel11);
        jLabel11.setBounds(20, 190, 960, 17);

        btnLiquidarLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLiquidarLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnLiquidarLancamentos.setText("Liquidar");
        btnLiquidarLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiquidarLancamentosActionPerformed(evt);
            }
        });
        add(btnLiquidarLancamentos);
        btnLiquidarLancamentos.setBounds(440, 540, 170, 30);

        tabelaAgendamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaAgendamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Forma Pagto", "Valor", "Parcelas", "Nº Parcela", "Vencimento", "Liquidado", "Tipo", "Cod"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAgendamentos.setRowHeight(25);
        tabelaAgendamentos.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabelaAgendamentos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tabelaAgendamentos);
        if (tabelaAgendamentos.getColumnModel().getColumnCount() > 0) {
            tabelaAgendamentos.getColumnModel().getColumn(0).setPreferredWidth(250);
            tabelaAgendamentos.getColumnModel().getColumn(1).setPreferredWidth(190);
            tabelaAgendamentos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaAgendamentos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabelaAgendamentos.getColumnModel().getColumn(4).setPreferredWidth(60);
            tabelaAgendamentos.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabelaAgendamentos.getColumnModel().getColumn(6).setPreferredWidth(60);
            tabelaAgendamentos.getColumnModel().getColumn(7).setPreferredWidth(50);
            tabelaAgendamentos.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        add(jScrollPane4);
        jScrollPane4.setBounds(20, 220, 960, 300);

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText("A pesquisar");
        add(jLabel28);
        jLabel28.setBounds(20, 100, 80, 17);

        comboPesquisaLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboPesquisaLancamentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Receita", "Despesa" }));
        comboPesquisaLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPesquisaLancamentosActionPerformed(evt);
            }
        });
        add(comboPesquisaLancamentos);
        comboPesquisaLancamentos.setBounds(110, 90, 120, 30);

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Período");
        add(jLabel29);
        jLabel29.setBounds(270, 100, 49, 17);

        dataPesquisa1Lancamentos.setDate(new Date());
        dataPesquisa1Lancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataPesquisa1Lancamentos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataPesquisa1LancamentosPropertyChange(evt);
            }
        });
        add(dataPesquisa1Lancamentos);
        dataPesquisa1Lancamentos.setBounds(330, 90, 140, 30);

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Até");
        add(jLabel30);
        jLabel30.setBounds(490, 100, 21, 17);

        dataPesquisa2Lancamentos.setDate(new Date());
        dataPesquisa2Lancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataPesquisa2Lancamentos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataPesquisa2LancamentosPropertyChange(evt);
            }
        });
        add(dataPesquisa2Lancamentos);
        dataPesquisa2Lancamentos.setBounds(530, 90, 140, 30);

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("Situação");
        add(jLabel31);
        jLabel31.setBounds(710, 100, 55, 17);

        btnPesquisarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        btnPesquisarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarLancamentoActionPerformed(evt);
            }
        });
        add(btnPesquisarLancamento);
        btnPesquisarLancamento.setBounds(940, 90, 40, 30);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Não liquidado");
        add(jLabel13);
        jLabel13.setBounds(790, 100, 130, 17);

        jLabel44.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel44.setText("Descricao");
        add(jLabel44);
        jLabel44.setBounds(30, 150, 70, 17);

        txtDescricaoPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescricaoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoPesquisarKeyReleased(evt);
            }
        });
        add(txtDescricaoPesquisar);
        txtDescricaoPesquisar.setBounds(110, 140, 360, 30);

        jLabel45.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel45.setText("Categoria");
        add(jLabel45);
        jLabel45.setBounds(510, 150, 80, 17);

        comboCategoriaPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboCategoriaPesquisar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));
        add(comboCategoriaPesquisar);
        comboCategoriaPesquisar.setBounds(600, 140, 310, 30);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Impressora 16px.png"))); // NOI18N
        add(jButton8);
        jButton8.setBounds(940, 140, 40, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLiquidarLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiquidarLancamentosActionPerformed

        int linha = tabelaAgendamentos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento na tabela para liquidar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel modelo = (DefaultTableModel) tabelaAgendamentos.getModel();
            Object tipo = modelo.getValueAt(linha, 7);
            if (tipo == null || tipo.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione um lançamento na tabela para liquidar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                lblTipo.setText(tipo.toString());
                lblDescricao.setText(modelo.getValueAt(linha, 0).toString());
                Class_Formas_Pagto formas = new Class_Formas_Pagto();
                formas.carregaFormasPagamento(comboFormaPagto);
                comboFormaPagto.setSelectedItem(modelo.getValueAt(linha, 1).toString());
                lblValor.setText(modelo.getValueAt(linha, 2).toString());
                lblQntParcelas.setText(modelo.getValueAt(linha, 3).toString());
                lblParcelaNumero.setText(modelo.getValueAt(linha, 4).toString());
                lblDataVencimento.setText(modelo.getValueAt(linha, 5).toString());
                
                liquidar_lancamentos.setBounds(0, 0, 650, 540);
                liquidar_lancamentos.setLocationRelativeTo(null);
                liquidar_lancamentos.setVisible(true);
                

            }
        }
        
    }//GEN-LAST:event_btnLiquidarLancamentosActionPerformed

    private void dataPesquisa1LancamentosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dataPesquisa1LancamentosPropertyChange

        if (dataPesquisa2Lancamentos.getDate().before(dataPesquisa1Lancamentos.getDate())) {
            dataPesquisa2Lancamentos.setDate(dataPesquisa1Lancamentos.getDate());
        }
    }//GEN-LAST:event_dataPesquisa1LancamentosPropertyChange

    private void dataPesquisa2LancamentosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dataPesquisa2LancamentosPropertyChange

        if (dataPesquisa2Lancamentos.getDate().before(dataPesquisa1Lancamentos.getDate())) {
            dataPesquisa2Lancamentos.setDate(dataPesquisa1Lancamentos.getDate());
        }
    }//GEN-LAST:event_dataPesquisa2LancamentosPropertyChange

    private void btnPesquisarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarLancamentoActionPerformed

        refreshLancamentos();
        
    }//GEN-LAST:event_btnPesquisarLancamentoActionPerformed

    private void comboFormaPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormaPagtoActionPerformed
        
        if (comboFormaPagto.getItemCount() > 0) {
            String forma = comboFormaPagto.getSelectedItem().toString();
            Class_Centros_Custo centros = new Class_Centros_Custo();
            Class_Usuarios usuarios = new Class_Usuarios();
            String tipo_usuario = usuarios.getTipoUsuario(nome_usuario);
            if (forma.equals("Dinheiro") || forma.equals("Cheque")) {
                centros.carregaCentrosCustoComboBox(comboCentroCusto, "Todos", id_usuario, tipo_usuario);
            } else {
                centros.carregaCentrosCustoComboBox(comboCentroCusto, "Conta bancária", id_usuario, tipo_usuario);
            }
        }
        
    }//GEN-LAST:event_comboFormaPagtoActionPerformed

    private void btnSairLiquidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairLiquidarActionPerformed
        
        liquidar_lancamentos.dispose();
        
    }//GEN-LAST:event_btnSairLiquidarActionPerformed

    private void btnLiquidarDefinitivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiquidarDefinitivoActionPerformed

        //Primeiro vamos adicionar a movimentação do caixa ou da conta bancária
        
        //Aqui vamos declarar as variáveis já existentes
        String descricao = lblDescricao.getText();
        String forma_pagamento = comboFormaPagto.getSelectedItem().toString();
        String valor = lblValor.getText().replace("R$ ", "");
        String centro = comboCentroCusto.getSelectedItem().toString();
        String tipo = lblTipo.getText();
        int id_receita_despesa = Integer.valueOf(tabelaAgendamentos.getModel().getValueAt(tabelaAgendamentos.getSelectedRow(), 8).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String data_pagamento = sdf.format(new Date());
        int id_movimentacao = 0, flag = 1;
        
        //Aqui vamos declarar as variáveis de retorno
        Class_Centros_Custo centros = new Class_Centros_Custo();
        String tipo_centro_custo = centros.retornaTipoCentroCusto(centro);
        int id_centro_custo = centros.retornaIdCentroCusto(centro);
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();

        if (tipo_centro_custo.equals("Caixa")) {
            Class_Caixa caixa = new Class_Caixa();
            int id_caixa = caixa.getIdCaixa(id_centro_custo);
            if (caixa.verificaCaixaAberto(id_caixa) == false) {
                flag = 0;
                JOptionPane.showMessageDialog(null, "O caixa que você está tentando realizar o lançamento não se encontra aberto no momento.\nAntes de liquidar este lançamento você terá que abrir este caixa!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                caixa.registraMovimentacaoCaixa(id_caixa, descricao, id_forma_pagamento, 1, troca.trocaVirgulaPorPonto(valor), 
                        tipo, id_usuario, data_pagamento);
                id_movimentacao = caixa.getIdUltimaMovimentacaoCaixa();
            }
        } else {
            Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
            conta.registraMovimentacaoContaBancaria(id_centro_custo, descricao, id_forma_pagamento, 1, 
                    troca.trocaVirgulaPorPonto(valor), tipo, id_usuario, data_pagamento);
            id_movimentacao = conta.getIdUltimaMovimentacaoContaBancaria();
        }
        
        if (flag == 1) {
            //Agora vamos inserir na tabela receitas ou na tabela despesas
            if (tipo.equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                if (tipo.equals("Caixa")) {
                    receitas.liquidarReceitaPelaMovimentacaoCaixa(id_movimentacao, 0, "", id_receita_despesa, data_pagamento);
                } else {
                    receitas.liquidarReceitaPelaMovimentacaoContaBancaria(id_movimentacao, 0, "", id_receita_despesa, data_pagamento);
                }
            } else {
                Class_Despesas despesas = new Class_Despesas();
                if (tipo.equals("Caixa")) {
                    despesas.liquidarDespesaPelaMovimentacaoCaixa(id_movimentacao, 0, id_receita_despesa, data_pagamento);
                } else {
                    despesas.liquidarDespesaPelaMovimentacaoContaBancaria(id_movimentacao, 0, id_receita_despesa, data_pagamento);
                }
            }
            
            //Preciso também atualizar o saldo do centro de custo
            centros.alteraSaldoCentroCusto(tipo, "0,00", valor, id_centro_custo);
            
            //Agora carrego a tela novamente
            refreshLancamentos();
            
            //Emito uma mensagem de confirmação
            JOptionPane.showMessageDialog(null, "Lançamento liquidado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            //Para finalizar, fecho a tela de liquidar
            liquidar_lancamentos.dispose();
        }
        
    }//GEN-LAST:event_btnLiquidarDefinitivoActionPerformed

    private void txtDescricaoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoPesquisarKeyReleased

        refreshLancamentos();

    }//GEN-LAST:event_txtDescricaoPesquisarKeyReleased

    private void comboPesquisaLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPesquisaLancamentosActionPerformed
        
        String pesquisa = comboPesquisaLancamentos.getSelectedItem().toString();
        if (pesquisa.equals("Todos")) {
            comboCategoriaPesquisar.removeAllItems();
            comboCategoriaPesquisar.addItem("Todos");
        } else if (pesquisa.equals("Receita")) {
            carregaSetores("Receita");
        } else {
            carregaSetores("Despesa");
        }
        
    }//GEN-LAST:event_comboPesquisaLancamentosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLiquidarDefinitivo;
    private javax.swing.JButton btnLiquidarLancamentos;
    private javax.swing.JButton btnPesquisarLancamento;
    private javax.swing.JButton btnSairLiquidar;
    private javax.swing.JComboBox comboCategoriaPesquisar;
    private javax.swing.JComboBox comboCentroCusto;
    private javax.swing.JComboBox comboFormaPagto;
    private javax.swing.JComboBox comboPesquisaLancamentos;
    private com.toedter.calendar.JDateChooser dataPesquisa1Lancamentos;
    private com.toedter.calendar.JDateChooser dataPesquisa2Lancamentos;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblDataVencimento;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblParcelaNumero;
    private javax.swing.JLabel lblQntParcelas;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblValor;
    private javax.swing.JDialog liquidar_lancamentos;
    private javax.swing.JTable tabelaAgendamentos;
    private javax.swing.JTextField txtDescricaoPesquisar;
    // End of variables declaration//GEN-END:variables
}
