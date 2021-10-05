package Practica_1;

public class Practica_1_2 {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Tarea1_2());
        Thread t2 = new Thread(new Tarea2_2());
        t1.start();
        t2.start();
    }
}

class Tarea1_2 implements Runnable {
    public void run() {
        int numHebras = 1000;
        for (int nHebra = 0; nHebra < numHebras; nHebra++) {
            System.out.println("Hola, soy la hebra 0");
        }
    }
}
class Tarea2_2 implements Runnable {
    public void run() {
        int numHebras = 1000;
        for (int nHebra = 0; nHebra < numHebras; nHebra++) {
            System.out.println("Hola, soy la hebra 1");
        }
    }
}
