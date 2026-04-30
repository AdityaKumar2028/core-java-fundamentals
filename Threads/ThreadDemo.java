
class myThread extends Thread {
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("Thread running: " + i);
        }
    }
}

class runnableThread implements Runnable {
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("Runnable: " + i);
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        myThread t1 = new myThread();
        t1.start();

        runnableThread obj = new runnableThread();

        Thread t2 = new Thread(obj);

        t2.start();
    }
}
