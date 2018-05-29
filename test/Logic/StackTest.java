package Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest {
    static Stack testSubject;
    static int size = 10;

    @BeforeEach
    public void setUp() {
        testSubject = new Stack(size);
    }

    @Test
    void newIsEmpty() {
        assertEquals(true, testSubject.isEmpty());
    }

    @Test
    void newGetSize() {
        assertEquals(size, testSubject.getSize());
    }

    @Test
    void pop() {
        testSubject.push("5");
        testSubject.push("-");
        testSubject.push("10");
        testSubject.push("+");
        testSubject.push("12");
        testSubject.push("=");
        assertEquals("=", testSubject.pop());
        assertEquals("12", testSubject.pop());
        assertEquals("+", testSubject.pop());
        assertEquals("10", testSubject.pop());
        assertEquals("-", testSubject.pop());
        assertEquals("5", testSubject.pop());
        assertEquals(true, testSubject.isEmpty());
    }

    @Test
    void topEquals() {
        testSubject.push("^");
        assertEquals(true, testSubject.topEquals("^"));
    }

    @Test
    void topEquals2() {
        testSubject.push("3");
        assertEquals(false, testSubject.topEquals("+"));
    }

    @Test
    void topEquals3() {
        testSubject.push("+");
        assertEquals(true, testSubject.topEquals("+"));
    }

    @Test
    void topEquals4() {
        testSubject.push("2.5");
        assertEquals(true, testSubject.topEquals("2.5"));
    }

    @Test
    void topEquals5() {
        testSubject.push("12");
        assertEquals(false, testSubject.topEquals("11"));
    }

    @Test
    void topEquals6() {
        testSubject.push("*");
        assertEquals(true, testSubject.topEquals("*"));
    }

    @Test
    void top() {
    }

    @Test
    void push() {
    }

    @Test
    void topEquals1() {
    }

    @Test
    void push1() {
    }

    @Test
    void testToString1() {
        testSubject.push("5");
        testSubject.push("-");
        testSubject.push("10");
        testSubject.push("+");
        testSubject.push("12");
        testSubject.push("^");
        testSubject.push("1.5");
        testSubject.push("*");
        testSubject.push("0.5");
        testSubject.push("=");
        assertEquals("= 0.5 * 1.5 ^ 12 + 10 - 5 ", testSubject.toString());
    }

    @Test
    void testToString2() {
        testSubject.push("2");
        testSubject.push("+");
        testSubject.push("2");
        testSubject.push("=");
        testSubject.toString();
        assertEquals("= 2 + 2 ", testSubject.toString());
    }

}