/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import centro_custo.classe.CentroCustoClasse;
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
import usuario.classe.UsuarioClasse;
import usuario.comboBox.UsuarioComboBoxCellRenderer;
import usuario.comboBox.UsuarioComboBoxModel;
import usuario.controller.UsuarioController;
import usuario.table.UsuarioTableModel;

/**
 *
 * @author Marcos
 */
public class CentroCustoGerenciarResponsavel extends JDialog implements ActionListener {
    
    private JPanel painel1, painel2, painel3;
    
    private JLabel lblTitulo, lblCentroCusto1, lblCentroCusto2, lblUsuariosCadastrados, lblResponsaveis;
    private JTable tabelaUsuariosResponsaveis;
    private JScrollPane scrollTabela;
    private JComboBox comboUsuariosCadastrados;
    private JButton btnPesquisarUsuario, btnAdicionar, btnRemover, btnSair;
    
    private Font fonteTitulo, fonteGeral, fonteNegrito;
    
    private CentroCustoClasse CentroCustoClasse;
    
    private List<UsuarioClasse> lista_usuario;
    private List<UsuarioClasse> lista_usuario_responsavel;
    
    public CentroCustoGerenciarResponsavel(CentroCustoClasse CentroCstoClasse) {
        
        this.CentroCustoClasse = CentroCstoClasse;
        
        setTitle("Gerenciar responsáveis");
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
        lblTitulo.setText("GERENCIAR RESPONSÁVEIS");
        lblTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setBounds(0, 0, 750, 60);
        
        painel1.add(lblTitulo);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBackground(Color.WHITE);
        painel2.setBounds(0, 60, 750, 360);
        
        lblCentroCusto1 = new JLabel("Centro de custo");
        lblCentroCusto1.setFont(fonteGeral);
        lblCentroCusto1.setBounds(30, 30, 100, 30);
        
        lblCentroCusto2 = new JLabel(this.CentroCustoClasse.getNome());
        lblCentroCusto2.setFont(fonteNegrito);
        lblCentroCusto2.setForeground(Color.red);
        lblCentroCusto2.setBounds(150, 30, 300, 30);
        
        lblResponsaveis = new JLabel("Responsáveis");
        lblResponsaveis.setFont(fonteGeral);
        lblResponsaveis.setBounds(30, 70, 140, 30);
        
        tabelaUsuariosResponsaveis = new JTable();
        tabelaUsuariosResponsaveis.setFont(fonteGeral);
        tabelaUsuariosResponsaveis.setRowHeight(25);
        tabelaUsuariosResponsaveis.getTableHeader().setReorderingAllowed(false);
        tabelaUsuariosResponsaveis.getTableHeader().setResizingAllowed(false);
        refreshTable();
        
        scrollTabela = new JScrollPane(tabelaUsuariosResponsaveis);
        scrollTabela.setBounds(150, 70, 500, 200);
        
        lblUsuariosCadastrados = new JLabel("Usuários");
        lblUsuariosCadastrados.setFont(fonteGeral);
        lblUsuariosCadastrados.setBounds(30, 290, 100, 30);
        
        comboUsuariosCadastrados = new JComboBox();
        comboUsuariosCadastrados.setFont(fonteGeral);
        comboUsuariosCadastrados.setBounds(150, 290, 250, 30);
        
        lista_usuario = new UsuarioController().findUsuario("");
        comboUsuariosCadastrados.setModel(new UsuarioComboBoxModel(lista_usuario));
        comboUsuariosCadastrados.setRenderer(new UsuarioComboBoxCellRenderer());
        
        btnPesquisarUsuario = new JButton(new ImageIcon(getClass().getResource("/imagens/Pesquisar 16px.png")));
        btnPesquisarUsuario.setBounds(410, 290, 40, 30);
        btnPesquisarUsuario.addActionListener(this);
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(fonteGeral);
        btnAdicionar.setBounds(460, 290, 90, 30);
        btnAdicionar.addActionListener(this);
        
        btnRemover = new JButton("Remover");
        btnRemover.setFont(fonteGeral);
        btnRemover.setBounds(560, 290, 90, 30);
        btnRemover.addActionListener(this);
        
        painel2.add(lblCentroCusto1);
        painel2.add(lblCentroCusto2);
        painel2.add(lblUsuariosCadastrados);
        painel2.add(comboUsuariosCadastrados);
        painel2.add(btnPesquisarUsuario);
        painel2.add(btnAdicionar);
        painel2.add(btnRemover);
        painel2.add(lblResponsaveis);
        painel2.add(scrollTabela);
        
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
        lista_usuario_responsavel = new UsuarioController().findUsuarioResponsavelCentroCusto(this.CentroCustoClasse.getId());
        if (lista_usuario_responsavel != null) {
            tabelaUsuariosResponsaveis.setModel(new UsuarioTableModel(lista_usuario_responsavel, 2));
            if (tabelaUsuariosResponsaveis.getRowCount() > 0) {
                tabelaUsuariosResponsaveis.setRowSelectionInterval(0, 0);
                tabelaUsuariosResponsaveis.scrollRectToVisible(new Rectangle(tabelaUsuariosResponsaveis.getCellRect(0, 0, true)));
            }
        }
    }
    
    public void pesquisarResponsavel() {
        String cod = JOptionPane.showInputDialog(null, "Digite o ID do usuário para selecioná-lo", "PESQUISAR USUÁRIO", JOptionPane.PLAIN_MESSAGE);
        Long id = null;
        boolean flag = false;
        try {
            if (cod != null) {
                id = Long.parseLong(cod);

                for (int i = 0; i < lista_usuario.size(); i++) {
                    if (lista_usuario.get(i).getId().equals(id)) {
                        comboUsuariosCadastrados.setSelectedIndex(i);
                        flag = true;
                        comboUsuariosCadastrados.repaint();
                    }
                }

                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "Não existe usuário cadastrado com este ID!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ID incorreto!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addResponsavel() {
        
        Long id_usuario = lista_usuario.get(comboUsuariosCadastrados.getSelectedIndex()).getId();
        boolean flag = false;
        
        for (int i = 0; i < lista_usuario_responsavel.size(); i++) {
            if (lista_usuario_responsavel.get(i).getId().equals(id_usuario) == true) {
                flag = true;
            }            
        }
        
        if (flag == true) {
            JOptionPane.showMessageDialog(null, "Este usuário já é responsável por este centro de custo!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            
            int result;
            UsuarioController cont = new UsuarioController();
            result = cont.addResponsavelCentroCusto(CentroCustoClasse.getId(), id_usuario);
            
            if (result == 1) {
                comboUsuariosCadastrados.setSelectedIndex(0);
                comboUsuariosCadastrados.repaint();
                refreshTable();
                JOptionPane.showMessageDialog(null, "Usuário vinculado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao tentar vincular o usuário ao centro de custo!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
            

        }
    }
    
    public void removeResponsavel() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente desvincular o usuário "+lista_usuario_responsavel.get(tabelaUsuariosResponsaveis.getSelectedRow()).getNome()+" ao centro de custo "+this.CentroCustoClasse.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            int result;
            UsuarioController cont = new UsuarioController();
            result = cont.removeResponsavelCentroCusto(CentroCustoClasse.getId(), lista_usuario_responsavel.get(tabelaUsuariosResponsaveis.getSelectedRow()).getId());
            if (result == 1) {
                refreshTable();
                JOptionPane.showMessageDialog(null, "Usuário desvinculado com sucesso!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao tentar desvincular o usuário ao centro de custo!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPesquisarUsuario) {
            pesquisarResponsavel();
        } else if (e.getSource() == btnAdicionar) {
            addResponsavel();
        } else if (e.getSource() == btnRemover) {
            removeResponsavel();           
        } else if (e.getSource() == btnSair) {
            this.dispose();
        }
    }
    
}
