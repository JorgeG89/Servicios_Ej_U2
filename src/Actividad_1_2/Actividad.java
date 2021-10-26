package Actividad_1_2;

import static java.lang.Math.min;

public class Actividad {
    public static void main(String args[]) {
        int numHebras;
        double  vector [ ] = new double[]{3.5, 3.9, 4.8, 5.9, 4.8, 2.5, 3.6, 8.3, 9.6, 10.6, 35.8, 23.4};
        numHebras = 4;

        implementacionSecuencial(vector);
        implementacionCiclica(vector, numHebras);
        implementacionBloques(vector, numHebras);
    }

    static void implementacionSecuencial(double[] vector) {
        double suma = 0;
        long t1;
        long t2;
        double tt;

        t1 = System.nanoTime();
        for (int contador = 0; contador<vector.length; contador++ ){
                suma += vector[contador];
            }

        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;

        System.out.println("\nTiempo Secuencial (seg.):\t\t\t" + tt);
        System.out.println("Acumulativo secuencial: "+suma);
    }

    static void implementacionCiclica(double[] vector, int numHebras) {
        long t1;
        long t2;
        double tt;
        Acumula  a = new Acumula();
        MiHebraCiclica listaHebras[] = new MiHebraCiclica[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraCiclica(idHebra, numHebras, vector, a);
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
        System.out.println("\nTiempo ciclico (seg.):\t\t\t" + tt);
        System.out.println("Acumulativo ciclico: "+a.dameResultado());
    }

    static void implementacionBloques(double[] vector, int numHebras) {
        long t1;
        long t2;
        double tt;
        Acumula  a = new Acumula();
        MiHebraPorBloques listaHebras[] = new MiHebraPorBloques[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraPorBloques(idHebra, numHebras, vector, a);
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
        System.out.println("\nTiempo por bloques (seg.):\t\t\t" + tt);
        System.out.println("Acumulativo por bloques: "+a.dameResultado());
    }
}

class Acumula {
    double suma = 0.0;

    synchronized void acumulaValor ( double valor ) {
        this.suma += valor;
    }

    synchronized double dameResultado() {
        return this.suma;
    }
}

class MiHebraCiclica extends Thread {
    int  miId, numHebras;
    double  vector [ ];
    Acumula  a;


    public MiHebraCiclica(int miId, int numHebras, double vector [ ], Acumula a ) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run () {
        for ( int i = miId; i < vector.length; i += numHebras ) {
            a.acumulaValor ( vector [ i ] );
        }
    }
}

class MiHebraPorBloques extends Thread {
    int  miId, numHebras;
    double  vector [ ];
    Acumula  a;


    public MiHebraPorBloques(int miId, int numHebras, double vector [ ], Acumula a ) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run () {
        int tamano = (vector.length+numHebras-1)/numHebras;
        int inicio = miId*tamano;
        int fin = min(vector.length,(miId+1)*tamano);

        for ( int i = inicio; i < fin; i++ ) {
            a.acumulaValor ( vector [ i ] );
        }
    }
}

