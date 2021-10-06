package ejemplo_4;

public class CreandoHebrasAnonima {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            public void run(){
                for (int i = 0; i < 10; i++){
                    System.out.println("Tarea 1");
                }
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                for (int i = 0; i < 10; i++){
                    System.out.println("Tarea 2");
                }
            }
        };

        t1.start();
        t2.start();
    }


}