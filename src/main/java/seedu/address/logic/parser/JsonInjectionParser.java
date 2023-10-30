package seedu.address.logic.parser;

import java.util.Arrays;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class handles the sanitisation of user input to prevent any possible JSON injections.
 */
public class JsonInjectionParser {
    // List of possible JSON commands.
    private final String quote = "\'";
    private final String hexEscape = "\\x";
    private final String octalEscape = "\\0";
    private final String comma = ",";
    private final String openParentheses = "(";
    private final String closedParentheses = ")";
    private final String openCurlyBrackets = "{";
    private final String closedCurlyBrackets = "}";
    private String[] wordsToSanitise =
            new String[]{quote, hexEscape, octalEscape, comma, openParentheses, closedParentheses,
                openCurlyBrackets, closedCurlyBrackets};

    /**
     * This method parses the user input.
     * @param args The String input from the user.
     * @throws ParseException The exception with the message ot the user to not include characters that could
     *     form a JSON command.
     */
    public void parse(String args) throws ParseException {
        if (Arrays.stream(wordsToSanitise).anyMatch(args::contains)) {
            throw new ParseException("Please do not input JSON like content");
        }
    }
}
