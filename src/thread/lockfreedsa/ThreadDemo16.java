package thread.lockfreedsa;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo16 {

    public static class IncrementingThread extends Thread {

        public static void main(String[] args) throws InterruptedException {

            InventoryCounter inventoryCounter = new InventoryCounter();
            IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
            DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

            incrementingThread.start();
            decrementingThread.start();

            incrementingThread.join();
            decrementingThread.join();

            System.out.println("No. of items we currently we have : " + inventoryCounter.getItems() + " items");
        }

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.incrementItems();
            }
        }
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrementItems();
            }
        }
    }

    private static class InventoryCounter {
        private AtomicInteger items = new AtomicInteger(0);

        public  void incrementItems() {
            items.incrementAndGet();
        }

        public  void decrementItems() {
            items.decrementAndGet();
        }

        public  int getItems() {
            return items.get();
        }

    }
}
