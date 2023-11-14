package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_MEDIA_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.group.tasks.Task;
import seedu.address.model.group.tasks.TaskModule;
import seedu.address.model.group.tasks.TaskStatus;
import seedu.address.model.group.tasks.Todo;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_MAJOR_AMY = "Computer Science";
    public static final String VALID_MAJOR_BOB = "Information Systems";
    public static final String VALID_YEAR_AMY = "2";
    public static final String VALID_YEAR_BOB = "3";
    public static final String VALID_EMAIL_AMY = "amy@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@u.nus.edu";
    public static final String VALID_DESCRIPTION_AMY = "Frontend Developer";
    public static final String VALID_DESCRIPTION_BOB = "Backend Developer";
    public static final String VALID_TUT_FIRST_AMY = "01";
    public static final String VALID_TUT_SECOND_AMY = "02";
    public static final String VALID_TUT_FIRST_BOB = "03";
    public static final String VALID_TUT_SECOND_BOB = "04";
    public static final String VALID_SM_LINKEDIN_AMY = "https://www.linkedin.com/in/amy";
    public static final String VALID_SM_GITHUB_AMY = "https://github.com/amy";
    public static final String VALID_SM_LINKEDIN_BOB = "https://www.linkedin.com/in/bob";
    public static final String VALID_SM_GITHUB_BOB = "https://github.com/bob";
    public static final String VALID_NATIONALITY_AMY = "local";
    public static final String VALID_NATIONALITY_BOB = "foreigner";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MAJOR_DESC_AMY = " " + PREFIX_MAJOR + VALID_MAJOR_AMY;
    public static final String MAJOR_DESC_BOB = " " + PREFIX_MAJOR + VALID_MAJOR_BOB;
    public static final String YEAR_DESC_AMY = " " + PREFIX_YEAR + VALID_YEAR_AMY;
    public static final String YEAR_DESC_BOB = " " + PREFIX_YEAR + VALID_YEAR_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String TUTORIAL_DESC_AMY = " " + PREFIX_TUTORIAL + VALID_TUT_FIRST_AMY + " "
            + PREFIX_TUTORIAL + VALID_TUT_SECOND_AMY;
    public static final String TUTORIAL_DESC_BOB = " " + PREFIX_TUTORIAL + VALID_TUT_FIRST_BOB + " "
            + PREFIX_TUTORIAL + VALID_TUT_SECOND_BOB;
    public static final String SM_DESC_AMY = " " + PREFIX_SOCIAL_MEDIA_LINK + VALID_SM_LINKEDIN_AMY + " "
            + PREFIX_SOCIAL_MEDIA_LINK + VALID_SM_GITHUB_AMY;
    public static final String SM_DESC_BOB = " " + PREFIX_SOCIAL_MEDIA_LINK + VALID_SM_LINKEDIN_BOB + " "
            + PREFIX_SOCIAL_MEDIA_LINK + VALID_SM_GITHUB_BOB;
    public static final String NATIONALITY_DESC_AMY = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_AMY;
    public static final String NATIONALITY_DESC_BOB = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_MAJOR_DESC = " " + PREFIX_MAJOR + "Computer Games"; // not in list of majors
    public static final String INVALID_YEAR_DESC = " " + PREFIX_YEAR + "-1"; // negative integer not allowed for year
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_DESCRIPTION_DESC = " "
            + PREFIX_DESCRIPTION; // empty string not allowed for description
    public static final String INVALID_TUTORIAL_DESC = " " + PREFIX_TUTORIAL + "1"; // not a 2 digit number
    public static final String INVALID_SM_DESC = " " + PREFIX_SOCIAL_MEDIA_LINK
            + "example.com"; // does not start with https://
    public static final String INVALID_NATIONALITY_DESC = " " + PREFIX_NATIONALITY + "Singaporean";
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "MALE";


    public static final String VALID_NUMBER_GROUP1 = "1";
    public static final String VALID_NUMBER_GROUP2 = "2";
    public static final String VALID_TUTORIAL_GROUP1 = "01";
    public static final String VALID_TUTORIAL_GROUP2 = "02";
    public static final Person VALID_MEMBER1_GROUP1 = ALICE;
    public static final Person VALID_MEMBER2_GROUP1 = BENSON;
    public static final Person VALID_MEMBER3_GROUP1 = CARL;
    public static final Person VALID_MEMBER1_GROUP2 = DANIEL;
    public static final Person VALID_MEMBER2_GROUP2 = ELLE;
    public static final Person VALID_MEMBER3_GROUP2 = FIONA;

    public static final Task VALID_TASK1 = new Todo("Upload video of OP1.",
            TaskStatus.NOT_DONE, TaskModule.CS2101);
    public static final Task VALID_TASK2 = new Todo("Complete mid semester review form.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T);
    public static final Task VALID_TASK3 = new Todo("Attend tutorials",
            TaskStatus.NOT_DONE, TaskModule.CS2103T);


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withMajor(VALID_MAJOR_AMY)
                .withYear(VALID_YEAR_AMY).withEmail(VALID_EMAIL_AMY).withDescription(VALID_DESCRIPTION_AMY)
                .withTutorials(VALID_TUT_FIRST_AMY, VALID_TUT_SECOND_AMY)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_AMY, VALID_SM_GITHUB_AMY)
                .withNationality(VALID_NATIONALITY_BOB).withGender(VALID_GENDER_BOB).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withMajor(VALID_MAJOR_BOB)
                .withYear(VALID_YEAR_BOB).withEmail(VALID_EMAIL_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withTutorials(VALID_TUT_FIRST_BOB, VALID_TUT_SECOND_BOB)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB)
                .withNationality(VALID_NATIONALITY_BOB).withGender(VALID_GENDER_BOB).build();
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
            Model expectedModel, boolean groupCommand) {
        CommandResult expectedCommandResult;
        if (groupCommand) {
            expectedCommandResult = new CommandResult(expectedMessage, false, false, true, false);
        } else {
            expectedCommandResult = new CommandResult(expectedMessage);
        }
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
     * Updates {@code model}'s filtered list to show only the group at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showGroupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGroupList().size());

        Group group = model.getFilteredGroupList().get(targetIndex.getZeroBased());
        int groupNumber = group.getNumber();
        model.updateFilteredGroupList(new GroupContainsKeywordsPredicate(List.of(String.valueOf(groupNumber))));

        assertEquals(1, model.getFilteredGroupList().size());
    }

}
