import java.util.Random;
import java.util.logging.Logger;

public class Jugador implements Runnable {
    Logger logging;

    public Jugador(Logger logging) {
        this.logging = logging;
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
                Thread.sleep(1 + ran.nextInt(MAXESPERA));
            } catch (InterruptedException e) {
                break;
            }

            int dadoAleatorio = ran.nextInt(1, 6);
            sumadorTiradas += dadoAleatorio;
            logging.info(hilo + "Posición: " + sumadorTiradas + "\n" + hilo + "Has sacado: " + dadoAleatorio);
            if (sumadorTiradas > topeTablero) {
                resto = sumadorTiradas - topeTablero;
                sumadorTiradas = topeTablero - resto;
            }

            logging.info(hilo + "Nueva Posición: " + sumadorTiradas);
            if (sumadorTiradas == 100) {
                Thread.currentThread().getThreadGroup().interrupt();
                hilo.getThreadGroup().interrupt();
                logging.info("Ha ganado el hilo: " + Thread.currentThread());
            }
        }

        logging.info(String.valueOf(sumadorTiradas));
    }
}