import java.util.ArrayList;
import java.util.logging.Logger;

public class MainJuego {
    //(x1)    Se utiliza un sistema de log basado en el visto en el aula, que, a su vez, utiliza java.util.Logger
    static Logger logging = PSPLogger.getLogger(MainJuego.class.getName());
    public static void main(String args[]) {
        int numJugadores = 10;
        //Creo una lista de X jugadores
        ArrayList<Thread> listaJugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            //(x3)    Se lanzan las tareas adecuadamente desde el programa principal.
            listaJugadores.add(new Thread(new Jugador(logging)));
        }
        //Inicio un hilo por cada jugador
        for (int i = 0; i < listaJugadores.size(); i++) {
            listaJugadores.get(i).start();
        }
        //Me dice en el log qué hilos hay y cuántos he creado al inicio de mi ejecución
        logging.info("Los hilos creados son: "+ listaJugadores);
    }
}
