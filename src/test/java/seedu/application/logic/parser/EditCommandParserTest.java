package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.COMPANY_DESC_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.COMPANY_DESC_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.application.logic.commands.CommandTestUtil.ROLE_DESC_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CHEF;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.application.testutil.TypicalIndexes.INDEX_THIRD_JOB;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.EditCommand;
import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.model.job.Company;
import seedu.application.model.job.Role;
import seedu.application.testutil.EditJobDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ROLE_CHEF, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ROLE_DESC_CHEF, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ROLE_DESC_CHEF, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid role
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid company

        // invalid role followed by valid company
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + COMPANY_DESC_CHEF, Role.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + INVALID_COMPANY_DESC,
                Role.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_JOB;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_CLEANER + ROLE_DESC_CHEF;

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withRole(VALID_ROLE_CHEF)
                .withCompany(VALID_COMPANY_CLEANER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // To be implemented once more fields are added
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_JOB;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_CLEANER + ROLE_DESC_CHEF;

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withCompany(VALID_COMPANY_CLEANER)
                .withRole(VALID_ROLE_CHEF).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // role
        Index targetIndex = INDEX_THIRD_JOB;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_CHEF;
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withRole(VALID_ROLE_CHEF).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = targetIndex.getOneBased() + COMPANY_DESC_CHEF;
        descriptor = new EditJobDescriptorBuilder().withCompany(VALID_COMPANY_CHEF).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_THIRD_JOB;
        String userInput = targetIndex.getOneBased() + INVALID_COMPANY_DESC + COMPANY_DESC_CLEANER;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + COMPANY_DESC_CLEANER + INVALID_COMPANY_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + COMPANY_DESC_CHEF + COMPANY_DESC_CHEF + COMPANY_DESC_CLEANER;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_COMPANY_DESC + INVALID_COMPANY_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));
    }
}
