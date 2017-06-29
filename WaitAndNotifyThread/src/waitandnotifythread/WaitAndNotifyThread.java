/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waitandnotifythread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben
 */
public class WaitAndNotifyThread {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        function3();
        Thread backgroundThread = new Thread(new BackgroundRunnable());
        backgroundThread.start();
        synchronized(WaitAndNotifyThread.class){
            function1();
            try {
                WaitAndNotifyThread.class.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitAndNotifyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            function2();
        }
        
        System.out.println("After synchronized block");
    }
    
    
    
    
    
    public static void function3(){
        System.out.println("function3");
    }
    
    public static void function1(){
        System.out.println("function1");
    }
    
    public static void function2(){
        System.out.println("function2");
        
    }
    
    static class BackgroundRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("In Background Thread");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitAndNotifyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized(WaitAndNotifyThread.class){
                System.out.println("In background thread's synchronzied block");
                WaitAndNotifyThread.class.notify(); // Wake up the thread
            }
        }
        
    }
    
}
