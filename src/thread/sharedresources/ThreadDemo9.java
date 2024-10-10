package thread.sharedresources;

public class ThreadDemo9 {


    // RACE condition
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
        private int items = 0;

        /*used synchronized keyword to maintain atomicity with concurrent thread on shared resources to allow one thread
        at a time to access shared resources.*/

//        public synchronized void incrementItems() {
//            items++;
//        }
//
//        public synchronized void decrementItems() {
//            items--;
//        }
//
//        public synchronized int getItems() {
//            return items;
//        }


        //or

        final Object lock = new Object();

        public void incrementItems() {

            synchronized (this.lock) {
                items++;
            }
        }

        public void decrementItems() {
            synchronized (this.lock) {
                items--;
            }
        }

        public int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}
