class example {
    synchronized void waitThread() {
        System.out.println("Thread waiting");
        try {
            wait();
        } catch (Exception e) {
        }
    }

    synchronized void notifyThread() {
        System.out.println("Notify Thread");
        notify();
    }
}

public class waitDemo {
    public static void main(String[] args) {
        example e1 = new example();

        Thread t1 = new Thread(() -> e1.waitThread());
        Thread t2 = new Thread(() -> e1.notifyThread());

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        t2.start();
    }
}
