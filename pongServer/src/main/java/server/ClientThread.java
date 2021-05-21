package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientThread extends Thread{


    private final Socket socket;
    private final ServerSocket serverSocket;
    public static int turn;

    public ClientThread (Socket socket, ServerSocket serverSocket) {
        this.socket = socket ;
        this.serverSocket = serverSocket;
    }

    private void printAndFlush(PrintWriter printWriter, String response){
        printWriter.println(response);
        printWriter.flush();
    }

    public void run () {
        try {
            boolean isLogged = false;
            String username = null;
            boolean ok = true;
            socket.setSoTimeout(180000);
            while(ok){
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                String request = in.readLine();
                // Send the response to the output stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                //analyzing the request
                if (request.startsWith("play") ) {
                    printAndFlush(out, "Server received the request...Let's play");
                }
                else if(request.startsWith("exit") && (request.split("\\s+").length == 1)){
                    printAndFlush(out, "Server received the request ...exiting..." );
                    socket.close();
                    ok=false;
                } else if(request.startsWith("stop") && (request.split("\\s+").length == 1)){
                    printAndFlush(out, "Server stopped.");
                    socket.close();
                    Server.isRunning = false;
                    serverSocket.close();
                    ok=false;
                } else{
                    printAndFlush(out, "Non-existing command! Check the syntax!");
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
