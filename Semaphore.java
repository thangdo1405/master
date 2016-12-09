package project4;

public class Semaphore      
{
  private int value;

  public Semaphore(int value) 
  {
    this.value = value;
  }

  public synchronized void acquire() 
  {
    try
    {
      while (value <= 0) 
        wait(); 
    }
    catch (InterruptedException e) {}

    --value;
  }

  public synchronized void release() 
  {
    ++value;
    notifyAll();
  }
}
 
