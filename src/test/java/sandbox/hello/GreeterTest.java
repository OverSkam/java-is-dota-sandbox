package sandbox.hello;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterTest {

    @Test
    void greetsByName() {
        Greeter greeter = new Greeter();
        assertEquals("Hello, World!", greeter.greet("World"));
    }
}