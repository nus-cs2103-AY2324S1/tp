package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.FindFilterStringTokenizer.Token;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a parser that constructs and interprets expression trees
 * from tokenized search queries. This parser helps in translating user
 * input queries into predicates that can be applied to filter lists of
 * persons.
 * <p>
 * Uses a basic recursive-descent parsing strategy, since for now the
 * grammar is compact. Grammar for find command is given, in
 * Backus–Naur form (BNF) syntax, as follows:
 *
 * <p>
 *
 * <pre>{@code
 * <expression> ::= <term> | <term> "||" <expression>
 * <term>       ::= <factor> | <factor> "&&" <term>
 * <factor>     ::= "!" <factor>
 *                | "(" <expression> ")"
 *                | <condition>
 * <condition>  ::= <prefix> "/" <keyword>
 * <keyword>    ::= <character> | <character> <keyword>
 * <prefix>     ::= "n" | "p" | "e" | "a" | "t"
 * }</pre>
 *
 * <p>
 *
 * Notice that an expression can be a term (if no OR is present)
 * which can itself be a factor (if no AND is present).
 *
 */
public class FindExpressionParser {

    private List<Token> tokens;
    private int pos = 0;

    /**
     * Parses a list of tokens into an expression tree, and returns
     * the final predicate that the ast resolves to.
     *
     * @param tokens The list of tokens to be parsed.
     * @return The predicate representing the tokens when parsed into an expression tree.
     */
    public Predicate<Person> parseToPredicate(List<Token> tokens) throws ParseException {
        if (tokens.isEmpty()) {
            throw new ParseException("Expression is empty!");
        }
        this.tokens = tokens;
        ExprNode completedAst = expression();
        if (!isAtEnd()) {
            throw new ParseException("Find command received an invalid filter string!");
        }
        return completedAst.toPredicate();
    }

    /**
     * Parses and constructs an "expression" node from the current list of tokens.
     * <p>
     * In the context of parsers and grammars, an "expression" is a higher-level construct representing
     * a combination of one or more "terms". The terms within an expression are typically combined
     * using the OR operator. For example, in a query "n/Bob OR (t/friends AND t/colleagues)",
     * both "n/Bob" and "(t/friends AND t/colleagues)" are terms combined to form an expression.
     * </p>
     *
     * @return The parsed expression node.
     */
    private ExprNode expression() throws ParseException {
        ExprNode node = term();

        while (isNextTokenType(Token.Type.OR)) {
            Token op = consume(Token.Type.OR);
            ExprNode right = term();
            node = new BinaryOpNode(node, FindExpressionOperator.OR, right);
        }

        return node;
    }

    /**
     * Parses and constructs a "term" node from the current list of tokens.
     * <p>
     * In the context of parsers and grammars, a "term" is a mid-level construct that consists of one or more
     * "factors". The factors within a term are usually combined using the AND operator. For instance,
     * in a query "n/Bob AND t/friends", both "n/Bob" and "t/friends" are factors combined to create a term.
     * </p>
     *
     * @return The parsed term node.
     */
    private ExprNode term() throws ParseException {
        ExprNode node = factor();

        while (isNextTokenType(Token.Type.AND)) {
            Token op = consume(Token.Type.AND);
            ExprNode right = factor();
            node = new BinaryOpNode(node, FindExpressionOperator.AND, right);
        }

        return node;
    }

    /**
     * Parses and constructs a "factor" node from the current list of tokens.
     * <p>
     * A "factor" is a foundational construct in this parser. It's the most granular unit and represents
     * a single condition or criteria in the query. Factors are building blocks for terms and expressions.
     * </p>
     *
     * @return The parsed factor node.
     */
    private ExprNode factor() throws ParseException {
        if (isNextTokenType(Token.Type.NOT)) {
            consume(Token.Type.NOT);
            ExprNode node = factor();
            return new NotNode(node);
        } else if (isNextTokenType(Token.Type.LPAREN)) {
            consume(Token.Type.LPAREN);
            ExprNode node = expression();
            consume(Token.Type.RPAREN);
            return node;
        } else {
            Token token = consume(Token.Type.CONDITION);
            // check if text contains a slash and is a valid condition
            if (!token.text.contains("/") || token.text.startsWith("/") || token.text.endsWith("/")) {
                throw new ParseException("Invalid condition: " + token.text);
            }
            // split by slash but include slash in substrings
            String[] parts = token.text.split("(?<=/)");
            FindSupportedField field = FindSupportedField.createFromPrefix(parts[0].trim().toLowerCase());
            return new ConditionNode(field, parts[1].trim());
        }
    }

    private boolean isNextTokenType(Token.Type type) {
        if (isAtEnd()) {
            return false;
        }
        return peek().type == type;
    }

    private Token consume(Token.Type type) throws ParseException {
        if (isNextTokenType(type)) {
            return tokens.get(pos++);
        }
        if (isAtEnd()) {
            throw new ParseException("Find command received an invalid filter string: "
                    + "Expected token of type " + type + " but reached end of input!");
        }
        throw new ParseException("Find command received an invalid filter string: "
                + "Expected token of type " + type + " but found " + peek().type);
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private boolean isAtEnd() {
        return pos == tokens.size();
    }

    /**
     * Enum representing the logical operators supported by the parser.
     */
    enum FindExpressionOperator {
        AND, OR
    }

    /**
     * Enum representing the fields that can be searched.
     */
    enum FindSupportedField {

        NAME(PREFIX_NAME.getPrefix()),
        PHONE(PREFIX_PHONE.getPrefix()),
        EMAIL(PREFIX_EMAIL.getPrefix()),
        ADDRESS(PREFIX_ADDRESS.getPrefix()),
        TAG(PREFIX_TAG.getPrefix());

        private final String prefix;

        FindSupportedField(String prefix) {
            this.prefix = prefix;
        }

        /**
         * Returns the corresponding field for a given prefix.
         *
         * @param prefix The prefix to look up.
         * @return The supported field corresponding to the given prefix.
         * @throws ParseException if the prefix is not supported.
         */
        public static FindSupportedField createFromPrefix(String prefix) throws ParseException {
            for (FindSupportedField field : FindSupportedField.values()) {
                if (field.prefix.equals(prefix)) {
                    return field;
                }
            }
            throw new ParseException("No supported field with prefix " + prefix);
        }
    }

    /**
     * Abstract class representing a node in the expression tree.
     */
    private abstract static class ExprNode {

        /**
         * Converts the expression node into a predicate for filtering persons.
         *
         * @return A predicate representing the conditions set by this node.
         */
        abstract Predicate<Person> toPredicate();
    }

    /**
     * Node representing a binary operation in the expression tree.
     */
    static class BinaryOpNode extends ExprNode {

        final ExprNode left;
        final ExprNode right;
        final FindExpressionOperator operator;

        BinaryOpNode(ExprNode left, FindExpressionOperator operator, ExprNode right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        Predicate<Person> toPredicate() {
            Predicate<Person> leftPred = left.toPredicate();
            Predicate<Person> rightPred = right.toPredicate();
            return (operator == FindExpressionOperator.AND)
                    ? leftPred.and(rightPred)
                    : leftPred.or(rightPred);
        }
    }

    /**
     * Node representing a NOT operation in the expression tree.
     */
    static class NotNode extends ExprNode {

        final ExprNode operand;

        NotNode(ExprNode operand) {
            this.operand = operand;
        }

        @Override
        Predicate<Person> toPredicate() {
            return operand.toPredicate().negate();
        }
    }

    /**
     * Node representing a condition in the expression tree.
     */
    static class ConditionNode extends ExprNode {

        final FindSupportedField field;
        final String keyword;

        ConditionNode(FindSupportedField field, String keyword) {
            this.field = field;
            this.keyword = keyword;
        }

        @Override
        Predicate<Person> toPredicate() {
            switch (field) {
            case NAME:
                return person -> StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword);
            case PHONE:
                return person -> StringUtil.containsSubstringIgnoreCase(person.getPhone().value, keyword);
            case EMAIL:
                return person -> StringUtil.containsSubstringIgnoreCase(person.getEmail().value, keyword);
            case ADDRESS:
                return person -> StringUtil.containsSubstringIgnoreCase(person.getAddress().value, keyword);
            case TAG:
                // Tags are slightly more complicated -- a person passes the predicate if the tag
                // specified is a member of the person's tag set. The specified tag must be an exact
                // match, not a substring match.
                return person -> person.getTags().contains(new Tag(keyword));
            default:
                throw new IllegalStateException("Unexpected field: " + field);
            }
        }
    }
}
