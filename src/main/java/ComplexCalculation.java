import java.math.BigInteger;

public class ComplexCalculation {

    public static void main(String[] args) throws InterruptedException {

        BigInteger result = new ComplexCalculation()
                .calculateResult(new BigInteger("2"), new BigInteger("2"),
                        new BigInteger("2"), new BigInteger("2"));

        Thread.sleep(2000);

        System.out.println(result);

    }
    public BigInteger calculateResult(BigInteger base1, BigInteger power1,
                                      BigInteger base2, BigInteger power2) throws InterruptedException {

        BigInteger result;

        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        // start each thread
        thread1.start();
        thread2.start();

        // Main thread need to wait both threads to complete before proceed.
        thread1.join();
        thread2.join();

        result = thread1.getResult().add(thread2.getResult());

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
           for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
               result = result.multiply(base);
           }
        }

        public BigInteger getResult() { return result; }
    }
}