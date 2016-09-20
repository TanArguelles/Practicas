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
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tania
 */
public class Connection extends Thread {
    
    DataInputStream entrada;
    DataOutputStream salida;
    Socket clienteSocket;
    
    /*public Connection(Socket aClienteSocket){
        
        clienteSocket = aClienteSocket;
        
        try {
            entrada = new DataInputStream(clienteSocket.getInputStream());
            salida = new DataOutputStream(clienteSocket.getOutputStream());
                this.start();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void run(){
        try {
            String data = entrada.readUTF();
            salida.writeUTF(data);
            clienteSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
     private static Bodega bodega;
    private static Thread productor;
    private static Thread [] consumidores;
    private static final int CANTIDADCONSUMIDORES = 5;
     
    
    public Connection(){
        
    }
    
   public static void main(String[] args)
   {
       String ret; 
System.out.println("Starting Client"); 
try { 
Socket cliente = new Socket(InetAddress.getByName(null), 8080); 
BufferedReader is = new BufferedReader(new InputStreamReader(cliente.getInputStream())); 
OutputStreamWriter os = new OutputStreamWriter(cliente.getOutputStream()); 
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
cliente.close(); 
} catch (Exception ex) { 
ex.printStackTrace(); 
} 
} 
   }    

