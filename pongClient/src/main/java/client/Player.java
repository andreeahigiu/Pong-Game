package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader ( new InputStreamReader(socket.getInputStream()))
        ) {

            String commandLine;
            BufferedReader console = new BufferedReader
                    (new InputStreamReader(System.in));

            while (true) {
                //read the command
                System.out.print("client>");
                commandLine = console.readLine();

                //if just a return, loop
                if (commandLine.equals(""))
                    continue;
                else
                {
                    // Send a request to the server
                    String request = commandLine;
                    out.println(request);

                    // Wait the response from the server
                    String response = in.readLine();
                    System.out.println(response);
                    //if exit, stop the client
                    if (commandLine.equals("exit") || commandLine.equals("stop")){
                        socket.close();
                        break;
                    }

                }


            }

        }
//        catch (UnknownHostException e) {
//            System.err.println("No server listening... " + e);
//        }
    }
}
