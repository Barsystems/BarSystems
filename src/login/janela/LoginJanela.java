/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.janela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import menu_principal.janela.MenuPrincipalJanela;
import usuario.classe.UsuarioClasse;
import usuario.controller.UsuarioController;

/**
 *
 * @author Marcos
 */
public class LoginJanela extends JFrame implements ActionListener, KeyListener, WindowListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblSenha;
    private JTextField txtNome;
    private JPasswordField pTxtSenha;
    private JButton btnEntrar, btnSair;
    
    private Font fonteTitulo, fonteGeral;
    
    public LoginJanela() {
        super("Entrar no sistema");
        setSize(500, 300);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 500, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("MÃOS À OBRA - PROJETOS E SOLUÇÕES");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 500, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 500, 140);
        
        lblNome = new JLabel("Nome de usuário");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 120, 30);
        
        txtNome = new JTextField();
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(160, 30, 210, 30);
        txtNome.addKeyListener(this);
        
        lblSenha = new JLabel("Senha do usuário");
        lblSenha.setFont(fonteGeral);
        lblSenha.setBounds(30, 70, 120, 30);
        
        pTxtSenha = new JPasswordField();
        pTxtSenha.setFont(fonteGeral);
        pTxtSenha.setBounds(160, 70, 210, 30);
        pTxtSenha.addKeyListener(this);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblSenha);
        painel2.add(pTxtSenha);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 200, 500, 100);
        
        btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(fonteGeral);
        btnEntrar.setBounds(160, 20, 100, 30);
        btnEntrar.addActionListener(this);
        
        btnSair = new JButton("Sair");
        btnSair.setFont(fonteGeral);
        btnSair.setBounds(270, 20, 100, 30);
        btnSair.addActionListener(this);
        
        painel3.add(btnEntrar);
        painel3.add(btnSair);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }

    public void entrar() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o nome de usuário!!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else if (pTxtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a senha!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            pTxtSenha.grabFocus();
        } else {
            UsuarioController controller = new UsuarioController();
            boolean result = controller.validaUsuario(txtNome.getText(), pTxtSenha.getText());
            if (result == false) {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                pTxtSenha.grabFocus();
                pTxtSenha.selectAll();
            } else {
                UsuarioClasse user = controller.getUsuario(txtNome.getText());
                new MenuPrincipalJanela(user).setVisible(true);
                dispose();
            }
        }
    }
    
    public void sair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEntrar) {
            entrar();
        } else if (e.getSource() == btnSair) {
            sair();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtNome) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                pTxtSenha.grabFocus();
                pTxtSenha.selectAll();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if (txtNome.getText().isEmpty()) {
                    sair();
                } else {
                    txtNome.setText("");
                }
            }
        } else if (e.getSource() == pTxtSenha) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                entrar();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if (pTxtSenha.getText().isEmpty()) {
                    txtNome.grabFocus();
                } else {
                    pTxtSenha.setText("");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        sair();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
}
