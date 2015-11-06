/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento.janela;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import orcamento.classe.OrcamentoClasse;
import orcamento.comboBox.OrcamentoComboBoxPesquisar;
import usuario.classe.UsuarioClasse;
import utilidades.VerificaMenuAberto;

/**
 *
 * @author Marcos
 */
public class OrcamentoJanela extends JPanel implements ActionListener, KeyListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblPesquisar;
    private JComboBox comboPesquisar;
    private JTextField txtPesquisar;
    private JScrollPane scrollTabela;
    private JTable tabela;
    private JButton btnData;
    private JDateChooser calendarPesquisar;
    private JButton btnNovo, btnEditar, btnExcluir, btnSair;
    
    private List<OrcamentoClasse> lista_orcamento;
    
    private Font fonteTitulo, fonteGeral;
    
    private JTabbedPane painel_principal;
    
    public OrcamentoJanela(UsuarioClasse user, int width, JTabbedPane painel_principal) {
        
        this.painel_principal = painel_principal;
        
        setLayout(null);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("MANUTENÇÃO DE ORÇAMENTOS");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 700, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblPesquisar = new JLabel("Pesquisar orçamento");
        lblPesquisar.setFont(fonteGeral);
        lblPesquisar.setBounds(30, 30, 120, 30);
        
        comboPesquisar = new JComboBox(new OrcamentoComboBoxPesquisar());
        comboPesquisar.setFont(fonteGeral);
        comboPesquisar.setBounds(170, 30, 130, 30);
        comboPesquisar.addActionListener(this);
        
        txtPesquisar = new JTextField();
        txtPesquisar.setFont(fonteGeral);
        txtPesquisar.setBounds(320, 30, 310, 30);
        txtPesquisar.setVisible(false);
        txtPesquisar.addKeyListener(this);
        
        calendarPesquisar = new JDateChooser(new Date());
        calendarPesquisar.setFont(fonteGeral);
        calendarPesquisar.setBounds(320, 30, 100, 30);
        calendarPesquisar.setVisible(false);
        
        //btnData = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        //btnData.setBounds(540, 30, 40, 30);
        //btnData.addActionListener(this);
        
        tabela = new JTable();
        tabela.setFont(fonteGeral);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        //refreshTable();
        
        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(30, 70, 1000, 250);
        
        painel2.add(lblPesquisar);
        painel2.add(comboPesquisar);
        painel2.add(txtPesquisar);
        painel2.add(calendarPesquisar);
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
    
    /*public void refreshTable() {
        lista_movimentacoes = new ContaBancariaMovimentacaoController().findMovimentacao(this.centro_custo.getId(), calendarData1.getDate(), calendarData2.getDate());
        if (lista_movimentacoes != null) {
            tabela.setModel(new ContaBancariaMovimentacaoTableModel(lista_movimentacoes));
            if (tabela.getRowCount() > 0) {
                tabela.setRowSelectionInterval(0, 0);
                tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(0, 0, true)));
            }
        }
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(470);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(110);
    }*/
    
    public void alterarPesquisa() {
        if (comboPesquisar.getSelectedIndex() == 0) {
            //PESQUISAR TODOS
            txtPesquisar.setVisible(false);
            calendarPesquisar.setVisible(false);
            painel2.repaint();
        }
        else if (comboPesquisar.getSelectedIndex() == 3) {
            //PESQUISAR ORÇAMENTOS POR DATA
            txtPesquisar.setVisible(false);
            calendarPesquisar.setVisible(true);
            painel2.repaint();
            //calendarPesquisar.repaint();
        } else {
            calendarPesquisar.setVisible(false);
            txtPesquisar.setVisible(true);
            painel2.repaint();
            txtPesquisar.setText("");
            txtPesquisar.grabFocus();
        }
    }
    
    public void novo() {
        OrcamentoCadastrar cadastrar = new OrcamentoCadastrar();
        cadastrar.setVisible(true);
        if (cadastrar.cadastrou == true) {
            //refreshTable();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboPesquisar) {
            alterarPesquisa();
        } else if (e.getSource() == btnNovo) {
            novo();
        } else if (e.getSource() == btnEditar) {
            
        } else if (e.getSource() == btnExcluir) {
            
        } else if (e.getSource() == btnSair) {
            VerificaMenuAberto verifica = new VerificaMenuAberto();
            int index = verifica.verificaMenuAberto(painel_principal, "Manutenção de orçamentos");
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
        
    }
    
}
