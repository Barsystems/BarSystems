/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.janela;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Rectangle;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import produto.classe.ProdutoClasse;
import produto.comboBox.ProdutoComboBox;
import produto.comboBox.ProdutoComboBoxCellRenderer;
import produto.controller.ProdutoController;
import produto.table.ProdutoTableModel;
import servico.classe.ServicoClasse;

/**
 *
 * @author Marcos
 */
public class ServicoVincularProdutos extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    
    private JLabel lblTitulo, lblServico1, lblServico2, lblProdutosVinculados, lblProdutosDisponiveis;
    private JTable tabela;
    private JScrollPane scrollTabela;
    private JComboBox comboProdutosDisponiveis;
    private JButton btnPesquisarProduto, btnAdicionar, btnRemover, btnSair;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private ServicoClasse classe;
    
    private List<ProdutoClasse> lista_produtos_disponiveis;
    private List<ProdutoClasse> lista_produtos_vinculados;
    
    public ServicoVincularProdutos (ServicoClasse classe) {
        
        this.classe = classe;
        
        setTitle("Vincular produtos");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 520);
        setLocationRelativeTo(null);
        setModal(true);
        
        fonteTitulo = new Font("Tahoma", Font.BOLD, 18);
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        fonteNegrito = new Font("Tahoma", Font.BOLD, 12);
        
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(Color.decode("#F5DF37"));
        painel1.setBounds(0, 0, 750, 60);
        
        lblTitulo = new JLabel();
        lblTitulo.setText("VINCULAR PRODUTOS");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 750, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 750, 360);
        
        lblServico1 = new JLabel("Serviço");
        lblServico1.setFont(fonteGeral);
        lblServico1.setBounds(30, 30, 100, 30);
        
        lblServico2 = new JLabel(this.classe.getNome());
        lblServico2.setFont(fonteNegrito);
        lblServico2.setForeground(Color.red);
        lblServico2.setBounds(150, 30, 300, 30);
        
        lblProdutosVinculados = new JLabel("Produtos vinculados");
        lblProdutosVinculados.setFont(fonteGeral);
        lblProdutosVinculados.setBounds(30, 70, 140, 30);
        
        tabela = new JTable();
        tabela.setFont(fonteGeral);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        refreshTable();
        
        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(150, 70, 500, 200);
        
        lblProdutosDisponiveis = new JLabel("Produtos disponíveis");
        lblProdutosDisponiveis.setFont(fonteGeral);
        lblProdutosDisponiveis.setBounds(30, 290, 140, 30);
        
        comboProdutosDisponiveis = new JComboBox();
        comboProdutosDisponiveis.setFont(fonteGeral);
        comboProdutosDisponiveis.setBounds(150, 290, 250, 30);
        
        lista_produtos_disponiveis = new ProdutoController().findProduto("");
        comboProdutosDisponiveis.setModel(new ProdutoComboBox(lista_produtos_disponiveis));
        comboProdutosDisponiveis.setRenderer(new ProdutoComboBoxCellRenderer());
        
        btnPesquisarProduto = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        btnPesquisarProduto.setBounds(410, 290, 40, 30);
        btnPesquisarProduto.addActionListener(this);
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(fonteGeral);
        btnAdicionar.setBounds(460, 290, 90, 30);
        btnAdicionar.addActionListener(this);
        
        btnRemover = new JButton("Remover");
        btnRemover.setFont(fonteGeral);
        btnRemover.setBounds(560, 290, 90, 30);
        btnRemover.addActionListener(this);
        
        painel2.add(lblServico1);
        painel2.add(lblServico2);
        painel2.add(lblProdutosVinculados);
        painel2.add(scrollTabela);
        painel2.add(lblProdutosDisponiveis);
        painel2.add(comboProdutosDisponiveis);
        painel2.add(btnPesquisarProduto);
        painel2.add(btnAdicionar);
        painel2.add(btnRemover);
        
        painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(0, 420, 700, 100);
        
        btnSair = new JButton("Sair");
        btnSair.setFont(fonteGeral);
        btnSair.setBounds(330, 20, 100, 30);
        btnSair.addActionListener(this);
        
        painel3.add(btnSair);
        
        add(painel1);
        add(painel2);
        add(painel3);
        
    }
    
    public void refreshTable() {
        lista_produtos_vinculados = new ProdutoController().findProdutoVinculadoAServico(this.classe.getId());
        if (lista_produtos_vinculados != null) {
            tabela.setModel(new ProdutoTableModel(lista_produtos_vinculados, 1));
            if (tabela.getRowCount() > 0) {
                tabela.setRowSelectionInterval(0, 0);
                tabela.scrollRectToVisible(new Rectangle(tabela.getCellRect(0, 0, true)));
            }
        }
    }
    
    public void pesquisarProduto() {
        String cod = JOptionPane.showInputDialog(null, "Digite o ID do produto para selecioná-lo", "PESQUISAR PRODUTO", JOptionPane.PLAIN_MESSAGE);
        Long id = null;
        boolean flag = false;
        try {
            if (cod != null) {
                id = Long.parseLong(cod);

                for (int i = 0; i < lista_produtos_disponiveis.size(); i++) {
                    if (lista_produtos_disponiveis.get(i).getId().equals(id)) {
                        comboProdutosDisponiveis.setSelectedIndex(i);
                        flag = true;
                        comboProdutosDisponiveis.repaint();
                    }
                }

                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "Não existe produto cadastrado com este ID!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ID incorreto!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addVinculacao() {
        
        Long id_produto = lista_produtos_disponiveis.get(comboProdutosDisponiveis.getSelectedIndex()).getId();
        boolean flag = false;
        
        for (int i = 0; i < lista_produtos_vinculados.size(); i++) {
            if (lista_produtos_vinculados.get(i).getId().equals(id_produto) == true) {
                flag = true;
            }            
        }
        
        if (flag == true) {
            JOptionPane.showMessageDialog(null, "Este produtos já está vinculado ao serviço!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            
            int result;
            ProdutoController cont = new ProdutoController();
            result = cont.addVinculacaoProdutoAServico(classe.getId(), id_produto);
            
            if (result == 1) {
                comboProdutosDisponiveis.setSelectedIndex(0);
                comboProdutosDisponiveis.repaint();
                refreshTable();
                JOptionPane.showMessageDialog(null, "Produto vinculado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao tentar vincular o produto ao serviço!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
            

        }
    }
    
    public void removeVinculacao() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente desvincular o produto "+lista_produtos_vinculados.get(tabela.getSelectedRow()).getNome()+" ao serviço "+this.classe.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            int result;
            ProdutoController cont = new ProdutoController();
            result = cont.removeVinculacaoProdutoAServico(classe.getId(), lista_produtos_vinculados.get(tabela.getSelectedRow()).getId());
            if (result == 1) {
                refreshTable();
                JOptionPane.showMessageDialog(null, "Produto desvinculado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao tentar desvincular o produto do serviço!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPesquisarProduto) {
            pesquisarProduto();
        } else if (e.getSource() == btnAdicionar) {
            addVinculacao();
        } else if (e.getSource() == btnRemover) {
            removeVinculacao();           
        } else if (e.getSource() == btnSair) {
            this.dispose();
        }
    }
    
}
