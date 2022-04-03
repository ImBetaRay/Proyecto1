package edd.src.Estructuras;
import java.util.Iterator;
import java.util.NoSuchElementException;
// iterador
//next

public class Lista<T> implements Collection<T> {

    // Clase Nodo
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }
    }

    // Iterador
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente;

        public Iterador(){
            siguiente = cabeza;
        }

        @Override public boolean hasNext(){
            return siguiente != null;
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;

            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;

        }

        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start(){
            this.anterior = null;
            this.siguiente = cabeza;
        }

        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }

    }

    private Nodo cabeza;
    private Nodo ultimo;
    private int longi;

    /**
     * Agrega un elemento a la lista.
     *
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }


    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
	Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.ultimo = nuevo;
        }
        else{
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n !=null){
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param elemento el elemento a eliminar.
     */
    public boolean delete(T elemento){
        if(elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);
        if(n==null){
            return false;
        }
        if(longi == 1){
            empty();
            return true;
        }
        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longi --;
            return true;
        }
        if (n == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            longi --;
            return true;
        }
        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longi --;
        return true;
    }



    /**
     * Regresa un elemento de la lista. (Ultimo)
     * y lo elimina.
     *
     * @return El elemento a sacar.
     */
    public T pop(){
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi --;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     *
     * @return el número de elementos en la lista.
     */
    public int size(){
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     *
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento){
        if(buscaElemento(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     *
     */
    public void empty(){
        cabeza =ultimo= null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     *
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return longi == 0;
    }



    /**
     * Regresa una copia de la lista.
     *
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     *
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion){
        // lo vemos en clase
        if(coleccion instanceof Lista) {
            return true;
        }
        return false;
    }



    /**
     * Metodo que invierte el orden de la lista, se crean dos nodos por decirlo de alguna forma un nodo va hacia el frente
     * de la lista original (auxCab) y el otro es quien va hacia atras para construir la lista (aux) intercambiando referencias.
     * El metodo se completa en orden lineal pues la cantidad de acciones ejecutadas
     * dentro del while es n lo cual es igual a n para toda n >= 0 es por lo que reverse in O(n).
     * La memoria es constante ya que solo se crean 2 nodos, no se genera algun elemento
     * fuera de estos dos nodos, solo se juega con las referencias.
     */
   public void reverse() {
        // Tu codigo aqui
        if (this.longi < 2) {
          return;
        }

        Nodo auxDelante = this.cabeza;
        Nodo auxAtras   = this.cabeza;
        this.cabeza     = this.ultimo;
        this.ultimo     = auxDelante;
        while (auxAtras != null) {
          auxDelante         = auxAtras.siguiente;
          auxAtras.siguiente = auxAtras.anterior;
          auxAtras.anterior  = auxDelante;
          auxAtras           = auxAtras.anterior;
        }
    }

    /**
     * Regresa una representación en cadena de la coleccion.
     *
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     */
    public String toString(){
        // Tu codigo aqui
        Nodo nodo = this.cabeza;
        if (this.cabeza == null) {
          return "";
        }
        String str = "";
        while (nodo.siguiente != null) {
            str = str + nodo.elemento + " -> ";
            nodo = nodo.siguiente;
        }
        str = str + nodo.elemento; // Como el ultimo elemento no lleva flecha, nos detemos uno antes para agregarlo sin ella.
        return str;

    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     *
     */

    public void append(Lista<T> lista) {
        // Tu codigo aqui

        if (lista.isEmpty()) {
	    return;
        }
        if (this.isEmpty()) {
	    this.cabeza = lista.cabeza;
	    this.ultimo = lista.ultimo;
	    this.longi  = lista.longi;
	    return;
        }

        this.ultimo.siguiente = lista.cabeza;
        lista.cabeza.anterior = this.ultimo;
        this.ultimo           = lista.ultimo;
        longi                 = this.longi + lista.longi;
    }


    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importará la primera aparición del elemento
     * Empieza a contar desde 0.
     *
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento, -1 si es que no está en la lista.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public int indexOf(T elemento) {
        // Tu codigo aqui
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }

        int index = 0;
        Nodo aux = this.cabeza;
        while(aux != null){
          if (aux.elemento.equals(elemento)) {
            return index;
          }
          aux = aux.siguiente;
          index++;
        }
        return (-1);
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al final de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     *
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        // Tu codigo aqui
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        if (i <= 0) {
          agregaInicio(elemento);
          return ;
        }
        if (i >= this.longi) {
          agregaFinal(elemento);
          return ;
        }

        Nodo nuevo = new Nodo(elemento);
        Nodo aux = this.cabeza;
        for (int j = 0; j < i; j++) {
          aux = aux.siguiente; //Recorremos la lista hasta encontrar el elemento en la posicion i para recorrerlo.
        }
        //Cambiamos referencias para recorrer el arreglo ya que elemento va en la posicion i
        nuevo.anterior = aux.anterior;
        nuevo.siguiente = aux;
        aux.anterior.siguiente = nuevo;
        aux.anterior = nuevo;
        longi++;
    }

    /**
     * Mezcla dos listas poniendo uno y uno de cada lista comenzando por la lista
     * con la que se llama al metodo.
     *
     * El metodo pertenece a O(n+m) pues en el peor de los casos solo se llama a la función recursiva
     * una cantidad de veces igual a la lista con menor longitud a saber n y como n <= n+m para todo n >= 0
     * y para todo m >= 0 entonces el orden de mezclaAlternada pertenece a O(n+m).
     *
     * El espacio es constante, pues en ningún momento se crea alguna variable, nodo o lista por lo que
     * el almacenamiento es constante.
     *
     * @param lista La lista que queremos mezclar a la llamada.
     */
   public void mezclaAlternada(Lista<T> lista){
      if (lista.isEmpty()) {
        return;
      }
      if (this.isEmpty()) {
        this.cabeza = lista.cabeza;
        this.ultimo = lista.ultimo;
        this.longi  = lista.longi;
        return;
      }

      mezclaAlternada(this.cabeza, lista.cabeza);
      this.longi = this.longi + lista.longi;
    }

    /**
     * Mezcla dos listas poniendo uno y uno de cada lista comenzando por la lista
     * con la que se llama al metodo, de manera recursiva.
     *
     * Cuando alguno de los dos nodos es igual a null signfica que la lista no continua y debemos
     * agregar los elementos restantes al otro nodo puesto que asi conservaras las referencias a
     * todos los siguientes elementos de la lista que no es null si es que los hay.
     *
     * EL metodo se manda a llamar recursivamente hasta que llegamos al final de alguna lista, donde
     * se hace lo mencionado anteriormente, posteriormente se cambian las referencias "de atras hacia adelante"
     * para conservar los anteriores de cada uno y no perder información alguna mientras cmabiamos, sin la necesidad
     * de crear nuevos nodos auxuliares.
     *
     * @param nodoLista1 El nodo de la lista con la que fue llamado el metodo publico original
     *                   es el que va primero cuando mezclamos las listas.
     * @param nodoLista2 El nodo a insertar en la lista despues de nodo 1.
     */
    private void mezclaAlternada(Nodo nodoLista1, Nodo nodoLista2){
      if (nodoLista1.siguiente == null) {
        nodoLista1.siguiente = nodoLista2;
        nodoLista2.anterior  = nodoLista1;
        return;
      }
      if (nodoLista2 == null) {
        return;
      }
      mezclaAlternada(nodoLista1.siguiente, nodoLista2.siguiente);
      nodoLista2.siguiente = nodoLista1.siguiente;
      nodoLista1.siguiente = nodoLista2;
      nodoLista2.anterior = nodoLista1;


    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
