
package financeiro;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import formas_pagamento.Class_Formas_Pagto;
import fornecedores.Class_Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Class_Despesas {
    
    public Class_Despesas() {
        
    }
    
    public void cadastraDespesa(int id_compra, String descricao, String responsavel, String fornecedor, int qnt_parcelas, 
            String data_pagamento, String data_vencimento, String forma_pagamento, String valor, String acrescimo, 
            String desconto, int fixo, int variavel, String setor, int id_usuario, int id_movimentacao_caixa, 
            int id_movimentacao_conta_bancaria, int liquidado, int agendado) {
        try {
            int dias = 30;
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            if (forma_pagamento.contains("Cartão")) {
                dias = formas.retornaDiasCartao(forma_pagamento);
            }
            
            Class_Fornecedores forn = new Class_Fornecedores();
            int id_fornecedor = forn.retornaIdFornecedor(fornecedor);
            
            int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
            
            Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
            int id_setor = setores.retornaIdSetorFinanceiro(setor);

            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            float valorDespesa = troca.trocaVirgulaPorPonto(valor);
            valorDespesa = valorDespesa / qnt_parcelas;
            float Acrescimo = troca.trocaVirgulaPorPonto(acrescimo);
            float Desconto = troca.trocaVirgulaPorPonto(desconto);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date data = sdf.parse(data_vencimento);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);

            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("insert into despesas "
                    + "(id_compra_fk, descricao, responsavel, id_fornecedor_fk, qnt_parcelas, numero_parcela, data_pagamento, "
                    + "data_vencimento, id_forma_pagamento_fk, valor, acrescimo, desconto, fixo, variavel, liquidado, agendado,"
                    + " id_setor_fk, id_usuario_fk, id_movimentacao_caixa_fk, id_movimentacao_conta_bancaria_fk) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (int i = 1; i <= qnt_parcelas; i++) {
                ps.setInt(1, id_compra);
                ps.setString(2, descricao);
                ps.setString(3, responsavel);
                ps.setInt(4, id_fornecedor);
                ps.setInt(5, qnt_parcelas);
                ps.setInt(6, i);
                ps.setString(7, data_pagamento);
                ps.setString(8, data_vencimento);
                ps.setInt(9, id_forma_pagamento);
                ps.setFloat(10, valorDespesa);
                ps.setFloat(11, Acrescimo);
                ps.setFloat(12, Desconto);
                ps.setInt(13, fixo);
                ps.setInt(14, variavel);
                ps.setInt(15, liquidado);
                ps.setInt(16, agendado);
                ps.setInt(17, id_setor);
                ps.setInt(18, id_usuario);
                ps.setInt(19, id_movimentacao_caixa);
                ps.setInt(20, id_movimentacao_conta_bancaria);
                ps.executeUpdate();

                calendar.add(Calendar.DAY_OF_MONTH, dias);
                data_vencimento = sdf.format(calendar.getTime());
                data_pagamento = null;
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void alteraDespesaPelaMovimentacaoCaixa(int id_movimentacao_caixa, String descricao, String forma_pagamento, 
            String valor) {
        
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "descricao = '"+descricao+"', "
                    + "id_forma_pagamento_fk = '"+id_forma_pagamento+"', "
                    + "valor = '"+Valor+"' "
                    + "WHERE id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiDespesaPelaMovimentacaoCaixa(int id_movimentacao_caixa) {
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "excluido = 1 "
                    + "WHERE id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alteraDespesaPelaMovimentacaoContaBancaria(int id_movimentacao_conta_bancaria, String descricao, 
            String forma_pagamento, String valor) {
        
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "descricao = '"+descricao+"', "
                    + "id_forma_pagamento_fk = '"+id_forma_pagamento+"', "
                    + "valor = '"+Valor+"' "
                    + "WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta_bancaria+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiDespesaPelaMovimentacaoContaBancaria(int id_movimentacao_conta_bancaria) {
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "excluido = 1 "
                    + "WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta_bancaria+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public float carregaDespesas(String situacao, String data_de, String data_ate, String descricao, String categoria, 
            DefaultTableModel tabela) {
        float valor_despesas = 0;
        
        String trechoLiquidado = "";
        if (situacao.equals("Liquidado")) {
            trechoLiquidado = " AND despesas.liquidado = 1";
        } else if (situacao.equals("Não liquidado")) {
            trechoLiquidado = " AND despesas.liquidado = 0";
        }
        
        String trechoCategoria = "", innerJoinSetor = "", selectSetor = "";
        if (!categoria.equals("Todos")) {
            trechoCategoria = " AND setores_financeiros.descricao = '"+categoria+"' ";
            innerJoinSetor = " INNER JOIN setores_financeiros ON despesas.id_setor_fk = setores_financeiros.id_setor ";
            selectSetor = " , setores_financeiros.descricao ";
        }
        
        Object linha[] = new Object[9];
        float valorFloat = 0;
        String valor, liquidado;
        NumberFormat z = NumberFormat.getCurrencyInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT despesas.*, formas_pagamento.descricao "+selectSetor+" "
                    + "FROM despesas "
                    + "INNER JOIN formas_pagamento ON despesas.id_forma_pagamento_fk = formas_pagamento.id_forma_pagamento "
                    + ""+innerJoinSetor+" "
                    + "WHERE despesas.excluido = 0 AND despesas.data_vencimento between '"+data_de+"' AND '"+data_ate+"' "
                    + "AND despesas.descricao LIKE '%"+descricao+"%' "+trechoCategoria+" "
                    + ""+trechoLiquidado+" ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                valorFloat = rs.getFloat("despesas.valor") + rs.getFloat("despesas.acrescimo") - rs.getFloat("despesas.desconto");
                valor_despesas = valor_despesas + valorFloat;
                valor = z.format(valorFloat);
                
                if (rs.getInt("despesas.liquidado") == 0) {
                    liquidado = "Não";
                } else {
                    liquidado = "Sim";
                }
                
                linha[0] = rs.getString("despesas.Descricao");
                linha[1] = rs.getString("formas_pagamento.descricao");
                linha[2] = valor;
                linha[3] = rs.getInt("despesas.qnt_parcelas");
                linha[4] = rs.getInt("despesas.numero_parcela");
                linha[5] = sdf.format(rs.getDate("despesas.data_vencimento"));
                linha[6] = liquidado;
                linha[7] = "Despesa";
                linha[8] = rs.getInt("despesas.id_despesa");
                tabela.addRow(linha);
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor_despesas;
    }
    
    public void liquidarDespesaPelaMovimentacaoCaixa(int id_movimentacao_caixa, int id_fornecedor, int id_despesa, 
            String data_pagamento) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"', "
                    + "id_cliente = '"+id_fornecedor+"', "
                    + "liquidado = 1, "
                    + "data_pagamento = '"+data_pagamento+"' WHERE id_despesa = '"+id_despesa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void liquidarDespesaPelaMovimentacaoContaBancaria(int id_movimentacao_conta, int id_fornecedor, int id_despesa, 
            String data_pagamento) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta+"', "
                    + "id_fornecedor_fk = '"+id_fornecedor+"', "
                    + "liquidado = 1, "
                    + "data_pagamento = '"+data_pagamento+"' WHERE id_despesa = '"+id_despesa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaDespesaAgendadaPelaMovimentacaoCaixa(int id_movimentacao_caixa) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT agendado FROM despesas "
                    + "WHERE id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"' AND agendado = 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public void desliquidarDespesaPelaMovimentacaoCaixa(int id_movimentacao_caixa) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "id_movimentacao_caixa_fk = 0, "
                    + "liquidado = 0, "
                    + "data_pagamento = null WHERE id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void desliquidarDespesaPelaMovimentacaoContaBancaria(int id_movimentacao_conta) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE despesas SET "
                    + "id_movimentacao_conta_bancaria_fk = 0, "
                    + "liquidado = 0, "
                    + "data_pagamento = null WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Date retornaDataPagamentoDespesa(int id_despesa) {
        Date pagamento = null;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT data_pagamento FROM despesas "
                    + "WHERE id_despesa = '"+id_despesa+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pagamento = rs.getDate(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pagamento;
    }
    
    public String retornaSetorDespesa(int id_despesa) {
        String setor = "";
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT setores_financeiros.descricao "
                    + "FROM setores_financeiros "
                    + "INNER JOIN despesas ON setores_financeiros.id_setor = despesas.id_setor_fk "
                    + "WHERE despesas.id_despesa = '"+id_despesa+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                setor = rs.getString(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setor;
    }
}
