package org.example;

import java.util.Random;

public class juegoDado {

    public static void main(String[] args) {

    }

    public static void jugada () {
        Random ran = new Random();
        int resultadoTirar = ran.nextInt(1,6);

        /*
         def __init__(self, cont, ganador):
        self.cont = cont
        self.ganador = ganador

    #__delante del método lo hago privado
    def __tirarDado(self):
        return random.randint(1, 6)

        #crear función de posiciones de cada jugador
        #A cada tirada le llamo calcular posición
    def __revisarPosicion(self):
        if self.cont > 100:
            resto = self.cont - 100
            self.cont = 100 - resto
        elif self.cont == 100:
            self.ganador= True

    def calcularPosicion(self):
        resultadoTirar = metodos.__tirarDado(self)
        print("tirada: " + str(resultadoTirar))
        #        self.cont += resultadoTirar #este es mi contador (meter en apuntes)
        metodos.__revisarPosicion(self)
        if self.ganador == True:
            print("Jugador ha ganado")




         */
    }
}
