import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

class Chopstick {

}

public class Philosopher extends Thread {
  private static int c = 0;
  private static Random random = new Random();
  final private Chopstick left, right;
  private int id;

  public Philosopher(Chopstick left, Chopstick right) {
    this.left = left;
    this.right = right;
    id = c++;
  }
  @Override
  public void run() {
    while(true) {
      try {
        Thread.sleep(random.nextInt(10));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      synchronized (left) {
        synchronized (right) {
          System.out.printf("%d is eating\n", id);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }
      System.out.printf("%d finished eating\n", id);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ArrayList<Philosopher> ps = new ArrayList<>();
    ArrayList<Chopstick> cs = new ArrayList<>();
    int number = 5;
    for (int i = 0; i < number; ++i) {
      cs.add(new Chopstick());
    }
    for (int i = 0; i < number; ++i) {
      ps.add(new Philosopher(cs.get(i), cs.get((i + 1) % number)));
    }
    for (int i = 0; i < number; ++i) {
      ps.get(i).start();
    }
    for (int i = 0; i < number; ++i) {
      ps.get(i).join();
    }
  }
}
