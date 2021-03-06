/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.controller.CentroCustoController;
import centro_custo.table.CentroCustoTableModel;
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
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class CentroCustoJanela extends JPanel implements ActionListener, KeyListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblPesquisar;
    private JTextField txtPesquisar;
    private JScrollPane scrollTabela;
    private JTable tabela;
    private JButton btnNovo, btnEditar, btnExcluir, btnTransferirSaldo, btnResponsavel, btnSair;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<CentroCustoClasse> centro;
    
    private JTabbedPane painel_principal;
    
    public CentroCustoJanela(UsuarioClasse user, int width, JTabbedPane painel_principal) {
        this.painel_principal = painel_principal;
        
        setLayout(null);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("MANUTENÇÃO DO CADASTRO DE CENTROS DE CUSTO");
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 550, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblPesquisar = new JLabel("Pesquisar c. de custo");
        lblPesquisar.setFont(fonteGeral);
        lblPesquisar.setBounds(30, 30, 130, 30);
        
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
        
        btnTransferirSaldo = new JButton("Transferir saldo");
        btnTransferirSaldo.setFont(fonteGeral);
        btnTransferirSaldo.setBounds(390, 20, 140, 30);
        btnTransferirSaldo.addActionListener(this);
        
        btnResponsavel = new JButton("Gerenciar responsáveis");
        btnResponsavel.setFont(fonteGeral);
        btnResponsavel.setBounds(540, 20, 170, 30);
        btnResponsavel.addActionListener(this);
        
        btnSair = new JButton("Sair");
        btnSair.setFont(fonteGeral);
        btnSair.setBounds(720, 20, 110, 30);
        btnSair.addActionListener(this);
        
        painel3.add(btnNovo);
        painel3.add(btnEditar);
        painel3.add(btnExcluir);
        painel3.add(btnTransferirSaldo);
        painel3.add(btnResponsavel);
        painel3.add(btnSair);
        
        add(painel1);
        add(painel2);
        add(painel3);
        
    }
    
    public void refreshTable() {
        centro = new CentroCustoController().findCentroCusto(txtPesquisar.getText());
        if (centro != null) {
            tabela.setModel(new CentroCustoTableModel(centro));
            if (tabela.getRowCount() > 0) {
                tabela.setRowSelectionInterval(0, 0);
                tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(0, 0, true)));
            }
        }
    }
    
    public void novo() {
        CentroCustoCadastrar cadastrar = new CentroCustoCadastrar();
        cadastrar.setVisible(true);
        if(cadastrar.cadastrou == true) {
            refreshTable();
        }
    }
    
    public void editar() {
        CentroCustoEditar editar = new CentroCustoEditar(centro.get(tabela.getSelectedRow()));
        editar.setVisible(true);
        if (editar.editou == true) {
            refreshTable();
        }
    }
    
    public void transferirSaldo() {
        CentroCustoClasse centro_custo = centro.get(tabela.getSelectedRow());
        if (centro_custo.getSaldo() == 0) {
            JOptionPane.showMessageDialog(null, "Este centro de custo não possui saldo para transferir!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            CentroCustoTransferirSaldo transferir = new CentroCustoTransferirSaldo(centro_custo);
            transferir.setVisible(true);
            if (transferir.transferiu == true) {
                refreshTable();
            }
        }
    }
    
    public void gerenciarResponsaveis() {
        CentroCustoGerenciarResponsavel responsavel = new CentroCustoGerenciarResponsavel(centro.get(tabela.getSelectedRow()));
        responsavel.setVisible(true);
        
    }
    
    public void excluir() {
        CentroCustoClasse centro_custo = centro.get(tabela.getSelectedRow());
        CentroCustoExcluir excluir = new CentroCustoExcluir();
        boolean result = excluir.excluir(centro_custo);
        if (result == true) {
            JOptionPane.showMessageDialog(null, "Centro de custo "+centro_custo.getNome()+ " excluído com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o centro de custo "+centro_custo.getNome()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
        } else if (source == btnTransferirSaldo) {
            transferirSaldo();
        } else if (source == btnResponsavel) {
            gerenciarResponsaveis();
        } else if (source == btnSair) {
            VerificaMenuAberto verifica = new VerificaMenuAberto();
            int index = verifica.verificaMenuAberto(painel_principal, "Cadastro de centros de custo");
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
