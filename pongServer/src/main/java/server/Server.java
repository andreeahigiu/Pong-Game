package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    public static final int PORT = 8100;
    public static boolean isRunning = true;
    private int playersNr = 0;

    public Server() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT))  {
            serverSocket.setSoTimeout(1000000);
            while (isRunning && playersNr < 2) {
                System.out.println("Waiting for a client ...");

                Socket socket = serverSocket.accept();
                playersNr++;
                System.out.println("Player #" + playersNr +"has connected");

                // Execute the client's request in a new thread
                if(isRunning){
                    new ClientThread(socket, serverSocket).start();
                }

            }
            System.out.println("No longer accepting connections, 2 players already connected");
        } catch (IOException e) {
            System.err. println ("IOException from Server " + e);
        }
    }
    public static void main ( String [] args ) throws IOException {
        Server server = new Server ();
    }

}


//iau id-ul fiecarui jucator dat de la server
//si o sa am un if in care verific al catelea jucator este; in functie de asta' o sa ii blochez "padul"/butonul care prinde mingea
