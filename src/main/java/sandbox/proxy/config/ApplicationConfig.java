package sandbox.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import sandbox.proxy.bean.Customer;
import sandbox.proxy.bean.IWaiter;
import sandbox.proxy.bean.Waiter;

@Configuration
@ComponentScan("sandbox.proxy")
public class ApplicationConfig {
    @Bean
    public IWaiter john() {
        return new Waiter("John");
    }

    @Bean
    public Customer andrew() {
        return new Customer("Andrew");
    }

    @Bean
    public Customer julia() {
        return new Customer("Julia");
    }

    @Bean
    public Customer nina() {
        return new Customer("Nina");
    }
}
