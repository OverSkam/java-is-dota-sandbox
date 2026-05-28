package sandbox.proxy.bean;

import java.time.Duration;

public class Waiter implements IWaiter {
    private final String name;

    public Waiter(String name) {
        this.name = name;
    }

    @Override
    public void serve(String customerName) {
        System.out.println(name + " is serving: " + customerName);

        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(name + " is free");
    }
}
