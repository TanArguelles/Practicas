/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverproxy;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Eduardo
 */
public class ServerProxy {

    /**
     * @param args the command line arguments
     */
    int PUERTO = 9999;
    
    void depura(String mensaje)
    {
        System.out.println("Mensaje: " + mensaje);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ServerProxy instancia = new ServerProxy();	
	instancia.arranca();
    }
    
    public void arranca() {
        depura("Arrancamos nuestro servidor");

        try
        {	
            ServerSocket s = new ServerSocket(PUERTO);
            depura("Quedamos a la espera de conexion");

            while(true)  // bucle infinito .... ya veremos como hacerlo de otro modo
            {
                Socket entrante = s.accept();
                PeticionWeb pCliente = new PeticionWeb(entrante);
                pCliente.start();
            }	
        }
        catch(Exception e)
        {
            depura("Error en servidor\n" + e.toString());
        }
    }
    
}
