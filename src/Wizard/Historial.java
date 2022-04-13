package edd.src.Wizard;

public class Historial {

    String historial; //String que contendra todo el historial de la partida.


    public Historial(){
        historial = "\n##### Este es el historial ####"; //Damos un mensaje para separar del demás contenido.
    }

    /**
     * Metodo que agrega en una nueva linea el menaje que queramos agregar al historial.
     * @param str String que deseamos añadir en una nueva linea.
     */
    public void append(String str){
        historial = historial + "\n" + str;
    }

    @Override
    public String toString(){
        return (historial + "\n######################"); //Para imprimir, solo imprimimos el historial deseado.
    }
    
}
