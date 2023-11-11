package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BALANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SECONDARY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.FindFilterStringTokenizer.Token;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Person;

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

    private final List<Token> tokens;
    private int pos = 0;

    /**
     * Constructs a FindExpressionParser.
     *
     * @param tokens The list of tokens to parse.
     */
    public FindExpressionParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Parses a list of tokens into an expression tree, and returns
     * the final predicate that the ast resolves to.
     *
     * @return The predicate representing the tokens when parsed into an expression tree.
     */
    public Predicate<Person> parseToPredicate() throws ParseException {
        if (this.tokens.isEmpty()) {
            throw new ParseException("Expression is empty!");
        }
        ExprNode completedAst = parseExpression();
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
    private ExprNode parseExpression() throws ParseException {
        ExprNode node = parseTerm();

        while (isNextTokenType(Token.Type.OR)) {
            Token op = consume(Token.Type.OR);
            ExprNode right = parseTerm();
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
    private ExprNode parseTerm() throws ParseException {
        ExprNode node = parseFactor();

        while (isNextTokenType(Token.Type.AND)) {
            Token op = consume(Token.Type.AND);
            ExprNode right = parseFactor();
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
    private ExprNode parseFactor() throws ParseException {
        if (isNextTokenType(Token.Type.NOT)) {
            consume(Token.Type.NOT);
            ExprNode node = parseFactor();
            return new NotNode(node);
        } else if (isNextTokenType(Token.Type.LPAREN)) {
            consume(Token.Type.LPAREN);
            ExprNode node = parseExpression();
            consume(Token.Type.RPAREN);
            return node;
        } else {
            Token token = consume(Token.Type.CONDITION);

            // split by slash but include slash in substrings
            String[] parts = token.text.split("(?<=/)");
            FindSupportedField field = FindSupportedField.createFromPrefix(parts[0].trim().toLowerCase());

            // remove double-quotes around keyword if present
            String keyword = parts[1].trim();
            if (keyword.startsWith("\"") && keyword.endsWith("\"")) {
                keyword = keyword.substring(1, keyword.length() - 1);
            }

            if (keyword.isEmpty()) {
                throw new ParseException("Invalid condition: Keyword cannot be empty!");
            }

            return new ConditionNode(field, keyword);
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
        TAG(PREFIX_TAG.getPrefix()),
        BIRTHDAY(PREFIX_BIRTHDAY.getPrefix()),
        LINKEDIN(PREFIX_LINKEDIN.getPrefix()),
        SECONDARY_EMAIL(PREFIX_SECONDARY_EMAIL.getPrefix()),
        TELEGRAM(PREFIX_TELEGRAM.getPrefix()),
        NOTE(PREFIX_NOTE.getPrefix()),
        BALANCE(PREFIX_BALANCE.getPrefix());


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
        abstract Predicate<Person> toPredicate() throws ParseException;
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
        Predicate<Person> toPredicate() throws ParseException {
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
        Predicate<Person> toPredicate() throws ParseException {
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
        Predicate<Person> toPredicate() throws ParseException {

            requireNonNull(field);

            switch (field) {
            case NAME:
                return createNamePredicate();
            case PHONE:
                return createPhonePredicate();
            case EMAIL:
                return createEmailPredicate();
            case ADDRESS:
                return createAddressPredicate();
            case TAG:
                return createTagPredicate();
            case BIRTHDAY:
                return createBirthdayPredicate();
            case LINKEDIN:
                return createLinkedinPredicate();
            case SECONDARY_EMAIL:
                return createSecondaryEmailPredicate();
            case TELEGRAM:
                return createTelegramPredicate();
            case NOTE:
                return createNotePredicate();
            case BALANCE:
                return createBalancePredicate();
            default:
                throw new AssertionError("Invalid field type!");
            }
        }

        /*
         * The following section includes methods that create predicates for
         * compulsory fields that use the basic "value contains keyword" logic.
         *
         * The createFieldContainsKeywordPredicate method facilitates
         * this by taking in a Person's getter function that returns a compulsory field.
         *
         * (This comment is not a Javadoc comment, and is meant for future maintainers)
         */

        private <T> Predicate<Person> createFieldContainsKeywordPredicate(Function<Person, T> getter) {
            return person -> StringUtil.containsSubstringIgnoreCase(getter.apply(person).toString(), keyword);
        }

        private Predicate<Person> createNamePredicate() {
            return createFieldContainsKeywordPredicate(Person::getName);
        }

        private Predicate<Person> createPhonePredicate() {
            return createFieldContainsKeywordPredicate(Person::getPhone);
        }

        private Predicate<Person> createEmailPredicate() {
            return createFieldContainsKeywordPredicate(Person::getEmail);
        }

        private Predicate<Person> createAddressPredicate() {
            return createFieldContainsKeywordPredicate(Person::getAddress);
        }

        /*
         * The following section includes methods that create predicates for
         * optional fields that use the basic "value contains keyword" logic,
         * but always return false if the optional field is not present.
         *
         * The createOptionalFieldContainsKeywordPredicate method facilitates
         * this by taking in a Person's getter function that returns an optional field.
         *
         * (This comment is not a Javadoc comment, and is meant for future maintainers)
         */

        private <T> Predicate<Person> createOptionalFieldContainsKeywordPredicate(
                Function<Person, Optional<T>> getter) {
            return person -> getter.apply(person)
                    .map(value -> StringUtil.containsSubstringIgnoreCase(value.toString(), keyword))
                    .orElse(false);
        }

        private Predicate<Person> createBirthdayPredicate() {
            return createOptionalFieldContainsKeywordPredicate(Person::getBirthday);
        }

        private Predicate<Person> createLinkedinPredicate() {
            return createOptionalFieldContainsKeywordPredicate(Person::getLinkedin);
        }

        private Predicate<Person> createSecondaryEmailPredicate() {
            return createOptionalFieldContainsKeywordPredicate(Person::getSecondaryEmail);
        }

        private Predicate<Person> createTelegramPredicate() {
            return createOptionalFieldContainsKeywordPredicate(Person::getTelegram);
        }

        /*
         * The following section is for methods that have field-specific behavior.
         *
         * (This comment is not a Javadoc comment, and is meant for future maintainers)
         */

        /**
         * Creates a predicate for the tags field.
         * Matches tags by checking if the keyword is an exact match of any tag in the person's tag set.
         *
         * @return A predicate that checks if a person's tag set contains the specified tag.
         */
        private Predicate<Person> createTagPredicate() {
            return person -> {
                // use tagName instead of Tag::toString to avoid considering the square brackets
                Set<String> tagsAsStrings = person.getTags().stream()
                        .map(tag -> tag.tagName).collect(Collectors.toSet());
                return StringUtil.containsStringIgnoreCaseInSet(tagsAsStrings, keyword);
            };
        }

        /**
         * Creates a predicate for the notes field.
         * Matches notes by checking if the keyword is a substring of any note in the person's note set.
         *
         * @return A predicate that checks if any note in a person's note set contains the specified keyword.
         */
        private Predicate<Person> createNotePredicate() {
            return person -> person.getNotes().stream()
                    .anyMatch(note -> StringUtil.containsSubstringIgnoreCase(note.toString(), keyword));
        }

        /**
         * Creates a predicate for the balance field.
         * If the keyword is a valid balance and is positive, the predicate checks if the person's balance is
         * greater than or equal to the keyword.
         * If the keyword is a valid balance and is negative, the predicate checks if the person's balance is
         * less than or equal to the keyword.
         * If the keyword is not a valid balance, the predicate throws a ParseException.
         *
         * @return A predicate that checks if a person's balance matches the given balance based on described logic.
         */
        private Predicate<Person> createBalancePredicate() throws ParseException {
            try {
                String preppedBalanceString = keyword.trim();

                // if there is a negative sign at start of keyword, remove it but record isNegative
                boolean isNegative = preppedBalanceString.startsWith("-");
                if (isNegative) {
                    preppedBalanceString = preppedBalanceString.replaceFirst("-", "");
                }

                Balance inputBalance = ParserUtil.parseBalance(preppedBalanceString);

                if (isNegative) {
                    return person -> person.getBalance().value <= -inputBalance.value;
                } else {
                    return person -> person.getBalance().value >= inputBalance.value;
                }

            } catch (NumberFormatException | ParseException e) {
                throw new ParseException("Invalid balance format: " + Balance.MESSAGE_CONSTRAINTS
                        + "\nAdditionally, when finding with a balance field, you can use"
                        + " a negative sign to find negative balances.");
            }
        }
    }
}
