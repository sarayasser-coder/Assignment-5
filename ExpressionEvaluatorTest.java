package eg.edu.alexu.csd.datastructure.stack.cs;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {
    ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
    @Test
    public void testInfixToPostfixPositiveOnlyNumbersOnly(){
        assertEquals(expressionEvaluator.infixToPostfix("2 + 3 * 4"),"2 3 4*+");
        assertEquals(expressionEvaluator.infixToPostfix("(1+2)*7"),"1 2+ 7*");
        assertEquals(expressionEvaluator.infixToPostfix("20+90*70"),"20 90 70*+");
        assertEquals(expressionEvaluator.infixToPostfix("200*500"),"200 500*");
        assertEquals(expressionEvaluator.infixToPostfix("500"),"500");
    }
    @Test
    public void testInfixToPostfixNumbersOnly(){
        assertEquals(expressionEvaluator.infixToPostfix("-7+-8*-5"),"0 7- 0 8- 0 5-*+");
        assertEquals(expressionEvaluator.infixToPostfix("-800*5"),"0 800- 5*");
        assertEquals(expressionEvaluator.infixToPostfix("(7+2)-50*8"),"7 2+ 50 8*-");
        assertEquals(expressionEvaluator.infixToPostfix("-500"),"0 500-");
    }
    @Test
    public void testInfixToPostfixAlphabetsOnly(){
        assertEquals(expressionEvaluator.infixToPostfix("a * b / c"),"a b* c/");
        assertEquals(expressionEvaluator.infixToPostfix("(a / (b - c + d)) * (e - a) * c"),"a b c- d+/ e a-* c*");
        assertEquals(expressionEvaluator.infixToPostfix("-b+a-c*f"),"0 b- a+ c f*-");
        assertEquals(expressionEvaluator.infixToPostfix("a"),"a");
    }
    @Test
    public void testInfixToPostfixNumbersAndAlphabets(){
        assertEquals(expressionEvaluator.infixToPostfix("5+b*c/4*a"),"5 b c* 4/ a*+");
        assertEquals(expressionEvaluator.infixToPostfix("(-9+a)/90*b"),"0 9- a+ 90/ b*");
    }
    @Test
    public void testInfixToPostfixExceptions(){
        assertThrows(RuntimeException.class,()->{expressionEvaluator.infixToPostfix("ab");});
        assertThrows(RuntimeException.class,()->{expressionEvaluator.infixToPostfix("5**8");});
        assertThrows(RuntimeException.class,()->{expressionEvaluator.infixToPostfix("48++8");});
        assertThrows(RuntimeException.class,()->{expressionEvaluator.infixToPostfix("c//ba");});
        assertThrows(RuntimeException.class,()->{expressionEvaluator.infixToPostfix("4=90");});
    }
    @Test
    public void testEvaluate(){
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("-7+-8*-5")),33);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("-800*5")),-4000);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("2 + 3 * 4")),14);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("(1+2)*7")),21);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("20+90*70")),6320);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("200*500")),100000);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("(7+2)-50*8")),-391);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("500")),500);
        assertEquals(expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("-500")),-500);
    }
    @Test
    public void testEvaluateExceptions(){
        assertThrows(RuntimeException.class,()->{expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("80/0"));});
        assertThrows(RuntimeException.class,()->{expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("a+b/c"));});
        assertThrows(RuntimeException.class,()->{expressionEvaluator.evaluate(expressionEvaluator.infixToPostfix("8//9"));});
    }
}