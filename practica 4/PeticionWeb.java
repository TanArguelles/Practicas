/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverproxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author Eduardo
 */
public class PeticionWeb extends Thread {
    private Socket scliente 	= null;		// representa la petición de nuestro cliente
    private PrintWriter out 	= null;		// representa el buffer donde escribimos la respuesta
    
    PeticionWeb(Socket ps)
    {
        scliente = ps;
    }
    
    void depura(String mensaje)
    {
        System.out.println("Mensaje: " + mensaje);
    }
    
    public void run() {
        depura("Procesamos conexion");
        String get ="";
        String host ="";
        int puerto =80;

        try {
            BufferedReader in = new BufferedReader (new InputStreamReader(scliente.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(scliente.getOutputStream(),"8859_1"),true) ;
            
            String cadena = "";		// cadena donde almacenamos las lineas que leemos
            int i=0;				// lo usaremos para que cierto codigo solo se ejecute una vez

            do {
                cadena = in.readLine();
                
                if (cadena != null ) {
                    // sleep(500);
                    depura("--" + cadena + "-");
                }

                if(i == 0) { // la primera linea nos dice que fichero hay que descargar
                    i++;
                    /*StringTokenizer st = new StringTokenizer(cadena);
                    if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                        retornaFichero(st.nextToken()) ;
                    }
                    else {
                        out.println("400 Petición Incorrecta") ;
                    }
                    */

                    if(cadena.contains("GET")){
                        get = cadena;
                    }
                    else if(cadena.contains("Host")){
                        cadena=cadena.replace(" ", "");
                        String[] array=cadena.split(":");
                        if(array.length==3){
                            host=array[1];
                            puerto=Integer.parseInt(array[2]);
                        }
                        else{
                            host=array[1];
                        }
                    }
                    
                   
                    String respuesta = Datos( get, host,  puerto);
                    out.println(respuesta);
                }
            } while (cadena != null && cadena.length() != 0);

        } catch(Exception e) {
            depura("Error en servidor\n" + e.toString());
        }

        depura("Hemos terminado");
    }
    
    String Datos(String get,String hostname, int port) throws IOException{
        int puerto=8081;
        String respuesta="";
        ServerSocket serCliente = null;
        Socket sokCliente  = null;
        try
        {
            serCliente = new ServerSocket(puerto);
            System.out.println("Esperando conexion cliente...");
            sokCliente = serCliente.accept();

            DataOutputStream envioCliente= new DataOutputStream(sokCliente.getOutputStream());

            envioCliente.writeUTF(get+","+hostname+","+port+" \n\r");

            BufferedReader entradaCliente = new BufferedReader (new InputStreamReader(sokCliente.getInputStream()));

            for (String line; (line = entradaCliente.readLine()) != null;) {
                  respuesta+=line+"\n";
            }
            envioCliente.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
}
