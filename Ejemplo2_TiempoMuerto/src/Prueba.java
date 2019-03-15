
class Util{ 
    // Util class to sleep a thread 
	// Util class para dormir un hilo
    static void sleep(long millis) { 
        try{ 
            Thread.sleep(millis); 
        } 
        catch (InterruptedException e) { 
            e.printStackTrace(); 
        } 
    } 
} 
//This class is shared by both threads 
//Esta class es compartido por ambos hilos
class Shared { 
 // first synchronized method 
 // primer metodo sincronizado
 synchronized void test1(Shared s2) { 
     System.out.println("test1-comenzado"); 
     Util.sleep(1000); 

     // taking object lock of s2 enters 
     //tomando objeto bloqueado de las entradas de s2
     // into test2 method 
     //en el método test2
     s2.test2(this); 
     System.out.println("test1-termino"); 
 } 

 // second synchronized method 
//segundo metodo sincronizado
 synchronized void test2(Shared s1) { 
     System.out.println("test2-comenzo"); 
     Util.sleep(1000); 

     // taking object lock of s1 enters 
   //tomando objeto bloqueado de las entradas de s1
     // into test1 method 
   //en el método test1
     s1.test1(this); 
     System.out.println("test2-finalizo"); 
 } 
} 


class Thread1 extends Thread { 
 private Shared s1; 
 private Shared s2; 

 // constructor to initialize fields 
 //constructor para inicializar campos
 public Thread1(Shared s1, Shared s2) { 
     this.s1 = s1; 
     this.s2 = s2; 
 } 

 // run method to start a thread 
 //Ejecutar método para iniciar un hilo
 @Override
 public void run() 
 { 
     // taking object lock of s1 enters 
	//tomando objeto bloqueado de las entradas de s1
     // into test1 method 
	 //en el metodo test1
     s1.test1(s2); 
 } 
} 


class Thread2 extends Thread 
{ 
 private Shared s1; 
 private Shared s2; 

 // constructor to initialize fields 
 /// constructor para inicializar campos
 public Thread2(Shared s1, Shared s2) 
 { 
     this.s1 = s1; 
     this.s2 = s2; 
 } 

 // run method to start a thread 
 // Ejecutar método para iniciar un hilo
 @Override
 public void run() 
 { 
     // taking object lock of s2 
	 // teniendo objeto de bloqueo de s2
     // enters into test2 method 
	 //entrando en el metodo test2
     s2.test2(s1); 
 } 
} 

public class Prueba {
	public static void main(String[] args) {
		// creating one object
		//creando un objeto
        Shared s1 = new Shared(); 
  
        // creating second object 
        //creando un segundo objeto
        Shared s2 = new Shared(); 
  
        // creating first thread and starting it 
        //creando un primer hilo y comenzandolo
        Thread1 t1 = new Thread1(s1, s2); 
        t1.start(); 
  
        // creating second thread and starting it 
        //creando un segundo hilo y comenzandolo
        Thread2 t2 = new Thread2(s1, s2); 
        t2.start(); 
  
        // sleeping main thread 
        //dormir el hilo main
        Util.sleep(2000); 
	}

}
