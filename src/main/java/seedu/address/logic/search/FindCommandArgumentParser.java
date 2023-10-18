package seedu.address.logic.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Utility class that parses search strings accepted by the Find/List command.
 */
public class FindCommandArgumentParser {

    private enum Joiner implements BiFunction<SearchPredicate, SearchPredicate, SearchPredicate> {
        IMPLICIT_AND(' ', 2, SearchPredicate::and),
        EXPLICIT_OR('|', 1, SearchPredicate::or),
        EXPLICIT_AND('&', 0, SearchPredicate::and);

        private static final Map<Character, Joiner> set = new HashMap<>();

        private final char symbol;
        private final int precedence;
        private final BiFunction<SearchPredicate, SearchPredicate, SearchPredicate> join;
        Joiner(char symbol, int precedence, BiFunction<SearchPredicate, SearchPredicate, SearchPredicate> join) {
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
        void sanityCheckElseThrow() throws UnexpectedTokenException {
            if (
                    predicates.size() + (nextInput == NextInput.PREDICATE? 1:0)
                    != joiners.size() + 1
            ) {
                throw new UnexpectedTokenException(nextInput.getOther(), nextInput);
            }
        }

        SearchPredicate collapse() throws UnexpectedTokenException {
            sanityCheckElseThrow();
            while (!joiners.isEmpty()) {
                Joiner joiner = joiners.pop();
                SearchPredicate newPredicate = joiner.apply(
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
                    assert false: "Unexpected NextInput value: " + input;
                    return input;
                }
            }
        }

        private static class UnexpectedTokenException extends Exception {
            UnexpectedTokenException(NextInput expected, NextInput actual) {
                //todo log
                System.out.println("expected: " + expected + " actual: " + actual);
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

        RecursiveParseHelper() {}

        RecursiveParseHelper(SearchPredicate predicate) throws ParserDualStack.UnexpectedTokenException {
            dualStack.append(predicate);
        }

        SearchPredicate parse() throws ParserDualStack.UnexpectedTokenException {
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

        SearchPredicate collapse() throws ParserDualStack.UnexpectedTokenException {
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
            if (isNextCharJoiner()) {
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
            incrementIndexWhileAlphanumeric();
            dualStack.append(new SingleTextSearchPredicate(search.substring(startIndexOfPredicate, index)));
        }

    }

    private boolean isNextCharJoiner() {
        return Joiner.set.containsKey(search.charAt(index));
    }

    private String search;
    private int index;

    public SearchTest parse(String query) {
        if (query == null) {
            return null;
        }
        search = query.trim();
        try {
            SearchPredicate predicate = parse();
            return new SearchTest(predicate);
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
        while(hasChar() && Character.isSpaceChar(getChar())) {
            incrementCharIndex();
        }
    }

    private void incrementIndexWhileAlphanumeric() {
        while (hasChar() && Character.isLetterOrDigit(getChar())) {
            incrementCharIndex();
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
