/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escaneolan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tania
 */
public class EscaneoLan {

    /**
     * @param args the command line arguments
     */
    
    static String host;
   
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
       
         int timeout=1000;
   for (int i=1;i<254;i++){
       host="192.168.0" + "." + i;
             try {
                 if (InetAddress.getByName(host).isReachable(timeout)){
                     System.out.println(host + " is reachable");
                     
                     
                     
                     
                     
                 }         } catch (UnknownHostException ex) {
                 Logger.getLogger(EscaneoLan.class.getName()).log(Level.SEVERE, null, ex);
             }
       
   
}
}
    
    public static void escanearPuertos(){
         for (int port = 1; port <= 65535; port++) {
         try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 2000);
            //socket.connect(new InetSocketAddress("localhost",port),1000);
            socket.close();
            System.out.println("Puerto abierto " + port+ host );
        } catch (Exception ex) {
        }
      }
   }
    }

   
    

