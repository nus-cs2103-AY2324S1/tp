package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_COMMAND_TYPE_ADD;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_NAME_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_COMMAND_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_NAME_DESC_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DepartmentCommand;
import seedu.address.model.department.Department;
import seedu.address.model.name.DepartmentName;
import seedu.address.testutil.DepartmentBuilder;

public class DepartmentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DepartmentCommand.MESSAGE_USAGE);

    private DepartmentCommandParser parser = new DepartmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no type
        assertParseFailure(parser, "name", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, DEPARTMENT_COMMAND_TYPE_ADD + INVALID_DEPARTMENT_NAME_DESC_EMPTY,
                DepartmentName.MESSAGE_CONSTRAINTS); // empty name
        assertParseFailure(parser, DEPARTMENT_COMMAND_TYPE_ADD + INVALID_DEPARTMENT_NAME_DESC,
                DepartmentName.MESSAGE_CONSTRAINTS); // invalid name
        // invalid command type followed by valid department name
        assertParseFailure(parser, INVALID_DEPARTMENT_COMMAND_TYPE + DEPARTMENT_NAME_INVESTMENT,
                DepartmentCommand.MESSAGE_UNDEFINED_ACTION_TYPE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = DEPARTMENT_COMMAND_TYPE_ADD + DEPARTMENT_NAME_INVESTMENT;
        DepartmentCommand.Type type = DepartmentCommand.Type.ADD;
        Department descriptor = new DepartmentBuilder().withName(VALID_DEPARTMENT_INVESTMENT).build();
        DepartmentCommand expectedCommand = new DepartmentCommand(descriptor, type);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
