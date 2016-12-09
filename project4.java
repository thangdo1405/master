/*
 	Solution 1:  Only allow 4 philosophers get chopsticks simultaneously.

 */



package project4;

import java.time.Duration;
import java.time.LocalDateTime;
//import java.util.Random;

public class project4
{
  public static final int PHILOSOPHERS_NO = 5;    // number of philosophers

  public static void main(String[] args)
  {
	// each chopstick with a Semaphore
    Semaphore[] chopSticks = new Semaphore[PHILOSOPHERS_NO];
    
    // Create the philosophers in 5 threads
    Thread[] philosophers = new Thread[PHILOSOPHERS_NO];  
    
    // Create object p in class Philosopher
    Philosopher[] p = new Philosopher[PHILOSOPHERS_NO];

    for (int i=0; i<PHILOSOPHERS_NO; i++)
    {
      chopSticks[i] = new Semaphore(1);
      p[i] = new Philosopher(i, chopSticks, p);
    }
    
    // start 4 threads
    for (int i=0; i<PHILOSOPHERS_NO-1; i++)
	    {
	      philosophers[i] = new Thread(p[i]);
	      philosophers[i].start();
	    }
    
  }
}

class Philosopher implements Runnable
{
 // private Random rand = new Random();
  int id; // The philosopher's unique id
  Semaphore[] chopSticks; // The chopsticks this philosopher may use
  Philosopher[] philosophers;
  int n;

  public Philosopher(int id, Semaphore[] chopSticks, Philosopher[] p)
  {
    this.id = id;
    this.chopSticks = chopSticks;
    this.philosophers = p;
    n = chopSticks.length;

  }

  public void run()
  {
	
    while(true)
    {
    	LocalDateTime time = LocalDateTime.now();
    
    	
      chopSticks[id].acquire();      // pick left chopstick
      
      System.out.println("Philosopher " + id + " took left chopstick ");
      
      chopSticks[(id+1) % n].acquire();    // pick right chopstick
      
      System.out.println("Philosopher " + id + " took right chopstick and eating ");
      
      
      chopSticks[id].release();     // return left chopstick

      System.out.println("Philosopher " + id + " put left chopstick ");

      chopSticks[(id+1) % n].release(); // return right chopstick  
   
      System.out.println("Philosopher " + id + " put right chopstick and thinking... ");

      LocalDateTime time2 = LocalDateTime.now();
  	  
  	  Duration timeElapsed = Duration.between(time, time2);
      System.out.println( timeElapsed);
      
      
    }
    
    
    
  }


}