import java.util.Random;


/**
 * Primer entregable d'eda
 * 
 * @author Alejandro Iznardo & Víctor García 
 * @version 
 */
public class CreadorArray{
    
    public int[] Array;
    int res = 0;
    int max = 0;
    public CreadorArray(){
        
        Random r = new Random();
        int numero = r.nextInt(99999) + 1;
        Array = new int[numero];
        for (int i = 0; i < Array.length; i++){
             r = new Random();
             Array[i] = r.nextInt(99) + 1;
        }
    }
    
    /**
     * Donat un nombre n calcula en l'array
     * la posició de la seqüència amb major 
     * suma de llargaria n
     * 
     * 
     */
    public int sumaReq(int n) {
        long tiempo;
        tiempo = System.nanoTime();
        int res = sumaReq(n, Array, 0, Array.length);
        tiempo = System.nanoTime() - tiempo;
        System.out.println("RECURSIU.seqüència " + max + " fins a " + (max+n));
        System.out.println(tiempo);
        return res; 
    }
    
    private int sumaReq(int n, int[] a, int i, int f) {
        int m =(i+f)/2;
        if(f - i > n + m){
            sumaReq(n,a,i,m);
            sumaReq(n,a,m+1,f);
        } else {
            sumacio(n,a,i,m);
            sumacio(n,a,m+1,f);
        }
        return max;
    } 
    
    
      private void sumacio(int n, int[] a, int i, int f) {

        while(i < f + n && i < a.length - n){
            int aux = 0;
            for(int j = i; j < i + n; j++){
                aux += a[j];
            }
            if(aux > res) { max = i; }
            i++;
        }
    }
    
    /**
     * Versió del codi sols iterativa 
 
     */
    
    public int SumaIter(int n){
        int res = 0;
        int max = 0;
        long tiempo = 0;
        
        System.out.println();
        
        tiempo = System.nanoTime();
        for(int i = 0; i < Array.length - n; i++){
            int aux= 0;
            for(int j = i; j < i + n; j++){ 
                aux += Array[j];
            }
            if(aux > res) { res = aux; max = i; }
        }
        tiempo = System.nanoTime() - tiempo;
        System.out.println("ITERATIU. La suma és:" + res + " en la seqüència " + max + " fins a " + (max+n));
        System.out.println(tiempo);
        return max;
    }
    
    
    
}