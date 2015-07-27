/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.controller;

import java.util.List;
import pais.classe.PaisClasse;
import pais.facade.PaisFacade;

/**
 *
 * @author Marcos
 */
public class PaisController {
    
    PaisFacade facade;
    
    public PaisController() {
        facade = new PaisFacade();
    }
    
    public List<PaisClasse> findPaises() {
        return facade.findAll();
    }
    
}
