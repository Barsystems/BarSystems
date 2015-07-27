/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.facade;

import java.util.List;
import pais.classe.PaisClasse;
import pais.dao.IPaisDAO;
import pais.dao.PaisDAO;

/**
 *
 * @author Marcos
 */
public class PaisFacade {
    
    IPaisDAO dao;
    
    public PaisFacade() {
        dao = new PaisDAO();
    }
    
    public List<PaisClasse> findAll() {
        return dao.findAll();
    }
    
}
