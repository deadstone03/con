/**
 * Created by xingdai on 5/6/17.
 */
public class Puzzle {
    static boolean isSolved = false;
    static int answer = 0;

    static Thread thread1 = new Thread() {
        public void run() {
            answer = 42;
            isSolved = true;
        }
    };

    static Thread thread2 = new Thread() {
        @Override
        public void run() {
            if (isSolved) {
                System.out.println("The answer is " + String.valueOf(answer));
            } else {
                System.out.println("I don't know the answer");
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        thread1.start(); thread2.start();
        thread1.join(); thread2.join();
    }
}
