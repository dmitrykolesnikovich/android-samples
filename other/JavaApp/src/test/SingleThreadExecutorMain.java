package test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleThreadExecutorMain {

  public static void main(String[] args){
    Executor executor = Executors.newSingleThreadExecutor(); // new ThreadPoolExecutor(1, 1, ...)
    executor.execute(new Task1(1));
    executor.execute(new Task1(2));
    executor.execute(new Task1(3));
    executor.execute(new Task1(4));
    executor.execute(new Task1(5));
    executor.execute(new Task1(6));
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