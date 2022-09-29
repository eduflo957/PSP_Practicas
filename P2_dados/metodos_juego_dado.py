import random

class metodos:

    def tirarDado(self):
        random.randint(1, 6)

    def jugarPartidaEntera(self):
        ganador = False
        cont = 0
        resto = 0
        while cont < 100:
            tirada = random.randint(1, 6);
            print("tirada: " + str(tirada))
            cont = cont + tirada
            if cont > 100:
                resto = cont - 100
                cont = 100 - resto
            print("contador " + str(cont))
        ganador = True
        print("Has ganado")