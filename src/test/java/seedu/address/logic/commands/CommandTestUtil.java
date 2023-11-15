package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEGIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYRATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.interval.Duration;
import seedu.address.model.interval.Interval;
import seedu.address.model.interval.IntervalBegin;
import seedu.address.model.interval.IntervalDay;
import seedu.address.model.interval.IntervalEnd;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_SUBJECT_AMY = "Maths";
    public static final String VALID_SUBJECT_BOB = "Physics";
    public static final String VALID_DAY_AMY = "Mon";
    public static final String VALID_DAY_BOB = "Tue";
    public static final String VALID_BEGIN_AMY = "1200";
    public static final String VALID_BEGIN_BOB = "1300";
    public static final String VALID_END_AMY = "1400";
    public static final String VALID_END_BOB = "1500";
    public static final String VALID_PAYRATE_AMY = "75.00";
    public static final String VALID_PAYRATE_BOB = "25.00";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String SUBJECT_DESC_AMY = " " + PREFIX_SUBJECT + VALID_SUBJECT_AMY;
    public static final String SUBJECT_DESC_BOB = " " + PREFIX_SUBJECT + VALID_SUBJECT_BOB;
    public static final String DAY_DESC_AMY = " " + PREFIX_DAY + VALID_DAY_AMY;
    public static final String DAY_DESC_BOB = " " + PREFIX_DAY + VALID_DAY_BOB;
    public static final String BEGIN_DESC_AMY = " " + PREFIX_BEGIN + VALID_BEGIN_AMY;
    public static final String BEGIN_DESC_BOB = " " + PREFIX_BEGIN + VALID_BEGIN_BOB;
    public static final String END_DESC_AMY = " " + PREFIX_END + VALID_END_AMY;
    public static final String END_DESC_BOB = " " + PREFIX_END + VALID_END_BOB;
    public static final String PAYRATE_DESC_AMY = " " + PREFIX_PAYRATE + VALID_PAYRATE_AMY;
    public static final String PAYRATE_DESC_BOB = " " + PREFIX_PAYRATE + VALID_PAYRATE_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_SUBJECT_DESC = " " + PREFIX_SUBJECT; // empty string not allowed for subjects
    public static final String INVALID_DAY_DESC = " " + PREFIX_DAY + "Mond"; // full day name not allowed
    public static final String INVALID_BEGIN_DESC = " " + PREFIX_BEGIN + "9999"; //not a valid time
    public static final String INVALID_END_DESC = " " + PREFIX_END + "8888"; // not a valid time
    public static final String INVALID_PAYRATE_DESC = " " + PREFIX_PAYRATE + "hundred";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSubject(VALID_SUBJECT_AMY).withDay(VALID_DAY_AMY)
                .withBegin(VALID_BEGIN_AMY).withEnd(VALID_END_AMY)
                .withPayRate(VALID_PAYRATE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withSubject(VALID_SUBJECT_BOB).withDay(VALID_DAY_BOB)
                .withBegin(VALID_BEGIN_BOB).withEnd(VALID_END_BOB)
                .withPayRate(VALID_PAYRATE_BOB).build();
    }

    public static final String VALID_INTERVAL_DAY = "Mon";
    public static final String VALID_INTERVAL_DURATION = "60";
    public static final String VALID_INTERVAL_BEGIN = "0800";
    public static final String VALID_INTERVAL_END = "2200";

    public static final String INVALID_INTERVAL_DAY = "Mond";
    public static final String INVALID_INTERVAL_DURATION = "-60";
    public static final String INVALID_INTERVAL_BEGIN = "8888";
    public static final String INVALID_INTERVAL_END = "9999";

    public static final Interval VALID_INTERVAL_ONE = new Interval(new IntervalDay("Mon"), new Duration("60"),
            new IntervalBegin("0800"), new IntervalEnd("2200"));

    public static final String INTERVAL_DAY_DESC_ONE = " " + PREFIX_DAY + VALID_INTERVAL_DAY;
    public static final String INTERVAL_DURATION_DESC_ONE = " " + PREFIX_DURATION + VALID_INTERVAL_DURATION;
    public static final String INTERVAL_BEGIN_DESC_ONE = " " + PREFIX_BEGIN + VALID_INTERVAL_BEGIN;
    public static final String INTERVAL_END_DESC_ONE = " " + PREFIX_END + VALID_INTERVAL_END;

    public static final String INVALID_INTERVAL_DAY_DESC_ONE = " " + PREFIX_DAY + INVALID_INTERVAL_DAY;
    public static final String INVALID_INTERVAL_DURATION_DESC_ONE = " " + PREFIX_DURATION + INVALID_INTERVAL_DURATION;
    public static final String INVALID_INTERVAL_BEGIN_DESC_ONE = " " + PREFIX_BEGIN + INVALID_INTERVAL_BEGIN;
    public static final String INVALID_INTERVAL_END_DESC_ONE = " " + PREFIX_END + INVALID_INTERVAL_END;

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Simulates an add command
     */
    public static void simulateAddCommand(Model model, Person person) {
        model.purgeAddressBook();
        model.addPerson(person);
        model.commitAddressBook();
    }

    /**
     * Simulates a delete command
     */
    public static void simulateDeleteCommand(Model model, Person person) {
        model.purgeAddressBook();
        model.deletePerson(person);
        model.commitAddressBook();
    }
}
