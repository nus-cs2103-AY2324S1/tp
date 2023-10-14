package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.*;
import seedu.address.model.tag.Subject;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // missing sort in prefix
        assertParseFailure(parser, VALID_SEQUENCE_ASC, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SEQUENCE_ASC, expectedMessage);
    }
    
}
