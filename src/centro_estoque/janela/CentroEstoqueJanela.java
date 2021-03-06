/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.janela;

import centro_estoque.classe.CentroEstoqueClasse;
import centro_estoque.controller.CentroEstoqueController;
import centro_estoque.table.CentroEstoqueTableModel;
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
import usuario.classe.UsuarioClasse;
import utilidades.VerificaMenuAberto;

/**
 *
 * @author Marcos
 */
public class CentroEstoqueJanela extends JPanel implements ActionListener, KeyListener {
    
    private JPanel painel1, painel2, painel3;
    private JButton btnNovo, btnEditar, btnExcluir, btnSair;
    private JLabel lblTitulo, lblPesquisar;
    private JTextField txtPesquisar;
    private JScrollPane scrollTabela;
    private JTable tabela;
    
    private List<CentroEstoqueClasse> lista;
    
    private Font fonteTitulo, fonteGeral;
    
    private JTabbedPane painel_principal;
    
    public CentroEstoqueJanela(UsuarioClasse user, int width, JTabbedPane painel_principal) {
        
        this.painel_principal = painel_principal;
        
        setLayout(null);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("MANUTENÇÃO DO CADASTRO DE CENTROS DE ESTOQUE");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 550, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblPesquisar = new JLabel("Pesquisar c. de estoque");
        lblPesquisar.setFont(fonteGeral);
        lblPesquisar.setBounds(30, 30, 140, 30);
        
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
    
    public void novo() {
        CentroEstoqueCadastrar cadastrar = new CentroEstoqueCadastrar();
        cadastrar.setVisible(true);
        if (cadastrar.cadastrou == true) {
            refreshTable();
        }
    }
    
    public void editar() {
        CentroEstoqueEditar editar = new CentroEstoqueEditar(lista.get(tabela.getSelectedRow()));
        editar.setVisible(true);
        if (editar.editou == true) {
            refreshTable();
        }
    }
    
    public void excluir() {
        CentroEstoqueClasse classe = lista.get(tabela.getSelectedRow());
        if (new CentroEstoqueExcluir().excluir(classe) == true) {
            JOptionPane.showMessageDialog(null, "Centro de estoque "+classe.getNome()+" excluído com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o centro de estoque "+classe.getNome()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshTable() {
        lista = new CentroEstoqueController().findCentroEstoque(txtPesquisar.getText());
        if (lista != null) {
            tabela.setModel(new CentroEstoqueTableModel(lista));
            if (tabela.getRowCount() > 0) {
                tabela.setRowSelectionInterval(0, 0);
                tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(0, 0, true)));
            }
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
            int index = verifica.verificaMenuAberto(painel_principal, "Cadastro de centros de estoque");
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
