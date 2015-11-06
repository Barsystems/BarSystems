/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento_produto.janela;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import orcamento_produto.classe.OrcamentoProdutoClasse;
import produto.classe.ProdutoClasse;
import produto.comboBox.ProdutoComboBox;
import produto.comboBox.ProdutoComboBoxCellRenderer;
import produto.controller.ProdutoController;
import utilidades.TrocaVirgulaPorPonto;

/**
 *
 * @author Marcos
 */
public class OrcamentoProdutoEscolherProduto extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblProduto, lblQuantidade;
    private JComboBox comboProduto;
    private JTextField txtQuantidade;
    private List<ProdutoClasse> lista_produto;
    private JButton btnPesquisarProduto, btnSalvar, btnSair;
    
    private Font fonteTitulo, fonteGeral;
    
    public boolean escolheu = false;
    public OrcamentoProdutoClasse classe = new OrcamentoProdutoClasse();
    
    public OrcamentoProdutoEscolherProduto() {
        setTitle("Escolher produto");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 300);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 550, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("ESCOLHER PRODUTO");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 550, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 550, 140);
        
        lblProduto = new JLabel("Produto");
        lblProduto.setFont(fonteGeral);
        lblProduto.setBounds(30, 30, 70, 30);
        
        comboProduto = new JComboBox();
        comboProduto.setFont(fonteGeral);
        comboProduto.setBounds(120, 30, 300, 30);
        
        lista_produto = new ProdutoController().findProduto("");
        comboProduto.setModel(new ProdutoComboBox(lista_produto));
        comboProduto.setRenderer(new ProdutoComboBoxCellRenderer());
        
        btnPesquisarProduto = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        btnPesquisarProduto.setBounds(430, 30, 40, 30);
        btnPesquisarProduto.addActionListener(this);
        
        lblQuantidade = new JLabel("Quantidade");
        lblQuantidade.setFont(fonteGeral);
        lblQuantidade.setBounds(30, 70, 70, 30);
        
        txtQuantidade = new JTextField();
        txtQuantidade.setFont(fonteGeral);
        txtQuantidade.setBounds(120, 70, 300, 30);
        
        painel2.add(lblProduto);
        painel2.add(comboProduto);
        painel2.add(btnPesquisarProduto);
        painel2.add(lblQuantidade);
        painel2.add(txtQuantidade);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 200, 550, 100);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(fonteGeral);
        btnSalvar.setBounds(170, 20, 100, 30);
        btnSalvar.addActionListener(this);
        
        btnSair = new JButton("Sair");
        btnSair.setFont(fonteGeral);
        btnSair.setBounds(280, 20, 100, 30);
        btnSair.addActionListener(this);
        
        painel3.add(btnSalvar);
        painel3.add(btnSair);
        
        add(painel1);
        add(painel2);
        add(painel3);
    }
    
    public void pesquisarProduto() {
        String cod;
        Long id = null;
        boolean flag = false;
        try {
            cod = JOptionPane.showInputDialog(null, "Digite o ID do produto para selecioná-lo", "PESQUISAR PRODUTO", JOptionPane.PLAIN_MESSAGE);
            if (cod != null) {
                id = Long.parseLong(cod);

                for (int i = 0; i < lista_produto.size(); i++) {
                    if (lista_produto.get(i).getId().equals(id)) {
                        comboProduto.setSelectedIndex(i);
                        flag = true;
                        comboProduto.repaint();
                    }
                }

                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "Não existe produto cadastrado com este ID!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID incorreto!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void salvar() {
        if (txtQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo quantidade não pode ficar vazio!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtQuantidade.grabFocus();
        }else {
            boolean flag = true;
            int index = comboProduto.getSelectedIndex();
            
            float quantidade = new TrocaVirgulaPorPonto().trocaVirgulaPorPonto(txtQuantidade.getText());
            if (lista_produto.get(index).getTipo_medida().equals("Unidade")) {
                //SE O RESTO DA DIVISÃO POR 1 FOR = 0, ENTÃO É UM NÚMERO INTEIRO
                if (quantidade % 1 != 0) {
                    JOptionPane.showMessageDialog(null, "Este produto foi cadastrado como \"unidade\"! Defina uma quantidade válida!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    txtQuantidade.grabFocus();
                    flag = false;
                }
            }
            
            if (flag == true) {
                classe.setId_produto(lista_produto.get(index).getId());
                classe.setNome_produto(lista_produto.get(index).getNome());
                classe.setValor_cobrado(lista_produto.get(index).getValor_venda());
                classe.setQuantidade(quantidade);
                classe.setTipo_medida(lista_produto.get(index).getTipo_medida());

                escolheu = true;
                dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPesquisarProduto) {
            pesquisarProduto();
        } else if (e.getSource() == btnSalvar) {
            salvar();
        } else if (e.getSource() == btnSair) {
            dispose();
        }
    }
    
}
