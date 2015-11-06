/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados_empresa.janela;

import dados_empresa.classe.DadosEmpresaClasse;
import dados_empresa.controller.DadosEmpresaController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import pais.classe.PaisClasse;
import pais.comboBox.PaisComboBoxCellRenderer;
import pais.comboBox.PaisComboBoxModel;
import pais.controller.PaisController;
import usuario.classe.UsuarioClasse;
import utilidades.FormataImagem;

/**
 *
 * @author Marcos
 */
public class DadosEmpresaJanela extends JDialog implements ActionListener, MouseListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblRazaoSocial, lblNomeFantasia, lblCnpj, lblEmail, lblTelefone, lblInscricaoEstadual, 
            lblCep, lblEndereco, lblBairro, lblCidade, lblEstado, lblPais, lblLogomarca1, lblLogomarca2, lblLogomarca3;
    private JTextField txtRazaoSocial, txtNomeFantasia, txtEmail, txtInscricaoEstadual, txtEndereco,
            txtBairro, txtCidade;
    private JFormattedTextField  txtCnpj, txtTelefone, txtCep, txtEstado;
    private JComboBox comboPais;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral, fontePequena;
    
    private List<PaisClasse> lista_paises;
    
    private DadosEmpresaClasse classe;
    
    private String caminho_imagem;
    private int editou_imagem = 0;
    
    public DadosEmpresaJanela(UsuarioClasse user, int width) throws ParseException, IOException {
        
        setTitle("Editar dados da empresa");
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
        lblTitulo.setText("EDITAR DADOS DA EMPRESA");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 800, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 800, 410);
        
        DadosEmpresaController cont = new DadosEmpresaController();
        classe = cont.findEmpresa();
        
        lblRazaoSocial = new JLabel("Razão social");
        lblRazaoSocial.setFont(fonteGeral);
        lblRazaoSocial.setBounds(30, 30, 80, 30);
        
        txtRazaoSocial = new JTextField();
        txtRazaoSocial.setText(classe.getRazao_social());
        txtRazaoSocial.setFont(fonteGeral);
        txtRazaoSocial.setBounds(110, 30, 250, 30);
        
        lblNomeFantasia = new JLabel("Nome fantasia");
        lblNomeFantasia.setFont(fonteGeral);
        lblNomeFantasia.setBounds(390, 30, 80, 30);
        
        txtNomeFantasia = new JTextField();
        txtNomeFantasia.setText(classe.getNome_fantasia());
        txtNomeFantasia.setFont(fonteGeral);
        txtNomeFantasia.setBounds(480, 30, 250, 30);
        
        lblCnpj = new JLabel("Cnpj");
        lblCnpj.setFont(fonteGeral);
        lblCnpj.setBounds(30, 70, 30, 30);
        
        txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        txtCnpj.setText(classe.getCnpj());
        txtCnpj.setFont(fonteGeral);
        txtCnpj.setBounds(110, 70, 250, 30);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(fonteGeral);
        lblEmail.setBounds(390, 70, 50, 30);
        
        txtEmail = new JTextField();
        txtEmail.setText(classe.getEmail());
        txtEmail.setFont(fonteGeral);
        txtEmail.setBounds(480, 70, 250, 30);
        
        lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(fonteGeral);
        lblTelefone.setBounds(30, 110, 60, 30);
        
        txtTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
        txtTelefone.setText(classe.getTelefone());
        txtTelefone.setFont(fonteGeral);
        txtTelefone.setBounds(110, 110, 250, 30);
        
        lblInscricaoEstadual = new JLabel("Insc. Estadual");
        lblInscricaoEstadual.setFont(fonteGeral);
        lblInscricaoEstadual.setBounds(390, 110, 80, 30);
        
        txtInscricaoEstadual = new JTextField();
        txtInscricaoEstadual.setText(classe.getInscricao_estadual());
        txtInscricaoEstadual.setFont(fonteGeral);
        txtInscricaoEstadual.setBounds(480, 110, 250, 30);
        
        lblCep = new JLabel("Cep");
        lblCep.setFont(fonteGeral);
        lblCep.setBounds(30, 180, 80, 30);
        
        txtCep = new JFormattedTextField(new MaskFormatter("########"));
        txtCep.setText(classe.getCep());
        txtCep.setFont(fonteGeral);
        txtCep.setBounds(110, 180, 100, 30);
        
        lblEndereco = new JLabel("Endereço");
        lblEndereco.setFont(fonteGeral);
        lblEndereco.setBounds(30, 220, 80, 30);
        
        txtEndereco = new JTextField();
        txtEndereco.setText(classe.getEndereco());
        txtEndereco.setFont(fonteGeral);
        txtEndereco.setBounds(110, 220, 250, 30);
        
        lblBairro = new JLabel("Bairro");
        lblBairro.setFont(fonteGeral);
        lblBairro.setBounds(30, 260, 70, 30);
        
        txtBairro = new JTextField();
        txtBairro.setText(classe.getBairro());
        txtBairro.setFont(fonteGeral);
        txtBairro.setBounds(110, 260, 250, 30);
        
        lblCidade = new JLabel("Cidade");
        lblCidade.setFont(fonteGeral);
        lblCidade.setBounds(30, 300, 70, 30);
        
        txtCidade = new JTextField();
        txtCidade.setText(classe.getCidade());
        txtCidade.setFont(fonteGeral);
        txtCidade.setBounds(110, 300, 250, 30);
        
        lblEstado = new JLabel("Uf");
        lblEstado.setFont(fonteGeral);
        lblEstado.setBounds(30, 340, 20, 30);
        
        txtEstado = new JFormattedTextField(new MaskFormatter("UU"));
        txtEstado.setText(classe.getEstado());
        txtEstado.setFont(fonteGeral);
        txtEstado.setBounds(110, 340, 50, 30);
        
        lblPais = new JLabel("País");
        lblPais.setFont(fonteGeral);
        lblPais.setBounds(180, 340, 40, 30);
        
        comboPais = new JComboBox();
        comboPais.setFont(fonteGeral);
        comboPais.setBounds(220, 340, 140, 30);
        
        lista_paises = new PaisController().findPaises();
        comboPais.setModel(new PaisComboBoxModel(lista_paises));
        comboPais.setRenderer(new PaisComboBoxCellRenderer());
        
        for (int i = 0; i < lista_paises.size(); i++) {
            if (lista_paises.get(i).getId().equals(this.classe.getId_pais())) {
                comboPais.setSelectedIndex(i);
            }
        }
        
        lblLogomarca1 = new JLabel("Logomarca");
        lblLogomarca1.setFont(fonteGeral);
        lblLogomarca1.setBounds(390, 180, 80, 30);
        
        lblLogomarca2 = new JLabel();
        lblLogomarca2.setBounds(480, 180, 250, 190);
        lblLogomarca2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLogomarca2.addMouseListener(this);
        
        if (classe.getLogomarca() != null) {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(classe.getLogomarca()));
            //AQUI DIMINUIMOS A IMAGEM PARA FICAR DO TAMANHO DO LABEL
            Image imagemMenor = img.getScaledInstance(250, 190, 0);
            lblLogomarca2.setIcon(new ImageIcon(imagemMenor));
        } else {
            lblLogomarca2.setText("Imagem não disponível");
            lblLogomarca2.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        }
        
        lblLogomarca3 = new JLabel("Clique na imagem para alterá-la");
        lblLogomarca3.setFont(fontePequena);
        lblLogomarca3.setForeground(Color.red);
        lblLogomarca3.setBounds(480, 375, 150, 20);
        
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
        painel2.add(lblLogomarca1);
        painel2.add(lblLogomarca2);
        painel2.add(lblLogomarca3);
        
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
    
    public void trocarLogomarca() {
        try {
            JFileChooser chooser = new JFileChooser();
            int escolha = chooser.showOpenDialog(this);
            if (escolha == JFileChooser.APPROVE_OPTION) {
                caminho_imagem = chooser.getSelectedFile().getAbsolutePath();
                BufferedImage img = ImageIO.read(new File(caminho_imagem));
                lblLogomarca2.setIcon(new ImageIcon(new FormataImagem().FormataImagem(img, 250, 190)));
                
                //DECLARO QUE A IMAGEM FOI ALTERADA
                editou_imagem = 1;
            }
        } catch (HeadlessException | IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar() throws IOException {
        if (txtRazaoSocial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo razão social não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtRazaoSocial.grabFocus();
        } else if (txtNomeFantasia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome fantasia não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNomeFantasia.grabFocus();
        } else {
            int result = 0;
            
            DadosEmpresaController cont = new DadosEmpresaController();
            classe.setRazao_social(txtRazaoSocial.getText());
            classe.setNome_fantasia(txtNomeFantasia.getText());
            classe.setCnpj(txtCnpj.getText());
            classe.setTelefone(txtTelefone.getText());
            classe.setEmail(txtEmail.getText());
            classe.setCep(txtCep.getText());
            classe.setEndereco(txtEndereco.getText());
            classe.setBairro(txtBairro.getText());
            classe.setCidade(txtCidade.getText());
            classe.setEstado(txtEstado.getText());
            classe.setId_pais(lista_paises.get(comboPais.getSelectedIndex()).getId());
            classe.setInscricao_estadual(txtInscricaoEstadual.getText());
            if (editou_imagem == 1) {
                BufferedImage logomarca = ImageIO.read(new File(caminho_imagem));
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                ImageIO.write(logomarca, "jpg", bytesImg);
                bytesImg.flush();
                classe.setLogomarca(bytesImg.toByteArray());
            }

            result = cont.updateDadosEmpresa(classe);
            
            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Dados da empresa editados com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao tentar editar os dados da empresa!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            try {
                salvar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnVoltar) {
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lblLogomarca2) {
            trocarLogomarca();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
