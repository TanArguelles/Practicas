/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author Tania
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class server {

    public static void main(String[] args) {

        ServerSocket s; //Socket servidor
        Socket sc; //Socket cliente

        PrintStream p; //Canal de escritura
        BufferedReader b; //Canal de Lectura

        String mensaje;

        InetAddress host;// obtener nombre del equipo
        try {
// socket server
            s = new ServerSocket(9999);
            System.out.println("Hola!! ...Espera");
            System.out.println("HOLA!!");
//Invoco el metodo accept del socket servidor, me devuelve una referencia al socket cliente
            sc = s.accept();
            System.out.println("Se conecto.." + sc);
            System.out.println("Ayuda: despliega las palaras \n Nombre: nombre del equipo \n SO: Sistema Operativo \n MAC: se obtiene direccion MAC\n Unidades Disco: despliega unidades de disco\n Fecha: despliega fecha y hora \n Usuario: usuario actual\n Procesos: procesos en ejecución\n adios: sale de sistema ");

//Obtengo una referencia a los canales de escritura y lectura del socket cliente
            b = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            p = new PrintStream(sc.getOutputStream());// salida del texto en el cliente

            while (true) {
//Leo lo que escribio el socket cliente en el canal de lectura
                mensaje = b.readLine();
                System.out.println(mensaje);

//p.println(mensaje);
                if (mensaje.equals("Ayuda")) {

                    p.println("Nombre: nombre del equipo \n SO: Sistema Operativo \n MAC: se obtiene direccion MAC\n Unidades Disco: despliega unidades de disco\n Fecha: despliega fecha y hora \n Usuario: usuario actual\n Procesos: procesos en ejecución\n adios: sale de sistema ");

                } else if (mensaje.equals("adios")) {
                    break;
                } else if (mensaje.equals("Nombre")) {
                    {
                        //obtengo el nombre del equipo, se crea variable host, InetAddress nos da esa información con el metodo get
                        host = InetAddress.getLocalHost();
                        p.println("Nombre :" + host.getHostName());
                    }
                } else if (mensaje.equals("Fecha")) {
                    // obtenemos fecha y hora con Date
                    p.println("fecha y hora:" + new Date());
                } else if (mensaje.equals("MAC")) {
                    // para obtener la direccion MAC
                    InetAddress ip;

                    ip = InetAddress.getLocalHost();
                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                    byte[] mac = network.getHardwareAddress();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    p.println("La direccion MAC:" + sb.toString());
                    System.out.println("La direccion MAC:" + sb.toString());
                } else if (mensaje.equals("SO")) {
                    //os.name nos da el nombre del sistema operativo dentro del sig metodo
                    String so = System.getProperty("os.name");
                    p.println("Tu Sistema Operativo es:" + so);
                    System.out.println(so);
                } else if (mensaje.equals("Unidades Disco")) {
                    //File nos enlista las unidades de disco
                    File[] roots = File.listRoots();
                    for (int i = 0; i < roots.length; i++) {
                        System.out.println(roots[i]);
                        p.println(roots[i]);
                    }
                } else if (mensaje.equals("Usuario")) {
                    String usuario = System.getProperty("user.name");
                    p.println("El usuario es" + usuario);
                } else if (mensaje.equals("Procesos")) {
                    p.println("Procesos ejecutados: "
                            + Runtime.getRuntime().availableProcessors());
                }
            }

            p.close();
            b.close();

            sc.close();
            s.close();
        } catch (IOException e) {
            System.out.println("No se puede establecer conexión!!!");
        }
    }

}
