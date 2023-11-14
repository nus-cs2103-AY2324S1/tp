package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_DUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contacts.EditContactCommand;
import seedu.address.logic.commands.events.EditEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    //-------------------------------Contacts-----------------------------
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_COMPANY_AMY = "Google";
    public static final String VALID_COMPANY_BOB = "Facebook";
    public static final String VALID_TELEGRAM_NAME_AMY = "@amybee";
    public static final String VALID_TELEGRAM_NAME_BOB = "@bobby";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String COMPANY_DESC_AMY = " " + PREFIX_COMPANY + VALID_COMPANY_AMY;
    public static final String COMPANY_DESC_BOB = " " + PREFIX_COMPANY + VALID_COMPANY_BOB;
    public static final String TELEGRAM_NAME_DESC_AMY = " " + PREFIX_TELEGRAM_NAME + VALID_TELEGRAM_NAME_AMY;
    public static final String TELEGRAM_NAME_DESC_BOB = " " + PREFIX_TELEGRAM_NAME + VALID_TELEGRAM_NAME_BOB;
    public static final String CLIENT_DESC_AMY = " " + PREFIX_CLIENT + VALID_NAME_AMY;
    public static final String CLIENT_DESC_BOB = " " + PREFIX_CLIENT + VALID_NAME_AMY;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "\"Lorem ipsum dolor sit amet, consectetur "
            + "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim "
            + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute "
            + "irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur "
            + "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est "
            + "laborum.\"\n" + "\n";
    // name must be less than 256 characters
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + "\"Lorem ipsum dolor sit amet, consectetur"
            + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim "
            + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute "
            + "irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur "
            + "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est "
            + "laborum.\"\n" + "\n"; // addresses must be less than 256 characters
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "\"Lorem ipsum dolor sit amet, consectetur"
            + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim "
            + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute "
            + "irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur "
            + "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est "
            + "laborum.\"\n" + "\n";
    // company must be less than 256 characters

    public static final String INVALID_TELEGRAM_NAME_DESC = " "
            + PREFIX_TELEGRAM_NAME + "!.d_"; // '.' not allowed in telegram name

    //-------------------------------Events-----------------------------
    public static final String VALID_EVENT_NAME_MEETING = "Meeting";
    public static final String VALID_EVENT_NAME_BIRTHDAY = "Birthday";
    public static final String VALID_TIME_START_MEETING = "01-01-2023 14:00";
    public static final String VALID_TIME_START_BIRTHDAY = "01-01-2023 15:00";
    public static final String VALID_TIME_END_MEETING = "01-01-2023 15:00";
    public static final String VALID_TIME_END_BIRTHDAY = "01-01-2023 16:00";
    public static final String VALID_LOCATION_MEETING = "Meeting Room";
    public static final String VALID_LOCATION_BIRTHDAY = "Conference Room";
    public static final String VALID_DESCRIPTION_MEETING = "Meeting for discussion";
    public static final String VALID_DESCRIPTION_BIRTHDAY = "Birthday celebration";
    public static final String EVENT_NAME_DESC_MEETING = " " + PREFIX_NAME + VALID_EVENT_NAME_MEETING;
    public static final String EVENT_NAME_DESC_BIRTHDAY = " " + PREFIX_NAME + VALID_EVENT_NAME_BIRTHDAY;
    public static final String TIME_START_DESC_MEETING = " " + PREFIX_TIME_START + VALID_TIME_START_MEETING;
    public static final String TIME_START_DESC_BIRTHDAY = " " + PREFIX_TIME_START + VALID_TIME_START_BIRTHDAY;
    public static final String TIME_END_DESC_MEETING = " " + PREFIX_TIME_END + VALID_TIME_END_MEETING;
    public static final String TIME_END_DESC_BIRTHDAY = " " + PREFIX_TIME_END + VALID_TIME_END_BIRTHDAY;
    public static final String LOCATION_DESC_MEETING = " " + PREFIX_LOCATION + VALID_LOCATION_MEETING;
    public static final String LOCATION_DESC_BIRTHDAY = " " + PREFIX_LOCATION + VALID_LOCATION_BIRTHDAY;
    public static final String DESCRIPTION_DESC_MEETING = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING;
    public static final String DESCRIPTION_DESC_BIRTHDAY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BIRTHDAY;
    public static final String CLIENT_DESC_MEETING = " " + PREFIX_CLIENT + VALID_NAME_BOB;
    public static final String CLIENT_DESC_BIRTHDAY = " " + PREFIX_CLIENT + VALID_NAME_AMY;
    public static final String INVALID_EVENT_NAME_DESC = " " + PREFIX_NAME + " Meeting&";
    // ' ' not allowed to be starting character in names
    public static final String INVALID_TIME_START_DESC = " " + PREFIX_TIME_START + "001-01/2023 25:00";
    public static final String INVALID_TIME_END_DESC = " " + PREFIX_TIME_END + "001-01/2023 12:00";
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION + " Meeting Room";
    // ' ' not allowed to be starting character in location
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + " Meeting for discussion";
    // ' ' not allowed to be starting character in description

    //-------------------------------Finance-----------------------------
    public static final String VALID_DESCRIPTION = "Test Description";
    public static final String VALID_AMOUNT = "900";
    public static final String VALID_TIME_DUE = "27-10-2023 01:57";
    public static final String DEFAULT_DESCRIPTION = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION;
    public static final String DEFAULT_AMOUNT = " " + PREFIX_AMOUNT + VALID_AMOUNT;
    public static final String DEFAULT_TIME_DUE = " " + PREFIX_TIME_DUE + VALID_TIME_DUE;

    public static final String INVALID_AMOUNT = " " + PREFIX_AMOUNT + "not money";
    public static final String INVALID_TIME_DUE = " " + PREFIX_TIME_DUE + "001-01/2023 25:00";
    public static final String INVALID_CLIENT = "i am definitely not a client";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditContactCommand.EditPersonDescriptor DESC_AMY;
    public static final EditContactCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withCompany(
                        VALID_COMPANY_AMY).withTelegramName(VALID_TELEGRAM_NAME_AMY)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withCompany(
                        VALID_COMPANY_BOB).withTelegramName(VALID_TELEGRAM_NAME_BOB)
                .build();
    }

    public static final EditEventCommand.EditEventDescriptor DESC_BIRTHDAY;
    public static final EditEventCommand.EditEventDescriptor DESC_MEETING;

    static {
        try {
            DESC_BIRTHDAY = new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_BIRTHDAY)
                    .withTimeStart(VALID_TIME_START_BIRTHDAY).withTimeEnd(VALID_TIME_END_BIRTHDAY)
                    .withLocation(VALID_LOCATION_BIRTHDAY).withDescription(VALID_DESCRIPTION_BIRTHDAY)
                    .build();

            DESC_MEETING = new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_MEETING)
                    .withTimeStart(VALID_TIME_START_MEETING).withTimeEnd(VALID_TIME_END_MEETING)
                    .withLocation(VALID_LOCATION_MEETING).withDescription(VALID_DESCRIPTION_MEETING)
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

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

}
