
package financeiro;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import formas_pagamento.Class_Formas_Pagto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Class_Receitas {
    
    public Class_Receitas() {
        
    }
    
    public void cadastraReceita(String descricao, int id_cliente, String nome_cliente, int id_setor, String forma_pagamento, 
            String valor, String acrescimo, String desconto, int numeroParcelas, int id_movimentacao_caixa, 
            int id_movimentacao_conta_bancaria, String data_pagamento, String data_vencimento, int liquidada, int agendada) { 
        int dias = 30;
        if (forma_pagamento.contains("Cartão")) {
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            dias = formas.retornaDiasCartao(forma_pagamento);
        }
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date data = sdf.parse(data_vencimento);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);

            Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
            float Valor = troca.trocaVirgulaPorPonto(valor);
            Valor = Valor / numeroParcelas;
            float Acrescimo = troca.trocaVirgulaPorPonto(acrescimo);
            float Desconto = troca.trocaVirgulaPorPonto(desconto);

            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("insert into receitas (descricao, id_cliente_fk, nome_cliente, "
                    + "id_setor, id_forma_pagamento, valor, acrescimo, desconto, qnt_parcelas, numero_parcela, "
                    + "id_movimentacao_caixa_fk, id_movimentacao_conta_bancaria_fk, liquidado, agendado, data_pagamento, data_vencimento) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for (int i = 1; i <= numeroParcelas; i++) {
                ps.setString(1, descricao);
                ps.setInt(2, id_cliente);
                ps.setString(3, nome_cliente);
                ps.setInt(4, id_setor);
                ps.setInt(5, id_forma_pagamento);
                ps.setFloat(6, Valor);
                ps.setFloat(7, Acrescimo);
                ps.setFloat(8, Desconto);
                ps.setInt(9, numeroParcelas);
                ps.setInt(10, i);
                ps.setInt(11, id_movimentacao_caixa);
                ps.setInt(12, id_movimentacao_conta_bancaria);
                ps.setInt(13, liquidada);
                ps.setInt(14, agendada);
                ps.setString(15, data_pagamento);
                ps.setString(16, data_vencimento);
                ps.executeUpdate();
                
                calendar.add(Calendar.DAY_OF_MONTH, dias);
                data_vencimento = sdf.format(calendar.getTime());
                data_pagamento = null;
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alteraReceitaPelaMovimentacaoCaixa(int id_movimentacao_caixa, String descricao, String forma_pagamento, 
            String valor) {
        
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "descricao = '"+descricao+"', "
                    + "id_forma_pagamento = '"+id_forma_pagamento+"', "
                    + "valor = '"+Valor+"' "
                    + "WHERE id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiReceitaPelaMovimentacaoCaixa(int id_movimentacao_caixa) {
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "excluido = 1 "
                    + "WHERE id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alteraReceitaPelaMovimentacaoContaBancaria(int id_movimentacao_conta_bancaria, String descricao, 
            String forma_pagamento, String valor) {
        
        Class_Formas_Pagto formas = new Class_Formas_Pagto();
        int id_forma_pagamento = formas.retornaIdFormaPagamento(forma_pagamento);
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "descricao = '"+descricao+"', "
                    + "id_forma_pagamento = '"+id_forma_pagamento+"', "
                    + "valor = '"+Valor+"' "
                    + "WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta_bancaria+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiReceitaPelaMovimentacaoContaBancaria(int id_movimentacao_conta_bancaria) {
        try {            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "excluido = 1 "
                    + "WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta_bancaria+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public float carregaReceitas(String situacao, String data_de, String data_ate, String descricao, String categoria, 
            DefaultTableModel tabela) {
        float valor_total = 0;
        
        String trechoLiquidado = "";
        if (situacao.equals("Liquidado")) {
            trechoLiquidado = " AND receitas.liquidado = 1 ";
        } else if (situacao.equals("Não liquidado")) {
            trechoLiquidado = " AND receitas.liquidado = 0 ";
        }
        
        String trechoCategoria = "", innerJoinSetor = "", selectSetor = "";
        if (!categoria.equals("Todos")) {
            trechoCategoria = " AND setores_financeiros.descricao = '"+categoria+"' ";
            innerJoinSetor = " INNER JOIN setores_financeiros ON receitas.id_setor = setores_financeiros.id_setor ";
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
            PreparedStatement ps = conn.prepareStatement("SELECT receitas.*, formas_pagamento.descricao "+selectSetor+" "
                    + "FROM receitas "
                    + "INNER JOIN formas_pagamento ON receitas.id_forma_pagamento = formas_pagamento.id_forma_pagamento "
                    + ""+innerJoinSetor+" "
                    + "WHERE receitas.excluido = 0 AND receitas.data_vencimento between '"+data_de+"' AND '"+data_ate+"' "
                    + "AND receitas.descricao LIKE '%"+descricao+"%' "+trechoCategoria+" "
                    + ""+trechoLiquidado+" ORDER BY receitas.data_vencimento");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                valorFloat = rs.getFloat("receitas.valor") + rs.getFloat("receitas.acrescimo") - rs.getFloat("receitas.desconto");
                valor_total = valor_total + valorFloat;
                valor = z.format(valorFloat);
                
                if (rs.getInt("receitas.liquidado") == 0) {
                    liquidado = "Não";
                } else {
                    liquidado = "Sim";
                }
                
                linha[0] = rs.getString("receitas.Descricao");
                linha[1] = rs.getString("formas_pagamento.descricao");
                linha[2] = valor;
                linha[3] = rs.getInt("receitas.qnt_parcelas");
                linha[4] = rs.getInt("receitas.numero_parcela");
                linha[5] = sdf.format(rs.getDate("receitas.data_vencimento"));
                linha[6] = liquidado;
                linha[7] = "Receita";
                linha[8] = rs.getInt("receitas.id_receita");
                tabela.addRow(linha);
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor_total;
    }
    
    public void liquidarReceitaPelaMovimentacaoCaixa(int id_movimentacao_caixa, int id_cliente, String nome_cliente, 
            int id_receita, String data_pagamento) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "id_movimentacao_caixa_fk = '"+id_movimentacao_caixa+"', "
                    + "id_cliente_fk = '"+id_cliente+"', "
                    + "nome_cliente = '"+nome_cliente+"', "
                    + "liquidado = 1, "
                    + "data_pagamento = '"+data_pagamento+"' WHERE id_receita = '"+id_receita+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void liquidarReceitaPelaMovimentacaoContaBancaria(int id_movimentacao_conta, int id_cliente, String nome_cliente, 
            int id_receita, String data_pagamento) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "id_movimentacao_caixa_fk = '"+id_movimentacao_conta+"', "
                    + "id_cliente_fk = '"+id_cliente+"', "
                    + "nome_cliente = '"+nome_cliente+"', "
                    + "liquidado = 1, "
                    + "data_pagamento = '"+data_pagamento+"' WHERE id_receita = '"+id_receita+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void desliquidarReceitaPelaMovimentacaoCaixa(int id_movimentacao_caixa) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
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
    
    public void desliquidarReceitaPelaMovimentacaoContaBancaria(int id_movimentacao_conta) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE receitas SET "
                    + "id_movimentacao_conta_bancaria = 0, "
                    + "liquidado = 0 "
                    + "data_pagamento = null, WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaReceitaAgendadaPelaMovimentacaoCaixa(int id_movimentacao_caixa) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT agendado FROM receitas "
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
    
    public boolean verificaReceitaAgendadaPelaMovimentacaoContaBancaria(int id_movimentacao_conta_bancaria) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT agendado FROM receitas "
                    + "WHERE id_movimentacao_conta_bancaria_fk = '"+id_movimentacao_conta_bancaria+"' AND agendado = 1");
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
    
    public Date retornaDataPagamentoReceita(int id_receita) {
        Date pagamento = null;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT data_pagamento FROM receitas "
                    + "WHERE id_receita = '"+id_receita+"'");
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
    
    public String retornaSetorReceita(int id_receita) {
        String setor = "";
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT setores_financeiros.descricao "
                    + "FROM setores_financeiros "
                    + "INNER JOIN receitas ON setores_financeiros.id_setor = receitas.id_setor "
                    + "WHERE receitas.id_receita = '"+id_receita+"'");
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
