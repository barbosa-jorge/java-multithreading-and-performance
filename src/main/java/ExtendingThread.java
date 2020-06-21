public class ExtendingThread {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.setName("New Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private static class NewThread extends Thread {
        public void run() {
            System.out.println("Hello from "+ this.getName());
            System.out.println("Priority "+ this.getPriority());
        }
    }
}
