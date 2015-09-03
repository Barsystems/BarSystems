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
public class ServicoEditar extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblSetor, lblValorVenda, lblValorComissao;
    private JTextField txtNome, txtValorVenda, txtValorComissao;
    private JComboBox comboSetor;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<ServicoSetorClasse> setores;
    
    private ServicoClasse classe;
    
    public boolean editou = false;
    
    public ServicoEditar(ServicoClasse classe) {
        
        this.classe = classe;
        
        setTitle("Editar cadastro");
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
        lblTitulo.setText("EDITAR CADASTRO");
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
        txtNome.setText(this.classe.getNome());
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
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getId().equals(this.classe.getId_setor())) {
                comboSetor.setSelectedIndex(i);
            }
        }
        
        lblValorVenda = new JLabel("Valor de venda");
        lblValorVenda.setFont(fonteGeral);
        lblValorVenda.setBounds(30, 110, 110, 30);
        
        txtValorVenda = new JTextField();
        txtValorVenda.setText(String.valueOf(this.classe.getValor_venda()));
        txtValorVenda.setFont(fonteGeral);
        txtValorVenda.setBounds(190, 110, 190, 30);   
        
        lblValorComissao = new JLabel("Valor da comissão");
        lblValorComissao.setFont(fonteGeral);
        lblValorComissao.setBounds(30, 150, 110, 30);
        
        txtValorComissao = new JTextField();
        txtValorComissao.setText(String.valueOf(classe.getValor_comissao()));
        txtValorComissao.setFont(fonteGeral);
        txtValorComissao.setBounds(190, 150, 190, 30);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblSetor);
        painel2.add(comboSetor);
        painel2.add(lblValorVenda);
        painel2.add(txtValorVenda);
        
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
    }
    
    public void salvar() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNome.grabFocus();
        } else {
            ServicoController cont = new ServicoController();
            if (cont.verificaServicoRepetido(txtNome.getText()) == true && classe.getNome().equals(txtNome.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O serviço "+txtNome.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
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
               
                classe.setNome(txtNome.getText());
                classe.setId_setor(setores.get(comboSetor.getSelectedIndex()).getId());
                classe.setValor_venda(valor_venda);
                classe.setValor_comissao(valor_comissao);

                int result = cont.updateServico(classe);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Serviço "+txtNome.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    editou = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao tentar editar o serviço "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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
