/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Marcos
 */
public class FormataImagem {
    
    public Image FormataImagem(BufferedImage img, int preferred_width, int preferred_height) {
        Image imagemMenor = img.getScaledInstance(preferred_width, preferred_height, 0);
        return imagemMenor;
    }
    
}
