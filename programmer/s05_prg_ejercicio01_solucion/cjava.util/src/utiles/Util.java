package utiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {
    public static Tipo OPC;
    // cargar de un archivo properties los valores
    static Properties myProps = new Properties();
    static {
        try{
            FileInputStream fis = new FileInputStream("datos.properties");
            myProps.load(fis);
            OPC = Tipo.valueOf(myProps.getProperty("opc"));
        } catch (IOException ex) {

        }
    }

    //public static final Tipo OPC = Tipo.MEMORIA;


}
