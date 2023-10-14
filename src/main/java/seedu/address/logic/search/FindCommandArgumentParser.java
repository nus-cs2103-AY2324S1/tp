package seedu.address.logic.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Utility class that parses search strings recursively.
 */
public class FindCommandArgumentParser {

    private enum Joiner implements BiFunction<SearchPredicate, SearchPredicate, SearchPredicate> {
        IMPLICIT_AND(' ', 2, SearchPredicate::and),
        EXPLICIT_OR('|', 1, SearchPredicate::or),
        EXPLICIT_AND('&', 0, SearchPredicate::and);

        static Map<Character, Joiner> set = new HashMap<>();

        private final char symbol;
        private final int precedence;
        private final BiFunction<SearchPredicate, SearchPredicate, SearchPredicate> join;
        Joiner(char symbol, int precedence, BiFunction<SearchPredicate, SearchPredicate, SearchPredicate> join) {
            this.symbol = symbol;
            this.precedence = precedence;
            this.join = join;
        }

        boolean isLeqPrecedenceThan(Joiner other) {
            return this.precedence >= other.precedence;
        }

        @Override
        public SearchPredicate apply(SearchPredicate a, SearchPredicate b) {
            return join.apply(a,b);
        }

        static {
            for (Joiner j : Joiner.values()) {
                set.put(j.symbol, j);
            }
        }
    }

    private static class ParserDualStack {
        enum NextInput {
            PREDICATE, JOINER
        }

        private static class UnexpectedTokenException extends Exception {
            UnexpectedTokenException(NextInput expected, NextInput actual) {
                //todo log
            }
        }
        Stack<SearchPredicate> predicates;
        Stack<Joiner> joiners;
        NextInput nextInput;

        ParserDualStack() {
            predicates = new Stack<>();
            joiners = new Stack<>();
            nextInput = NextInput.PREDICATE;
        }

        void append(SearchPredicate predicate) throws UnexpectedTokenException {
            throwIfUnexpectedNextInput(NextInput.PREDICATE);
            predicates.push(predicate);
        }

        void append(Joiner joiner) throws UnexpectedTokenException {
            throwIfUnexpectedNextInput(NextInput.JOINER);
            resolveOperationsBeforeAppending(joiner);
            joiners.push(joiner);
        }

        void throwIfUnexpectedNextInput(NextInput actualInput) throws UnexpectedTokenException {
            if (nextInput != actualInput) {
                throw new UnexpectedTokenException(nextInput, NextInput.JOINER);
            }
        }

        void resolveOperationsBeforeAppending(Joiner joiner) {
            while (joiner.isLeqPrecedenceThan(joiners.peek())) {
                predicates.push(joiners.pop().apply(predicates.pop(), predicates.pop()));
            }
        }
    }

    private class RecursiveParseHelper {

        private ParserDualStack dualStack = new ParserDualStack();

        RecursiveParseHelper() {}

        RecursiveParseHelper(SearchPredicate predicate) throws ParserDualStack.UnexpectedTokenException {
            dualStack.append(predicate);
        }

        SearchPredicate parse() throws ParserDualStack.UnexpectedTokenException {
            while (index < search.length()) {
                if (nextChar() == '(') {

                }
                getAndAppendNextToken();
            }
        }

        SearchPredicate collapse() {
            // apply all the predicates and joiners in the dualstack
        }

        void getAndAppendNextToken() throws ParserDualStack.UnexpectedTokenException {
            if (dualStack.nextInput == ParserDualStack.NextInput.JOINER) {
                appendNextJoiner();
            } else if (dualStack.nextInput == ParserDualStack.NextInput.PREDICATE) {

            } else {
                assert false; //error has occurred
            }
        }

        void appendNextJoiner() throws ParserDualStack.UnexpectedTokenException {
            incrementIndexWhileSpace();
            if (isNextCharJoiner()) {
                dualStack.append(Joiner.set.get(nextChar()));
            } else {
                dualStack.append(Joiner.set.get(' '));
            }
        }

        void appendNextPredicate

    }

    private boolean isNextCharJoiner() {
        return Joiner.set.containsKey(search.charAt(index));
    }

    private String search;
    private int index;

    public SearchPredicateApplier parse(String stringToSearch) {
        if (stringToSearch == null) {
            return null;
        }
        search = stringToSearch.trim();
        try {
            SearchPredicate predicate = parse();
            return new SearchPredicateApplier(predicate);
        } catch (ParserDualStack.UnexpectedTokenException e) {
            throw new RuntimeException(e);
        }
    }

    private SearchPredicate parse() throws ParserDualStack.UnexpectedTokenException {
        index = 0;
        SearchPredicate predicate = new RecursiveParseHelper().parse();
        while (index < search.length()) {
            incrementIndexWhileSpace();
            predicate = new RecursiveParseHelper(predicate).parse();
        }
        return predicate;
    }

    private void incrementIndexWhileSpace() {
        while(nextChar() == ' ') {
            index++;
        }
    }

    private char nextChar() {
        return search.charAt(index);
    }

}
