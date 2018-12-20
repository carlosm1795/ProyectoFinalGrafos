
package proyectofinal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grafo {
    private NodoListaLugar nodo;
    
    int posicionEnUso = 0;
    boolean dirigido;
    int numVertices;
    public NodoListaLugar []lugares;
    public void Grafo(boolean pDirigido,int pNumVertices){
        dirigido = pDirigido;
        numVertices = pNumVertices;
        
    }
    
    public void testing(String [][]datosLugares){
        for(int y=0;y<datosLugares.length;y+=2){
            System.out.println("El lugar tiene el nombre de," + datosLugares[y][0]);
        }
    }
    public void agregarNodoAlGrafo(String porigen,String [][] datosLugares){
        lugares = new NodoListaLugar[numVertices];
        int posicionEnLugarx=0;
        for(int y=0;y<datosLugares.length;y+=2){
            // Crear el nuevo nodo lista lugar que se va  a encontrar en la posicion en uso del arreglo 
            nodo = new NodoListaLugar();
            nodo.nombreLugar = datosLugares[y][0];
            //Almacenar en la poscion disponible el nombre del lugar de origen
            lugares[posicionEnLugarx] = nodo;
            posicionEnLugarx++;
        }
        
        int num =0;
        for(int y =0;y<datosLugares.length;y+=2){
            for(int index = 1; index<datosLugares[0].length;index++){
            recursivaInsertarGrafo(lugares[num],datosLugares,index);
            }
            incrementarPosicionEnUso();
            num++;
        }
 
    }
    
    public void recursivaInsertarGrafo(NodoListaLugar lugar,String [][] datosLugares,int iteracion){
        if(lugar.sig== null){
            if(datosLugares[posicionEnUso][iteracion] != null){
                NodoListaLugar nodoAux = new NodoListaLugar();
                nodoAux.nombreLugar = datosLugares[posicionEnUso][iteracion];
                nodoAux.distancia = datosLugares[posicionEnUso+1][iteracion-1];
                nodoAux.sig = null;
                lugar.sig = nodoAux;
            }
            
            return;
        }else{
            recursivaInsertarGrafo(lugar.sig,datosLugares,iteracion);
        }
    
    }
    
    public void mostrarInfoDeGrafos(){
        
        for (int index = 0; index<lugares.length;index++){
            System.out.println("EL nombre del lugar es:" + lugares[index].nombreLugar);
            System.out.println("Estas son las rutas disponibles");
            System.out.println (mostrarInfo(lugares[index].sig));
        }
       
    }
    
    public String mostrarInfo(NodoListaLugar lugar){
        if(lugar== null){
            return "";
        }else{
            return lugar.nombreLugar + ", que esta a " + lugar.distancia + "km," + mostrarInfo(lugar.sig);
        }
        
    }
    public void incrementarPosicionEnUso(){
        posicionEnUso = posicionEnUso + 2;
    }
    
    public void mostrarCaminoEntreDosCiudades(String pnombreOrigen,String pNombreDestino){
        int ciudadOrigen = encontrarCiudad(pnombreOrigen);
        int ciudadDestino = encontrarCiudad(pNombreDestino);
        
        if(ciudadOrigen > -1 && ciudadDestino > -1){
            
        }else{
            if(ciudadOrigen == -1){
                System.out.println(pnombreOrigen+", no encontrado");
            }else if(ciudadDestino == -1){
                    System.out.println(pNombreDestino+", no encontrado");
            }
        }
        
    }
    public void buscarDestino(String pDestino){
        String resultado = "";
        for(int index=0;index< lugares.length;index++){
            resultado += buscarDetinoEnPosicionEspecifico(lugares[index],pDestino,index);
        }
        System.out.println(resultado);
        
        
    }
    
    public String buscarDetinoEnPosicionEspecifico(NodoListaLugar nodo, String pDestino,int index){
        if(nodo.sig==null){
            return ";";
        }else if(nodo.nombreLugar.equals(pDestino)){
            return index+";";
        }else{
            return buscarDetinoEnPosicionEspecifico(nodo.sig, pDestino, index);
        }
    }
    public int encontrarCiudad(String pNombre){
        int posicionEncontrada = -1;
        for (int index=0;index<lugares.length;index++){
            if(lugares[index].nombreLugar.equals(pNombre)){
                posicionEncontrada = index;
                return posicionEncontrada;
            }
        }
        return posicionEncontrada;
    }
    
    public void validarCaminos(int indexOrigen,String pDestino){
        if(lugares[indexOrigen].sig != null){
            
        }
    }
    public String buscarCiudadEnUnLugar(String pDestino,NodoListaLugar lugar){
        return "";
    }
    
    public String[][] muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        
        BufferedReader b = new BufferedReader(f);
        int line=0;
        int largoMatriz=0;
        String[] data = null;
        while((cadena = b.readLine())!=null) {
            
            if(cadena.isEmpty()){
                //System.out.println("Vacio");
            }else{
                //System.out.println(cadena);
                if(line==0){
                   data = cadena.split(";");
                    
                }
                line++;
            }
        }
        b.close();
        
        FileReader file = new FileReader(archivo);
        BufferedReader c = new BufferedReader(file);
        String [][] datos = new String [line][data.length];
        //System.out.println(line); y 
        //System.out.println(data.length); x 
        int y=0;
        while((cadena = c.readLine())!=null) {
            
            if(cadena.isEmpty()){
               
            }else{
                //System.out.println(cadena);
                   data = cadena.split(";");
                   for(int x=0;x<data.length;x++){
                       datos[y][x]=data[x];
                   }
                   y++;
            }
        }
        c.close();
        
       return datos;
    }
    public void arrancadorProfundidad(){
        mostrarProfundidad(lugares[0]);
    }
    public String mostrarProfundidad(NodoListaLugar lugar){
        List<String> mostrado = new ArrayList<String>();
        List<String> cola = new ArrayList<String>();
        String output = recursividadProfundidad(lugar,mostrado,cola,true);
        System.out.println(output);
        
        return "";
    }
    public String recursividadProfundidad(NodoListaLugar lugar,List<String> mostrado,List<String> cola,boolean primeraVez){
        int posicionEnCola=0;
        if(primeraVez){
            mostrado.add(lugar.nombreLugar);
            cola = retornarlugaresAsociadosAunLugar(lugar.nombreLugar);
            return recursividadProfundidad(lugar,mostrado,cola,false);
        }else{
            if(cola.size()>0 && lugarMostrado(cola.get(cola.size()-1),mostrado)){
                posicionEnCola = cola.size()-1;
                //Agregar lugares asociados a este destino
                List<String> lugaresAsociados = retornarlugaresAsociadosAunLugar(cola.get(cola.size()-1));
                for(int index=0;index<lugaresAsociados.size();index++){
                    if(!lugarMostrado(lugaresAsociados.get(index),mostrado)){
                        cola.add(lugaresAsociados.get(index));
                    }
                }
                //Lugar ya se ha mostrado
                cola.remove(posicionEnCola);
                return recursividadProfundidad(lugar,mostrado,cola,false);
            }else if(cola.size()>0){
                mostrado.add(cola.get(cola.size()-1));
                posicionEnCola = cola.size()-1;
                //Agregar lugares asociados a este destino
                List<String> lugaresAsociados = retornarlugaresAsociadosAunLugar(cola.get(cola.size()-1));
                for(int index=0;index<lugaresAsociados.size();index++){
                    // Validar con la funcion lugarMostrado antes de agregarlo a la cola tambien agregar este cambio en la linea 216
                    if(!lugarMostrado(lugaresAsociados.get(index),mostrado)){
                        cola.add(lugaresAsociados.get(index));
                    }
                    
                }
                //Lugar ya se ha mostrado
                cola.remove(posicionEnCola);
                return recursividadProfundidad(lugar,mostrado,cola,false);
            }else{
                return mostrado.toString();
            }
        }
        
        
    }
    
    public void arrancadorAnchura(){
        mostrarAnchura(lugares[0]);
    }
    public String mostrarAnchura(NodoListaLugar lugar){
        List<String> mostrado = new ArrayList<String>();
        List<String> cola = new ArrayList<String>();
        String output = recursividadAnchura(lugar,mostrado,cola,true);
        System.out.println(output);
        
        return "";
    }
    
    public String recursividadAnchura(NodoListaLugar lugar,List<String> mostrado,List<String> cola,boolean primeraVez){
        if(primeraVez){
            mostrado.add(lugar.nombreLugar);
            cola = retornarlugaresAsociadosAunLugar(lugar.nombreLugar);
            return recursividadAnchura(lugar,mostrado,cola,false);
        }else{
            if(cola.size()>0 && lugarMostrado(cola.get(0),mostrado)){
                
                //Agregar lugares asociados a este destino
                List<String> lugaresAsociados = retornarlugaresAsociadosAunLugar(cola.get(0));
                for(int index=0;index<lugaresAsociados.size();index++){
                    if(!lugarMostrado(lugaresAsociados.get(index),mostrado)){
                        cola.add(lugaresAsociados.get(index));
                    }
                }
                //Lugar ya se ha mostrado
                cola.remove(0);
                return recursividadAnchura(lugar,mostrado,cola,false);
            }else if(cola.size()>0){
                mostrado.add(cola.get(0));
                 //Agregar lugares asociados a este destino
                List<String> lugaresAsociados = retornarlugaresAsociadosAunLugar(cola.get(0));
                for(int index=0;index<lugaresAsociados.size();index++){
                    // Validar con la funcion lugarMostrado antes de agregarlo a la cola tambien agregar este cambio en la linea 216
                    if(!lugarMostrado(lugaresAsociados.get(index),mostrado)){
                        cola.add(lugaresAsociados.get(index));
                    }
                    
                }
                //Lugar ya se ha mostrado
                cola.remove(0);
                return recursividadAnchura(lugar,mostrado,cola,false);
            }else{
                return mostrado.toString();
            }
        }
        
        
    }
    public boolean lugarMostrado(String pNombreLugar,List<String> mostrado){
        boolean encontrado= false;
        for(int index=0; index<mostrado.size();index++){
            if(mostrado.get(index).equals(pNombreLugar)){
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    public List<String> retornarlugaresAsociadosAunLugar(String nombreLugarOrigen){
        List<String> Nombrelugares = new ArrayList<>();
        int posicion = encontrarCiudad(nombreLugarOrigen);
        String [] data;
        if(posicion>=0){
            String lugaresAsociados = recursividadLugaresAsociadosAunLugar(lugares[posicion].sig);
            data = lugaresAsociados.split(";");
            for(int x=0;x<data.length;x++){
                Nombrelugares.add(data[x]);
            }
        }
        
        
        
        return Nombrelugares;
    }
    public String recursividadLugaresAsociadosAunLugar(NodoListaLugar lugar){
        
        while(lugar !=null){
            return lugar.nombreLugar+";"+recursividadLugaresAsociadosAunLugar(lugar.sig);
        }return "";
    }
    public boolean validarArrayMostrado(String pPalabra,List<String> mostrado){
       boolean encontrado = false;
        for(int index=0;index<mostrado.size();index++){
           if(pPalabra.equals(mostrado.get(index))){
               encontrado=true;
               break;
           }
       }return encontrado;
    }
            
}

