import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FooBarTest {

    @Test
    public void resolve() {
        String resolve = new FooBar().resolve(5);
        assertEquals("1 2 Foo 4 Bar", resolve);

        resolve = new FooBar().resolve(10);
        assertEquals("1 2 Foo 4 Bar Foo 7 8 Foo Bar", resolve);

        resolve = new FooBar().resolve(50);
        assertEquals("1 2 Foo 4 Bar Foo 7 8 Foo Bar " +
                "11 Foo 13 14 FooBar 16 17 Foo 19 Bar " +
                "Foo 22 23 Foo Bar 26 Foo 28 29 FooBar " +
                "31 32 Foo 34 Bar Foo 37 38 Foo Bar " +
                "41 Foo 43 44 FooBar 46 47 Foo 49 Bar", resolve);
    }
}