
package barsystems;

public class Class_Consumir_Letras {
    
    public Class_Consumir_Letras() {
        
    }
    
    public void consome(String caracteres, java.awt.event.KeyEvent evt) {
        
        if (!caracteres.contains(evt.getKeyChar()+"")) {
            evt.consume();
        }
    }
    
}
