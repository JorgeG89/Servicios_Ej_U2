package Actividad_1_2;

import static java.lang.Math.min;

public class Actividad2 {
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
        Acumula2  a = new Acumula2();
        MiHebraCiclica2 listaHebras[] = new MiHebraCiclica2[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraCiclica2(idHebra, numHebras, vector, a);
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
        Acumula2  a = new Acumula2();
        MiHebraPorBloques2 listaHebras[] = new MiHebraPorBloques2[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraPorBloques2(idHebra, numHebras, vector, a);
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

class Acumula2 {
    double suma = 0.0;

    synchronized void acumulaValor ( double valor ) {
        this.suma += valor;
    }

    synchronized double dameResultado() {
        return this.suma;
    }
}

class MiHebraCiclica2 extends Thread {
    int  miId, numHebras;
    double  vector [ ];
    Acumula2 a;


    public MiHebraCiclica2(int miId, int numHebras, double[] vector, Acumula2 a ) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run () {
        //Dato interno, solo 1 hebra lo vé y no la disturban las otras (MEJORA LA EFICIENCIA)
        double sumaLocal = 0;

        for ( int i = miId; i < vector.length; i += numHebras ) {
            sumaLocal += vector [ i ];
        }
        a.acumulaValor(sumaLocal);
    }
}

class MiHebraPorBloques2 extends Thread {
    int  miId, numHebras;
    double  vector [ ];
    Acumula2  a;


    public MiHebraPorBloques2(int miId, int numHebras, double[] vector, Acumula2 a ) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run () {
        //Dato interno, solo 1 hebra lo vé y no la disturban las otras (MEJORA LA EFICIENCIA)
        double sumaLocal = 0;

        int tamano = (vector.length+numHebras-1)/numHebras;
        int inicio = miId*tamano;
        int fin = min(vector.length,(miId+1)*tamano);

        for ( int i = inicio; i < fin; i++ ) {
            sumaLocal += vector [ i ] ;
        }
        a.acumulaValor(sumaLocal);
    }
}