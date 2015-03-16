
package barsystems;

public class Class_Manipular_Data {
    
    public Class_Manipular_Data() {
        
    }
    
    public String retornaDataFormatoMySQL(String dataFormatoBrasil, String hora) {
        String dia = dataFormatoBrasil.substring(0, 2);
        String mes = dataFormatoBrasil.substring(3, 5);
        String ano = dataFormatoBrasil.substring(6, 10);
        
        String data = ano+"/"+mes+"/"+dia+" "+hora+":00";
        return data;
    }
    
}
