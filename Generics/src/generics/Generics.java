/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sten
 */
public class Generics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        TimeoutQueue<String> object = new TimeoutQueue<String>();
        object.offer("1111", 10000);
         object.offer("2222", 10000);
          object.offer("3333", 2000);
          System.out.println(object.poll());
          System.out.println(object.poll());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Generics.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println(object.poll());
          System.out.println(object.size());
              
    }
    
}
