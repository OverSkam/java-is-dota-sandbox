package sandbox.proxy.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import sandbox.proxy.bean.Customer;

import java.lang.reflect.Method;

@Component
public class PlaceOrderLoggingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return createProxyBean(bean);
    }

    private Object createProxyBean(Object bean) {
        Class<?> beanClass = bean.getClass();

        if (bean instanceof Customer customer) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    System.out.println(customer.getName() + " place order!");
                    return method.invoke(customer, args);
                }
            });
            return enhancer.create();
        }

        return bean;
    }
}
