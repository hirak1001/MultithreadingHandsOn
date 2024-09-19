public class ThreadDemo2 {
    public static void main(String[] args) {

        Thread thread = new NewThread();
        thread.start();

    }

    // thread creation by extending thread class

    public static class NewThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
            System.out.println("Thread id : " + this.threadId());
        }
    }
}
