package edd.src.wizard;

import edd.src.Estructuras.*;

import java.lang.reflect.Array;
import java.util.Random;  

public class Ronda {

  private Carta paloTriunfo;
  private Carta paloLider;
  private Jugador[] jugadores;
  private int ganadorTruco;

  public Ronda(Carta paloTriunfo, Jugador[] jugadores) {
    this.paloTriunfo = paloTriunfo;
    this.jugadores = jugadores;
    this.ganadorTruco = 0;
    this.paloLider = null;
  }

  public void iniciarRonda(int numRonda) {
    System.out.println(ColorTerminal.RED_BACKGROUND + "Es hora de apostar: " + ColorTerminal.RESET);

    for (int i = 0; i < jugadores.length; i++) {

      jugadores[i].apostar(numRonda, (i+1));
    }

    System.out.println(ColorTerminal.RED_BACKGROUND + "Es hora de jugar: " + ColorTerminal.RESET);
    Carta[] truco;
    Carta aux;

    for (int m = 0; m < numRonda; m++) {  

      truco = new Carta[jugadores.length];
      System.out.println(ColorTerminal.RED_BACKGROUND + "Truco " + (m+1) + " : " + ColorTerminal.RESET);

      for (int i = ganadorTruco; i < truco.length; i++) {
        System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + " eres el palo lider : " + ColorTerminal.RESET);
        if (i == truco.length - 1) {
          i = 0;
        }  
        aux = jugadores[i].juegaCarta();
        truco[i] = aux;
        if (aux.getNumero() != 14 && aux.getNumero() != 0) {
          this.paloLider = aux;
          break;
        }
        System.out.println("Ups! Es un mago o bufon, el lider será el siguiente.");
      }
      
      for (int i = 0; i < jugadores.length; i++) {

        if (truco[i] != null) {
          System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + " ya jugaste cuando se decidio el palo lider: : " + ColorTerminal.RESET);
          continue;
        }
        System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + " es tu turno de jugar un carta: " + ColorTerminal.RESET);
        truco[i] = jugadores[i].juegaCarta(paloLider);

      }

      System.out.println(ColorTerminal.RED_BACKGROUND + "Veamos quien gana " + ColorTerminal.RESET);
      ganador(truco);

    }
    

    System.out.println(ColorTerminal.RED_BACKGROUND + "Termina la ronda, los puntajes son " + ColorTerminal.RESET);

    for (int i = 0; i < jugadores.length; i++) {
      System.out.println(ColorTerminal.RED_BACKGROUND + "Jugador " + (i+1) + ": " + ColorTerminal.RESET);
      jugadores[i].finalRonda();
    }

  }

  private void ganador(Carta[] truco) {
    int jugador = 0;
    Carta[] aux = truco.clone();
    int max = 0;

    for (int i = 1; i < truco.length; ++i) {
      Carta temp = aux[i];
      int j = i - 1;
      while (j >= 0 && aux[j].getNumero() > temp.getNumero()) {
          aux[j+1] = aux[j];
          j--;
      }
      aux[j + 1] = temp;
  }

    for (int k = 0; k < truco.length; k++) {
      if (aux[max] == truco[k]) {
        max = k;
        break;
      }
    }
    
    
    jugadores[max].ganaTruco();
    ganadorTruco = max;
    System.out.println(ColorTerminal.RED_BACKGROUND + "Gana Jugador " + (jugador+1) + " con la carta " + truco[max] + " : " + ColorTerminal.RESET);

  }

  private void swap(Carta[] array, int max, int i) {
    Carta temp = array[max];
    array[max] = array[i];
    array[i]   = temp;

  }
  
}
