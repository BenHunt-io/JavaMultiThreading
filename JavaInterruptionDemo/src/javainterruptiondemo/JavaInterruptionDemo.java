/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javainterruptiondemo;

/**
 *
 * @author Ben
 */
public class JavaInterruptionDemo {

  private static Thread backgroundThread;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
       BackgroundRunnable backgroundRunnable = new BackgroundRunnable();
        
       backgroundThread = new Thread(backgroundRunnable);
       backgroundThread.start(); 
       
       try{
       Thread.sleep(1000);
       }catch(InterruptedException e){}
       
       backgroundThread.interrupt();
       
       // Can no longer access old thread. It's still running.
       // It's essentially the same as doing new Thread(new Runnable()); anonymous thread      
       backgroundThread = new Thread(backgroundRunnable); 
       backgroundThread.start();
       
       
    }
    
    
    public static class BackgroundRunnable implements Runnable{
        
       
        // If the same BackgroundRunnable instance is used, only 1 thread can 
        // access the run() method at once bc of synchronized. Once the first 
        // thread is done accessing it, it gives the lock to the queued thread
        @Override
        public synchronized void run() {

            
           while(true){
                for(int i=0; i<5; i++){
                    System.out.println(i + " " +  Thread.currentThread().toString());
                }
                try{
                Thread.sleep(2500);
                }catch(InterruptedException e){
                    System.out.println("Interrupted Thread");
                    return;
                }

            
            }
            
        
        }
    
    }
    
}
    
    