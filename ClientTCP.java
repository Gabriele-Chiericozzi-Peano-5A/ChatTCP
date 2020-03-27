/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 *
 * @author chiericozzi.gabriele
 */
public class ClientTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String indirizzoIP = "127.0.0.1";
        int numPort = 1234;

        try {
            InetAddress address = InetAddress.getByName(indirizzoIP);
            
            Socket clientSocket = new Socket(address, numPort);
            
            System.out.println("Client: usa Ctrl-C per terminare e ENTER per spedire il messaggio.\n");
            
            Listener l;
            
            try {
                l = new Listener(clientSocket);
                Thread t = new Thread((Runnable) l);
                t.start();

            } catch (Exception e) {
                System.out.println("Connessione non riuscita con il server");
            } 
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            
            System.out.println("Inserisci il tuo messaggio: ");
            System.out.println(">");
            while((userInput = buffer.readLine()) != null){
                out.println(userInput);
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.println(">");
            }    
            
            clientSocket.close();
            System.out.println("Connessione Terminata");
        } catch (IOException e) {
                System.out.println("Connessione terminata dal server: ");
                e.printStackTrace();
            }

    }

}

    

