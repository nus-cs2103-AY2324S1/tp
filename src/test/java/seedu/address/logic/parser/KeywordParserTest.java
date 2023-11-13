package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.GenderPredicate;
import seedu.address.model.person.IcContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;


public class KeywordParserTest {
    @Test
    public void parseInputWithName_returnsNamePredicate() {
        String[] input = {"John", "Doe"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithIC_returnsIcPredicate() {
        String[] input = {"S1234567A"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof IcContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithGender_returnsGenderPredicate() {
        String[] input = {"M"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof GenderPredicate);
    }

    @Test
    public void parseInputWithInvalidGender_returnsNamePredicate() {
        String[] input = {"X"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithMultipleKeywords_returnsNamePredicate() {
        String[] input = {"John", "Doe", "Carl"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithIcAndGender_returnsIcPredicate() {
        String[] input = {"S1234567A", "M"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof IcContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithIcAndRandom_returnsIcPredicate() {
        String[] input = {"S1234567A", "RANDOM"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof IcContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithGenderAndRandom_returnsGenderPredicate() {
        String[] input = {"M", "RANDOM"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof GenderPredicate);
    }
}
