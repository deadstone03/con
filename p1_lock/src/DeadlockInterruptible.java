import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xingdai on 5/7/17.
 */
public class DeadlockInterruptible {
  public static void main(String[] args) throws InterruptedException {
    final ReentrantLock lock1 = new ReentrantLock();
    final ReentrantLock lock2 = new ReentrantLock();

    Thread thread1 = new Thread() {
      @Override
      public void run() {
        try {
          lock1.lockInterruptibly();
          Thread.sleep(100);
          lock2.lockInterruptibly();
        } catch (InterruptedException e) {
        }
      }
    };
    Thread thread2 = new Thread() {
      @Override
      public void run() {
        try {
          lock2.lockInterruptibly();
          Thread.sleep(100);
          lock1.lockInterruptibly();
        } catch (InterruptedException e) {
        }
      }
    };

    thread1.start(); thread2.start();
    thread1.interrupt(); thread2.interrupt();
    thread1.join(); thread2.join();
  }
}
