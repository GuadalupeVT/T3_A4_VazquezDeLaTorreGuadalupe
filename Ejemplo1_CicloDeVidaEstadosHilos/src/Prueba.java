
class thread implements Runnable { 
    public void run() { 
    	// moving thread2 to timed waiting state 
    	//moviendo el hilo2 al estado de tiempo de espera
    	try{ 
            Thread.sleep(1500); 
        }  
        catch (InterruptedException e)  { 
            e.printStackTrace(); 
        } 
          
    	System.out.println("State of thread1 while it called join() method on thread2 -"+ Prueba.thread1.getState()); 
        try{ 
            Thread.sleep(200); 
        }  
        catch (InterruptedException e)  { 
            e.printStackTrace(); 
        }      
    }
}


public class Prueba implements Runnable{
	 public static Thread thread1; 
	 public static Prueba obj; 

	public static void main(String[] args) {
		obj = new Prueba(); 
        thread1 = new Thread(obj); 
          
        // thread1 created and is currently in the NEW state. 
       // thread1 creado y se encuentra actualmente en el estado NUEVO.
        System.out.println("State of thread1 after creating it - " + thread1.getState()); 
        thread1.start(); 
          
        // thread1 moved to Runnable state 
     // thread1 movido al estado ejecutable
        System.out.println("State of thread1 after calling .start() method on it - " +  
            thread1.getState()); 
	}//main
	
	public void run() { 
        thread myThread = new thread(); 
        Thread thread2 = new Thread(myThread); 
          
        // thread1 created and is currently in the NEW state. 
        // thread1 creado y se encuentra actualmente en el estado NUEVO.
        System.out.println("State of thread2 after creating it - "+ thread2.getState()); 
        thread2.start(); 
          
        // thread2 moved to Runnable state 
        // thread2 movido al estado Ejecutable
        System.out.println("State of thread2 after calling .start() method on it - " +  thread2.getState()); 
          
        // moving thread1 to timed waiting state 
        // moviendo thread1 al estado de espera cronometrado
        try{ 
            //moving thread1 to timed waiting state 
        	// moviendo thread1 al estado de espera cronometrado
            Thread.sleep(200); 
        }  
        catch (InterruptedException e)  { 
            e.printStackTrace(); 
        } 
        System.out.println("State of thread2 after calling .sleep() method on it - "+  
            thread2.getState() ); 
          
          
        try { 
            // waiting for thread2 to die 
        	// esperando que el thread2 muera
            thread2.join(); 
        }  
        catch (InterruptedException e)  { 
            e.printStackTrace(); 
        } 
        System.out.println("State of thread2 when it has finished it's execution - " +  
            thread2.getState()); 
    } 

}
