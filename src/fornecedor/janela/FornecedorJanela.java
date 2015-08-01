/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.janela;

import fornecedor.classe.FornecedorClasse;
import fornecedor.controller.FornecedorController;
import fornecedor.table.FornecedorTableModel;
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
import principal.VerificaMenuAberto;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class FornecedorJanela extends JPanel implements KeyListener, ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblPesquisar;
    private JTextField txtPesquisar;
    private JScrollPane scrollTabela;
    private JTable tabela;
    private JButton btnNovo, btnEditar, btnExcluir, btnSair;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<FornecedorClasse> fornecedor;
    
    private JTabbedPane painel_principal;
    
    public FornecedorJanela(UsuarioClasse user, int width, JTabbedPane painel_principal) {
        this.painel_principal = painel_principal;
        
        setLayout(null);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("MANUTENÇÃO DO CADASTRO DE FORNECEDORES");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 500, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblPesquisar = new JLabel("Pesquisar fornecedor");
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
        refreshTable();
        
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
    
    public void refreshTable() {
        fornecedor = new FornecedorController().findFornecedor(txtPesquisar.getText());
        if (fornecedor != null) {
            tabela.setModel(new FornecedorTableModel(fornecedor));
            if (tabela.getRowCount() > 0) {
                tabela.setRowSelectionInterval(0, 0);
                tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(0, 0, true)));
            }
        }
    }
    
    public void novo() {
        try {
            FornecedorCadastrar cadastrar = new FornecedorCadastrar();
            cadastrar.setVisible(true);
            if(cadastrar.cadastrou == true) {
                refreshTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editar() {
        try {
            FornecedorEditar editar = new FornecedorEditar(fornecedor.get(tabela.getSelectedRow()));
            editar.setVisible(true);
            if (editar.editou == true) {
                refreshTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluir() {
        FornecedorClasse forn = fornecedor.get(tabela.getSelectedRow());
        FornecedorExcluir excluir = new FornecedorExcluir();
        boolean result = excluir.excluir(forn);
        if (result == true) {
            JOptionPane.showMessageDialog(null, "Fornecedor "+forn.getRazao_social()+ " excluído com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o fornecedor "+forn.getRazao_social()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
            int index = verifica.verificaMenuAberto(painel_principal, "Cadastro de fornecedores");
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
                refreshTable();
            }
        }
    }
    
}
