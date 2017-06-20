/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasynchronizeddemo;

/**
 *
 * @author Ben
 */
public class JavaSynchronizedDemo {

    private static Thread backgroundThread;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
       LoopClass loopClass = new LoopClass();
       backgroundThread = new Thread(new BackgroundRunnable(loopClass));
       backgroundThread.start(); 
       
       // Can no longer access old thread. It's still running.
       // It's essentially the same as doing new Thread(new Runnable()); anonymous thread
       backgroundThread = new Thread(new BackgroundRunnable(loopClass)); 
       backgroundThread.start();

     
    }
    
    
    public static class BackgroundRunnable implements Runnable{
        
        LoopClass loopClass;
        
        public BackgroundRunnable(LoopClass loopClass){
            this.loopClass = loopClass;
        }

        @Override
        public synchronized void run() {
        
                loopClass.loopStuff(); // Both threads are accessing the same instance variable
                try{
                Thread.sleep(1000);
                }catch(InterruptedException e){
                    System.out.println("Interrupted Thread");
                    return;
                }

            
        }
        
    }
    
    
    
    
    public static class LoopClass{
        // Gives the "Lock" to the method that first access it. 
        // If another thread is trying to execute, it has to wait to get the 
        // lock
        synchronized void loopStuff(){
            for(int i = 0; i<100; i++){
                System.out.println(i);
            }
        }
    }
    
    
    
    
}
    

