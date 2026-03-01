/**
 * Class to evaluate infix expressions.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class CalculateInfix {

    /**
     * Takes in an infix expression as a queue and returns it in postfix form.
     * 
     * @param tokens Infix queue
     * @return Postfix Queue
     * @throws IllegalArgumentException If the expression is malformed
     */
    public static Queue<Object> infixToPostfix(Queue<Object> tokens) {
        Queue<Object> output = new Queue<Object>();
        Stack<Character> symbols = new Stack<Character>();
        while (!tokens.isEmpty()) {
            Object token = tokens.dequeue();
            if (token instanceof Double) { // enqueue number
                output.enqueue(token);
            } else if (token instanceof Character) {
                Character symbol = (Character) token;
                if (symbol == '(') { // push '(' to stack
                    symbols.push(symbol);
                } else if (symbol == ')') {
                    while (isOperator(symbols.peek())) { // pop and enqueue all operators above '('
                        output.enqueue(symbols.pop());
                    }
                    if (symbols.isEmpty()) { // throw exception if there was no '('
                        throw new IllegalArgumentException("Malformed parentheses");
                    }
                    symbols.pop(); // pop '('
                } else {
                    checkOperator(symbol);
                    while (isOperator(symbols.peek()) && !isHigherOperator(symbol, symbols.peek())) {
                        output.enqueue(symbols.pop());
                        // pop and enqueue operators until reach low precedence, empty stack, or '('
                    }
                    symbols.push(symbol); // push new operator
                }
            } else {
                throw new IllegalArgumentException("Token not recognized");
            }
        }
        while (!symbols.isEmpty()) {
            if (symbols.peek() == '(') { // throw exception if unclosed parentheses
                throw new IllegalArgumentException("Malformed parentheses");
            }
            output.enqueue(symbols.pop()); // pop and enqueue remaining operators
            // postfixToResult() will throw exception if there are extra operators on the end
        }
        return output;
    }

    /**
     * Takes in an infix expression as a queue and returns the result.
     * 
     * @param tokens Queue of symbols in infix expression
     * @return Resulting number
     * @throws IllegalArgumentException If the expression is malformed
     */
    public static Double infixToResult(Queue<Object> tokens) {
        return CalculatePostfix.postfixToResult(infixToPostfix(tokens));
    }

    /**
     * Compares the precedence of two operators.
     * 
     * @param a First operator
     * @param b Second operator
     * @return True if the first operator has strictly higher precedence than the second
     * @throws IllegalArgumentException If either operator is unrecognizable
     */
    private static boolean isHigherOperator(Character a, Character b) {
        checkOperator(a);
        checkOperator(b);
        return ((a == '*' || a == '/') && (b == '+' || b == '-'));
    }

    /**
     * Returns whether the given character is a valid operator (+, -, *, /).
     * 
     * @param op Operator to check
     * @return True if valid, false otherwise
     */
    private static boolean isOperator(Character op) {
        if (op == null) {
            return false;
        }
        return ((op == '+') || (op == '-') || (op == '*') || (op == '/'));
    }

    /**
     * Throws an exception if the given character is not a valid operator (+, -, *, /).
     * 
     * @param op Operator to check
     * @throws IllegalArgumentException If the operator is unrecognizable
     */
    private static void checkOperator(Character op) {
        if (!isOperator(op)) {
            throw new IllegalArgumentException("Operator not recognized");
        }
    }

    /**
     * Prints the result of an infix expression from the command line.
     * 
     * @param args Infix expression
     * @throws IllegalArgumentException If the expression is malformed
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage:  java CalculateInfix <expr>");
        } else {
            System.out.println("Answer: " + infixToResult(Tokenizer.readTokens(args[0])));
        }
    }
}
