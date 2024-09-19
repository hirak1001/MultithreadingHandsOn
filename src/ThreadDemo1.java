public class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {


        // Thread creation using runnable interface
        Thread thread = new Thread(() -> {

            System.out.println("We are now in thread: " + Thread.currentThread().getName());
            System.out.println("Thread Priority : " + Thread.currentThread().getPriority());
            throw new RuntimeException("Internal Exception");

        });

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before staring a new Thread");
        thread.setName("New Worker Thread");
        thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println("a critical error happened at : " + t.getName() + " & error is " + e.getMessage());
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after staring a new Thread");

        Thread.sleep(1000);


    }
}