package thread.highperformanceiomodels;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Thread per task or Thread per-request model
public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 1000;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Press enter to start");
        s.nextLine();
        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);

        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("Tasks took %dms to complete\n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {

        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                service.submit(IoBoundApplication::blockingIoOperations);
            }
        }
    }

    private static void blockingIoOperations() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
