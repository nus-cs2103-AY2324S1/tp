package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORGANISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditClientCommand;
import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.logic.commands.edit.EditProjectCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameClientContainsKeywordsPredicate;
import seedu.address.model.developer.Developer;
import seedu.address.model.developer.NameDeveloperContainsKeywordsPredicate;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.EditDeveloperDescriptorBuilder;
import seedu.address.testutil.EditProjectDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CALEB = "Caleb Kee";
    public static final String VALID_NAME_DAN = "Dan yo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CALEB = "33333333";
    public static final String VALID_PHONE_DAN = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CALEB = "caleb@example.com";
    public static final String VALID_EMAIL_DAN = "dan@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_CALEB = "Block 312, Caleb Street 4";
    public static final String VALID_ADDRESS_DAN = "Block 132, Dan Street 6";
    public static final String VALID_ROLE_AMY = "Developer";
    public static final String VALID_ROLE_BOB = "Tester";
    public static final String VALID_ROLE_CALEB = "HR";
    public static final String VALID_ROLE_DAN = "Developer";
    public static final String VALID_SALARY_AMY = "50000";
    public static final String VALID_SALARY_BOB = "40000";
    public static final String VALID_DATEJOINED_AMY = "01-01-2020";
    public static final String VALID_DATEJOINED_BOB = "01-02-2020";
    public static final String VALID_GITHUBID_AMY = "amywalker";
    public static final String VALID_GITHUBID_BOB = "bobwalker";
    public static final String VALID_RATING_AMY = "5";
    public static final String VALID_RATING_BOB = "4";
    public static final String VALID_PROJECT_1_AMY = "ProjectA";
    public static final String VALID_PROJECT_2_AMY = "ProjectB";
    public static final String VALID_PROJECT_1_BOB = "ProjectC";
    public static final String VALID_PROJECT_2_BOB = "ProjectD";
    public static final String VALID_PROJECT_3_BOB = "Project C";
    public static final String VALID_PROJECT_1_CALEB = "ProjectC";
    public static final String VALID_PROJECT_2_CALEB = "ProjectD";
    public static final String VALID_PROJECT_1_DAN = "ProjectA";
    public static final String VALID_PROJECT_2_DAN = "ProjectB";

    public static final String VALID_ORGANISATION_AMY = "Google";
    public static final String VALID_ORGANISATION_BOB = "Facebook";
    public static final String VALID_ORGANISATION_CALEB = "Github";
    public static final String VALID_ORGANISATION_DAN = "Apple";
    public static final String VALID_DOCUMENT_AMY = "https://www.google.com";
    public static final String VALID_DOCUMENT_BOB = "https://www.facebook.com";
    public static final String VALID_DOCUMENT_CALEB = "https://www.github.com";
    public static final String VALID_DOCUMENT_DAN = "https://www.apple.com";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CALEB = " " + PREFIX_NAME + VALID_NAME_CALEB;
    public static final String NAME_DESC_DAN = " " + PREFIX_NAME + VALID_NAME_DAN;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_CALEB = " " + PREFIX_PHONE + VALID_PHONE_CALEB;
    public static final String PHONE_DESC_DAN = " " + PREFIX_PHONE + VALID_PHONE_DAN;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CALEB = " " + PREFIX_EMAIL + VALID_EMAIL_CALEB;
    public static final String EMAIL_DESC_DAN = " " + PREFIX_EMAIL + VALID_EMAIL_DAN;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_CALEB = " " + PREFIX_ADDRESS + VALID_ADDRESS_CALEB;
    public static final String ADDRESS_DESC_DAN = " " + PREFIX_ADDRESS + VALID_ADDRESS_DAN;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String ROLE_DESC_CALEB = " " + PREFIX_ROLE + VALID_ROLE_CALEB;
    public static final String ROLE_DESC_DAN = " " + PREFIX_ROLE + VALID_ROLE_DAN;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String PROJECT_DESC_AMY = " " + PREFIX_PROJECT + VALID_PROJECT_2_AMY
            + ", " + VALID_PROJECT_1_AMY;
    public static final String PROJECT1_DESC_AMY = " " + PREFIX_PROJECT + VALID_PROJECT_1_AMY;
    public static final String PROJECT2_DESC_AMY = " " + PREFIX_PROJECT + VALID_PROJECT_2_AMY;
    public static final String PROJECT_DESC_BOB = " " + PREFIX_PROJECT + VALID_PROJECT_1_BOB + VALID_PROJECT_2_BOB;
    public static final String PROJECT_DESC_CALEB = " " + PREFIX_PROJECT + VALID_PROJECT_1_CALEB
            + VALID_PROJECT_2_CALEB;
    public static final String PROJECT_DESC_DAN = " " + PREFIX_PROJECT + VALID_PROJECT_1_DAN + VALID_PROJECT_2_DAN;
    public static final String DATEJOINED_DESC_AMY = " " + PREFIX_DATEJOINED + VALID_DATEJOINED_AMY;
    public static final String DATEJOINED_DESC_BOB = " " + PREFIX_DATEJOINED + VALID_DATEJOINED_BOB;
    public static final String GITHUBID_DEC_AMY = " " + PREFIX_GITHUBID + VALID_GITHUBID_AMY;
    public static final String GITHUBID_DEC_BOB = " " + PREFIX_GITHUBID + VALID_GITHUBID_BOB;
    public static final String RATING_DEC_AMY = " " + PREFIX_RATING + VALID_RATING_AMY;
    public static final String RATING_DEC_BOB = " " + PREFIX_RATING + VALID_RATING_BOB;
    public static final String ORGANISATION_DESC_AMY = " " + PREFIX_ORGANISATION + VALID_ORGANISATION_AMY;
    public static final String ORGANISATION_DESC_BOB = " " + PREFIX_ORGANISATION + VALID_ORGANISATION_BOB;
    public static final String ORGANISATION_DESC_CALEB = " " + PREFIX_ORGANISATION + VALID_ORGANISATION_CALEB;
    public static final String ORGANISATION_DESC_DAN = " " + PREFIX_ORGANISATION + VALID_ORGANISATION_DAN;
    public static final String DOCUMENT_DESC_AMY = " " + PREFIX_DOCUMENT + VALID_DOCUMENT_AMY;
    public static final String DOCUMENT_DESC_BOB = " " + PREFIX_DOCUMENT + VALID_DOCUMENT_BOB;
    public static final String DOCUMENT_DESC_CALEB = " " + PREFIX_DOCUMENT + VALID_DOCUMENT_CALEB;
    public static final String DOCUMENT_DESC_DAN = " " + PREFIX_DOCUMENT + VALID_DOCUMENT_DAN;

    public static final String VALID_PROJECT_DESCRIPTION_APPLEAPP = "Developing the AppleApp";
    public static final String VALID_PROJECT_DESCRIPTION_GOOGLEAPP = "Working on the GoogleApp";
    public static final String VALID_PROJECT_DESCRIPTION_ANDROIDAPP = "AndroidApp development";
    public static final String VALID_FULL_PROJECT_DEADLINE_1 = "31-12-2019,Develop front end interface,HIGH,0";
    public static final String VALID_FULL_PROJECT_DEADLINE_2 = "01-02-2020,Develop back end,HIGH,0";
    public static final String INVALID_FULL_PROJECT_DEADLINE_1 = "30-02-2020,Develop back end,HIGH,0";
    public static final String VALID_PROJECT_DEADLINE_APPLEAPP = "2023-12-31";
    public static final String VALID_PROJECT_DEADLINE_GOOGLEAPP = "2023-11-30";
    public static final String VALID_PROJECT_DEADLINE_ANDROIDAPP = "2023-10-31";

    public static final String VALID_PROJECT_PRIORITY_APPLEAPP = "High";
    public static final String VALID_PROJECT_PRIORITY_GOOGLEAPP = "Medium";
    public static final String VALID_PROJECT_PRIORITY_ANDROIDAPP = "Low";

    public static final String VALID_PROJECT_NAME_APPLEAPP = "AppleApp";
    public static final String VALID_PROJECT_NAME_GOOGLEAPP = "GoogleApp";
    public static final String VALID_PROJECT_NAME_ANDROIDAPP = "AndroidApp";

    public static final String VALID_PROJECT_TAG_UI = "UI";
    public static final String VALID_PROJECT_TAG_BACKEND = "Backend";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE; //
    public static final String INVALID_SALARY_DESC = "abc" + PREFIX_SALARY; //
    public static final String INVALID_DATEJOINED_DESC = "2003-23" + PREFIX_DATEJOINED; //
    public static final String INVALID_GITHUBID_DESC = " " + PREFIX_GITHUBID; //
    public static final String INVALID_RATING_DESC = "abc" + PREFIX_RATING; //
    public static final String INVALID_PROJECT_DESC = " " + PREFIX_PROJECT; //



    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditDeveloperCommand.EditDeveloperDescriptor DESC_AMY;
    public static final EditDeveloperCommand.EditDeveloperDescriptor DESC_BOB;
    public static final EditClientCommand.EditClientDescriptor DESC_CALEB;
    public static final EditClientCommand.EditClientDescriptor DESC_DAN;
    public static final EditProjectCommand.EditProjectDescriptor DESC_PROJECT1;
    public static final EditProjectCommand.EditProjectDescriptor DESC_PROJECT2;


    static {
        DESC_AMY = new EditDeveloperDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRole(VALID_ROLE_AMY).withSalary(VALID_SALARY_AMY).withDateJoined(VALID_DATEJOINED_AMY)
                .withGithubId(VALID_GITHUBID_AMY).withRating(VALID_RATING_AMY).build();
        DESC_BOB = new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRole(VALID_ROLE_BOB).withSalary(VALID_SALARY_BOB).withDateJoined(VALID_DATEJOINED_BOB)
                .withGithubId(VALID_GITHUBID_BOB).withRating(VALID_RATING_BOB).build();

        DESC_CALEB = new EditClientDescriptorBuilder().withName(VALID_NAME_CALEB)
                .withPhone(VALID_PHONE_CALEB).withAddress(VALID_ADDRESS_CALEB).withEmail(VALID_EMAIL_CALEB)
                .withProjects(VALID_PROJECT_1_CALEB).withDocument(VALID_DOCUMENT_CALEB).build();
        DESC_DAN = new EditClientDescriptorBuilder().withName(VALID_NAME_DAN).withPhone(VALID_PHONE_DAN)
                .withAddress(VALID_ADDRESS_DAN).withEmail(VALID_EMAIL_DAN).withProjects(VALID_PROJECT_1_DAN)
                .withDocument(VALID_DOCUMENT_DAN).build();

        DESC_PROJECT1 = new EditProjectDescriptorBuilder().withDescription(VALID_PROJECT_DESCRIPTION_ANDROIDAPP)
                .withDeadlines(VALID_FULL_PROJECT_DEADLINE_1).build();
        DESC_PROJECT2 = new EditProjectDescriptorBuilder().withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP)
                .withDeadlines(VALID_FULL_PROJECT_DEADLINE_2).build();
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false,
                false, TabIndex.Developer);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }
    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertProjectCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false,
                false, TabIndex.Project);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertClientCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false,
                false, TabIndex.Client);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertDeveloperCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                  Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false,
                false, TabIndex.Developer);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered developer list and selected developer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Developer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDeveloperList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredDeveloperList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the developer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showDeveloperAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDeveloperList().size());

        Developer developer = model.getFilteredDeveloperList().get(targetIndex.getZeroBased());
        final String[] splitName = developer.getName().fullName.split("\\s+");
        model.updateFilteredDeveloperList(new NameDeveloperContainsKeywordsPredicate(Collections
                .singletonList(splitName[0])));

        assertEquals(1, model.getFilteredDeveloperList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameClientContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());

        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = project.getName().split("\\s+");
        model.updateFilteredProjectList(new ProjectNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(4, model.getFilteredProjectList().size());
    }

}
