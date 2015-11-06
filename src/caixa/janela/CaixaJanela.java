/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa.janela;

import caixa.controller.CaixaController;
import caixa.classe.CaixaClasse;
import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import caixa_movimentacao.controller.CaixaMovimentacaoController;
import caixa_movimentacao.table.CaixaMovimentacaoTableModel;
import centro_custo.classe.CentroCustoClasse;
import centro_custo.janela.CentroCustoTransferirSaldo;
import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.controller.FormaPagamentoController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class CaixaJanela extends JPanel implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblAbertura1, lblAbertura2, lblSaldo1, lblSaldo2;
    private JScrollPane scrollTabela;
    private JTable tabela;
    private JButton btnAbrirCaixa, btnTransferirSaldo, btnFecharCaixa;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private List<CaixaMovimentacaoClasse> lista_movimentacoes;
    
    private CaixaClasse caixa;
    
    private CentroCustoClasse centro_custo;
    
    private List<FormaPagamentoClasse> lista_formas_pagamento;
    
    public CaixaJanela(UsuarioClasse user, int width, CentroCustoClasse centro_custo) {
        
        this.centro_custo = centro_custo;
        
        CaixaController cont = new CaixaController();
        caixa = cont.getCaixa(this.centro_custo.getId());
        
        //SE O CAIXA ESTIVER FECHADO, SEU ID SERÁ NULL, DEVEMOS MUDAR O ID PARA 0;
        if (this.caixa.getId() == null) {
            caixa.setId(Long.valueOf(0));
        }
        
        setLayout(null);
        
        lista_formas_pagamento = new FormaPagamentoController().findFormaPagamento("");
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fonteNegrito = new Font("Tahoma", Font.BOLD, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, width, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("GERENCIAMENTO DO CAIXA: "+this.centro_custo.getNome().toUpperCase());
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(30, 0, 700, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, width, 350);
        
        lblAbertura1 = new JLabel("Data de abertura");
        lblAbertura1.setFont(fonteGeral);
        lblAbertura1.setBounds(30, 30, 100, 30);
        
        lblAbertura2 = new JLabel("");
        lblAbertura2.setFont(fonteNegrito);
        lblAbertura2.setForeground(Color.red);
        lblAbertura2.setBounds(150, 30, 400, 30);
        
        lblSaldo1 = new JLabel("Saldo do caixa");
        lblSaldo1.setFont(fonteGeral);
        lblSaldo1.setBounds(690, 30, 90, 30);
        
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
        
        painel2.add(scrollTabela);
        painel2.add(lblAbertura1);
        painel2.add(lblAbertura2);
        painel2.add(lblSaldo1);
        painel2.add(lblSaldo2);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 410, width, 100);
        
        btnAbrirCaixa = new JButton("Abrir caixa");
        btnAbrirCaixa.setFont(fonteGeral);
        btnAbrirCaixa.setBounds(30, 20, 150, 30);
        btnAbrirCaixa.addActionListener(this);
        
        btnTransferirSaldo = new JButton("Transferir saldo");
        btnTransferirSaldo.setFont(fonteGeral);
        btnTransferirSaldo.setBounds(190, 20, 150, 30);
        btnTransferirSaldo.addActionListener(this);
        
        btnFecharCaixa = new JButton("Fechar caixa");
        btnFecharCaixa.setFont(fonteGeral);
        btnFecharCaixa.setBounds(350, 20, 150, 30);
        btnFecharCaixa.addActionListener(this);
        
        verificaCaixaAberto();
        
        painel3.add(btnAbrirCaixa);
        painel3.add(btnFecharCaixa);
        painel3.add(btnTransferirSaldo);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }
    
    public void verificaCaixaAberto() {
        if (caixa.getData_abertura() == null) {
            lblAbertura2.setText("Este caixa ainda não está aberto! Abra-o para executar ações!");
            
            btnAbrirCaixa.setEnabled(true);
            btnFecharCaixa.setEnabled(false);
            btnTransferirSaldo.setEnabled(false);
        } else {
            lblAbertura2.setText("Este caixa foi aberto em: "+caixa.getData_abertura());
            btnAbrirCaixa.setEnabled(false);
            btnFecharCaixa.setEnabled(true);
            btnTransferirSaldo.setEnabled(true);
        }
    }
    
    public void refreshTable() {
        lista_movimentacoes = new CaixaMovimentacaoController().findMovimentacao(this.caixa.getId());
        if (lista_movimentacoes != null) {
            tabela.setModel(new CaixaMovimentacaoTableModel(lista_movimentacoes));
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
    
    public void abrirCaixa() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente abrir o caixa?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            //ABRO O CAIXA
            CaixaController cont = new CaixaController();
            int result = cont.abrirCaixa(centro_custo.getId());
            if (result == 1) {
                caixa = cont.getCaixa(centro_custo.getId());
                
                NumberFormat nb = NumberFormat.getCurrencyInstance();

                //ADICIONO A MOVIMENTAÇÃO DE ABERTURA
                CaixaMovimentacaoClasse caixaMovClasse = new CaixaMovimentacaoClasse();
                caixaMovClasse.setId_caixa(caixa.getId());
                caixaMovClasse.setDescricao("Abertura do caixa! Saldo atual: "+nb.format(centro_custo.getSaldo()));
                for (int i = 0; i < lista_formas_pagamento.size(); i++) {
                    if (lista_formas_pagamento.get(i).getNome().equals("Dinheiro")) {
                        caixaMovClasse.setId_forma_pagamento(lista_formas_pagamento.get(i).getId());
                    }
                }
                caixaMovClasse.setValor(centro_custo.getSaldo());
                caixaMovClasse.setTipo("Abertura");
                
                CaixaMovimentacaoController contMov = new CaixaMovimentacaoController();
                contMov.addMovimentacao(caixaMovClasse);

                //FAÇO ALTERAÇÕES NO LAYOUT
                verificaCaixaAberto();
                
                //CARREGO A TABELA PARA APARECER AS MOVIMENTAÇÕES
                refreshTable();
                
                JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar abrir caixa!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void fecharCaixa() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o caixa?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            
            NumberFormat nb = NumberFormat.getCurrencyInstance();
            
            //ADICIONO A MOVIMENTAÇÃO DE FECHAMENTO
            CaixaMovimentacaoClasse caixaMovClasse = new CaixaMovimentacaoClasse();
            caixaMovClasse.setId_caixa(caixa.getId());
            caixaMovClasse.setDescricao("Fechamento do caixa! Saldo final: "+nb.format(centro_custo.getSaldo()));
            for (int i = 0; i < lista_formas_pagamento.size(); i++) {
                if (lista_formas_pagamento.get(i).getNome().equals("Dinheiro")) {
                    caixaMovClasse.setId_forma_pagamento(lista_formas_pagamento.get(i).getId());
                }
            }
            caixaMovClasse.setValor(centro_custo.getSaldo());
            caixaMovClasse.setTipo("Fechamento");
            
            CaixaMovimentacaoController contMov = new CaixaMovimentacaoController();
            contMov.addMovimentacao(caixaMovClasse);

            //FECHO O CAIXA
            CaixaController cont = new CaixaController();
            int result = cont.fecharCaixa(caixa.getId());
            if (result == 1) {
                
                caixa = new CaixaClasse();
                caixa.setId(Long.valueOf(0));
                
                //FAÇO ALTERAÇÕES NO LAYOUT
                verificaCaixaAberto();

                //CARREGO A TABELA NOVAMENTE
                refreshTable();

                JOptionPane.showMessageDialog(null, "Caixa fechado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar fechar caixa!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        if (e.getSource() == btnAbrirCaixa) {
            abrirCaixa();
        }  else if (e.getSource() == btnTransferirSaldo) {
            transferirSaldo();
        } else if (e.getSource() == btnFecharCaixa) {
            fecharCaixa();
        }
    }
    
}
