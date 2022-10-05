import java.util.Random;
import java.util.logging.Logger;

public class Jugador implements Runnable {
    Logger logging = PSPLogger.getLogger(Jugador.class.getName());

    public void run() {
        Random ran = new Random();
        int cont = 0, resto = 0;
        int sumadorTiradas = 0;
        sumadorTiradas += ran.nextInt(1, 6);
        System.out.println("prueba");
        if (sumadorTiradas > 100) {
            resto = sumadorTiradas - 100;
            sumadorTiradas = 100 - resto;
        }
        logging.info(String.valueOf(sumadorTiradas));
        //} while (sumadorTiradas < 100);
    }


}


}

        {
public static void main(String args[])
        throws InterruptedException{

        // Si no se dice otra cosa, la variable
        // patience indica una hora.
        long patience=1000*60*60;

        // El argumento, si existe, debe poder
        // convertirse a segundos
        if(args.length>0){
        try{
        patience=Long.parseLong(args[0])*1000;
        }catch(NumberFormatException e){
        System.err.println("Argument must be an integer.");
        System.exit(1);
        }
        }

        threadMessage("Comenzando la tarea MessageLoop");
        long startTime=System.currentTimeMillis();

        // Aquí es donde se define la tarea
        Thread t=new Thread(new MessageLoop());
        // Aquí es donde se arranca
        t.start();

        threadMessage("Esperando el final de MessageLoop");
        // loop until MessageLoop
        // thread exits
        while(t.isAlive()){
        threadMessage("Esperando...");

        // Espera de un segundo a que la tarea se pare
        t.join(1000);
        if(((System.currentTimeMillis()-startTime)>patience)
        &&t.isAlive()){
        // La tarea sigue viva
        threadMessage("Me he hartado. Procedo a interrumpir");

        // Se interrumpe la tares
        t.interrupt();
        // Se vuelve a arrancar
        t.join();
        }
        }
        threadMessage("¡Por fin!");

        }