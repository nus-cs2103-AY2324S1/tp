package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.commands.CommandTestUtil.COMPANY_DESC_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_CYCLE_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_REJECTED;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_ACCEPTED;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.EditCommand;
import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_JANE_STREET, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_JANE_STREET, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_JANE_STREET, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_CYCLE_DESC, Cycle.MESSAGE_CONSTRAINTS); // invalid cycle
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status

        // invalid role followed by valid cycle
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + CYCLE_DESC_SUMMER, Role.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC + INVALID_CYCLE_DESC
                        + VALID_STATUS_ACCEPTED + VALID_ROLE_FULL_STACK, Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased()
                + ROLE_DESC_BACK_END
                + CYCLE_DESC_SUMMER
                + STATUS_DESC_ACCEPTED
                + COMPANY_DESC_JANE_STREET;

        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_JANE_STREET)
                .withRole(VALID_ROLE_BACK_END).withCycle(VALID_CYCLE_SUMMER)
                .withStatus(VALID_STATUS_ACCEPTED)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_BACK_END + CYCLE_DESC_SUMMER;

        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder()
                .withRole(VALID_ROLE_BACK_END)
                .withCycle(VALID_CYCLE_SUMMER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_JANE_STREET;
        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_JANE_STREET).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_FULL_STACK;
        descriptor = new EditInternApplicationDescriptorBuilder().withRole(VALID_ROLE_FULL_STACK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // cycle
        userInput = targetIndex.getOneBased() + CYCLE_DESC_SUMMER;
        descriptor = new EditInternApplicationDescriptorBuilder().withCycle(VALID_CYCLE_SUMMER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_ACCEPTED;
        descriptor = new EditInternApplicationDescriptorBuilder().withStatus(VALID_STATUS_ACCEPTED).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + ROLE_DESC_BACK_END;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + ROLE_DESC_BACK_END + INVALID_ROLE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + ROLE_DESC_FULL_STACK + STATUS_DESC_ACCEPTED + CYCLE_DESC_SUMMER
                + ROLE_DESC_FULL_STACK + STATUS_DESC_ACCEPTED + CYCLE_DESC_SUMMER
                + ROLE_DESC_BACK_END + STATUS_DESC_REJECTED + CYCLE_DESC_WINTER;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + INVALID_STATUS_DESC + INVALID_CYCLE_DESC
                + INVALID_ROLE_DESC + INVALID_STATUS_DESC + INVALID_CYCLE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS));
    }
}
