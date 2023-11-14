package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.commands.CommandTestUtil;
import connectify.logic.commands.EditCompanyCommand;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.testutil.EditCompanyDescriptorBuilder;
import connectify.testutil.TypicalIndexes;

public class EditCompanyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCompanyCommand.MESSAGE_USAGE);

    private EditCompanyCommandParser parser = new EditCompanyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_COMPANY_NAME_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCompanyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC,
                CompanyName.MESSAGE_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_PHONE_DESC,
                CompanyPhone.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_EMAIL_DESC,
                CompanyEmail.MESSAGE_CONSTRAINTS);
        // invalid address
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_ADDRESS_DESC,
                CompanyAddress.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_COMPANY;
        String userInput = targetIndex.getOneBased()
                + " " + PREFIX_NAME + CommandTestUtil.VALID_COMPANY_NAME_B
                + " " + PREFIX_PHONE + CommandTestUtil.VALID_COMPANY_PHONE_B
                + " " + PREFIX_EMAIL + CommandTestUtil.VALID_COMPANY_EMAIL_B
                + " " + PREFIX_ADDRESS + CommandTestUtil.VALID_COMPANY_ADDRESS_B;

        EditCompanyCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withName(CommandTestUtil.VALID_COMPANY_NAME_B)
                .withPhone(CommandTestUtil.VALID_COMPANY_PHONE_B)
                .withEmail(CommandTestUtil.VALID_COMPANY_EMAIL_B)
                .withAddress(CommandTestUtil.VALID_COMPANY_ADDRESS_B)
                .build();

        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = "1" + " " + PREFIX_NAME + CommandTestUtil.VALID_COMPANY_NAME_A
                + " " + PREFIX_PHONE + CommandTestUtil.VALID_COMPANY_PHONE_B;

        EditCompanyCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withName(CommandTestUtil.VALID_COMPANY_NAME_A)
                .withPhone(CommandTestUtil.VALID_COMPANY_PHONE_B)
                .build();

        EditCompanyCommand expectedCommand = new EditCompanyCommand(INDEX_FIRST_COMPANY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = "1" + CommandTestUtil.NAME_DESC_A;
        EditCompanyCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withName(CommandTestUtil.VALID_COMPANY_NAME_A).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(TypicalIndexes.INDEX_FIRST_COMPANY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
