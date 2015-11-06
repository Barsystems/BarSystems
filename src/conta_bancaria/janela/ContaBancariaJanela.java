/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_bancaria.janela;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.janela.CentroCustoTransferirSaldo;
import com.toedter.calendar.JDateChooser;
import conta_bancaria_movimentacao.classe.ContaBancariaMovimentacaoClasse;
import conta_bancaria_movimentacao.controller.ContaBancariaMovimentacaoController;
import conta_bancaria_movimentacao.table.ContaBancariaMovimentacaoTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class ContaBancariaJanela extends JPanel implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblData1, lblData2, lblData3, lblSaldo1, lblSaldo2;
    private JScrollPane scrollTabela;
    private JTable tabela;
    private JButton btnTransferirSaldo, btnData;
    private JDateChooser calendarData1, calendarData2;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private List<ContaBancariaMovimentacaoClasse> lista_movimentacoes;
    
    private CentroCustoClasse centro_custo;
    
    public ContaBancariaJanela(UsuarioClasse user, int width, CentroCustoClasse centro_custo) {
        
        this.centro_custo = centro_custo;
        
        setLayout(null);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fonteNegrito = new Font("Tahoma", Font.BOLD, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("GERENCIAMENTO DA CONTA BANCÁRIA: "+this.centro_custo.getNome().toUpperCase());
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 700, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblData1 = new JLabel("Data da movimentação");
        lblData1.setFont(fonteGeral);
        lblData1.setBounds(30, 30, 150, 30);
        
        lblData2 = new JLabel("De");
        lblData2.setFont(fonteNegrito);
        lblData2.setForeground(Color.red);
        lblData2.setBounds(200, 30, 30, 30);
        
        calendarData1 = new JDateChooser(new Date());
        calendarData1.setFont(fonteGeral);
        calendarData1.setBounds(250, 30, 100, 30);
        
        lblData3 = new JLabel("Até");
        lblData3.setFont(fonteNegrito);
        lblData3.setForeground(Color.red);
        lblData3.setBounds(370, 30, 30, 30);
        
        calendarData2 = new JDateChooser(new Date());
        calendarData2.setFont(fonteGeral);
        calendarData2.setBounds(420, 30, 100, 30);
        
        btnData = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        btnData.setBounds(540, 30, 40, 30);
        btnData.addActionListener(this);
        
        lblSaldo1 = new JLabel("Saldo da conta bancária");
        lblSaldo1.setFont(fonteGeral);
        lblSaldo1.setBounds(690, 30, 140, 30);
        
        NumberFormat nb = NumberFormat.getCurrencyInstance();
        lblSaldo2 = new JLabel(nb.format(centro_custo.getSaldo()));
        lblSaldo2.setFont(fonteNegrito);
        lblSaldo2.setForeground(Color.red);
        lblSaldo2.setBounds(850, 30, 300, 30);
        
        tabela = new JTable();
        tabela.setFont(fonteGeral);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        refreshTable();
        
        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(30, 70, 1000, 250);
        
        painel2.add(lblData1);
        painel2.add(lblData2);
        painel2.add(calendarData1);
        painel2.add(lblData3);
        painel2.add(calendarData2);
        painel2.add(btnData);
        painel2.add(lblSaldo1);
        painel2.add(lblSaldo2);
        painel2.add(scrollTabela);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 410, width, 100);
        
        btnTransferirSaldo = new JButton("Transferir saldo");
        btnTransferirSaldo.setFont(fonteGeral);
        btnTransferirSaldo.setBounds(30, 20, 150, 30);
        btnTransferirSaldo.addActionListener(this);
        
        painel3.add(btnTransferirSaldo);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }
    
    public void refreshTable() {
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
    }
    
    public void transferirSaldo() {
        CentroCustoTransferirSaldo transferir = new CentroCustoTransferirSaldo(centro_custo);
        transferir.setVisible(true);
        if (transferir.transferiu == true) {
            refreshTable();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTransferirSaldo) {
            transferirSaldo();
        } else if (e.getSource() == btnData) {
            refreshTable();
        }
    }
    
}
