package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.BloodTypePredicate;
import seedu.address.model.person.GenderPredicate;
import seedu.address.model.person.IcContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;


public class KeywordParserTest {
    @Test
    public void parseInputWithName() {
        String[] input = {"John", "Doe"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithIC() {
        String[] input = {"S1234567A"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof IcContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithGender() {
        String[] input = {"M"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof GenderPredicate);
    }

    @Test
    public void parseInputWithBloodType() {
        String[] input = {"Blood", "Type", "A+"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof BloodTypePredicate);
    }

    @Test
    public void parseInputWithInvalidGender() {
        String[] input = {"X"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithInvalidBloodType() {
        String[] input = {"Blood", "Type", "C"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithMultipleKeywords() {
        String[] input = {"John", "Doe", "Carl"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof NameContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithICAndGender() {
        String[] input = {"S1234567A", "M"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof IcContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithICAndBloodType() {
        String[] input = {"S1234567A", "A+"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof IcContainsKeywordsPredicate);
    }

    @Test
    public void parseInputWithGenderAndBloodType() {
        String[] input = {"M", "A+"};
        Predicate<Person> predicate = KeywordParser.parseInput(input);
        assertTrue(predicate instanceof GenderPredicate);
    }
}
