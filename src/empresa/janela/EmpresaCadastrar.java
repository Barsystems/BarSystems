/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.janela;

import empresa.classe.EmpresaClasse;
import empresa.controller.EmpresaController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
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
public class EmpresaCadastrar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblRazaoSocial, lblNomeFantasia, lblCnpj, lblEmail, lblTelefone, lblInscricaoEstadual, 
            lblObservacao, lblCep, lblEndereco, lblBairro, lblCidade, lblEstado, lblPais;
    private JTextField txtRazaoSocial, txtNomeFantasia, txtEmail, txtInscricaoEstadual, txtEndereco,
            txtBairro, txtCidade;
    private JFormattedTextField  txtCnpj, txtTelefone, txtCep, txtEstado;
    private JTextArea txtObservacao;
    private JScrollPane scrollTextArea;
    private JComboBox comboPais;
    private JButton btnSalvar, btnLimpar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<PaisClasse> lista_paises;
    
    public boolean cadastrou = false;
    
    public EmpresaCadastrar() throws ParseException {
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
        
        lblRazaoSocial = new JLabel("Razão social");
        lblRazaoSocial.setFont(fonteGeral);
        lblRazaoSocial.setBounds(30, 30, 80, 30);
        
        txtRazaoSocial = new JTextField();
        txtRazaoSocial.setFont(fonteGeral);
        txtRazaoSocial.setBounds(110, 30, 250, 30);
        
        lblNomeFantasia = new JLabel("Nome fantasia");
        lblNomeFantasia.setFont(fonteGeral);
        lblNomeFantasia.setBounds(390, 30, 80, 30);
        
        txtNomeFantasia = new JTextField();
        txtNomeFantasia.setFont(fonteGeral);
        txtNomeFantasia.setBounds(480, 30, 250, 30);
        
        lblCnpj = new JLabel("Cnpj");
        lblCnpj.setFont(fonteGeral);
        lblCnpj.setBounds(30, 70, 30, 30);
        
        txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        txtCnpj.setFont(fonteGeral);
        txtCnpj.setBounds(110, 70, 250, 30);
        
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
        
        lblInscricaoEstadual = new JLabel("Insc. Estadual");
        lblInscricaoEstadual.setFont(fonteGeral);
        lblInscricaoEstadual.setBounds(390, 110, 80, 30);
        
        txtInscricaoEstadual = new JTextField();
        txtInscricaoEstadual.setFont(fonteGeral);
        txtInscricaoEstadual.setBounds(480, 110, 250, 30);
        
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
        
        painel2.add(lblRazaoSocial);
        painel2.add(txtRazaoSocial);
        painel2.add(lblNomeFantasia);
        painel2.add(txtNomeFantasia);
        painel2.add(lblCnpj);
        painel2.add(txtCnpj);
        painel2.add(lblEmail);
        painel2.add(txtEmail);
        painel2.add(lblTelefone);
        painel2.add(txtTelefone);
        painel2.add(lblInscricaoEstadual);
        painel2.add(txtInscricaoEstadual);
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
        if (txtRazaoSocial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo razão social não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtRazaoSocial.grabFocus();
        } else if (txtNomeFantasia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome fantasia não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeFantasia.grabFocus();
        } else {
            EmpresaController cont = new EmpresaController();
            if (cont.verificaEmpresaRepetidaRazaoSocial(txtRazaoSocial.getText()) == true) {
                JOptionPane.showMessageDialog(null, "A empresa cuja razão social é "+txtRazaoSocial.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtRazaoSocial.selectAll();
                txtRazaoSocial.grabFocus();
            } else if (cont.verificaEmpresaRepetidaNomeFantasia(txtNomeFantasia.getText()) == true) {
                JOptionPane.showMessageDialog(null, "A empresa cujo nome fantasia é "+txtNomeFantasia.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNomeFantasia.selectAll();
                txtNomeFantasia.grabFocus();
            } else {                
                EmpresaClasse empresa = new EmpresaClasse();
                empresa.setRazao_social(txtRazaoSocial.getText());
                empresa.setNome_fantasia(txtNomeFantasia.getText());
                empresa.setCnpj(txtCnpj.getText());
                empresa.setTelefone(txtTelefone.getText());
                empresa.setEmail(txtEmail.getText());
                empresa.setCep(txtCep.getText());
                empresa.setEndereco(txtEndereco.getText());
                empresa.setBairro(txtBairro.getText());
                empresa.setCidade(txtCidade.getText());
                empresa.setEstado(txtEstado.getText());
                empresa.setId_Pais(lista_paises.get(comboPais.getSelectedIndex()).getId());
                empresa.setInscricao_estadual(txtInscricaoEstadual.getText());
                empresa.setObservacao(txtObservacao.getText());
                
                int result = cont.addEmpresa(empresa);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Empresa "+txtRazaoSocial.getText()+" cadastrada com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar a empresa "+txtRazaoSocial.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void limpar () {
        txtRazaoSocial.setText("");
        txtNomeFantasia.setText("");
        txtCnpj.setValue(null);
        txtTelefone.setValue(null);
        txtEmail.setText("");
        txtTelefone.setText("");
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
        
        txtInscricaoEstadual.setText("");
        txtObservacao.setText("");
        
        txtRazaoSocial.grabFocus();
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
