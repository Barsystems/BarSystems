/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.janela;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utilidades.TrocaVirgulaPorPonto;
import produto.classe.ProdutoClasse;
import produto.comboBox.ProdutoComboBoxTipoMedida;
import produto.controller.ProdutoController;
import produto_setor.classe.ProdutoSetorClasse;
import produto_setor.comboBox.ProdutoSetorComboBox;
import produto_setor.comboBox.ProdutoSetorComboBoxCellRenderer;
import produto_setor.controller.ProdutoSetorController;

/**
 *
 * @author Marcos
 */
public class ProdutoEditar extends JDialog implements ActionListener, KeyListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblNome, lblSetor, lblTipoMedida, lblValorCompra, lblPorcentagemVenda, lblValorVenda, lblValorComissao;
    private JTextField txtNome, txtValorCompra, txtPorcentagemVenda, txtValorVenda, txtValorComissao;
    private JComboBox comboSetor, comboTipoMedida;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<ProdutoSetorClasse> setores;
    
    private ProdutoClasse prod;
    
    public boolean editou = false;
    
    public ProdutoEditar(ProdutoClasse prod) {
        
        this.prod = prod;
        
        setTitle("Editar cadastro");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 490);
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
        painel2.setBounds(0, 60, 500, 330);
        
        lblNome = new JLabel("Nome do produto");
        lblNome.setFont(fonteGeral);
        lblNome.setBounds(30, 30, 110, 30);
        
        txtNome = new JTextField();
        txtNome.setText(this.prod.getNome());
        txtNome.setFont(fonteGeral);
        txtNome.setBounds(190, 30, 190, 30);
        
        lblSetor = new JLabel("Setor do produto");
        lblSetor.setFont(fonteGeral);
        lblSetor.setBounds(30, 70, 110, 30);
        
        comboSetor = new JComboBox();
        comboSetor.setFont(fonteGeral);
        comboSetor.setBounds(190, 70, 190, 30);
        
        setores = new ProdutoSetorController().findSetor("");
        comboSetor.setModel(new ProdutoSetorComboBox(setores));
        comboSetor.setRenderer(new ProdutoSetorComboBoxCellRenderer());
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getId().equals(this.prod.getId_setor())) {
                comboSetor.setSelectedIndex(i);
            }
        }
        
        lblTipoMedida = new JLabel("Tipo de medida");
        lblTipoMedida.setFont(fonteGeral);
        lblTipoMedida.setBounds(30, 110, 110, 30);
        
        comboTipoMedida = new JComboBox();
        comboTipoMedida.setFont(fonteGeral);
        comboTipoMedida.setBounds(190, 110, 190, 30);
        
        comboTipoMedida.setModel(new ProdutoComboBoxTipoMedida());
        comboTipoMedida.setSelectedItem(this.prod.getTipo_medida());
        
        lblValorCompra = new JLabel("Valor de compra");
        lblValorCompra.setFont(fonteGeral);
        lblValorCompra.setBounds(30, 150, 150, 30);
        
        txtValorCompra = new JTextField();
        txtValorCompra.setText(String.valueOf(this.prod.getValor_compra()));
        txtValorCompra.setFont(fonteGeral);
        txtValorCompra.setBounds(190, 150, 190, 30);
        txtValorCompra.addKeyListener(this);
        
        lblPorcentagemVenda = new JLabel("Porcentagem de lucro");
        lblPorcentagemVenda.setFont(fonteGeral);
        lblPorcentagemVenda.setBounds(30, 190, 110, 30);
        
        txtPorcentagemVenda = new JTextField();
        txtPorcentagemVenda.setFont(fonteGeral);
        txtPorcentagemVenda.setBounds(190, 190, 190, 30);
        float porcentagem_venda = ((this.prod.getValor_venda() * 100) / this.prod.getValor_compra()) - 100;
        if (porcentagem_venda < 0) {
            porcentagem_venda = 0;
        }
        txtPorcentagemVenda.setText(String.valueOf(porcentagem_venda));
        txtPorcentagemVenda.addKeyListener(this);
        
        lblValorVenda = new JLabel("Valor de venda");
        lblValorVenda.setFont(fonteGeral);
        lblValorVenda.setBounds(30, 230, 110, 30);
        
        txtValorVenda = new JTextField();
        txtValorVenda.setText(String.valueOf(this.prod.getValor_venda()));
        txtValorVenda.setFont(fonteGeral);
        txtValorVenda.setBounds(190, 230, 190, 30);   
        txtValorVenda.addKeyListener(this);
        
        lblValorComissao = new JLabel("Valor da comissão");
        lblValorComissao.setFont(fonteGeral);
        lblValorComissao.setBounds(30, 270, 110, 30);
        
        txtValorComissao = new JTextField();
        txtValorComissao.setText(String.valueOf(prod.getValor_comissao()));
        txtValorComissao.setFont(fonteGeral);
        txtValorComissao.setBounds(190, 270, 190, 30);
        
        painel2.add(lblNome);
        painel2.add(txtNome);
        painel2.add(lblSetor);
        painel2.add(comboSetor);
        painel2.add(lblTipoMedida);
        painel2.add(comboTipoMedida);
        painel2.add(lblValorCompra);
        painel2.add(txtValorCompra);
        painel2.add(lblPorcentagemVenda);
        painel2.add(txtPorcentagemVenda);
        painel2.add(lblValorVenda);
        painel2.add(txtValorVenda);
        painel2.add(lblValorComissao);
        painel2.add(txtValorComissao);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 390, 500, 100);
        
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
            ProdutoController cont = new ProdutoController();
            if (cont.verificaProdutoRepetido(txtNome.getText()) == true && prod.getNome().equals(txtNome.getText()) == false) {
                JOptionPane.showMessageDialog(null, "O produto "+txtNome.getText()+" já está cadastrado!", "Atenção", JOptionPane.WARNING_MESSAGE);
                txtNome.selectAll();
                txtNome.grabFocus();
            } else {
                
                TrocaVirgulaPorPonto troca = new TrocaVirgulaPorPonto();
                float valor_compra = troca.trocaVirgulaPorPonto(txtValorCompra.getText());
                float valor_venda = troca.trocaVirgulaPorPonto(txtValorVenda.getText());
                float valor_comissao = troca.trocaVirgulaPorPonto(txtValorComissao.getText());
                
                if (valor_venda < valor_compra) {
                    JOptionPane.showMessageDialog(null, "O valor de venda não pode ser menor que o valor de compra!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    txtValorVenda.selectAll();
                    txtValorVenda.grabFocus();
                } else {                
                    prod.setNome(txtNome.getText());
                    prod.setId_setor(setores.get(comboSetor.getSelectedIndex()).getId());
                    prod.setValor_compra(valor_compra);
                    prod.setValor_venda(valor_venda);
                    prod.setValor_comissao(valor_comissao);

                    int result = cont.updateProduto(prod);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Produto "+txtNome.getText()+" editado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
                        editou = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao tentar editar o produto "+txtNome.getText()+"!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                    }
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
    
    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        TrocaVirgulaPorPonto troca = new TrocaVirgulaPorPonto();
        
        float valor_compra = 0; 
        if (txtValorCompra.getText().isEmpty() == false) {
            valor_compra = troca.trocaVirgulaPorPonto(txtValorCompra.getText());
        }
        
        float porcentagem_venda = 0;
        if (txtPorcentagemVenda.getText().isEmpty() == false) 
            porcentagem_venda = troca.trocaVirgulaPorPonto(txtPorcentagemVenda.getText());
        
        float valor_venda = 0;
        if (txtValorVenda.getText().isEmpty() == false) {
            valor_venda = troca.trocaVirgulaPorPonto(txtValorVenda.getText());
        }
        
        if (e.getSource() == txtValorCompra) {
            if (porcentagem_venda > 0) {
                txtValorVenda.setText(String.valueOf((valor_compra * (porcentagem_venda / 100)) + valor_compra));
            }
        } else if (e.getSource() == txtPorcentagemVenda) {            
            txtValorVenda.setText(String.valueOf((valor_compra * (porcentagem_venda / 100)) + valor_compra));          
        } else if (e.getSource() == txtValorVenda) {
            if (txtValorCompra.getText().isEmpty()) {
                txtValorCompra.setText("0.0");
            }
            
            if (valor_venda < valor_compra) {
                txtPorcentagemVenda.setText("0.0");
            } else {
                txtPorcentagemVenda.setText(String.valueOf(((valor_venda * 100) / valor_compra) - 100));
            }
        }
    }
    
}
