
package funcionarios;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Class_Funcionarios {
    
    ArrayList list = new ArrayList();
    
    public Class_Funcionarios() {
        
    }
    
    public ArrayList getListaIdFuncionario() {
        return list;
    }
    
    public DefaultListModel carregaFuncionario(String texto) {
        DefaultListModel list = new DefaultListModel();
        String sql = "SELECT id_funcionario, nome FROM funcionarios WHERE excluido = 0 AND nome LIKE '%"+texto+"%' ORDER BY nome";
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.list.add(rs.getString(1));
                list.addElement(rs.getString(2));
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void cadastrarFuncionario(String nome, String nascimento, String rg, String cpf, String email, String telefone, 
            String celular, int id_funcao, String cep, String endereco, String bairro, String cidade, String estado, String pais) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO funcionarios (nome, data_nascimento, rg, cpf, email, "
                    + "telefone, celular, id_funcao, cep, endereco, bairro, cidade, estado, pais) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nome);
            ps.setString(2, nascimento);
            ps.setString(3, rg);
            ps.setString(4, cpf);
            ps.setString(5, email);
            ps.setString(6, telefone);
            ps.setString(7, celular);
            ps.setInt(8, id_funcao);
            ps.setString(9, cep);
            ps.setString(10, endereco);
            ps.setString(11, bairro);
            ps.setString(12, cidade);
            ps.setString(13, estado);
            ps.setString(14, pais);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterarFuncionario(String nome, String nascimento, String rg, String cpf, String email, String telefone, 
            String celular, int id_funcao, String cep, String endereco, String bairro, String cidade, String estado, String pais,
            int id_funcionario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE funcionarios SET "
                    + "nome = '"+nome+"', "
                    + "data_nascimento = '"+nascimento+"', "
                    + "rg = '"+rg+"', "
                    + "cpf = '"+cpf+"', "
                    + "email = '"+email+"', "
                    + "telefone = '"+telefone+"', "
                    + "celular = '"+celular+"', "
                    + "id_funcao = '"+id_funcao+"', "
                    + "cep = '"+cep+"', "
                    + "endereco = '"+endereco+"', "
                    + "bairro = '"+bairro+"', "
                    + "cidade = '"+cidade+"', "
                    + "estado = '"+estado+"', "
                    + "pais = '"+pais+"' "
                    + "WHERE id_funcionario = '"+id_funcionario+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void excluirFuncionario(int codigo) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE funcionarios SET Excluido = 1 WHERE id_funcionario = '"+codigo+"'");
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList carregaDadosFuncionario(int id_funcionario) {
        ArrayList dados = new ArrayList();
        String sql = "SELECT funcionarios.*, funcoes.nome "
                + "FROM funcionarios "
                + "INNER JOIN funcoes ON funcionarios.id_funcao = funcoes.id_funcao "
                + "WHERE funcionarios.id_funcionario = '"+id_funcionario+"'";
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dados.add(rs.getString("funcionarios.nome"));
                dados.add(rs.getString("funcionarios.data_nascimento"));
                dados.add(rs.getString("funcionarios.rg"));
                dados.add(rs.getString("funcionarios.cpf"));
                dados.add(rs.getString("funcionarios.email"));
                dados.add(rs.getString("funcionarios.telefone"));
                dados.add(rs.getString("funcionarios.celular"));
                dados.add(rs.getString("funcoes.nome"));
                dados.add(rs.getString("funcionarios.cep"));
                dados.add(rs.getString("funcionarios.endereco"));
                dados.add(rs.getString("funcionarios.bairro"));
                dados.add(rs.getString("funcionarios.cidade"));
                dados.add(rs.getString("funcionarios.estado"));
                dados.add(rs.getString("funcionarios.pais"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dados;
    }
    
    public void carregaFuncoesComboBox(JComboBox combo) {
        String sql = "SELECT nome FROM funcoes WHERE excluido = 0";
        combo.removeAllItems();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int retornaIdFuncao(String funcao) {
        String sql = "SELECT id_funcao FROM funcoes WHERE nome = '"+funcao+"' AND excluido = 0";
        int id_funcao = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_funcao = rs.getInt(1);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_funcao;
    }
    
    public DefaultListModel refreshFuncoes() {
        DefaultListModel list = new DefaultListModel();
        String sql = "SELECT nome FROM funcoes WHERE excluido = 0 AND nome != 'Sem função'";
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.addElement(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean verificaFuncaoRepetida(String funcao) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("Select * from funcoes where Nome = '"+funcao+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
    
    public void cadastrarFuncao(String nomeFuncao) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO funcoes (nome) values (?)");
            ps.setString(1, nomeFuncao);
            ps.executeUpdate();

            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Função cadastrada com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterarFuncao(String nome_antigo, String novo_nome) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE funcoes SET "
                    + "nome = '"+novo_nome+"' "
                    + "WHERE nome = '"+nome_antigo+"' AND excluido = 0");
            ps.executeUpdate();

            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Função alterada com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaFuncaoPadrao(String funcao) {
        boolean Flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT Padrao FROM funcoes WHERE nome = '"+funcao+"' "
                    + "AND padrao = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Flag = true;
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Flag;
    }
    
    public boolean verificaFuncaoVinculadaAoFuncionario(int id_funcao) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT id_funcao FROM funcionarios WHERE id_funcao = '"+id_funcao+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
    
    public void excluirFuncao(String funcao) {

        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE funcoes SET "
                    + "excluido = 1 "
                    + "WHERE funcao = '"+funcao+"' AND excluido = 0");
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
