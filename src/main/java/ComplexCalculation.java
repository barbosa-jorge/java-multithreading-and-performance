import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ComplexCalculation {

    public static void main(String[] args) throws InterruptedException {
        new ComplexCalculation()
                .calculateResult(new BigInteger("2"), new BigInteger("2"),
                                 new BigInteger("2"), new BigInteger("2"));
        Thread.sleep(10000);
    }
    public BigInteger calculateResult(BigInteger base1, BigInteger power1,
                                      BigInteger base2, BigInteger power2) throws InterruptedException {

        BigInteger result = BigInteger.ZERO;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        List<PowerCalculatingThread> threads = new ArrayList<>();
        threads.add(new PowerCalculatingThread(base1, power1));
        threads.add(new PowerCalculatingThread(base2, power2));

        for (PowerCalculatingThread powerCalculatingThread: threads) {
            powerCalculatingThread.start();
        }

        for (PowerCalculatingThread powerCalculatingThread: threads) {
            powerCalculatingThread.join();
        }

        for (PowerCalculatingThread powerCalculatingThread: threads) {
            result = result.add(powerCalculatingThread.getResult());
        }
        System.out.println(result);
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
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

        public BigInteger getResult() { return result; }
    }
}