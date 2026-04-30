class myThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
    }
}

public class ThreadPriority {
    public static void main(String[] args) {
        myThread t1 = new myThread();

        myThread t2 = new myThread();
        t1.setName("Thead 1");
        t2.setName("Thread 2");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);

        t1.start();
        t2.start();
    }
}
