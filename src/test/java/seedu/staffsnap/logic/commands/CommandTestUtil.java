package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.staffsnap.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.interview.Rating;
import seedu.staffsnap.testutil.EditApplicantDescriptorBuilder;

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
    public static final String VALID_POSITION_AMY = "Backend Engineer";
    public static final String VALID_POSITION_BOB = "Software Engineer";
    public static final String VALID_TYPE_TECHNICAL = "technical";
    public static final String VALID_TYPE_BEHAVIORAL = "behavioral";
    public static final String VALID_RATING_TEN = "10.0";
    public static final Interview VALID_INTERVIEW_BEHAVIORAL = new Interview("behavioral", new Rating("8.5"));
    public static final Interview VALID_INTERVIEW_TECHNICAL = new Interview("technical", new Rating("8.0"));

    public static final Interview VALID_INTERVIEW_HR = new Interview("HR", new Rating("9.0"));

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String POSITION_DESC_AMY = " " + PREFIX_POSITION + VALID_POSITION_AMY;
    public static final String POSITION_DESC_BOB = " " + PREFIX_POSITION + VALID_POSITION_BOB;
    public static final String TYPE_DESC_TECHNICAL = " " + PREFIX_TYPE + VALID_TYPE_TECHNICAL;
    public static final String TYPE_DESC_BEHAVIORAL = " " + PREFIX_TYPE + VALID_TYPE_BEHAVIORAL;
    public static final String RATING_DESC_TEN = " " + PREFIX_RATING + VALID_RATING_TEN;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION; // empty string not allowed for positions
    public static final String INVALID_TYPE_DESC = " "
            + PREFIX_TYPE + ""; // empty string not allowed in interviews
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING
            + "-1"; // negative numbers not allowed for ratings
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicantDescriptor DESC_AMY;
    public static final EditCommand.EditApplicantDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withPosition(VALID_POSITION_AMY)
                .withInterviews(VALID_TYPE_TECHNICAL).build();
        DESC_BOB = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withPosition(VALID_POSITION_BOB)
                .withInterviews(VALID_TYPE_BEHAVIORAL, VALID_TYPE_TECHNICAL).build();
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
     * - the applicant book, filtered applicant list and selected applicant in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ApplicantBook expectedApplicantBook = new ApplicantBook(actualModel.getApplicantBook());
        List<Applicant> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicantList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedApplicantBook, actualModel.getApplicantBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicantList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s applicant book.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicantList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");
        model.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

}
