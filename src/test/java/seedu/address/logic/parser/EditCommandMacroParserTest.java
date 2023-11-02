package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.person.lead.LeadType;

public class EditCommandMacroParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommandMacroParser.MACRO_MESSAGE_USAGE);

    private EditCommandMacroParser badCommandWordParser = new EditCommandMacroParser("edit");
    private EditCommandMacroParser goodCommandWordParser = new EditCommandMacroParser("lead");

    @Test
    public void parse_validCommandWord_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        descriptor.setLead(new Lead(LeadType.HOT));
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        String userInput = "1 HOT";

        assertParseSuccess(goodCommandWordParser, userInput, expectedCommand);
    }

    @Test
    public void parse_validCommandWordInvalidInput_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        descriptor.setLead(new Lead(LeadType.HOT));
        String userInput = "1 medium";

        assertParseFailure(goodCommandWordParser, userInput, Lead.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidCommandWord_failure() {
        String userInput = "1 HOT";

        assertParseFailure(badCommandWordParser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidCommandWordInvalidInput_failure() {
        String userInput = "1 medium";

        assertParseFailure(badCommandWordParser, userInput, MESSAGE_INVALID_FORMAT);
    }
}
