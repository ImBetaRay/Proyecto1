package edd.src.wizard;

import java.util.NoSuchElementException;
import edd.src.Estructuras.Lista;
import edd.src.wizard.Carta;

import java.util.Scanner;
import java.lang.Math;


public class Jugador {

    private Lista<Carta> mano;
    private int puntos;
    private Scanner scanner;
    private int apuesta;
    private int ganadas;

    public Jugador(){
	this.mano = new Lista<Carta>();
	this.puntos = 0;
    scanner = new Scanner(System.in);
    apuesta = 0;
    ganadas = 0;
    }

    public int getPuntos(){
	return this.puntos;
    }

    public void roba(Carta robo) {
        this.mano.add(robo);
    }


  
    public Carta juegaCarta(){
	    String str = "puedes elegir alguna de las siguientes (indices de 0 a " + (mano.size()-1) + "): " + mano;
        Carta carta = mano.get(leeEntero(str, mano.size()));
        mano.delete(carta);
    	return carta;
	//elimina esa carta de la mano que tenemos.
    }


    public Carta juegaCarta(Carta paloLider){
        Lista<Integer> opciones = new Lista<Integer>();
        
        for (int i = 0; i < mano.size(); i++) {
            if(mano.get(i).getPalo() == paloLider.getPalo()){
                opciones.add(i);
            }
        }

        if (opciones.size() == 0) {
            return juegaCarta();
        }

	    String str  = "Puedes elegir alguna de las siguientes: " + mano;
	    String str1 = str + "\nPreferentemente juega la carta más alta del palo líder.";

        Boolean bool = true;
        int eleccion;
        while (bool == true) {
            eleccion = leeEntero(str1, opciones.size());
            if (opciones.contains(eleccion)) {
                Carta carta = mano.get(eleccion);
                mano.delete(carta);
    	        return carta;
            } else {
                System.out.println("Esta carta no pertenece a tu mano. Elige una que sí pertenezca a esta.");
            }
        }

        return null;
    }

    public void apostar(int max, int numJ) {
        this.apuesta = leeEntero("Cúantos trucos ganaras jugador" + numJ +"?", max);
    }


    public void ganaTruco(){
        this.ganadas++;
    }

    public void finalRonda() {
        if (ganadas == apuesta) {
            puntos = 20 + (10*ganadas);
        } else {
            puntos = (-10) + (Math.abs(apuesta - ganadas));
        }

        System.out.println("Tienes " + puntos + "puntos.");
    }

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
	return mano.toString();
    }
    
}
