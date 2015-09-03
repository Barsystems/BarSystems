/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.janela;

import cliente.classe.ClienteClasse;
import cliente.controller.ClienteController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public class ClienteEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblNascimento, lblCpf, lblRg, lblEmail, lblTelefone, lblCelular, 
            lblObservacao, lblBloqueado, lblCep, lblEndereco, lblBairro, lblCidade, lblEstado, lblPais;
    private JTextField txtNome, txtRg, txtEmail, txtEndereco,
            txtBairro, txtCidade;
    private JFormattedTextField txtNascimento, txtCpf, txtTelefone, txtCelular, txtCep, txtEstado;
    private JTextArea txtObservacao;
    private JScrollPane scrollTextArea;
    private JComboBox comboPais;
    private JRadioButton radioBloqueado, radioLiberado;
    private ButtonGroup grupoRadio;
    private JButton btnSalvar, btnLimpar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral, fontePequena;
    
    private List<PaisClasse> lista_paises;
    
    private ClienteClasse cliente;
    
    public boolean editou = false;
    
    public ClienteEditar(ClienteClasse cliente) throws ParseException {
        
        this.cliente = cliente;
        
        setTitle("Editar cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 570);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fontePequena = new Font("Tahoma", Font.PLAIN, 10);
        
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
        
        lblNome = new JLabel("Nome");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 80, 30);
        
        txtNome = new JTextField();
        txtNome.setFont(fonteGeral);
        txtNome.setText(this.cliente.getNome());
        txtNome.setBounds(110, 30, 250, 30);
        
        lblNascimento = new JLabel("Nascimento");
        lblNascimento.setFont(fonteGeral);
        lblNascimento.setBounds(390, 30, 80, 30);
        
        txtNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        txtNascimento.setFont(fonteGeral);
        txtNascimento.setText(this.cliente.getData_nascimento());
        txtNascimento.setBounds(480, 30, 80, 30);
        
        lblCpf = new JLabel("Cpf");
        lblCpf.setFont(fonteGeral);
        lblCpf.setBounds(580, 30, 30, 30);
        
        txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        txtCpf.setFont(fonteGeral);
        txtCpf.setText(this.cliente.getCpf());
        txtCpf.setBounds(620, 30, 110, 30);
        
        lblRg = new JLabel("Rg");
        lblRg.setFont(fonteGeral);
        lblRg.setBounds(30, 70, 20, 30);
        
        txtRg = new JTextField();
        txtRg.setFont(fonteGeral);
        txtRg.setText(this.cliente.getRg());
        txtRg.setBounds(110, 70, 250, 30);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(fonteGeral);
        lblEmail.setBounds(390, 70, 50, 30);
        
        txtEmail = new JTextField();
        txtEmail.setFont(fonteGeral);
        txtEmail.setText(this.cliente.getEmail());
        txtEmail.setBounds(480, 70, 250, 30);
        
        lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(fonteGeral);
        lblTelefone.setBounds(30, 110, 60, 30);
        
        txtTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
        txtTelefone.setFont(fonteGeral);
        txtTelefone.setText(this.cliente.getTelefone());
        txtTelefone.setBounds(110, 110, 250, 30);
        
        lblCelular = new JLabel("Celular");
        lblCelular.setFont(fonteGeral);
        lblCelular.setBounds(390, 110, 80, 30);
        
        txtCelular = new JFormattedTextField(new MaskFormatter("(##) ########*"));
        txtCelular.setFont(fonteGeral);
        txtCelular.setText(this.cliente.getCelular());
        txtCelular.setBounds(480, 110, 250, 30);
        
        lblObservacao = new JLabel("Observação");
        lblObservacao.setFont(fonteGeral);
        lblObservacao.setBounds(30, 150, 80, 30);
        
        txtObservacao = new JTextArea();
        txtObservacao.setFont(fonteGeral);
        txtObservacao.setText(this.cliente.getObservacao());
        txtObservacao.setLineWrap(true);
        txtObservacao.setWrapStyleWord(true);
        
        scrollTextArea = new JScrollPane(txtObservacao);
        scrollTextArea.setBounds(110, 150, 620, 90);
        
        lblBloqueado = new JLabel("Cliente bloqueado para compra ou serviço");
        lblBloqueado.setFont(fontePequena);
        lblBloqueado.setForeground(Color.red);
        lblBloqueado.setBounds(390, 240, 200, 30);
        
        radioLiberado = new JRadioButton("Não");
        radioLiberado.setFont(fontePequena);
        radioLiberado.setBackground(Color.WHITE);
        radioLiberado.setBounds(620, 240, 60, 30);
        
        radioBloqueado = new JRadioButton("Sim");
        radioBloqueado.setFont(fontePequena);
        radioBloqueado.setBackground(Color.WHITE);
        radioBloqueado.setBounds(680, 240, 60, 30);
        
        grupoRadio = new ButtonGroup();
        grupoRadio.add(radioLiberado);
        grupoRadio.add(radioBloqueado);
        
        boolean bloqueado = this.cliente.isBloqueado();
        if (bloqueado == true) {
            radioBloqueado.setSelected(true);
        } else {
            radioLiberado.setSelected(true);
        }
        
        lblCep = new JLabel("Cep");
        lblCep.setFont(fonteGeral);
        lblCep.setBounds(30, 270, 30, 30);
        
        txtCep = new JFormattedTextField(new MaskFormatter("########"));
        txtCep.setFont(fonteGeral);
        txtCep.setText(this.cliente.getCep());
        txtCep.setBounds(110, 270, 100, 30);
        
        lblEndereco = new JLabel("Endereço");
        lblEndereco.setFont(fonteGeral);
        lblEndereco.setBounds(30, 310, 80, 30);
        
        txtEndereco = new JTextField();
        txtEndereco.setFont(fonteGeral);
        txtEndereco.setText(this.cliente.getEndereco());
        txtEndereco.setBounds(110, 310, 250, 30);
        
        lblBairro = new JLabel("Bairro");
        lblBairro.setFont(fonteGeral);
        lblBairro.setBounds(390, 310, 70, 30);
        
        txtBairro = new JTextField();
        txtBairro.setFont(fonteGeral);
        txtBairro.setText(this.cliente.getBairro());
        txtBairro.setBounds(480, 310, 250, 30);
        
        lblCidade = new JLabel("Cidade");
        lblCidade.setFont(fonteGeral);
        lblCidade.setBounds(30, 350, 70, 30);
        
        txtCidade = new JTextField();
        txtCidade.setFont(fonteGeral);
        txtCidade.setText(this.cliente.getCidade());
        txtCidade.setBounds(110, 350, 250, 30);
        
        lblEstado = new JLabel("Uf");
        lblEstado.setFont(fonteGeral);
        lblEstado.setBounds(390, 350, 20, 30);
        
        txtEstado = new JFormattedTextField(new MaskFormatter("UU"));
        txtEstado.setFont(fonteGeral);
        txtEstado.setText(this.cliente.getEstado());
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
            if (lista_paises.get(i).getId().equals(this.cliente.getId_pais())) {
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
        painel2.add(lblBloqueado);
        painel2.add(radioLiberado);
        painel2.add(radioBloqueado);
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
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else {
            ClienteController cont = new ClienteController();
            if (cont.verificaClienteRepetido(txtCpf.getText()) == true && cliente.getCpf().equals(txtCpf.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O cliente cujo cpf é "+txtCpf.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtCpf.selectAll();
                txtCpf.grabFocus();
            } else {
                
                boolean bloqueado;
                if (radioBloqueado.isSelected()) {
                    bloqueado = true;
                } else {
                    bloqueado = false;
                }
                
                this.cliente.setNome(txtNome.getText());
                this.cliente.setData_nascimento(txtNascimento.getText());
                this.cliente.setCpf(txtCpf.getText());
                this.cliente.setRg(txtRg.getText());
                this.cliente.setEmail(txtEmail.getText());
                this.cliente.setTelefone(txtTelefone.getText());
                this.cliente.setCelular(txtCelular.getText());
                this.cliente.setObservacao(txtObservacao.getText());
                this.cliente.setBloqueado(bloqueado);
                this.cliente.setCep(txtCep.getText());
                this.cliente.setEndereco(txtEndereco.getText());
                this.cliente.setBairro(txtBairro.getText());
                this.cliente.setCidade(txtCidade.getText());
                this.cliente.setEstado(txtEstado.getText());
                this.cliente.setId_pais(lista_paises.get(comboPais.getSelectedIndex()).getId());
                
                int result = cont.updateCliente(this.cliente);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Cliente "+txtNome.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar o cliente "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
