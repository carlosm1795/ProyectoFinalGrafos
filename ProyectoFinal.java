
package proyectofinal;

import java.io.IOException;
import java.util.Scanner;




public class ProyectoFinal {

    
    
    public static void main(String[] args) throws IOException  {
        
        /*
        String [][] datos = new String [6][4];
        String [][] datos2;
       
        
        Grafo estructura = new Grafo();
        estructura.dirigido = true;
        
        //estructura.agregarNodoAlGrafo(datos[0][0], datos);
        datos2 = estructura.muestraContenido("C:\\Users\\Usuario\\Desktop\\Carlos\\Lugares.txt");
        estructura.numVertices = datos2.length/2;
        estructura.agregarNodoAlGrafo(datos2[0][0], datos2);
        
        //estructura.mostrarInfoDeGrafos(); working
        
        estructura.arrancadorAnchura(); 
        estructura.arrancadorProfundidad();
        
        //estructura.buscarDestino("Tres Rios");
        //estructura.mostrarCaminoEntreDosCiudades("San Jose", "Cartagopo");
         //System.out.println(estructura.lugares[0].nombreLugar);
        */
        menu();
    }
    public static void menu() throws IOException{
        int opccion=0;
        String [][] datos2;
        Grafo estructura = new Grafo();
        estructura.dirigido = true;
        Scanner teclado = new Scanner(System.in);
        
        do{
            
            System.out.println("################### Menu ################################");
            System.out.println("1- Leer archivo y cargar nodo");
            System.out.println("2- Mostrar en Anchura");
            System.out.println("3- Mostrar en Profundidad");
            System.out.println("4- Mostrar Toda la info del grafo");
            System.out.println("5- Salir ");
            System.out.println("################### Menu ################################");
            opccion = teclado.nextInt();
            generarEspacios();
            switch(opccion){
                case 1:

                    datos2 = estructura.muestraContenido("C:\\Users\\Usuario\\Desktop\\Carlos\\Lugares.txt");
                    estructura.numVertices = datos2.length/2;
                    estructura.agregarNodoAlGrafo(datos2[0][0], datos2);
                    System.out.println("Info Cargada");
                    break;
                case 2:
                    System.out.println("Resultado de Anchura");
                    estructura.arrancadorAnchura();
                    
                    break;

                case 3:
                    System.out.println("Resultado de Profundidad");
                    estructura.arrancadorProfundidad();
                    
                    break;
                case 4:
                    System.out.println("Info De Grafos");
                    estructura.mostrarInfoDeGrafos();
                    
                    break;
                case 5:
                    break;

            }
        }while(opccion<5);
    }
    
    public static void generarEspacios(){
    
        for(int x=0;x<50;x++){
            System.out.println();
        }
    }
    
}
