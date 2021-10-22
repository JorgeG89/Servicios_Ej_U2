package Practica_4_2;

import Practica_4_3.Sincronizacion_basica_3;

public class Sincronizacion_basica_2 {
    public static void main(String args[]) {
        int numHebras;
        long vectorNumeros[] = {
                200000033L, 200000039L, 200000051L, 200000069L,
                200000081L, 200000083L, 200000089L, 200000093L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L
        };


        numHebras = 4;

        implementacionSecuencial(vectorNumeros);

        implementacionCiclica(vectorNumeros, numHebras);

        //implementacionBloques(vectorNumeros, numHebras);


    }

    static void implementacionSecuencial(long[] vectorNumeros) {
        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementación secuencial.");

        t1 = System.nanoTime();
        //Escribe aquí la implementación secuencial
        System.out.println("Numeros primos: ");
        for (int contador = 0; contador<vectorNumeros.length; contador++ ){
            if(esPrimo(vectorNumeros[contador])){
                System.out.printf(vectorNumeros[contador]+" ");
            }
        }

        //Fin de la implementación secuencial
        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;
        System.out.println("\nTiempo secuencial (seg.):\t\t\t" + tt);
    }

    static void implementacionCiclica(long[] vectorNumeros, int numHebras) {
        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementación cíclica.");

        MiHebraCiclica listaHebras[] = new MiHebraCiclica[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraCiclica(idHebra, numHebras, vectorNumeros);
            listaHebras[idHebra].start();
        }

        for (int i = 0; i < numHebras; i++) {
            try {
                listaHebras[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;

        System.out.println("\nTiempo cíclico (seg.):\t\t\t" + tt);
    }


//------------------------------------------------------------------------------------------------------------

    /*static void implementacionBloques(long[] vectorNumeros, int numHebras) {

        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementación por bloques.");

        MiHebraBloques v[] = new MiHebraBloques[numHebras];

        t1 = System.nanoTime();

        for (int i = 0; i < numHebras; i++) {
            v[i] = new MiHebraBloques(i, numHebras, vectorNumeros);
            v[i].start();
        }

        for (int i = 0; i < numHebras; i++) {
            try {
                v[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;

        System.out.println("Tiempo Bloques (seg.):\t\t\t" + tt);
    }*/

    static boolean esPrimo( long num ) {
        boolean primo;
        if( num < 2 ) {
            primo = false;
        } else {
            primo = true;
            long i = 2;
            while( ( i < num )&&( primo ) ) {
                primo = ( num % i != 0 );
                i++;
            }
        }
        return( primo );
    }
}

class MiHebraCiclica extends Thread {
    int idHebra;
    int numHebras;
    long[] vectorNumeros;

    public MiHebraCiclica(int idHebra, int numHebras, long[] vectorNumeros) {
        this.idHebra = idHebra;
        this.numHebras = numHebras;
        this.vectorNumeros = vectorNumeros;
    }
    public void run() {
        for (int i = idHebra; i < vectorNumeros.length; i+=numHebras) {
            if(Sincronizacion_basica_2.esPrimo(vectorNumeros[i])){
                System.out.print(vectorNumeros[i]+" ");
            }
            //System.out.println();
        }
    }
}


