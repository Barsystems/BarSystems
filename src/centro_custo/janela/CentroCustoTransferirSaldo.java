/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.comboBox.CentroCustoComboBox;
import centro_custo.comboBox.CentroCustoComboBoxCellRenderer;
import centro_custo.controller.CentroCustoController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        lblSaldoDisponivel2 = new JLabel();
        lblSaldoDisponivel2.setText(String.valueOf(centro_retirar_saldo.getSaldo()));
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
            if (lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getTipo().equals("Caixa")) {
                //AQUI VERIFICO SE O CAIXA ESTÁ ABERTO, SE NÃO ESTIVER, NÃO POSSO CONTINUAR
            } else {
                //SE FOR CONTA BANCÁRIA, CONTINUO COM O MÉTODO
            }
            /*
            TrocaVirgulaPorPonto troca = new TrocaVirgulaPorPonto();
            float saldo = troca.trocaVirgulaPorPonto(txtSaldoRetirar.getText());
            if (saldo > centro_retirar_saldo.getSaldo()) {
                JOptionPane.showMessageDialog(null, "O saldo a retirar não pode ser maior que o saldo disponível!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtSaldoRetirar.selectAll();
                txtSaldoRetirar.grabFocus();
            } else {
                int result = 0;
                CentroCustoController cont = new CentroCustoController();
                cont.diminuiSaldo(centro_retirar_saldo.getId(), saldo);JOptionPane.showMessageDialog(null, centro_retirar_saldo.getId()+" "+saldo+" "+lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getId()+" "+saldo);
                result = cont.aumentaSaldo(lista_centros.get(comboCentroCustoEntrar.getSelectedIndex()).getId(), saldo);
                
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    transferiu = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível transferir o saldo!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }
            }*/
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
