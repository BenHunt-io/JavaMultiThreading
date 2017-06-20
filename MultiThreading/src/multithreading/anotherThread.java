/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

/**
 *
 * @author Ben
 */
public class anotherThread implements Runnable {

  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println("Hello");
//        Thread thread2 = new Thread(new MultiThreading());
        // the new thread starts.. run is called and immedieatly goes back
        // to the main thread.
        anotherThread runnable1 = new anotherThread();
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        
        
        anotherThread runnable2 = new anotherThread();
        Thread thread2 = new Thread(runnable2);
        thread2.start();
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            
        }
        
        
        anotherThread runnable3 = new anotherThread();
        Thread thread3 = new Thread(runnable3);
        thread3.start();
        
        anotherThread runnable4 = new anotherThread();
        Thread thread4 = new Thread(runnable4);
        thread4.start();
        
    }
    
    @Override
    public void run() {
        
        for(int i = 0; i< 10; i++){
            System.out.println("main thread: " + i);
        }
    }
    
   
    
}
