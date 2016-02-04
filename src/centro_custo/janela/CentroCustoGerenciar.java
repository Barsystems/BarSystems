/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.janela;

import caixa.janela.CaixaJanela;
import centro_custo.classe.CentroCustoClasse;
import centro_custo.controller.CentroCustoController;
import conta_bancaria.janela.ContaBancariaJanela;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class CentroCustoGerenciar extends JPanel implements ChangeListener {
    
    private Font fonteGeral;
    
    private JTabbedPane painel_centros;
    
    private List<CentroCustoClasse> lista_centros;
    
    private List<CaixaJanela> lista_caixa;
    private List<ContaBancariaJanela> lista_conta;
    
    private UsuarioClasse user;
    
    public CentroCustoGerenciar(UsuarioClasse user, int width, int height) {
        
        this.user = user;
        
        setLayout(null);
        
        fonteGeral = new Font("Tahoma", Font.PLAIN, 12);
        
        CentroCustoController cont = new CentroCustoController();
        lista_centros = cont.findCentroCustoDoResponsavel(this.user.getId());
        
        painel_centros = new JTabbedPane();
        painel_centros.setFont(fonteGeral);
        painel_centros.setBounds(0, 0, width, height);
        painel_centros.addChangeListener(this);
        
        lista_caixa = new ArrayList<CaixaJanela>();
        lista_conta = new ArrayList<ContaBancariaJanela>();
        
        if (lista_centros.isEmpty()) {
            JLabel lblMensagem = new JLabel("Este usuário não é responsável por nenhum centro de custo atualmente!");
            lblMensagem.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblMensagem.setForeground(Color.red);
            lblMensagem.setBounds(30, 20, 450, 30);
            add(lblMensagem);
        } else {
            
            for (int i = 0; i < lista_centros.size(); i++) {
                if (lista_centros.get(i).getTipo().equals("Caixa")) {
                    CaixaJanela caixa = new CaixaJanela(this.user, width, lista_centros.get(i));
                    lista_caixa.add(caixa);
                    painel_centros.addTab(lista_centros.get(i).getNome(), null, caixa);
                } else {
                    ContaBancariaJanela conta = new ContaBancariaJanela(this.user, width, lista_centros.get(i));
                    lista_conta.add(conta);
                    painel_centros.addTab(lista_centros.get(i).getNome(), null, conta);
                }
            }
            add(painel_centros); 
        }
    }
    
    public void carregaMovimentacaoCentroCusto() {
        int index = painel_centros.getSelectedIndex();
        if (lista_centros.get(index).getTipo().equals("Caixa")) {
            lista_caixa.get(index).refreshTable();
        } else {
            //lista_conta.get(index - lista_caixa.size()).refreshTable();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == painel_centros) {
            carregaMovimentacaoCentroCusto();
        }
    }
    
}
