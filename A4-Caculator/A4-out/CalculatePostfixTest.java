import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests functionality of CalculatePostfix class.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class CalculatePostfixTest {
    
    /**
     * Tests addition.
     */
    @Test
    public void testAdd() {
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("1.5 2 +")) , (Double) 3.5);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("1 0 +")) , (Double) 1.0);
    }

    /**
     * Tests subtraction.
     */
    @Test
    public void testSubtract() {
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("5 3 -")) , (Double) 2.0);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("4 0 -")) , (Double) 4.0);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("4 5 -")) , (Double) (-1.0));
    }

    /**
     * Tests multiplication.
     */
    @Test
    public void testMultiply() {
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("2 3 *")) , (Double) 6.0);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("5 0 *")) , (Double) 0.0);
    }

    /**
     * Tests division.
     */
    @Test
    public void testDivide() {
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("8 4 /")) , (Double) 2.0);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("5 2 /")) , (Double) 2.5);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("0 1 /")) , (Double) 0.0);
    }

    /**
     * Tests that dividing by 0 results in an exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDivideBy0() {
        CalculatePostfix.postfixToResult(Tokenizer.readTokens("1 0 /"));
    }

    /**
     * Tests that unrecognizable operators result in an exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBadOperator() {
        CalculatePostfix.postfixToResult(Tokenizer.readTokens("1 0 ="));
    }

    /**
     * Tests evaluating expressions with multiple operations.
     */
    @Test 
    public void testMulti() {
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("3 4 + 4 * 2 / 1 -")) , (Double) 13.0);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("3 0 * 9 + 3 /")) , (Double) 3.0);
        assertEquals(CalculatePostfix.postfixToResult(Tokenizer.readTokens("2 1 5 + *")) , (Double) 12.0);
    }

    /**
     * Tests that attempting operations with insufficient numbers results in an exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInsufficientNumbers() {
        CalculatePostfix.postfixToResult(Tokenizer.readTokens("1 1 - +"));
    }

    /**
     * Tests that extra numbers on the end results in an exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testExcessiveNumbers() {
        CalculatePostfix.postfixToResult(Tokenizer.readTokens("1 1 + 1"));
    }
}
