package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONSULTATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToConsultCommand;
import seedu.address.logic.commands.AddToConsultCommand.AddToConsultationDescriptor;
import seedu.address.model.person.Name;
import seedu.address.testutil.AddToConsultationDescriptorBuilder;

public class AddToConsultCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddToConsultCommand.MESSAGE_USAGE);

    private AddToConsultCommandParser parser = new AddToConsultCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddToConsultCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidName_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // multiple invalid name
    }

    @Test
    public void parse_validName_success() {
        Index targetIndex = INDEX_FIRST_CONSULTATION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder()
                .withNames(VALID_NAME_AMY).build();
        AddToConsultCommand expectedCommand = new AddToConsultCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validMultipleNames_success() {
        Index targetIndex = INDEX_FIRST_CONSULTATION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + NAME_DESC_BOB;
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder()
                .withNames(VALID_NAME_AMY, VALID_NAME_BOB).build();
        AddToConsultCommand expectedCommand = new AddToConsultCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        Index targetIndex = INDEX_FIRST_CONSULTATION;
        String userInput = targetIndex.getOneBased() + " i/JohnDoe";

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}
