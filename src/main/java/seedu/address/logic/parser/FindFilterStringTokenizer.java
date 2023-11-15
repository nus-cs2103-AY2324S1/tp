package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses complex boolean find filter strings into tokens.
 */
public class FindFilterStringTokenizer {

    public static final Pattern VALID_CONDITION = Pattern
            .compile("^[a-zA-Z0-9]+/(?:[a-zA-Z0-9,@.\\-_#$]+|\"[a-zA-Z0-9,@.\\-_#$()/!&| ]+\")");
    private final String filterString;
    private int pos = 0;

    /**
     * Constructs a FindFilterStringTokenizer.
     *
     * @param filterString the find filter string to tokenize.
     */
    public FindFilterStringTokenizer(String filterString) {
        this.filterString = filterString;
    }

    /**
     * Converts the provided find string into a list of tokens.
     *
     * @return a list of tokens representing the filter string components
     */
    public List<Token> tokenize() throws ParseException {
        List<Token> tokens = new ArrayList<>();

        while (pos < filterString.length()) {
            char current = peek();

            if (Character.isWhitespace(current)) {
                next();
            } else if (current == '&' && peek(1) == '&') {
                tokens.add(new Token(Token.Type.AND, "&&"));
                pos += 2;
            } else if (current == '|' && peek(1) == '|') {
                tokens.add(new Token(Token.Type.OR, "||"));
                pos += 2;
            } else if (current == '!') {
                tokens.add(new Token(Token.Type.NOT, "!"));
                pos++;
            } else if (current == '(' || current == ')') {
                tokens.add(new Token(current == '('
                        ? Token.Type.LPAREN
                        : Token.Type.RPAREN, String.valueOf(current)));
                pos++;
            } else {
                String conditionString = readConditionToken();
                if (!isValidCondition(conditionString)) {
                    throw new ParseException("Invalid filter string: "
                            + "Condition should be of the form 'FIELD/KEYWORD' or 'FIELD/\"KEYWORD WITH SPACES\"'!"
                            + "\nAdditionally, if special characters like (, ), |, &, /, ! are used in the keyword,"
                            + " it should be enclosed in double-quotes!"
                            + "\nYou may not search for the double-quote character itself!");
                }

                tokens.add(new Token(Token.Type.CONDITION, conditionString));
            }
        }

        return tokens;
    }

    private String readConditionToken() throws ParseException {
        StringBuilder sb = new StringBuilder();

        while (pos < filterString.length() && isPartOfCondition(peek())) {
            sb.append(next());

            // When a '/' is encountered, determine the next part of the condition.
            if (sb.charAt(sb.length() - 1) == '/' && peek() == '\"') {
                sb.append(next()); // Consume the opening double-quote

                while (pos < filterString.length() && peek() != '\"') {
                    sb.append(next());
                }

                if (peek() == '\"') {
                    sb.append(next()); // Consume the closing double-quote
                } else {
                    throw new ParseException("Invalid filter string: "
                            + "Unmatched double-quote in filter string!");
                }

                // Check that the closing double-quote is the end of the condition
                if (sb.charAt(sb.length() - 1) == '\"' && isPartOfCondition(peek())) {
                    throw new ParseException("Invalid filter string: "
                            + "Closing double-quote should be the end of condition!");
                }
            }
        }

        return sb.toString();

    }

    private boolean isPartOfCondition(char c) {
        return Character.isLetterOrDigit(c)
                || c == '/' || c == ',' || c == '@' || c == '.' || c == '-'
                || c == '_' || c == '#' || c == '$' || c == '\"';
    }

    private boolean isValidCondition(String conditionString) {
        return VALID_CONDITION.matcher(conditionString).matches();
    }


    private char next() {
        return filterString.charAt(pos++);
    }

    private char peek() {
        return (pos < filterString.length())
                ? filterString.charAt(pos)
                : '\0';
    }

    private char peek(int relativePosition) {
        return ((pos + relativePosition) < filterString.length())
                ? filterString.charAt(pos + relativePosition)
                : '\0';
    }

    /**
     * Represents a component (token) in the boolean filter string.
     */
    public static class Token {
        final Type type;
        final String text;

        /**
         * Constructs a token.
         *
         * @param type the type of the token.
         * @param text the text representing the token in the filter string.
         */
        Token(Type type, String text) {
            this.type = type;
            this.text = text;
        }

        @Override
        public String toString() {
            return type + ":" + text;
        }

        enum Type {
            AND, OR, NOT, LPAREN, RPAREN, CONDITION
        }
    }

}
