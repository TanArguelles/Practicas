/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg3;

/**
 *
 * @author Tania
 */
public class Lab3 {

   private static Bodega bodega;
    private static Thread productor;
    private static Thread [] consumidores;
    private static final int CANTIDADCONSUMIDORES = 5;
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        bodega = new Bodega();
        productor = new Thread(new Productor(bodega, 1));
        consumidores = new Thread[CANTIDADCONSUMIDORES];
 
        for(int i = 0; i < CANTIDADCONSUMIDORES; i++)
        {
            consumidores[i] = new Thread(new Consumidor(bodega, i));
            consumidores[i].start();
        }
         
        productor.start();
    }    
    
}
