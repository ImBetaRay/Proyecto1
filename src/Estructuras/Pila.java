package edd.src.Estructuras;

public class Pila<T> extends PushPop<T>{


    // Agregar al inicio.
    public void push(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("");
        }
        Nodo aux = new Nodo(elemento);
        if(isEmpty()){
            this.cabeza=ultimo=aux;
            longi++;
            return ;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi ++;

    }

    /**
     * Regresa un clon de la estructura.
     *
     * @return un clon de la estructura.
     */
     public Pila<T> clone(){
         Pila<T> nueva     = new Pila<T>();
         Lista<T> corregir = new Lista<T>();
         if (this.isEmpty()) {
             return nueva;
         }
         corregir.add(this.cabeza.elemento);
         Nodo n = this.cabeza;
         while (n.siguiente != null) {
            corregir.add(n.siguiente.elemento);
            n = n.siguiente;
         }
         corregir.reverse();
         IteradorLista<T> iter = corregir.iteradorLista();
         while(iter.hasNext()){
           nueva.push(iter.next());
         }
         return nueva;
     }


    public String toString(){
        if (this.isEmpty()) {
            return "";
        }
        String regreso = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            regreso += ", " + n.siguiente.elemento.toString();
            n = n.siguiente;
        }
        return regreso;
    }


}
