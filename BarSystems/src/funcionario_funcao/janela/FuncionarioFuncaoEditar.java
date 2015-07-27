/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.janela;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import funcionario_funcao.controller.FuncionarioFuncaoController;
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
public class FuncionarioFuncaoEditar extends JDialog implements ActionListener{
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblFuncao;
    private JTextField txtFuncao;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private FuncionarioFuncaoClasse func;
    
    public boolean editou = false;
    
    public FuncionarioFuncaoEditar(FuncionarioFuncaoClasse func) {
        
        this.func = func;
        
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
        
        lblFuncao = new JLabel("Função");
        lblFuncao.setFont(fonteGeral);
        lblFuncao.setBounds(30, 30, 40, 30);
        
        txtFuncao = new JTextField(func.getFuncao());
        txtFuncao.setFont(fonteGeral);
        txtFuncao.setBounds(110, 30, 200, 30);
        
        painel2.add(lblFuncao);
        painel2.add(txtFuncao);
        
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
        if (txtFuncao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo função não pode ficar em branco!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtFuncao.grabFocus();
        } else {
            FuncionarioFuncaoController cont = new FuncionarioFuncaoController();
            if (cont.verificaFuncaoRepetida(txtFuncao.getText()) == true && func.getFuncao().equals(txtFuncao.getText()) == false) {
                JOptionPane.showMessageDialog(null, "A função "+txtFuncao.getText()+" já está cadastrada!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtFuncao.selectAll();
                txtFuncao.grabFocus();
            } else {
                
                func.setFuncao(txtFuncao.getText());
                
                int result = cont.updateFuncao(func);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Função "+txtFuncao.getText()+" editada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar a função "+txtFuncao.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
