package seedu.address.logic.parser;

import seedu.address.model.person.GenderPredicate;
import seedu.address.model.person.IcContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordParser {
    public static Predicate<Person> parseInput(String[] input) {
        Pattern nricPattern = Pattern.compile("^T[0-9]{7}G$");
        Pattern genderPattern = Pattern.compile("^Gender (MALE|FEMALE)$");

        Matcher genderMatcher = genderPattern.matcher(input[0]);
        Matcher nricMatcher = nricPattern.matcher(input[0]);


        if (nricMatcher.matches()) {
            return new IcContainsKeywordsPredicate(input[0]);
        } else if (genderMatcher.matches()) {
            return new GenderPredicate(input[0]);
        } else {
            return new NameContainsKeywordsPredicate(Arrays.asList(input));
        }
    }
}
