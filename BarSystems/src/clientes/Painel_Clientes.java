
package clientes;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import principal.Class_Consumir_Letras;
import principal.Class_Manipular_Data;
import principal.Class_Verifica_Menu_Aberto;

public class Painel_Clientes extends javax.swing.JPanel {
    
    JTabbedPane painel_principal;
    ArrayList arrayList = new ArrayList();

    public Painel_Clientes(JTabbedPane painel_principal, int id_usuario, String nome_usuario) {
        initComponents();
        
        this.painel_principal = painel_principal;
    }
    
    public void refreshList() {
        Class_Clientes clientes = new Class_Clientes();
        list_clientes.setModel(clientes.refreshList());
        arrayList = clientes.getIdClientes();
    }
    
    public void limpaCamposCliente() {
        txtNome.setText("");
        txtNascimento.setText("");
        lblIdadeCliente.setText("");
        txtRG.setText("");
        txtCPF.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtCEP.setText("");
        txtEndereco.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtPais.setText("");
        txtObservacoes.setText("");
    }
    
    public void limpaCamposNovoCadastro() {
        txtNomeCadastrar.setText("");
        txtNascimentoCadastrar.setText("");
        txtRgCadastrar.setText("");
        txtCPFCadastrar.setText("");
        txtTelefoneCadastrar.setText("");
        txtCelularCadastrar.setText("");
        txtCEPCadastrar.setText("");
        txtEnderecoCadastrar.setText("");
        txtBairroCadastrar.setText("");
        txtCidadeCadastrar.setText("");
        txtEstadoCadastrar.setText("");
        txtPaisCadastrar.setText("");
        txtObservacoesCadastrar.setText("");
        
        lblIdadeClienteCadastrar.setText("");
        txtNomeCadastrar.grabFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        novo_cliente = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNomeCadastrar = new javax.swing.JTextField();
        txtNascimentoCadastrar = new javax.swing.JFormattedTextField();
        txtRgCadastrar = new javax.swing.JTextField();
        txtCPFCadastrar = new javax.swing.JFormattedTextField();
        txtTelefoneCadastrar = new javax.swing.JTextField();
        txtCelularCadastrar = new javax.swing.JTextField();
        txtCEPCadastrar = new javax.swing.JFormattedTextField();
        txtEnderecoCadastrar = new javax.swing.JTextField();
        txtBairroCadastrar = new javax.swing.JTextField();
        txtCidadeCadastrar = new javax.swing.JTextField();
        txtEstadoCadastrar = new javax.swing.JTextField();
        txtPaisCadastrar = new javax.swing.JTextField();
        txtObservacoesCadastrar = new javax.swing.JTextField();
        lblIdadeClienteCadastrar = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnSalvarCadastro = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSairCadastro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_clientes = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        txtRG = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtCEP = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        txtObservacoes = new javax.swing.JTextField();
        lblIdadeCliente = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnVisualizarObservacoes = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();

        novo_cliente.setModal(true);
        novo_cliente.setResizable(false);
        novo_cliente.getContentPane().setLayout(null);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cliente 24px.png"))); // NOI18N
        jLabel9.setText("Novo cadastro");
        novo_cliente.getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 30, 670, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Nome");
        novo_cliente.getContentPane().add(jLabel16);
        jLabel16.setBounds(120, 100, 50, 17);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText(" Nascimento");
        novo_cliente.getContentPane().add(jLabel18);
        jLabel18.setBounds(80, 140, 80, 17);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText(" RG");
        novo_cliente.getContentPane().add(jLabel19);
        jLabel19.setBounds(130, 180, 30, 17);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("CPF");
        novo_cliente.getContentPane().add(jLabel20);
        jLabel20.setBounds(350, 180, 28, 17);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Telefone");
        novo_cliente.getContentPane().add(jLabel21);
        jLabel21.setBounds(100, 220, 56, 17);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Celular");
        novo_cliente.getContentPane().add(jLabel22);
        jLabel22.setBounds(340, 220, 45, 17);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("CEP");
        novo_cliente.getContentPane().add(jLabel23);
        jLabel23.setBounds(130, 270, 28, 17);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText(" Endereço");
        novo_cliente.getContentPane().add(jLabel24);
        jLabel24.setBounds(90, 310, 70, 17);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText(" Cidade");
        novo_cliente.getContentPane().add(jLabel25);
        jLabel25.setBounds(110, 390, 50, 17);

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText(" Estado");
        novo_cliente.getContentPane().add(jLabel26);
        jLabel26.setBounds(110, 430, 50, 17);

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText("País");
        novo_cliente.getContentPane().add(jLabel27);
        jLabel27.setBounds(260, 430, 27, 17);

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText(" Observações");
        novo_cliente.getContentPane().add(jLabel28);
        jLabel28.setBounds(70, 470, 90, 17);

        txtNomeCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtNomeCadastrar);
        txtNomeCadastrar.setBounds(180, 90, 350, 30);

        try {
            txtNascimentoCadastrar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNascimentoCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNascimentoCadastrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNascimentoCadastrarKeyReleased(evt);
            }
        });
        novo_cliente.getContentPane().add(txtNascimentoCadastrar);
        txtNascimentoCadastrar.setBounds(180, 130, 140, 30);

        txtRgCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtRgCadastrar);
        txtRgCadastrar.setBounds(180, 170, 140, 30);

        try {
            txtCPFCadastrar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPFCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtCPFCadastrar);
        txtCPFCadastrar.setBounds(400, 170, 130, 30);

        txtTelefoneCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtTelefoneCadastrar);
        txtTelefoneCadastrar.setBounds(180, 210, 140, 30);

        txtCelularCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtCelularCadastrar);
        txtCelularCadastrar.setBounds(400, 210, 130, 30);

        try {
            txtCEPCadastrar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCEPCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtCEPCadastrar);
        txtCEPCadastrar.setBounds(180, 260, 110, 30);

        txtEnderecoCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtEnderecoCadastrar);
        txtEnderecoCadastrar.setBounds(180, 300, 350, 30);

        txtBairroCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtBairroCadastrar);
        txtBairroCadastrar.setBounds(180, 340, 350, 30);

        txtCidadeCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtCidadeCadastrar);
        txtCidadeCadastrar.setBounds(180, 380, 350, 30);

        txtEstadoCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEstadoCadastrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstadoCadastrarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoCadastrarKeyTyped(evt);
            }
        });
        novo_cliente.getContentPane().add(txtEstadoCadastrar);
        txtEstadoCadastrar.setBounds(180, 420, 50, 30);

        txtPaisCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtPaisCadastrar);
        txtPaisCadastrar.setBounds(310, 420, 220, 30);

        txtObservacoesCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        novo_cliente.getContentPane().add(txtObservacoesCadastrar);
        txtObservacoesCadastrar.setBounds(180, 460, 350, 30);

        lblIdadeClienteCadastrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIdadeClienteCadastrar.setForeground(new java.awt.Color(255, 0, 0));
        lblIdadeClienteCadastrar.setText("Este cliente possui 18 anos");
        novo_cliente.getContentPane().add(lblIdadeClienteCadastrar);
        lblIdadeClienteCadastrar.setBounds(340, 140, 200, 17);

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Bairro");
        novo_cliente.getContentPane().add(jLabel29);
        jLabel29.setBounds(120, 350, 50, 17);

        btnSalvarCadastro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar 16px.png"))); // NOI18N
        btnSalvarCadastro.setText("Salvar");
        btnSalvarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCadastroActionPerformed(evt);
            }
        });
        novo_cliente.getContentPane().add(btnSalvarCadastro);
        btnSalvarCadastro.setBounds(190, 520, 100, 30);

        btnLimpar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Limpar 16px.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        novo_cliente.getContentPane().add(btnLimpar);
        btnLimpar.setBounds(300, 520, 100, 30);

        btnSairCadastro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSairCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSairCadastro.setText("Sair");
        btnSairCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairCadastroActionPerformed(evt);
            }
        });
        novo_cliente.getContentPane().add(btnSairCadastro);
        btnSairCadastro.setBounds(410, 520, 100, 30);

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cliente 24px.png"))); // NOI18N
        jLabel1.setText("Manutenção do cadastro de clientes");
        add(jLabel1);
        jLabel1.setBounds(0, 30, 810, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Clientes cadastrados");
        add(jLabel2);
        jLabel2.setBounds(20, 100, 170, 14);

        list_clientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        list_clientes.setSelectionBackground(new java.awt.Color(204, 255, 255));
        list_clientes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        list_clientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_clientesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list_clientes);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 270, 400);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Nome");
        add(jLabel3);
        jLabel3.setBounds(380, 130, 50, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText(" Nascimento");
        add(jLabel4);
        jLabel4.setBounds(340, 170, 80, 17);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText(" RG");
        add(jLabel5);
        jLabel5.setBounds(390, 210, 30, 17);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("CPF");
        add(jLabel6);
        jLabel6.setBounds(610, 210, 28, 17);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Telefone");
        add(jLabel7);
        jLabel7.setBounds(360, 250, 56, 17);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Celular");
        add(jLabel8);
        jLabel8.setBounds(600, 250, 45, 17);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("CEP");
        add(jLabel10);
        jLabel10.setBounds(390, 300, 28, 17);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText(" Endereço");
        add(jLabel11);
        jLabel11.setBounds(350, 340, 70, 17);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText(" Cidade");
        add(jLabel12);
        jLabel12.setBounds(370, 420, 50, 17);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText(" Estado");
        add(jLabel13);
        jLabel13.setBounds(370, 460, 50, 17);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("País");
        add(jLabel14);
        jLabel14.setBounds(520, 460, 27, 17);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText(" Observações");
        add(jLabel15);
        jLabel15.setBounds(330, 500, 90, 17);

        txtNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNome.setEnabled(false);
        add(txtNome);
        txtNome.setBounds(440, 120, 350, 30);

        txtNascimento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNascimento.setEnabled(false);
        add(txtNascimento);
        txtNascimento.setBounds(440, 160, 140, 30);

        txtRG.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtRG.setEnabled(false);
        add(txtRG);
        txtRG.setBounds(440, 200, 140, 30);

        txtCPF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCPF.setEnabled(false);
        add(txtCPF);
        txtCPF.setBounds(660, 200, 130, 30);

        txtTelefone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelefone.setEnabled(false);
        add(txtTelefone);
        txtTelefone.setBounds(440, 240, 140, 30);

        txtCelular.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCelular.setEnabled(false);
        add(txtCelular);
        txtCelular.setBounds(660, 240, 130, 30);

        txtCEP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCEP.setEnabled(false);
        add(txtCEP);
        txtCEP.setBounds(440, 290, 100, 30);

        txtEndereco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEndereco.setEnabled(false);
        add(txtEndereco);
        txtEndereco.setBounds(440, 330, 350, 30);

        txtBairro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBairro.setEnabled(false);
        add(txtBairro);
        txtBairro.setBounds(440, 370, 350, 30);

        txtCidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCidade.setEnabled(false);
        add(txtCidade);
        txtCidade.setBounds(440, 410, 350, 30);

        txtEstado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEstado.setEnabled(false);
        add(txtEstado);
        txtEstado.setBounds(440, 450, 50, 30);

        txtPais.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPais.setEnabled(false);
        add(txtPais);
        txtPais.setBounds(570, 450, 220, 30);

        txtObservacoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtObservacoes.setEnabled(false);
        add(txtObservacoes);
        txtObservacoes.setBounds(440, 490, 300, 30);

        lblIdadeCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblIdadeCliente.setForeground(new java.awt.Color(255, 0, 0));
        lblIdadeCliente.setText(" ");
        add(lblIdadeCliente);
        lblIdadeCliente.setBounds(600, 170, 200, 17);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Bairro");
        add(jLabel17);
        jLabel17.setBounds(380, 380, 50, 17);

        btnVisualizarObservacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        add(btnVisualizarObservacoes);
        btnVisualizarObservacoes.setBounds(750, 490, 40, 30);

        btnNovo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        add(btnNovo);
        btnNovo.setBounds(200, 570, 100, 30);

        btnEditar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar 16px.png"))); // NOI18N
        btnEditar.setText("Editar");
        add(btnEditar);
        btnEditar.setBounds(310, 570, 100, 30);

        btnExcluir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir 16px.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        add(btnExcluir);
        btnExcluir.setBounds(420, 570, 100, 30);

        btnSair.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Sair02 16px.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        add(btnSair);
        btnSair.setBounds(530, 570, 100, 30);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png"))); // NOI18N
        add(btnPesquisar);
        btnPesquisar.setBounds(250, 90, 40, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        
        Class_Verifica_Menu_Aberto verifica = new Class_Verifica_Menu_Aberto();
        int index = verifica.verificaMenuAberto(painel_principal, "Clientes   ");
        painel_principal.remove(index);
        
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        
        limpaCamposNovoCadastro();
        novo_cliente.setBounds(0, 0, 670, 600);
        novo_cliente.setLocationRelativeTo(null);
        novo_cliente.setVisible(true);
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        
        limpaCamposNovoCadastro();
        
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairCadastroActionPerformed
        
        novo_cliente.dispose();
        
    }//GEN-LAST:event_btnSairCadastroActionPerformed

    private void btnSalvarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCadastroActionPerformed
        
        if (txtNomeCadastrar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode ficar vazio!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeCadastrar.grabFocus();
        } else if (txtNomeCadastrar.getText().length() >45) {
            JOptionPane.showMessageDialog(null, "O campo nome ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeCadastrar.grabFocus();
        } else if (txtRgCadastrar.getText().length() > 15) {
            JOptionPane.showMessageDialog(null, "O campo RG ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtRgCadastrar.grabFocus();
        } else if (txtTelefoneCadastrar.getText().length() > 15) {
            JOptionPane.showMessageDialog(null, "O campo telefone ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtTelefoneCadastrar.grabFocus();
        } else if (txtCelularCadastrar.getText().length() > 15) {
            JOptionPane.showMessageDialog(null, "O campo celular ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCelularCadastrar.grabFocus();
        } else if (txtEnderecoCadastrar.getText().length() > 45) {
            JOptionPane.showMessageDialog(null, "O campo endereço ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtEnderecoCadastrar.grabFocus();
        } else if (txtBairroCadastrar.getText().length() > 45) {
            JOptionPane.showMessageDialog(null, "O campo bairro ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtBairroCadastrar.grabFocus();
        } else if (txtCidadeCadastrar.getText().length() > 45) {
            JOptionPane.showMessageDialog(null, "O campo cidade ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCidadeCadastrar.grabFocus();
        } else if (txtPaisCadastrar.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "O campo país ultrapassou o limite de caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtPaisCadastrar.grabFocus();
        } else {
            Class_Clientes clientes = new Class_Clientes();
            clientes.cadastrarCliente(txtNomeCadastrar.getText(), txtNascimentoCadastrar.getText(), txtRgCadastrar.getText(), 
                    txtCPFCadastrar.getText(), txtTelefoneCadastrar.getText(), txtCelularCadastrar.getText(), 
                    txtCEPCadastrar.getText(), txtEnderecoCadastrar.getText(), txtBairroCadastrar.getText(), 
                    txtCidadeCadastrar.getText(), txtEstadoCadastrar.getText(), txtPaisCadastrar.getText(), 
                    txtObservacoesCadastrar.getText());
            
            novo_cliente.dispose();
            refreshList();
        }
        
    }//GEN-LAST:event_btnSalvarCadastroActionPerformed

    private void txtEstadoCadastrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoCadastrarKeyReleased
        
        int index = txtEstadoCadastrar.getCaretPosition();
        txtEstadoCadastrar.setText(txtEstadoCadastrar.getText().toUpperCase());
        txtEstadoCadastrar.setCaretPosition(index);        
        
    }//GEN-LAST:event_txtEstadoCadastrarKeyReleased

    private void txtEstadoCadastrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoCadastrarKeyTyped
        
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        cons.consomeQntDeCaracteres(txtEstadoCadastrar, 1, evt);
        cons.consome("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMçÇ", evt);
        
    }//GEN-LAST:event_txtEstadoCadastrarKeyTyped

    private void txtNascimentoCadastrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNascimentoCadastrarKeyReleased
       
        Class_Consumir_Letras cons = new Class_Consumir_Letras();
        String a = cons.retiraLetrasEPontos(txtNascimentoCadastrar.getText());
        if (a.length() == 8) {
            try {
                Class_Manipular_Data data = new Class_Manipular_Data();
                int diasVividos = data.retornaQntAnos(txtNascimentoCadastrar.getText());
                if (diasVividos <= 0) {
                    lblIdadeClienteCadastrar.setText("");
                } else if (diasVividos == 1) {
                    lblIdadeClienteCadastrar.setText("Este cliente possui "+diasVividos+" ano");
                } else {
                    lblIdadeClienteCadastrar.setText("Este cliente possui "+diasVividos+" anos");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblIdadeClienteCadastrar.setText("");
        }
        
    }//GEN-LAST:event_txtNascimentoCadastrarKeyReleased

    private void list_clientesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_clientesValueChanged
        
        if (list_clientes.getSelectedIndex() != -1) {
            int id_cliente = Integer.valueOf(arrayList.get(list_clientes.getSelectedIndex()).toString());
            Class_Clientes clientes = new Class_Clientes();
            clientes.refreshCliente(id_cliente);
            txtNome.setText(clientes.getNome());
            txtNascimento.setText(clientes.getNascimento());
            if (!txtNascimento.getText().equals("  /  /    ")) {
                Class_Manipular_Data data = new Class_Manipular_Data();
                lblIdadeCliente.setText("Este cliente possui "+String.valueOf(data.retornaQntAnos(clientes.getNascimento()))+" anos");
            } else {
                lblIdadeCliente.setText("");
            }
            txtRG.setText(clientes.getRg());
            txtCPF.setText(clientes.getCpf());
            txtTelefone.setText(clientes.getTelefone());
            txtCelular.setText(clientes.getCelular());
            txtCEP.setText(clientes.getCep());
            txtEndereco.setText(clientes.getEndereco());
            txtBairro.setText(clientes.getBairro());
            txtCidade.setText(clientes.getCidade());
            txtEstado.setText(clientes.getEstado());
            txtPais.setText(clientes.getPais());
            txtObservacoes.setText(clientes.getObservacoes());
        } else {
            limpaCamposCliente();
        }
        
    }//GEN-LAST:event_list_clientesValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSairCadastro;
    private javax.swing.JButton btnSalvarCadastro;
    private javax.swing.JButton btnVisualizarObservacoes;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIdadeCliente;
    private javax.swing.JLabel lblIdadeClienteCadastrar;
    private javax.swing.JList list_clientes;
    private javax.swing.JDialog novo_cliente;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBairroCadastrar;
    private javax.swing.JTextField txtCEP;
    private javax.swing.JFormattedTextField txtCEPCadastrar;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JFormattedTextField txtCPFCadastrar;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCelularCadastrar;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCidadeCadastrar;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEnderecoCadastrar;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtEstadoCadastrar;
    private javax.swing.JTextField txtNascimento;
    private javax.swing.JFormattedTextField txtNascimentoCadastrar;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeCadastrar;
    private javax.swing.JTextField txtObservacoes;
    private javax.swing.JTextField txtObservacoesCadastrar;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtPaisCadastrar;
    private javax.swing.JTextField txtRG;
    private javax.swing.JTextField txtRgCadastrar;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JTextField txtTelefoneCadastrar;
    // End of variables declaration//GEN-END:variables
}