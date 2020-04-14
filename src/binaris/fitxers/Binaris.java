package binaris.fitxers;

import binaris.template.Persona;

import java.io.*;
import java.util.ArrayList;

public class Binaris {
    File directori,fitxer;
    FileOutputStream fOutStream;
    ObjectOutputStream oOutputStream;
    Persona p1,p2,p3,p4,p5,p;
    FileInputStream fInputStream;
    ObjectInputStream oInputStream;
    MyObjectOutputStream myOOutputString;
    ArrayList<Persona> alp;
    public Binaris() {
        creaDirectori("dades");
        creaFileOutput("fitxerBinari.bin",false);
        preparaEscriptor(); //l'escriptor serà oOutputStream
        creaObjectes();
        escriuObjectesAlFitxer();
        preparaLector();
        llegirFitxerBinari();
        creaFileOutput("fitxerBinari.bin",true);
        preparaNouEscriptor();
        creaNousObjectes();
        escriuNousObjectesAlFitxer();
        preparaLector();
        llegirFitxerBinari();

        System.out.println("Ara treballem amb ArrayLists.");

        creaFileOutput("fitxerBinari2.bin",false);
        preparaEscriptor();
        creaLlistaObjectes();
        escriuLlistaAlFitxer();
        preparaLector();
        llegirFitxerBinari2();
        creaFileOutput("fitxerBinari2.bin",true);
        preparaNouEscriptor();
        creaUnaAltraLlista();
        escriuNovaLlistaAlFitxer();
        preparaLector();
        llegirFitxerBinari2();





    }




    private void creaDirectori(String nomDirectori) {
        directori = new File(nomDirectori);
        if(directori.mkdir()){
            System.out.println("Directori Creat correctament");
        }else{
            System.out.println("No s'ha creat el directori.. O ja estava creat o hi ha algun error");
        }
    }

    private void creaFileOutput(String nomFitxer,boolean append) {
        fitxer = new File(directori.getAbsolutePath()+File.separator+nomFitxer);
        try {
            fOutStream = new FileOutputStream(fitxer,append); //fitxer creat
            //fitxer.createNewFile(); //fitxer creat d'altra manera
        } catch (FileNotFoundException e) {
            System.err.println("Error al crear el fitxer en la instància FileOutputStream: "+e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void preparaEscriptor() {
        try {
            oOutputStream = new ObjectOutputStream(fOutStream);
        } catch (IOException e) {
            System.err.println("Error al crear l'escriptor: "+e);
        }
    }

    private void creaObjectes() {
        p1 = new Persona("Hector",39);
        p2 = new Persona("David",45);
        p3 = new Persona("Miquel",53);
    }

    private void escriuObjectesAlFitxer() {
        try {
            oOutputStream.writeObject(p1);
            oOutputStream.writeObject(p2);
            oOutputStream.writeObject(p3);
            oOutputStream.close();
        } catch (IOException e) {
            System.err.println("Error al escriure objectes al fitxer: "+e);
        }
    }
    private void preparaLector() {
        try {
            fInputStream = new FileInputStream(fitxer);
            oInputStream = new ObjectInputStream(fInputStream);
        } catch (FileNotFoundException e) {
            System.err.println("Error al instanciar FileInputStream: "+e);
        } catch (IOException e) {
            System.err.println("Error al instanciar ObjectInputStream: "+e);
        }

    }

    private void llegirFitxerBinari() {
        while(true){
            try {
                p = (Persona) oInputStream.readObject(); //quan finalitza envia una excepció (EOFException)
                p.mostrarDades();
            } catch(EOFException e){
                System.out.println("Final del fitxer: "+e);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void preparaNouEscriptor() {
        try {
            myOOutputString = new MyObjectOutputStream(fOutStream);
        } catch (IOException e) {
            System.err.println("Error al crear el nou escriptor: "+e);
        }
    }

    private void creaNousObjectes() {
        p4 = new Persona("Marta",40);
        p5 = new Persona("Mireia",60);

    }

    private void escriuNousObjectesAlFitxer() {

        try {
            myOOutputString.writeObject(p4);
            myOOutputString.writeObject(p5);

            myOOutputString.close();
        } catch (IOException e) {
            System.err.println("Error al escriure nous objectes al fitxer: "+e);
        }
    }


    private void creaLlistaObjectes() {
        alp = new ArrayList<Persona>();
        alp.add(p1);
        alp.add(p2);
        alp.add(p3);

    }

    private void escriuLlistaAlFitxer() {
        try {
            oOutputStream.writeObject(alp);
            oOutputStream.close();
        } catch (IOException e) {
            System.err.println("Error al escriure llista al fitxer: "+e);
        }
    }

    private void llegirFitxerBinari2() {
        ArrayList<Persona> al = new ArrayList<Persona>();
        while(true){
            try {
                al = (ArrayList<Persona>) oInputStream.readObject(); //quan finalitza envia una excepció (EOFException)
                for (Persona p:
                     al) {
                    p.mostrarDades();
                }

            } catch(EOFException e){
                System.out.println("Final del fitxer: "+e);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void creaUnaAltraLlista() {
        alp = new ArrayList<Persona>();
        alp.add(p4);
        alp.add(p5);

    }

    private void escriuNovaLlistaAlFitxer() {
        try {
            myOOutputString.writeObject(alp);
            myOOutputString.close();
        } catch (IOException e) {
            System.err.println("Error al escriure nova llista al fitxer: "+e);
        }
    }



}
