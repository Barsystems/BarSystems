/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.janela;

import cliente.classe.ClienteClasse;
import cliente.controller.ClienteController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import pais.classe.PaisClasse;
import pais.comboBox.PaisComboBoxCellRenderer;
import pais.comboBox.PaisComboBoxModel;
import pais.controller.PaisController;

/**
 *
 * @author Marcos
 */
public class ClienteCadastrar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblNascimento, lblCpf, lblRg, lblEmail, lblTelefone, lblCelular, 
            lblObservacao, lblCep, lblEndereco, lblBairro, lblCidade, lblEstado, lblPais;
    private JTextField txtNome, txtRg, txtEmail, txtEndereco,
            txtBairro, txtCidade;
    private JFormattedTextField txtNascimento, txtCpf, txtTelefone, txtCelular, txtCep, txtEstado;
    private JTextArea txtObservacao;
    private JScrollPane scrollTextArea;
    private JComboBox comboPais;
    private JButton btnSalvar, btnLimpar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<PaisClasse> lista_paises;
    
    public boolean cadastrou = false;
    
     public ClienteCadastrar() throws ParseException {
        setTitle("Novo cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 570);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 800, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("NOVO CADASTRO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 800, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 800, 410);
        
        lblNome = new JLabel("Nome");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 80, 30);
        
        txtNome = new JTextField();
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(110, 30, 250, 30);
        
        lblNascimento = new JLabel("Nascimento");
        lblNascimento.setFont(fonteGeral);
        lblNascimento.setBounds(390, 30, 80, 30);
        
        txtNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        txtNascimento.setFont(fonteGeral);
        txtNascimento.setBounds(480, 30, 80, 30);
        
        lblCpf = new JLabel("Cpf");
        lblCpf.setFont(fonteGeral);
        lblCpf.setBounds(580, 30, 30, 30);
        
        txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        txtCpf.setFont(fonteGeral);
        txtCpf.setBounds(620, 30, 110, 30);
        
        lblRg = new JLabel("Rg");
        lblRg.setFont(fonteGeral);
        lblRg.setBounds(30, 70, 20, 30);
        
        txtRg = new JTextField();
        txtRg.setFont(fonteGeral);
        txtRg.setBounds(110, 70, 250, 30);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(fonteGeral);
        lblEmail.setBounds(390, 70, 50, 30);
        
        txtEmail = new JTextField();
        txtEmail.setFont(fonteGeral);
        txtEmail.setBounds(480, 70, 250, 30);
        
        lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(fonteGeral);
        lblTelefone.setBounds(30, 110, 60, 30);
        
        txtTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
        txtTelefone.setFont(fonteGeral);
        txtTelefone.setBounds(110, 110, 250, 30);
        
        lblCelular = new JLabel("Celular");
        lblCelular.setFont(fonteGeral);
        lblCelular.setBounds(390, 110, 80, 30);
        
        txtCelular = new JFormattedTextField(new MaskFormatter("(##) ########*"));
        txtCelular.setFont(fonteGeral);
        txtCelular.setBounds(480, 110, 250, 30);
        
        lblObservacao = new JLabel("Observação");
        lblObservacao.setFont(fonteGeral);
        lblObservacao.setBounds(30, 150, 80, 30);
        
        txtObservacao = new JTextArea();
        txtObservacao.setFont(fonteGeral);
        txtObservacao.setLineWrap(true);
        txtObservacao.setWrapStyleWord(true);
        
        scrollTextArea = new JScrollPane(txtObservacao);
        scrollTextArea.setBounds(110, 150, 620, 90);
        
        lblCep = new JLabel("Cep");
        lblCep.setFont(fonteGeral);
        lblCep.setBounds(30, 270, 30, 30);
        
        txtCep = new JFormattedTextField(new MaskFormatter("########"));
        txtCep.setFont(fonteGeral);
        txtCep.setBounds(110, 270, 100, 30);
        
        lblEndereco = new JLabel("Endereço");
        lblEndereco.setFont(fonteGeral);
        lblEndereco.setBounds(30, 310, 80, 30);
        
        txtEndereco = new JTextField();
        txtEndereco.setFont(fonteGeral);
        txtEndereco.setBounds(110, 310, 250, 30);
        
        lblBairro = new JLabel("Bairro");
        lblBairro.setFont(fonteGeral);
        lblBairro.setBounds(390, 310, 70, 30);
        
        txtBairro = new JTextField();
        txtBairro.setFont(fonteGeral);
        txtBairro.setBounds(480, 310, 250, 30);
        
        lblCidade = new JLabel("Cidade");
        lblCidade.setFont(fonteGeral);
        lblCidade.setBounds(30, 350, 70, 30);
        
        txtCidade = new JTextField();
        txtCidade.setFont(fonteGeral);
        txtCidade.setBounds(110, 350, 250, 30);
        
        lblEstado = new JLabel("Uf");
        lblEstado.setFont(fonteGeral);
        lblEstado.setBounds(390, 350, 20, 30);
        
        txtEstado = new JFormattedTextField(new MaskFormatter("UU"));
        txtEstado.setFont(fonteGeral);
        txtEstado.setBounds(480, 350, 50, 30);
        
        lblPais = new JLabel("País");
        lblPais.setFont(fonteGeral);
        lblPais.setBounds(550, 350, 40, 30);
        
        comboPais = new JComboBox();
        comboPais.setFont(fonteGeral);
        comboPais.setBounds(590, 350, 140, 30);
        
        lista_paises = new PaisController().findPaises();
        comboPais.setModel(new PaisComboBoxModel(lista_paises));
        comboPais.setRenderer(new PaisComboBoxCellRenderer());
        
        for (int i = 0; i < lista_paises.size(); i++) {
            if (lista_paises.get(i).getPais().equals("Brasil")) {
                comboPais.setSelectedIndex(i);
            }
        }
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblNascimento);
        painel2.add(txtNascimento);
        painel2.add(lblCpf);
        painel2.add(txtCpf);
        painel2.add(lblRg);
        painel2.add(txtRg);
        painel2.add(lblEmail);
        painel2.add(txtEmail);
        painel2.add(lblTelefone);
        painel2.add(txtTelefone);
        painel2.add(lblCelular);
        painel2.add(txtCelular);
        painel2.add(lblObservacao);
        painel2.add(scrollTextArea);
        painel2.add(lblCep);
        painel2.add(txtCep);
        painel2.add(lblEndereco);
        painel2.add(txtEndereco);
        painel2.add(lblBairro);
        painel2.add(txtBairro);
        painel2.add(lblCidade);
        painel2.add(txtCidade);
        painel2.add(lblEstado);
        painel2.add(txtEstado);
        painel2.add(lblPais);
        painel2.add(comboPais);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 470, 800, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(240, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(fonteGeral);
        btnLimpar.setBounds(350, 20, 100, 30);
        btnLimpar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(460, 20, 100, 30);
        btnVoltar.addActionListener(this);
        
        painel3.add(btnSalvar);
        painel3.add(btnLimpar);
        painel3.add(btnVoltar);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }

    public void salvar() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else {
            ClienteController cont = new ClienteController();
            if (cont.verificaClienteRepetido(txtCpf.getText()) == true) {
                JOptionPane.showMessageDialog(null, "O cliente cujo cpf é "+txtCpf.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                ClienteClasse cliente = new ClienteClasse();
                cliente.setNome(txtNome.getText());
                cliente.setData_nascimento(txtNascimento.getText());
                cliente.setCpf(txtCpf.getText());
                cliente.setRg(txtRg.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefone(txtTelefone.getText());
                cliente.setCelular(txtCelular.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setCep(txtCep.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setBairro(txtBairro.getText());
                cliente.setCidade(txtCidade.getText());
                cliente.setEstado(txtEstado.getText());
                cliente.setId_pais(lista_paises.get(comboPais.getSelectedIndex()).getId());
                cliente.setBloqueado(false);
                cliente.setObservacao(txtObservacao.getText());
                
                int result = cont.addCliente(cliente);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Cliente "+txtNome.getText()+" cadastrado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar o cliente "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void limpar () {
        txtNome.setText("");
        txtNascimento.setValue(null);
        txtCpf.setValue(null);
        txtRg.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        txtObservacao.setText("");
        txtCep.setValue(null);
        txtEndereco.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setValue(null);
        for (int i = 0; i < lista_paises.size(); i++) {
            if (lista_paises.get(i).getPais().equals("Brasil")) {
                comboPais.setSelectedIndex(i);
            }
        }
        comboPais.repaint();
        
        txtNome.grabFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnSalvar) {
            salvar();
        } else if (source == btnLimpar) {
            limpar();
        } else if (source == btnVoltar) {
            dispose();
        }
    }
    
}
