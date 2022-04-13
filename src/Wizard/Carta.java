package edd.src.Wizard;

public class Carta {

  private String palo; // El palo es representado mediante un string que altera el color de la terminal.
  private int numero; // El valor de la carta es un entero de 0 a 14.

  public Carta(int numero, String palo){
    this.palo = palo;
    this.numero = numero;
  }

  @Override
  public String toString() {
    if (this.numero == 0) { //Si el valor es 0, es la carta más baja por lo tanto es un bufon.
      String aux = ColorTerminal.PURPLE_BOLD + "Bufon" + ColorTerminal.RESET;
      return aux;
    }
    if (this.numero == 14) { // Si el valor es 14, es el valor más alto por lo tanto es un mágo.
      String aux = ColorTerminal.PURPLE_BOLD + "Mago" + ColorTerminal.RESET;
      return aux;
    }
    String aux = this.palo + numero + ColorTerminal.RESET;
    return aux;
  }

  /**
   * Metodo que devuelve el valor de la carta.
   * @return El valor en forma de entero de la carta.
   */
  public int getNumero(){
    return this.numero;
  }

  /**
   * Metodo para obtener el palo en forma de string de una carta.
   * @return El palo de la carta en fomra de string.
   */
  public String getPalo(){
    return this.palo;
  }

}
