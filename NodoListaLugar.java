
package proyectofinal;


public class NodoListaLugar {
    public String nombreLugar;
    public NodoListaLugar sig;
    public String distancia;
    
    public void NodoListaLugar(String pNombreLugar){
        nombreLugar = pNombreLugar;
        sig = null;
    }
    public void NodoListaLugar(String pNombreLugar, NodoListaLugar siguiente,String pdistancia){
        nombreLugar = pNombreLugar;
        siguiente = sig;
        distancia = pdistancia;
    }
}
