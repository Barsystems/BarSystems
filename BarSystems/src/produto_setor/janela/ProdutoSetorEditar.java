/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.janela;

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
import produto_setor.classe.ProdutoSetorClasse;
import produto_setor.controller.ProdutoSetorController;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblSetor;
    private JTextField txtSetor;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private ProdutoSetorClasse setor;
    
    public boolean editou = false;
    
    public ProdutoSetorEditar(ProdutoSetorClasse setor) {
        this.setor = setor;
        
        setTitle("Editar cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 280);
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
        painel2.setBounds(0, 60, 400, 130);
        
        lblSetor = new JLabel("Setor");
        lblSetor.setFont(fonteGeral);
        lblSetor.setBounds(30, 30, 40, 30);
        
        txtSetor = new JTextField(setor.getSetor());
        txtSetor.setFont(fonteGeral);
        txtSetor.setBounds(110, 30, 200, 30);
        
        painel2.add(lblSetor);
        painel2.add(txtSetor);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 180, 400, 100);
        
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
        if (txtSetor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo setor não pode ficar em branco!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtSetor.grabFocus();
        } else {
            ProdutoSetorController cont = new ProdutoSetorController();
            if (cont.verificaSetorRepetido(txtSetor.getText()) == true && setor.getSetor().equals(txtSetor.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O setor "+txtSetor.getText()+" já está cadastrado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtSetor.selectAll();
                txtSetor.grabFocus();
            } else {
                
                setor.setSetor(txtSetor.getText());
                
                int result = cont.updateSetor(setor);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Setor "+txtSetor.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar o setor "+txtSetor.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
