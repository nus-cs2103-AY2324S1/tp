package seedu.lovebook.commons.util;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

/**
 * A class that takes in an arraylist of predicates and package it into a predicate
 */
public class PredicatesUtil implements Predicate<Date> {
    private ArrayList<MetricContainsKeywordPredicate> p;

    /**
     * Constructor for PredicatesUtil
     * @param predicates
     */
    public PredicatesUtil(ArrayList<MetricContainsKeywordPredicate> predicates) {
        this.p = predicates;
    }

    /**
     * Test if the input argument satisfies all the predicates in the arraylist
     * @param person the input argument
     * @return
     */
    public boolean test(Date person) {
        for (MetricContainsKeywordPredicate predicate : p) {
            if (!predicate.test(person)) {
                return false;
            }
        }
        return true;
    }
}
