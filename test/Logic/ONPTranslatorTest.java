package Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ONPTranslatorTest {
    static ONPTranslator testSubject;

    @BeforeEach
    public void setUp() {
        testSubject = new ONPTranslator();
    }

    @Test
    public void ToPrefix1() {
        assertEquals("2 2 + = ", testSubject.toPrefix("2+2=").toString());
    }

    @Test
    public void ToPrefix2() {
        assertEquals("15 3 - 3 2 + ^ 6 * 3 / = ", testSubject.toPrefix("( 15 - 3 ) ^ ( 3 + 2 ) * 6 /  3 =").toString());
    }

    @Test
    public void toPrefix3() {
        assertEquals("4 3 1 - 2 3 * ^ / = ", testSubject.toPrefix("4 / (3 - 1) ^ (2 * 3) =").toString());
    }

    @Test
    public void toPrefix4() {
        assertEquals("2 1 + 3 * 4 7 4 + * - = ", testSubject.toPrefix("(2+1)*3-4*(7+4)").toString());
    }

    @Test
    public void toPrefix5() {
        assertEquals("10 3 5 * 16 4 - / + = ", testSubject.toPrefix("10 + 3 * 5 / (16 - 4)").toString());
    }

    @Test
    public void toPrefix6() {
        assertEquals("2.6 5.4 + 4 / 2 2 ^ 0.5 * + = ", testSubject.toPrefix("(2.6 + 5.4)/4 + 2 ^ 2 * 0.5=").toString());
    }

    @Test
    public void calculatePrefix1() {
        assertEquals("4.0", testSubject.calculatePrefix(testSubject.toPrefix("2+2=")));
    }

    @Test
    public void calculatePrefix2() {
        assertEquals("1.220703125E8", testSubject.calculatePrefix(testSubject.toPrefix("( 15 - 3 ) ^ ( 3 + 2 ) * 6 /  3 =")));
    }

    @Test
    public void calculatePrefix3() {
        assertEquals("0.006944444444444444", testSubject.calculatePrefix(testSubject.toPrefix("4 / (3 - 1) ^ (2 * 3) =")));
    }

    @Test
    public void calculatePrefix4() {
        assertEquals("35.0", testSubject.calculatePrefix(testSubject.toPrefix("(2+1)*3-4*(7+4)")));
    }

    @Test
    public void calculatePrefix5() {
        assertEquals("9.2", testSubject.calculatePrefix(testSubject.toPrefix("10 + 3 * 5 / (16 - 4)")));
    }

    @Test
    public void calculatePrefix6() {
        assertEquals("2.5", testSubject.calculatePrefix(testSubject.toPrefix("(2.6 + 5.4)/4 + 2 ^ 2 * 0.5=")));
    }

}