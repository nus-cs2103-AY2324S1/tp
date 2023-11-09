package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.InteractionCommand;
import seedu.address.model.person.interaction.Interaction;

public class InteractionCommandParserTest {

    private final InteractionCommandParser parser = new InteractionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_OUTCOME + "INTERESTED note about interaction";
        Interaction interaction = new Interaction("note about interaction", Interaction.Outcome.INTERESTED);
        InteractionCommand expectedCommand = new InteractionCommand(targetIndex, interaction);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + " note about interaction";
        Interaction interaction = new Interaction("note about interaction", Interaction.Outcome.UNKNOWN);
        InteractionCommand expectedCommand = new InteractionCommand(targetIndex, interaction);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                InteractionCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser,
                CliSyntax.PREFIX_OUTCOME + "INTERESTED note about interaction",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser,
                "0 " + CliSyntax.PREFIX_OUTCOME + "INTERESTED note about interaction",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, InteractionCommand.MESSAGE_USAGE));

        // invalid outcome
        assertParseFailure(parser,
                "1 " + CliSyntax.PREFIX_OUTCOME + "notAnOutcome note about interaction",
                Interaction.Outcome.MESSAGE_CONSTRAINTS);
    }
}
