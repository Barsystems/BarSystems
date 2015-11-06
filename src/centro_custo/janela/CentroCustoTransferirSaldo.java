/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import caixa.classe.CaixaClasse;
import caixa.controller.CaixaController;
import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import caixa_movimentacao.controller.CaixaMovimentacaoController;
import centro_custo.classe.CentroCustoClasse;
import centro_custo.comboBox.CentroCustoComboBox;
import centro_custo.comboBox.CentroCustoComboBoxCellRenderer;
import centro_custo.controller.CentroCustoController;
import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.controller.FormaPagamentoController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utilidades.TrocaVirgulaPorPonto;

/**
 *
 * @author Marcos
 */
public class CentroCustoTransferirSaldo extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblCentroCustoSair, lblCentroCustoSair2, lblSaldoDisponivel, lblSaldoDisponivel2, 
            lblCentroCustoEntrar, lblSaldoRetirar;
    private JTextField txtSaldoRetirar;
    private JComboBox comboCentroCustoEntrar;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private CentroCustoClasse centro_retirar_saldo;
    
    private List<CentroCustoClasse> lista_centros;
    
    private List<FormaPagamentoClasse> lista_formas_pagamento;
    
    public boolean transferiu = false;
    
    public CentroCustoTransferirSaldo(CentroCustoClasse centro_retirar_saldo) {
        
        this.centro_retirar_saldo = centro_retirar_saldo;
        
        setTitle("Transferir saldo");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 370);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fonteNegrito = new Font("Tahoma", Font.BOLD, 12);
        
        lista_formas_pagamento = new FormaPagamentoController().findFormaPagamento("");
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 480, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("TRANSFERIR SALDO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 480, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 480, 210);
        
        lblCentroCustoSair = new JLabel("Centro de custo de saída");
        lblCentroCustoSair.setFont(fonteGeral);
        lblCentroCustoSair.setBounds(30, 30, 140, 30);
        
        lblCentroCustoSair2 = new JLabel();
        lblCentroCustoSair2.setText(centro_retirar_saldo.getNome());
        lblCentroCustoSair2.setForeground(Color.red);
        lblCentroCustoSair2.setFont(fonteNegrito);
        lblCentroCustoSair2.setBounds(220, 30, 200, 30);
        
        lblSaldoDisponivel = new JLabel("Saldo disponível");
        lblSaldoDisponivel.setFont(fonteGeral);
        lblSaldoDisponivel.setBounds(30, 70, 140, 30);
        
        NumberFormat nb = NumberFormat.getCurrencyInstance();
        lblSaldoDisponivel2 = new JLabel();
        lblSaldoDisponivel2.setText(nb.format(centro_retirar_saldo.getSaldo()));
        lblSaldoDisponivel2.setForeground(Color.red);
        lblSaldoDisponivel2.setFont(fonteNegrito);
        lblSaldoDisponivel2.setBounds(220, 70, 200, 30);
        
        lblCentroCustoEntrar = new JLabel("Centro de custo de entrada");
        lblCentroCustoEntrar.setFont(fonteGeral);
        lblCentroCustoEntrar.setBounds(30, 110, 160, 30);
        
        comboCentroCustoEntrar = new JComboBox();
        comboCentroCustoEntrar.setFont(fonteGeral);
        comboCentroCustoEntrar.setBounds(220, 110, 200, 30);
        
        lista_centros = new CentroCustoController().findCentroCusto("");
        for (int i = 0; i < lista_centros.size(); i++) {
            if (lista_centros.get(i).getNome().equals(this.centro_retirar_saldo.getNome())) {
                lista_centros.remove(i);
            }
        }
        comboCentroCustoEntrar.setModel(new CentroCustoComboBox(lista_centros));
        comboCentroCustoEntrar.setRenderer(new CentroCustoComboBoxCellRenderer());
        
        lblSaldoRetirar = new JLabel("Saldo a retirar");
        lblSaldoRetirar.setFont(fonteGeral);
        lblSaldoRetirar.setBounds(30, 150, 140, 30);
        
        txtSaldoRetirar = new JTextField();
        txtSaldoRetirar.setFont(fonteGeral);
        txtSaldoRetirar.setBounds(220, 150, 200, 30);
        
        painel2.add(lblCentroCustoSair);
        painel2.add(lblCentroCustoSair2);
        painel2.add(lblSaldoDisponivel);
        painel2.add(lblSaldoDisponivel2);
        painel2.add(lblCentroCustoEntrar);
        painel2.add(comboCentroCustoEntrar);
        painel2.add(lblSaldoRetirar);
        painel2.add(txtSaldoRetirar);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 270, 480, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(140, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(250, 20, 100, 30);
        btnVoltar.addActionListener(this);
        
        painel3.add(btnSalvar);
        painel3.add(btnVoltar);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }
    
    public void salvar() {
        if (txtSaldoRetirar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo saldo a retirar não pode ficar em branco!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtSaldoRetirar.grabFocus();
        } else {
            //PRIMEIRO DEVEMOS VERIFICAR SE OS CENTROS DE CUSTO ENVOLVIDOS NA TRANSFERÊNCIA SÃO CAIXAS
            //SE FOREM, PRECISO VERIFICAR SE ESTÃO ABERTOS, POIS SE NÃO ESTIVEREM, NÃO POSSO DEIXAR A TRANSFERÊNCIA CONTINUAR
            
            boolean flag = true;
            CaixaController caixaCont = new CaixaController();
            if (centro_retirar_saldo.getTipo().equals("Caixa")) {
                if (caixaCont.verificaCaixaAberto(centro_retirar_saldo.getId()) == false) {
                    flag = false;
                    JOptionPane.showMessageDialog(null, "O centro de custo de saída é um caixa e ele não se encontra aberto no momento!\nAbra-o para poder transferir o saldo!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
                }
            }
            //DEVO VERIFICA UM A UM, SEM USAR ELSE IF
            if (lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getTipo().equals("Caixa")) {
                if (caixaCont.verificaCaixaAberto(lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getId()) == false) {
                    flag = false;
                    JOptionPane.showMessageDialog(null, "O centro de custo de destino é um caixa e ele não se encontra aberto no momento!\nAbra-o para poder transferir o saldo!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //SE TUDO CORRER BEM, SEGUE A TRANSFERÊNCIA
            if (flag == true) {
                
                //AGORA PRECISO VERIFICAR SE O SALDO QUE ESTOU TENTANDO RETIRAR NÃO É MAIOR QUE O SALDO DISPONÍVEL
                //SE FOR, NÃO POSSO DEIXAR A TRANSFE^RENCIA CONTINUAR
                TrocaVirgulaPorPonto troca = new TrocaVirgulaPorPonto();
                float saldo = troca.trocaVirgulaPorPonto(txtSaldoRetirar.getText());
                if (saldo > centro_retirar_saldo.getSaldo()) {
                    JOptionPane.showMessageDialog(null, "O saldo a retirar não pode ser maior que o saldo disponível!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    txtSaldoRetirar.selectAll();
                    txtSaldoRetirar.grabFocus();
                } else {
                    //SE TUDO CORRER BEM, SEGUE A TRANSFERÊNCIA
                    int result;
                    CentroCustoController cont = new CentroCustoController();
                    cont.diminuiSaldo(centro_retirar_saldo.getId(), saldo);
                    result = cont.aumentaSaldo(lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getId(), saldo);
                    
                    //AGORA PRECISO ADICIONAR A MOVIMENTAÇÃO NO CAIXA OU NA CONTA BANCÁRIA
                    //PARA ISSO, PRECISO VERIFICAR SE, TANTO O CENTRO DE ENTRADA QUANTO O DE SAÍDA, SÃO CAIXAS OU CONTAS
                    if (centro_retirar_saldo.getTipo().equals("Caixa")) {
                        CaixaMovimentacaoController contMov = new CaixaMovimentacaoController();
                        CaixaMovimentacaoClasse caixaMovClasse = new CaixaMovimentacaoClasse();
                        CaixaClasse caixa = caixaCont.getCaixa(centro_retirar_saldo.getId());
                        caixaMovClasse.setId_caixa(caixa.getId());
                        NumberFormat nb = NumberFormat.getCurrencyInstance();
                        caixaMovClasse.setDescricao("Transfêrencia de "+nb.format(saldo)+" para o centro de custo "+(lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getNome()));
                        for (int i = 0; i < lista_formas_pagamento.size(); i++) {
                            if (lista_formas_pagamento.get(i).getNome().equals("Dinheiro")) {
                                caixaMovClasse.setId_forma_pagamento(lista_formas_pagamento.get(i).getId());
                            }
                        }
                        caixaMovClasse.setValor(saldo);
                        caixaMovClasse.setTipo("Transferência");
                        result = contMov.addMovimentacao(caixaMovClasse);
                    } else {
                        //SE O CENTRO DE SAIDA NÃO FOR CAIXA, ADICIONO A MOVIMENTAÇÃO DE CONTA BANCÁRIA
                    }

                    if (lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getTipo().equals("Caixa")) {
                        //ADICIONO A MOVIMENTAÇÃO
                        CaixaMovimentacaoController contMov = new CaixaMovimentacaoController();
                        CaixaMovimentacaoClasse caixaMovClasse = new CaixaMovimentacaoClasse();
                        CaixaClasse caixa = caixaCont.getCaixa(lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getId());
                        caixaMovClasse.setId_caixa(caixa.getId());
                        NumberFormat nb = NumberFormat.getCurrencyInstance();
                        caixaMovClasse.setDescricao("Saldo de "+nb.format(saldo)+" vindo do centro de custo "+centro_retirar_saldo.getNome());
                        for (int i = 0; i < lista_formas_pagamento.size(); i++) {
                            if (lista_formas_pagamento.get(i).getNome().equals("Dinheiro")) {
                                caixaMovClasse.setId_forma_pagamento(lista_formas_pagamento.get(i).getId());
                            }
                        }
                        caixaMovClasse.setValor(saldo);
                        caixaMovClasse.setTipo("Transferência");
                        result = contMov.addMovimentacao(caixaMovClasse);
                    } else {
                        //SE O CENTRO DE DESTINO NÃO FOR CAIXA, ADICIONO A MOVIMENTAÇÃO DE CONTA BANCÁRIA
                    }
                    
                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                        transferiu = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao transferir saldo!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            salvar();
        } else if (e.getSource() == btnVoltar) {
            dispose();
        }
    }
    
}
