/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;
import java.util.ArrayList;
import java.util.*;
import java.util.LinkedList;
/**
 *
 * @author GrupoG3_EDA2
 */
public class Grafo {
    
    ArrayList<NodoGrafo> ListaNodos; //Array de tamaño variable
    int[][] matriz; //Matriz de Conexiones, 1 = conectado, 0 = no conectado
    LinkedList<Integer> adj[]; //Lista de adyacencia, en cada nodo almacena un integer (que indica el vertice) 
    int size;
    
    public Grafo(int size) //Size --> Cantidad de vertices
    {
        ListaNodos = new ArrayList<>();
        matriz = new int[size][size];
        this.size = size;
        
        adj = new LinkedList[size];  //Arreglo de Listas enlazadas
        for (int i=0; i<size; ++i)
            adj[i] = new LinkedList();
    }
    
    //Creación de vertices
    public void addNodo(NodoGrafo nodo)
    {
        ListaNodos.add(nodo);
    }
    
    //Conexión de vertices
    public void addArista(int origen, int destino)  //origen es fila, destino es columna
    {
        matriz[origen][destino] = 1; //Conecta en matriz
        adj[origen].add(destino);  //Conecta en lista
    }
    public void addArista(int origen, int destino, boolean esDirigido)  
    {
       //Conecta primer sentido
        matriz[origen][destino] = 1; 
        adj[origen].add(destino);
        if (esDirigido==false) //Si es un grafo NO dirigido
        {
            //establece conexión en el otro sentido
            matriz[destino][origen] = 1; 
            adj[destino].add(origen);
        }
    }
    
    //Revisa si dos vertices están conectados 
    public boolean checkArista(int origen, int destino) //DIRIGIDO, un solo sentido
    {
        if (matriz[origen][destino] == 1) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkArista(int origen, int destino, boolean esDirigido) //DIRIGIDO o NO DIRIGIDO
    {
        if(esDirigido == true) //Si es dirigido
        {
            if (matriz[origen][destino] == 1) 
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else  //Si es no dirigido
        {
            if (matriz[origen][destino] == 1 && matriz[destino][origen] == 1) 
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
   

    //Print de la Matriz de Conexiones
    public void print()
    {
        System.out.print("  ");
        for(NodoGrafo nodo : ListaNodos)  //Para cada nodo en la lista
        {
            System.out.print(nodo.data + " ");
        }
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) //Filas
        {
            System.out.print(ListaNodos.get(i).data + " ");
            for (int j = 0; j < matriz[i].length; j++) //Columnas
            {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public ArrayList<NodoGrafo> getListaNodos() {
        return ListaNodos;
    }

    public int[][] getMatrizAdyacencia() {
        return matriz;
    }

    public int getSize() {
        return size;
    }
    
    //MAURICIO
    public int[] greedyColoringLista()
    {
        //Arreglo donde cada indice (vertice) tendrá un color
        int resultado[] = new int[size];
        //Al principio, TODOS los vertices no tienen un color asignado
        Arrays.fill(resultado, -1);

        //Se le asigna el primero color al primer vertice
        resultado[0] = 0;

        //Arreglo que almacena la disponibilidad de los colores (se irá renovando en cada iteracion)
        //Si disponible[color] es FALSE, entonces ese color ha sido asignado a uno de sus vecinos
        boolean disponible[] = new boolean[size];       
        //Todos los colores estan disponibles al principio
        Arrays.fill(disponible, true);

        //Bucle para Asignacion de color a los vertices que quedan
        for (int j = 1; j<size; j++)
        {
            Iterator<Integer> iter = adj[j].iterator(); //Funcionalmente, Iterator empieza en una posición PREVIA al primer elemento de la lista enlazada
            while (iter.hasNext()) //Itera por CADA vecino que tenga el vertice
            {
                int vert = iter.next(); //Itera (Avanza al siguiente nodo de la lista), vert se vuelve el indice (vertice) que este nodo representa.
                if (resultado[vert] != -1)  //Si su vecino TIENE color[i] asignado
                    disponible[resultado[vert]] = false;  //Marca color[i] como NO Disponible
            }

            //Busca el primer color disponible
            int color;
            for (color = 0; color<size; color++){
                if (disponible[color])
                {
                    break;
                }
            }
            resultado[j] = color; //Le asigna el color encontrado

            //Renueva los valores del arreglo de disponibilidad a TRUE para la siguiente iteracion
            Arrays.fill(disponible, true);
        }

        return resultado;
    }
    
    //MAURICIO
    public int[] greedyColoringMatriz()
    {
        //Arreglo donde cada indice (vertice) tendrá un color
        int resultado[] = new int[size];
        //Al principio, TODOS los vertices no tienen un color asignado
        Arrays.fill(resultado, -1);

        //Se le asigna el primero color al primer vertice
        resultado[0] = 0;

        //Arreglo que almacena la disponibilidad de los colores (se irá renovando en cada iteracion)
        //Si disponible[color] es FALSE, entonces ese color ha sido asignado a uno de sus vecinos
        boolean disponible[] = new boolean[size];       
        //Todos los colores estan disponibles al principio
        Arrays.fill(disponible, true);

        //Bucle para Asignacion de color a los vertices que quedan
        for (int j = 1; j<size; j++)
        {
            
            for (int i = 0; i < size; i++) //Recorre fila de matriz de adyacencia
            {
                if (matriz[j][i] == 1) //Si es vecino
                {
                    if (resultado[i] != -1)  //Si su vecino TIENE color[i] asignado
                        disponible[resultado[i]] = false;  //Marca color[i] como NO Disponible
                }
            }
            
            //Busca el primer color disponible
            int color;
            for (color = 0; color<size; color++){
                if (disponible[color])
                {
                    break;
                }
            }
            resultado[j] = color; //Le asigna el color encontrado

            //Renueva los valores del arreglo de disponibilidad a TRUE para la siguiente iteracion
            Arrays.fill(disponible, true);
        }

        return resultado;
    }
    
    public void printColores(int[] resultado)
    {
        for(int u = 0; u < size; u++)
        {
            System.out.println("Vertice " + u + " --> Color " + resultado[u]);
        }
    }
    
    //ADOLFO
    //metodo usado en Welsh-Powell para determinar si es que un vertice ya esta en la lista de verticesMayores
    public boolean estaEnLista(int ind, int[] lista){
        for (int i = 0; i < lista.length; i++) {
            if (ind == lista[i]){
                return true;
            }
        }
        return false;
    }
    
    //codigo modificado de: https://iq.opengenus.org/welsh-powell-algorithm/ 
    //se realizaron los cambios necesarios para que el algoritmo sea iterativo y no recursivo
    //como en el ordenamiento de vertices y en el bucle de coloreo
    public int[] welshPowellColoring(){
        int colores[] = new int[size]; //lista donde se guardaran los colores asignados
        boolean coloreado[] = new boolean[size]; //lista con booleanos para ubicar los vertices coloreados
        int numConexiones[] = new int[size]; //lista con el grado de cada vertice (numero de aristas conectadas a este)
        int verticesMayores[] = new int[size]; //lista con los indices de los vertices ordenados de mayor grado a menor grado a partir de la lista numConexiones
        
        int color = 0; //inicializacion del color: 0,1,2,3,...
        
        //variable booleana que ayuda a identificar si es que se genera un conflicto entre vertices
        //TRUE: el vertice se puede colorear del color actual en el que se esta iterando, FALSE: No puede debido a que hay un vecino con el mismo color en el que se esta iterando
        boolean puedeColorear = true;
        
        //llenado de listas con valores por reemplazar
        Arrays.fill(colores, -1);
        Arrays.fill(coloreado, false);
        Arrays.fill(numConexiones, 0);
        Arrays.fill(verticesMayores, -1);
        
        //contar el numero de conexiones de cada vertice para determinar su grado
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matriz[i][j] == 1){
                    numConexiones[i]++;
                }
            }
        }
        
        //ordenar los indices de los vertices por su grado
        for (int i = 0; i < size; i++) {
            int temp = 0;
            int pos = 0;
            for (int j = 0; j < size; j++) {
                if (temp < numConexiones[j] && !estaEnLista(j,verticesMayores)){ //se compara con la variable temp y se asegura de que el vertice no haya sido ingresado anteriormente en la lista
                    temp = numConexiones[j]; //se busca el grado mas grande
                    pos = j; //se guarda el indice del vertice con el grado mas grande
                }
            }
            verticesMayores[i] = pos; //se guardan los indices en orden en la lista
        }
        
        
        for (int i = 0; i < size; i++) {
            if (!coloreado[verticesMayores[i]]){ //verifica que el vertice no tenga color aun
                    colores[verticesMayores[i]] = color; //pintar vertice
                    coloreado[verticesMayores[i]] = true;
                    for (int j = 0; j < size; j++) {
                        int ind = verticesMayores[j]; //ind servirá para seguir el orden de grado que acompaña a la teoria de Welsh-Powell
                        if (!coloreado[ind]){ //aqui se revisaran los vertices que todavia no estan coloreados en orden de grado
                            puedeColorear = true; //reinicio de la variable para cada nuevo vertice ind
                            
                            for (int k = 0; k < size; k++) {
                                if (matriz[ind][k] == 1 && colores[k] == color){ //se verifica si es que el vertice ind no es adyacente a otro vertice k y si es que este otro vertice no tiene el color actual
                                    puedeColorear = false; //si es que se cumple, quiere decir que hay conflicto y no se puede colorear con el color actual
                                    break;
                                }
                            }
                        
                            if (puedeColorear){ //no se encontro conflicto, por lo que se puede colorear los o el vertice no coloreado
                                colores[ind] = color;
                                coloreado[ind] = true;
                            }
                        }
                    }
                    color++; //pasar al siguiente color
                }
        }
        
        return colores;
    }
    
    //JOAQUIN
    //funcion que verifica que el color a asignar no genere conflicto
    public boolean isSafe(int color, int vertice, int[] listacolor){
        for (int i=0;i<this.size;i++){
            //Comprueba que en el vertice con el que conecta
            if(matriz[vertice][i]==1&&listacolor[i]==color){
                //Si el color que queremos es igual al que tiene un vertice se rechaza
                return false;
            }
        }
        //si no hay problemas retorna
        return true;
    }
    
    //funcion para asignar los colores
    public boolean backtrackingColoring(int origen, int[] colores, int limite){
        if (origen>=size) return true;
        //Definimos como c al numero total de colores asignables
        for (int c=0; c<limite;c++){
            //Se llama a la funcion isSafe para ver si el color que se le va a dar esta usada por
            //otro vertice
            if(isSafe(c, origen, colores)){
                colores[origen]=c; //asignar color antes de retornar
                //vuelve a llamar a la funcion, si recibe un true vuelve a realizar
                //la funcion y retorna true, si recibe false no se vuelve a
                //ejecutar y retorna false
                if (backtrackingColoring(origen+1, colores, limite)){ //llama a la funcion
                    return true;
                }
                colores[origen]=-1;
            }
        }
        return false;
    }
    
    //funcion que llama la funcion y retorna la lista de colores
    public int[] MegaBTC(){
        //Donde se almacena los colores
        int[] colores = new int[size];
        //el primer vertice
        int origen = 0;
        //primero se asigna sin color a todos los vertices
        for(int i=0; i<colores.length; i++){
            colores[i]=-1;
        }
        //si nos da false todo significa que se puede realizar el coloreado
        //si nos da true en algun momento retorna la lista de colores como esta
        if(!backtrackingColoring(origen, colores, numMinino())){
            return colores;
        }
        return colores;
    }
    
    //funcion que nos devuelve el limite porque backtracking nos devuelve
    //un grafo coloreado no optimo si no limitamos el numero limite de colores
    public int numMinino(){
        int origen=0;
        int[] soporte = new int[size];
        for(int i=0;i<size;i++){
            for (int j = 0; j < soporte.length; j++) {
                soporte[j] = -1;
            }
            //realiza backtracking
            if(backtrackingColoring(origen, soporte, i)){
                return i;
            }
        }
        return size;
    }
    
    
    // codigo inspirado de: https://www.geeksforgeeks.org/dsa/dsatur-algorithm-for-graph-coloring/
    // no copie el código, pero la lectura del código y la explicación me ayudó a implementar mi versión
    //RAFAEL
    public int[] DSaturColoring() {
        int[] colores = new int[size]; // arreglo con los colores de cada vertice
        int[] grados = new int[size]; // arreglo con los grados de cada vertice
        int[] saturacion = new int[size]; // arreglo con la saturacion (n° de colores adyacentes) de cada vertice
        boolean[] coloreado = new boolean[size]; // arreglo que verifica si ya se pintó el vertice
        
        // inicializar arreglo de hashsets
        // un hashset permite almacenar valores unicos (no repetidos)
        // se usara para almacenar los colores usados por los vecinos de cada vertice
        HashSet<Integer>[] coloresVecinos = new HashSet[size]; 
        for (int i = 0; i < size; i++) {
            coloresVecinos[i] = new HashSet<>();
        }        
        
        // calcular el grado (cant de vertices adyacentes) de cada vertice
        for (int i = 0; i < size; i++) {
            int cont = 0;
            for (int j = 0; j < size; j++) {
                if (matriz[i][j] == 1) {
                    cont++;
                }
            }
            grados[i] = cont;
        }    
        
        // elegir vertice con mayor grado y colorear con el primer color
        int mayor = 0;
        for (int i = 1; i < size; i++) {
            if (grados[i] > grados[mayor]) {
                mayor = i;
            }
        }
        
        colores[mayor] = 1;
        coloreado[mayor] = true;
        
        // cambiar la saturación de los vecinos del primer vertice a 1 y almacenar el color
        for (int j = 0; j < size; j++) {
            if (matriz[mayor][j] == 1) {
                coloresVecinos[j].add(1);
                saturacion[j] = 1;
            }
        }
        
        // empezar a colorear los demas vértices
        for (int cont = 1; cont < size; cont++) 
        {
            
            // elegir vertice con mayor saturacion
            int elegido = -1;
            int maxSaturacion = -1;
            for (int i = 0; i < size; i++) {
                if (!coloreado[i]) {
                    // caso 1: saturacion de i es mayor al elegido
                    if (elegido == -1 || saturacion[i] > maxSaturacion) {
                            elegido = i;
                            maxSaturacion = saturacion[i];
                        }
                    else {
                        // caso 2: si hay empate, se define por el vertice con mayor grado
                        if (saturacion[i] == maxSaturacion ) {
                            if (grados[i] > grados[elegido]) {
                                    elegido = i;
                                }
                            }
                        }
                    }
                }

            // recorrer HashSet hasta encontrar el menor color no usado
            int colorMenor = 1;
            while (coloresVecinos[elegido].contains(colorMenor)) {
                colorMenor++;
            }
        
            // asignar color al vertice (que tenia mayor saturacion) y marcar como coloreado
            colores[elegido] = colorMenor;
            coloreado[elegido] = true;
            
            // actualizar saturacion de los vecinos del nodo actual
            // solo se actualiza de los no coloreados porque los coloreados ya no se necesita, porque ya estan coloreados
            for (int j = 0; j < size; j++) {
                // si el nodo que se acaba de colorear tiene un vecino y no ha sido coloreado
                if (matriz[elegido][j] == 1 && !coloreado[j]) {
                   // intentar añadir el color utilizado al vecino encontrado, si se añade aumentar la saturacion del vecino
                    if (coloresVecinos[j].add(colorMenor)) {
                        saturacion[j]++;
                    }
                }
            }
        }           
        return colores;
    }
    

    }