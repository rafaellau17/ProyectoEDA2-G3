/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import Ventanas.MostrarGrafo;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Grupo03-EDA2
 */
public class Main 
{
    
    public static void main(String[] args) {
        // Grafo de prueba con 10 verticesm n = 10
        Grafo grafo1 = new Grafo(10);
       
        grafo1.addNodo(new NodoGrafo("SIS. ORGA"));
        grafo1.addNodo(new NodoGrafo("I.A. APLICADA"));
        grafo1.addNodo(new NodoGrafo("CALC. II"));
        grafo1.addNodo(new NodoGrafo("FISICA. SIS")); 
        grafo1.addNodo(new NodoGrafo("INTRO. PROGRA"));
        grafo1.addNodo(new NodoGrafo("ESTRUC. DISC"));
        grafo1.addNodo(new NodoGrafo("COSTEO OPE."));
        grafo1.addNodo(new NodoGrafo("ESTAD. PROB"));
        grafo1.addNodo(new NodoGrafo("CALC. III"));   
        grafo1.addNodo(new NodoGrafo("MOD. SISTEMAS"));   
        
        grafo1.addArista(0, 1, false);  
        grafo1.addArista(1, 2, false);  
        grafo1.addArista(2, 3, false);  
        grafo1.addArista(3, 4, false);  
        grafo1.addArista(4, 0, false);  
        grafo1.addArista(5, 6, false);  
        grafo1.addArista(6, 7, false);  
        grafo1.addArista(7, 8, false);  
        grafo1.addArista(8, 9, false);  
        grafo1.addArista(9, 5, false);  
        grafo1.addArista(0, 5, false);
        grafo1.addArista(2, 7, false);
        grafo1.addArista(4, 9, false);
        grafo1.addArista(1, 8, false);
        grafo1.addArista(3, 6, false);

        
        while(true) {
            int seleccionado = menuAlgoritmo();
            
            if (seleccionado == 5) {
                break;
            }
            
            int[] resultadoColores = null;
            long tiempoInicio = System.nanoTime();
            switch (seleccionado) {
                case 1 -> resultadoColores = grafo1.greedyColoringLista();
                case 2 -> resultadoColores = grafo1.welshPowellColoring();
                case 3 -> resultadoColores = grafo1.MegaBTC();
                case 4 -> resultadoColores = grafo1.DSaturColoring();
            }
            long tiempoFinal = System.nanoTime();
            System.out.println("Tiempo ejecucion: "+(tiempoFinal-tiempoInicio)+" nanosegundos.");
            mostrarGrafo(grafo1, resultadoColores, seleccionado);        
        }
    }
    
    
    public static int menuAlgoritmo() {
        Scanner sc = new Scanner(System.in);
        int seleccion = 0;
        System.out.println("=========================================");
        System.out.println("      Menu de Seleccion de Algoritmo     ");
        System.out.println("1. Algoritmo Greedy");
        System.out.println("2. Algoritmo de Welsh-Powell");
        System.out.println("3. Algoritmo Backtracking");
        System.out.println("4. Algoritmo DSatur");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------");
        
        boolean numValido = false;
        while (!numValido) {
            System.out.print("Ingresar opcion: ");
            seleccion = sc.nextInt();
            
            if (seleccion >= 1 && seleccion <= 5) {
                numValido = true;
            } 
            else {
                System.out.println("Opcion no valida.");
            }
        }
        return seleccion;
    }
    
    
    public static void mostrarGrafo(Grafo g1, int[] resultado, int numAlgo) {
        JFrame frame = new JFrame("Visualizador de Grafo");            
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // usar grafo creado como argumento para la ventana que muestra el grafo
        MostrarGrafo graphPanel = new MostrarGrafo(g1, resultado, numAlgo);
        frame.add(graphPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }    
     
}
