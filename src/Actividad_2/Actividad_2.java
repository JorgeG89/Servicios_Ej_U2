package Actividad_2;

public class Actividad_2 {
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

    //Esto se podria hacer como el ejemplo 5 cambiando la idHebra y los parametros de entrada
    public void run(){
        for (int i = 0 ; i < 3; i++) {
            if (idHebra == 0)
                System.out.println("hola");
            else if (idHebra == 1)
                System.out.println("adios");
        }
    }
}
