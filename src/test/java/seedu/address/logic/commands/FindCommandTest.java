package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TeachingCoursePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ArrayList<Predicate<Person>> firstCommandPredicates = new ArrayList<>();
        ArrayList<Predicate<Person>> secondCommandPredicates = new ArrayList<>();
        firstCommandPredicates.add(firstPredicate);
        secondCommandPredicates.add(secondPredicate);
        FindCommand findFirstCommand = new FindCommand(firstCommandPredicates);
        FindCommand findSecondCommand = new FindCommand(secondCommandPredicates);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstCommandPredicates);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        expectedMessage = "Filters applied: " + predicate.toFilterString() + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        expectedMessage = "Filters applied: " + predicate.toFilterString() + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_moduleFilter_singlePersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        TeachingCoursePredicate predicate = prepareTeachingPredicate("CS2103T");
        expectedMessage = "Filters applied: " + predicate.toFilterString() + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_moduleFilter_multiplePersonsFound1() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TeachingCoursePredicate predicate = prepareTeachingPredicate("CS2100");
        expectedMessage = "Filters applied: " + predicate.toFilterString() + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_moduleFilter_multiplePersonsFound2() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TeachingCoursePredicate predicate = prepareTeachingPredicate("CS1231S");
        expectedMessage = "Filters applied: " + predicate.toFilterString() + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate);
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_moduleFilter_courseNotFound() {
        ParseException exception = assertThrows(ParseException.class, () -> prepareTeachingPredicate("CS1269"));
        assertEquals("Course not found.\n"
                + "Available courses: CS1231S, CS1101S, CS2040S, CS2100, CS2103T", exception.getMessage());
    }

    @Test
    public void execute_nameAndModuleFilter_singlePersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate1 = prepareNamePredicate("Carl");
        TeachingCoursePredicate predicate2 = prepareTeachingPredicate("CS1231S");
        expectedMessage = "Filters applied: " + predicate1.toFilterString() + ", " + predicate2.toFilterString()
                + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate1);
        predicates.add(predicate2);
        FindCommand command = new FindCommand(predicates);
        Predicate<Person> combinedPredicate = predicates.stream().reduce(x -> true, Predicate::and);
        expectedModel.updateFilteredPersonList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndModuleFilter_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate1 = prepareNamePredicate("Elle Fiona");
        TeachingCoursePredicate predicate2 = prepareTeachingPredicate("CS2100");
        expectedMessage = "Filters applied: " + predicate1.toFilterString() + ", " + predicate2.toFilterString()
                + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate1);
        predicates.add(predicate2);
        FindCommand command = new FindCommand(predicates);
        Predicate<Person> combinedPredicate = predicates.stream().reduce(x -> true, Predicate::and);
        expectedModel.updateFilteredPersonList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndModuleFilter_noPersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate1 = prepareNamePredicate("Darius");
        TeachingCoursePredicate predicate2 = prepareTeachingPredicate("CS2100");
        expectedMessage = "Filters applied: " + predicate1.toFilterString() + ", " + predicate2.toFilterString()
                + "\n" + expectedMessage;
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate1);
        predicates.add(predicate2);
        FindCommand command = new FindCommand(predicates);
        Predicate<Person> combinedPredicate = predicates.stream().reduce(x -> true, Predicate::and);
        expectedModel.updateFilteredPersonList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(predicate);
        FindCommand findCommand = new FindCommand(predicates);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=[" + predicate + "]}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private TeachingCoursePredicate prepareTeachingPredicate(String userInput) throws ParseException {
        Set<Course> courseList = ParserUtil.parseCourses(Arrays.asList(userInput.split("\\s+")));
        return new TeachingCoursePredicate(new ArrayList<>(courseList));
    }
}
