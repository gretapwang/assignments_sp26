import java.util.Scanner;

/**
 * Plays an animal guessing game with the user.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class AnimalGuess {
    
    /**
     * Asks the user a given question until they respond with a valid yes/no answer.
     * Returns the answer as a boolean.
     * 
     * @param question Question to ask
     * @param scanner Scanner to read user input
     * @return True if user answers yes, false if no
     */
    private static boolean getYNAnswer(String question, Scanner scanner) {
        System.out.println(question);
        String response = scanner.nextLine().strip().toLowerCase();
        if (response.equals("yes") || response.equals("y")) {
            return true;
        } else if (response.equals("no") || response.equals("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return getYNAnswer(question, scanner);
        }
    }

    /**
     * Asks the user for input until they enter a valid question (ending with '?').
     * Returns the question.
     * 
     * @param scanner Scanner to read user input
     * @return Question entered by user
     */
    private static String getQuestionInput(Scanner scanner) {
        String question = scanner.nextLine();
            if (question.isEmpty() || question.strip().charAt(question.length() - 1) != '?') {
                System.out.println("Invalid input. Please enter a question ending with a question mark.");
                return getQuestionInput(scanner);
            }
            return question;
    }

    /**
     * Runs guessing game.
     * Guesses the user's chosen animal by asking yes/no questions.
     * Retains information learned from each round by writing to a file
     * specified in the command line.
     * 
     * @param args Command line arguments, should contain file name
     * 
     */
    public static void main(String[] args) {
        String fileName;
        if (args.length >= 1) {
            fileName = args[0];
        } else {
            fileName = "AnimalTree.txt";
        }
        DecisionTree tree = new DecisionTree("");
        tree.populateFromFile(fileName);
        Scanner scanner = new Scanner(System.in);
        boolean stillPlaying = true;
        while (stillPlaying) {
            DecisionTree pos = tree;
            System.out.println("Think of an animal.\nI'll try to guess it.");
            while (pos.isBranch()) {
                pos = pos.makeChoice(getYNAnswer(pos.getData(), scanner));
            }
            if (getYNAnswer("Is your animal a " + pos.getData() + "?", scanner)) {
                System.out.println("I guessed it!");
            } else {
                System.out.println("I got it wrong.\nPlease help me to learn.\nWhat was your animal?");
                String newAnimal = scanner.nextLine();
                String oldAnimal = pos.getData();
                System.out.println("Type a yes or no question that would distinguish between a " + newAnimal
                    + " and a " + oldAnimal + ".");
                pos.setData(getQuestionInput(scanner));
                if (getYNAnswer("Would you answer yes to this question for the " + newAnimal + "?", scanner)) {
                    pos.setLeafChildren(newAnimal, oldAnimal);
                } else {
                    pos.setLeafChildren(oldAnimal, newAnimal);
                }
            }
            stillPlaying = getYNAnswer("Play again?", scanner);
        }
        scanner.close();
        tree.writeToFile(fileName);
    }
}