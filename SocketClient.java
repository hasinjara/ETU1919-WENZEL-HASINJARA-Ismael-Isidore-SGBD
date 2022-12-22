package socket.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import algo.filtre.*;
import algo.fonction.*;
import algo.langage.*;
import algo.tableRelationnelle.*;
import algo.main.*;
import algo.erreur.*;

/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class SocketClient {
    Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public Socket connect(String host, int port) throws Exception {
        try {
            socket = new Socket(host, port);
            return socket;
        } catch (Exception e) {
            throw new Exception(e.getMessage());        
        }
    }

    public Socket connectLocal(int port) throws Exception {
        try {
            InetAddress host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), port);
            return socket;
        } catch (Exception e) {
            throw new Exception(e.getMessage());        
        }
    }

    public void sendRequestToServer(String request, ObjectOutputStream oos) throws Exception {
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Object getResponseByServer(ObjectInputStream ois) throws Exception{
        try {
            Object response = ois.readObject();
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        try {
            SocketClient defineClass = new SocketClient();
            InetAddress host = InetAddress.getLocalHost();
            int port = 1900;
            System.out.println("Port default: "+ port );
            System.out.print("Port: " );
            Socket socket = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            int done = 0;
            Fonction affichage = new Fonction();
            String request = "";
            BufferedReader buffered_reader = null;
            buffered_reader = new BufferedReader(new InputStreamReader(System.in));
            String new_port = buffered_reader.readLine();
            if(new_port.compareToIgnoreCase("") != 0) {
                port = Integer.valueOf(new_port);
            }
            System.out.println( "port : "+port);
            while(done != 1) {
                socket = defineClass.connect(host.getHostName(), port);
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println(" ");
                System.out.print("SQL>");
                buffered_reader = new BufferedReader(new InputStreamReader(System.in));
                request = buffered_reader.readLine();
                defineClass.sendRequestToServer(request, oos);
                ois = new ObjectInputStream(socket.getInputStream());
                Object response = defineClass.getResponseByServer(ois);
                if(response instanceof TableRelationnelle) {
                    affichage.affTable( (TableRelationnelle)response);
                }
                if(response instanceof Erreur) {
                    //System.out.println(response);
                    System.out.println( ((Erreur)response).getMessage_erreur());
                }
                else if(response instanceof String){
                    System.out.println(response);
                }
                ois.close();
                oos.close();
            }
            // while(done != 1) {
            //     socket = defineClass.connect(host.getHostName(), port);
            //     oos = new ObjectOutputStream(socket.getOutputStream());
            //     System.out.println("Sending request to Socket Server");
            //     defineClass.sendRequestToServer("salut", oos);
            //     ois = new ObjectInputStream(socket.getInputStream());
            //     String response = defineClass.getResponseByServer(ois);
            //     System.out.println(response);
            //     ois.close();
            //     oos.close();
            //     Thread.sleep(10000);
            // }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
}