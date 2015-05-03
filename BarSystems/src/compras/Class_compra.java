
package compras;

import centros_custo.Class_Caixa;
import centros_custo.Class_Conta_Bancaria;
import conexao_banco.Class_Conexao_Banco;
import estoque.Class_estoque;
import financeiro.Class_Despesas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import principal.Class_Manipular_Data;
import principal.Class_Troca_Virgula_Por_Ponto;
import produtos.Class_produtos;

public class Class_compra {
    private String id_compra;
    private String descricao;
    private String fornecedor;
    private String numero_nota;
    private String data;
    private String responsavel;
    private String valor;
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    
    /**
     * Carrega tabela com dados passados
     * @param tabela 
     */
    public void carregaTabela(DefaultTableModel tabela){
        Object linha[] = new Object[3];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tabela.setRowCount(0);       
        try {
            String sql = "SELECT id_compra, descricao, data FROM compras WHERE excluido = 0";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement stmt = conn.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();  
            while(rs.next())
            {
                linha[0] = rs.getInt(1);
                linha[1] = rs.getString(2);
                linha[2] = sdf.format(rs.getDate(3));
                tabela.addRow(linha);
            }
            rs.close();  
            stmt.close();
            conn.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }// FIM CARREGA TABELA   
    
    public void carregaDados(int id_compra) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            NumberFormat n = NumberFormat.getCurrencyInstance();
            
            String sql = "SELECT compras.id_compra, compras.descricao, fornecedores.nome_fantasia, compras.numero_nota, "
                    + "compras.data, compras.responsavel, compras.valor "
                    + "FROM compras "
                    + "INNER JOIN fornecedores ON compras.id_fornecedor = fornecedores.id_fornecedor "
                    + "WHERE id_compra = '"+id_compra+"' AND compras.excluido = 0";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement stmt = conn.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();  
            while(rs.next())
            {
                this.id_compra = rs.getString(1);
                this.descricao = rs.getString(2);
                this.fornecedor = rs.getString(3);
                this.numero_nota = rs.getString(4);
                this.data = sdf.format(rs.getDate(5));
                this.responsavel = rs.getString(6);
                this.valor = n.format(rs.getFloat(7));
            }
            rs.close();  
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void carregaProdutos_Compra(int id_compra, DefaultTableModel tabela) {
        Object linha[] = new Object[2];
        tabela.setRowCount(0);       
        try {
            String sql = "SELECT produtos.descricao, produtos_compra.quantidade_em_unidade "
                    + "FROM produtos "
                    + "INNER JOIN produtos_compra ON produtos.id_produto = produtos_compra.id_produtos "
                    + "WHERE id_compras_fk = '"+id_compra+"'";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement stmt = conn.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();  
            while(rs.next())
            { 
                linha[0] = rs.getString(1);
                linha[1] = rs.getString(2);
                tabela.addRow(linha);
            }
            rs.close();  
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
      
    public int getIdUltimaCompra() {
        int id_compra = 0;
         try{
            String sql = "SELECT MAX(id_compra) FROM compras WHERE excluido = 0";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement stmt = conn.prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                id_compra = rs.getInt(1);
            }
            
            rs.close();  
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return id_compra;
    }
    
    public void cadastraCompra( String descricao, int id_fornecedor, String numero_nota, String data, String responsavel,
                                float valor, int parcelas, int id_forma_pagamento) {
        
        String sql = "INSERT INTO compras "
                + "(descricao, id_fornecedor, numero_nota, data, responsavel, valor, parcelas, id_forma_pagamento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";    
    
            try {    
                Class_Conexao_Banco banco = new Class_Conexao_Banco();
                Connection conn = banco.getConexaoMySQL();
                PreparedStatement ps = conn.prepareStatement(sql);    
                ps.setString(1, descricao);
                ps.setInt(2, id_fornecedor);    
                ps.setString(3, numero_nota);    
                ps.setString(4, data);
                ps.setString(5, responsavel);
                ps.setFloat(6, valor);
                ps.setInt(7, parcelas);
                ps.setInt(8, id_forma_pagamento);
                ps.executeUpdate();
                
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public void cadastraPagamentosCompra(JTable tabela_parcelas, int id_compra, String descricao, String responsavel, 
            int id_fornecedor, String data_compra, int qnt_parcelas, int id_forma_pagamento, int id_setor, int id_usuario, 
            Class_Caixa caixa, int id_caixa, String tipo_centro_custo, int id_centro_custo) {
        try {        
            String sql = "INSERT INTO pagamentos_compra (id_compra, numero_parcela, valor, data_pagamento, liquidado) VALUES (?, ?, ?, ?, ?)";
            Class_Manipular_Data man = new Class_Manipular_Data();
            DefaultTableModel modelo = (DefaultTableModel) tabela_parcelas.getModel();
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            Class_Despesas despesas = new Class_Despesas();
            
            String data_pagamento, data_vencimento, Liq;
            int numero_parcela, liquidado, id_movimentacao_caixa = 0, id_movimentacao_conta_bancaria = 0;
            float valor_parcela = 0;
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            for(int i = 0; i < tabela_parcelas.getRowCount(); i++) {
                
                Liq = modelo.getValueAt(i, 2).toString();
                if (Liq.equals("true")) {
                    liquidado = 1;
                } else {
                    liquidado = 0;
                }
                
                data_vencimento = man.retornaDataFormatoMySQLSemHora(modelo.getValueAt(i, 3).toString());
                numero_parcela = (int) modelo.getValueAt(i, 0);
                valor_parcela = troca.trocaVirgulaPorPonto(modelo.getValueAt(i, 1).toString().replace("R$ ", ""));
                
                if (liquidado == 1) {
                    if (tipo_centro_custo.equals("Caixa")) {
                        caixa.registraMovimentacaoCaixa(id_caixa, "Pagto da parcela "+numero_parcela+" da compra "+descricao, id_forma_pagamento, qnt_parcelas, valor_parcela, 
                                "Despesa", id_usuario, data_compra);
                        id_movimentacao_caixa = caixa.getIdUltimaMovimentacaoCaixa();
                    } else {
                        Class_Conta_Bancaria conta = new Class_Conta_Bancaria();
                        conta.registraMovimentacaoContaBancaria(id_centro_custo, "Pagto da parcela "+numero_parcela+" da compra "+descricao, id_forma_pagamento, qnt_parcelas, 
                                valor_parcela, sql, id_usuario, data_compra);
                        id_movimentacao_conta_bancaria = conta.getIdUltimaMovimentacaoContaBancaria();
                    }
                    data_pagamento = data_compra;
                } else {
                    data_pagamento = null;
                    id_movimentacao_caixa = 0;
                    id_movimentacao_conta_bancaria = 0;
                }
                
                ps.setInt(1, id_compra);
                ps.setInt(2, numero_parcela);    
                ps.setFloat(3, valor_parcela);    
                ps.setString(4, data_pagamento);
                ps.setInt(5, liquidado);
                ps.executeUpdate();
                
                despesas.cadastraDespesaParaCompra(id_compra, "Parcela de número "+i+1+" da compra "+descricao, responsavel, 
                        id_fornecedor, qnt_parcelas, numero_parcela, data_pagamento, data_vencimento+" 00:00:00", 
                        id_forma_pagamento, valor_parcela, 0, 0, 0, 1, id_setor, id_usuario, id_movimentacao_caixa, 
                        id_movimentacao_conta_bancaria, liquidado, 0);
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cadastraProdutosCompra(JTable tabela_produtos, int id_compra) {
        try {
            String sql = "INSERT INTO produtos_compra "
                    + "(id_produtos, valor_compra, quantidade_em_unidade, id_centro_estoque, id_compras_fk) "
                    + "VALUES (?, ?, ?, ?, ?)";
            
            Class_produtos produtos = new Class_produtos();
            Class_estoque estoque = new Class_estoque();
            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            
            int id_produto, quantidade;
            float valor;
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < tabela_produtos.getRowCount(); i++) {
                
                id_produto = produtos.retornaIdProduto((String) tabela_produtos.getValueAt(i, 0));
                quantidade = (int) tabela_produtos.getValueAt(i, 1);
                valor = troca.trocaVirgulaPorPonto(tabela_produtos.getValueAt(i, 3).toString().replace("R$", ""));
                
                ps.setInt(1, id_produto);
                ps.setFloat(2, valor);    
                ps.setInt(3, quantidade);    
                ps.setInt(4, estoque.retornaIdCentroEstoque((String) tabela_produtos.getValueAt(i, 4)));
                ps.setInt(5, id_compra);
                ps.executeUpdate();
                
                //AINDA PRECISO ATUALIZAR O VALOR DE COMPRA DO PRODUTO CASO ESTE VALOR SEJA MAIOR QUE O ANTERIOR
                produtos.AtualizaValorCompraProduto(id_produto, (valor / quantidade));
            }
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the id_compra
     */
    public String getId_compra() {
        return id_compra;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @return the numero_nota
     */
    public String getNumero_nota() {
        return numero_nota;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the responsavel
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }    
}
