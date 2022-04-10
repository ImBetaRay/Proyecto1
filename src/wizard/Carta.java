package edd.src.wizard;

public class Carta {

  private String palo;
  private int numero;

  public Carta(int numero, String palo){
    this.palo = palo;
    this.numero = numero;
  }

  @Override
  public String toString() {
    if (this.numero == 0) {
      String aux = ColorTerminal.WHITE_BACKGROUND + "Bufon" + ColorTerminal.RESET;
      return aux;
    }
    if (this.numero == 14) {
      String aux = ColorTerminal.WHITE_BACKGROUND + "Mago" + ColorTerminal.RESET;
      return aux;
    }
    String aux = this.palo + numero + ColorTerminal.RESET;
    return aux;
  }

  public int getNumero(){
    return this.numero;
  }

  public String getPalo(){
    return this.palo;
  }

}
