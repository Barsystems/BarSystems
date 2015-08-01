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
public class CentroCustoEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblTipo, lblTipo2;
    private JTextField txtNome;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private CentroCustoClasse centro;
    
    public boolean editou = false;
    
    public CentroCustoEditar(CentroCustoClasse centro) {
        
        this.centro = centro;
        
        setTitle("Editar cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 330);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fonteNegrito = new Font("Tahoma", Font.BOLD, 12);
        
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
        painel2.setBounds(0, 60, 400, 170);
        
        lblNome = new JLabel("Nome");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 40, 30);
        
        txtNome = new JTextField();
        txtNome.setText(this.centro.getNome());
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(110, 30, 200, 30);
        
        lblTipo = new JLabel("Tipo");
        lblTipo.setFont(fonteGeral);
        lblTipo.setBounds(30, 70, 40, 30);
        
        lblTipo2 = new JLabel();
        lblTipo2.setText(this.centro.getTipo());
        lblTipo2.setFont(fonteNegrito);
        lblTipo2.setForeground(Color.red);
        lblTipo2.setBounds(110, 70, 130, 30);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblTipo);
        painel2.add(lblTipo2);
        
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
            if (cont.verificaCentroCustoRepetido(txtNome.getText()) == true && this.centro.getNome().equals(txtNome.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O centro de custo "+txtNome.getText()+" já está cadastrado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                centro.setNome(txtNome.getText());
                
                int result = cont.updateCentroCusto(centro);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Centro de custo "+txtNome.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar o centro de custo "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
