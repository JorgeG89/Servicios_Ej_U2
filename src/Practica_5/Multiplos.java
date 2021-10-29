package Practica_5;

public class Multiplos {
    public static void main(String[] args) {
        int numHebras;
        long  vector [ ] = new long[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
                29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41};

        numHebras = 4;

        implementacionSecuencial(vector);
        implementacionCiclica(vector, numHebras);
        implementacionCiclicaSinReduccion(vector, numHebras);


    }
    static void implementacionSecuencial(long[] vector){
        long t1;
        long t2;
        double tt;
        int mul2 = 0;
        int mul3 = 0;
        int mul5 = 0;

        t1 = System.nanoTime();
        for (int contador = 0; contador<vector.length; contador++ ){
            if(vector[contador]%2 == 0)
                mul2++;
            if(vector[contador]%3 == 0)
                mul3++;
            if(vector[contador]%5 == 0)
                mul5++;
        }

        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;

        System.out.println("\nTiempo Secuencial (seg.):\t\t\t" + tt);
        System.out.println("Multiplos de 2: "+mul2);
        System.out.println("Multiplos de 3: "+mul3);
        System.out.println("Multiplos de 5: "+mul5);

    }

    static void implementacionCiclica(long[] vector, int numHebras) {
        long t1;
        long t2;
        double tt;
        devolverValor e = new devolverValor();
        MiHebraCiclicaReduccion listaHebras[] = new MiHebraCiclicaReduccion[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraCiclicaReduccion(idHebra, numHebras, vector, e);
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
        System.out.println("Multiplos de 2: "+e.dameResultadoMul2());
        System.out.println("Multiplos de 3: "+e.dameResultadoMul3());
        System.out.println("Multiplos de 5: "+e.dameResultadoMul5());
    }

    static void implementacionCiclicaSinReduccion(long[] vector, int numHebras) {
        long t1;
        long t2;
        double tt;
        devolverValor e = new devolverValor();
        MiHebraCiclicaSinReduccion listaHebras[] = new MiHebraCiclicaSinReduccion[numHebras];

        t1 = System.nanoTime();

        for (int idHebra = 0; idHebra < numHebras; idHebra++) {
            listaHebras[idHebra] = new MiHebraCiclicaSinReduccion(idHebra, numHebras, vector, e);
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
        System.out.println("\nTiempo ciclico sin reduccion (seg.):\t\t\t" + tt);
        System.out.println("Multiplos de 2: "+e.dameResultadoMul2());
        System.out.println("Multiplos de 3: "+e.dameResultadoMul3());
        System.out.println("Multiplos de 5: "+e.dameResultadoMul5());
    }
}

class devolverValor {
    int mul2 = 0;
    int mul3 = 0;
    int mul5 = 0;

    synchronized void sumarValor ( int mul2, int mul3, int mul5 ) {
        this.mul2 += mul2;
        this.mul3 += mul3;
        this.mul5 += mul5;
    }

    synchronized double dameResultadoMul2() {
        return this.mul2;
    }
    synchronized double dameResultadoMul3() {
        return this.mul3;
    }
    synchronized double dameResultadoMul5() {
        return this.mul5;
    }
}

class MiHebraCiclicaReduccion extends Thread {
    int  miId, numHebras;
    long  vector [ ];
    devolverValor e;

    public MiHebraCiclicaReduccion(int miId, int numHebras, long[] vector, devolverValor e) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.e = e;
    }

    public void run () {
        int mul2 = 0;
        int mul3 = 0;
        int mul5 = 0;

        for ( int i = miId; i < vector.length; i += numHebras ) {
            if(vector[i]%2 == 0)
                mul2++;
            if(vector[i]%3 == 0)
                mul3++;
            if(vector[i]%5 == 0)
                mul5++;
        }
        e.sumarValor(mul2, mul3, mul5);

    }
}

class MiHebraCiclicaSinReduccion extends Thread {
    int  miId, numHebras;
    long  vector [ ];
    devolverValor e;

    public MiHebraCiclicaSinReduccion(int miId, int numHebras, long[] vector, devolverValor e) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.e = e;
    }

    public void run () {
        for ( int i = miId; i < vector.length; i += numHebras ) {
            if(vector[i]%2 == 0)
                e.sumarValor(1, 0, 0);
            if(vector[i]%3 == 0)
                e.sumarValor(0, 1, 0);
            if(vector[i]%5 == 0)
                e.sumarValor(0, 0, 1);
        }
    }
}