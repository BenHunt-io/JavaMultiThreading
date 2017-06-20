/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamultithreading;

/**
 *
 * @author Ben
 */

// Have to extend the Thread class. No import needed.
public class JavaMultiThreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Make an instance of the class we just made. Since it extends Thread,
        // it is of type thread.
        Thread clock = new CountDownClock();
        clock.start(); // Starts the thread we created.
        
        // Made a new thread by passing in a new instance of our class that extends Runnable
        Thread runnableClock = new Thread(new RunnableClock(2, "Barely Started Counting"));
        runnableClock.start(); // Starts the thread we created
        // More concise version (if you don't need to access the Thread later)
        // new Thread(new RunnableClass()).start();
        
        // Because halfWay and oneSecondLeft after being initialized will implement
        // Runnable, they can be said to be of type Runnable.
        Runnable halfWay, oneSecondLeft;
        // RunnableClock halfWay, oneSecondLeft; also works...
        
        // make two instances of the RunnableClock class we defined.
        halfWay = new RunnableClock(5, "Half way done Baby!");
        oneSecondLeft = new RunnableClock(9, "One second left holmes");
        
        // pass them as parameters to Thread Consturctor to create new Threads
        new Thread(halfWay).start();  // no variable for this thread is created
        new Thread(oneSecondLeft).start(); // no variable for this thread is created
         
        
        
        
        
        
    }
    
    // Problem with extending Thread: A class can only have one superclass
    public static class CountDownClock extends Thread{
        // run() gets called when Thread starts.
        public void run(){
            // Countdown 
            for(int i = 10; i>=0; i--){
                System.out.println("MY COUNTDOWN THREAD: " + i);
                // sleep method throws an interrupted exception. Have to surround
                // with try/catch block to catch it. (We in this case do nothing
                // with the caught exception.
                try{
                Thread.sleep(1000);
                }catch(InterruptedException e){
                }
            }
        }
        
    }
    
    // Thread class implements runnable, that's why it has a run method and so
    // does a class that implements runnable.
    //The Runnable interface marks an object that can be run as a thread
    public static class RunnableClock implements Runnable{
        
        public String message;
        public int wait;
        
        public RunnableClock(int wait, String message){
            this.message = message;
            this.wait = wait;
        }
        public void run(){
            
            try{
            Thread.sleep(wait * 1000);
            System.out.println(message);
            }catch(InterruptedException e){}
        }
    }
    
    
    
    
    
}
