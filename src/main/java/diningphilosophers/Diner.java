package diningphilosophers;

public class Diner implements Runnable {

    private final Object leftFork;
    private final Object rightFork;

    Diner(Object left, Object right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (leftFork) {
                    doAction(" picks up left fork");
                    synchronized (rightFork) {
                        doAction(" picks up right fork");
                        long startTime = System.currentTimeMillis();
                        doAction(" is eating"); // eating
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        doAction(" ate for " + elapsedTime + "ms. Pausing to digest...");
                        doAction(" puts down right fork");
                    }

                    doAction(" puts down left fork");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}