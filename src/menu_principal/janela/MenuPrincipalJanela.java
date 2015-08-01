/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_principal.janela;

import centro_custo.janela.CentroCustoJanela;
import empresa.janela.EmpresaJanela;
import financeiro_setor.janela.FinanceiroSetorJanela;
import forma_pagamento.janela.FormaPagamentoJanela;
import forma_pagamento_maquina_cartao.janela.FormaPagamentoMaquinaCartaoJanela;
import fornecedor.janela.FornecedorJanela;
import funcionario.janela.FuncionarioJanela;
import funcionario_funcao.janela.FuncionarioFuncaoJanela;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import login.janela.LoginJanela;
import principal.VerificaMenuAberto;
import produto.janela.ProdutoJanela;
import produto_setor.janela.ProdutoSetorJanela;
import usuario.classe.UsuarioClasse;
import usuario.janela.UsuarioJanela;

/**
 *
 * @author Marcos
 */
public class MenuPrincipalJanela extends JFrame implements ActionListener {
    
    private JMenuBar barra_menu;
    
    private JMenu menu_configuracoes, menu_clientes, menu_servicos, menu_estoque, menu_gerenciar, menu_financeiro, 
            menu_consultas, menu_relatorios, menu_sistema;
    
    private JMenuItem menu_item_usuarios, menu_item_funcoes, menu_item_funcionarios, 
            menu_item_pessoa_fisica, menu_item_pessoa_juridica,
            menu_item_setor_servico, menu_item_servico,
            menu_item_setor_produtos, menu_item_produtos, menu_item_centros_estoque, menu_item_fornecedores,
            menu_item_gerenciar_estoque, menu_item_gerenciar_compra, menu_item_gerenciar_venda, menu_item_gerenciar_ordem_servico,
            menu_item_centros_custo, menu_item_setor_financeiro, menu_item_forma_pagamento, menu_item_maquinas_cartoes, menu_item_taxa_cartao,  
            menu_item_trocar_usuario, menu_item_sair;
    
    private JPanel painel;
    
    private JButton btnClientes, btnEmpresas, btnEstoque, btnVendas, btnServicos, btnOrcamento, btnLogout, btnSair;
    
    private JTabbedPane painel_tabulado;
    
    private Font fonte;
    
    private int width, height;
    
    private UsuarioClasse user;
    
    public MenuPrincipalJanela(UsuarioClasse user) {
        super("MÃOS A OBRA - PROJETOS E SOLUÇÕES");
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fonte = new Font("Tahoma", Font.PLAIN, 12);
        
        this.user = user;
        
        Dimension d = getTamanhoTela();
        width = (int) d.getWidth();
        height = (int) d.getHeight();
        
        //AQUI SÃO INSERIDOS OS MENUS
        //------------------------------------------------------------------------------------------
        
        barra_menu = new JMenuBar();
        
        //---------
        
        menu_configuracoes = new JMenu("CONFIGURAÇÕES");
        menu_configuracoes.setFont(fonte);
        
        menu_item_usuarios = new JMenuItem("Usuários");
        menu_item_usuarios.setFont(fonte);
        menu_item_usuarios.addActionListener(this);
        
        menu_item_funcoes = new JMenuItem("Funções");
        menu_item_funcoes.setFont(fonte);
        menu_item_funcoes.addActionListener(this);
        
        menu_item_funcionarios = new JMenuItem("Funcionários");
        menu_item_funcionarios.setFont(fonte);
        menu_item_funcionarios.addActionListener(this);
        
        menu_configuracoes.add(menu_item_usuarios);
        menu_configuracoes.add(menu_item_funcoes);
        menu_configuracoes.add(menu_item_funcionarios);
        
        //-----------
        
        menu_clientes = new JMenu("CLIENTES");
        menu_clientes.setFont(fonte);
        
        menu_item_pessoa_fisica = new JMenuItem("Pessoa física");
        menu_item_pessoa_fisica.setFont(fonte);
        menu_item_pessoa_fisica.addActionListener(this);
        
        menu_item_pessoa_juridica = new JMenuItem("Pessoa jurídica");
        menu_item_pessoa_juridica.setFont(fonte);
        menu_item_pessoa_juridica.addActionListener(this);
        
        menu_clientes.add(menu_item_pessoa_fisica);
        menu_clientes.add(menu_item_pessoa_juridica);
        
        //------------
        
        menu_servicos = new JMenu("SERVIÇOS");
        menu_servicos.setFont(fonte);
        
        menu_item_setor_servico = new JMenuItem("Setores de serviços");
        menu_item_setor_servico.setFont(fonte);
        menu_item_setor_servico.addActionListener(this);
        
        menu_item_servico = new JMenuItem("Serviços");
        menu_item_servico.setFont(fonte);
        menu_item_servico.addActionListener(this);
        
        menu_servicos.add(menu_item_setor_servico);
        menu_servicos.add(menu_item_servico);
        
        //-----------
        
        menu_estoque = new JMenu("ESTOQUE");
        menu_estoque.setFont(fonte);
        
        menu_item_setor_produtos = new JMenuItem("Setores de produtos");
        menu_item_setor_produtos.setFont(fonte);
        menu_item_setor_produtos.addActionListener(this);
        
        menu_item_produtos = new JMenuItem("Produtos");
        menu_item_produtos.setFont(fonte);
        menu_item_produtos.addActionListener(this);
        
        menu_item_centros_estoque = new JMenuItem("Centros de estoque");
        menu_item_centros_estoque.setFont(fonte);
        menu_item_centros_estoque.addActionListener(this);
        
        menu_item_fornecedores = new JMenuItem("Fornecedores");
        menu_item_fornecedores.setFont(fonte);
        menu_item_fornecedores.addActionListener(this);
        
        menu_estoque.add(menu_item_setor_produtos);
        menu_estoque.add(menu_item_produtos);
        menu_estoque.add(menu_item_centros_estoque);
        menu_estoque.add(menu_item_fornecedores);
        
        //----------
        
        menu_gerenciar = new JMenu("GERENCIAR");
        menu_gerenciar.setFont(fonte);
        
        menu_item_gerenciar_estoque = new JMenuItem("Estoque");
        menu_item_gerenciar_estoque.setFont(fonte);
        menu_item_gerenciar_estoque.addActionListener(this);
        
        menu_item_gerenciar_compra = new JMenuItem("Compra");
        menu_item_gerenciar_compra.setFont(fonte);
        menu_item_gerenciar_compra.addActionListener(this);
        
        menu_item_gerenciar_venda = new JMenuItem("Venda");
        menu_item_gerenciar_venda.setFont(fonte);
        menu_item_gerenciar_venda.addActionListener(this);
        
        menu_item_gerenciar_ordem_servico = new JMenuItem("Ordem de serviço");
        menu_item_gerenciar_ordem_servico.setFont(fonte);
        menu_item_gerenciar_ordem_servico.addActionListener(this);
        
        menu_gerenciar.add(menu_item_gerenciar_estoque);
        menu_gerenciar.add(menu_item_gerenciar_compra);
        menu_gerenciar.add(menu_item_gerenciar_venda);
        menu_gerenciar.add(menu_item_gerenciar_ordem_servico);
        
        //---------------
        
        menu_financeiro = new JMenu("FINANCEIRO");
        menu_financeiro.setFont(fonte);
        
        menu_item_centros_custo = new JMenuItem("Centros de custo");
        menu_item_centros_custo.setFont(fonte);
        menu_item_centros_custo.addActionListener(this);
        
        menu_item_setor_financeiro = new JMenuItem("Setores financeiros");
        menu_item_setor_financeiro.setFont(fonte);
        menu_item_setor_financeiro.addActionListener(this);
        
        menu_item_forma_pagamento = new JMenuItem("Formas de pagamento");
        menu_item_forma_pagamento.setFont(fonte);
        menu_item_forma_pagamento.addActionListener(this);
        
        menu_item_maquinas_cartoes = new JMenuItem("Máquinas de cartão");
        menu_item_maquinas_cartoes.setFont(fonte);
        menu_item_maquinas_cartoes.addActionListener(this);
        
        menu_financeiro.add(menu_item_centros_custo);
        menu_financeiro.add(menu_item_setor_financeiro);
        menu_financeiro.add(menu_item_forma_pagamento);
        menu_financeiro.add(menu_item_maquinas_cartoes);
        
        //-----------
        
        menu_consultas = new JMenu("CONSULTAS");
        menu_consultas.setFont(fonte);
        
        //-----------
        
        menu_relatorios = new JMenu("RELATÓRIOS");
        menu_relatorios.setFont(fonte);
        
        //------------
        
        menu_sistema = new JMenu("SISTEMA");
        menu_sistema.setFont(fonte);
        
        menu_item_trocar_usuario = new JMenuItem("Trocar usuário");
        menu_item_trocar_usuario.setFont(fonte);
        menu_item_trocar_usuario.addActionListener(this);
        
        menu_item_sair = new JMenuItem("Sair");
        menu_item_sair.setFont(fonte);
        menu_item_sair.addActionListener(this);
        
        menu_sistema.add(menu_item_trocar_usuario);
        menu_sistema.add(menu_item_sair);
        
        //-----------
        
        barra_menu.add(menu_configuracoes);
        barra_menu.add(menu_clientes);
        barra_menu.add(menu_servicos);
        barra_menu.add(menu_estoque);
        barra_menu.add(menu_gerenciar);
        barra_menu.add(menu_financeiro);
        barra_menu.add(menu_consultas);
        barra_menu.add(menu_relatorios);
        barra_menu.add(menu_sistema);
        
        //------------
        
        setJMenuBar(barra_menu);
        
        //FIM
        //-------------------------------------------------------------------------------------------
                
        
        
        //AQUI É INSERIDO O PANEL TABULADO
        //-------------------------------------------------------------------------------------------
        
        painel_tabulado = new JTabbedPane();
        painel_tabulado.setFont(fonte);
        
        painel_tabulado.setBounds(0, 70, width, height);
        painel_tabulado.addTab("Centros de custo", null, null);
        painel_tabulado.addTab("Agenda de serviço", null, null);
        
        add(painel_tabulado);
        
        //FIM
        //-------------------------------------------------------------------------------------------
        
        
        //AQUI SÃO INSERIDOS OS BOTÕES E O PAINEL
        //-------------------------------------------------------------------------------------------
        
        painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(0, 0, width, 90);
        
        btnClientes = new JButton("Clientes");
        btnClientes.setBounds(10, 10, 100, 50);
        btnClientes.setFont(fonte);
        btnClientes.setIcon(new ImageIcon(getClass().getResource("/imagens/Cliente 16px.png")));
        btnClientes.setHorizontalTextPosition(SwingConstants.CENTER);   
        btnClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnEmpresas = new JButton("Empresas");
        btnEmpresas.setBounds(115, 10, 100, 50);
        btnEmpresas.setFont(fonte);
        btnEmpresas.setIcon(new ImageIcon(getClass().getResource("/imagens/Trocar usuário 16px.png")));
        btnEmpresas.setHorizontalTextPosition(SwingConstants.CENTER);   
        btnEmpresas.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEmpresas.addActionListener(this);
        
        btnEstoque = new JButton("Estoque");
        btnEstoque.setBounds(220, 10, 100, 50);
        btnEstoque.setFont(fonte);
        btnEstoque.setIcon(new ImageIcon(getClass().getResource("/imagens/Transferir 16px.png")));
        btnEstoque.setHorizontalTextPosition(SwingConstants.CENTER); 
        btnEstoque.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnVendas = new JButton("Vendas");
        btnVendas.setBounds(325, 10, 100, 50);
        btnVendas.setFont(fonte);
        btnVendas.setIcon(new ImageIcon(getClass().getResource("/imagens/Dinheiro 16px.png")));
        btnVendas.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVendas.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnServicos = new JButton("Serviços");
        btnServicos.setBounds(430, 10, 100, 50);
        btnServicos.setFont(fonte);
        btnServicos.setIcon(new ImageIcon(getClass().getResource("/imagens/Configurações 16px.png")));
        btnServicos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnServicos.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnOrcamento = new JButton("Orçamento");
        btnOrcamento.setBounds(535, 10, 100, 50);
        btnOrcamento.setFont(fonte);
        btnOrcamento.setIcon(new ImageIcon(getClass().getResource("/imagens/Símbolo de dinheiro 16px.png")));
        btnOrcamento.setHorizontalTextPosition(SwingConstants.CENTER);
        btnOrcamento.setVerticalTextPosition(SwingConstants.BOTTOM); 
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(640, 10, 100, 50);
        btnLogout.setFont(fonte);
        btnLogout.setIcon(new ImageIcon(getClass().getResource("/imagens/Sair 16px.png")));
        btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLogout.setVerticalTextPosition(SwingConstants.BOTTOM);  
        btnLogout.addActionListener(this);
        
        btnSair = new JButton("Sair");
        btnSair.setBounds(745, 10, 100, 50);
        btnSair.setFont(fonte);
        btnSair.setIcon(new ImageIcon(getClass().getResource("/imagens/Sair02 16px.png")));
        btnSair.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSair.addActionListener(this);
        
        painel.add(btnClientes);
        painel.add(btnEmpresas);
        painel.add(btnEstoque);
        painel.add(btnVendas);
        painel.add(btnServicos);
        painel.add(btnOrcamento);
        painel.add(btnLogout);
        painel.add(btnSair);
        add(painel);
        
        //FIM
        //-------------------------------------------------------------------------------------------
    }
    
    private Dimension getTamanhoTela() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();  
        return toolkit.getScreenSize(); 
    }
    
    public void abrirCadastroUsuarios() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de usuários");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            UsuarioJanela usuario = new UsuarioJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de usuários", null, usuario);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroFuncoes() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de funções");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            FuncionarioFuncaoJanela funcao = new FuncionarioFuncaoJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de funções", null, funcao);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroFuncionarios() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de funcionários");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            FuncionarioJanela funcionario = new FuncionarioJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de funcionários", null, funcionario);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroSetorProdutos() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de setores de produtos");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            ProdutoSetorJanela setor = new ProdutoSetorJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de setores de produtos", null, setor);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroProdutos() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de produtos");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            ProdutoJanela prod = new ProdutoJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de produtos", null, prod);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroCentrosEstoque() {
        
    }
    
    public void abrirCadastroFornecedores() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de fornecedores");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            FornecedorJanela forn = new FornecedorJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de fornecedores", null, forn);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroClientes() {
        
    }
    
    public void abrirCadastroEmpresas() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de empresas");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            EmpresaJanela empresa = new EmpresaJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de empresas", null, empresa);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroCentrosCusto() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de centros de custo");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            CentroCustoJanela centro = new CentroCustoJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de centros de custo", null, centro);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroSetoresFinanceiros() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de setores financeiros");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            FinanceiroSetorJanela centro = new FinanceiroSetorJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de setores financeiros", null, centro);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroFormasPagamento() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de formas de pagamento");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            FormaPagamentoJanela centro = new FormaPagamentoJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de formas de pagamento", null, centro);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void abrirCadastroMaquinasCartao() {
        VerificaMenuAberto verifica = new VerificaMenuAberto();
        int index = verifica.verificaMenuAberto(painel_tabulado, "Cadastro de máquinas de cartão");
        if (index >= 0)
        {
            painel_tabulado.setSelectedIndex(index);
        }
        else
        {
            FormaPagamentoMaquinaCartaoJanela centro = new FormaPagamentoMaquinaCartaoJanela(this.user, width, this.painel_tabulado);
            painel_tabulado.addTab("Cadastro de máquinas de cartão", null, centro);
            painel_tabulado.setSelectedIndex(painel_tabulado.getTabCount()-1);
        }
    }
    
    public void trocarUsuario() {
        if (JOptionPane.showConfirmDialog(null, "Deseja trocar de usuário?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            new LoginJanela().setVisible(true);
            dispose();
        }
    }
    
    public void sair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu_item_usuarios) {
            abrirCadastroUsuarios();
        } else if (e.getSource() == menu_item_funcoes) {
            abrirCadastroFuncoes();
        } else if (e.getSource() == menu_item_funcionarios) {
            abrirCadastroFuncionarios();
        } else if (e.getSource() == menu_item_setor_produtos) {
            abrirCadastroSetorProdutos();
        } else if (e.getSource() == menu_item_produtos) {
            abrirCadastroProdutos();
        } else if (e.getSource() == menu_item_centros_estoque) {
            abrirCadastroCentrosEstoque();
        } else if (e.getSource() == menu_item_fornecedores) {
            abrirCadastroFornecedores();
        } else if (e.getSource() == menu_item_pessoa_fisica) {
            abrirCadastroClientes();
        } else if (e.getSource() == menu_item_pessoa_juridica) {
            abrirCadastroEmpresas();
        } else if (e.getSource() == menu_item_centros_custo) {
            abrirCadastroCentrosCusto();
        } else if (e.getSource() == menu_item_setor_financeiro) {
            abrirCadastroSetoresFinanceiros();
        } else if (e.getSource() == menu_item_forma_pagamento) {
            abrirCadastroFormasPagamento();
        } else if (e.getSource() == menu_item_maquinas_cartoes) {
            abrirCadastroMaquinasCartao();
        } else if (e.getSource() == menu_item_trocar_usuario) {
            trocarUsuario();
        } else if (e.getSource() == menu_item_sair) {
            sair();
        } else if (e.getSource() == btnEmpresas) {
            abrirCadastroEmpresas();
        } else if (e.getSource() == btnLogout) {
            trocarUsuario();
        } else if (e.getSource() == btnSair) {
            sair();
        }
    }
    
}
