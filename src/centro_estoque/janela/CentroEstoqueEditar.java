/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.janela;

import centro_estoque.classe.CentroEstoqueClasse;
import centro_estoque.controller.CentroEstoqueController;
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
public class CentroEstoqueEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblAtivo;
    private JTextField txtNome;
    private JRadioButton radioAtivo, radioNaoAtivo;
    private ButtonGroup grupoRadio;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    CentroEstoqueClasse classe;
    
    public boolean editou = false;
    
    public CentroEstoqueEditar(CentroEstoqueClasse classe) {
        
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
        
        lblNome = new JLabel("Nome");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 40, 30);
        
        txtNome = new JTextField();
        txtNome.setFont(fonteGeral);
        txtNome.setText(this.classe.getNome());
        txtNome.setBounds(110, 30, 200, 30);
        
        lblAtivo = new JLabel("Ativo");
        lblAtivo.setFont(fonteGeral);
        lblAtivo.setBounds(30, 70, 40, 30);
        
        radioAtivo = new JRadioButton("Sim");
        radioAtivo.setFont(fonteGeral);
        radioAtivo.setBackground(Color.WHITE);
        radioAtivo.setBounds(110, 70, 50, 30);
        radioAtivo.setSelected(true);
        
        radioNaoAtivo = new JRadioButton("Não");
        radioNaoAtivo.setFont(fonteGeral);
        radioNaoAtivo.setBackground(Color.WHITE);
        radioNaoAtivo.setBounds(180, 70, 50, 30);
        
        grupoRadio = new ButtonGroup();
        grupoRadio.add(radioAtivo);
        grupoRadio.add(radioNaoAtivo);
        
        if (this.classe.isAtivo() == true) {
            radioAtivo.setSelected(true);
        } else {
            radioNaoAtivo.setSelected(true);
        }
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblAtivo);
        painel2.add(radioAtivo);
        painel2.add(radioNaoAtivo);
        
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
            JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else {
            CentroEstoqueController cont = new CentroEstoqueController();
            if (cont.verificaCentroEstoqueRepetido(txtNome.getText()) == true && classe.getNome().equals(txtNome.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O centro de estoque "+txtNome.getText()+" já está cadastrado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                boolean ativo;
                if (radioAtivo.isSelected()) {
                    ativo = true;
                } else {
                    ativo = false;
                }
                
                classe.setNome(txtNome.getText());
                classe.setAtivo(ativo);
                
                int result = cont.updateCentroEstoque(classe);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Centro de estoque "+txtNome.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar o centro de estoque "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
