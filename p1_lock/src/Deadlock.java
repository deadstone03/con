/**
 * Created by xingdai on 5/7/17.
 */
public class Deadlock {
  public static void main(String[] args) throws InterruptedException {
    Object lock1 = new Object(), lock2 = new Object();

    Thread thread1 = new Thread() {
      @Override
      public void run() {
        synchronized (lock1) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
          synchronized (lock2) {
          }
        }
      }
    };
    Thread thread2 = new Thread() {
      @Override
      public void run() {
        synchronized (lock2) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
          synchronized (lock1) {
          }
        }
      }
    };

    thread1.start(); thread2.start();
    thread1.interrupt(); thread2.interrupt();
    thread1.join(); thread2.join();
  }
}
