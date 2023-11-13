package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.commands.CommandTestUtil.COMPANY_DESC_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.COMPANY_DESC_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_CYCLE_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.letsgethired.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_REJECTED;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.letsgethired.testutil.TypicalInternApplications.A;
import static seedu.letsgethired.testutil.TypicalInternApplications.B;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.commands.AddCommand;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;
import seedu.letsgethired.testutil.InternApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        InternApplication expectedInternApplication = new InternApplicationBuilder(B).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + COMPANY_DESC_BYTEDANCE
                        + ROLE_DESC_BACK_END
                        + CYCLE_DESC_WINTER
                        + STATUS_DESC_REJECTED,
                new AddCommand(expectedInternApplication));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        InternApplication expectedInternApplication = new InternApplicationBuilder(A).build();
        assertParseSuccess(parser, COMPANY_DESC_JANE_STREET
                        + ROLE_DESC_FULL_STACK + CYCLE_DESC_SUMMER
                        + STATUS_DESC_ACCEPTED,
                new AddCommand(expectedInternApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser, VALID_COMPANY_BYTEDANCE
                        + ROLE_DESC_BACK_END
                        + CYCLE_DESC_WINTER,
                expectedMessage);

        // missing role prefix
        assertParseFailure(parser, COMPANY_DESC_BYTEDANCE
                        + VALID_ROLE_BACK_END
                        + CYCLE_DESC_WINTER,
                expectedMessage);

        // missing cycle prefix
        assertParseFailure(parser, COMPANY_DESC_BYTEDANCE
                        + ROLE_DESC_BACK_END
                        + VALID_CYCLE_WINTER,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_BYTEDANCE
                        + VALID_ROLE_BACK_END
                        + VALID_CYCLE_WINTER,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC
                        + ROLE_DESC_BACK_END
                        + CYCLE_DESC_WINTER
                        + STATUS_DESC_REJECTED,
                Company.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, COMPANY_DESC_BYTEDANCE
                        + INVALID_ROLE_DESC
                        + CYCLE_DESC_WINTER
                        + STATUS_DESC_REJECTED,
                Role.MESSAGE_CONSTRAINTS);

        // invalid cycle
        assertParseFailure(parser, COMPANY_DESC_BYTEDANCE
                        + ROLE_DESC_BACK_END
                        + INVALID_CYCLE_DESC
                        + STATUS_DESC_REJECTED,
                Cycle.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, COMPANY_DESC_BYTEDANCE
                        + ROLE_DESC_BACK_END
                        + CYCLE_DESC_WINTER
                        + INVALID_STATUS_DESC,
                Status.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_DESC
                        + ROLE_DESC_BACK_END
                        + CYCLE_DESC_WINTER
                        + INVALID_STATUS_DESC,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + COMPANY_DESC_BYTEDANCE
                        + ROLE_DESC_BACK_END
                        + CYCLE_DESC_WINTER
                        + STATUS_DESC_REJECTED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
