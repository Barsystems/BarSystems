/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.janela;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import servico.classe.ServicoClasse;
import servico.controller.ServicoController;
import servico_setor.classe.ServicoSetorClasse;
import servico_setor.comboBox.ServicoSetorComboBox;
import servico_setor.comboBox.ServicoSetorComboBoxCellRenderer;
import servico_setor.controller.ServicoSetorController;
import utilidades.TrocaVirgulaPorPonto;

/**
 *
 * @author Marcos
 */
public class ServicoCadastrar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblSetor, lblValorVenda, lblValorComissao;
    private JTextField txtNome, txtValorVenda, txtValorComissao;
    private JComboBox comboSetor;
    private JButton btnSalvar, btnLimpar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<ServicoSetorClasse> setores;
    
    public boolean cadastrou = false; //esta variável vai dizer para o form pai se foi cadastrado algum usuário. Se sim, atualiza a tabela na outra tela
    
    public ServicoCadastrar() {
        setTitle("Novo cadastro");
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
        
        lblNome = new JLabel("Nome do serviço");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 110, 30);
        
        txtNome = new JTextField();
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(190, 30, 190, 30);
        
        lblSetor = new JLabel("Setor do serviço");
        lblSetor.setFont(fonteGeral);
        lblSetor.setBounds(30, 70, 110, 30);
        
        comboSetor = new JComboBox();
        comboSetor.setFont(fonteGeral);
        comboSetor.setBounds(190, 70, 190, 30);
        
        setores = new ServicoSetorController().findSetor("");
        comboSetor.setModel(new ServicoSetorComboBox(setores));
        comboSetor.setRenderer(new ServicoSetorComboBoxCellRenderer());
        
        lblValorVenda = new JLabel("Valor de venda");
        lblValorVenda.setFont(fonteGeral);
        lblValorVenda.setBounds(30, 110, 110, 30);
        
        txtValorVenda = new JTextField();
        txtValorVenda.setFont(fonteGeral);
        txtValorVenda.setBounds(190, 110, 190, 30);
        
        lblValorComissao = new JLabel("Valor da comissão");
        lblValorComissao.setFont(fonteGeral);
        lblValorComissao.setBounds(30, 150, 110, 30);
        
        txtValorComissao = new JTextField();
        txtValorComissao.setFont(fonteGeral);
        txtValorComissao.setBounds(190, 150, 190, 30);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblSetor);
        painel2.add(comboSetor);
        painel2.add(lblValorVenda);
        painel2.add(txtValorVenda);
        painel2.add(lblValorComissao);
        painel2.add(txtValorComissao);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 270, 500, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(90, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(fonteGeral);
        btnLimpar.setBounds(200, 20, 100, 30);
        btnLimpar.addActionListener(this);
        
        btnVoltar = new JButton("Sair");
        btnVoltar.setFont(fonteGeral);
        btnVoltar.setBounds(310, 20, 100, 30);
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
            JOptionPane.showMessageDialog(null, "O nome do serviço não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else if (txtNome.getText().length() > 70) {
            JOptionPane.showMessageDialog(null, "O nome do serviço não pode ter mais que 70 caracteres!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            TrocaVirgulaPorPonto troca = new TrocaVirgulaPorPonto();
            if (txtValorVenda.getText().isEmpty()) {
                txtValorVenda.setText("0,00");
            }
            float valor_venda = troca.trocaVirgulaPorPonto(txtValorVenda.getText());
            if (txtValorComissao.getText().isEmpty()) {
                txtValorComissao.setText("0,00");
            }
            float valor_comissao = troca.trocaVirgulaPorPonto(txtValorComissao.getText());
           
            ServicoController cont = new ServicoController();
            if (cont.verificaServicoRepetido(txtNome.getText()) == true) {
                JOptionPane.showMessageDialog(null, "O serviço "+txtNome.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {                
                ServicoClasse classe = new ServicoClasse();
                classe.setNome(txtNome.getText());
                classe.setId_setor(setores.get(comboSetor.getSelectedIndex()).getId());
                classe.setValor_venda(valor_venda);
                classe.setValor_comissao(valor_comissao);

                int result = cont.addServico(classe);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Serviço "+txtNome.getText()+" cadastrado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    cadastrou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar cadastrar o serviço "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void limpar() {
        txtNome.setText("");
        comboSetor.setSelectedIndex(0);
        comboSetor.repaint();
        txtValorVenda.setText("");
        txtValorComissao.setText("");
        
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
