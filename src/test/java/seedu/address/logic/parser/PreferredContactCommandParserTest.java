package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_CONTACT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PreferredContactCommand;
import seedu.address.model.person.PreferredContact;

public class PreferredContactCommandParserTest {
    private PreferredContactCommandParser parser = new PreferredContactCommandParser();
    private final String nonEmptyPreferredContact = "phone";

    @Test
    public void parse_indexSpecified_success() {
        // have PreferredContact
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PREFERRED_CONTACT + nonEmptyPreferredContact;
        PreferredContactCommand expectedCommand = new PreferredContactCommand(targetIndex,
                new PreferredContact(nonEmptyPreferredContact));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no PreferredContact
        userInput = targetIndex.getOneBased() + " " + PREFIX_PREFERRED_CONTACT;
        expectedCommand = new PreferredContactCommand(targetIndex, new PreferredContact(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PreferredContactCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, PreferredContactCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, PreferredContactCommand.COMMAND_WORD + " " + nonEmptyPreferredContact,
                expectedMessage);
    }
}
