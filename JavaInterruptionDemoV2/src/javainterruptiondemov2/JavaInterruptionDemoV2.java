/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javainterruptiondemov2;

/**
 *
 * @author Ben
 */
public class JavaInterruptionDemoV2 {

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
       
       
       // backgroundThread.interrupt() will be the broadcast reciever interrupting
       // current run Thread because a new Geofence transition occured. Need to end
       // old thread and begin new thread keeping track of the new data
       backgroundThread.interrupt();
       
       // Can no longer access old thread. It's still running.
       // It's essentially the same as doing new Thread(new Runnable()); anonymous thread      
       backgroundThread = new Thread(backgroundRunnable); 
       backgroundThread.start();
       
       
    }
    
    
    public static class BackgroundRunnable implements Runnable{
        
       private static boolean flag = true;
        // If the same BackgroundRunnable instance is used, only 1 thread can 
        // access the doSomething method at once bc of synchronized. Once the first 
        // thread is done accessing it, it gives the lock to the queued thread
        @Override
        public void run() {
            
            System.out.println("Read from database before entering");
            doSomething();
            

        
        }
        
       // Could just use join() to achieve the same thing, but since I lose the 
       // reference to the first thread, I can't say .join() on the first thread
       // from the second thread
        public synchronized void doSomething(){
            while(flag){
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
    