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
public class Consumidor implements Runnable{
    
     private final Bodega bodega;
    private final int idconsumidor;
 
    /**
     * Constructor de la clase
     * @param bodega Contenedor común a los consumidores y el productor
     * @param idconsumidor Identificador del consumidor
     */
    public Consumidor(Bodega bodega, int idconsumidor) 
    {
        this.bodega = bodega;
        this.idconsumidor = idconsumidor;
    }
 
    @Override
    /**
     * Implementación de la hebra
     */
    public void run() 
    {
        while(Boolean.TRUE)
        {
            System.out.println("El consumidor " + idconsumidor + " consume: " + bodega.get());
        }
    }
    
}
