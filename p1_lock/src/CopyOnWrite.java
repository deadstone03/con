import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by xingdai on 5/7/17.
 */
public class CopyOnWrite {
  public static void main(String[] args) {

    final CopyOnWriteArrayList<Integer> ints = new CopyOnWriteArrayList<>();

    Thread thread1 = new Thread() {
      @Override
      public void run() {
        for (int j = 0; j < 10; j ++) {
          System.out.println("Iterator " + j);
          for(Integer i: ints) {
            System.out.println(i);
            try {
              Thread.sleep(4);
            } catch (InterruptedException e) {
            }
          }
        }
      }
    };

    Thread thread2 = new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; ++i) {
          ints.add(i);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
          }
        }
      }
    };

    thread1.start(); thread2.start();
    thread1.yield(); thread2.yield();
  }
}
