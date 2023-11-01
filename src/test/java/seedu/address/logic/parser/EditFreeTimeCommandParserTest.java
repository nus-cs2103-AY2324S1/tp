package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FREETIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FREETIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FROM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FREETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TO_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditPersonDescriptorBuilder;


public class EditFreeTimeCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFreeTimeCommand.MESSAGE_USAGE);

    private final EditFreeTimeCommandParser parser = new EditFreeTimeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, FREETIME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + FREETIME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + FREETIME_DESC_BOB, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid free time, from time is after to time
        assertThrows(ParseException.class, () -> parser.parse("1 d/ 2" + INVALID_FREETIME_DESC));

        // invalid day, not within range 1 - 5
        assertThrows(ParseException.class, () -> parser.parse("1 d/ 6" + FROM_DESC_BOB + TO_DESC_BOB));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = "2" + FREETIME_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        EditFreeTimeCommand expectedCommand = new EditFreeTimeCommand(targetIndex, VALID_DAY_BOB, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
