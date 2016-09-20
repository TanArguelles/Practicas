/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg3;

import java.util.Random;

/**
 *
 * @author Tania
 */
public class Productor implements Runnable{
     private final Random aleatorio;
    private final Bodega bodega;
    private final int idproductor;
    private final int TIEMPOESPERA = 1500;
 
    /**
     * Constructor de la clase
     * @param bodega Contenedor común a los consumidores y el productor
     * @param idproductor Identificador del productor
     */
    public Productor(Bodega bodega, int idproductor) 
    {
        this.bodega = bodega;
        this.idproductor = idproductor;
        aleatorio = new Random();
    }
 
    @Override
    /**
     * Implementación de la hebra
     */
    public void run() 
    {
        while(Boolean.TRUE)
        {
            int poner = aleatorio.nextInt(300);
            bodega.put(poner);
            System.out.println("El productor " + idproductor + " pone: " + poner);
            try
            {
                Thread.sleep(TIEMPOESPERA);
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Productor " + idproductor + ": Error en run -> " + e.getMessage());
            }
        }
    }
    
}
