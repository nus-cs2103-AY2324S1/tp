package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.BloodTypePredicate;
import seedu.address.model.person.GenderPredicate;
import seedu.address.model.person.IcContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;


/**
 * Parses user input to generate corresponding predicate.
 */
public class KeywordParser {
    /**
     * Parses the given {@code String[]} of argument and returns
     * a Predicate that tests the given attribute.
     */
    public static Predicate<Person> parseInput(String[] input) {
        Pattern nricPattern = Pattern.compile("^[ST]\\d{7}[A-Z]$");
        Pattern genderPattern = Pattern.compile("^([MF])$");
        Pattern bloodtypePattern = Pattern.compile("^Blood Type (A\\+|A-|B\\+|B-|AB\\+|AB-|O\\+|O-)$");

        Matcher genderMatcher = genderPattern.matcher(input[0]);
        Matcher nricMatcher = nricPattern.matcher(input[0]);
        Matcher bloodtypeMatcher = bloodtypePattern.matcher(input[0]);

        if (nricMatcher.matches()) {
            return new IcContainsKeywordsPredicate(input[0]);
        } else if (genderMatcher.matches()) {
            return new GenderPredicate(input[0]);
        } else if (bloodtypeMatcher.matches()) {
            return new BloodTypePredicate(input[0]);
        }

        return new NameContainsKeywordsPredicate(Arrays.asList(input));
    }
}

