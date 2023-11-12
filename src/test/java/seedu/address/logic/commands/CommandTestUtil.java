package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPREMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

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
    public static final String VALID_GROUP_AMY = "CS2103T, CS2100";
    public static final String VALID_GROUP_BOB = "ST2334";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String GROUP_DESC_AMY = " " + PREFIX_GROUPTAG + VALID_GROUP_AMY;
    public static final String GROUP_DESC_BOB = " " + PREFIX_GROUPTAG + VALID_GROUP_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB + "&"; // '&' not in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GROUP_DESC_BLANK = " " + PREFIX_GROUPTAG; // empty string not allowed for group
    public static final String INVALID_GROUP_DESC = " " + PREFIX_GROUPTAG
        + "CS2103T&"; // '&' not allowed in group names
    public static final String INVALID_GROUP_DESC_BOB = " " + PREFIX_GROUPTAG
            + VALID_GROUP_BOB + "&"; // '&' not allowed in group names

    public static final String VALID_TIME_MON = "mon 1300 - mon 1400";
    public static final String VALID_TIME_MON_2 = "mon 1330 - mon 1430";
    public static final String VALID_TIME_TUE = "tue 1300 - tue 1400";
    public static final String VALID_TIME_WED = "wed 1300 - wed 1400";
    public static final String VALID_TIME_MON_CAPS = "MON 1300 - mOn 1400";
    public static final String INVALID_TIME_MON = "mond 1300 - monday 1400";
    public static final String INVALID_TIME_MON_2 = "mon 11pm - mon 2500";
    public static final String INVALID_TIME_START_AFTER_END = "mon 1100 - mon 1000";

    public static final String VALID_TIME_DESC_MON = " " + PREFIX_FREETIME + VALID_TIME_MON;
    public static final String VALID_TIME_DESC_TUE = " " + PREFIX_FREETIME + VALID_TIME_TUE;
    public static final String VALID_TIME_DESC_WED = " " + PREFIX_FREETIME + VALID_TIME_WED;
    public static final String VALID_TIME_DESC_MON_2 = " " + PREFIX_FREETIME + VALID_TIME_MON_CAPS;

    public static final String INVALID_TIME_DESC_MON = " " + PREFIX_FREETIME + INVALID_TIME_MON;
    public static final String INVALID_TIME_DESC_TUE = " " + PREFIX_FREETIME + INVALID_TIME_MON_2;
    public static final String INVALID_TIME_DESC_START_AFTER_END = " " + PREFIX_FREETIME + INVALID_TIME_START_AFTER_END;

    public static final String VALID_GROUP_CS = "CS";
    public static final String VALID_GROUP_CS2103T = "CS2103T";
    public static final String VALID_GROUP_ST2334 = "ST2334";

    public static final String NAME_DESC_CS = " " + PREFIX_GROUPTAG + VALID_GROUP_CS;
    public static final String NAME_DESC_CS2103T = " " + PREFIX_GROUPTAG + VALID_GROUP_CS2103T;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final String VALID_GROUP_REMARK = "quiz tomorrow";
    public static final String GROUP_REMARK_DESC = " " + PREFIX_GROUPREMARK + VALID_GROUP_REMARK;
    public static final String VALID_GROUP_REMARK_SPECIAL = "sdd&IFUDH%f*89fd*(F!899#$#%\"'`";
    public static final String GROUP_REMARK_SPECIAL_DESC = " " + PREFIX_GROUPREMARK + VALID_GROUP_REMARK_SPECIAL;
    public static final String VALID_GROUP_REMARK_UNICODE = "我要吃面包";
    public static final String GROUP_REMARK_UNICODE_DESC = " " + PREFIX_GROUPREMARK + VALID_GROUP_REMARK_UNICODE;
    public static final String VALID_GROUP_REMARK_OTHERS = "gU4&😊!";
    public static final String GROUP_REMARK_OTHERS_DESC = " " + PREFIX_GROUPREMARK + VALID_GROUP_REMARK_OTHERS;



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
        } catch (ParseException pe) {
            throw new AssertionError("Execution of command should not fail.", pe);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, java.lang.String expectedMessage,
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

}
