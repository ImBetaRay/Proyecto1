package edd.src.Wizard;
import  edd.src.Estructuras.*;
import java.util.Random;

public class Baraja {

  public Lista<Carta> deck; // Una lista de cartas que representa lo que seria el deck en fisico.

  public Baraja() {
    this.deck = new Lista<Carta>(); //Instanciamos la lista.
    reiniciaBaraja(); //Creamos la baraja.
  }

  /**
   * Metodo que reinicia la baraja, es decir coloca todas las cartas en orden y si es que faltan vuelve a incluir las faltantes. 
   */
  public void reiniciaBaraja() {
    this.deck = new Lista<Carta>();  // Reiniciamos la lista.
    for (int i = 0; i <  4; i++) {   // Para añadir los cuatro palo.
      for (int j = 0; j < 15; j++) { // Para las cartas del 0 al 14.
        switch (i) {
          case 0:
            deck.add(new Carta(j, ColorTerminal.RED_BOLD)); break;
          case 1:
            deck.add(new Carta(j, ColorTerminal.BLUE_BOLD)); break;
          case 2:
            deck.add(new Carta(j, ColorTerminal.GREEN_BOLD)); break;
          case 3:
            deck.add(new Carta(j, ColorTerminal.YELLOW_BOLD)); break;
        }
      }
    }
  }

  /**
   * Metodo que mezcla la baraja para poder jugar con las carta en un orden aleatorio.
   * @param seed Un objeto de tipo random que será usado para poder barajar y en caso de ser necesario
   *             poder barajar con una semilla en concreto.
   */
  public void Shuffle(Random seed){
    reiniciaBaraja();
    int cantidadCartas = deck.size();

    seed.nextInt();
    for (int m = 0; m < 10; m++){
      for (int i = 0; i < cantidadCartas; i++) {
          int pos = i + seed.nextInt(cantidadCartas - i);
         swap(i, pos);
      }
    }
  }

  /**
   * Metodo que intercambia la posición de dos elementos de una lista (la creada en el laboratorio).
   * @param posInicial Posición del primero objeto a permutar.
   * @param posFinal   Posición del segundo objeto a permutar.
   */
  private void swap(int posInicial,int posFinal){
    IteradorLista<Carta> iter = deck.iteradorLista();
    Carta cInicial = null;
    Carta cFinal = null;

    //For para recorrer y guardar los elementos de las posiciones dadas en variables auxiliares.
    for (int i = 0; i < deck.size(); i++) { 
      if (i == posInicial) {
        cInicial = iter.next();
        if (i != posFinal) continue;
      }
      if (i == posFinal) {
        if (posFinal == 59) {
          cFinal = cInicial;
          break;
        }
        cFinal = iter.next();
        break;
      }
      iter.next();
    }

    //Eliminamos los elementos y colocamos donde debian ir.
    deck.delete(cInicial);
    deck.insert(posInicial, cFinal);
    deck.delete(cFinal);
    deck.insert(posFinal, cInicial);
  }

  @Override
  public String toString() {
    return deck.toString();
  }

}
