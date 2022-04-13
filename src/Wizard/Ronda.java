package edd.src.Wizard;
import edd.src.Estructuras.*;
import edd.src.Wizard.WizardUI;

import java.util.Random; 
import java.util.Scanner; 

public class Ronda {

  /*
  * Cada ronda según las reglas de wizard se tiene un palo triunfo que como su nombre lo dice es casi
  * victoria instantanea, un palo lider que es el que si está en la posibilidad todos deben jugar 
  * a menos que se tenga un mago, un arreglo con los jugadores que participan en la ronda y 
  * un entero que sirver para saber cual fue el ultimo jugador en ganar un truco pues será
  * el que empiece el siguiente.
  */
  private Carta paloTriunfo;
  private Carta paloLider;
  private Jugador[] jugadores;
  private int ganadorTruco;

  /**
   * Metodo constructor.
   * @param paloTriunfo Carta de palo de triunfo.
   * @param jugadores Array de jugadores.
   */
  public Ronda(Carta paloTriunfo, Jugador[] jugadores) {
    this.paloTriunfo = paloTriunfo;
    this.jugadores = jugadores;
    this.ganadorTruco = 0;
    this.paloLider = null;
  }

  /**
   * Metodo que simula una ronda en el juego de wizard, es decir primero se apuestan los trucos a ganar, 
   * a continucación se saca el palo lider y cada jugador jugara una carta según el palo lider.
   * Finalmente se acaban los trucos y se calculan los puntos.
   * @param numRonda El número de trucos a jugar es el equivalente al número de ronda.
   */
  public void iniciarRonda(int numRonda) {
    System.out.println(ColorTerminal.RED_BACKGROUND + "Es hora de apostar: " + ColorTerminal.RESET);

    for (int i = 0; i < jugadores.length; i++) {

      jugadores[i].apostar(numRonda, (i+1));
    }

    System.out.println(ColorTerminal.RED_BACKGROUND + "Es hora de jugar: " + ColorTerminal.RESET);
    Carta[] truco; //Creamos un array donde se almacenarán las cartas jugadas en cada truco.
    Carta aux;

    for (int m = 0; m < numRonda; m++) {  

      truco = new Carta[jugadores.length];
      System.out.println(ColorTerminal.RED_BACKGROUND + "Truco " + (m+1) + " : " + ColorTerminal.RESET);

      if (leeEntero("Si quieres ver el historial presiona 1.", 2) == 1) {
        System.out.println(WizardUI.hist);
      }
      

      WizardUI.hist.append("### Se jugó el truco " + (m+1) + " ###");

      //El palo lider siempre lo decide el ultimo ganador.
      for (int i = ganadorTruco; i < truco.length; i++) {
        System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + " eres el palo lider : " + ColorTerminal.RESET);
        WizardUI.hist.append("El jugador " + (i+1) + " Fue el primero en jugar el truco (el palo lider) y esto pasó: ");
        if (i == truco.length - 1) {
          i = 0; // Si llegamos al final del array y aún no hay palo lider vamos al inicio.
        }
        aux = jugadores[i].juegaCarta();
        truco[i] = aux;
        if (i == ganadorTruco - 1) break; // Si ya recorrimos todo el array terminamos y jugaremos sin palo lider.
        if (aux.getNumero() != 14 && aux.getNumero() != 0) {
          this.paloLider = aux;
          break;
        }
        System.out.println("Ups! Es un mago o bufon, el lider será el siguiente.");
        WizardUI.hist.append("El jugador " + (i+1) + "sacó un mago o bufón por lo tanto no puede ser el palo lider.");
      }
      
      for (int i = 0; i < jugadores.length; i++) {

        if (truco[i] != null) {
          System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + " ya jugaste cuando se decidio el palo lider: " + ColorTerminal.RESET);
          continue;
        }
        System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + " es tu turno de jugar un carta: " + ColorTerminal.RESET);
        WizardUI.hist.append("El jugador " + (i+1) + " Fue el siguiente en jugar el truco  y esto pasó: ");
        truco[i] = jugadores[i].juegaCarta(paloLider);

      }

      System.out.println(ColorTerminal.RED_BACKGROUND + "Veamos quien gana " + ColorTerminal.RESET);
      this.ganadorTruco = ganador(truco);

    }
    

    System.out.println(ColorTerminal.RED_BACKGROUND + "Termina la ronda, los puntajes son " + ColorTerminal.RESET);
    WizardUI.hist.append("# Terminó la ronda y estos fueorn los puntajes #");
    for (int i = 0; i < jugadores.length; i++) {
      System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + ": " + ColorTerminal.RESET);
      WizardUI.hist.append("El jugador " + (i+1) + ":");
      jugadores[i].finalRonda();
    }

  }

  /**
   * Metodo que calcula el ganador del truco en el siguiente orden de prioridades.
   * 1. Fue el primero en jugar un mago.
   * 2. Jugó la carta más alta del palo de triunfo.
   * 3. Jugó la carta más alta del palo líder.
   * 4. Fue el primero en jugar un bufón.
   * @param truco Arreglo con las cartas jugadas en cada partida.
   * @return El indice en el cual se encuentra el jugador que gano dentro del array de jugadores.
   */
  private int ganador(Carta[] truco) {
    int jugador = 0;

    for (int i = 0; i < truco.length; ++i) {
      if (truco[i].getNumero() == 14) {
        jugador = i;
        break;
      }
      if (truco[i].getPalo().equals(paloTriunfo.getPalo())) {
        if ((truco[jugador].getPalo().equals(paloTriunfo.getPalo())) && (truco[jugador].getNumero() > truco[i].getNumero())) {
          continue;
        }
        jugador = i;
      }
      if (truco[i].getPalo().equals(paloLider.getPalo()) && !(truco[i].getPalo().equals(paloTriunfo.getPalo()))) {
        if ((truco[jugador].getPalo().equals(paloLider.getPalo())) && (truco[jugador].getNumero() > truco[i].getNumero()) ) {
          continue;
        }
        jugador = i;
      }
    }
    
    
    jugadores[jugador].ganaTruco();
    ganadorTruco = jugador;
    WizardUI.hist.append("Ganó el truco el Jugador " + (jugador+1) + " con la carta " + truco[jugador] + ".");
    System.out.println(ColorTerminal.RED_BACKGROUND + "Gana Jugador " + (jugador+1) + " con la carta " + truco[jugador] + " : " + ColorTerminal.RESET);
    return jugador;
  }  


  /**
     * Metodo que lee un entero que otorga el usuario por el teclado y devuelve este mismo.
     * @param mensaje El mensaje que se le dará al lector para que escriba un entero.
     * @param max     El máximo valor que puede tener ese entero (sirve para regular las opciones).
     * @return El primer número valido que otorgue el usuraio.
     */
    private int leeEntero(String mensaje, int max) {
      Scanner scanner = new Scanner(System.in);
      System.out.println(mensaje);
      boolean error = false;
      int num = -1;
      try {
        num = Integer.parseInt(scanner.nextLine());
        if (num < 0 || num > max) {
          error = true;
          System.out.println("El valor más chico posible es 0 y el mayor " + max + ".");
        }
      } catch (NumberFormatException nfe) {
        error = true;
        System.out.println("Por favor ingresa un número válido.");
      }
      if (error) {
        num = leeEntero(mensaje, max);
      }
      return num;
    }

}
