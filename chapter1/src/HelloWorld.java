/**
 * Created by xingdai on 5/6/17.
 */
public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread() {
            public void run() {
                System.out.println("This is in the sub thread");
            }
        };
        myThread.start();
//        Thread.yield();
        Thread.sleep(1000);
        System.out.println("This is in the parent thread");
        myThread.join();
    }
}
