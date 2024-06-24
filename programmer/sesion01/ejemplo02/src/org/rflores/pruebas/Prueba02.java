package org.rflores.pruebas;

import org.rflores.beans.Animal;
import org.rflores.beans.AnimalFactory;
import org.rflores.beans.AnimalFactory2;
import org.rflores.beans.Tipo;
import org.rflores.util.Util;

public class Prueba02 {
    public static void main(String[] args) {

       Animal animal = AnimalFactory.getAnimal(Util.opc);
       System.out.println(animal.pedirAyuda());

       Animal animalA = AnimalFactory.getAnimalote(Tipo.EPATO);
       System.out.println(animalA.pedirAyuda());

        Animal animal1 = AnimalFactory2.getInstance().getAnimal(Util.opc);
        System.out.println(animal1.pedirAyuda());

        Animal animal2 = AnimalFactory2.getInstance().getAnimal(1);
        System.out.println(animal2.pedirAyuda());

    }

}










