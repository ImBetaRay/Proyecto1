package edd.src.Wizard;

import java.util.NoSuchElementException;
import edd.src.Estructuras.Lista;

import java.util.Scanner;
import java.lang.Math;


public class Jugador {
    /*
    * Todo jugador debe tener una mano y una cantidad de puntos acumulada.
    * Extra tenmos un contador de trucos ganados <code>ganadas</code> para comparar con las apostadas y 
    * hacer el calculo de puntos, tambíen extra tenemos un scanner para leer los enteros pedidos.
    */
    private Lista<Carta> mano; 
    private int puntos;
    private Scanner scanner;
    private int apuesta;
    private int ganadas;

    //Iniciamos todos nustros atributos.
    public Jugador(){
	this.mano = new Lista<Carta>();
	this.puntos = 0;
    scanner = new Scanner(System.in);
    apuesta = 0;
    ganadas = 0;
    }

    /**
     * Metodo para regresar la cantidad de puntos de un jugador.
     * @return puntos</code> Los puntos en la partida del jugador.
     */
    public int getPuntos(){
	return this.puntos;
    }

    /**
     * Metodo que añade una carta a la mano, simula la acción de tomar una carta/robar en el juego fisico.
     * @param robo La carta que se añadirá a la mano.
     */
    public void roba(Carta robo) {
        this.mano.add(robo);
    }


    /**
     * Metodo que juega cualquier carta de nuestra mano.
     * @return <code>carta</code> Carta que se jugara en el truco.
     */
    public Carta juegaCarta(){
        System.out.println("Esta es tu mano: " + mano); //Imprime la mano.
	    String str = "Puedes elegir alguna de las siguientes (indices de 0 a " + (mano.size()-1) + "): " + mano; 
        Carta carta = mano.get(leeEntero(str, (mano.size()-1))); //Obtenos la carta en el indice deseado.
        mano.delete(carta); //elimina esa carta de la mano que tenemos.
        WizardUI.hist.append("El jugador, jugó: " + carta); //Añadimos la carta al historial.
    	return carta;
	
    }

    /**
     * Metodo que juega una carta de un palo dado, en caso de no tener cartas de ese palo, bufones o magos
     * se permite jugar cualquier carta llamando al metodo <code>juegaCarta()</code>.
     * @param paloLider Palo del cual se tiene que jugar una carta de la mano.
     * @return <code>carta</carta> La carta del palo dado a jugar.
     */
    public Carta juegaCarta(Carta paloLider){
        Lista<Integer> opciones = new Lista<Integer>(); // Cremos una lista que contendra los indices de las cartas a jugar.
        
        //For que revisa toda la mano y añade a las opciones el indice de lar cartas con el mismo palo o de los magos.
        for (int i = 0; i < mano.size(); i++) {
            if((mano.get(i).getPalo().equals(paloLider.getPalo())) || (mano.get(i).getNumero() == 14 || mano.get(i).getNumero() == 0 )){
                opciones.add(i); 
            }
        }

        //Si no hay cartas del palo, magos o bufones podemos jugar cualquiera.
        if (opciones.size() == 0) {
            return juegaCarta();
        }  

        System.out.println("Esta es tu mano: " + mano);
        
	    String str1  = "Puedes elegir alguno de los siguientes indices: " + opciones + "\nPreferentemente juega la carta más alta del palo líder.";

        Boolean bool = true;
        int eleccion; 
        //For que ciclará hasta que el usuario de una opción valida.
        while (bool == true) {
            eleccion = leeEntero(str1, (mano.size()-1));
            if (opciones.contains(eleccion)) {
                Carta carta = mano.get(eleccion);
                mano.delete(carta);
                WizardUI.hist.append("El jugador, jugó: " + carta);
    	        return carta;
            } else {
                System.out.println("Esta carta no la puedes jugar. Elige una que sí pertenezca a las opciones dadas.");
            }
        }

        return null;
    }

    /**
     * Metodo que lee un entero otorgado por el jugador mediante el teclado y es la cantidad de trucos que
     * espera ganar el usuario.
     * @param max Número de trucos posibles por apostar.
     * @param numJ Número del jugador que apostará.
     */
    public void apostar(int max, int numJ) {
        this.apuesta = leeEntero("Cúantos trucos ganaras jugador" + numJ +"?", max);
        WizardUI.hist.append("El jugador apostó " + apuesta + " trucos.");
    }

    /**
     * Metodo que suma uno a los trucos ganados para el calculo de puntos.
     */
    public void ganaTruco(){
        this.ganadas++;
    }

    /**
     * Metodo que calcula los puntos finales de cada partida, se calcula de la forma siguiente.
     * Si a  = t, entonces su puntuación será 20 + 10t.
     * Si a != t, entonces su puntuación será −10 · |a − t| (esto es, se restan 10
     * puntos por cada truco que haya estado debajo o sobre su apuesta).
     * Donde "t" son los trucos ganados y "a" los apostados. 
     */
    public void finalRonda() {
        if (ganadas == apuesta) {
            puntos = puntos + (20 + (10*ganadas));
        } else {
            puntos = puntos + ((-10) * (Math.abs(apuesta - ganadas)));
        }

        System.out.println("Tienes " + puntos + "puntos.");
        WizardUI.hist.append("Terminó con " + puntos + ".");
    }

    /**
     * Metodo que lee un entero que otorga el usuario por el teclado y devuelve este mismo.
     * @param mensaje El mensaje que se le dará al lector para que escriba un entero.
     * @param max     El máximo valor que puede tener ese entero (sirve para regular las opciones).
     * @return El primer número valido que otorgue el usuraio.
     */
    private int leeEntero(String mensaje, int max) {
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

 
    @Override
    public String toString(){
	return mano.toString(); //Para representar al jugador solo imprimimos la mano.
    }
    
}
