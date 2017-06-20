/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamultithreading_workingtogether;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */

// Have to extend the Thread class. No import needed.
public class JavaMultiThreading_WorkingTogether {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Make an instance of the class we just made. Since it extends Thread,
        // it is of type thread.
        CountDownClock clock = new CountDownClock(10);
        
       
        
        // Because halfWay and oneSecondLeft after being initialized will implement
        // Runnable, they can be said to be of type Runnable.
        // make instances of the RunnableClock class we defined
        ArrayList<Runnable> events = new ArrayList<Runnable>();
        events.add(new RunnableClock(5,"Half way done baby", clock));
        events.add(new RunnableClock(1,"One second left holmes", clock));
        events.add(new RunnableClock(6,"Evil Number 6", clock));
        events.add(new RunnableClock(9,"Barely Started Counting", clock));
        // RunnableClock halfWay, oneSecondLeft; also works...
        
        clock.start(); // Starts the thread we created.

        // Made a new thread by passing in a new instance of our class that extends Runnable
        // pass them as parameters to Thread Consturctor to create new Threads
        for(Runnable e : events){
            new Thread(e).start(); // no variable for this thread is created
        }
    
        
        
        
        
        
    }
    
    // Problem with extending Thread: A class can only have one superclass
    public static class CountDownClock extends Thread implements TimeMonitor{
        // start time
        private int t;
        
        public CountDownClock(int start){
            this.t = start;
        }
        
        // run() gets called when Thread starts.
        public void run(){
            // Countdown 
            for(; t>=0; t--){
                System.out.println("MY COUNTDOWN THREAD: " + t);
                // sleep method throws an interrupted exception. Have to surround
                // with try/catch block to catch it. (We in this case do nothing
                // with the caught exception.
                try{
                Thread.sleep(1000);
                }catch(InterruptedException e){
                }
            }
        }
        
        public int getTime(){
            return t;
        } 
        
    }
    
    // Thread class implements runnable, that's why it has a run method and so
    // does a class that implements runnable.
    //The Runnable interface marks an object that can be run as a thread
    public static class RunnableClock implements Runnable{
        
        public String message;
        public int start;
        TimeMonitor timeMonitor;
        // passing in a reference to TimeMonitor as monitor in CountDownClock 
        public RunnableClock(int start, String message, TimeMonitor monitor){
            this.message = message;
            this.start = start;
            this.timeMonitor = monitor;
        }
        public void run(){
            Boolean eventDone = false;
            while (!eventDone){
                try{
                    // Keep checking every 10ms to see if the time from the 
                    // TimeMonitor is less than the specified time to start displaying
                    // the message.
                    Thread.sleep(10);
                    if(timeMonitor.getTime() <= start){
                        System.out.println(message);
                        eventDone = true;
                    }
                }catch(InterruptedException e){}
            }
         }
    }
 }
    
    
    
    
    

// Used to communicate between the classes.
interface TimeMonitor{
    public int getTime();
}
