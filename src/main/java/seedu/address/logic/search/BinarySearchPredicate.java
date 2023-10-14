package seedu.address.logic.search;

abstract class BinarySearchPredicate extends SearchPredicate{

    protected SearchPredicate a;
    protected SearchPredicate b;

    @Override
    void setFlag(SearchPredicate.Flag flag, boolean isFlagApplied) {
        super.setFlag(flag, isFlagApplied);
        a.setFlag(flag, isFlagApplied);
        b.setFlag(flag, isFlagApplied);
    }

}
