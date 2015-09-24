package test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AndroidThreadPoolMain {

  private static final int CORE_POOL_SIZE = 5;
  private static final int MAXIMUM_POOL_SIZE = 128;
  private static final int KEEP_ALIVE = 1;
  private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(10);
  private static final ThreadFactory sThreadFactory = new ThreadFactory() {
    private final AtomicInteger mCount = new AtomicInteger(1);
    public Thread newThread(Runnable r) {
      return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
    }
  };

  public static void main(String[] args){
    Executor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
        TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
    executor.execute(new Task1(0));
    executor.execute(new Task1(1));
    executor.execute(new Task1(2));
    executor.execute(new Task1(3));
    executor.execute(new Task1(4));
    executor.execute(new Task1(5));
  }
  private static class Task1 implements Runnable{
    private int id;
    private Task1(int id) {
      this.id = id;
    }
    @Override
    public void run() {
      for(int i=0; i<10; i++){
        System.out.println(Thread.currentThread()+", id = "+id+": "+i);
      }
    }
  }

}
