/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.classe;

/**
 *
 * @author Marcos
 */
public class CentroCustoClasse {
    
    private Long id;
    private String nome;
    private String tipo;
    private float saldo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "CentroCustoClasse{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", saldo=" + saldo + '}';
    }
    
}
