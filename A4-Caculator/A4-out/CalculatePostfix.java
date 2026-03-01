/**
 * Class to evaluate postfix expressions.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class CalculatePostfix {
    
    /**
     * Takes in a postfix expression as a queue and returns the result.
     * 
     * @param tokens Queue of symbols in postfix expression
     * @return Resulting number
     * @throws IllegalArgumentException If the expression is malformed
     */
    public static Double postfixToResult(Queue<Object> tokens) {
        Stack<Double> nums = new Stack<Double>();
        while (!tokens.isEmpty()) {
            Object token = tokens.dequeue();
            if (token instanceof Double) { // if number, push to stack
                Double value = (Double) token;
                nums.push(value);
            } else if (token instanceof Character) {
                Double b = nums.pop(); // nums.pop() will throw exception if not enough numbers for operation
                Double a = nums.pop();
                nums.push(doArithmetic(a, b, (Character) token)); // do operation, push result
            } else {
                throw new IllegalArgumentException("Token not recognized");
            }
        }
        if (nums.size() != 1) {
            throw new IllegalArgumentException("Malformed expression");
        }
        return nums.pop();
    }

    /**
     * Evaluates the result of two decimal numbers with the specified operator (+,-,*,/).
     * 
     * @param a First number
     * @param b Second number
     * @param operator Indicates operation to be done
     * @return Resulting number
     * @throws IllegalArgumentException If given an unrecognizable operator, or if dividing by 0
     */
    private static Double doArithmetic(Double a, Double b, Character operator) {
        if (operator == '+') {
            return a + b;
        } else if (operator == '-') {
            return a - b;
        } else if (operator == '*') {
            return a * b;
        } else if (operator == '/') {
            if (b == 0) {
                throw new IllegalArgumentException("Division by 0 undefined");
            }
            return a / b;
        } else {
            throw new IllegalArgumentException("Operator not recognized");
        }
    }

    /**
     * Prints the result of a postfix expression from the command line.
     * 
     * @param args Postfix expression
     * @throws IllegalArgumentException If the expression is malformed
     */
    public static void main(String args[]){
        if (args.length == 0) {
            System.err.println("Usage:  java CalculatePostfix <expr>");
        } else {
            System.out.println("Answer: " + postfixToResult(Tokenizer.readTokens(args[0])));
        }
    }
}
