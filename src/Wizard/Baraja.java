package edd.src.Wizard;
import  edd.src.Estructuras.*;
import java.util.Random;

public class Baraja {

  private class Carta {

    private String palo;
    private int numero;

    public Carta(int numero, String palo){
      this.palo = palo;
      this.numero = numero;
    }

    @Override
    public String toString() {
      String aux = this.palo + numero + ColorTerminal.RESET;
      return aux;
    }

  }

  private Lista<Carta> deck;

  public Baraja() {
    reiniciaBaraja();
  }

  public void reiniciaBaraja() {
    deck = new Lista<Carta>();
    for (int i = 0; i <  4; i++) {
      for (int j = 0; j < 15; j++) {
        switch (i) {
          case 0:
            deck.add(new Carta(j, ColorTerminal.RED)); break;
          case 1:
            deck.add(new Carta(j, ColorTerminal.BLUE)); break;
          case 2:
            deck.add(new Carta(j, ColorTerminal.GREEN)); break;
          case 3:
            deck.add(new Carta(j, ColorTerminal.YELLOW)); break;
        }
      }
    }
  }

  public void Shuffle(Random seed){
    int cantidadCartas = deck.size();

    seed.nextInt();
    for (int i = 0; i < cantidadCartas; i++) {
        int pos = i + seed.nextInt(cantidadCartas - i);
        swap(i, pos);
    }

  }

  private void swap(int posInicial,int posFinal){
    IteradorLista<Carta> iter = deck.iteradorLista();
    Carta cInicial = null;
    Carta cFinal = null;

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
