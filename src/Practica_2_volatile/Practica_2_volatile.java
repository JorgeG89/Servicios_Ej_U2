package Practica_2_volatile;

public class Practica_2_volatile {
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

        System.out.println ("Soy el programa principal y contador vale: " + cuenta.dameContador());
    }
}

class CuentaIncrementos{
    volatile long contador = 0;

    void incrementaContador(){
        contador++;
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
        System.out.println("Inicio de la hebra: "+idHebra);
        for (int repetir = 0; repetir < 1000000; repetir++){
            cuenta.incrementaContador();
        }

        System.out.println("La hebra "+idHebra+" ha terminado");
    }
}
