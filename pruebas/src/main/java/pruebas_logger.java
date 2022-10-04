import java.util.logging.Logger;

public class pruebas_logger {
    //Esto lo ponemos para hacer el logger
    private static Logger logging = Logger.getLogger(pruebas_logger.class.getName());

    //Ponemos el interrupted para poder interrumpir nuestra
    //accion cuando queramos
    public static void main(String args[]) throws InterruptedException {
        //Voy a trabajar con este String
        String frases [] = {
                "frase1", "frase2", "frase3"
        };
        for(int i=0; i<frases.length; i++) {
            //Pausa de 4000ms en mi programa
            Thread.sleep(4000);
            //Con logging info visualizo lo que quiero por pantalla
            logging.info(frases[i]);
        }

    }
}