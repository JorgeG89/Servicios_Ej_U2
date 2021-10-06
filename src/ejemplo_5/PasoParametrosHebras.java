package ejemplo_5;

public class PasoParametrosHebras {

    public static void main(String[] args) {
        Tarea1 t1 = new Tarea1(0);
        Tarea1 t2 = new Tarea1(1);

        t1.start();
        t2.start();
    }
}

class Tarea1 extends Thread{
    int idHebra;
    public Tarea1 (int idHebra) {
        this.idHebra = idHebra;
    }
    public void run(){
        System.out.println("Soy la hebra " + idHebra);
    }
}