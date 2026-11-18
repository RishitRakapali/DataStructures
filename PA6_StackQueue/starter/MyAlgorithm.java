/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA6 write-up, course materials
 *
 * This file checks whether brackets in a string are correctly matched
 * and nested. It uses MyStack to process the input in linear time.
 */

/**
 * Provides an algorithm to validate the matching and order of brackets.
 * Ignores characters that are not one of the six bracket symbols.
 */
public class MyAlgorithm {
    private static final char OPEN_PAREN = '(';
    private static final char CLOSE_PAREN = ')';
    private static final char OPEN_SQUARE = '[';
    private static final char CLOSE_SQUARE = ']';
    private static final char OPEN_CURLY = '{';
    private static final char CLOSE_CURLY = '}';

    /**
     * Checks whether every opening bracket has a correctly ordered match.
     * Returns true for empty strings and strings without brackets.
     *
     * @param input the string to examine for balanced brackets
     * @return true if every bracket is matched and correctly nested
     */
    public static boolean isValidBrackets(String input) {
        if (input == null) {
            throw new NullPointerException();
        }

        MyStack<Character> openBrackets =
                new MyStack<Character>(input.length());

        for (int index = 0; index < input.length(); index++) {
            char currentChar = input.charAt(index);

            if (currentChar == OPEN_PAREN || currentChar == OPEN_SQUARE
                    || currentChar == OPEN_CURLY) {
                openBrackets.push(currentChar);
            } else if (currentChar == CLOSE_PAREN) {
                if (openBrackets.empty()
                        || openBrackets.pop() != OPEN_PAREN) {
                    return false;
                }
            } else if (currentChar == CLOSE_SQUARE) {
                if (openBrackets.empty()
                        || openBrackets.pop() != OPEN_SQUARE) {
                    return false;
                }
            } else if (currentChar == CLOSE_CURLY) {
                if (openBrackets.empty()
                        || openBrackets.pop() != OPEN_CURLY) {
                    return false;
                }
            }
        }

        return openBrackets.empty();
    }
}
