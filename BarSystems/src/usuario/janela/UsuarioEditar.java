/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.janela;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import usuario.classe.UsuarioClasse;
import usuario.comboBox.UsuarioComboBoxModel;
import usuario.controller.UsuarioController;

/**
 *
 * @author Marcos
 */
public class UsuarioEditar extends JDialog implements MouseListener, ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblUsuario, lblSenha, lblRepetirSenha, lblTipo, lblTrocarSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha, txtRepetirSenha;
    private JComboBox comboTipo;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    public boolean editou = false; //esta variável vai dizer para o form pai se foi editado algum usuário. Se sim, atualiza a tabela na outra tela
    
    private UsuarioClasse user;
    
    public UsuarioEditar(UsuarioClasse user) {
        
        this.user = user;
        
        setTitle("Alterar cadastro");
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
        lblTitulo.setText("NOVO CADASTRO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 500, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 500, 210);
        
        lblUsuario = new JLabel("Nome do usuário");
        lblUsuario.setFont(fonteGeral);
        lblUsuario.setBounds(30, 30, 110, 30);
        
        txtUsuario = new JTextField(this.user.getNome());
        txtUsuario.setFont(fonteGeral);
        txtUsuario.setBounds(150, 30, 230, 30);
        
        lblTipo = new JLabel("Tipo do usuário");
        lblTipo.setFont(fonteGeral);
        lblTipo.setBounds(30, 70, 110, 30);
        
        comboTipo = new JComboBox(new UsuarioComboBoxModel());
        comboTipo.setFont(fonteGeral);
        comboTipo.setBounds(150, 70, 230, 30);
        comboTipo.setSelectedItem(user.getTipo());
        
        lblTrocarSenha = new JLabel("Trocar senha");
        lblTrocarSenha.setFont(fonteGeral);
        lblTrocarSenha.setBounds(150, 110, 230, 30);
        lblTrocarSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTrocarSenha.setForeground(Color.blue);
        lblTrocarSenha.addMouseListener(this);  
        
        lblSenha = new JLabel("Senha do usuário");
        lblSenha.setFont(fonteGeral);
        lblSenha.setBounds(30, 110, 110, 30);
        
        txtSenha = new JPasswordField();
        txtSenha.setFont(fonteGeral);
        txtSenha.setBounds(150, 110, 230, 30);
        
        lblRepetirSenha = new JLabel("Repita a senha");
        lblRepetirSenha.setFont(fonteGeral);
        lblRepetirSenha.setBounds(30, 150, 110, 30);

        txtRepetirSenha = new JPasswordField();
        txtRepetirSenha.setFont(fonteGeral);
        txtRepetirSenha.setBounds(150, 150, 230, 30);
        
        painel2.add(lblUsuario);
        painel2.add(txtUsuario);
        painel2.add(lblTipo);
        painel2.add(comboTipo);
        painel2.add(lblTrocarSenha);
        
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
        
        //SE O USUÁRIO FOR O ADMINISTRADOR, SÓ PODEMOS ALTERAR A SENHA
        if (user.getNome().equals("Administrador")) {
            txtUsuario.setEnabled(false);
            comboTipo.setEnabled(false);
        }
    }
    
    public void alterarSenha() {
        if (lblTrocarSenha.getText().equals("Trocar senha")) {
            
            txtSenha.setText("");
            txtRepetirSenha.setText("");
            
            painel2.add(lblSenha);
            painel2.add(txtSenha);
            painel2.add(lblRepetirSenha);
            painel2.add(txtRepetirSenha);
            
            lblTrocarSenha.setText("Não trocar senha");
            lblTrocarSenha.setBounds(150, 180, 230, 30);
            
            painel2.repaint();
            
            txtSenha.grabFocus();
            
        } else {
            painel2.remove(lblSenha);
            painel2.remove(txtSenha);
            painel2.remove(lblRepetirSenha);
            painel2.remove(txtRepetirSenha);
            
            lblTrocarSenha.setText("Trocar senha");
            lblTrocarSenha.setBounds(150, 110, 230, 30);
            
            painel2.repaint();
            
            txtUsuario.grabFocus();
        }
    }
    
    public void salvar() {
        boolean flag = false;
        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome de usuário não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtUsuario.grabFocus();
        } else if (txtUsuario.getText().length() > 16) {
            JOptionPane.showMessageDialog(null, "O nome de usuário não pode ter mais que 16 caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            boolean valido = txtSenha.isValid();
            if (valido) {
                if (txtSenha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O senha não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    txtSenha.grabFocus();
                } else if (txtSenha.getText().length() > 16) {
                    JOptionPane.showMessageDialog(null, "A senha não pode ter mais que 16 caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else if (txtRepetirSenha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Confirme a senha!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    txtRepetirSenha.grabFocus();
                } else if (txtSenha.getText().equals(txtRepetirSenha.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "As senhas não coincidem!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    if (txtSenha.getText().isEmpty()) {
                        txtSenha.grabFocus();
                    } else {
                        txtRepetirSenha.selectAll();
                        txtRepetirSenha.grabFocus();
                    }
                } else {
                    flag = true;
                }
            } else {
                flag = true;
            }
            
            //se tudo ocorreu bem, então flag == true. Assim, podemos salvar
            if (flag ==true) {
                

                UsuarioController cont = new UsuarioController();
                if (cont.verificaUsuarioRepetido(txtUsuario.getText()) == true && txtUsuario.getText().equals(user.getNome()) == false) {
                    JOptionPane.showMessageDialog(null, "O usuário "+txtUsuario.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    txtUsuario.selectAll();
                    txtUsuario.grabFocus();
                } else {
                    
                    user.setNome(txtUsuario.getText());
                    if (valido) {
                        user.setSenha(txtSenha.getText());
                    }
                    user.setTipo(comboTipo.getSelectedItem().toString());
                    
                    int result = cont.updateUsuario(user);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Usuário "+txtUsuario.getText()+" editado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        editou = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao tentar editar o usuário "+txtUsuario.getText()+"!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == lblTrocarSenha) {
            alterarSenha();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (source == lblTrocarSenha) {
            lblTrocarSenha.setForeground(Color.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == lblTrocarSenha) {
            lblTrocarSenha.setForeground(Color.blue);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnSalvar) {
            salvar();
        } else {
            dispose();
        }
    }
    
}
