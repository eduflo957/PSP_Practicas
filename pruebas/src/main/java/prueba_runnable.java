import java.util.logging.Logger;

public class prueba_runnable implements Runnable{

    private static Logger logging = Logger.getLogger(prueba_runnable.class.getName());

    @Override
    public void run()  {
        logging.info("Hola desde Runnable");
    }

    public static void main(String args[]) {
        (new Thread(new prueba_runnable())).start();
    }
}