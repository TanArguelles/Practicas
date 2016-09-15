/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Tania
 */
public class Practica2 {

    /**
     * @param args the command line arguments
     */
  
      
        
        public static void main(String[] args) throws Exception {
         
           for (int port = 1; port <= 65535; port++) {
         try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", port), 2000);
            //socket.connect(new InetSocketAddress("localhost",port),1000);
            socket.close();
            System.out.println("Puerto abierto " + port );
        } catch (Exception ex) {
        }
      }
   }
    }
    

