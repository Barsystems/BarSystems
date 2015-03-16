
package barsystems;

public class Class_Consumir_Letras {
    
    public Class_Consumir_Letras() {
        
    }
    
    public void consome(String caracteres, java.awt.event.KeyEvent evt) {
        
        if (!caracteres.contains(evt.getKeyChar()+"")) {
            evt.consume();
        }
    }
    
    public String retiraLetras(String valor) {
        String valorAtualizado = "";
        String caracteres = "1234567890,.";
        for (int i = 0; i < valor.length(); i++)
        {
            if (caracteres.contains(String.valueOf(valor.charAt(i))))
            {
                valorAtualizado = valorAtualizado + String.valueOf(valor.charAt(i));
            }
        }
        return valorAtualizado;
    }
    
}
