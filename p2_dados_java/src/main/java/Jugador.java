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
        int topeTablero = 100; //El tablero es de 100, pero recomiendo poner 10 para que el log sea más legible

        while (sumadorTiradas != topeTablero) {
            try {
                //(x6)    El programa principal para correctamente el resto de las tareas.
                //Pongo la espera entre 1 y 2000 milisegundos
                Thread.sleep(1 + ran.nextInt(MAXESPERA));
                //(x7)    El programa principal espera al final de todas las tareas
                //Controlo que se paren todos los hilos cuando deje de cumplirse el while
            } catch (InterruptedException e) {
                break;
            }

            //Este es mi programa del juego, donde controlo que si se pasa del final de tablero,
            // se quede el resto y se reste a 100, así hasta que llegue exactamente al final tablero.
            //(x2)    Se define cada tarea de jugador conforme a las instrucciones
            int dadoAleatorio = ran.nextInt(1, 6);
            sumadorTiradas += dadoAleatorio;
            //(x4)    El programa principal hace las comprobaciones de estado de las tareas correctamente en el periodo especificado.
            logging.info(hilo + "Posición: " + sumadorTiradas + "\n" + hilo + "Has sacado: " + dadoAleatorio);
            if (sumadorTiradas > topeTablero) {
                resto = sumadorTiradas - topeTablero;
                sumadorTiradas = topeTablero - resto;
            }

            //Entra en mi log el estado de las posiciones del jugador
            logging.info("\n" + hilo + "Nueva Posición: " + sumadorTiradas);
        }

        //(x5)    El programa principal averigua correctamente quién es el vencedor.
        //Si el hilo llega a 100, se para el juego y sale un ganador en el log.
        if (sumadorTiradas == topeTablero) {
            Thread.currentThread().getThreadGroup().interrupt();
            logging.info("Ha ganado el hilo: " + Thread.currentThread());
        }

    }
}