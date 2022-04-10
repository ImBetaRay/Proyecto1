package edd.src.wizard;
import edd.src.Estructuras.*;
import java.util.Scanner;
import java.util.Random;

public class WizardUI {
    
    private Jugador[] jugadores;
    private Scanner scanner;
    private Random rand;
    private long seed;


    public WizardUI() {
		scanner = new Scanner(System.in);
		jugadores = null;
        rand = new Random();
        seed = rand.nextLong();
        rand.setSeed(seed);
        System.out.println("Antes de iniciar la semilla es: " + seed);
    }

    private int leeEntero(String mensaje) {
		System.out.println(mensaje);
		boolean error = false;
		int num = -1;
		try {
			num = Integer.parseInt(scanner.nextLine());
			if (num <= 2 || num > 6) {
				error = true;
				System.out.println("El valor más chico posible es uno y el mayor 6.");
			}
		} catch (NumberFormatException nfe) {
			error = true;
			System.out.println("Por favor ingresa un número válido.");
		}
		if (error) {
			num = leeEntero(mensaje);
		}
		return num;
	}

    public void imprimeMenu() {
        System.out.println("Opciones:");
		System.out.println("3) Jugar");
		System.out.println("4) Creditos");
		System.out.println("5) Salir");
    }

    public void imprimeMenuJuego() {
        System.out.println("Opciones:");
		System.out.println("3) Seguir jugando");
		System.out.println("4) Ver puntajes");
		System.out.println("5) Salir");
    }

    public void imprimeCreditos() {
        System.out.println("oa");
    }

    public void jugarRondas(int numRondas){
        System.out.println("Se jugaran " + numRondas + " rondas.");
        Baraja baraja = new Baraja();
        Ronda ronda;
        Carta paloTriunfo;
        for (int i = 1; i <= numRondas; i++) {
            baraja.Shuffle(rand);
            System.out.println("Repartamos las manos para la ronda " + i + "!");
            for (int j = 0; j < jugadores.length; j++) {
                for (int m = 0; m < i; m++) {
                    jugadores[j].roba(baraja.deck.pop());
                }
            }

            paloTriunfo = baraja.deck.pop();
            System.out.println("El palo de triunfo es: " + paloTriunfo + "!");

            ronda = new Ronda(paloTriunfo, jugadores);
            ronda.iniciarRonda(i);

            int opcion = leeEntero("Selecciona una opción: \n 3) Seguir \n 4) Terminar");
			switch(opcion) {
			case 3:
				break;
			case 4:
				return;
			default:
				System.out.println("Esa no es una opción válida del menú.");
			}

        }






    }

    public void jugar() {
        jugadores = new Jugador[leeEntero("Cúantos jugadores seremos? (3,4,5 o 6)")];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Jugador();
        }
        switch (jugadores.length) {
            case 3:
                jugarRondas(20);
                break;
            case 4:
                jugarRondas(15);
                break;
            case 5:
                jugarRondas(12);
                break;    
            default:
                jugarRondas(10);
                break;
        }

        System.out.println("El ganador es: ");
        int ganador = 1;
        Jugador jGanador = jugadores[0];

        for (int i = 1; i < jugadores.length; i++) {
            if (jGanador.getPuntos() < jugadores[i].getPuntos()) {
                ganador = i+1;
                jGanador = jugadores[i];
            } 
        }

        System.out.println("Jugador " + ganador);
        
    }

    public void corre() {
        System.out.println("Bienvenido al proyecto 1: Wizard. Vamos a jugar!");
        boolean continuar = true;
		while(continuar) {
			imprimeMenu();
			int opcion = leeEntero("Selecciona una opción:");
			switch(opcion) {
			case 3:
				jugar();
				break;
			case 4:
				imprimeCreditos();
				break;
			case 5:
				continuar = false;
				break;
			default:
				System.out.println("Esa no es una opción válida del menú.");
			}
		}

        


    }


    public static void main(String[] args) {

        WizardUI interfaz = new WizardUI();
		interfaz.corre();
        
    }

}

