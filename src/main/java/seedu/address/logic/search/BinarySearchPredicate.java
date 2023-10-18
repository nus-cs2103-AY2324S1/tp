package seedu.address.logic.search;

abstract class BinarySearchPredicate extends SearchPredicate{

    protected SearchPredicate a;
    protected SearchPredicate b;
    protected char operation;

    BinarySearchPredicate(SearchPredicate a, SearchPredicate b, char operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }


    @Override
    void setFlag(SearchPredicate.Flag flag, boolean isFlagApplied) {
        super.setFlag(flag, isFlagApplied);
        a.setFlag(flag, isFlagApplied);
        b.setFlag(flag, isFlagApplied);
    }

    @Override
    public String toString() {
        return "(" + a + operation + b + ")";
    }
}
