/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.classe;

/**
 *
 * @author Marcos
 */
public class FornecedorClasse {
    
    private Long id;
    private String razao_social;
    private String nome_fantasia;
    private String cnpj;
    private String telefone;
    private String email;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private Long id_pais;
    private String pais;
    private String inscricao_estadual;
    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "FornecedorClasse{" + "id=" + id + ", razao_social=" + razao_social + ", nome_fantasia=" + nome_fantasia + ", cnpj=" + cnpj + ", telefone=" + telefone + ", email=" + email + ", cep=" + cep + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", id_pais=" + id_pais + ", pais=" + pais + ", inscricao_estadual=" + inscricao_estadual + ", observacao=" + observacao + '}';
    }
    
}
