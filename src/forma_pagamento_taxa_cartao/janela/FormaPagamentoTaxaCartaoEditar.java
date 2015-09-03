/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.janela;

import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTaxaCartaoEditar {
    
    private JPanel painel1, painel2, painel3;
    private JLabel lblTitulo, lblFormaPagamento, lblMaquinaCartao, lblTaxa;
    private JTextField txtTaxa;
    private JComboBox comboFormaPagamento, comboMaquinaCartao;
    private JButton btnSalvar, btnVoltar;
    
    private Font fonteTitulo, fonteGeral;
    
    private List<FormaPagamentoClasse> lista_formas_pagto;
    private List<FormaPagamentoMaquinaCartaoClasse> lista_maquinas;
    
    public boolean editou = false;
    
    public FormaPagamentoTaxaCartaoEditar (FormaPagamentoTaxaCartaoClasse classe) {
        
    }
    
}
