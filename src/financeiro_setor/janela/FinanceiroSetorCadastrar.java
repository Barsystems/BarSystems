/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.janela;

import financeiro_setor.classe.FinanceiroSetorClasse;
import financeiro_setor.controller.FinanceiroSetorController;
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
public class FinanceiroSetorCadastrar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblTipo;
    private JTextField txtNome;
    private JRadioButton radioReceita, radioDespesa;
    private ButtonGroup grupoRadio;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    public boolean cadastrou = false;
    
    public FinanceiroSetorCadastrar() {
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
        
        radioReceita = new JRadioButton("Receita");
        radioReceita.setFont(fonteGeral);
        radioReceita.setBackground(Color.WHITE);
        radioReceita.setBounds(110, 70, 80, 30);
        radioReceita.setSelected(true);
        
        radioDespesa = new JRadioButton("Despesa");
        radioDespesa.setBackground(Color.WHITE);
        radioDespesa.setFont(fonteGeral);
        radioDespesa.setBounds(200, 70, 130, 30);
        
        grupoRadio = new ButtonGroup();
        grupoRadio.add(radioReceita);
        grupoRadio.add(radioDespesa);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblTipo);
        painel2.add(radioReceita);
        painel2.add(radioDespesa);
        
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
            FinanceiroSetorController cont = new FinanceiroSetorController();
            if (cont.verificaSetorRepetido(txtNome.getText()) == true) {
                JOptionPane.showMessageDialog(null, "O setor financeiro "+txtNome.getText()+" já está cadastrado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                String tipo;
                if (radioReceita.isSelected()) {
                    tipo = "Receita";
                } else {
                    tipo = "Despesa";
                }
                
                FinanceiroSetorClasse setor = new FinanceiroSetorClasse();
                setor.setNome(txtNome.getText());
                setor.setTipo(tipo);
                
                int result = cont.addSetor(setor);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Setor financeiro "+txtNome.getText()+" cadastrado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar o setor financeiro "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
