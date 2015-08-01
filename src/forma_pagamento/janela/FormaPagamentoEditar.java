/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.janela;

import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.controller.FormaPagamentoController;
import forma_pagamento_tipo.classe.FormaPagamentoTipoClasse;
import forma_pagamento_tipo.comboBox.FormaPagamentoTipoComboBox;
import forma_pagamento_tipo.comboBox.FormaPagamentoTipoComboBoxCellRenderer;
import forma_pagamento_tipo.controller.FormaPagamentoTipoController;
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

/**
 *
 * @author Marcos
 */
public class FormaPagamentoEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblTipo, lblDiasRecebimento;
    private JTextField txtNome, txtDiasRecebimento;
    private JComboBox comboTipo;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private FormaPagamentoClasse classe;
    
    private List<FormaPagamentoTipoClasse> lista_tipo_forma;
    
    public boolean editou = false;
    
    public FormaPagamentoEditar(FormaPagamentoClasse classe) {
        
        this.classe = classe;
        
        setTitle("Editar cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 500, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("EDITAR CADASTRO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 500, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 500, 190);
        
        lblNome = new JLabel("Forma de pagamento");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 180, 30);
        
        txtNome = new JTextField();
        txtNome.setText(this.classe.getNome());
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(210, 30, 200, 30);
        
        lblTipo = new JLabel("Tipo da forma de pagamento");
        lblTipo.setFont(fonteGeral);
        lblTipo.setBounds(30, 70, 180, 30);
        
        comboTipo = new JComboBox();
        comboTipo.setFont(fonteGeral);
        comboTipo.setBounds(210, 70, 200, 30);
        
        lista_tipo_forma = new FormaPagamentoTipoController().findTipo("");
        comboTipo.setModel(new FormaPagamentoTipoComboBox(lista_tipo_forma));
        comboTipo.setRenderer(new FormaPagamentoTipoComboBoxCellRenderer());
        
        for (int i = 0; i < lista_tipo_forma.size(); i++) {
            if (lista_tipo_forma.get(i).getId().equals(this.classe.getId_tipo())) {
                comboTipo.setSelectedIndex(i);
            }
        }
        
        lblDiasRecebimento = new JLabel("Dias para recebimento");
        lblDiasRecebimento.setFont(fonteGeral);
        lblDiasRecebimento.setBounds(30, 110, 180, 30);
        
        txtDiasRecebimento = new JTextField();
        txtDiasRecebimento.setText(String.valueOf(this.classe.getDias_recebimento()));
        txtDiasRecebimento.setFont(fonteGeral);
        txtDiasRecebimento.setBounds(210, 110, 200, 30);
        
        if (this.classe.isPadrao() == true) {
            txtNome.setEnabled(false);
            comboTipo.setEnabled(false);
        }
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblTipo);
        painel2.add(comboTipo);
        painel2.add(lblDiasRecebimento);
        painel2.add(txtDiasRecebimento);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 250, 500, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(150, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(260, 20, 100, 30);
        btnVoltar.addActionListener(this);
        
        painel3.add(btnSalvar);
        painel3.add(btnVoltar);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }

    public void salvar() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo forma de pagamento não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else if (txtDiasRecebimento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo dias para recebimento não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtDiasRecebimento.grabFocus();
        } else {
            FormaPagamentoController cont = new FormaPagamentoController();
            if (cont.verificaFormaPagamentoRepetida(txtNome.getText()) == true && this.classe.getNome().equals(txtNome.getText()) == false) {
                JOptionPane.showMessageDialog(null, "A forma de pagamento "+txtNome.getText()+" já está cadastrada!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {                
                this.classe.setNome(txtNome.getText());
                this.classe.setId_tipo(lista_tipo_forma.get(comboTipo.getSelectedIndex()).getId());
                this.classe.setDias_recebimento(Integer.valueOf(txtDiasRecebimento.getText()));
                
                int result = cont.updateFormaPagamento(this.classe);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Forma de pagamento "+txtNome.getText()+" editada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar a forma de pagamento "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void limpar () {
        txtNome.setText("");
        comboTipo.setSelectedIndex(0);
        txtDiasRecebimento.setText("");
        
        txtNome.grabFocus();
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
