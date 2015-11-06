/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento.janela;

import cliente.classe.ClienteClasse;
import cliente.comboBox.ClienteComboBox;
import cliente.comboBox.ClienteComboBoxCellRenderer;
import cliente.controller.ClienteController;
import empresa.classe.EmpresaClasse;
import empresa.comboBox.EmpresaComboBox;
import empresa.comboBox.EmpresaComboBoxCellRenderer;
import empresa.controller.EmpresaController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import orcamento_produto.classe.OrcamentoProdutoClasse;
import orcamento_produto.janela.OrcamentoProdutoEscolherProduto;
import orcamento_produto.table.OrcamentoProdutoTableModel;
import servico.classe.ServicoClasse;
import servico.comboBox.ServicoComboBox;
import servico.comboBox.ServicoComboBoxCellRenderer;
import servico.controller.ServicoController;
import utilidades.TrocaVirgulaPorPonto;

/**
 *
 * @author Marcos
 */
public class OrcamentoCadastrar extends JDialog implements ActionListener, MouseListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblAlvoOrcamento, lblClienteEmpresa, lblServico, lblDescricaoProduto, lblValorServico1, lblValorServico2, lblValorProdutos1, lblValorProdutos2, lblDesconto1, lblDesconto2, lblValorTotal1, lblValorTotal2;
    private JRadioButton radioCliente, radioEmpresa;
    private ButtonGroup grupoRadio;
    private JComboBox comboClienteEmpresa, comboServico;
    private JTable tabelaProduto;
    private JScrollPane scrollTabelaProduto;
    private JButton btnPesquisarClienteEmpresa, btnPesquisarServico, btnAddProduto, btnExcluirProduto, btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private List<ServicoClasse> lista_servicos;
    private List<ClienteClasse> lista_cliente;
    private List<EmpresaClasse> lista_empresa;
    private List<OrcamentoProdutoClasse> lista_produtos_adicionados;
    
    private NumberFormat nf = NumberFormat.getCurrencyInstance();
    private float valor_servico, valor_produtos, valor_desconto, valor_total;
    
    public boolean cadastrou = false; //esta variável vai dizer para o form pai se foi cadastrado algum usuário. Se sim, atualiza a tabela na outra tela
    
    public OrcamentoCadastrar() {
        setTitle("Novo cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 610);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fonteNegrito = new Font("Tahoma", Font.BOLD, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 800, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("NOVO CADASTRO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 800, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 800, 450);
        
        lblAlvoOrcamento = new JLabel("O orçamento será feito para");
        lblAlvoOrcamento.setFont(fonteGeral);
        lblAlvoOrcamento.setBounds(30, 30, 200, 30);
        
        radioCliente = new JRadioButton("Pessoa física (cliente)");
        radioCliente.setFont(fonteGeral);
        radioCliente.setBackground(Color.WHITE);
        radioCliente.setBounds(250, 30, 200, 30);
        radioCliente.setSelected(true);
        radioCliente.addActionListener(this);
        
        radioEmpresa = new JRadioButton("Pessoa jurídica (empresa)");
        radioEmpresa.setFont(fonteGeral);
        radioEmpresa.setBackground(Color.WHITE);
        radioEmpresa.setBounds(470, 30, 200, 30);
        radioEmpresa.addActionListener(this);
        
        grupoRadio = new ButtonGroup();
        grupoRadio.add(radioCliente);
        grupoRadio.add(radioEmpresa);
        
        lblClienteEmpresa = new JLabel();
        lblClienteEmpresa.setFont(fonteGeral);
        lblClienteEmpresa.setBounds(30, 70, 200, 30);
        
        comboClienteEmpresa = new JComboBox();
        comboClienteEmpresa.setFont(fonteGeral);
        comboClienteEmpresa.setBounds(250, 70, 430, 30);
        refreshClientes();
        
        btnPesquisarClienteEmpresa = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        btnPesquisarClienteEmpresa.setBounds(690, 70, 40, 30);
        btnPesquisarClienteEmpresa.addActionListener(this);
        
        lblServico = new JLabel("Selecione o serviço desejado");
        lblServico.setFont(fonteGeral);
        lblServico.setBounds(30, 110, 200, 30);
        
        comboServico = new JComboBox();
        comboServico.setFont(fonteGeral);
        comboServico.setBounds(250, 110, 430, 30);
        
        lista_servicos = new ServicoController().findServico("");
        comboServico.setModel(new ServicoComboBox(lista_servicos));
        comboServico.setRenderer(new ServicoComboBoxCellRenderer());
        
        btnPesquisarServico = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        btnPesquisarServico.setBounds(690, 110, 40, 30);
        btnPesquisarServico.addActionListener(this);
        
        lblDescricaoProduto = new JLabel("Produtos adicionados ao orçamento");
        lblDescricaoProduto.setFont(fonteGeral);
        lblDescricaoProduto.setBounds(30, 190, 250, 30);
        
        tabelaProduto = new JTable();
        tabelaProduto.setFont(fonteGeral);
        tabelaProduto.setRowHeight(25);
        tabelaProduto.getTableHeader().setReorderingAllowed(false);
        tabelaProduto.getTableHeader().setResizingAllowed(false);
        refreshTable();
        
        scrollTabelaProduto = new JScrollPane(tabelaProduto);
        scrollTabelaProduto.setBounds(30, 230, 650, 150);
        
        btnAddProduto = new JButton(new ImageIcon(getClass().getResource("/imagens/Adicionar 16px.png")));
        btnAddProduto.setFont(fonteGeral);
        btnAddProduto.setBounds(690, 230, 40, 30);
        btnAddProduto.addActionListener(this);
        
        btnExcluirProduto = new JButton(new ImageIcon(getClass().getResource("/imagens/Excluir 16px.png")));
        btnExcluirProduto.setFont(fonteGeral);
        btnExcluirProduto.setBounds(690, 270, 40, 30);
        btnExcluirProduto.addActionListener(this);
        
        lblValorServico1 = new JLabel("Serviço");
        lblValorServico1.setFont(fonteGeral);
        lblValorServico1.setBounds(30, 390, 50, 30);
        
        lblValorServico2 = new JLabel();
        lblValorServico2.setFont(fonteNegrito);
        lblValorServico2.setBounds(90, 390, 100, 30);
        lblValorServico2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblValorServico2.addMouseListener(this);
        refreshValorServico();
        
        lblValorProdutos1 = new JLabel("Produtos");
        lblValorProdutos1.setFont(fonteGeral);
        lblValorProdutos1.setBounds(200, 390, 50, 30);
        
        lblValorProdutos2 = new JLabel();
        lblValorProdutos2.setFont(fonteNegrito);
        lblValorProdutos2.setBounds(260, 390, 100, 30);
        refreshValorProdutos();
        
        lblDesconto1 = new JLabel("Desconto");
        lblDesconto1.setFont(fonteGeral);
        lblDesconto1.setBounds(370, 390, 60, 30);
        
        lblDesconto2 = new JLabel("R$ 0,00");
        lblDesconto2.setFont(fonteNegrito);
        lblDesconto2.setBounds(430, 390, 100, 30);
        lblDesconto2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblDesconto2.addMouseListener(this);
        
        lblValorTotal1 = new JLabel("Valor total");
        lblValorTotal1.setFont(fonteGeral);
        lblValorTotal1.setBounds(540, 390, 80, 30);
        
        lblValorTotal2 = new JLabel();
        lblValorTotal2.setFont(fonteNegrito);
        lblValorTotal2.setBounds(630, 390, 100, 30);
        lblValorTotal2.setForeground(Color.red);
        refreshValorTotal();
        
        painel2.add(lblAlvoOrcamento);
        painel2.add(radioCliente);
        painel2.add(radioEmpresa);
        painel2.add(lblClienteEmpresa);
        painel2.add(comboClienteEmpresa);
        painel2.add(btnPesquisarClienteEmpresa);
        painel2.add(lblServico);
        painel2.add(comboServico);
        painel2.add(btnPesquisarServico);
        painel2.add(lblDescricaoProduto);
        painel2.add(scrollTabelaProduto);
        painel2.add(btnAddProduto);
        painel2.add(btnExcluirProduto);
        painel2.add(lblValorServico1);
        painel2.add(lblValorServico2);
        painel2.add(lblValorProdutos1);
        painel2.add(lblValorProdutos2);
        painel2.add(lblDesconto1);
        painel2.add(lblDesconto2);
        painel2.add(lblValorTotal1);
        painel2.add(lblValorTotal2);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 510, 800, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(280, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(390, 20, 100, 30);
        btnVoltar.addActionListener(this);
        
        painel3.add(btnSalvar);
        painel3.add(btnVoltar);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }
    
    public void refreshValorServico() {
        valor_servico = lista_servicos.get(comboServico.getSelectedIndex()).getValor_venda();
        lblValorServico2.setText(nf.format(valor_servico));
    }
    
    public void defineValorServico() {
        String valor = null;
        valor = JOptionPane.showInputDialog(null, "Defina o valor do serviço", "ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
        if (valor != null) {
            valor_servico = new TrocaVirgulaPorPonto().trocaVirgulaPorPonto(valor);
            lblValorServico2.setText(nf.format(valor_servico));
        }
    }
    
    public void refreshValorProdutos() {
        valor_produtos = 0;
        for (int i = 0; i < lista_produtos_adicionados.size(); i++) {
            valor_produtos = valor_produtos + (lista_produtos_adicionados.get(i).getValor_cobrado() * lista_produtos_adicionados.get(i).getQuantidade());
        }
        lblValorProdutos2.setText(nf.format(valor_produtos));
    }
    
    public void defineValorDesconto() {
        String valor = null;
        valor = JOptionPane.showInputDialog(null, "Defina o valor do desconto", "ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
        if (valor != null) {
            valor_desconto = new TrocaVirgulaPorPonto().trocaVirgulaPorPonto(valor);
            lblDesconto2.setText(nf.format(valor_desconto));
        }
    }
    
    public void refreshValorTotal() {
        lblValorTotal2.setText(nf.format(valor_servico+valor_produtos-valor_desconto));
    }
    
    public void refreshTable() {
        if (lista_produtos_adicionados == null) {
            lista_produtos_adicionados = new ArrayList<OrcamentoProdutoClasse>();
        }
        
        tabelaProduto.setModel(new OrcamentoProdutoTableModel(lista_produtos_adicionados));
        if (tabelaProduto.getRowCount() > 0) {
            tabelaProduto.setRowSelectionInterval(0, 0);
            tabelaProduto.scrollRectToVisible(new Rectangle(tabelaProduto.getCellRect(0, 0, true)));
        }
        
        tabelaProduto.getColumnModel().getColumn(0).setPreferredWidth(350);
        tabelaProduto.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelaProduto.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaProduto.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    
    /*public void salvar() {
        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome de usuário não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtUsuario.grabFocus();
        } else if (txtUsuario.getText().length() > 16) {
            JOptionPane.showMessageDialog(null, "O nome de usuário não pode ter mais que 16 caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O senha não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtSenha.grabFocus();
        } else if (txtSenha.getText().length() > 16) {
            JOptionPane.showMessageDialog(null, "A senha não pode ter mais que 16 caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (txtRepetirSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Confirme a senha!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtRepetirSenha.grabFocus();
        } else if (txtSenha.getText().equals(txtRepetirSenha.getText()) == false) {
            JOptionPane.showMessageDialog(null, "As senhas não coincidem!", "Atenção", JOptionPane.WARNING_MESSAGE);
            if (txtSenha.getText().isEmpty()) {
                txtSenha.grabFocus();
            } else {
                txtRepetirSenha.selectAll();
                txtRepetirSenha.grabFocus();
            }
        } else {
            UsuarioController cont = new UsuarioController();
            if (cont.verificaUsuarioRepetido(txtUsuario.getText()) == true) {
                JOptionPane.showMessageDialog(null, "O usuário "+txtUsuario.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtUsuario.selectAll();
                txtUsuario.grabFocus();
            } else {
                
                UsuarioClasse user = new UsuarioClasse();
                user.setNome(txtUsuario.getText());
                user.setSenha(txtSenha.getText());
                user.setTipo(comboTipo.getSelectedItem().toString());
                
                int result = cont.addUsuario(user);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Usuário "+txtUsuario.getText()+" cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar o usuário "+txtUsuario.getText()+"!", "Atenção", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }*/
    
    public void refreshClientes() {
        lblClienteEmpresa.setText("Selecione o cliente desejado");
        
        lista_cliente = new ClienteController().findCliente("");
        comboClienteEmpresa.setModel(new ClienteComboBox(lista_cliente));
        comboClienteEmpresa.setRenderer(new ClienteComboBoxCellRenderer());
    }
    
    public void refreshEmpresas() {
        lblClienteEmpresa.setText("Selecione a empresa desejada");
        
        lista_empresa = new EmpresaController().findEmpresa("");
        comboClienteEmpresa.setModel(new EmpresaComboBox(lista_empresa));
        comboClienteEmpresa.setRenderer(new EmpresaComboBoxCellRenderer());
    }
    
    public void pesquisarClienteEmpresa() {
        String cod;
        Long id = null;
        boolean flag = false;
        try {
            if (radioCliente.isSelected()) {
                cod = JOptionPane.showInputDialog(null, "Digite o ID do cliente para selecioná-lo", "PESQUISAR CLIENTE", JOptionPane.PLAIN_MESSAGE);
                if (cod != null) {
                    id = Long.parseLong(cod);

                    for (int i = 0; i < lista_cliente.size(); i++) {
                        if (lista_cliente.get(i).getId().equals(id)) {
                            comboClienteEmpresa.setSelectedIndex(i);
                            flag = true;
                            comboClienteEmpresa.repaint();
                        }
                    }

                    if (flag == false) {
                        JOptionPane.showMessageDialog(null, "Não existe cliente cadastrado com este ID!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                cod = JOptionPane.showInputDialog(null, "Digite o ID da empresa para selecioná-lo", "PESQUISAR EMPRESA", JOptionPane.PLAIN_MESSAGE);
                if (cod != null) {
                    id = Long.parseLong(cod);

                    for (int i = 0; i < lista_empresa.size(); i++) {
                        if (lista_empresa.get(i).getId().equals(id)) {
                            comboClienteEmpresa.setSelectedIndex(i);
                            flag = true;
                            comboClienteEmpresa.repaint();
                        }
                    }

                    if (flag == false) {
                        JOptionPane.showMessageDialog(null, "Não existe empresa cadastrada com este ID!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID incorreto!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void pesquisarServico() {
        String cod;
        Long id = null;
        boolean flag = false;
        try {
            cod = JOptionPane.showInputDialog(null, "Digite o ID do serviço para selecioná-lo", "PESQUISAR SERVIÇO", JOptionPane.PLAIN_MESSAGE);
            if (cod != null) {
                id = Long.parseLong(cod);

                for (int i = 0; i < lista_servicos.size(); i++) {
                    if (lista_servicos.get(i).getId().equals(id)) {
                        comboServico.setSelectedIndex(i);
                        flag = true;
                        comboServico.repaint();
                    }
                }

                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "Não existe serviço cadastrado com este ID!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID incorreto!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addProduto() {
        boolean flag = false;
        
        OrcamentoProdutoEscolherProduto escolher = new OrcamentoProdutoEscolherProduto();
        escolher.setVisible(true);
        
        //VERIFICO SE JÁ EXISTE ESTE PRODUTO ADICIONADO NA TABELA
        //SE EXISTIR, O FLAG SERÁ TRUE, E ENTÃO EU SÓ SOMO A QUANTIDADE
        if (escolher.escolheu == true) {
            if (tabelaProduto.getRowCount() > 0) {
                for (int i = 0; i < lista_produtos_adicionados.size(); i++) {
                    if (escolher.classe.getId_produto().equals(lista_produtos_adicionados.get(i).getId_produto()) == true) {
                        //COMO O PRODUTO EXISTE NA TABELA, ADICIONO AQUI MESMO A QUANTIDADE
                        lista_produtos_adicionados.get(i).setQuantidade(lista_produtos_adicionados.get(i).getQuantidade() + escolher.classe.getQuantidade());
                        flag = true;
                    }
                }
            }
            
            //SE O FLAG É FALSE, ENTÃO O PRODUTO AINDA NÃO EXISTE NA TABELA
            //LOGO, DEVO ADICIONAR E CARREGAR A TABELA NOVAMENTE
            //ADICIONO FORA DO FOR PARA QUE SEJA ADICIONADO APENAS UMA VEZ
            if (flag == false) {
                lista_produtos_adicionados.add(escolher.classe); 
            }
            
            //POR FIM RECARREGO A TABELA E OS VALORES
            refreshTable();
            refreshValorProdutos();
            refreshValorTotal();
        }
    }
    
    public void excluirProduto() {
        int index = tabelaProduto.getSelectedRow();
        OrcamentoProdutoClasse produto = lista_produtos_adicionados.get(index);
        if (produto.getTipo_medida().equals("Unidade") && produto.getQuantidade() == 1) {
            lista_produtos_adicionados.remove(index);
            refreshTable();
            refreshValorProdutos();
            refreshValorTotal();
        } else {
            String quant = null;
            quant = JOptionPane.showInputDialog(null, "Defina a quantidade a retirar", "ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
            if (quant == null) {
                JOptionPane.showMessageDialog(null, "Não foi possível remover o produto pois foi definido um valor incorreto!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            } else {
                float quantidade = new TrocaVirgulaPorPonto().trocaVirgulaPorPonto(quant);
                
                //AQUI EU VERIFICAREI SE A QUANTIDADE DIGITADA É INTEIRO OU DECIMAL
                //SE FOR DECIMAL E O PRODUTO FOR INTEIRO, NÃO POSSO DEIXAR CONTINUAR
                if (quantidade % 1 != 0 && produto.getTipo_medida().equals("Unidade")) {
                    //SE O RESTO DA DIVISÃO POR 1 FOR = 0, ENTÃO É UM NÚMERO INTEIRO
                    JOptionPane.showMessageDialog(null, "Este produto foi cadastrado como \"unidade\"! Defina uma quantidade válida!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    //AQUI VOU VERIFICAR SE A QUANTIDADE DIGITADA É IGUAL OU MAIOR QUE A QUANTIDADE EXISTENTE
                    //SE FOR, ENTÃO DELETO O PRODUTO, SE NÃO, DIMINUO A QUANTIDADE
                    if (quantidade >= produto.getQuantidade()) {
                        //SE FOR
                        lista_produtos_adicionados.remove(index);
                    } else {
                        //SE NÃO
                        produto.setQuantidade(produto.getQuantidade() - quantidade);
                        lista_produtos_adicionados.set(index, produto);
                    }
                    //AO FINAL, CARREGO A TABELA E OS VALORES NOVAMENTE
                    refreshTable();
                    refreshValorProdutos();
                    refreshValorTotal();
                }
            }
            
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == radioCliente) {
            refreshClientes();
        } else if (source == radioEmpresa) {
            refreshEmpresas();
        } else if (source == btnPesquisarClienteEmpresa) {
            pesquisarClienteEmpresa();
        } else if (source == btnPesquisarServico) {
            pesquisarServico();
        } else if (source == btnAddProduto) {
            addProduto();
        } else if (source == btnExcluirProduto) {
            excluirProduto();
        } else if (source == btnSalvar) {
            //salvar();
            //imprimir();
        } else if (source == btnVoltar) {
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lblValorServico2) {
            defineValorServico();
            refreshValorTotal();
        } else if (e.getSource() == lblDesconto2) {
            defineValorDesconto();
            refreshValorTotal();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == lblValorServico2) {
            lblValorServico2.setForeground(Color.red);
        } else if (e.getSource() == lblDesconto2) {
            lblDesconto2.setForeground(Color.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == lblValorServico2) {
            lblValorServico2.setForeground(Color.black);
        } else if (e.getSource() == lblDesconto2) {
            lblDesconto2.setForeground(Color.black);
        }
    }
    
}
