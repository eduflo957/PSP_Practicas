import java.util.ArrayList;

public class MainJuego {
    public static void main(String args[]) {
        int numJugadores = 0;
        ArrayList<Thread> listaJugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            listaJugadores.add(new Thread(new Jugador()));
        }
        for (int i = 0; i < listaJugadores.size(); i++) {
            listaJugadores.get(i).start();
        }
    }
}
