/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.classe;

/**
 *
 * @author Marcos
 */
public class PaisClasse {
    
    private Long id;
    private String pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "PaisClasse{" + "id=" + id + ", pais=" + pais + '}';
    }
    
}
