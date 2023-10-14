package seedu.address.logic.search;

class RecursiveParseHelper {

    private enum Precedence {
        IMPLICIT_AND,
        EXPLICIT_OR,
        EXPLICIT_AND;
    }
    private final int start;
    private final int end;
    private final String searchString;
    private int currentIndex;
    private SearchPredicate predicate;
    private int nestLevel;

    RecursiveParseHelper(String searchString, int start, int end) {
        this.searchString = searchString;
        this.start = start;
        this.end = end;
        parseSubstring();
    }

    private void parseSubstring() {
        int subPredicateStart = 0;
        for (currentIndex = start; currentIndex < end; currentIndex++) {
            switch(searchString.charAt(currentIndex)) {
            case '(':
                nestLevel++;
                subPredicateStart = currentIndex;
                break;
            case ')':
                nestLevel--;
                handleSubPredicate(subPredicateStart);
                break;
            case ' ':

            }
        }
    }

    private void handleSubPredicate(int subPredicateStart) {
        if (nestLevel <= 0) {
            if (nestLevel < 0) {
                subPredicateStart = start;
                nestLevel = 0;
            }
            consumeFlags();
            predicate = new RecursiveParseHelper(subPredicateStart, currentIndex+1).getSearchPredicate();
        }
    }

    private void consumeFlags() {
        //function starts with currentIndex -> ')'
        for (currentIndex++; currentIndex < end; currentIndex++) {
            char c = Character.toLowerCase(searchString.charAt(currentIndex));
            if (!Character.isAlphabetic(c)) {
                currentIndex--;
                return;
            }
            switch() {

            }
        }
    }

    SearchPredicate getSearchPredicate() {
        return this.predicate;
    }
}