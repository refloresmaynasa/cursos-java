package org.rflores.beans;

import static org.rflores.util.Util.PERRO;
import static org.rflores.util.Util.GATO;
import static org.rflores.util.Util.CONEJO;
import static org.rflores.util.Util.CERDO;
import static org.rflores.util.Util.PATO;
import static java.lang.Math.random;
import static java.lang.Math.PI;

public class AnimalFactory {

    public static Animal getAnimal(int tipo){
        switch(tipo){
            case PERRO: return new Perro();
            case GATO: return new Gato();
            case CONEJO: return new Conejo();
            case CERDO: return new Cerdo();
            case PATO: return new Pato();
            default: return null;
        }
    }
    //no se debe usar codigo duro
    public static Animal getAnimalito(int tipo){
        switch(tipo){
            case 0: return new Perro();
            case 1: return new Gato();
            case 2: return new Conejo();
            case 3: return new Cerdo();
            case 4: return new Pato();
            default: return null;
        }
    }
    void m1(){
        double d = random()*PI;
    }

    public static Animal getAnimalote(Tipo tipo){
        switch(tipo){
            case EPERRO: return new Perro();
            case EGATO: return new Gato();
            case ECONEJO: return new Conejo();
            case ECERDO: return new Cerdo();
            case EPATO: return new Pato();
            default: return null;
        }
    }
    
}
