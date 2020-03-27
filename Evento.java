/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.util.ArrayList;

/**
 *
 * @author chiericozzi.gabriele
 */
class Evento {
    private String messaggio;
    
    private ArrayList<SocketWorker> workers = new ArrayList<>();
    
    
       void addSubscriber(SocketWorker w) {
        this.workers.add(w);
    }
       
       synchronized void sendNewMessaggio(String m) {
        this.messaggio = m;
        for (SocketWorker worker: this.workers) {
            worker.sendMessaggio(this.messaggio);
        }
    
}
}

interface EventoSubscriber {
    public void sendMessage(String m);
        
        }

abstract class EventoPublisher { 
    Evento newMessaggio;

    //
    public void registraPublisher(Evento newMessaggio) {
        this.newMessaggio = newMessaggio;
    }

    public void messaggioReceived(String m) {
        this.newMessaggio.sendNewMessaggio(m);
    }
}

