package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.EditBandCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.musician.EditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditBandDescriptorBuilder;
import seedu.address.testutil.EditMusicianDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_ACE = "Ace Jazz";
    public static final String VALID_NAME_BOOM = "Boom Rock";
    public static final String VALID_NAME_ROCKSTARS = "The Rockstars";
    public static final String VALID_NAME_POPSTARS = "The Popstars";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_GENRE_JAZZ = "jazz";
    public static final String VALID_GENRE_POP = "pop";
    public static final String VALID_GENRE_ROCK = "rock";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_ACE = " " + PREFIX_NAME + VALID_NAME_ACE;
    public static final String NAME_DESC_BOOM = " " + PREFIX_NAME + VALID_NAME_BOOM;
    public static final String NAME_DESC_POPSTARS = " " + PREFIX_NAME + VALID_NAME_POPSTARS;
    public static final String NAME_DESC_ROCKSTARS = " " + PREFIX_NAME + VALID_NAME_ROCKSTARS;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String GENRE_DESC_JAZZ = " " + PREFIX_GENRE + VALID_GENRE_JAZZ;
    public static final String GENRE_DESC_POP = " " + PREFIX_GENRE + VALID_GENRE_POP;
    public static final String GENRE_DESC_ROCK = " " + PREFIX_GENRE + VALID_GENRE_ROCK;
    public static final String BAND_INDEX_DESC_FIRST = " " + PREFIX_BINDEX + "1";
    public static final String BAND_INDEX_DESC_SECOND = " " + PREFIX_BINDEX + "2";
    public static final String MUSICIAN_INDEX_DESC_FIRST = " " + PREFIX_MINDEX + "1";
    public static final String MUSICIAN_INDEX_DESC_SECOND = " " + PREFIX_MINDEX + "2";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_GENRE_DESC = " " + PREFIX_GENRE + "r0ck"; // not among allowed genres
    // zero index not allowed
    public static final String INVALID_BAND_INDEX_DESC_ZERO = " " + PREFIX_BINDEX + "0";
    // negative index not allowed
    public static final String INVALID_MUSICIAN_INDEX_DESC_NEGATIVE = " " + PREFIX_MINDEX + "-1";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditMusicianDescriptor DESC_AMY;
    public static final EditCommand.EditMusicianDescriptor DESC_BOB;
    public static final EditBandCommand.EditBandDescriptor DESC_ROCKSTARS;
    public static final EditBandCommand.EditBandDescriptor DESC_POPSTARS;

    static {
        DESC_AMY = new EditMusicianDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditMusicianDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();
        DESC_POPSTARS = new EditBandDescriptorBuilder().withName(VALID_NAME_POPSTARS).build();
        DESC_ROCKSTARS = new EditBandDescriptorBuilder().withName(VALID_NAME_ROCKSTARS).build();
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
     * - the address book, filtered musician list and selected musician in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Musician> expectedFilteredList = new ArrayList<>(actualModel.getFilteredMusicianList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredMusicianList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the musician at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showMusicianAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMusicianList().size());

        Musician musician = model.getFilteredMusicianList().get(targetIndex.getZeroBased());
        final String[] splitName = musician.getName().fullName.split("\\s+");
        model.updateFilteredMusicianList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredMusicianList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the band at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showBandAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBandList().size());

        Band band = model.getFilteredBandList().get(targetIndex.getZeroBased());
        final String bandName = band.getName().fullName;
        model.updateFilteredBandList(new BandNameContainsKeywordsPredicate(bandName));

        assertEquals(1, model.getFilteredBandList().size());
    }

}
