package org.example;

import java.util.Random;

public class Main {

    public class Jugador implements Runnable {
        int pos = 0;

        protected static Random ran = new Random();
        protected static int MAXESPERA = 2000;

        @Override
        public void run() {
            Thread hilo = Thread.currentThread();
            int wa = hilo.getThreadGroup().activeCount();
            while (pos != 63) {
                try {
                    Thread.sleep(1 + ran.nextInt(MAXESPERA));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (hilo.getThreadGroup().activeCount() != wa) {
                    break;
                }
                int dados = ran.nextInt(6) + 1;
                System.out.println(hilo + "Posicion: " + pos + "\n" + hilo + "Has sacado: " + dados);
                if (pos < 63) {
                    pos += dados;
                } else if (pos > 63) {
                    pos -= dados;
                }
                System.out.println(hilo + "Nueva Posicion: " + pos);
                if (pos == 63) {
                    System.out.println("HAS GANADO AAAAAAAAAAAAAAAAAAAAAAAAAA" + hilo);
                }
            }

        }


        public static void Jugada() {
            Random ran = new Random();
            int cont = 0;
            int dado = ran.nextInt(6) + 1;
            cont = cont + dado;
        }

        public static void main(String[] args) {
        }


    }

}