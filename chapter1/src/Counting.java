/**
 * Created by xingdai on 5/6/17.
 */

class Counter {
    private int count = 0;

    public void inc() {
        count ++;
    }

    public int getCount() {
        return count;
    }
}

public class Counting {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        class CountThread extends Thread {
            public void run() {
                for (int i = 0; i < 10000; ++i)
                counter.inc();
            }
        }

        Thread thread1 = new CountThread();
        Thread thread2 = new CountThread();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter.getCount());
    }
}
