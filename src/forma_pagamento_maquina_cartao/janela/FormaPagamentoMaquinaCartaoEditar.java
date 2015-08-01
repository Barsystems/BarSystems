/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.janela;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import forma_pagamento_maquina_cartao.controller.FormaPagamentoMaquinaCartaoController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome;
    private JTextField txtNome;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    FormaPagamentoMaquinaCartaoClasse classe;
    
    public boolean editou = false;
    
    public FormaPagamentoMaquinaCartaoEditar(FormaPagamentoMaquinaCartaoClasse classe) {
        
        this.classe = classe;
        
        setTitle("Editar cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 290);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 400, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("EDITAR CADASTRO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 400, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 400, 130);
        
        lblNome = new JLabel("Máquina");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 70, 30);
        
        txtNome = new JTextField();
        txtNome.setText(this.classe.getNome());
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(110, 30, 200, 30);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 190, 400, 100);
        
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
            JOptionPane.showMessageDialog(null, "O campo máquina não pode ficar em branco!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else {
            FormaPagamentoMaquinaCartaoController cont = new FormaPagamentoMaquinaCartaoController();
            if (cont.verificaMaquinaCartaoRepetida(txtNome.getText()) == true && this.classe.getNome().equals(txtNome.getText()) == false) {
                JOptionPane.showMessageDialog(null, "A máquina de cartão "+txtNome.getText()+" já está cadastrada!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                classe.setNome(txtNome.getText());
                
                int result = cont.updateMaquinaCartao(classe);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Máquina de cartão "+txtNome.getText()+" editada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar a máquina de cartão "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
