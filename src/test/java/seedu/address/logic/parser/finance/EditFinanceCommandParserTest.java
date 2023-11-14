package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_TIME_DUE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_DUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.finance.EditFinanceCommand;
import seedu.address.logic.commands.finance.EditFinanceCommand.EditFinanceDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.TimeDue;
import seedu.address.testutil.EditFinanceDescriptorBuilder;

public class EditFinanceCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFinanceCommand.MESSAGE_USAGE);

    private EditFinanceCommandParser parser = new EditFinanceCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditFinanceCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_AMOUNT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DESCRIPTION, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT, Amount.MESSAGE_CONSTRAINTS); // invalid amount
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + createMoreThanAllowedString(),
                Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + INVALID_TIME_DUE, TimeDue.MESSAGE_CONSTRAINTS); // invalid time due
        // invalid amount followed by valid description
        assertParseFailure(parser, "1" + INVALID_AMOUNT + VALID_DESCRIPTION, Amount.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_AMOUNT + INVALID_CLIENT,
                Amount.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DEFAULT_TIME_DUE
                + DEFAULT_AMOUNT + DEFAULT_DESCRIPTION;

        EditFinanceDescriptor descriptor = new EditFinanceDescriptorBuilder().withAmount(VALID_AMOUNT)
                .withDescription(VALID_DESCRIPTION)
                .withTimeDue(VALID_TIME_DUE).build();
        EditFinanceCommand expectedCommand = new EditFinanceCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + DEFAULT_AMOUNT + DEFAULT_DESCRIPTION;

        EditFinanceDescriptor descriptor = new EditFinanceDescriptorBuilder().withAmount(VALID_AMOUNT)
                .withDescription(VALID_DESCRIPTION).build();
        EditFinanceCommand expectedCommand = new EditFinanceCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() throws ParseException {
        // amount
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + DEFAULT_AMOUNT;
        EditFinanceDescriptor descriptor = new EditFinanceDescriptorBuilder().withAmount(VALID_AMOUNT).build();
        EditFinanceCommand expectedCommand = new EditFinanceCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DEFAULT_DESCRIPTION;
        descriptor = new EditFinanceDescriptorBuilder().withDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditFinanceCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time due
        userInput = targetIndex.getOneBased() + DEFAULT_TIME_DUE;
        descriptor = new EditFinanceDescriptorBuilder().withTimeDue(VALID_TIME_DUE).build();
        expectedCommand = new EditFinanceCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + DEFAULT_DESCRIPTION + INVALID_AMOUNT;

        assertParseFailure(parser, userInput, Amount.MESSAGE_CONSTRAINTS);

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC + createMoreThanAllowedString()
                + VALID_AMOUNT;

        assertParseFailure(parser, userInput, Description.MESSAGE_CONSTRAINTS);

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + DEFAULT_AMOUNT + DEFAULT_AMOUNT + DEFAULT_DESCRIPTION
                + DEFAULT_DESCRIPTION + DEFAULT_TIME_DUE;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT, PREFIX_DESCRIPTION));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_AMOUNT + INVALID_CLIENT + INVALID_DESCRIPTION_DESC
                + INVALID_DESCRIPTION_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }

    private String createMoreThanAllowedString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 257; i++) {
            sb.append("a");
        }
        return sb.toString();
    }
}
