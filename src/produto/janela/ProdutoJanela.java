/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.janela;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import utilidades.VerificaMenuAberto;
import produto.classe.ProdutoClasse;
import produto.controller.ProdutoController;
import produto.table.ProdutoTableModel;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoJanela extends JPanel implements ActionListener, KeyListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblPesquisar;
    private JTextField txtPesquisar;
    private JScrollPane scrollTabela;
    private JTable tabela;
    private JButton btnNovo, btnEditar, btnExcluir, btnSair;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<ProdutoClasse> produto;
    
    private JTabbedPane painel_principal;
    
    public ProdutoJanela(UsuarioClasse user, int width, JTabbedPane painel_principal) {
        
        this.painel_principal = painel_principal;
        
        setLayout(null);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("MANUTENÇÃO DO CADASTRO DE PRODUTOS");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 450, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblPesquisar = new JLabel("Pesquisar produto");
        lblPesquisar.setFont(fonteGeral);
        lblPesquisar.setBounds(30, 30, 120, 30);
        
        txtPesquisar = new JTextField();
        txtPesquisar.setFont(fonteGeral);
        txtPesquisar.setBounds(170, 30, 430, 30);
        txtPesquisar.addKeyListener(this);
        
        tabela = new JTable();
        tabela.setFont(fonteGeral);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        refreshTable(0);
        
        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(30, 70, 570, 250);
        
        painel2.add(lblPesquisar);
        painel2.add(txtPesquisar);
        painel2.add(scrollTabela);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 410, width, 100);
        
        btnNovo = new JButton("Novo");
        btnNovo.setFont(fonteGeral);
        btnNovo.setBounds(30, 20, 110, 30);
        btnNovo.addActionListener(this);
        
        btnEditar = new JButton("Editar");
        btnEditar.setFont(fonteGeral);
        btnEditar.setBounds(150, 20, 110, 30);
        btnEditar.addActionListener(this);
        
        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(fonteGeral);
        btnExcluir.setBounds(270, 20, 110, 30);
        btnExcluir.addActionListener(this);
        
        btnSair = new JButton("Sair");
        btnSair.setFont(fonteGeral);
        btnSair.setBounds(390, 20, 110, 30);
        btnSair.addActionListener(this);
        
        painel3.add(btnNovo);
        painel3.add(btnEditar);
        painel3.add(btnExcluir);
        painel3.add(btnSair);
        
        add(painel1);
        add(painel2);
        add(painel3);
        
    }
    
    public void refreshTable(int linha) {
        produto = new ProdutoController().findProduto(txtPesquisar.getText());
        if (produto != null) {
            tabela.setModel(new ProdutoTableModel(produto, 3));
            if (tabela.getRowCount() > 0 && linha >= 0) {
                tabela.setRowSelectionInterval(linha, linha);
                tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(linha, linha, true)));
            }
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(370);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        }
    }
    
    public void novo() {
        ProdutoCadastrar cadastrar = new ProdutoCadastrar();
        cadastrar.setVisible(true);
        if(cadastrar.cadastrou == true) {
            ProdutoClasse prod = produto.get(tabela.getSelectedRow());
            refreshTable(-1);
            for (int i = 0; i < produto.size(); i++) {
                if (produto.get(i).getNome().equals(prod.getNome())) {
                    tabela.setRowSelectionInterval(i, i);
                    tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(i, i, true)));
                }
            }
        }
    }
    
    public void editar() {
        ProdutoEditar editar = new ProdutoEditar(produto.get(tabela.getSelectedRow()));
        editar.setVisible(true);
        if (editar.editou == true) {
            refreshTable(tabela.getSelectedRow());
        }
    }
    
    public void excluir() {
        ProdutoClasse prod = produto.get(tabela.getSelectedRow());
        ProdutoExcluir excluir = new ProdutoExcluir();
        boolean result = excluir.excluir(prod);
        if (result == true) {
            JOptionPane.showMessageDialog(null, "Produto "+prod.getNome()+ " excluído com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            int index = tabela.getSelectedRow();
            if (index == tabela.getRowCount()-1) {
                index = index - 1;
            }
            refreshTable(index);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o produto "+prod.getNome()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnNovo) {
            novo();
        } else if (source == btnEditar) {
            editar();
        } else if (source == btnExcluir) {
            excluir();
        } else if (source == btnSair) {
            VerificaMenuAberto verifica = new VerificaMenuAberto();
            int index = verifica.verificaMenuAberto(painel_principal, "Cadastro de produtos");
            painel_principal.remove(index);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (e.getSource() == txtPesquisar) {
            if (key != 38 && key != 40 && key != 112 && key != 113 && key != 114 && key != 115 && key != 116 && key != 27) {
                refreshTable(tabela.getSelectedRow());
            }
        }
    }
    
}
