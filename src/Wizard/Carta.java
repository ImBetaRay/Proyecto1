package edd.src.Wizard;

public class Carta {

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

  public int getNumero(){
    return this.numero;
  }

  public String getPalo(){
    return this.palo;
  }

}
