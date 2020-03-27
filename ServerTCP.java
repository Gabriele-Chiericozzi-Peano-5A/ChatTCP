/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author chiericozzi.gabriele
 */
public class ServerTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int portNumber = 1234; //porta a cui il server si mettera' in ascolto
        Evento newMessaggio = new Evento();


        try{
            
            ServerSocket server = new ServerSocket(portNumber);
            System.out.println("Server chatTCP in esecuzione...");

            while(true){
                
                SocketWorker w;
                try {
                   
                    Socket newSocket = server.accept();
                    
                    w = new SocketWorker(newSocket);

                   
                    w.registraPublisher(newMessaggio);

                  
                    newMessaggio.addSubscriber(w);

                    //genero il Thread per l'esecuzione del nuovo Worker
                    Thread t = new Thread((Runnable) w);
                    //Avvio l'esecuzione del nuovo worker nel Thread
                    t.start();
                } catch (IOException e) {
                    System.out.println("Connessione NON riuscita con client: ");
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error! Porta: " + portNumber + " non disponibile");
            System.exit(-1);
        }

        
    }
}
