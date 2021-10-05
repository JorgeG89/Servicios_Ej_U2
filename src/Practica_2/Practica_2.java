package Practica_2;

public class Practica_2 {
    public static void main (String args[] ) {
        if ( args.length != 1 ) {
            System.out.println ("Error");
            System.exit(-1);
        }

        int numHebras = Integer.parseInt(args[0]);

        miHebra[] v = new miHebra [numHebras];
        CuentaIncrementos cuenta = new CuentaIncrementos();

        System.out.println ( "Soy el programa principal y contador vale: " + cuenta.dameContador());

        for ( int i = 0; i < numHebras; i++ ) {
            v[i] = new miHebra (i,cuenta);
        }

        for ( int i = 0; i < numHebras; i++ ) {
            v[i].start();

        }

        for (int i = 0; i < numHebras; i++ ) {
            try{
                v[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();

            }
        }

        System.out.println ("Soy el programa principal y contador vale:" + cuenta.dameContador());
    }
}

class CuentaIncrementos{
    long contador = 0;

    void incrementaContador(){

    }

    long dameContador(){
        return contador;
    }
}

class miHebra extends Thread{
    int idHebra;
    CuentaIncrementos cuenta;
    public miHebra(int idHebra, CuentaIncrementos cuenta){
        this.idHebra = idHebra;
        this.cuenta = cuenta;
    }

    public void run(){

        System.out.println("Hola soy la hebra: "+idHebra);
    }
}
