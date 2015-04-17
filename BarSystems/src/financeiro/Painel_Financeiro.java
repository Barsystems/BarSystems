
package financeiro;

import renderers.Class_Renderer_Receitas_Despesas;
import centros_custo.Class_Caixa;
import centros_custo.Class_Centros_Custo;
import formas_pagamento.Class_Formas_Pagto;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import principal.Class_Consumir_Letras;
import principal.Class_Manipular_Data;
import principal.Class_Troca_Virgula_Por_Ponto;
import principal.Class_Verifica_Menu_Aberto;
import usuarios.Class_Usuarios;

public class Painel_Financeiro extends javax.swing.JPanel {

    protected JTabbedPane painel_principal;
    protected int id_usuario;
    protected String nome_usuario;
    protected String id_centro_custo;
    
    public Painel_Financeiro(JTabbedPane painel_principal, int id_usuario, String nome_usuario) {
        initComponents();
        
        this.painel_principal = painel_principal;
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        
        tabelaLancamentos.setDefaultRenderer(Object.class, new Class_Renderer_Receitas_Despesas());
    }
    
    public void carregaLancamentos() {
        
        int flagReceita = 0, flagDespesa = 0;
        String liquidado = comboSituacaoLancamentos.getSelectedItem().toString(), data_de, data_ate;
        
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
        float valor_receitas = 0, valor_despesas = 0;
        
        //AGORA FAÇO A PESQUISA.
        DefaultTableModel modelo = (DefaultTableModel) tabelaLancamentos.getModel();
        modelo.setRowCount(0);
        if (flagReceita == 1) {
            modelo.addRow(new Object[] {"--- RECEITAS ---", "", "", "", "", "", ""});
            Class_Receitas receitas = new Class_Receitas();
            valor_receitas = receitas.carregaReceitas(liquidado, data_de, data_ate, txtDescricaoPesquisar.getText(), 
                    comboCategoriaPesquisar.getSelectedItem().toString(), modelo);
        }
        if (flagDespesa == 1) {
            modelo.addRow(new Object[] {"--- DESPESAS ---", "", "", "", "", "", ""});
            Class_Despesas despesas = new Class_Despesas();
            valor_despesas = despesas.carregaDespesas(liquidado, data_de, data_ate, txtDescricaoPesquisar.getText(), 
                    comboCategoriaPesquisar.getSelectedItem().toString(), modelo);
        }
        
        NumberFormat z = NumberFormat.getCurrencyInstance();
        lblTotalReceitas.setText(z.format(valor_receitas));
        lblTotalDespesas.setText(z.format(valor_despesas));
        lblValorTotal.setText(z.format(valor_receitas - valor_despesas)); 
    }
    
    public void limpaCamposNovoLancamento() {
        comboTipoNovoLancamento.setSelectedIndex(0);
        txtDescricaoNovoLancamento.setText("");
        txtValorNovoLancamento.setText("");
        comboParcelasNovoLancamento.setSelectedIndex(0);
        comboCategoriaNovoAgendamento.setSelectedIndex(0);
        dataVencimentoNovoLancamento.setDate(new Date());
        txtDescricaoNovoLancamento.grabFocus();
    }
    
    public void carregaSetores(String tipo) {
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        setores.carregaSetoresFinanceirosComboBox(comboCategoriaPesquisar, tipo, "Todos");
    }
    
    public void refreshListSetores() {
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        listSetores.setModel(setores.refreshList());
    }
    
    public void carregaDadosLancamento(DefaultTableModel tabela, int linha, int cod) {
        //PRECISAREMOS USAR ESTAS CLASSES PARA TRAZER O SETOR E A DATA DE PAGAMENTO
        Class_Receitas receitas = new Class_Receitas();
        Class_Despesas despesas = new Class_Despesas();
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        Class_Manipular_Data manipular = new Class_Manipular_Data();

        //COLETAMOS OS DADOS PARA APRESENTAR NOS CAMPOS
        String tipo = tabela.getValueAt(linha, 7).toString();
        comboTipoAlterarLancamento.setSelectedItem(tipo);
        txtDescricaoAlterarLancamento.setText(tabela.getValueAt(linha, 0).toString());
        formas.carregaFormasPagamento(comboFormaAlterarLancamento);
        comboFormaAlterarLancamento.setSelectedItem(tabela.getValueAt(linha, 1).toString());
        txtValorAlterarLancamento.setText(tabela.getValueAt(linha, 2).toString().replace("R$ ", ""));
        lblNumeroParcelasAlterarLancamento.setText(tabela.getValueAt(linha, 3).toString());
        lblParcelaNumeroAlterarlancamento.setText(tabela.getValueAt(linha, 4).toString());
        dataVencimentoAlterarLancamento.setDate(manipular.transformaDataStringParaDate(tabela.getValueAt(linha, 5).toString()));
        //SE FOR RECEITA DEVEM SER APRESENTADOS OS SETORES DE RECEITAS, E VICE VERSA
        if (tipo.equals("Receita")) {
            comboCategoriaAlterarAgendamento.setSelectedItem(receitas.retornaSetorReceita(cod));
        } else {
            comboCategoriaAlterarAgendamento.setSelectedItem(despesas.retornaSetorDespesa(cod));
        }
        lblStatusAlterarLancamento.setText(tabela.getValueAt(linha, 6).toString());

        //AQUI PESQUISAMOS PARA VERIFICAR SE JÁ FOI PAGO OU NÃO. DEPENDENDO DA SITUAÇÃO É UMA MENSAGEM DIFERENTE
        String liquidado = tabela.getValueAt(linha, 6).toString();
        if (liquidado.equals("Sim")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String pagamento;
            if (tipo.equals("Receita")) {
                pagamento = sdf.format(receitas.retornaDataPagamentoReceita(cod));
            } else {
                pagamento = sdf.format(despesas.retornaDataPagamentoDespesa(cod));
            }
            lblDataPagamentoAlterarLancamento.setText(pagamento);
            
            comboFormaAlterarLancamento.setEnabled(false);
            txtValorAlterarLancamento.setEnabled(false);
            dataVencimentoAlterarLancamento.setEnabled(false);
            comboCategoriaAlterarAgendamento.setEnabled(false);
        } else {
            lblDataPagamentoAlterarLancamento.setText(" ----- ");
            
            comboFormaAlterarLancamento.setEnabled(true);
            txtValorAlterarLancamento.setEnabled(true);
            dataVencimentoAlterarLancamento.setEnabled(true);
            comboCategoriaAlterarAgendamento.setEnabled(true);
        }
        comboTipoAlterarLancamento.setEnabled(false);
        
        txtDescricaoAlterarLancamento.grabFocus();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Centros_Custo = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_centros_custo = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtNomeCentro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSaldoCentro = new javax.swing.JTextField();
        btnNovoCentroCusto = new javax.swing.JButton();
        btnAlterarCentrosCusto = new javax.swing.JButton();
        btnExcluirCentrosCusto = new javax.swing.JButton();
        btnSairCentrosCusto = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtTipoCentroCusto = new javax.swing.JTextField();
        btnTrnaferirSaldoCentroCusto = new javax.swing.JButton();
        Novo_Centro_Custo = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNomeCentro1 = new javax.swing.JTextField();
        btnSalvarCentroCusto = new javax.swing.JButton();
        btnSairCadastroCentroCusto = new javax.swing.JButton();
        btnLimparDadosCentroCusto = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        radioCaixa = new javax.swing.JRadioButton();
        radioContaBancaria = new javax.swing.JRadioButton();
        Alterar_Centro_Custo = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNomeCentro2 = new javax.swing.JTextField();
        btnSalvarCentroCusto2 = new javax.swing.JButton();
        btnSairCadastroCentroCusto2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblTipoCentroCusto = new javax.swing.JLabel();
        Transferir_Saldo = new javax.swing.JDialog();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lblCentroCustoTransferirSaldo = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        lblSaldoDisponivelTransferirSaldo = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtSaldoTransferirSaldo = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        comboCentroDestinoTransferirSaldo = new javax.swing.JComboBox();
        btnSalvarTransferirSaldo = new javax.swing.JButton();
        btnSairTransferirSaldo = new javax.swing.JButton();
        Configurar_Responsaveis_Caixa = new javax.swing.JDialog();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_caixas = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        lblCaixaSelecionado = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista_responsaveis = new javax.swing.JList();
        btnSairConfiguracaoResponsaveis = new javax.swing.JButton();
        btnRetirarResponsavelCaixa = new javax.swing.JButton();
        btnAdicionarResponsavelCaixa = new javax.swing.JButton();
        Setores = new javax.swing.JDialog();
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
        Lancamentos = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        comboPesquisaLancamentos = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        dataPesquisa1Lancamentos = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        dataPesquisa2Lancamentos = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        comboSituacaoLancamentos = new javax.swing.JComboBox();
        btnPesquisarLancamento = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaLancamentos = new javax.swing.JTable();
        btnAlterarLancamentos = new javax.swing.JButton();
        btnNovoLancamentos = new javax.swing.JButton();
        btnExcluirLancamentos = new javax.swing.JButton();
        btnSairLancamentos = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        lblTotalReceitas = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblTotalDespesas = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtDescricaoPesquisar = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        comboCategoriaPesquisar = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        Agendar_Lancamento = new javax.swing.JDialog();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtDescricaoNovoLancamento = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        comboTipoNovoLancamento = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        txtValorNovoLancamento = new javax.swing.JTextField();
        btnSalvarNovoLancamento = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSairNovoLancamento = new javax.swing.JButton();
        comboFormaNovoLancamento = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        comboParcelasNovoLancamento = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        comboCategoriaNovoAgendamento = new javax.swing.JComboBox();
        jLabel61 = new javax.swing.JLabel();
        dataVencimentoNovoLancamento = new com.toedter.calendar.JDateChooser();
        Editar_Lancamento = new javax.swing.JDialog();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtDescricaoAlterarLancamento = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        comboTipoAlterarLancamento = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        txtValorAlterarLancamento = new javax.swing.JTextField();
        btnSalvarAlterarLancamento = new javax.swing.JButton();
        btnSairAlterarLancamento = new javax.swing.JButton();
        comboFormaAlterarLancamento = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        comboCategoriaAlterarAgendamento = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        dataVencimentoAlterarLancamento = new com.toedter.calendar.JDateChooser();
        jLabel63 = new javax.swing.JLabel();
        lblParcelaNumeroAlterarlancamento = new javax.swing.JLabel();
        lblNumeroParcelasAlterarLancamento = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lblStatusAlterarLancamento = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        lblDataPagamentoAlterarLancamento = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCentrosCusto = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnConfigurarCentros = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnLancamentos = new javax.swing.JButton();
        btnSairPainelFinanceiro = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        Centros_Custo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Centros_Custo.setTitle("Manutenção do cadastro de centros de custo");
        Centros_Custo.setModal(true);
        Centros_Custo.setResizable(false);
        Centros_Custo.getContentPane().setLayout(null);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caixa registradora 24px.png"))); // NOI18N
        jLabel4.setText("Manutenção do cadastro de centros de custo");
        Centros_Custo.getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 30, 610, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Centros de custo");
        Centros_Custo.getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 90, 110, 17);

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lista_centros_custo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lista_centros_custo.setSelectionBackground(new java.awt.Color(204, 255, 255));
        lista_centros_custo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        lista_centros_custo.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_centros_custoValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista_centros_custo);

        Centros_Custo.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 110, 220, 230);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        Centros_Custo.getContentPane().add(jButton1);
        jButton1.setBounds(200, 80, 40, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Nome");
        Centros_Custo.getContentPane().add(jLabel6);
        jLabel6.setBounds(300, 120, 40, 17);

        txtNomeCentro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNomeCentro.setEnabled(false);
        Centros_Custo.getContentPane().add(txtNomeCentro);
        txtNomeCentro.setBounds(350, 110, 240, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Saldo");
        Centros_Custo.getContentPane().add(jLabel7);
        jLabel7.setBounds(300, 200, 40, 17);

        txtSaldoCentro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSaldoCentro.setEnabled(false);
        Centros_Custo.getContentPane().add(txtSaldoCentro);
        txtSaldoCentro.setBounds(350, 190, 240, 30);

        btnNovoCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoCentroCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovoCentroCusto.setText("Novo");
        btnNovoCentroCusto.setToolTipText("Cadastrar novo centro de custo");
        btnNovoCentroCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCentroCustoActionPerformed(evt);
            }
        });
        Centros_Custo.getContentPane().add(btnNovoCentroCusto);
        btnNovoCentroCusto.setBounds(90, 390, 100, 30);

        btnAlterarCentrosCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAlterarCentrosCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        btnAlterarCentrosCusto.setText("Editar");
        btnAlterarCentrosCusto.setToolTipText("Alterar centro de custo existente");
        btnAlterarCentrosCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarCentrosCustoActionPerformed(evt);
            }
        });
        Centros_Custo.getContentPane().add(btnAlterarCentrosCusto);
        btnAlterarCentrosCusto.setBounds(200, 390, 100, 30);

        btnExcluirCentrosCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirCentrosCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        btnExcluirCentrosCusto.setText("Excluir");
        btnExcluirCentrosCusto.setToolTipText("Excluir centro de custo");
        btnExcluirCentrosCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCentrosCustoActionPerformed(evt);
            }
        });
        Centros_Custo.getContentPane().add(btnExcluirCentrosCusto);
        btnExcluirCentrosCusto.setBounds(310, 390, 100, 30);

        btnSairCentrosCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairCentrosCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairCentrosCusto.setText("Sair");
        btnSairCentrosCusto.setToolTipText("Fechar a tela de centros de custo");
        btnSairCentrosCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairCentrosCustoActionPerformed(evt);
            }
        });
        Centros_Custo.getContentPane().add(btnSairCentrosCusto);
        btnSairCentrosCusto.setBounds(420, 390, 100, 30);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("  Tipo");
        Centros_Custo.getContentPane().add(jLabel14);
        jLabel14.setBounds(300, 160, 40, 20);

        txtTipoCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTipoCentroCusto.setEnabled(false);
        Centros_Custo.getContentPane().add(txtTipoCentroCusto);
        txtTipoCentroCusto.setBounds(350, 150, 240, 30);

        btnTrnaferirSaldoCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnTrnaferirSaldoCentroCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Transferir saldo 16px.png"))); // NOI18N
        btnTrnaferirSaldoCentroCusto.setText("Transferir saldo");
        btnTrnaferirSaldoCentroCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrnaferirSaldoCentroCustoActionPerformed(evt);
            }
        });
        Centros_Custo.getContentPane().add(btnTrnaferirSaldoCentroCusto);
        btnTrnaferirSaldoCentroCusto.setBounds(423, 310, 170, 30);

        Novo_Centro_Custo.setTitle("Novo cadastro");
        Novo_Centro_Custo.setModal(true);
        Novo_Centro_Custo.setResizable(false);
        Novo_Centro_Custo.getContentPane().setLayout(null);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caixa registradora 24px.png"))); // NOI18N
        jLabel8.setText("Cadastrar centro de custo");
        Novo_Centro_Custo.getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 20, 420, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Nome");
        Novo_Centro_Custo.getContentPane().add(jLabel9);
        jLabel9.setBounds(50, 90, 40, 17);

        txtNomeCentro1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Novo_Centro_Custo.getContentPane().add(txtNomeCentro1);
        txtNomeCentro1.setBounds(100, 80, 240, 30);

        btnSalvarCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarCentroCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarCentroCusto.setText("Salvar");
        btnSalvarCentroCusto.setToolTipText("Salvar o cadastro do centro de custo");
        btnSalvarCentroCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCentroCustoActionPerformed(evt);
            }
        });
        Novo_Centro_Custo.getContentPane().add(btnSalvarCentroCusto);
        btnSalvarCentroCusto.setBounds(50, 190, 100, 30);

        btnSairCadastroCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairCadastroCentroCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairCadastroCentroCusto.setText("Sair");
        btnSairCadastroCentroCusto.setToolTipText("Cancelar cadastro");
        btnSairCadastroCentroCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairCadastroCentroCustoActionPerformed(evt);
            }
        });
        Novo_Centro_Custo.getContentPane().add(btnSairCadastroCentroCusto);
        btnSairCadastroCentroCusto.setBounds(270, 190, 100, 30);

        btnLimparDadosCentroCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparDadosCentroCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar 16px.png"))); // NOI18N
        btnLimparDadosCentroCusto.setText("Limpar");
        btnLimparDadosCentroCusto.setToolTipText("Limpar dados e recomeçar cadastro");
        btnLimparDadosCentroCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparDadosCentroCustoActionPerformed(evt);
            }
        });
        Novo_Centro_Custo.getContentPane().add(btnLimparDadosCentroCusto);
        btnLimparDadosCentroCusto.setBounds(160, 190, 100, 30);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("  Tipo");
        Novo_Centro_Custo.getContentPane().add(jLabel10);
        jLabel10.setBounds(50, 130, 40, 20);

        radioCaixa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radioCaixa.setText("Caixa");
        radioCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCaixaActionPerformed(evt);
            }
        });
        Novo_Centro_Custo.getContentPane().add(radioCaixa);
        radioCaixa.setBounds(120, 120, 70, 40);

        radioContaBancaria.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radioContaBancaria.setText("Conta bancária");
        radioContaBancaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioContaBancariaActionPerformed(evt);
            }
        });
        Novo_Centro_Custo.getContentPane().add(radioContaBancaria);
        radioContaBancaria.setBounds(200, 120, 130, 40);

        Alterar_Centro_Custo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Alterar_Centro_Custo.setTitle("Alteração de dados");
        Alterar_Centro_Custo.setModal(true);
        Alterar_Centro_Custo.setResizable(false);
        Alterar_Centro_Custo.getContentPane().setLayout(null);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caixa registradora 24px.png"))); // NOI18N
        jLabel12.setText("Alterar centro de custo");
        Alterar_Centro_Custo.getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 20, 420, 30);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Nome");
        Alterar_Centro_Custo.getContentPane().add(jLabel13);
        jLabel13.setBounds(50, 90, 40, 17);

        txtNomeCentro2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Alterar_Centro_Custo.getContentPane().add(txtNomeCentro2);
        txtNomeCentro2.setBounds(100, 80, 240, 30);

        btnSalvarCentroCusto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarCentroCusto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarCentroCusto2.setText("Salvar");
        btnSalvarCentroCusto2.setToolTipText("Salvar alterações");
        btnSalvarCentroCusto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCentroCusto2ActionPerformed(evt);
            }
        });
        Alterar_Centro_Custo.getContentPane().add(btnSalvarCentroCusto2);
        btnSalvarCentroCusto2.setBounds(100, 170, 100, 30);

        btnSairCadastroCentroCusto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairCadastroCentroCusto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairCadastroCentroCusto2.setText("Sair");
        btnSairCadastroCentroCusto2.setToolTipText("Cancelar alterações");
        btnSairCadastroCentroCusto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairCadastroCentroCusto2ActionPerformed(evt);
            }
        });
        Alterar_Centro_Custo.getContentPane().add(btnSairCadastroCentroCusto2);
        btnSairCadastroCentroCusto2.setBounds(210, 170, 100, 30);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Tipo do centro de custo:");
        Alterar_Centro_Custo.getContentPane().add(jLabel11);
        jLabel11.setBounds(60, 130, 160, 20);

        lblTipoCentroCusto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTipoCentroCusto.setForeground(new java.awt.Color(255, 0, 0));
        lblTipoCentroCusto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoCentroCusto.setText("Conta bancária");
        Alterar_Centro_Custo.getContentPane().add(lblTipoCentroCusto);
        lblTipoCentroCusto.setBounds(220, 130, 110, 20);

        Transferir_Saldo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Transferir_Saldo.setTitle("Transferir saldo");
        Transferir_Saldo.setModal(true);
        Transferir_Saldo.setResizable(false);
        Transferir_Saldo.getContentPane().setLayout(null);

        jLabel64.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Transferir saldo 24px.png"))); // NOI18N
        jLabel64.setText("Transferir saldo");
        Transferir_Saldo.getContentPane().add(jLabel64);
        jLabel64.setBounds(0, 30, 540, 30);

        jLabel66.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel66.setText("O saldo sairá do centro de custo");
        Transferir_Saldo.getContentPane().add(jLabel66);
        jLabel66.setBounds(50, 90, 210, 17);

        lblCentroCustoTransferirSaldo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCentroCustoTransferirSaldo.setForeground(new java.awt.Color(255, 0, 0));
        lblCentroCustoTransferirSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCentroCustoTransferirSaldo.setText("lblCentroCusto");
        Transferir_Saldo.getContentPane().add(lblCentroCustoTransferirSaldo);
        lblCentroCustoTransferirSaldo.setBounds(280, 90, 230, 17);

        jLabel68.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel68.setText("Saldo disponível do centro de custo");
        Transferir_Saldo.getContentPane().add(jLabel68);
        jLabel68.setBounds(30, 130, 240, 17);

        lblSaldoDisponivelTransferirSaldo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSaldoDisponivelTransferirSaldo.setForeground(new java.awt.Color(255, 0, 0));
        lblSaldoDisponivelTransferirSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSaldoDisponivelTransferirSaldo.setText("lblSaldoDisponivel");
        Transferir_Saldo.getContentPane().add(lblSaldoDisponivelTransferirSaldo);
        lblSaldoDisponivelTransferirSaldo.setBounds(280, 130, 230, 17);

        jLabel70.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel70.setText("Saldo a transferir em R$");
        Transferir_Saldo.getContentPane().add(jLabel70);
        jLabel70.setBounds(100, 170, 160, 17);

        txtSaldoTransferirSaldo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSaldoTransferirSaldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoTransferirSaldoKeyTyped(evt);
            }
        });
        Transferir_Saldo.getContentPane().add(txtSaldoTransferirSaldo);
        txtSaldoTransferirSaldo.setBounds(280, 160, 230, 30);

        jLabel71.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel71.setText(" Centro de custo de destino");
        Transferir_Saldo.getContentPane().add(jLabel71);
        jLabel71.setBounds(80, 210, 180, 17);

        comboCentroDestinoTransferirSaldo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Transferir_Saldo.getContentPane().add(comboCentroDestinoTransferirSaldo);
        comboCentroDestinoTransferirSaldo.setBounds(280, 200, 230, 30);

        btnSalvarTransferirSaldo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarTransferirSaldo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarTransferirSaldo.setText("Salvar");
        btnSalvarTransferirSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarTransferirSaldoActionPerformed(evt);
            }
        });
        Transferir_Saldo.getContentPane().add(btnSalvarTransferirSaldo);
        btnSalvarTransferirSaldo.setBounds(170, 280, 100, 30);

        btnSairTransferirSaldo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairTransferirSaldo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairTransferirSaldo.setText("Sair");
        btnSairTransferirSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairTransferirSaldoActionPerformed(evt);
            }
        });
        Transferir_Saldo.getContentPane().add(btnSairTransferirSaldo);
        btnSairTransferirSaldo.setBounds(280, 280, 100, 30);

        Configurar_Responsaveis_Caixa.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Configurar_Responsaveis_Caixa.setTitle("Configurar responsáveis pelo caixa");
        Configurar_Responsaveis_Caixa.setModal(true);
        Configurar_Responsaveis_Caixa.setResizable(false);
        Configurar_Responsaveis_Caixa.getContentPane().setLayout(null);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Trocar usuário 24px.png"))); // NOI18N
        jLabel17.setText("Configuração dos responsáveis pelos caixas");
        Configurar_Responsaveis_Caixa.getContentPane().add(jLabel17);
        jLabel17.setBounds(0, 30, 650, 30);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Caixas disponíveis");
        Configurar_Responsaveis_Caixa.getContentPane().add(jLabel18);
        jLabel18.setBounds(20, 90, 120, 17);

        lista_caixas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lista_caixas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista_caixas.setToolTipText("");
        lista_caixas.setSelectionBackground(new java.awt.Color(204, 255, 255));
        lista_caixas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        lista_caixas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_caixasValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lista_caixas);

        Configurar_Responsaveis_Caixa.getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 110, 200, 250);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Selecione um usuário para adicionar aos responsáveis");
        Configurar_Responsaveis_Caixa.getContentPane().add(jLabel19);
        jLabel19.setBounds(280, 90, 350, 17);

        comboUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Selecione>" }));
        comboUsuario.setToolTipText("Selecione um usuário para adicionar como responsável");
        Configurar_Responsaveis_Caixa.getContentPane().add(comboUsuario);
        comboUsuario.setBounds(280, 110, 350, 30);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Responsáveis pelo caixa");
        Configurar_Responsaveis_Caixa.getContentPane().add(jLabel20);
        jLabel20.setBounds(280, 190, 170, 17);

        lblCaixaSelecionado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCaixaSelecionado.setForeground(new java.awt.Color(255, 0, 0));
        lblCaixaSelecionado.setText("Caixa selecionado");
        Configurar_Responsaveis_Caixa.getContentPane().add(lblCaixaSelecionado);
        lblCaixaSelecionado.setBounds(450, 190, 180, 17);

        lista_responsaveis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lista_responsaveis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista_responsaveis.setToolTipText("Responsável pelo caixa selecionado");
        lista_responsaveis.setSelectionBackground(new java.awt.Color(204, 255, 255));
        lista_responsaveis.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(lista_responsaveis);

        Configurar_Responsaveis_Caixa.getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(280, 210, 350, 150);

        btnSairConfiguracaoResponsaveis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairConfiguracaoResponsaveis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairConfiguracaoResponsaveis.setText("Sair");
        btnSairConfiguracaoResponsaveis.setToolTipText("Fechar a tela de configuração dos responsáveis pelos caixas");
        btnSairConfiguracaoResponsaveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairConfiguracaoResponsaveisActionPerformed(evt);
            }
        });
        Configurar_Responsaveis_Caixa.getContentPane().add(btnSairConfiguracaoResponsaveis);
        btnSairConfiguracaoResponsaveis.setBounds(280, 410, 100, 30);

        btnRetirarResponsavelCaixa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRetirarResponsavelCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        btnRetirarResponsavelCaixa.setText("Retirar");
        btnRetirarResponsavelCaixa.setToolTipText("Retirar responsáveis do caixa");
        btnRetirarResponsavelCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarResponsavelCaixaActionPerformed(evt);
            }
        });
        Configurar_Responsaveis_Caixa.getContentPane().add(btnRetirarResponsavelCaixa);
        btnRetirarResponsavelCaixa.setBounds(510, 140, 120, 30);

        btnAdicionarResponsavelCaixa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAdicionarResponsavelCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnAdicionarResponsavelCaixa.setText("Adicionar");
        btnAdicionarResponsavelCaixa.setToolTipText("Adicionar responsáveis ao caixa selecionado");
        btnAdicionarResponsavelCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarResponsavelCaixaActionPerformed(evt);
            }
        });
        Configurar_Responsaveis_Caixa.getContentPane().add(btnAdicionarResponsavelCaixa);
        btnAdicionarResponsavelCaixa.setBounds(380, 140, 120, 30);

        Setores.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Setores.setTitle("Cadastro de categorias financeiras");
        Setores.setModal(true);
        Setores.setResizable(false);
        Setores.getContentPane().setLayout(null);

        jLabel40.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Categoria financeira 24px.png"))); // NOI18N
        jLabel40.setText("Manutenção do cadastro de setores financeiros");
        Setores.getContentPane().add(jLabel40);
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

        Setores.getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(20, 120, 250, 240);

        jLabel41.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel41.setText("Setores cadastrados");
        Setores.getContentPane().add(jLabel41);
        jLabel41.setBounds(20, 100, 160, 17);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        Setores.getContentPane().add(jButton3);
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
        Setores.getContentPane().add(btnNovoSetor);
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
        Setores.getContentPane().add(btnEditarSetor);
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
        Setores.getContentPane().add(btnExcuirSetor);
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
        Setores.getContentPane().add(btnSairSetor);
        btnSairSetor.setBounds(460, 410, 100, 30);

        jLabel42.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel42.setText("Nome");
        Setores.getContentPane().add(jLabel42);
        jLabel42.setBounds(330, 130, 50, 17);

        jLabel43.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel43.setText("  Tipo");
        Setores.getContentPane().add(jLabel43);
        jLabel43.setBounds(330, 170, 40, 17);

        txtNomeSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNomeSetor.setEnabled(false);
        Setores.getContentPane().add(txtNomeSetor);
        txtNomeSetor.setBounds(400, 120, 250, 30);

        txtTipoSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTipoSetor.setEnabled(false);
        Setores.getContentPane().add(txtTipoSetor);
        txtTipoSetor.setBounds(400, 160, 250, 30);

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

        Lancamentos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Lancamentos.setTitle("Gerenciamento de receitas e despesas");
        Lancamentos.setModal(true);
        Lancamentos.setResizable(false);
        Lancamentos.getContentPane().setLayout(null);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Notas 24px.png"))); // NOI18N
        jLabel23.setText("Manutenção do gerenciamento de receitas e despesas");
        Lancamentos.getContentPane().add(jLabel23);
        jLabel23.setBounds(0, 30, 1000, 30);

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText("A pesquisar");
        Lancamentos.getContentPane().add(jLabel28);
        jLabel28.setBounds(20, 100, 80, 17);

        comboPesquisaLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboPesquisaLancamentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Receita", "Despesa" }));
        comboPesquisaLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPesquisaLancamentosActionPerformed(evt);
            }
        });
        Lancamentos.getContentPane().add(comboPesquisaLancamentos);
        comboPesquisaLancamentos.setBounds(110, 90, 120, 30);

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Período");
        Lancamentos.getContentPane().add(jLabel29);
        jLabel29.setBounds(270, 100, 49, 17);

        dataPesquisa1Lancamentos.setDate(new Date());
        dataPesquisa1Lancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataPesquisa1Lancamentos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataPesquisa1LancamentosPropertyChange(evt);
            }
        });
        Lancamentos.getContentPane().add(dataPesquisa1Lancamentos);
        dataPesquisa1Lancamentos.setBounds(330, 90, 140, 30);

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Até");
        Lancamentos.getContentPane().add(jLabel30);
        jLabel30.setBounds(490, 100, 21, 17);

        dataPesquisa2Lancamentos.setDate(new Date());
        dataPesquisa2Lancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dataPesquisa2Lancamentos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataPesquisa2LancamentosPropertyChange(evt);
            }
        });
        Lancamentos.getContentPane().add(dataPesquisa2Lancamentos);
        dataPesquisa2Lancamentos.setBounds(530, 90, 140, 30);

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("Situação");
        Lancamentos.getContentPane().add(jLabel31);
        jLabel31.setBounds(710, 100, 55, 17);

        comboSituacaoLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboSituacaoLancamentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Liquidado", "Não liquidado" }));
        Lancamentos.getContentPane().add(comboSituacaoLancamentos);
        comboSituacaoLancamentos.setBounds(780, 90, 130, 30);

        btnPesquisarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        btnPesquisarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarLancamentoActionPerformed(evt);
            }
        });
        Lancamentos.getContentPane().add(btnPesquisarLancamento);
        btnPesquisarLancamento.setBounds(940, 90, 40, 30);

        tabelaLancamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
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
        tabelaLancamentos.setRowHeight(25);
        tabelaLancamentos.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tabelaLancamentos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tabelaLancamentos);
        if (tabelaLancamentos.getColumnModel().getColumnCount() > 0) {
            tabelaLancamentos.getColumnModel().getColumn(0).setPreferredWidth(250);
            tabelaLancamentos.getColumnModel().getColumn(1).setPreferredWidth(170);
            tabelaLancamentos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaLancamentos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabelaLancamentos.getColumnModel().getColumn(4).setPreferredWidth(60);
            tabelaLancamentos.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabelaLancamentos.getColumnModel().getColumn(6).setPreferredWidth(60);
            tabelaLancamentos.getColumnModel().getColumn(7).setPreferredWidth(50);
            tabelaLancamentos.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        Lancamentos.getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(20, 190, 960, 300);

        btnAlterarLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAlterarLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        btnAlterarLancamentos.setText("Editar");
        btnAlterarLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarLancamentosActionPerformed(evt);
            }
        });
        Lancamentos.getContentPane().add(btnAlterarLancamentos);
        btnAlterarLancamentos.setBounds(370, 550, 130, 30);

        btnNovoLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovoLancamentos.setText("Agendar");
        btnNovoLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoLancamentosActionPerformed(evt);
            }
        });
        Lancamentos.getContentPane().add(btnNovoLancamentos);
        btnNovoLancamentos.setBounds(230, 550, 130, 30);

        btnExcluirLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        btnExcluirLancamentos.setText("Excluir");
        btnExcluirLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirLancamentosActionPerformed(evt);
            }
        });
        Lancamentos.getContentPane().add(btnExcluirLancamentos);
        btnExcluirLancamentos.setBounds(510, 550, 130, 30);

        btnSairLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairLancamentos.setText("Sair");
        Lancamentos.getContentPane().add(btnSairLancamentos);
        btnSairLancamentos.setBounds(650, 550, 130, 30);

        jLabel35.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 0, 0));
        jLabel35.setText("Total de receitas na situação:");
        Lancamentos.getContentPane().add(jLabel35);
        jLabel35.setBounds(20, 500, 170, 15);

        lblTotalReceitas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTotalReceitas.setForeground(new java.awt.Color(255, 0, 0));
        lblTotalReceitas.setText("lblTotalReceitas");
        Lancamentos.getContentPane().add(lblTotalReceitas);
        lblTotalReceitas.setBounds(210, 500, 100, 15);

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setText("Total de despesas na situação:");
        Lancamentos.getContentPane().add(jLabel37);
        jLabel37.setBounds(410, 500, 180, 15);

        lblTotalDespesas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTotalDespesas.setForeground(new java.awt.Color(255, 0, 0));
        lblTotalDespesas.setText("lblTotalDespesas");
        Lancamentos.getContentPane().add(lblTotalDespesas);
        lblTotalDespesas.setBounds(610, 500, 100, 15);

        jLabel39.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 0, 0));
        jLabel39.setText("Valor total:");
        Lancamentos.getContentPane().add(jLabel39);
        jLabel39.setBounds(810, 500, 80, 15);

        lblValorTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(255, 0, 0));
        lblValorTotal.setText("lblValorTotal");
        Lancamentos.getContentPane().add(lblValorTotal);
        lblValorTotal.setBounds(890, 500, 100, 15);

        jLabel44.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel44.setText("Descricao");
        Lancamentos.getContentPane().add(jLabel44);
        jLabel44.setBounds(30, 150, 70, 17);

        txtDescricaoPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescricaoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoPesquisarKeyReleased(evt);
            }
        });
        Lancamentos.getContentPane().add(txtDescricaoPesquisar);
        txtDescricaoPesquisar.setBounds(110, 140, 360, 30);

        jLabel45.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel45.setText("Categoria");
        Lancamentos.getContentPane().add(jLabel45);
        jLabel45.setBounds(510, 150, 80, 17);

        comboCategoriaPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboCategoriaPesquisar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));
        Lancamentos.getContentPane().add(comboCategoriaPesquisar);
        comboCategoriaPesquisar.setBounds(600, 140, 310, 30);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Impressora 16px.png"))); // NOI18N
        Lancamentos.getContentPane().add(jButton8);
        jButton8.setBounds(940, 140, 40, 30);

        Agendar_Lancamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Agendar_Lancamento.setTitle("Lançamento avulso na conta bancária");
        Agendar_Lancamento.setModal(true);
        Agendar_Lancamento.setResizable(false);
        Agendar_Lancamento.getContentPane().setLayout(null);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Notas 24px.png"))); // NOI18N
        jLabel24.setText("Novo agendamento");
        Agendar_Lancamento.getContentPane().add(jLabel24);
        jLabel24.setBounds(0, 30, 671, 30);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Descrição do lançamento");
        Agendar_Lancamento.getContentPane().add(jLabel25);
        jLabel25.setBounds(40, 150, 170, 17);

        txtDescricaoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Agendar_Lancamento.getContentPane().add(txtDescricaoNovoLancamento);
        txtDescricaoNovoLancamento.setBounds(220, 140, 410, 30);

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText(" Forma de pagamento");
        Agendar_Lancamento.getContentPane().add(jLabel26);
        jLabel26.setBounds(60, 190, 150, 17);

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText(" Tipo de lançamento");
        Agendar_Lancamento.getContentPane().add(jLabel27);
        jLabel27.setBounds(70, 110, 140, 17);

        comboTipoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboTipoNovoLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Receita", "Despesa" }));
        comboTipoNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoNovoLancamentoActionPerformed(evt);
            }
        });
        Agendar_Lancamento.getContentPane().add(comboTipoNovoLancamento);
        comboTipoNovoLancamento.setBounds(220, 100, 410, 30);

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("Valor do lançamento");
        Agendar_Lancamento.getContentPane().add(jLabel32);
        jLabel32.setBounds(70, 230, 140, 17);

        txtValorNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtValorNovoLancamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorNovoLancamentoKeyTyped(evt);
            }
        });
        Agendar_Lancamento.getContentPane().add(txtValorNovoLancamento);
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
        Agendar_Lancamento.getContentPane().add(btnSalvarNovoLancamento);
        btnSalvarNovoLancamento.setBounds(180, 420, 100, 30);

        btnLimpar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar 16px.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.setToolTipText("Limpa os campos para recomeçar o lançamento");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        Agendar_Lancamento.getContentPane().add(btnLimpar);
        btnLimpar.setBounds(290, 420, 100, 30);

        btnSairNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairNovoLancamento.setText("Sair");
        btnSairNovoLancamento.setToolTipText("Cancelar lançamento na conta bancária");
        btnSairNovoLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairNovoLancamentoActionPerformed(evt);
            }
        });
        Agendar_Lancamento.getContentPane().add(btnSairNovoLancamento);
        btnSairNovoLancamento.setBounds(400, 420, 100, 30);

        comboFormaNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Agendar_Lancamento.getContentPane().add(comboFormaNovoLancamento);
        comboFormaNovoLancamento.setBounds(220, 180, 410, 30);

        jLabel33.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel33.setText(" Número de parcelas");
        Agendar_Lancamento.getContentPane().add(jLabel33);
        jLabel33.setBounds(400, 230, 140, 17);

        comboParcelasNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboParcelasNovoLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        Agendar_Lancamento.getContentPane().add(comboParcelasNovoLancamento);
        comboParcelasNovoLancamento.setBounds(560, 220, 70, 30);

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("Obs: Este agendamento irá gerar uma receita ou despesa não liquidados!");
        Agendar_Lancamento.getContentPane().add(jLabel34);
        jLabel34.setBounds(90, 370, 520, 17);

        jLabel46.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel46.setText(" Categoria");
        Agendar_Lancamento.getContentPane().add(jLabel46);
        jLabel46.setBounds(130, 270, 70, 17);

        comboCategoriaNovoAgendamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Agendar_Lancamento.getContentPane().add(comboCategoriaNovoAgendamento);
        comboCategoriaNovoAgendamento.setBounds(220, 260, 410, 30);

        jLabel61.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel61.setText("Data do pagamento");
        Agendar_Lancamento.getContentPane().add(jLabel61);
        jLabel61.setBounds(70, 310, 140, 17);

        dataVencimentoNovoLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Agendar_Lancamento.getContentPane().add(dataVencimentoNovoLancamento);
        dataVencimentoNovoLancamento.setBounds(220, 300, 170, 30);

        Editar_Lancamento.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Editar_Lancamento.setTitle("Editar lançamento avulso na conta bancária");
        Editar_Lancamento.setModal(true);
        Editar_Lancamento.setResizable(false);
        Editar_Lancamento.getContentPane().setLayout(null);

        jLabel53.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Notas 24px.png"))); // NOI18N
        jLabel53.setText("Editar agendamento");
        Editar_Lancamento.getContentPane().add(jLabel53);
        jLabel53.setBounds(0, 30, 671, 30);

        jLabel54.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel54.setText("Descrição do lançamento");
        Editar_Lancamento.getContentPane().add(jLabel54);
        jLabel54.setBounds(40, 150, 170, 17);

        txtDescricaoAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Editar_Lancamento.getContentPane().add(txtDescricaoAlterarLancamento);
        txtDescricaoAlterarLancamento.setBounds(220, 140, 410, 30);

        jLabel55.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel55.setText(" Forma de pagamento");
        Editar_Lancamento.getContentPane().add(jLabel55);
        jLabel55.setBounds(60, 190, 150, 17);

        jLabel56.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel56.setText(" Tipo de lançamento");
        Editar_Lancamento.getContentPane().add(jLabel56);
        jLabel56.setBounds(70, 110, 140, 17);

        comboTipoAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboTipoAlterarLancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Receita", "Despesa" }));
        comboTipoAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoAlterarLancamentoActionPerformed(evt);
            }
        });
        Editar_Lancamento.getContentPane().add(comboTipoAlterarLancamento);
        comboTipoAlterarLancamento.setBounds(220, 100, 410, 30);

        jLabel57.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel57.setText("Valor do lançamento");
        Editar_Lancamento.getContentPane().add(jLabel57);
        jLabel57.setBounds(70, 230, 140, 17);

        txtValorAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtValorAlterarLancamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorAlterarLancamentoKeyTyped(evt);
            }
        });
        Editar_Lancamento.getContentPane().add(txtValorAlterarLancamento);
        txtValorAlterarLancamento.setBounds(220, 220, 80, 30);

        btnSalvarAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarAlterarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarAlterarLancamento.setText("Salvar");
        btnSalvarAlterarLancamento.setToolTipText("Edita a receita na conta bancária");
        btnSalvarAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlterarLancamentoActionPerformed(evt);
            }
        });
        Editar_Lancamento.getContentPane().add(btnSalvarAlterarLancamento);
        btnSalvarAlterarLancamento.setBounds(240, 420, 100, 30);

        btnSairAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairAlterarLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairAlterarLancamento.setText("Sair");
        btnSairAlterarLancamento.setToolTipText("Cancelar edição na conta bancária");
        btnSairAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairAlterarLancamentoActionPerformed(evt);
            }
        });
        Editar_Lancamento.getContentPane().add(btnSairAlterarLancamento);
        btnSairAlterarLancamento.setBounds(350, 420, 100, 30);

        comboFormaAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Editar_Lancamento.getContentPane().add(comboFormaAlterarLancamento);
        comboFormaAlterarLancamento.setBounds(220, 180, 410, 30);

        jLabel58.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel58.setText(" Número de parcelas");
        Editar_Lancamento.getContentPane().add(jLabel58);
        jLabel58.setBounds(70, 270, 140, 17);

        jLabel60.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel60.setText("Categoria");
        Editar_Lancamento.getContentPane().add(jLabel60);
        jLabel60.setBounds(140, 310, 70, 17);

        comboCategoriaAlterarAgendamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Editar_Lancamento.getContentPane().add(comboCategoriaAlterarAgendamento);
        comboCategoriaAlterarAgendamento.setBounds(220, 300, 410, 30);

        jLabel62.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel62.setText("Data do vencimento");
        Editar_Lancamento.getContentPane().add(jLabel62);
        jLabel62.setBounds(330, 230, 140, 17);

        dataVencimentoAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Editar_Lancamento.getContentPane().add(dataVencimentoAlterarLancamento);
        dataVencimentoAlterarLancamento.setBounds(480, 220, 150, 30);

        jLabel63.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel63.setText(" Parcela número");
        Editar_Lancamento.getContentPane().add(jLabel63);
        jLabel63.setBounds(350, 270, 120, 17);

        lblParcelaNumeroAlterarlancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblParcelaNumeroAlterarlancamento.setForeground(new java.awt.Color(255, 0, 0));
        lblParcelaNumeroAlterarlancamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblParcelaNumeroAlterarlancamento.setText("1");
        Editar_Lancamento.getContentPane().add(lblParcelaNumeroAlterarlancamento);
        lblParcelaNumeroAlterarlancamento.setBounds(480, 270, 80, 17);

        lblNumeroParcelasAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNumeroParcelasAlterarLancamento.setForeground(new java.awt.Color(255, 0, 0));
        lblNumeroParcelasAlterarLancamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroParcelasAlterarLancamento.setText("10");
        Editar_Lancamento.getContentPane().add(lblNumeroParcelasAlterarLancamento);
        lblNumeroParcelasAlterarLancamento.setBounds(220, 270, 80, 17);

        jLabel59.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel59.setText("Liquidado");
        Editar_Lancamento.getContentPane().add(jLabel59);
        jLabel59.setBounds(140, 350, 70, 17);

        lblStatusAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatusAlterarLancamento.setForeground(new java.awt.Color(255, 0, 0));
        lblStatusAlterarLancamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatusAlterarLancamento.setText("lblStatusPagamento");
        Editar_Lancamento.getContentPane().add(lblStatusAlterarLancamento);
        lblStatusAlterarLancamento.setBounds(220, 350, 90, 17);

        jLabel65.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel65.setText("Data do pagamento");
        Editar_Lancamento.getContentPane().add(jLabel65);
        jLabel65.setBounds(340, 350, 130, 17);

        lblDataPagamentoAlterarLancamento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDataPagamentoAlterarLancamento.setForeground(new java.awt.Color(255, 0, 0));
        lblDataPagamentoAlterarLancamento.setText("lblDataPagamento");
        Editar_Lancamento.getContentPane().add(lblDataPagamentoAlterarLancamento);
        lblDataPagamentoAlterarLancamento.setBounds(490, 350, 140, 17);

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Símbolo de dinheiro 24px.png"))); // NOI18N
        jLabel1.setText("Manutenção do sistema financeiro");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 760, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Cadastre centros de custo para");
        add(jLabel2);
        jLabel2.setBounds(20, 110, 200, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("gerenciar melhor seu dinheiro");
        add(jLabel3);
        jLabel3.setBounds(20, 130, 190, 17);

        btnCentrosCusto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCentrosCusto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Caixa registradora 16px.png"))); // NOI18N
        btnCentrosCusto.setText("Centros de custo");
        btnCentrosCusto.setToolTipText("Cadastre os centros de custo!");
        btnCentrosCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCentrosCustoActionPerformed(evt);
            }
        });
        add(btnCentrosCusto);
        btnCentrosCusto.setBounds(20, 160, 170, 30);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Defina os responsáveis por cada");
        add(jLabel15);
        jLabel15.setBounds(20, 230, 210, 17);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("caixa existente no sistema");
        add(jLabel16);
        jLabel16.setBounds(20, 250, 170, 17);

        btnConfigurarCentros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnConfigurarCentros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Trocar usuário 16px.png"))); // NOI18N
        btnConfigurarCentros.setText("Responsáveis");
        btnConfigurarCentros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurarCentrosActionPerformed(evt);
            }
        });
        add(btnConfigurarCentros);
        btnConfigurarCentros.setBounds(20, 280, 170, 30);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Gerencie suas receitas e despesas");
        add(jLabel21);
        jLabel21.setBounds(270, 230, 230, 17);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("e agende lançamentos");
        add(jLabel22);
        jLabel22.setBounds(270, 250, 144, 17);

        btnLancamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLancamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Notas 16px.png"))); // NOI18N
        btnLancamentos.setText("Lançamentos");
        btnLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancamentosActionPerformed(evt);
            }
        });
        add(btnLancamentos);
        btnLancamentos.setBounds(270, 280, 170, 30);

        btnSairPainelFinanceiro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairPainelFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairPainelFinanceiro.setText("Sair");
        btnSairPainelFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairPainelFinanceiroActionPerformed(evt);
            }
        });
        add(btnSairPainelFinanceiro);
        btnSairPainelFinanceiro.setBounds(300, 350, 110, 30);

        jLabel36.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel36.setText("Cadastre e gerencie as categorias");
        add(jLabel36);
        jLabel36.setBounds(270, 110, 230, 17);

        jLabel38.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel38.setText("de receitas e despesas");
        add(jLabel38);
        jLabel38.setBounds(270, 130, 160, 17);

        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Categoria financeira 16px.png"))); // NOI18N
        jButton2.setText("Setores");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(270, 160, 170, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCentrosCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCentrosCustoActionPerformed
        
        txtNomeCentro.setText("");
        txtTipoCentroCusto.setText("");
        txtSaldoCentro.setText("");
        Class_Centros_Custo centro_custo = new Class_Centros_Custo();
        lista_centros_custo.setModel(centro_custo.carregaLista());
        Centros_Custo.setBounds(0, 0, 630, 480);
        Centros_Custo.setLocationRelativeTo(null);
        Centros_Custo.setVisible(true);
        
    }//GEN-LAST:event_btnCentrosCustoActionPerformed

    private void btnNovoCentroCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCentroCustoActionPerformed
        
        txtNomeCentro1.setText("");
        Novo_Centro_Custo.setBounds(0, 0, 430, 290);
        Novo_Centro_Custo.setLocationRelativeTo(null);
        Novo_Centro_Custo.setVisible(true);
        txtNomeCentro1.grabFocus();
        
    }//GEN-LAST:event_btnNovoCentroCustoActionPerformed

    private void btnSalvarCentroCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCentroCustoActionPerformed
        
        if (txtNomeCentro1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não pode haver campos vazios!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeCentro1.grabFocus();
        } else if (!radioCaixa.isSelected() && !radioContaBancaria.isSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo do centro de custo!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            String tipo;
            if (radioCaixa.isSelected()) {
                tipo = "Caixa";
            } else {
                tipo = "Conta bancária";
            }
            Class_Centros_Custo centro_custo = new Class_Centros_Custo();
            centro_custo.Cadastra(txtNomeCentro1.getText(), tipo);
            Novo_Centro_Custo.dispose();
            lista_centros_custo.setModel(centro_custo.carregaLista());
            txtNomeCentro.setText("");
            txtTipoCentroCusto.setText("");
            txtSaldoCentro.setText("");
        }
        
    }//GEN-LAST:event_btnSalvarCentroCustoActionPerformed

    private void btnAlterarCentrosCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarCentrosCustoActionPerformed
        
        Object centro_custo = lista_centros_custo.getSelectedValue();
        if (centro_custo == null) {
            JOptionPane.showMessageDialog(null, "Selecione um centro de custo para alterar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            txtNomeCentro2.setText(txtNomeCentro.getText());
            lblTipoCentroCusto.setText(txtTipoCentroCusto.getText());
            Alterar_Centro_Custo.setBounds(0, 0, 420, 250);
            Alterar_Centro_Custo.setLocationRelativeTo(null);
            Alterar_Centro_Custo.setVisible(true);
            txtNomeCentro2.grabFocus();
        }
        
    }//GEN-LAST:event_btnAlterarCentrosCustoActionPerformed

    private void btnLimparDadosCentroCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparDadosCentroCustoActionPerformed
        
        txtNomeCentro1.setText("");
        radioCaixa.setSelected(false);
        radioContaBancaria.setSelected(false);
        txtNomeCentro1.grabFocus();
        
    }//GEN-LAST:event_btnLimparDadosCentroCustoActionPerformed

    private void btnSalvarCentroCusto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCentroCusto2ActionPerformed
        
        if (txtNomeCentro2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não pode haver campos vazios!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeCentro2.grabFocus();
        } else {
            Class_Centros_Custo centro_custo = new Class_Centros_Custo();
            centro_custo.edita(id_centro_custo, txtNomeCentro2.getText());
            Alterar_Centro_Custo.dispose();
            lista_centros_custo.setModel(centro_custo.carregaLista());
            txtNomeCentro.setText("");
            txtTipoCentroCusto.setText("");
            txtSaldoCentro.setText("");
        }
        
    }//GEN-LAST:event_btnSalvarCentroCusto2ActionPerformed

    private void lista_centros_custoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_centros_custoValueChanged
        
        Object centroCusto = lista_centros_custo.getSelectedValue();
        if (centroCusto != null) {
            Class_Centros_Custo centros_custo = new Class_Centros_Custo();
            centros_custo.carregaCentroCusto(centroCusto.toString());
            id_centro_custo = centros_custo.getIdCentroCusto();
            txtNomeCentro.setText(centros_custo.getNomeCentroCusto());
            txtTipoCentroCusto.setText(centros_custo.getTipoCentroCusto());
            txtSaldoCentro.setText(centros_custo.getSaldoCentroCusto());
        } else {
            txtNomeCentro.setText("");
            txtTipoCentroCusto.setText("");
            txtSaldoCentro.setText("");
        }
        
    }//GEN-LAST:event_lista_centros_custoValueChanged

    private void btnSairCadastroCentroCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairCadastroCentroCustoActionPerformed
        
        Novo_Centro_Custo.dispose();
        
    }//GEN-LAST:event_btnSairCadastroCentroCustoActionPerformed

    private void btnSairCadastroCentroCusto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairCadastroCentroCusto2ActionPerformed
        
        Alterar_Centro_Custo.dispose();
        
    }//GEN-LAST:event_btnSairCadastroCentroCusto2ActionPerformed

    private void btnExcluirCentrosCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCentrosCustoActionPerformed
        
        Object centro_custo = lista_centros_custo.getSelectedValue();
        if (centro_custo == null) {
            JOptionPane.showMessageDialog(null, "Selecione um centro de custo para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (txtTipoCentroCusto.getText().equals("Caixa")) {
                Class_Caixa caixa = new Class_Caixa();
                int id_caixa = caixa.getIdCaixa(Integer.valueOf(id_centro_custo));
                if (caixa.verificaCaixaAberto(id_caixa) == true) {
                    JOptionPane.showMessageDialog(null, "Este centro de estoque é um caixa e se encontra aberto!\nFeche o caixa antes de excluí-lo!", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
                    if (troca.trocaVirgulaPorPonto(txtSaldoCentro.getText().replace("R$ ", "")) > 0) {
                        JOptionPane.showMessageDialog(null, "Este centro de custo possui saldo! Retire o saldo antes de excluí-lo!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    } else if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                        Class_Centros_Custo centros_custo = new Class_Centros_Custo();
                        centros_custo.exclui(id_centro_custo);
                        lista_centros_custo.setModel(centros_custo.carregaLista());
                        
                    }
                }
            } else {
                Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
                if (troca.trocaVirgulaPorPonto(txtSaldoCentro.getText()) > 0) {
                    JOptionPane.showMessageDialog(null, "Este centro de custo possui saldo! Retire o saldo antes de excluí-lo!", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                    Class_Centros_Custo centros_custo = new Class_Centros_Custo();
                    centros_custo.exclui(id_centro_custo);
                    lista_centros_custo.setModel(centros_custo.carregaLista());

                }         
            }
        }
        
    }//GEN-LAST:event_btnExcluirCentrosCustoActionPerformed

    private void btnSairCentrosCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairCentrosCustoActionPerformed
        
        Centros_Custo.dispose();
        
    }//GEN-LAST:event_btnSairCentrosCustoActionPerformed

    private void radioCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCaixaActionPerformed
        
        radioContaBancaria.setSelected(false);
        
    }//GEN-LAST:event_radioCaixaActionPerformed

    private void radioContaBancariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioContaBancariaActionPerformed
        
        radioCaixa.setSelected(false);
        
    }//GEN-LAST:event_radioContaBancariaActionPerformed

    private void btnConfigurarCentrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurarCentrosActionPerformed
        
        Class_Centros_Custo centros = new Class_Centros_Custo();
        lista_caixas.setModel(centros.refreshCaixas());
        comboUsuario.removeAllItems();
        comboUsuario.addItem("<Selecione>");
        Class_Usuarios usuarios = new Class_Usuarios();
        usuarios.carregaUsuariosNormaisComboBox(comboUsuario);
        comboUsuario.setSelectedIndex(0);
        lblCaixaSelecionado.setVisible(false);
        Configurar_Responsaveis_Caixa.setBounds(0, 0, 660, 490);
        Configurar_Responsaveis_Caixa.setLocationRelativeTo(null);
        Configurar_Responsaveis_Caixa.setVisible(true);
        
    }//GEN-LAST:event_btnConfigurarCentrosActionPerformed

    private void btnSairConfiguracaoResponsaveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairConfiguracaoResponsaveisActionPerformed
        
        Configurar_Responsaveis_Caixa.dispose();
        
    }//GEN-LAST:event_btnSairConfiguracaoResponsaveisActionPerformed

    private void lista_caixasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_caixasValueChanged
        
        Object caixa1 = lista_caixas.getSelectedValue();
        if (caixa1 == null) {
            lista_caixas.setSelectedIndex(0);
        }
        lblCaixaSelecionado.setVisible(true);
        String nome_caixa = lista_caixas.getSelectedValue().toString();
        lblCaixaSelecionado.setText(nome_caixa);
        Class_Caixa caixa = new Class_Caixa();
        lista_responsaveis.setModel(caixa.carregaResponsaveisCaixa(nome_caixa));
        
    }//GEN-LAST:event_lista_caixasValueChanged

    private void btnAdicionarResponsavelCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarResponsavelCaixaActionPerformed
        
        if (lista_caixas.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Selecione o caixa desejado para adicionar os responsáveis!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (comboUsuario.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione o usuário para adicionar aos responsáveis!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                ListModel lista = lista_responsaveis.getModel();
                String responsavel = comboUsuario.getSelectedItem().toString();
                boolean flag = false;
                for (int i = 0; i < lista.getSize(); i++) {
                    if (responsavel.equals(lista.getElementAt(i).toString())) {
                        flag = true;
                    }
                }
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, "Este usuário já é responsável por este caixa!\nNão é preciso definí-lo novamente!", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    Class_Usuarios usuarios = new Class_Usuarios();
                    int id_usuario = usuarios.getIdUsuario(responsavel);
                    Class_Centros_Custo centros = new Class_Centros_Custo();
                    int id_centro_custo = centros.retornaIdCentroCusto(lista_caixas.getSelectedValue().toString());
                    Class_Caixa caixa = new Class_Caixa();
                    caixa.adicionaResponsavelCaixa(id_centro_custo, id_usuario);
                    lista_responsaveis.setModel(caixa.carregaResponsaveisCaixa(lista_caixas.getSelectedValue().toString()));
                }
            }
        }
        
    }//GEN-LAST:event_btnAdicionarResponsavelCaixaActionPerformed

    private void btnRetirarResponsavelCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarResponsavelCaixaActionPerformed
        
        Object responsavel = lista_responsaveis.getSelectedValue();
        if (responsavel == null) {
            JOptionPane.showMessageDialog(null, "Selecione o responsável na lista abaixo para retirar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            Class_Usuarios usuarios = new Class_Usuarios();
            int id_usuario = usuarios.getIdUsuario(responsavel.toString());
            Class_Centros_Custo centros = new Class_Centros_Custo();
            int id_centro_custo = centros.retornaIdCentroCusto(lista_caixas.getSelectedValue().toString());
            Class_Caixa caixa = new Class_Caixa();
            caixa.retiraResponsavelCaixa(id_centro_custo, id_usuario);
            lista_responsaveis.setModel(caixa.carregaResponsaveisCaixa(lista_caixas.getSelectedValue().toString()));
        }
        
    }//GEN-LAST:event_btnRetirarResponsavelCaixaActionPerformed

    private void btnSairPainelFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairPainelFinanceiroActionPerformed
        
        Class_Verifica_Menu_Aberto verifica = new Class_Verifica_Menu_Aberto();
        int index = verifica.verificaMenuAberto(painel_principal, "Sistema financeiro   ");
        if (index >= 0) {
            painel_principal.remove(index);
        }
        
    }//GEN-LAST:event_btnSairPainelFinanceiroActionPerformed

    private void btnLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancamentosActionPerformed

        carregaLancamentos();
        
        Lancamentos.setBounds(0, 0, 1010, 640);
        Lancamentos.setLocationRelativeTo(null);
        Lancamentos.setVisible(true);
        
    }//GEN-LAST:event_btnLancamentosActionPerformed

    private void btnPesquisarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarLancamentoActionPerformed
        
        carregaLancamentos();
        
    }//GEN-LAST:event_btnPesquisarLancamentoActionPerformed

    private void btnNovoLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoLancamentosActionPerformed
        
        limpaCamposNovoLancamento();
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        formas.carregaFormasPagamento(comboFormaNovoLancamento);
        Agendar_Lancamento.setBounds(0, 0, 670, 510);
        Agendar_Lancamento.setLocationRelativeTo(null);
        Agendar_Lancamento.setVisible(true);
        
    }//GEN-LAST:event_btnNovoLancamentosActionPerformed

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
            String pagamento = null;
            String vencimento = sdf.format(dataVencimentoNovoLancamento.getDate());
            
            if (comboTipoNovoLancamento.getSelectedItem().toString().equals("Receita")) {
                Class_Receitas receitas = new Class_Receitas();
                receitas.cadastraReceita(txtDescricaoNovoLancamento.getText(), 0, "", 
                        comboCategoriaNovoAgendamento.getSelectedItem().toString(), forma, txtValorNovoLancamento.getText(), "0",
                        "0", Integer.valueOf(comboParcelasNovoLancamento.getSelectedItem().toString()), 0, 0, pagamento, vencimento, 0, 1);
            } else if (comboTipoNovoLancamento.getSelectedItem().equals("Despesa")) {
                Class_Despesas despesas = new Class_Despesas();
                despesas.cadastraDespesa(0, txtDescricaoNovoLancamento.getText(), nome_usuario, "", 
                    Integer.valueOf(comboParcelasNovoLancamento.getSelectedItem().toString()), pagamento, vencimento, forma,
                    txtValorNovoLancamento.getText(), "0", "0", 0, 1, comboCategoriaNovoAgendamento.getSelectedItem().toString(),
                    id_usuario, 0, 0, 0, 1);
            }

            carregaLancamentos();
            JOptionPane.showMessageDialog(null, "Lançamento agendado com sucesso! Lembre-se que este é um lançamento não liquidado,\nou seja, é preciso acessar os centros de custo para liquidá-lo depois!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            Agendar_Lancamento.dispose();
        }
    }//GEN-LAST:event_btnSalvarNovoLancamentoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed

        limpaCamposNovoLancamento();
        
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairNovoLancamentoActionPerformed

        Agendar_Lancamento.dispose();
        
    }//GEN-LAST:event_btnSairNovoLancamentoActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        refreshListSetores();
        Setores.setBounds(0, 0, 680, 500);
        Setores.setLocationRelativeTo(null);
        Setores.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtDescricaoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoPesquisarKeyReleased
        
        carregaLancamentos();
        
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

    private void btnSairNovoSetorFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairNovoSetorFinanceiroActionPerformed
        
        Novo_Setor.dispose();
        
    }//GEN-LAST:event_btnSairNovoSetorFinanceiroActionPerformed

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
        
        Setores.dispose();
        
    }//GEN-LAST:event_btnSairSetorActionPerformed

    private void comboTipoNovoLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoNovoLancamentoActionPerformed
        
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        setores.carregaSetoresFinanceirosComboBox(comboCategoriaNovoAgendamento, 
                comboTipoNovoLancamento.getSelectedItem().toString(), "Não");
        
    }//GEN-LAST:event_comboTipoNovoLancamentoActionPerformed

    private void btnAlterarLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarLancamentosActionPerformed
        
        int linha = tabelaLancamentos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel tabela = (DefaultTableModel) tabelaLancamentos.getModel();
            Object cod = tabela.getValueAt(linha, 8);
            if (cod == null || cod.toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione um lançamento para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                carregaDadosLancamento(tabela, linha, Integer.valueOf(cod.toString()));
                
                Editar_Lancamento.setBounds(0, 0, 670, 510);
                Editar_Lancamento.setLocationRelativeTo(null);
                Editar_Lancamento.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_btnAlterarLancamentosActionPerformed

    private void txtValorAlterarLancamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAlterarLancamentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorAlterarLancamentoKeyTyped

    private void btnSalvarAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlterarLancamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarAlterarLancamentoActionPerformed

    private void btnSairAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairAlterarLancamentoActionPerformed
        
        Editar_Lancamento.dispose();
        
    }//GEN-LAST:event_btnSairAlterarLancamentoActionPerformed

    private void comboTipoAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoAlterarLancamentoActionPerformed
        
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        setores.carregaSetoresFinanceirosComboBox(comboCategoriaAlterarAgendamento, 
                comboTipoAlterarLancamento.getSelectedItem().toString(), "Não");
        
    }//GEN-LAST:event_comboTipoAlterarLancamentoActionPerformed

    private void btnExcluirLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirLancamentosActionPerformed
        
        int linha = tabelaLancamentos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um lançamento para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel tabela = (DefaultTableModel) tabelaLancamentos.getModel();
            Object cod = tabela.getValueAt(linha, 8);
            if (cod == null || cod.toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione um lançamento para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                
            }
        }
        
    }//GEN-LAST:event_btnExcluirLancamentosActionPerformed

    private void btnTrnaferirSaldoCentroCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrnaferirSaldoCentroCustoActionPerformed
        
        Object centro = lista_centros_custo.getSelectedValue();
        if (centro == null) {
            JOptionPane.showMessageDialog(null, "Selecione um centro de estoque para transferir o saldo!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            lblCentroCustoTransferirSaldo.setText(centro.toString());
            lblSaldoDisponivelTransferirSaldo.setText(txtSaldoCentro.getText());
            txtSaldoTransferirSaldo.setText("");
            Class_Centros_Custo centros = new Class_Centros_Custo();
            centros.retornaCentrosCustoComboBoxComExcessao(comboCentroDestinoTransferirSaldo, centro.toString());
            
            Transferir_Saldo.setBounds(0, 0, 540, 360);
            Transferir_Saldo.setLocationRelativeTo(null);
            Transferir_Saldo.setVisible(true);
        }
        
    }//GEN-LAST:event_btnTrnaferirSaldoCentroCustoActionPerformed

    private void btnSairTransferirSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairTransferirSaldoActionPerformed
        
        Transferir_Saldo.dispose();
        
    }//GEN-LAST:event_btnSairTransferirSaldoActionPerformed

    private void btnSalvarTransferirSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarTransferirSaldoActionPerformed
        
        if (txtSaldoTransferirSaldo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o saldo a ser transferido!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtSaldoTransferirSaldo.grabFocus();
        } else {
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            float inicial = troca.trocaVirgulaPorPonto(lblSaldoDisponivelTransferirSaldo.getText().replace("R$ ", ""));
            float transferir = troca.trocaVirgulaPorPonto(txtSaldoTransferirSaldo.getText());            
            if (inicial < transferir) {
                JOptionPane.showMessageDialog(null, "O valor a transferir não pode ser maior que o valor disponível!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                Class_Centros_Custo centros = new Class_Centros_Custo();
                
                //debitamos do centro inicial
                int id_centro = centros.retornaIdCentroCusto(lblCentroCustoTransferirSaldo.getText());
                centros.alteraSaldoCentroCusto("Despesa", "0,00", txtSaldoTransferirSaldo.getText(), id_centro);
                //acrescentamos no centro de destino
                id_centro = centros.retornaIdCentroCusto(comboCentroDestinoTransferirSaldo.getSelectedItem().toString());
                centros.alteraSaldoCentroCusto("Receita", "0,00", txtSaldoTransferirSaldo.getText(), id_centro);
                
                JOptionPane.showMessageDialog(null, "Saldo transferido com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                Transferir_Saldo.dispose();
                Class_Centros_Custo centro_custo = new Class_Centros_Custo();
                lista_centros_custo.setModel(centro_custo.carregaLista());
            }
        }
        
    }//GEN-LAST:event_btnSalvarTransferirSaldoActionPerformed

    private void txtSaldoTransferirSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoTransferirSaldoKeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consome("1234567890,.", evt);
        
    }//GEN-LAST:event_txtSaldoTransferirSaldoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Agendar_Lancamento;
    private javax.swing.JDialog Alterar_Centro_Custo;
    private javax.swing.JDialog Alterar_Setor;
    private javax.swing.JDialog Centros_Custo;
    private javax.swing.JDialog Configurar_Responsaveis_Caixa;
    private javax.swing.JDialog Editar_Lancamento;
    private javax.swing.JDialog Lancamentos;
    private javax.swing.JDialog Novo_Centro_Custo;
    private javax.swing.JDialog Novo_Setor;
    private javax.swing.JDialog Setores;
    private javax.swing.JDialog Transferir_Saldo;
    private javax.swing.JButton btnAdicionarResponsavelCaixa;
    private javax.swing.JButton btnAlterarCentrosCusto;
    private javax.swing.JButton btnAlterarLancamentos;
    private javax.swing.JButton btnCentrosCusto;
    private javax.swing.JButton btnConfigurarCentros;
    private javax.swing.JButton btnEditarSetor;
    private javax.swing.JButton btnExcluirCentrosCusto;
    private javax.swing.JButton btnExcluirLancamentos;
    private javax.swing.JButton btnExcuirSetor;
    private javax.swing.JButton btnLancamentos;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLimparDadosCentroCusto;
    private javax.swing.JButton btnNovoCentroCusto;
    private javax.swing.JButton btnNovoLancamentos;
    private javax.swing.JButton btnNovoSetor;
    private javax.swing.JButton btnPesquisarLancamento;
    private javax.swing.JButton btnRetirarResponsavelCaixa;
    private javax.swing.JButton btnSairAlterarLancamento;
    private javax.swing.JButton btnSairAlterarSetorFinanceiro;
    private javax.swing.JButton btnSairCadastroCentroCusto;
    private javax.swing.JButton btnSairCadastroCentroCusto2;
    private javax.swing.JButton btnSairCentrosCusto;
    private javax.swing.JButton btnSairConfiguracaoResponsaveis;
    private javax.swing.JButton btnSairLancamentos;
    private javax.swing.JButton btnSairNovoLancamento;
    private javax.swing.JButton btnSairNovoSetorFinanceiro;
    private javax.swing.JButton btnSairPainelFinanceiro;
    private javax.swing.JButton btnSairSetor;
    private javax.swing.JButton btnSairTransferirSaldo;
    private javax.swing.JButton btnSalvarAlterarLancamento;
    private javax.swing.JButton btnSalvarAlterarSetorFinanceiro;
    private javax.swing.JButton btnSalvarCentroCusto;
    private javax.swing.JButton btnSalvarCentroCusto2;
    private javax.swing.JButton btnSalvarNovoLancamento;
    private javax.swing.JButton btnSalvarNovoSetorFinanceiro;
    private javax.swing.JButton btnSalvarTransferirSaldo;
    private javax.swing.JButton btnTrnaferirSaldoCentroCusto;
    private javax.swing.JComboBox comboCategoriaAlterarAgendamento;
    private javax.swing.JComboBox comboCategoriaNovoAgendamento;
    private javax.swing.JComboBox comboCategoriaPesquisar;
    private javax.swing.JComboBox comboCentroDestinoTransferirSaldo;
    private javax.swing.JComboBox comboFormaAlterarLancamento;
    private javax.swing.JComboBox comboFormaNovoLancamento;
    private javax.swing.JComboBox comboParcelasNovoLancamento;
    private javax.swing.JComboBox comboPesquisaLancamentos;
    private javax.swing.JComboBox comboSituacaoLancamentos;
    private javax.swing.JComboBox comboTipoAlterarLancamento;
    private javax.swing.JComboBox comboTipoAlterarSetorFinanceiro;
    private javax.swing.JComboBox comboTipoNovoLancamento;
    private javax.swing.JComboBox comboTipoNovoSetorFinanceiro;
    private javax.swing.JComboBox comboUsuario;
    private com.toedter.calendar.JDateChooser dataPesquisa1Lancamentos;
    private com.toedter.calendar.JDateChooser dataPesquisa2Lancamentos;
    private com.toedter.calendar.JDateChooser dataVencimentoAlterarLancamento;
    private com.toedter.calendar.JDateChooser dataVencimentoNovoLancamento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblCaixaSelecionado;
    private javax.swing.JLabel lblCentroCustoTransferirSaldo;
    private javax.swing.JLabel lblDataPagamentoAlterarLancamento;
    private javax.swing.JLabel lblNumeroParcelasAlterarLancamento;
    private javax.swing.JLabel lblParcelaNumeroAlterarlancamento;
    private javax.swing.JLabel lblSaldoDisponivelTransferirSaldo;
    private javax.swing.JLabel lblStatusAlterarLancamento;
    private javax.swing.JLabel lblTipoCentroCusto;
    private javax.swing.JLabel lblTotalDespesas;
    private javax.swing.JLabel lblTotalReceitas;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JList listSetores;
    private javax.swing.JList lista_caixas;
    private javax.swing.JList lista_centros_custo;
    private javax.swing.JList lista_responsaveis;
    private javax.swing.JRadioButton radioCaixa;
    private javax.swing.JRadioButton radioContaBancaria;
    private javax.swing.JTable tabelaLancamentos;
    private javax.swing.JTextField txtDescricaoAlterarLancamento;
    private javax.swing.JTextField txtDescricaoNovoLancamento;
    private javax.swing.JTextField txtDescricaoPesquisar;
    private javax.swing.JTextField txtNomeAlterarSetorFinanceiro;
    private javax.swing.JTextField txtNomeCentro;
    private javax.swing.JTextField txtNomeCentro1;
    private javax.swing.JTextField txtNomeCentro2;
    private javax.swing.JTextField txtNomeNovoSetorFinanceiro;
    private javax.swing.JTextField txtNomeSetor;
    private javax.swing.JTextField txtSaldoCentro;
    private javax.swing.JTextField txtSaldoTransferirSaldo;
    private javax.swing.JTextField txtTipoCentroCusto;
    private javax.swing.JTextField txtTipoSetor;
    private javax.swing.JTextField txtValorAlterarLancamento;
    private javax.swing.JTextField txtValorNovoLancamento;
    // End of variables declaration//GEN-END:variables
}
