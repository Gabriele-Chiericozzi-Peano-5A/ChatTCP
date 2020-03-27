/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cuomo.alessandro
 */
class SocketWorker extends EventoPublisher implements Runnable, EventoSubscriber {
    private Socket client;
    private PrintWriter out = null;

    SocketWorker(Socket client) {
        this.client = client;
        System.out.println("Connesso il client: " + client);
    }

    public void registraPublisher(Evento newMessaggio) {
    }

    void sendMessaggio(String messaggio) {
        out.println("Server--> " + messaggio);
    } 

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in = BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            
        }catch(IOException e){
            System.out.println("ERRORE: in or out");
            System.out.println(-1);
        }
        String line = "";
        int clientPorta = client.getPort();
        while(line != null){
            try{
                line = in.readLine();
                messaggioReceived(line);
                System.out.println(clientPorta + ">>" + line);
            }catch(IOException e){
                System.out.println("Errore di lettura da parte del socket");
                System.out.println(-1);
            }
        }
        try{
            client.close();
            System.out.println("Connessione con client: " + client + "Terminata");
            
        }catch(IOException e){
        System.out.println("Errore di connessione con client: " + client);

        }
    } 

    private BufferedReader BufferedReader(InputStreamReader inputStreamReader) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMessage(String m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
