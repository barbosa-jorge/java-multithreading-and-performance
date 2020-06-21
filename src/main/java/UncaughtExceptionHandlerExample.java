
/*
* Usually not handled unchecked exceptions inside the main thread brings down the entire application.
* We can capture the exception using the setUncaughtExceptionHandler and perform some operations.
* */


public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Intentional Exception");
        });
        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("A critical error happened in thread "+ t.getName() +
                    "the error is: "+ e.getMessage());
        });

        thread.start();

    }
}
