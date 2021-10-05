package ejemplo_7;

public class ConJoin {
    public static void main(String[] args) throws InterruptedException {
        Task1 t1 = new Task1(0);
        Task1 t2 = new Task1 (1);
        Task1 t3 = new Task1(2);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final del programa");
    }
}

class Task1 extends Thread{
    private int idHebra;
    public Task1 (int idHebra){
        this.idHebra = idHebra;
    }
    public void run(){
        try{
            int x = (int) (Math.random() * 5000);
            Thread.sleep(x);
            System.out.println("Soy la hebra: "+ idHebra + "( " + x + ")" );
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

