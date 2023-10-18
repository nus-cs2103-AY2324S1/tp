package seedu.address.logic.search;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Utility class that parses search strings accepted by the Find/List command.
 */
public class FindCommandArgumentParser {

    private String search;
    private int index;

    private enum Joiner implements BiFunction<SearchMatcher, SearchMatcher, SearchMatcher> {
        IMPLICIT_AND(' ', 2, SearchMatcher::and),
        EXPLICIT_OR('|', 1, SearchMatcher::or),
        EXPLICIT_AND('&', 0, SearchMatcher::and);

        private static final Map<Character, Joiner> set = new HashMap<>();

        private final char symbol;
        private final int precedence;
        private final BiFunction<SearchMatcher, SearchMatcher, SearchMatcher> join;

        Joiner(char symbol, int precedence, BiFunction<SearchMatcher, SearchMatcher, SearchMatcher> join) {
            this.symbol = symbol;
            this.precedence = precedence;
            this.join = join;
        }

        static Joiner get(Character c) {
            return set.get(c);
        }

        boolean isLeqPrecedenceThan(Joiner other) {
            return this.precedence <= other.precedence;
        }

        @Override
        public SearchMatcher apply(SearchMatcher a, SearchMatcher b) {
            return join.apply(a, b);
        }

        static {
            for (Joiner j : Joiner.values()) {
                set.put(j.symbol, j);
            }
        }
    }

    private static class ParserDualStack {

        private final Stack<SearchMatcher> predicates;
        private final Stack<Joiner> joiners;
        private NextInput nextInput;

        ParserDualStack() {
            predicates = new Stack<>();
            joiners = new Stack<>();
            nextInput = NextInput.PREDICATE;
        }

        void sanityCheckElseThrow() throws UnexpectedTokenException {
            if (
                    predicates.size() + (nextInput == NextInput.PREDICATE ? 1 : 0)
                            != joiners.size() + 1
            ) {
                throw new UnexpectedTokenException(nextInput.getOther(), nextInput);
            }
        }

        SearchMatcher collapse() throws UnexpectedTokenException {
            sanityCheckElseThrow();
            if (nextInput == NextInput.PREDICATE) {
                throw new UnexpectedTokenException(NextInput.JOINER, nextInput);
            }
            if (joiners.isEmpty() && predicates.isEmpty()) {
                return null;
            }
            while (!joiners.isEmpty()) {
                Joiner joiner = joiners.pop();
                SearchMatcher newPredicate = joiner.apply(
                        predicates.pop(),
                        predicates.pop()
                );
                predicates.push(newPredicate);
            }
            assert predicates.size() == 1;
            return predicates.pop();
        }

        enum NextInput {
            PREDICATE, JOINER;

            NextInput getOther() {
                return NextInput.getOther(this);
            }

            private static NextInput getOther(NextInput input) {
                switch (input) {
                case PREDICATE:
                    return JOINER;
                case JOINER:
                    return PREDICATE;
                default:
                    assert false : "Unexpected NextInput value: " + input;
                    return input;
                }
            }
        }

        static class UnexpectedTokenException extends Exception {
            UnexpectedTokenException(NextInput ignoredExpected, NextInput ignoredActual) {}
        }

        void append(SearchMatcher predicate) throws UnexpectedTokenException {
            throwIfUnexpectedNextInput(NextInput.PREDICATE);
            predicates.push(predicate);
            nextInput = NextInput.JOINER;
        }

        void append(Joiner joiner) throws UnexpectedTokenException {
            throwIfUnexpectedNextInput(NextInput.JOINER);
            resolveOperationsBeforeAppending(joiner);
            joiners.push(joiner);
            nextInput = NextInput.PREDICATE;
        }

        void throwIfUnexpectedNextInput(NextInput actualInput) throws UnexpectedTokenException {
            if (nextInput != actualInput) {
                throw new UnexpectedTokenException(nextInput, NextInput.JOINER);
            }
        }

        void resolveOperationsBeforeAppending(Joiner joiner) {
            while (!joiners.isEmpty() && joiner.isLeqPrecedenceThan(joiners.peek())) {
                predicates.push(joiners.pop().apply(predicates.pop(), predicates.pop()));
            }
        }
    }

    private class RecursiveParseHelper {

        private final ParserDualStack dualStack = new ParserDualStack();

        RecursiveParseHelper() {
        }

        RecursiveParseHelper(SearchMatcher predicate) throws ParserDualStack.UnexpectedTokenException {
            dualStack.append(predicate);
        }

        SearchMatcher parse() throws ParserDualStack.UnexpectedTokenException {
            while (hasChar()) {
                if (getChar() == '(') {
                    incrementCharIndex();
                    dualStack.append(new RecursiveParseHelper().parse());
                    dualStack.sanityCheckElseThrow();
                } else if (getChar() == ')') {
                    incrementCharIndex(); //consume ')'
                    break;
                }
                getAndAppendNextToken();
            }
            return collapse();
        }

        SearchMatcher collapse() throws ParserDualStack.UnexpectedTokenException {
            return dualStack.collapse();
        }

        void getAndAppendNextToken() throws ParserDualStack.UnexpectedTokenException {
            if (dualStack.nextInput == ParserDualStack.NextInput.JOINER) {
                findAndAppendNextJoiner();
            } else if (dualStack.nextInput == ParserDualStack.NextInput.PREDICATE) {
                findAndAppendNextPredicate();
            } else {
                assert false; //error has occurred
            }
        }

        void findAndAppendNextJoiner() throws ParserDualStack.UnexpectedTokenException {
            incrementIndexWhileSpace();
            if (!hasChar()) {
                return;
            }
            if (isCharJoiner()) {
                dualStack.append(Joiner.get(getChar()));
                incrementCharIndex();
            } else {
                dualStack.append(Joiner.get(' '));
            }
            incrementIndexWhileSpace();
        }

        void findAndAppendNextPredicate() throws ParserDualStack.UnexpectedTokenException {
            incrementIndexWhileSpace();
            if (!hasChar()) {
                return;
            }
            int startIndexOfPredicate = index;
            incrementIndexWhileNotReservedChar();
            dualStack.append(new SingleTextSearchMatcher(search.substring(startIndexOfPredicate, index)));
        }

    }

    private boolean isCharJoiner() {
        return Joiner.set.containsKey(getChar());
    }

    /**
     * Returns a SearchPredicate representing the provided expression.
     * Meant for use with {@link FindCommand}.
     *
     * @param query expression to parse into a predicate for Persons.
     * @return SearchPredicate that returns true if applied to a Person matching the expression provided.
     * @throws ParseException if expression is not supported or is invalid.
     */
    public SearchPredicate parse(String query) throws ParseException {
        if (query == null) {
            return new SearchPredicate(null);
        }
        try {
            search = query.trim();
            SearchMatcher predicate = parse();
            return new SearchPredicate(predicate);
        } catch (ParserDualStack.UnexpectedTokenException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private SearchMatcher parse() throws ParserDualStack.UnexpectedTokenException {
        index = 0;
        SearchMatcher predicate = new RecursiveParseHelper().parse();
        while (hasChar()) {
            incrementIndexWhileSpace();
            predicate = new RecursiveParseHelper(predicate).parse();
        }
        return predicate;
    }

    private void incrementIndexWhileSpace() {
        while (hasChar() && Character.isSpaceChar(getChar())) {
            incrementCharIndex();
        }
    }

    private void incrementIndexWhileNotReservedChar() {
        while (hasChar()) {
            char c = getChar();
            if (Character.isWhitespace(c) || isCharJoiner()) {
                break;
            }
            switch (c) {
            case '(':
            case ')':
                return;
            default:
                incrementCharIndex();
            }
        }
    }

    private void incrementCharIndex() {
        if (hasChar()) {
            index++;
        }
    }

    private boolean hasChar() {
        return index < search.length();
    }

    private char getChar() {
        return search.charAt(index);
    }

}
