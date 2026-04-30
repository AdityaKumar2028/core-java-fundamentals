class display {
    synchronized void print() {
        System.out.println("Thread :" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

    }
}

public class BlockedState {
    public static void main(String[] args) {
        display db = new display();
        Thread t1 = new Thread(() -> db.print());

        Thread t2 = new Thread(() -> db.print());

        t1.start();
        t2.start();
    }
}
