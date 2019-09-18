package prueba1;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import aplicaciones.biblioteca.Indice;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Font;

/**
 * Version de la clase Indexador (Practica 3) para el Primer Examen de Lab. 
 * @author (EDA) 
 * @version (Curso 2017-2018)
 */

public class IndexadorEx {
    
    protected final static String SEPARADORES = "[[ ]*|[,]*|[\\.]*|[\t]*|[:]*|[;]*|[(]*|[)]*|[/]*|[!]*|[?]*|[¿]*|[“]*|[??]*|[+]*]+";
    protected Map<String, ListaConPI<Indice>> indices;
    
    /**
     * Devuelve aquella palabra (String) de la lista l que aparece  
     * con mayor frecuencia en una coleccion de libros. Si ninguna palabra de 
     * l esta en la coleccion, o l esta vacia, el metodo debe devolver un String
     * vacio (i.e. "").
     */ 
    public String palMasFrecuente(ListaConPI<String> l) {    
        String res = "";
        int freq = 0;
        for(l.inicio(); !l.esFin(); l.siguiente()){
            ListaConPI<Indice> par = indices.recuperar(l.recuperar());
            if (par != null) {
                int num = 0;                
                if (num > freq) {
                    freq = num;
                    res = l.recuperar();
                }
            }
        }
        return res;        
    }
    
    public IndexadorEx() { 
        String tituloLibro = "heroes";
        Scanner libro = new Scanner("I\nI will be king And you\nyou will be queen Though nothing\nwill drive them awayWe can be heroes\nWe can be heroes\nJust for one day\nWe can be heroes");
        
        indices = new TablaHash<String, ListaConPI<Indice>>(20);
        int numLin = 0;
        while (libro.hasNext()) {
            String linea = libro.nextLine().toLowerCase();
            String[] words = linea.split(SEPARADORES);
            numLin++;
            for (int i = 0; i < words.length; i++) {
                if (words[i].matches("[a-zA-Z]+")) {
                    ListaConPI<Indice> valor = indices.recuperar(words[i]);
                    Indice ind = new Indice(tituloLibro, numLin);
                    if (valor == null) {
                        valor = new LEGListaConPI<Indice>();
                        valor.insertar(ind);
                        indices.insertar(words[i], valor);
                    } else {
                        valor.insertar(ind);
                    }
                }
            }
        }
    }
    
    public String toString() {
        String res ="";
        ListaConPI<String> claves = indices.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) {
            String c = claves.recuperar();
            ListaConPI<Indice> lpi = indices.recuperar(c); 
            res += c + ":\n" + lpi.toString() + "\n";
        }
        return res;
    }
}
