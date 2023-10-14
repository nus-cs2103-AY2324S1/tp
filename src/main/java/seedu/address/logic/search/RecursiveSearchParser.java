package seedu.address.logic.search;

import seedu.address.logic.parser.Parser;

import java.util.ArrayList;

/**
 * Utility class that parses search strings recursively.
 */
public class RecursiveSearchParser{

    public SearchPredicateApplier parse(String search) {
        if (search == null) {
            return null;
        }
        return new SearchPredicateApplier(
                new RecursiveParseHelper(search, 0, search.length()).getSearchPredicate()
        );
    }

}
