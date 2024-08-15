
package com.umg.productorconsumidor;

import java.util.LinkedList;
import java.util.Queue;


public class barberia {
    
    private final Queue<Integer> fila = new LinkedList<>();
    private static final int capacidad = 3;
    
    public synchronized  void producir() throws InterruptedException{
        int cliente = 0;
        
        while (true){
            while (fila.size() == capacidad){
                wait();
                cliente = 0;
            }
            fila.add(cliente);
            cliente++;
            System.out.println("Tienes "+cliente+" clientes en espera "  );
            notify();
            Thread.sleep(2000);
        }
        
    }
    
     public synchronized void consumidor() throws InterruptedException{
            
         while (true){
             while(fila.isEmpty()){
                 wait();
                 int atendido = 1;
             }
             int atendido = fila.poll();
              int t = 3-atendido;
             System.out.println("cliente atendido tienes "+t+" clientes en espera "  );
             notify();
             Thread.sleep(2000);        
         }
        }
    
}

