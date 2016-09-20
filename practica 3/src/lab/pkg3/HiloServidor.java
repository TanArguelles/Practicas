/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Tania
 */
public class HiloServidor  extends Thread{
private static int i = 0; 
private int nS; 
Socket client; 
 private static Bodega bodega;
    private static Thread productor;
    private static Thread [] consumidores;
    private static final int CANTIDADCONSUMIDORES = 5;
     private final int idconsumidor=0;
     Consumidor consumidor = new Consumidor(bodega,idconsumidor);

/** Creates a new instance of AcceptedSocket */ 
public HiloServidor(Socket client) { 
nS = i++; 
this.client = client; 

} 
/* */ 
public void run() { 
String ret; 
try { 
BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream())); 
OutputStreamWriter os = new OutputStreamWriter(client.getOutputStream()); 
while((ret = is.readLine()) != null) {
        while(Boolean.TRUE)
        {
            System.out.println("El consumidor " + idconsumidor + " consume: " + bodega.get());
            
        }
    }
client.close(); 
} catch (Exception ex) { 
ex.printStackTrace(); 
} 
} 
    
}
