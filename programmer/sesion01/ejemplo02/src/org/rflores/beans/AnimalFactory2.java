package org.rflores.beans;

import static org.rflores.util.Util.*;

public class AnimalFactory2 {

    private static final AnimalFactory2 INSTANCE=new AnimalFactory2();

    private AnimalFactory2() {
        System.out.println("llamado al constructor");
    }

    public static AnimalFactory2 getInstance() {
        return INSTANCE;
    }

     public Animal getAnimal(int tipo){
        switch(tipo){
            case PERRO: return new Perro();
            case GATO: return new Gato();
            case CONEJO: return new Conejo();
            case CERDO: return new Cerdo();
            case VACA: return new Vaca();
            case PATO: return new Pato();
            case ALPACA: return new Alpaca();
            case CABALLO: return new Caballo();
            default: return null;
        }
    }

    public Animal getAnimal2(Tipo tipo){
        switch(tipo){
            case EPERRO: return new Perro();
            case EGATO: return new Gato();
            case ECONEJO: return new Conejo();
            case ECERDO: return new Cerdo();
            case EVACA: return new Vaca();
            case EPATO: return new Pato();

            default: return null;
        }
    }
    
}
