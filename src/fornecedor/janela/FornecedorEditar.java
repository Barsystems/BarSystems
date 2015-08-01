/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.janela;

import fornecedor.classe.FornecedorClasse;
import fornecedor.controller.FornecedorController;
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
public class FornecedorEditar extends JDialog implements ActionListener {
    
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
    
    private FornecedorClasse forn;
    
    public boolean editou = false;
    
    public FornecedorEditar(FornecedorClasse forn) throws ParseException {
        
        this.forn = forn;
        
        setTitle("Editar cadastro");
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
        lblTitulo.setText("EDITAR CADASTRO");
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
        txtRazaoSocial.setText(this.forn.getRazao_social());
        txtRazaoSocial.setFont(fonteGeral);
        txtRazaoSocial.setBounds(110, 30, 250, 30);
        
        lblNomeFantasia = new JLabel("Nome fantasia");
        lblNomeFantasia.setFont(fonteGeral);
        lblNomeFantasia.setBounds(390, 30, 80, 30);
        
        txtNomeFantasia = new JTextField();
        txtNomeFantasia.setText(this.forn.getNome_fantasia());
        txtNomeFantasia.setFont(fonteGeral);
        txtNomeFantasia.setBounds(480, 30, 250, 30);
        
        lblCnpj = new JLabel("Cnpj");
        lblCnpj.setFont(fonteGeral);
        lblCnpj.setBounds(30, 70, 30, 30);
        
        txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        txtCnpj.setText(this.forn.getCnpj());
        txtCnpj.setFont(fonteGeral);
        txtCnpj.setBounds(110, 70, 250, 30);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(fonteGeral);
        lblEmail.setBounds(390, 70, 50, 30);
        
        txtEmail = new JTextField();
        txtEmail.setText(this.forn.getEmail());
        txtEmail.setFont(fonteGeral);
        txtEmail.setBounds(480, 70, 250, 30);
        
        lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(fonteGeral);
        lblTelefone.setBounds(30, 110, 60, 30);
        
        txtTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
        txtTelefone.setText(this.forn.getTelefone());
        txtTelefone.setFont(fonteGeral);
        txtTelefone.setBounds(110, 110, 250, 30);
        
        lblInscricaoEstadual = new JLabel("Insc. Estadual");
        lblInscricaoEstadual.setFont(fonteGeral);
        lblInscricaoEstadual.setBounds(390, 110, 80, 30);
        
        txtInscricaoEstadual = new JTextField();
        txtInscricaoEstadual.setText(this.forn.getInscricao_estadual());
        txtInscricaoEstadual.setFont(fonteGeral);
        txtInscricaoEstadual.setBounds(480, 110, 250, 30);
        
        lblObservacao = new JLabel("Observação");
        lblObservacao.setFont(fonteGeral);
        lblObservacao.setBounds(30, 150, 80, 30);
        
        txtObservacao = new JTextArea();
        txtObservacao.setText(this.forn.getObservacao());
        txtObservacao.setFont(fonteGeral);
        txtObservacao.setLineWrap(true);
        txtObservacao.setWrapStyleWord(true);
        
        scrollTextArea = new JScrollPane(txtObservacao);
        scrollTextArea.setBounds(110, 150, 620, 90);
        
        lblCep = new JLabel("Cep");
        lblCep.setFont(fonteGeral);
        lblCep.setBounds(30, 270, 30, 30);
        
        txtCep = new JFormattedTextField(new MaskFormatter("########"));
        txtCep.setText(this.forn.getCep());
        txtCep.setFont(fonteGeral);
        txtCep.setBounds(110, 270, 100, 30);
        
        lblEndereco = new JLabel("Endereço");
        lblEndereco.setFont(fonteGeral);
        lblEndereco.setBounds(30, 310, 80, 30);
        
        txtEndereco = new JTextField();
        txtEndereco.setText(this.forn.getEndereco());
        txtEndereco.setFont(fonteGeral);
        txtEndereco.setBounds(110, 310, 250, 30);
        
        lblBairro = new JLabel("Bairro");
        lblBairro.setFont(fonteGeral);
        lblBairro.setBounds(390, 310, 70, 30);
        
        txtBairro = new JTextField();
        txtBairro.setText(this.forn.getBairro());
        txtBairro.setFont(fonteGeral);
        txtBairro.setBounds(480, 310, 250, 30);
        
        lblCidade = new JLabel("Cidade");
        lblCidade.setFont(fonteGeral);
        lblCidade.setBounds(30, 350, 70, 30);
        
        txtCidade = new JTextField();
        txtCidade.setText(this.forn.getCidade());
        txtCidade.setFont(fonteGeral);
        txtCidade.setBounds(110, 350, 250, 30);
        
        lblEstado = new JLabel("Uf");
        lblEstado.setFont(fonteGeral);
        lblEstado.setBounds(390, 350, 20, 30);
        
        txtEstado = new JFormattedTextField(new MaskFormatter("UU"));
        txtEstado.setText(this.forn.getEstado());
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
            if (lista_paises.get(i).getId().equals(this.forn.getId_Pais())) {
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
        btnSalvar.setBounds(300, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(410, 20, 100, 30);
        btnVoltar.addActionListener(this);
        
        painel3.add(btnSalvar);
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
            FornecedorController cont = new FornecedorController();
            if (cont.verificaFornecedorRepetidoRazaoSocial(txtRazaoSocial.getText()) == true && forn.getRazao_social().equals(txtRazaoSocial.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O fornecedor cuja razão social é "+txtRazaoSocial.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtRazaoSocial.selectAll();
                txtRazaoSocial.grabFocus();
            } else if (cont.verificaFornecedorRepetidoNomeFantasia(txtNomeFantasia.getText()) == true && forn.getNome_fantasia().equals(txtNomeFantasia.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O fornecedor cujo nome fantasia é "+txtNomeFantasia.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNomeFantasia.selectAll();
                txtNomeFantasia.grabFocus();
            } else {
                forn.setRazao_social(txtRazaoSocial.getText());
                forn.setNome_fantasia(txtNomeFantasia.getText());
                forn.setCnpj(txtCnpj.getText());
                forn.setTelefone(txtTelefone.getText());
                forn.setEmail(txtEmail.getText());
                forn.setCep(txtCep.getText());
                forn.setEndereco(txtEndereco.getText());
                forn.setBairro(txtBairro.getText());
                forn.setCidade(txtCidade.getText());
                forn.setEstado(txtEstado.getText());
                forn.setId_Pais(lista_paises.get(comboPais.getSelectedIndex()).getId());
                forn.setInscricao_estadual(txtInscricaoEstadual.getText());
                forn.setObservacao(txtObservacao.getText());
                
                int result = cont.updateFornecedor(forn);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Fornecedor "+txtRazaoSocial.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar o fornecedor "+txtRazaoSocial.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
