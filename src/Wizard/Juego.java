package edd.src.Wizard;
import  src.Estructuras.*;
import java.util.Random;

public class Juego{

    public static void main(String[] args) {

      Random rand = new Random);
      long seed = rand.nextLong();
      rand.setSeed(seed);
      System.out.println(seed);

      Baraja deck = new Baraja();
      System.out.println(deck);

      for (int i = 0; i < 10; i++) {
        deck.Shuffle(rand);
      }

      System.out.println(deck);
      deck.reiniciaBaraja();
      System.out.println(deck);

    }

}
