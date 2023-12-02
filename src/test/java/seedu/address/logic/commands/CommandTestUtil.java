package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_GOOGLE = "Google";
    public static final String VALID_NAME_TIKTOK = "Tiktok";
    public static final String VALID_PHONE_GOOGLE = "11111111";
    public static final String VALID_PHONE_TIKTOK = "22222222";
    public static final String VALID_EMAIL_GOOGLE = "amy@example.com";
    public static final String VALID_EMAIL_TIKTOK = "bob@example.com";
    public static final String VALID_ROLE_GOOGLE = "Software Engineer";
    public static final String VALID_ROLE_TIKTOK = "Data Scientist";
    public static final String VALID_DEADLINE_GOOGLE = "10-10-2023";
    public static final String VALID_DEADLINE_TIKTOK = "11-11-2023";
    public static final String VALID_STATUS_GOOGLE = "PA";
    public static final String VALID_STATUS_TIKTOK = "PI";
    public static final String VALID_RECRUITER_NAME_GOOGLE = "John Doe";
    public static final String VALID_RECRUITER_NAME_TIKTOK = "Timmy Tan";
    public static final String VALID_PRIORITY_GOOGLE = "HIGH";
    public static final String VALID_PRIORITY_TIKTOK = "LOW";
    public static final String VALID_REMARK_GOOGLE = "Java/Python";
    public static final String VALID_REMARK_TIKTOK = "NO REMARKS";

    public static final String NAME_DESC_GOOGLE = " " + PREFIX_COMPANY_NAME + VALID_NAME_GOOGLE;
    public static final String NAME_DESC_TIKTOK = " " + PREFIX_COMPANY_NAME + VALID_NAME_TIKTOK;
    public static final String PHONE_DESC_GOOGLE = " " + PREFIX_PHONE + VALID_PHONE_GOOGLE;
    public static final String PHONE_DESC_TIKTOK = " " + PREFIX_PHONE + VALID_PHONE_TIKTOK;
    public static final String EMAIL_DESC_GOOGLE = " " + PREFIX_EMAIL + VALID_EMAIL_GOOGLE;
    public static final String EMAIL_DESC_TIKTOK = " " + PREFIX_EMAIL + VALID_EMAIL_TIKTOK;
    public static final String ROLE_DESC_GOOGLE = " " + PREFIX_ROLE + VALID_ROLE_GOOGLE;
    public static final String ROLE_DESC_TIKTOK = " " + PREFIX_ROLE + VALID_ROLE_TIKTOK;
    public static final String DEADLINE_DESC_GOOGLE = " " + PREFIX_DEADLINE + VALID_DEADLINE_GOOGLE;
    public static final String DEADLINE_DESC_TIKTOK = " " + PREFIX_DEADLINE + VALID_DEADLINE_TIKTOK;
    public static final String STATUS_DESC_GOOGLE = " " + PREFIX_STATUS + VALID_STATUS_GOOGLE;
    public static final String STATUS_DESC_TIKTOK = " " + PREFIX_STATUS + VALID_STATUS_TIKTOK;
    public static final String RECRUITER_NAME_DESC_GOOGLE = " " + PREFIX_RECRUITER_NAME + VALID_RECRUITER_NAME_GOOGLE;
    public static final String RECRUITER_NAME_DESC_TIKTOK = " " + PREFIX_RECRUITER_NAME + VALID_RECRUITER_NAME_TIKTOK;
    public static final String PRIORITY_DESC_GOOGLE = " " + PREFIX_PRIORITY + VALID_PRIORITY_GOOGLE;
    public static final String PRIORITY_DESC_TIKTOK = " " + PREFIX_PRIORITY + VALID_PRIORITY_TIKTOK;
    public static final String REMARK_DESC_GOOGLE = " " + PREFIX_REMARK + VALID_REMARK_GOOGLE;
    public static final String REMARK_DESC_TIKTOK = " " + PREFIX_REMARK + VALID_REMARK_TIKTOK;
    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME + "Google&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "Software*"; // '*' not allowed in roles
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "10-10-20223"; // yyyy format
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "L"; // invalid status
    public static final String INVALID_RECRUITER_NAME_DESC =
            " " + PREFIX_RECRUITER_NAME + "Google&"; // '&' not allowed in names

    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "HIGHH"; // invalid priority
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + " "; // invalid remark
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCompanyDescriptor DESC_GOOGLE;
    public static final EditCommand.EditCompanyDescriptor DESC_TIKTOK;

    static {
        DESC_GOOGLE = new EditCompanyDescriptorBuilder().withName(VALID_NAME_GOOGLE)
                .withPhone(VALID_PHONE_GOOGLE).withEmail(VALID_EMAIL_GOOGLE).withPriority(VALID_PRIORITY_GOOGLE)
                .build();
        DESC_TIKTOK = new EditCompanyDescriptorBuilder().withName(VALID_NAME_TIKTOK)
                .withPhone(VALID_PHONE_TIKTOK).withEmail(VALID_EMAIL_TIKTOK).withPriority(VALID_PRIORITY_TIKTOK)
                .build();
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
     * - the address book, filtered company list and selected company in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Company> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCompanyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCompanyList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the company at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCompanyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCompanyList().size());

        Company company = model.getFilteredCompanyList().get(targetIndex.getZeroBased());
        final String[] splitName = company.getName().fullName.split("\\s+");
        model.updateFilteredCompanyList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCompanyList().size());
    }

}
