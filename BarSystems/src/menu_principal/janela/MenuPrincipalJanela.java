/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_principal.janela;

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
    private JMenu menu_cadastros, menu_sistema;
    private JMenuItem menu_item_usuarios, menu_item_funcoes, menu_item_funcionarios, menu_item_setor_produtos, 
            menu_item_produtos, menu_item_centros_estoque, menu_item_trocar_usuario, menu_item_sair;
    private JPanel painel;
    private JButton btnClientes, btnEstoque;
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
        
        menu_cadastros = new JMenu("CADASTROS");
        menu_cadastros.setFont(fonte);
        
        menu_item_usuarios = new JMenuItem("Usuários");
        menu_item_usuarios.setFont(fonte);
        menu_item_usuarios.addActionListener(this);
        
        menu_item_funcoes = new JMenuItem("Funções");
        menu_item_funcoes.setFont(fonte);
        menu_item_funcoes.addActionListener(this);
        
        menu_item_funcionarios = new JMenuItem("Funcionários");
        menu_item_funcionarios.setFont(fonte);
        menu_item_funcionarios.addActionListener(this);
        
        menu_item_setor_produtos = new JMenuItem("Setores de produtos");
        menu_item_setor_produtos.setFont(fonte);
        menu_item_setor_produtos.addActionListener(this);
        
        menu_item_produtos = new JMenuItem("Produtos");
        menu_item_produtos.setFont(fonte);
        menu_item_produtos.addActionListener(this);
        
        menu_item_centros_estoque = new JMenuItem("Centros de estoque");
        menu_item_centros_estoque.setFont(fonte);
        menu_item_centros_estoque.addActionListener(this);
        
        menu_sistema = new JMenu("SISTEMA");
        menu_sistema.setFont(fonte);
        
        menu_item_trocar_usuario = new JMenuItem("Trocar usuário");
        menu_item_trocar_usuario.setFont(fonte);
        menu_item_trocar_usuario.addActionListener(this);
        
        menu_item_sair = new JMenuItem("Sair");
        menu_item_sair.setFont(fonte);
        menu_item_sair.addActionListener(this);
        
        barra_menu.add(menu_cadastros);
        barra_menu.add(menu_sistema);
        menu_cadastros.add(menu_item_usuarios);
        menu_cadastros.add(menu_item_funcoes);
        menu_cadastros.add(menu_item_funcionarios);
        menu_cadastros.add(menu_item_setor_produtos);
        menu_cadastros.add(menu_item_produtos);
        menu_cadastros.add(menu_item_centros_estoque);
        menu_sistema.add(menu_item_trocar_usuario);
        menu_sistema.add(menu_item_sair);
        
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
        btnClientes.setIcon(new ImageIcon(getClass().getResource("/Imagens/Cliente 16px.png")));
        btnClientes.setHorizontalTextPosition(SwingConstants.CENTER);   
        btnClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnEstoque = new JButton("Estoque");
        btnEstoque.setBounds(115, 10, 100, 50);
        btnEstoque.setFont(fonte);
        btnEstoque.setIcon(new ImageIcon(getClass().getResource("/Imagens/Estoque 16px.png")));
        btnEstoque.setHorizontalTextPosition(SwingConstants.CENTER); 
        btnEstoque.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        painel.add(btnClientes);
        painel.add(btnEstoque);
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
            
        } else if (e.getSource() == menu_item_trocar_usuario) {
            trocarUsuario();
        } else if (e.getSource() == menu_item_sair) {
            sair();
        }
    }
    
}
