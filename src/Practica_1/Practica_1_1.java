package Practica_1;

public class Practica_1_1 {
    public static void main(String[] args) {

        Tarea1_1 t1 = new Tarea1_1(0);
        Tarea1_1 t2 = new Tarea1_1(1);
        t1.start();
        t2.start();
    }
}

class Tarea1_1 extends Thread {
    int idHebra;
    public Tarea1_1(int idHebra) {
        this.idHebra = idHebra;
    }

    public void run() {
        int numHebras = 1000;
        for (int nHebra = 0; nHebra < numHebras; nHebra++) {
            System.out.println("Hola, soy la hebra "+ idHebra);
        }
    }
}


