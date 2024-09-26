package thread.creation;

import java.math.BigInteger;

public class ThreadDemo4 {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger(String.valueOf(20000)), new BigInteger(String.valueOf(1000000))));
        thread.setDaemon(true);
        thread.start();
//        thread.interrupt();  commented code below for this method
    }

    public static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + "=" + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {

                //this block used with interrupt method
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Prematurely interrupted computation");
//                    return BigInteger.ZERO;
//                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
