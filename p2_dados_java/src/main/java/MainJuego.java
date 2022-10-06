import java.util.ArrayList;
import java.util.logging.Logger;

public class MainJuego {
    static Logger logging = PSPLogger.getLogger(MainJuego.class.getName());
    public static void main(String args[]) {
        int numJugadores = 3;
        ArrayList<Thread> listaJugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            listaJugadores.add(new Thread(new Jugador(logging)));
        }
        for (int i = 0; i < listaJugadores.size(); i++) {
            listaJugadores.get(i).start();
        }
        logging.info(String.valueOf(listaJugadores));
    }
}
