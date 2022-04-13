package edd.src.Wizard;
import edd.src.Estructuras.*;
import edd.src.Wizard.ColorTerminal;

import java.util.Scanner;
import java.util.Random;

public class WizardUI {
    /*
    * Cada partida de wizard tiene un arraglo de los jugadores ivolucrados, un scanner para leer texto desde
    * el teclado otorgado por el usuario, un random para aleatorizar el barajado, una semilla del randomizado
    * y un historial.
    */
    private Jugador[] jugadores;
    private Scanner scanner;
    private Random rand;
    private long seed;
    public static final Historial hist = new Historial();;


    public WizardUI() {
		scanner = new Scanner(System.in);
		jugadores = null;
        rand = new Random();
        seed = rand.nextLong();
        rand.setSeed(seed);
        System.out.println("Antes de iniciar la semilla es: " + seed);
    }

    /**
     * Metodo que lee un entero que otorga el usuario por el teclado y devuelve este mismo.
     * @param mensaje El mensaje que se le dará al lector para que escriba un entero.
     * @param min     El minimo valor que puede tener ese entero (sirve para regular las opciones).
     * @return El primer número valido que otorgue el usuraio.
     */
    private int leeEntero(String mensaje, int min) {
		System.out.println(mensaje);
		boolean error = false;
		int num = -1;
		try {
			num = Integer.parseInt(scanner.nextLine());
			if (num < min || num > 6) {
				error = true;
				System.out.println("El valor más chico posible es " +  min + " y el mayor 6.");
			}
		} catch (NumberFormatException nfe) {
			error = true;
			System.out.println("Por favor ingresa un número válido.");
		}
		if (error) {
			num = leeEntero(mensaje, min);
		}
		return num;
	}

    /**
     * Imprime el menu principal del juego Wizard.
     */
    public void imprimeMenu() {
        System.out.println("Opciones:");
		System.out.println("3) Jugar");
		System.out.println("4) Creditos");
		System.out.println("5) Salir");
    }

    /**
     * Imprime el meno de un juego ya iniciado de wizard.
     */
    public void imprimeMenuJuego() {
        System.out.println("Opciones:");
		System.out.println("3) Seguir jugando");
		System.out.println("4) Ver puntajes");
		System.out.println("5) Salir");
    }

    /**
     * Imprime a todos los creadores del juego.
     */
    public void imprimeCreditos() {
        System.out.println("Creado por: ");
        System.out.println(ColorTerminal.PURPLE_BOLD + "Rodriguez Belmonte Lázaro Eduardo" + ColorTerminal.RESET);
        System.out.println(ColorTerminal.PURPLE_BOLD + "Lopez Villalba Cielo" + ColorTerminal.RESET);
    }

    /**
     * Metodo que simula una partida completa de Wizard según el número de rondas dadas.
     * Para cada ronda se elije un palo triunfo, si es mago un jugador en turno elije el palo triunfo,
     * si sale un bufon o es la ultima ronda se juega sin palo triunfo.
     * Despues de cada ronda se pueden pedir  el historial, seguir o salir.
     * @param numRondas La cantidad de rondas a jugar.
     */
    public void jugarRondas(int numRondas){
        String str = "Se jugaran " + numRondas + " rondas.";
        hist.append(str);
        System.out.println(str);
        Baraja baraja = new Baraja();
        Ronda ronda;
        Carta paloTriunfo;
        for (int i = 1; i <= numRondas; i++) {
            baraja.Shuffle(rand);
            System.out.println("Repartamos las manos para la ronda " + i + "!");
            WizardUI.hist.append(ColorTerminal.PURPLE_BOLD + "Comenzó la ronda " + i + " y esto pasó:" + ColorTerminal.RESET);
            for (int j = 0; j < jugadores.length; j++) {
                for (int m = 0; m < i; m++) {
                    jugadores[j].roba(baraja.deck.pop());
                }
            }   

            if (i == numRondas) {
                paloTriunfo = new Carta(0, ColorTerminal.BLACK);
                hist.append("La ronda se jugó sin palo de triunfo");
                System.out.println("Esta ronda se jugara sin palo de tirunfo pues estamos en la ultima ronda.");
            }
            paloTriunfo = baraja.deck.pop();
            System.out.println("La carta que revelara el palo de triunfo es: " + paloTriunfo);
            if (paloTriunfo.getNumero() == 0){
                paloTriunfo = new Carta(0, ColorTerminal.BLACK);
                hist.append("La ronda se jugó sin palo de triunfo");
                System.out.println("Esta ronda se jugara sin palo de tirunfo pues salio un bufon.");
            } else if (paloTriunfo.getNumero() == 14) {
                boolean ciclo = true;
                
                while (ciclo == true) {
                    int opcion = leeEntero("Salío un mago es turno del jugador " + (numRondas % jugadores.length) + " de eligir el palo"
                     + " puedes elegir entre 4 colores (De 1 a 4): \n" + 
                    ColorTerminal.RED_BOLD    + "Rojo"     + ColorTerminal.RESET + ", " + 
                    ColorTerminal.BLUE_BOLD   + "Azul"     + ColorTerminal.RESET + ", " + 
                    ColorTerminal.GREEN_BOLD  + "Verde"    + ColorTerminal.RESET + ", " +
                    ColorTerminal.YELLOW_BOLD + "Amarillo" + ColorTerminal.RESET +".", 0);
                    switch (opcion) {
                        case 1:
                            paloTriunfo = new Carta(13, ColorTerminal.RED_BOLD);
                            str = "El palo de triunfo es: " + paloTriunfo + "!";
                            hist.append(str);
                            System.out.println(str);
                            ciclo = false;
                            break;
                        case 2:
                            paloTriunfo = new Carta(13, ColorTerminal.BLUE_BOLD);
                            str = "El palo de triunfo es: " + paloTriunfo + "!";
                            hist.append(str);
                            System.out.println(str);
                            ciclo = false;
                            break;
                        case 3:
                            paloTriunfo = new Carta(13, ColorTerminal.GREEN_BOLD);
                            str = "El palo de triunfo es: " + paloTriunfo + "!";
                            hist.append(str);
                            System.out.println(str);
                            ciclo = false;
                            break;
                        case 4:
                            paloTriunfo = new Carta(13, ColorTerminal.YELLOW_BOLD);
                            str = "El palo de triunfo es: " + paloTriunfo + "!";
                            hist.append(str);
                            System.out.println(str);
                            ciclo = false;
                            break;    
                    default:
                            System.out.println("Esa no es una opción válida.");
                    }
                }
                
            } else {
            str = "El palo de triunfo es: " + paloTriunfo + "!";
            hist.append(str);
            System.out.println(str);
            }

            ronda = new Ronda(paloTriunfo, jugadores);
            ronda.iniciarRonda(i);

			boolean bool = true;
            while(bool == true){
                int opcion = leeEntero("Selecciona una opción: \n 3) Seguir \n 4) Mostrar historial \n 5) Terminar", 3);
                switch(opcion) {
			        case 3:
				        bool = false; break;
                    case 4:
		        		System.out.println(hist); break;
			        case 5:
				        return;
		        	default:
				        System.out.println("Esa no es una opción válida del menú.");
		    	}
            }
        }






    }

    /**
     * Metodo que según un entero otorgado por el teclado inicia el juego para la cantidad de jugadores dados
     * Y al final da el jugador ganador.
     */
    public void jugar() {
        jugadores = new Jugador[leeEntero("Cúantos jugadores seremos? (3,4,5 o 6)", 3)];
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

    /**
     * Metodo que simula un menu principal para iniciar a jugar, ver creditos o salir.
     */
    public void corre() {
        System.out.println("Bienvenido al proyecto 1: Wizard. Vamos a jugar!");
        boolean continuar = true;
		while(continuar) {
			imprimeMenu();
			int opcion = leeEntero("Selecciona una opción:", 3);
			switch(opcion) {
			case 3:
				jugar();
				break;
			case 4:
				imprimeCreditos();
				break;
			case 5:
                System.out.println(ColorTerminal.PURPLE_BOLD + "Vuelva pronto." + ColorTerminal.RESET);
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

