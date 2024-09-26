package thread.coordination;

import java.math.BigInteger;

public class ThreadDemo6 {
    public static void main(String[] args) throws InterruptedException {

        PowerCalculatingThread thread1 =new PowerCalculatingThread(BigInteger.valueOf(55L),BigInteger.valueOf(55L));
        PowerCalculatingThread thread2 =new PowerCalculatingThread(BigInteger.valueOf(32L),BigInteger.valueOf(55L));
        thread1.start();
        thread2.start();
        thread1.join(2000);
        thread2.join(2000);
        BigInteger result = thread1.getResult().add(thread2.getResult());
        System.out.println("BigInteger : "+result);
    }


    public static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}