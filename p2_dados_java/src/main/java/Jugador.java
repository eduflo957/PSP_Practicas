import java.util.Random;
import java.util.logging.Logger;

public class Jugador implements Runnable {
    Logger logging;
    public Jugador (Logger logging){
        this.logging=logging;
    }

    void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        this.logging.info(String.format("%s: %s%n", threadName, message));
    }

    int MAXESPERA = 2000;

    public void run() {
        Thread hilo = Thread.currentThread();
        Random ran = new Random();
        int cont = 0, resto = 0;
        int sumadorTiradas = 0;
        int topeTablero = 30; //Luego tengo que cambiarlo a 100

        while (sumadorTiradas != topeTablero) {
            try {
                sumadorTiradas += ran.nextInt(1, 6);
                if (sumadorTiradas > topeTablero) {
                    resto = sumadorTiradas - topeTablero;
                    sumadorTiradas = topeTablero - resto;
                }
                Thread.sleep(MAXESPERA);
                threadMessage("Mi sumador de tirada es: " + sumadorTiradas);
            } catch (InterruptedException e) {
                break;
            }
        }

        logging.info(String.valueOf(sumadorTiradas));
    }
}