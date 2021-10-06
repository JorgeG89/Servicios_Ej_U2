package Actividad_3;

public class Actividad_3 {
    public static void main(String[] args) {
        int numHebras = 5;

        numHebra[] v = new numHebra[10];
        for (int i = 0; i < numHebras; i++){
            v[i] = new numHebra(i);
        }
        for (int i = 0; i < numHebras; i++){
            v[i].start();
        }

        for (int i = 0; i < numHebras; i++){
            try{
                v[i].join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Todas las hebras han acabado");
    }
}

class numHebra extends Thread{
    int idHebra;
    public numHebra (int idHebra){
        this.idHebra = idHebra;
    }

    public void run(){
        System.out.println("Hola soy la hebra: "+idHebra);
    }
}
