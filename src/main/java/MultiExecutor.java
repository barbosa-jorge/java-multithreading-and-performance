import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
    private List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        for (Runnable r: tasks) {
            new Thread(r).start();
        }
    }

    public static void main(String[] args) {

        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> System.out.println("Task 1"));
        tasks.add(() -> System.out.println("Task 2"));
        tasks.add(() -> System.out.println("Task 3"));

        new MultiExecutor(tasks).executeAll();
    }
}
