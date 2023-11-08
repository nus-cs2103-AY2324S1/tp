package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPersonPredicate;
import seedu.address.model.person.LastContactTimeContainsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsPredicate;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private LocalDateTime emptyDateTime = LocalDateTime.MIN;
    private LocalDateTime lastContacted =
            LocalDateTime.of(LocalDate.of(0001, 01, 01), LocalTime.of(00, 00));
    private LocalDateTime lastContacted2 =
            LocalDateTime.of(LocalDate.of(0001, 01, 30), LocalTime.of(12, 00));

    @Test
    public void equals() {
        GeneralPersonPredicate firstPredicate =
                new GeneralPersonPredicate(
                        new NameContainsKeywordsPredicate(Collections.singletonList("first")),
                        new PhoneContainsPredicate(List.of("")),
                        new EmailContainsKeywordsPredicate(List.of("")),
                        new LastContactTimeContainsPredicate(lastContacted),
                        new StatusContainsKeywordsPredicate(List.of("")),
                        new PersonTagContainsKeywordsPredicate(List.of(""))
                );
        GeneralPersonPredicate secondPredicate =
                new GeneralPersonPredicate(
                        new NameContainsKeywordsPredicate(Collections.singletonList("second")),
                        new PhoneContainsPredicate(List.of("")),
                        new EmailContainsKeywordsPredicate(List.of("")),
                        new LastContactTimeContainsPredicate(lastContacted2),
                        new StatusContainsKeywordsPredicate(List.of("")),
                        new PersonTagContainsKeywordsPredicate(List.of(""))
                );

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allPersonFound() {
        int expectedNumber = model.getFilteredPersonList().size();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, expectedNumber);
        GeneralPersonPredicate predicate = preparePredicate(new String[]{"", "", "", "", ""}, emptyDateTime);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOB, ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_oneNameKeyword_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        GeneralPersonPredicate namePredicate = preparePredicate(new String[]{"ALICE", "", "", "", ""}, emptyDateTime);
        FindCommand findNameCommand = new FindCommand(namePredicate);
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(findNameCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_onePhoneKeyword_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        GeneralPersonPredicate phonePredicate = preparePredicate(new String[]{"", "512", "", "", ""}, emptyDateTime);
        FindCommand findPhoneCommand = new FindCommand(phonePredicate);
        expectedModel.updateFilteredPersonList(phonePredicate);
        assertCommandSuccess(findPhoneCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneLastContactKeyword_onePersonFound() {
        LocalDateTime time = DateTimeUtil.parse("20.10.2023 1100");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        GeneralPersonPredicate phonePredicate = preparePredicate(new String[]{"", "", "", "", ""}, time);
        FindCommand findPhoneCommand = new FindCommand(phonePredicate);
        expectedModel.updateFilteredPersonList(phonePredicate);
        assertCommandSuccess(findPhoneCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        GeneralPersonPredicate predicate =
                preparePredicate(new String[]{"Alice Benson", "", "", "", ""}, emptyDateTime);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        GeneralPersonPredicate predicate =
                preparePredicate(new String[]{"Kurz Elle Kunz", "", "", "", ""}, emptyDateTime);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        GeneralPersonPredicate predicate = new GeneralPersonPredicate(
                new NameContainsKeywordsPredicate(List.of("keyword")),
                new PhoneContainsPredicate(List.of("0")),
                new EmailContainsKeywordsPredicate(List.of("keyword")),
                new LastContactTimeContainsPredicate(lastContacted),
                new StatusContainsKeywordsPredicate(List.of("keyword")),
                new PersonTagContainsKeywordsPredicate(List.of("keyword"))
        );
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private GeneralPersonPredicate preparePredicate(String[] userInput, LocalDateTime lastContacted) {
        return new GeneralPersonPredicate(
                new NameContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new PhoneContainsPredicate(List.of(userInput[1].split("\\s+"))),
                new EmailContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new LastContactTimeContainsPredicate(lastContacted),
                new StatusContainsKeywordsPredicate(List.of(userInput[3].split("\\s+"))),
                new PersonTagContainsKeywordsPredicate(List.of(userInput[4].split("\\s+")))
        );
    }
}
