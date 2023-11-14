package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.DisplayableCommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.logic.commands.editcommands.editdescriptors.EditCompanyDescriptor;
import seedu.address.logic.commands.editcommands.editdescriptors.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    // test samples for Person entity
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses

    // test samples for Company entity
    public static final String VALID_COMPANY_NAME_APPLE = "Apple Inc.";
    public static final String VALID_COMPANY_NAME_GOOGLE = "Google LLC";
    public static final String VALID_COMPANY_NAME_ORACLE = "Oracle";
    public static final String VALID_COMPANY_PHONE_APPLE = "12345678";
    public static final String VALID_COMPANY_PHONE_GOOGLE = "34567890";
    public static final String VALID_COMPANY_PHONE_ORACLE = "67890123";
    public static final String VALID_COMPANY_EMAIL_APPLE = "apple@example.com";
    public static final String VALID_COMPANY_EMAIL_GOOGLE = "google@example.com";
    public static final String VALID_COMPANY_EMAIL_ORACLE = "oracle@example.com";
    public static final String VALID_COMPANY_DESCRIPTION_APPLE = "A technology company.";
    public static final String VALID_COMPANY_DESCRIPTION_GOOGLE = "An internet-related services and products company.";
    public static final String VALID_COMPANY_DESCRIPTION_ORACLE = "A cloud service company";
    public static final String VALID_TAG_TECH = "tech";
    public static final String VALID_TAG_INNOVATIVE = "innovative";
    public static final String VALID_TAG_SEARCH = "search";
    public static final String COMPANY_NAME_DESC_APPLE = " " + PREFIX_NAME + VALID_COMPANY_NAME_APPLE;
    public static final String COMPANY_NAME_DESC_GOOGLE = " " + PREFIX_NAME + VALID_COMPANY_NAME_GOOGLE;
    public static final String COMPANY_NAME_DESC_ORACLE = " " + PREFIX_NAME + VALID_COMPANY_NAME_ORACLE;
    public static final String COMPANY_PHONE_DESC_APPLE = " " + PREFIX_PHONE + VALID_COMPANY_PHONE_APPLE;
    public static final String COMPANY_PHONE_DESC_GOOGLE = " " + PREFIX_PHONE + VALID_COMPANY_PHONE_GOOGLE;
    public static final String COMPANY_PHONE_DESC_ORACLE = " " + PREFIX_PHONE + VALID_COMPANY_PHONE_ORACLE;
    public static final String COMPANY_EMAIL_DESC_APPLE = " " + PREFIX_EMAIL + VALID_COMPANY_EMAIL_APPLE;
    public static final String COMPANY_EMAIL_DESC_GOOGLE = " " + PREFIX_EMAIL + VALID_COMPANY_EMAIL_GOOGLE;
    public static final String COMPANY_EMAIL_DESC_ORACLE = " " + PREFIX_EMAIL + VALID_COMPANY_EMAIL_ORACLE;
    public static final String COMPANY_DESCRIPTION_DESC_APPLE = " " + PREFIX_DESCRIPTION
            + VALID_COMPANY_DESCRIPTION_APPLE;
    public static final String COMPANY_DESCRIPTION_DESC_GOOGLE = " " + PREFIX_DESCRIPTION
            + VALID_COMPANY_DESCRIPTION_GOOGLE;
    public static final String COMPANY_DESCRIPTION_DESC_ORACLE = " " + PREFIX_DESCRIPTION
            + VALID_COMPANY_DESCRIPTION_ORACLE;
    public static final String TAG_DESC_TECH = " " + PREFIX_TAG + VALID_TAG_TECH;
    public static final String TAG_DESC_INNOVATIVE = " " + PREFIX_TAG + VALID_TAG_INNOVATIVE;
    public static final String TAG_DESC_SEARCH = " " + PREFIX_TAG + VALID_TAG_SEARCH;
    public static final String INVALID_COMPANY_NAME_DESC = " " + PREFIX_NAME; // Company name cannot be blank
    public static final String INVALID_COMPANY_PHONE_DESC = " " + PREFIX_PHONE + "911b"; // 'b' not allowed in phones
    public static final String INVALID_COMPANY_EMAIL_DESC = " " + PREFIX_EMAIL + "apple!yahoo"; // missing '@' symbol
    // Company description cannot be blank
    public static final String INVALID_COMPANY_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION;

    // test samples for Person and Company entities
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // test samples for Internship entity
    public static final String VALID_INTERNSHIP_NAME_SOFTWARE_ENGINEER = "Software Engineering Intern 2024";
    public static final String VALID_INTERNSHIP_NAME_DATA_ANALYST = "Data Analyst Intern 2024";
    public static final String VALID_INTERNSHIP_NAME_MARKETING_INTERN = "Marketing Intern 2024";
    public static final String VALID_INTERNSHIP_DESCRIPTION_SOFTWARE_ENGINEER_WITH_DATE_TIME =
            "Produce high quality software following good architecture that are scalable";
    public static final String VALID_INTERNSHIP_DESCRIPTION_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME =
            "Develop new features/functionalities on existing software products";
    public static final String VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST = "Learn data analysis tools and techniques";
    public static final String VALID_INTERNSHIP_DESCRIPTION_MARKETING_INTERN = "Conduct market research and analysis";
    public static final String VALID_INTERNSHIP_SOFTWARE_ENGINEER_INTERVIEW_DATE_TIME = "20-12-2023 08:00";
    public static final String VALID_INTERNSHIP_DATA_ANALYST_INTERVIEW_DATE_TIME = "15-01-2024 10:30";
    public static final String INTERNSHIP_INDEX_DESC = "1";
    public static final String INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITH_DATE_TIME = " " + PREFIX_NAME
            + VALID_INTERNSHIP_NAME_SOFTWARE_ENGINEER;
    public static final String INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME = " " + PREFIX_NAME
            + VALID_INTERNSHIP_NAME_SOFTWARE_ENGINEER;
    public static final String INTERNSHIP_NAME_DESC_DATA_ANALYST = " " + PREFIX_NAME
            + VALID_INTERNSHIP_NAME_DATA_ANALYST;
    public static final String INTERNSHIP_NAME_DESC_MARKETING_INTERN = " " + PREFIX_NAME
            + VALID_INTERNSHIP_NAME_MARKETING_INTERN;
    public static final String INTERNSHIP_DESCRIPTION_DESC_SOFTWARE_ENGINEER_WITH_DATE_TIME = " " + PREFIX_DESCRIPTION
            + VALID_INTERNSHIP_DESCRIPTION_SOFTWARE_ENGINEER_WITH_DATE_TIME;
    public static final String INTERNSHIP_DESCRIPTION_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME = " "
            + PREFIX_DESCRIPTION + VALID_INTERNSHIP_DESCRIPTION_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME;
    public static final String INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST = " " + PREFIX_DESCRIPTION
            + VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST;
    public static final String INTERNSHIP_DESCRIPTION_DESC_MARKETING_INTERN = " " + PREFIX_DESCRIPTION
            + VALID_INTERNSHIP_DESCRIPTION_MARKETING_INTERN;
    public static final String INTERNSHIP_INTERVIEW_DATE_TIME_DESC_SOFTWARE_ENGINEER = " " + PREFIX_SCHEDULED
            + VALID_INTERNSHIP_SOFTWARE_ENGINEER_INTERVIEW_DATE_TIME;
    public static final String INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST = " " + PREFIX_SCHEDULED
            + VALID_INTERNSHIP_DATA_ANALYST_INTERVIEW_DATE_TIME;
    public static final String INVALID_INTERNSHIP_INDEX_DESC = "99999999999999";
    public static final String INVALID_INTERNSHIP_NAME_DESC = " " + PREFIX_NAME; // Internship name cannot be blank
    // Internship description cannot be blank
    public static final String INVALID_INTERNSHIP_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION;
    // Internship interview date time must follow the format dd-MM-yyyy HH:mm
    public static final String INVALID_INTERNSHIP_INTERVIEW_DATE_TIME_DESC = " " + PREFIX_SCHEDULED + "09-10-11 15:00";

    public static final EditPersonDescriptor DESC_AMY;
    public static final EditPersonDescriptor DESC_BOB;
    public static final EditCompanyDescriptor DESC_APPLE;
    public static final EditCompanyDescriptor DESC_GOOGLE;
    public static final EditCompanyDescriptor DESC_ORACLE;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_APPLE = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_APPLE)
                .withCompanyPhone(VALID_COMPANY_PHONE_APPLE)
                .withCompanyEmail(VALID_COMPANY_EMAIL_APPLE)
                .withDescription(VALID_COMPANY_DESCRIPTION_APPLE)
                .withTags(VALID_TAG_TECH, VALID_TAG_INNOVATIVE).build();
        DESC_GOOGLE = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
                .withCompanyPhone(VALID_COMPANY_PHONE_GOOGLE)
                .withCompanyEmail(VALID_COMPANY_EMAIL_GOOGLE)
                .withDescription(VALID_COMPANY_DESCRIPTION_GOOGLE)
                .withTags(VALID_TAG_TECH, VALID_TAG_SEARCH).build();
        DESC_ORACLE = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_ORACLE)
                .withCompanyPhone(VALID_COMPANY_PHONE_ORACLE)
                .withCompanyEmail(VALID_COMPANY_EMAIL_ORACLE)
                .withDescription(VALID_COMPANY_DESCRIPTION_ORACLE)
                .withTags(VALID_TAG_TECH).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertRegularCommandSuccess(Command command, Model actualModel,
                                                   RegularCommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertRegularCommandSuccess(Command, Model, RegularCommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertRegularCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                   Model expectedModel) {
        RegularCommandResult expectedCommandResult = new RegularCommandResult(expectedMessage);
        assertRegularCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }


    /**
     * Convenience wrapper to {@link #assertDisplayableCommandSuccess(Command, Model, DisplayableCommandResult, Model)}
     * that takes a string {@code expectedMessage} and a Person {@code expectedPerson}.
     */
    public static void assertDisplayableCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                       Model expectedModel, Person expectedPerson) {
        DisplayableCommandResult expectedCommandResult = new DisplayableCommandResult(expectedMessage, expectedPerson);
        assertDisplayableCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertDisplayableCommandSuccess(Command, Model, DisplayableCommandResult, Model)}
     * that takes a string {@code expectedMessage} and a Company {@code expectedCompany}.
     */
    public static void assertDisplayableCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                       Model expectedModel, Company expectedCompany) {
        DisplayableCommandResult expectedCommandResult = new DisplayableCommandResult(expectedMessage, expectedCompany);
        assertDisplayableCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertDisplayableCommandSuccess(Command command, Model actualModel,
                                                       DisplayableCommandResult expectedCommandResult,
                                                       Model expectedModel) {
        try {
            DisplayableCommandResult result = (DisplayableCommandResult) command.execute(actualModel);
            boolean is = result.equals(expectedCommandResult);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertPersonCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered company list and selected company in {@code actualModel} remain unchanged
     */
    public static void assertCompanyCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Company> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCompanyList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCompanyList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered company list
     * - and selected company(including its internship list) in {@code actualModel} remain unchanged
     */
    public static void assertInternshipCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Company> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCompanyList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCompanyList());
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
     * Updates {@code model}'s filtered list to show only the company at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCompanyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCompanyList().size());

        Company company = model.getFilteredCompanyList().get(targetIndex.getZeroBased());
        final String[] splitName = company.getCompanyName().fullName.split("\\s+");
        model.updateFilteredCompanyList(
                new CompanyNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCompanyList().size());
    }
}
