package binaris.template;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nom;
    private int edat;

    public Persona(String nom, int edat) {
        this.nom = nom;
        this.edat = edat;
    }

    public void mostrarDades() {
        System.out.print("Nom: "+nom);
        System.out.println(" - Edat: "+edat);
        System.out.println();
    }
}
