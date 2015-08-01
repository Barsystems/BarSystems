/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.classe;

/**
 *
 * @author Marcos
 */
public class FuncionarioClasse {
    
    private Long id;
    private String nome;
    private String nascimento;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private String celular;
    private Long id_funcao;
    private String funcao;
    private float salario;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private Long id_pais;
    private String pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Long getId_funcao() {
        return id_funcao;
    }

    public void setId_funcao(Long id_funcao) {
        this.id_funcao = id_funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId_Pais() {
        return id_pais;
    }

    public void setId_Pais(Long id_pais) {
        this.id_pais = id_pais;
    }
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "FuncionarioClasse{" + "id=" + id + ", nome=" + nome + ", nascimento=" + nascimento + ", cpf=" + cpf + ", rg=" + rg + ", email=" + email + ", telefone=" + telefone + ", celular=" + celular + ", id_funcao=" + id_funcao + ", funcao=" + funcao + ", salario=" + salario + ", cep=" + cep + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", id_pais=" + id_pais + ", pais=" + pais + '}';
    }
    
}
