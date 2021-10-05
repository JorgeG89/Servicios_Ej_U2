package ejemplo_1;

public class CreandoSecuencial {
    public static void main(String[] args) {
        Tarea1 t1 = new Tarea1();
        Tarea2 t2 = new Tarea2();

        t1.metodo1();
        t2.metodo2();
    }
}

class Tarea1 {
    public void metodo1(){
        for (int i = 0 ; i < 10; i++){
            System.out.println("Tarea 1");
        }
    }
}

class Tarea2 {
    public void metodo2(){
        for (int i = 0 ; i < 10; i++){
            System.out.println("Tarea 2");
        }
    }
}