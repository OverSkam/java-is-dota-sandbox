package sandbox.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sandbox.proxy.bean.Customer;
import sandbox.proxy.bean.IWaiter;
import sandbox.proxy.config.ApplicationConfig;

public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("Secrete file");
        image.display();
        image.display();

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IWaiter john = context.getBean("john", IWaiter.class);
        Customer andrew = context.getBean("andrew", Customer.class);
        Customer julia = context.getBean("julia", Customer.class);
        Customer nina = context.getBean("nina", Customer.class);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> andrew.placeOrder(john)).start();
            new Thread(() -> julia.placeOrder(john)).start();
            new Thread(() -> nina.placeOrder(john)).start();
        }
    }

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String fileName;

        public RealImage(final String fileName) {
            this.fileName = fileName;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("Loading image from disk: " + fileName);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + fileName);
        }
    }

    static class ProxyImage implements Image {
        private RealImage realImage;

        private final String fileName;

        public ProxyImage(final String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }

            realImage.display();
        }
    }
}
