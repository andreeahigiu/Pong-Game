package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    public static final int PORT = 8100;
    public static boolean isRunning = true;

    public Server() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT))  {
            while (isRunning) {
                System.out.println("Waiting for a client ...");
                if(!isRunning){
                    serverSocket.setSoTimeout(1000);
                }
                Socket socket = serverSocket.accept();

                // Execute the client's request in a new thread
                if(isRunning){
                    new ClientThread(socket, serverSocket).start();
                }

            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        }
    }
    public static void main ( String [] args ) throws IOException {
        Server server = new Server ();
    }

}
