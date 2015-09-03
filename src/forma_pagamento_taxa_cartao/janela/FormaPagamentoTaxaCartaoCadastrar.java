/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.janela;

import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.comboBox.FormaPagamentoComboBox;
import forma_pagamento.comboBox.FormaPagamentoComboBoxCellRenderer;
import forma_pagamento.controller.FormaPagamentoController;
import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import forma_pagamento_maquina_cartao.comboBox.FormaPagamentoMaquinaCartaoComboBox;
import forma_pagamento_maquina_cartao.comboBox.FormaPagamentoMaquinaCartaoComboBoxCellRenderer;
import forma_pagamento_maquina_cartao.controller.FormaPagamentoMaquinaCartaoController;
import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import forma_pagamento_taxa_cartao.controller.FormaPagamentoTaxaCartaoController;
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
public class FormaPagamentoTaxaCartaoCadastrar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblFormaPagamento, lblMaquinaCartao, lblTaxa;
    private JTextField txtTaxa;
    private JComboBox comboFormaPagamento, comboMaquinaCartao;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<FormaPagamentoClasse> lista_formas_pagto;
    private List<FormaPagamentoMaquinaCartaoClasse> lista_maquinas;
    
    public boolean cadastrou = false;
    
    public FormaPagamentoTaxaCartaoCadastrar() {
        setTitle("Novo cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 370);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 500, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("CONFIGURAR NOVA TAXA");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 500, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 500, 210);
        
        lblFormaPagamento = new JLabel("Forma de pagamento");
        lblFormaPagamento.setFont(fonteGeral);
        lblFormaPagamento.setBounds(30, 30, 150, 30);
        
        comboFormaPagamento = new JComboBox();
        comboFormaPagamento.setFont(fonteGeral);
        comboFormaPagamento.setBounds(190, 30, 230, 30);
        
        lista_formas_pagto = new FormaPagamentoController().findFormaPagamentoTipoEspecifico("Cartão%");
        comboFormaPagamento.setModel(new FormaPagamentoComboBox(lista_formas_pagto));
        comboFormaPagamento.setRenderer(new FormaPagamentoComboBoxCellRenderer());
        
        lblMaquinaCartao = new JLabel("Máquina de cartão");
        lblMaquinaCartao.setFont(fonteGeral);
        lblMaquinaCartao.setBounds(30, 70, 150, 30);
        
        comboMaquinaCartao = new JComboBox();
        comboMaquinaCartao.setFont(fonteGeral);
        comboMaquinaCartao.setBounds(190, 70, 230, 30);
        
        lista_maquinas = new FormaPagamentoMaquinaCartaoController().findMaquinaCartao("");
        comboMaquinaCartao.setModel(new FormaPagamentoMaquinaCartaoComboBox(lista_maquinas));
        comboMaquinaCartao.setRenderer(new FormaPagamentoMaquinaCartaoComboBoxCellRenderer());
        
        lblTaxa = new JLabel("Taxa em porcentagem");
        lblTaxa.setFont(fonteGeral);
        lblTaxa.setBounds(30, 110, 150, 30);
        
        txtTaxa = new JTextField();
        txtTaxa.setFont(fonteGeral);
        txtTaxa.setBounds(190, 110, 230, 30);
        
        painel2.add(lblFormaPagamento);
        painel2.add(comboFormaPagamento);
        painel2.add(lblMaquinaCartao);
        painel2.add(comboMaquinaCartao);
        painel2.add(lblTaxa);
        painel2.add(txtTaxa);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 270, 500, 100);
        
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
        if (txtTaxa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "A taxa não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtTaxa.grabFocus();
        } else {
            
            String forma = lista_formas_pagto.get(comboFormaPagamento.getSelectedIndex()).getNome();
            String maquina = lista_maquinas.get(comboMaquinaCartao.getSelectedIndex()).getNome();
            
            Long id_forma = lista_formas_pagto.get(comboFormaPagamento.getSelectedIndex()).getId();
            Long id_maquina = lista_maquinas.get(comboMaquinaCartao.getSelectedIndex()).getId();
            float taxa = new TrocaVirgulaPorPonto().trocaVirgulaPorPonto(txtTaxa.getText());
            
            FormaPagamentoTaxaCartaoController cont = new FormaPagamentoTaxaCartaoController();
            if (cont.verificaTaxaCartaoRepetida(id_forma, id_maquina) == true) {
                JOptionPane.showMessageDialog(null, "A taxa do cartão "+forma+" para a máquina "+maquina+" já está cadastrada!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                comboFormaPagamento.grabFocus();
            } else {
                
                FormaPagamentoTaxaCartaoClasse classe = new FormaPagamentoTaxaCartaoClasse();
                classe.setId_forma_pagamento(id_forma);
                classe.setId_maquina_cartao(id_maquina);
                classe.setTaxa(taxa);
                
                int result = cont.addTaxaCartao(classe);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Taxa do cartão "+forma+" para a máquina "+maquina+" cadastrada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar a taxa do cartão "+forma+" para a máquina "+maquina+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
