/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tania
 */
public class Servidor {
    
    
    public static void main(String[] args){
          try {
            ServerSocket socketServidor = null;
            socketServidor=new ServerSocket(8080);
            System.out.println("iniciando servidor");
            while(true){
            Socket cliente = socketServidor.accept();
            System.out.println("Aceptando:"+ cliente.getInetAddress());
            
            HiloServidor c = new HiloServidor(cliente);
            c.start();
        }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
    
    
    }
}