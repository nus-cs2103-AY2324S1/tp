package seedu.address.logic.search;

abstract class BinarySearchMatcher extends SearchMatcher {

    protected SearchMatcher a;
    protected SearchMatcher b;
    protected char operation;

    BinarySearchMatcher(SearchMatcher a, SearchMatcher b, char operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }


    @Override
    void setFlag(SearchMatcher.Flag flag, boolean isFlagApplied) {
        super.setFlag(flag, isFlagApplied);
        a.setFlag(flag, isFlagApplied);
        b.setFlag(flag, isFlagApplied);
    }

    @Override
    public String toString() {
        return "(" + a + operation + b + ")";
    }
}
