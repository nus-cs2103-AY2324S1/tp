package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.GenderPredicate;
import seedu.address.model.person.IcContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class KeywordParserTest {

    @Test
    public void test_userInput_returnsCorrectFindCommand() {
        String[] testIcInput = {"T1234567G"};
        String[] testGenderInput = {"M"};
        String[] testNameInput = {"Alice", "Bob"};

        Predicate<Person> testPredicate1 = new IcContainsKeywordsPredicate("T1234567G");
        assertTrue(KeywordParser.parseInput(testIcInput).equals(testPredicate1));

        Predicate<Person> testPredicate2 = new GenderPredicate("M");
        assertTrue(KeywordParser.parseInput(testGenderInput).equals(testPredicate2));

        Predicate<Person> testPredicate3 = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(KeywordParser.parseInput(testNameInput).equals(testPredicate3));
    }
}
