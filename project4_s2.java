/*

	Solution 2 : Add time sleep 100  before Philosopher pick left chopstick

 */



package project4;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class project4_s2
{
  public static final int PHILOSOPHERS_NO = 5;    // number of philosophers

  public static void main(String[] args)
  {
	// each chopstick with a Semaphore
    Semaphore[] chopSticks = new Semaphore[PHILOSOPHERS_NO];
    
    // Create the philosophers in 5 threads
    Thread[] philosophers = new Thread[PHILOSOPHERS_NO];  
    
    // Create object p in class Philosopher
    Philo2[] p = new Philo2[PHILOSOPHERS_NO];

    for (int i=0; i<PHILOSOPHERS_NO; i++)
    {
      chopSticks[i] = new Semaphore(1);
      p[i] = new Philo2(i, chopSticks, p);
    }
    
    // start 5 threads
    for (int i=0; i<PHILOSOPHERS_NO; i++)
	    {
	      philosophers[i] = new Thread(p[i]);
	      philosophers[i].start();
	    }
    
  }
}

class Philo2 implements Runnable
{
  private Random rand = new Random();
  int id; // The philosopher's unique id
  Semaphore[] chopSticks; // The chopsticks this philosopher may use
  Philo2[] philosophers;
  int n;

  public Philo2(int id, Semaphore[] chopSticks, Philo2[] p)
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
    	
    	
    	try {
    		Thread.sleep (rand.nextInt(100));
          } catch (InterruptedException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
          }

    	
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