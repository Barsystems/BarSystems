
package clientes;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Class_Clientes {
    
    protected String nome, nascimento, rg, cpf, telefone, celular, cep, endereco, bairro, cidade, estado, pais, observacoes;
    protected ArrayList arrayList = new ArrayList(); 
    
    public Class_Clientes() {
        
    }

    public String getNome() {
        return nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCelular() {
        return celular;
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getObservacoes() {
        return observacoes;
    }    
    
    public ArrayList getIdClientes() {
        return this.arrayList;
    }
    
    public void carregaClientesComboBox() {
        
    }
    
    public DefaultListModel refreshList() {
        DefaultListModel list = new DefaultListModel();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT id_cliente, nome FROM clientes WHERE excluido = 0 ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arrayList.add(rs.getString(1));
                list.addElement(rs.getString(2));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void cadastrarCliente(String nome, String nascimento, String rg, String cpf, String telefone, String celular, 
            String cep, String endereco, String bairro, String cidade, String estado, String pais, String observacoes) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO clientes (nome, data_nascimento, rg, cpf, telefone, celular, "
                    + "cep, endereco, bairro, cidade, estado, pais, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nome);
            ps.setString(2, nascimento);
            ps.setString(3, rg);
            ps.setString(4, cpf);
            ps.setString(5, telefone);
            ps.setString(6, celular);
            ps.setString(7, cep);
            ps.setString(8, endereco);
            ps.setString(9, bairro);
            ps.setString(10, cidade);
            ps.setString(11, estado);
            ps.setString(12, pais);
            ps.setString(13, observacoes);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refreshCliente(int id_cliente) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM clientes WHERE excluido = 0 "
                    + "AND id_cliente = '"+id_cliente+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.nome = rs.getString("nome");
                this.nascimento = rs.getString("data_nascimento");
                this.rg = rs.getString("rg");
                this.cpf = rs.getString("cpf");
                this.telefone = rs.getString("telefone");
                this.celular = rs.getString("celular");
                this.cep = rs.getString("cep");
                this.endereco = rs.getString("endereco");
                this.bairro = rs.getString("bairro");
                this.cidade = rs.getString("cidade");
                this.estado = rs.getString("estado");
                this.pais = rs.getString("pais");
                this.observacoes = rs.getString("observacoes");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editarCliente(String nome, String nascimento, String rg, String cpf, String telefone, String celular, 
            String cep, String endereco, String bairro, String cidade, String estado, String pais, String observacoes, 
            int id_cliente) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE clientes SET "
                    + "nome = '"+nome+"', "
                    + "data_nascimento = '"+nascimento+"', "
                    + "rg = '"+rg+"', "
                    + "cpf = '"+cpf+"', "
                    + "telefone = '"+telefone+"', "
                    + "celular = '"+celular+"', "
                    + "cep = '"+cep+"', "
                    + "endereco = '"+endereco+"', "
                    + "bairro = '"+bairro+"', "
                    + "cidade = '"+cidade+"', "
                    + "estado = '"+estado+"', "
                    + "pais = '"+pais+"', "
                    + "observacoes = '"+observacoes+"' "
                    + "WHERE id_cliente = '"+id_cliente+"'");
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiCliente(int id_cliente) {
        String sql = "UPDATE clientes SET excluido = 1 WHERE id_cliente = '"+id_cliente+"'";
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
