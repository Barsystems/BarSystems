/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.dao;

import java.util.List;
import pais.classe.PaisClasse;

/**
 *
 * @author Marcos
 */
public interface IPaisDAO {
    
    List<PaisClasse> findAll();
    
}
