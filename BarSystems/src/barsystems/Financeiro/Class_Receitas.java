
package barsystems.Financeiro;

import barsystems.Class_Troca_Virgula_Por_Ponto;
import barsystems.conexaoBanco.Class_Conexao_Banco;
import barsystems.formasPagamento.Class_Formas_Pagto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Class_Receitas {
    
    public Class_Receitas() {
        
    }
    
    public void cadastraReceita(String descricao, int id_cliente, String nome_cliente, String setor, String forma_pagamento, 
            String valor, String acrescimo, String desconto, int numeroParcelas, int id_movimentacao_caixa, 
            String data_pagamento, String data_vencimento) {
        int liquidada = 1;
        int dias = 0;
        if (forma_pagamento.contains("Cartão")) {
            Class_Formas_Pagto formas = new Class_Formas_Pagto();
            dias = formas.retornaDiasCartao(forma_pagamento);
        }
        Class_Setores_Financeiros setores = new Class_Setores_Financeiros();
        int id_setor = setores.retornaIdSetorFinanceiro(setor);
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
                    + "id_movimentacao_caixa_fk, liquidado, data_pagamento, data_vencimento) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
                ps.setInt(12, liquidada);
                ps.setString(13, data_pagamento);
                ps.setString(14, data_vencimento);
                ps.executeUpdate();
                
                calendar.add(Calendar.DAY_OF_MONTH, dias);
                data_vencimento = sdf.format(calendar.getTime());

                if (!forma_pagamento.contains("Cartão")) {
                    liquidada = 0;
                }
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
