package Actividad_1_2;

import static java.lang.Math.min;

public class Actividad_MaximoVector {
    public static void main(String args[]) {
        int numHebras;
        double  vector [ ] = new double[]{3.5, 3.9, 4.8, 5.9, 4.8, 2.5, 3.6, 8.3, 9.6, 10.6, 35.8, 23.4};
        numHebras = 4;

        implementacionSecuencial(vector);
        implementacionCiclica(vector, numHebras);
        implementacionBloques(vector, numHebras);
    }

    static void implementacionSecuencial(double[] vector) {
        double maximo = 0;
        long t1;
        long t2;
        double tt;

        t1 = System.nanoTime();
        //Comparamos el dato actual con la variable maximo, si es mayor, se asigna a esta
        for (int contador = 0; contador<vector.length; contador++ ){
            if(vector[contador]>maximo)
            maximo = vector[contador];
        }

        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;

        System.out.println("\nTiempo Secuencial (seg.):\t\t\t" + tt);
        System.out.println("Maximo por secuencial: "+maximo);
    }

    static void implementacionCiclica(double[] vector, int numHebras) {
        long t1;
        long t2;
        double tt;
        Asignar3 a = new Asignar3();
        MiHebraCiclica3 listaHebras[] = new MiHebraCiclica3[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraCiclica3(idHebra, numHebras, vector, a);
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
        System.out.println("Maximo por ciclico: "+a.dameResultado());
    }

    static void implementacionBloques(double[] vector, int numHebras) {
        long t1;
        long t2;
        double tt;
        Asignar3 a = new Asignar3();
        MiHebraPorBloques3 listaHebras[] = new MiHebraPorBloques3[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraPorBloques3(idHebra, numHebras, vector, a);
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
        System.out.println("Maximo por bloques: "+a.dameResultado());
    }
}

class Asignar3 {
    double maximo = 0.0;

    synchronized void asignarValor ( double valor ) {
        this.maximo = valor;
    }

    synchronized double dameResultado() {
        return this.maximo;
    }
}

class MiHebraCiclica3 extends Thread {
    int  miId, numHebras;
    double  vector [ ];
    Asignar3 a;


    public MiHebraCiclica3(int miId, int numHebras, double[] vector, Asignar3 a ) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run () {
        //Comparamos el valor actual asignado en la clase "Asignar3" y si es mayor, lo asignamos de nuevo con el valor
        //actual
        for ( int i = miId; i < vector.length; i += numHebras ) {
            if(vector[i]>a.dameResultado())
                a.asignarValor(vector[i]);
        }
    }
}

class MiHebraPorBloques3 extends Thread {
    int  miId, numHebras;
    double  vector [ ];
    Asignar3 a;

    public MiHebraPorBloques3(int miId, int numHebras, double[] vector, Asignar3 a ) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run () {
        int tamano = (vector.length+numHebras-1)/numHebras;
        int inicio = miId*tamano;
        int fin = min(vector.length,(miId+1)*tamano);

        //Comparamos el valor actual asignado en la clase "Asignar3" y si es mayor, lo asignamos de nuevo con el valor
        //actual
        for ( int i = inicio; i < fin; i++ ) {
            if(vector[i]>a.dameResultado())
                a.asignarValor(vector[i]);
        }
    }
}