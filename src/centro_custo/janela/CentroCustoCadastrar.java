/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.controller.CentroCustoController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Marcos
 */
public class CentroCustoCadastrar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblTipo;
    private JTextField txtNome;
    private JRadioButton radioCaixa, radioContaBancaria;
    private ButtonGroup grupoRadio;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    public boolean cadastrou = false;
    
    public CentroCustoCadastrar() {
        setTitle("Novo cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 330);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 400, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("NOVO CADASTRO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 400, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 400, 170);
        
        lblNome = new JLabel("Nome");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 40, 30);
        
        txtNome = new JTextField();
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(110, 30, 200, 30);
        
        lblTipo = new JLabel("Tipo");
        lblTipo.setFont(fonteGeral);
        lblTipo.setBounds(30, 70, 40, 30);
        
        radioCaixa = new JRadioButton("Caixa");
        radioCaixa.setFont(fonteGeral);
        radioCaixa.setBackground(Color.WHITE);
        radioCaixa.setBounds(110, 70, 80, 30);
        radioCaixa.setSelected(true);
        
        radioContaBancaria = new JRadioButton("Conta bancária");
        radioContaBancaria.setBackground(Color.WHITE);
        radioContaBancaria.setFont(fonteGeral);
        radioContaBancaria.setBounds(200, 70, 130, 30);
        
        grupoRadio = new ButtonGroup();
        grupoRadio.add(radioCaixa);
        grupoRadio.add(radioContaBancaria);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblTipo);
        painel2.add(radioCaixa);
        painel2.add(radioContaBancaria);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 230, 400, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(100, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(210, 20, 100, 30);
        btnVoltar.addActionListener(this);
        
        painel3.add(btnSalvar);
        painel3.add(btnVoltar);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }
    
    public void salvar() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else {
            CentroCustoController cont = new CentroCustoController();
            if (cont.verificaCentroCustoRepetido(txtNome.getText()) == true) {
                JOptionPane.showMessageDialog(null, "O centro de custo "+txtNome.getText()+" já está cadastrado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                String tipo;
                if (radioCaixa.isSelected()) {
                    tipo = "Caixa";
                } else {
                    tipo = "Conta bancária";
                }
                
                CentroCustoClasse centro = new CentroCustoClasse();
                centro.setNome(txtNome.getText());
                centro.setTipo(tipo);
                
                int result = cont.addCentroCusto(centro);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Centro de custo "+txtNome.getText()+" cadastrado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar o centro de custo "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnSalvar) {
            salvar();
        } else if (source == btnVoltar) {
            dispose();
        }
    }
    
}
