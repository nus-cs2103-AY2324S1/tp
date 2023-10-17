package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.NameContainsKeywordsPredicate;
import seedu.lovebook.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_AGE_AMY = "33";
    public static final String VALID_AGE_BOB = "22";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_HEIGHT_AMY = "30";
    public static final String VALID_HEIGHT_BOB = "10";
    public static final String VALID_INCOME_AMY = "3000";
    public static final String VALID_INCOME_BOB = "2000";
    public static final String VALID_HOROSCOPE_AMY = "TAURUS";
    public static final String VALID_HOROSCOPE_BOB = "LIBRA";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String HEIGHT_DESC_AMY = " " + PREFIX_HEIGHT + VALID_HEIGHT_AMY;
    public static final String HEIGHT_DESC_BOB = " " + PREFIX_HEIGHT + VALID_HEIGHT_BOB;
    public static final String INCOME_DESC_AMY = " " + PREFIX_INCOME + VALID_INCOME_AMY;
    public static final String INCOME_DESC_BOB = " " + PREFIX_INCOME + VALID_INCOME_BOB;
    public static final String HOROSCOPE_DESC_AMY = " " + PREFIX_HOROSCOPE + VALID_HOROSCOPE_AMY;
    public static final String HOROSCOPE_DESC_BOB = " " + PREFIX_HOROSCOPE + VALID_HOROSCOPE_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "911a"; // 'a' not allowed in ages
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_HEIGHT_DESC = " " + PREFIX_HEIGHT; // empty string not allowed for addresses
    public static final String INVALID_INCOME_DESC = " " + PREFIX_INCOME; // empty string not allowed for income
    public static final String INVALID_HOROSCOPE_DESC =
            " " + PREFIX_HOROSCOPE; // empty string not allowed for horoscope
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAge(VALID_AGE_AMY).withGender(VALID_GENDER_AMY).withHeight(VALID_HEIGHT_AMY)
                .withIncome(VALID_INCOME_AMY).withHoroscope(VALID_HOROSCOPE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withAge(VALID_AGE_BOB).withGender(VALID_GENDER_BOB).withHeight(VALID_HEIGHT_BOB)
                .withIncome(VALID_INCOME_AMY).withHoroscope(VALID_HOROSCOPE_BOB).build();
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
     * - the lovebook book, filtered date list and selected date in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        LoveBook expectedLoveBook = new LoveBook(actualModel.getLoveBook());
        List<Date> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLoveBook, actualModel.getLoveBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the date at the given {@code targetIndex} in the
     * {@code model}'s lovebook book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Date date = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = date.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
